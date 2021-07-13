package com.elct.elctchar.web.temp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 프로젝트 설정 확인용 엔티티
 * TODO 테스트 성공 후 삭제예정
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "temp_table")
public class TempEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "test_name")
    private String testName;

    @Column(name = "test_password")
    private String testPassword;

    public TempEntity(String testName, String testPassword) {
        this.testName = testName;
        this.testPassword = testPassword;
    }
}
