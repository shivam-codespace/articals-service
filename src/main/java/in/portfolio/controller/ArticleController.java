package in.portfolio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.portfolio.dato.ArticleRequest;
import in.portfolio.dato.ArticleResponse;
import in.portfolio.model.Article;
import in.portfolio.service.ArticleService;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@CrossOrigin
public class ArticleController {

    private static final Logger logger =
            LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService service;

    public ArticleController(ArticleService service) {
        this.service = service;
        logger.info("ArticleController initialized");
    }

    @PostMapping
    public ResponseEntity<Article> create(@RequestBody ArticleRequest request) {
        logger.info("Creating article with title: {}", request.getTitle());
        Article article = service.createArticle(request);
        logger.info("Article created successfully with ID: {}", article.getId());
        return ResponseEntity.ok(article);
    }

    @GetMapping
    public List<Article> getAll() {
        logger.info("Fetching all articles");
        List<Article> articles = service.getAllArticles();
        logger.info("Total articles fetched: {}", articles.size());
        return articles;
    }

    @GetMapping("/{slug}")
    public ArticleResponse getBySlug(@PathVariable String slug) {
        logger.info("Fetching article by slug: {}", slug.toLowerCase());
        ArticleResponse response = service.getArticleBySlug(slug.toLowerCase());
        logger.info("Article fetched successfully for slug: {}", slug.toLowerCase());
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        logger.warn("Deleting article with ID: {}", id);
        service.deleteArticle(id);
        logger.info("Article deleted successfully with ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}
