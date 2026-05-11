package com.tp1.exo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tp1.exo.entity.Article;
import com.tp1.exo.entity.Comment;
import com.tp1.exo.service.ArticleService;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    // Constructor Injection
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // عرض جميع المقالات
    @GetMapping
    public String listArticles(Model model) {
        model.addAttribute("articles", articleService.findAll());
        return "articles/list";
    }

    // عرض نموذج إضافة مقال جديد
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("article", new Article());
        return "articles/form";
    }

    // حفظ المقال
    @PostMapping("/save")
    public String saveArticle(@ModelAttribute Article article) {
        articleService.save(article);
        return "redirect:/articles";
    }

    // عرض نموذج تعديل مقال
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Article article = articleService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article id"));
        model.addAttribute("article", article);
        return "articles/form";
    }

    // حذف مقال
    @GetMapping("/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteById(id);
        return "redirect:/articles";
    }

    // عرض تفاصيل مقال واحد مع التعليقات
    @GetMapping("/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        Article article = articleService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article id"));

        model.addAttribute("article", article);
        model.addAttribute("comment", new Comment());

        return "articles/details";
    }
}
