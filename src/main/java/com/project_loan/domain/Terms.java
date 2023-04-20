package com.project_loan.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Setter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted = false")
public class Terms extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long termsId;

    @Column(columnDefinition = "varchar(255) NOT NULL COMMENT '약관'")
    private String name;

    @Column(columnDefinition = "varchar(255) NOT NULL COMMENT '약관상세 URL'")
    private String termsDetailUrl;
}
