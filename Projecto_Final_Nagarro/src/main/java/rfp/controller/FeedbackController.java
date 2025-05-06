package rfp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rfp.command.CreateFeedbackDto;
import rfp.command.UpdateFeedbackDto;
import rfp.command.FeedbackResponseDto;
import rfp.service.FeedbackService;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<FeedbackResponseDto> create(@RequestBody CreateFeedbackDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackResponseDto> update(@PathVariable Long id, @RequestBody UpdateFeedbackDto dto) {
        return ResponseEntity.ok(feedbackService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(feedbackService.getById(id));
    }

    @GetMapping
    public List<FeedbackResponseDto> getAll() {
        return feedbackService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        feedbackService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
