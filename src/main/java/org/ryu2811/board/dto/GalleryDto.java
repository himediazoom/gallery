package org.ryu2811.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ryu2811.board.entity.GalleryEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GalleryDto {
  private int idx;
  private String title;
  private String fileNames;
  private String writer;
  private LocalDateTime createdAt;

  private MultipartFile file;

  // entity -> dto (for Select)
  public static GalleryDto of (final GalleryEntity entity) {
    return GalleryDto.builder()
                     .idx(entity.getIdx())
                     .title(entity.getTitle())
                     .fileNames(entity.getFileNames())
                     .writer(entity.getWriter())
                     .createdAt(entity.getCreatedAt())
                     .build();
  }

  // this -> entity (for DML)
  public GalleryEntity toEntity () {
    return GalleryEntity.builder()
                        .idx(this.idx)
                        .title(this.title)
                        .fileNames(this.fileNames)
                        .writer(this.writer)
                        .createdAt(this.createdAt)
                        .build();
  }
}
