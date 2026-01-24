package in.portfolio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "articles")
public class Article {

    @Id
    private String id;

    private String title;
    private String slug;
    private String description;
    private String githubRawUrl;
    private List<String> keywords;
    private LocalDateTime createdAt;

    // ✅ No-args constructor (required by MongoDB)
    public Article() {
    }

    // ✅ All-args constructor (used by Builder)
    private Article(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.slug = builder.slug;
        this.description = builder.description;
        this.githubRawUrl = builder.githubRawUrl;
        this.keywords = builder.keywords;
        this.createdAt = builder.createdAt;
    }

    // ✅ Static builder() method (same as Lombok)
    public static Builder builder() {
        return new Builder();
    }

    // ✅ Builder class
    public static class Builder {

        private String id;
        private String title;
        private String slug;
        private String description;
        private String githubRawUrl;
        private List<String> keywords;
        private LocalDateTime createdAt;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder slug(String slug) {
            this.slug = slug;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder githubRawUrl(String githubRawUrl) {
            this.githubRawUrl = githubRawUrl;
            return this;
        }

        public Builder keywords(List<String> keywords) {
            this.keywords = keywords;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Article build() {
            return new Article(this);
        }
    }

    // ======================
    // Getters and Setters
    // ======================

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGithubRawUrl() {
        return githubRawUrl;
    }

    public void setGithubRawUrl(String githubRawUrl) {
        this.githubRawUrl = githubRawUrl;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
