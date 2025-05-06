package rfp.rag.service;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import rfp.model.Snippet;
import rfp.rag.persistence.VectorStore;
import rfp.repository.SnippetRepository;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AiService {

    @Value("${ai.function_prompt_template}")
    private String promptTemplate;

    private final ChatClient chatClient;
    private final ResourceLoader resourceLoader;
    private final SnippetRepository snippetRepository;
    private final VectorStore vectorStore;

    public AiService(ChatClient chatClient,
                     ResourceLoader resourceLoader,
                     SnippetRepository snippetRepository,
                     VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.resourceLoader = resourceLoader;
        this.snippetRepository = snippetRepository;
        this.vectorStore = vectorStore;
    }

    /**
     * Used by the GET /api/ai/suggest-snippets endpoint
     */
    public List<Snippet> suggestRelevantSnippets(String query) {
        List<String> ids = vectorStore.search(query, 5); // returns List<String>
        List<Long> longIds = ids.stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());
        return snippetRepository.findAllById(longIds);
    }
    /**
     * Used by the POST /api/ai/generate-reply endpoint
     */
    public String generateReply(String query) {
        try {
            List<String> contextChunks = vectorStore.search(query, 3);
            String context = String.join("\n", contextChunks);

            String templateText = loadPromptTemplate("classpath:ai/templates/rag-prompt-template.st");
            PromptTemplate template = new PromptTemplate(templateText);

            Prompt prompt = template.create(Map.of(
                    "query", query,
                    "documents", context,
                    "tone", "formal"
            ));

            var result = chatClient.call(prompt);
            System.out.println("RAW result: " + result);
            System.out.println("Prompt sent: " + prompt.getInstructions());
            return result.getResult().getOutput().getContent();
        } catch (Exception e) {
            e.printStackTrace();
            return "AI error: " + e.getMessage();
        }
    }

    private String loadPromptTemplate(String path) {
        try {
            Resource resource = resourceLoader.getResource(path);
            byte[] bytes = resource.getInputStream().readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load prompt template", e);
        }
    }


}