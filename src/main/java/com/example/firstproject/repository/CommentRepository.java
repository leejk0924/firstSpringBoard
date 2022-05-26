package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// JPA 리포지터리 : Paging 처리 및 Sorting 처리 하는 리포지터리
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글의 모든 댓글 조회

    @Query(value = "SELECT *"+
            " FROM comment"+
            " WHERE article_id = :articleId",
            nativeQuery = true)
    List<Comment> findByArticleId(@Param("articleId")Long articleId);            // @Query의 :articled 와 (Long articleId의 이름이 같아야지 참조 된다.)
    // @Param("articleId") 입력해줘야지 Comment Test에서 파라미터 값을 찾아온다.

    // 특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(@Param("nickname")String nickname);
}
