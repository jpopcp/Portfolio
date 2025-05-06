package rfp.controller;

import org.springframework.web.bind.annotation.*;
import rfp.model.Snippet;
import rfp.rag.service.AiService;
import java.util.List;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*") // Optional: allows frontend to access the endpoint
public class RestAiController {

    private AiService aiService;

    public RestAiController(AiService aiService) {
        this.aiService = aiService;
    }


    @GetMapping("/suggest-snippets")
    public List<Snippet> suggestSnippets(@RequestParam String query) {
        return aiService.suggestRelevantSnippets(query);
    }

    @PostMapping("/generate-reply")
    public String generateReply(@RequestBody String userQuestion) {
        return aiService.generateReply(userQuestion);
    }



}
