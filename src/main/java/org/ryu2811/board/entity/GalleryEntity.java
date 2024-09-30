package org.ryu2811.board.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Builder
@Data // immutable 관련 메소드만 포함
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class GalleryEntity {
  @Id // primary key임을 표시
  @GeneratedValue(strategy = GenerationType.IDENTITY) // java 내부적으로 시퀀스 관리
  private int idx;
  @Column(nullable = false) // 기본적으로 nullable
  private String title;
  private String fileNames;
  private String writer;
  @CreatedDate
  private LocalDateTime createdAt;
}
