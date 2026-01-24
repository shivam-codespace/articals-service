package in.portfolio.dato;

import java.util.List;

 
public class ArticleRequest {
    private String title;
    private String description;
    private String githubRawUrl;
    private List<String> keywords;
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
    
    
}
