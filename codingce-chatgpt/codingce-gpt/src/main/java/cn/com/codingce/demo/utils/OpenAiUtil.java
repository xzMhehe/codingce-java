package cn.com.codingce.demo.utils;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

@Component
public class OpenAiUtil {
    @Value("${openai.secret-key}")
    private String token;

    private OpenAiService service;

    @PostConstruct
    public void init() {
        service = new OpenAiService(token, Duration.ofSeconds(60L));
    }

    public List<CompletionChoice> sendComplete(String prompt) {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("text-davinci-003")
                .maxTokens(1500)
                .prompt(prompt)
                .user("testing")
                .logitBias(new HashMap<>())
                .build();

        return service.createCompletion(completionRequest).getChoices();
    }
}