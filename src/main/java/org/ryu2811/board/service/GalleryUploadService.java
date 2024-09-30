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
import java.util.Optional;

@Data
@Slf4j
@RequiredArgsConstructor
@Service
public class GalleryUploadService {
  private final String DIR_DEST = "D:/Works/HiMedia/11.SpringBoot/FileUploadTest";

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
}