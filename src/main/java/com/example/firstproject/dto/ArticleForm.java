package com.example.firstproject.dto;


import com.example.firstproject.entity.Article;
import lombok.*;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@NoArgsConstructor
@Setter
@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;

    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
}

/*
자바 bean은 컴포넌트 스캔하여 객체를 미리 가져가고 @Autowired로 주입시켜준다.

@Autowired 말고 간단하게 작성하는 방법

필드값에 final을 붙이면 초기화가 필요하다. 이를 이용하여 @RequiredArgsConstructor 어노테이션을 사용하면
초기화 되지 않은 생성자를 호출할 때 초기화 해준다.

참고 : https://www.youtube.com/watch?v=7Qteld8sInM
 */
