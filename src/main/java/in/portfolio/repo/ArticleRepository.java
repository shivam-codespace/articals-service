package in.portfolio.repo;

 

 
import org.springframework.data.mongodb.repository.MongoRepository;

import in.portfolio.model.Article;

import java.util.Optional;

public interface ArticleRepository extends MongoRepository<Article, String> {
    Optional<Article> findBySlug(String slug);
}

