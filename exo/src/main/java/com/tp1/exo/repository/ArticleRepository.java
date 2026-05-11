package com.tp1.exo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp1.exo.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
