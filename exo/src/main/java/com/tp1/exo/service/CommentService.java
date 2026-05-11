package com.tp1.exo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tp1.exo.entity.Comment;
import com.tp1.exo.repository.CommentRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // جلب جميع التعليقات
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    // جلب تعليق حسب ID
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    // جلب جميع التعليقات الخاصة بمقال معين
    public List<Comment> findByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    // حفظ تعليق
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    // حذف تعليق
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
