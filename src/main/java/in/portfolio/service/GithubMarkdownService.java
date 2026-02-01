//package in.portfolio.service;
//
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class GithubMarkdownService {
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    public String fetchMarkdown(String rawUrl) {
//
//        if (!rawUrl.startsWith("https://raw.githubusercontent.com/")) {
//            throw new RuntimeException("Invalid GitHub raw markdown URL");
//        }
//
//        return restTemplate.getForObject(rawUrl, String.class);
//    }
//}
//


package in.portfolio.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubMarkdownService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchMarkdown(String rawUrl) {

        if (!rawUrl.startsWith("https://raw.githubusercontent.com/")) {
            throw new RuntimeException("Invalid GitHub raw markdown URL");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Portfolio-App"); // 🔴 REQUIRED

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                rawUrl,
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();
    }
}

