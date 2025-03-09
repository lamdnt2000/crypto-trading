package com.lamdnt.cryptotrading.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@MappedSuperclass
public abstract class Auditing<T> implements Serializable {
  public abstract T getId();

  @Column(name = "created_at")
  @CreatedDate
  private LocalDateTime createdAt = LocalDateTime.now();

  @Column(name = "updated_at")
  @LastModifiedDate
  private LocalDateTime updatedAt = LocalDateTime.now();

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "updated_by")
  private String updatedBy;
}
