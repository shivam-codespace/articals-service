package in.portfolio.controller;


 
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

    private final ArticleService service;

    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Article> create(@RequestBody ArticleRequest request) {
        return ResponseEntity.ok(service.createArticle(request));
    }

    @GetMapping
    public List<Article> getAll() {
        return service.getAllArticles();
    }

    @GetMapping("/{slug}")
    public ArticleResponse getBySlug(@PathVariable String slug) {
        return service.getArticleBySlug(slug);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}
