package challenge.preonboarding.wanted.global.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @LastModifiedDate
    @Column(name = "MOD_DATE", nullable = false, updatable = true, columnDefinition = "TIMESTAMP")
    @Comment("최종 수정일")
    private LocalDateTime lastModifiedAt;

    @CreationTimestamp
    @Column(name = "INS_DATE", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    @Comment("생성일")
    private LocalDateTime createdAt;
}
