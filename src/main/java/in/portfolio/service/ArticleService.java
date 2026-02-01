package in.portfolio.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import in.portfolio.dato.ArticleRequest;
import in.portfolio.dato.ArticleResponse;
import in.portfolio.model.Article;
import in.portfolio.repo.ArticleRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {

    private static final Logger logger =
            LoggerFactory.getLogger(ArticleService.class);

    private final ArticleRepository repository;
    private final GithubMarkdownService githubService;

    public ArticleService(ArticleRepository repository,
                          GithubMarkdownService githubService) {
        this.repository = repository;
        this.githubService = githubService;
        logger.info("ArticleService initialized");
    }

    public Article createArticle(ArticleRequest request) {

        logger.info("Creating article with title: {}", request.getTitle());

        Article article = Article.builder()
                .title(request.getTitle())
                .slug(generateSlug(request.getTitle()))
                .description(request.getDescription())
                .githubRawUrl(request.getGithubRawUrl())
                .keywords(request.getKeywords())
                .createdAt(LocalDateTime.now())
                .build();

        Article savedArticle = repository.save(article);

        logger.info("Article saved successfully with ID: {} and slug: {}",
                savedArticle.getId(), savedArticle.getSlug());

        return savedArticle;
    }

    public List<Article> getAllArticles() {
        logger.info("Fetching all articles");
        List<Article> articles = repository.findAll();
        logger.info("Fetched {} articles from database", articles.size());
        return articles;
    }

    public ArticleResponse getArticleBySlug(String slug) {

        logger.info("Fetching article by slug: {}", slug);

        Article article = repository.findBySlug(slug)
                .orElseThrow(() -> {
                    logger.error("Article not found for slug: {}", slug);
                    return new RuntimeException("Article not found");
                });

        logger.debug("Fetching markdown from GitHub URL: {}",
                article.getGithubRawUrl());

        String markdownContent =
                githubService.fetchMarkdown(article.getGithubRawUrl());

        logger.info("Markdown content fetched successfully for slug: {}", slug);

        return ArticleResponse.builder()
                .title(article.getTitle())
                .description(article.getDescription())
                .content(markdownContent)
                .build();
    }

    public void deleteArticle(String id) {
        logger.warn("Deleting article with ID: {}", id);
        repository.deleteById(id);
        logger.info("Article deleted successfully with ID: {}", id);
    }

    private String generateSlug(String title) {
        logger.debug("Generating slug for title: {}", title);
        return title.toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("(^-|-$)", "");
    }
}
