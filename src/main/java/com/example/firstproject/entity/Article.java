package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity         // DB가 해당 객체를 인식 가능하게 해준다. (해당 클래스로 테이블 생성)
public class Article {
    // 대표값을 넣어줘야한다!

    @Id                     // 대표값 지정
//    @GeneratedValue()         // 1,2,3 .... 자동생성 어노테이션!
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // DB가 id를 자동 생성 어노테이션
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article article) {
        if (article.title != null) {
            this.title = article.title;
        }
        if (article.content != null) {
            this.content = article.content;
        }
    }

    /*
    [ 에러 발생  Class 'Article' should have [public, protected] no-arg constructor ]

    JPA 사용하다 보면 위와 같은 에러가 발생하는 경우가 있다.
    이는 @Entity, @Embeddable 어노테이션을 사용하면 이 클래스는 테이블과 매핑할 클래스라는 것을 명시해 준다.
    이때 반드시 기본 생성자(no-arg constructor) 가 필수적이다.

    그러니 @RequiredArgsConstructor 어노테이션을 붙여주던가 아니면 기본 생성자를 입력해주자.
     */
}
