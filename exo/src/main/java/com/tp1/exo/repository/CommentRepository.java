package com.tp1.exo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp1.exo.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // جلب جميع التعليقات الخاصة بمقال معين
    List<Comment> findByArticleId(Long articleId);
}
