package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleId) {
        // 조회 : 댓글 목록
//        List<Comment> comments = commentRepository.findByArticleId(articleId);

        // 변환 : Entity -> DTO
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for (int i = 0; i < comments.size(); i++) {
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }
//        return dtos;
        // 위의 코드를 stream 으로 작성


        // 반환
        return commentRepository.findByArticleId(articleId)
                .stream()       // stream 생성
                .map(comment -> CommentDto.createCommentDto(comment))           // 하나하나 꺼내와서 map메서드를 통해서 맵핑, 꺼내진 comment를 CommentDto로 변환
                .collect(Collectors.toList());                                  // CommentDto.createCommentDto(comment)에서 반환한 값은 Stream<Object> 인데 toList()로 묶어준다.
    }
    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        log.info("입력 값 =>{}", articleId);
        log.info("입력 값 =>{}", dto);
        // 게시글 조회 및 예외 처리
         Article article = articleRepository
                 .findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));

        // 댓글 Entity 생성
        Comment comment = Comment.createComment(dto, article);

        // 댓글 Entity를 DB로 저장
        Comment created = commentRepository.save(comment);
        // DTO로 변경하여 변환
//        return CommentDto.createCommentDto(created);
        CommentDto createdDto = CommentDto.createCommentDto(created);
        log.info("반환값 => {}", createdDto);
        return createdDto;
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {

        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));

        // 댓글을 수정
        target.patch(dto);

        // DB로 갱신
        Comment updated = commentRepository.save(target);
        // 댓글 Entity 를 DTO 로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }
    @Transactional
    public CommentDto delete(Long id) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));

        // 댓글 삭제
        commentRepository.delete(target);
        // 삭제 댓글을 DTO로 반환
        return CommentDto.createCommentDto(target);

    }
}
