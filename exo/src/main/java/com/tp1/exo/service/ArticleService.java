package com.tp1.exo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tp1.exo.entity.Article;
import com.tp1.exo.repository.ArticleRepository;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // جلب جميع المقالات
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    // جلب مقال حسب ID
    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    // حفظ مقال
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    // حذف مقال
    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }
}
