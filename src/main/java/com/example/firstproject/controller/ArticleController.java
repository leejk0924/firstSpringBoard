package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
@Slf4j          // 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired          // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결!
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }


    // /articles/new 에서 던진 정보를 DTO에 받는다.
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());
//        System.out.println(form.toString());  -> 서버에서 println 은 성능 저하시킨다. -> 로깅으로 대체

        // 1. Dto를 변환! Entity!
        Article article = form.toEntity();
//        System.out.println(article.toString());
        log.info(article.toString());

        // 2. Repository에게 Entity를 DB안에 저장하게 함!

        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());
        log.info(saved.toString());
//        return "redirect:/articles/"+saved.getId();
        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        // id로 데이터를 가져온다.
//        Optional<Article> articleEntity = articleRepository.findById(id);
        // optional 기능을 사용할 수 없다면, 아래와 같이 구현
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // orElse : id 값을 찾았는데 해당 id 값이 없다면 null 을 반환하라 -> 값이 있거나 없으면 null 반환
        List<CommentDto> commentDtos = commentService.comments(id);

        // 2. 가져온 데이터를 모델에 등록한다.
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);

        // 3. 보여줄 페이지 설정
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model) {
        // 1. 모든 Article 을 가져온다.
        // 타입 불일치 문제 해결 (Repository 의 ArticleRepository 에서 findAll() 의 타입을 오버라이드 해줌)
        List<Article> articleEntityList = articleRepository.findAll();

//        Iterable<Article> articleEntityList = articleRepository.findAll();

        // 2. 가져온 Article 묶음을 뷰로 전달 (모델 사용)
        model.addAttribute("articleList", articleEntityList);


        // 3. 뷰 페이지 설정
        return "/articles/index";        // articles/index.mustache
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // 수정할 데이터를 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터 등록!
        model.addAttribute("article", articleEntity);

        // 뷰 페이지 설정
       return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());

        // 1. DTO를 Entity로 변환하다.
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        // 2. Entity를 DB로 저장한다.

        // 2-1. DB에서 기존 데이터를 가져온다.
//        Optional<Article> target = articleRepository.findById(articleEntity.getId());
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2-2. 기존 데이터에 값을 갱신한다.
        if (target != null) {
            articleRepository.save(articleEntity);  // Entity 가 DB로 갱신
        }

        // 3. 수정 결과 페이지로 리다이렉트 한다.

        return "redirect:/articles/"+articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")            // 삭제 요청을 받음 (원래는 DeleteMapping이어야 한다.)
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {      // PathVariable 을 통해서 id 값을 받음
        log.info("삭제 요청이 들어왔습니다.");

        // 1. 삭제 대상을 가져온다.
        // DB와 일을하려면 Repository를 통해서 일을 해야한다~
         Article target = articleRepository.findById(id).orElse(null);          // DB에서 대상 검색

        // 2. 대상을 삭제한다.
        if (target != null) {
            articleRepository.delete(target);               // Repository의 delete메서드를 통해 삭제
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
            // 한번 사용하고 사라지는 데이터 : addFlashAttribute
        }

        // 3. 결과 페이지로 리다이렉트 한다.

        return "redirect:/articles";

    }
}
