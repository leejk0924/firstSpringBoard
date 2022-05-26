package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     // 일반 컨트롤러는 view 템플릿  페이지를 반환하지만, RestController는 JSON을 반환
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello() {
        return "hello world";
    }



}
/*
@RestController 와 @Controller의 차이는 응답의 Body에서 차이가 난다.
반환하는 타입이 다름
@Controller : View template page (HTML 코드)
@RestController : 단순 문자열, 객체 반환(JSON, XML), 컬레션 타입의 객체 반환(LIST, MAP etc..), ResponseEntity 타입
 */