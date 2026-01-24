package in.portfolio.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubMarkdownService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchMarkdown(String rawUrl) {

        if (!rawUrl.startsWith("https://raw.githubusercontent.com/")) {
            throw new RuntimeException("Invalid GitHub raw markdown URL");
        }

        return restTemplate.getForObject(rawUrl, String.class);
    }
}

