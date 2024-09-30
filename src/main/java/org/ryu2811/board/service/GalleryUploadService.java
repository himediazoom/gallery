package org.ryu2811.board.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ryu2811.board.dto.GalleryDto;
import org.ryu2811.board.entity.GalleryEntity;
import org.ryu2811.board.repository.GalleryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Slf4j
@RequiredArgsConstructor
@Service
public class GalleryUploadService {
  private final String DIR_DEST = "D:/Works/HiMedia/12.SpringBoot/FileUploadTest";

  private final GalleryRepository dao;

  @Transactional
  public void save (GalleryDto dto) throws IOException {
    MultipartFile file = dto.getFile();
    log.info ("fileName: {}", file.getOriginalFilename());
    log.info ("fileSize: {}", file.getSize());

    if (file.getSize() > 0) {
      // MultipartFile -> java.io.file -> save
      File toSave = new File(DIR_DEST + File.separator + file.getOriginalFilename());
      file.transferTo(toSave);
      dto.setFileNames(file.getOriginalFilename());
      log.info("UPLOAD DTO: {}", dto.toString());
      dao.save(dto.toEntity());
    }
  }

  GalleryDto dto = null;
  public GalleryDto findOne(int i) {
    Optional<GalleryEntity> optional = dao.findById(i);
    /*
    optional.ifPresent(o -> {
      GalleryEntity entity = optional.get();
      dto = GalleryDto.of(entity);  // lambda는 함수형 인터페이스(추상 method가 1개인 인터페이스)로
                                    // 익명 클래스의 method로 구현되어 global 변수만 접근 가능.
    });
     */

    return optional.map(GalleryDto::of).orElse(null);
  }

  //upload 파일 여러개 가져와서 저장하기 : 파일명 여러개를 , 로 구분. 나열한 값을 db에 저장
  public void uploadManyFile(GalleryDto dto) throws IOException {
    List<MultipartFile> files = dto.getFileS();
    StringBuilder fnBuilder = new StringBuilder();
    for(MultipartFile file : files){
      if(file.getSize() !=0){
        //서버디렉토리에 저장은 java.io.File 객체를 생성합니다.
        File pathFile = new File(DIR_DEST + "/" + file.getOriginalFilename());
        file.transferTo(pathFile);
        //db에 저장할 파일명 저장.
        fnBuilder.append(file.getOriginalFilename()).append(",");
      }
    }
    //db 테이블에 저장될 값 확인
    dto.setFileNames(fnBuilder.toString());
    log.info("dto:{}",dto);
    dao.save(dto.toEntity());

  }

  public List<GalleryDto> list() {
    List<GalleryEntity> entities = dao.findAll();
    return entities.stream().map(GalleryDto::of)
      .collect(Collectors.toList());
  }
}