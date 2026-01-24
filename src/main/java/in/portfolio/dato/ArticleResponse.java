package in.portfolio.dato;

public class ArticleResponse {

    private String title;
    private String description;
    private String content; // markdown text

    // ✅ No-args constructor
    public ArticleResponse() {
    }

    // ✅ Private constructor used by Builder
    private ArticleResponse(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.content = builder.content;
    }

    // ✅ Static builder() method
    public static Builder builder() {
        return new Builder();
    }

    // ✅ Builder class
    public static class Builder {

        private String title;
        private String description;
        private String content;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public ArticleResponse build() {
            return new ArticleResponse(this);
        }
    }

    // ======================
    // Getters and Setters
    // ======================

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
