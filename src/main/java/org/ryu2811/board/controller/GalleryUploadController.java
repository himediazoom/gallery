package org.ryu2811.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ryu2811.board.dto.GalleryDto;
import org.ryu2811.board.service.GalleryUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GalleryUploadController {
  private final GalleryUploadService svc;

  @GetMapping("/gallery")
  public String gallery(Model model) {
    GalleryDto dto = svc.findOne(3);
    if (dto == null) {dto = new GalleryDto();}
    model.addAttribute("dto", dto);
    return "gallery";
  }

  @PostMapping("/gallery")
  public String upload (GalleryDto dto) throws IOException {
    svc.save (dto);

    return "redirect:/gallery";
  }

  @GetMapping("/galleries")
  public String galleries(Model model) {
    //전체 리스트 가져오기
    List<GalleryDto> list = svc.list();
    model.addAttribute("list", list);
    return "galleries";
  }

  @PostMapping("/galleries")
  public String uploadMany(GalleryDto dto) throws IOException {
    //하나의 요청으로 여러개의 파일을 업로드
    svc.uploadManyFile(dto);
    return "redirect:/galleries";
  }
}

/*
1) MultipartFile (DTO)
MultipartFile은 스프링 프레임워크에서 제공하는 인터페이스로,
HTTP 요청에서 전송된 파일 데이터를 처리하기 위한 객체입니다.
파일 업로드 시 클라이언트가 전송한 파일은 서버 측에서 MultipartFile 객체로 매핑되어 사용됩니다.

주요 메서드
getBytes(): 파일 데이터를 바이트 배열로 반환합니다.
getInputStream(): 파일 데이터를 읽기 위한 InputStream을 반환합니다.
getOriginalFilename(): 클라이언트가 업로드한 파일의 원본 파일 이름을 반환합니다.
getSize(): 파일의 크기를 반환합니다.
isEmpty(): 파일이 비어있는지 확인합니다.
transferTo(File dest): 업로드된 파일을 지정한 경로로 직접 저장합니다.

2) enctype="multipart/form-data" 설명 (HTML)
enctype은 HTML 폼(POST)에서 데이터를 서버로 전송할 때 사용하는 인코딩 방식을 정의하는 속성
지금까지  form 은 application/x-www-form-urlencoded 방식으로 전송(텍스트만 전송함)
파일 업로드를 위해서는 enctype="multipart/form-data"를 사용합니다.

multipart/form-data는 텍스트 데이터, 이진 데이터(미디어 파일 등) 다양한 형식 정송.
텍스트 필드와 파일을 동시에 전송하는 복합적인 폼 데이터를 처리할 수 있습니다.
파일 업로드 할 때 반드시 enctype="multipart/form-data"로 설정해야 합니다

3) input 요소 accept 속성 (type="file")
MIME 타입: 인터넷 상에서 파일을 전송할 때 파일의 타입을 나타내는 표준화된 형식입니다.
image/* 모든 이미지를 나타냄. image/jpeg는 JPEG 이미지를 나타냄.

파일 확장자: 파일의 이름 끝에 붙는 부분으로, 파일의 형식을 나타냅니다.
.jpg, .png, .pdf 같은 확장자는 파일의 유형을 구분하는 역할을 합니다.

4) MultipartFile 을 서버에 저장
   위치: C:\Users\ryu2811\Desktop\Uploaded
 */