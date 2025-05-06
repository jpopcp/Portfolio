package rfp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import rfp.rag.service.AiService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rag")
public class RagController {

    @Autowired
    private AiService aiService;

    @PostMapping("/reply")
    public Map<String, String> generateReply(@RequestBody Map<String, String> body) {
        String query = body.get("query");
        System.out.println("Received query: " + query);
        try {
            String answer = aiService.generateReply(query);
            return Map.of("output", answer, "input", query);
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("output", "Error: failed to generate response", "input", query);
        }
    }
}