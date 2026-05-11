package com.tp1.exo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tp1.exo.entity.Article;
import com.tp1.exo.entity.Comment;
import com.tp1.exo.service.ArticleService;
import com.tp1.exo.service.CommentService;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final ArticleService articleService;

    // Constructor Injection
    public CommentController(CommentService commentService,
                             ArticleService articleService) {
        this.commentService = commentService;
        this.articleService = articleService;
    }

    // حفظ تعليق جديد
    @PostMapping("/save/{articleId}")
    public String saveComment(@PathVariable Long articleId,
                              @ModelAttribute Comment comment) {

        Article article = articleService.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article id"));

        comment.setArticle(article);
        commentService.save(comment);

        return "redirect:/articles/" + articleId;
    }

    // حذف تعليق
    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable Long id) {

        Comment comment = commentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment id"));

        Long articleId = comment.getArticle().getId();

        commentService.deleteById(id);

        return "redirect:/articles/" + articleId;
    }
}
