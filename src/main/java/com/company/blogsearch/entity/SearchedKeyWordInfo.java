package com.company.blogsearch.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DynamicInsert
@Table(indexes = @Index(name = "idx_key_word", columnList = "keyWord"))
@Entity(name = "SRCH_KEY_WORD_INFO")
@EntityListeners(AuditingEntityListener.class)
public class SearchedKeyWordInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seqNo;

    private String keyWord;
    private Long srchCnt;

    @LastModifiedDate
    private LocalDateTime modDate;

    @CreatedDate
    private LocalDateTime inDate;


}
