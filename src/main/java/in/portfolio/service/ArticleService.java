package in.portfolio.service;



 
import org.springframework.stereotype.Service;

import in.portfolio.dato.ArticleRequest;
import in.portfolio.dato.ArticleResponse;
import in.portfolio.model.Article;
import in.portfolio.repo.ArticleRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository repository;
    private final GithubMarkdownService githubService;

    public ArticleService(ArticleRepository repository,
                          GithubMarkdownService githubService) {
        this.repository = repository;
        this.githubService = githubService;
    }

    public Article createArticle(ArticleRequest request) {

        Article article = Article.builder()
                .title(request.getTitle())
                .slug(generateSlug(request.getTitle()))
                .description(request.getDescription())
                .githubRawUrl(request.getGithubRawUrl())
                .keywords(request.getKeywords())
                .createdAt(LocalDateTime.now())
                .build();

        return repository.save(article);
    }

    public List<Article> getAllArticles() {
        return repository.findAll();
    }

    public ArticleResponse getArticleBySlug(String slug) {

        Article article = repository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        String markdownContent =
                githubService.fetchMarkdown(article.getGithubRawUrl());

        return ArticleResponse.builder()
                .title(article.getTitle())
                .description(article.getDescription())
                .content(markdownContent)
                .build();
    }

    public void deleteArticle(String id) {
        repository.deleteById(id);
    }

    private String generateSlug(String title) {
        return title.toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("(^-|-$)", "");
    }
}
