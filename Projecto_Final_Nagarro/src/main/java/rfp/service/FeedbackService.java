package rfp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rfp.command.CreateFeedbackDto;
import rfp.command.UpdateFeedbackDto;
import rfp.command.FeedbackResponseDto;
import rfp.model.Feedback;
import rfp.repository.FeedbackRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public FeedbackResponseDto create(CreateFeedbackDto dto) {
        Feedback feedback = new Feedback();
        feedback.setPositivePoints(dto.getPositivePoints());
        feedback.setNegativePoints(dto.getNegativePoints());
        feedback.setDescription(dto.getDescription());
        feedback.setFinalMark(dto.getFinalMark());

        return toResponseDto(feedbackRepository.save(feedback));
    }

    public FeedbackResponseDto update(Long id, UpdateFeedbackDto dto) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Feedback not found with ID: " + id));

        feedback.setPositivePoints(dto.getPositivePoints());
        feedback.setNegativePoints(dto.getNegativePoints());
        feedback.setDescription(dto.getDescription());
        feedback.setFinalMark(dto.getFinalMark());

        return toResponseDto(feedbackRepository.save(feedback));
    }

    public FeedbackResponseDto getById(Long id) {
        return feedbackRepository.findById(id)
                .map(this::toResponseDto)
                .orElseThrow(() -> new IllegalArgumentException("Feedback not found with ID: " + id));
    }

    public List<FeedbackResponseDto> getAll() {
        return feedbackRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        feedbackRepository.deleteById(id);
    }

    private FeedbackResponseDto toResponseDto(Feedback feedback) {
        FeedbackResponseDto dto = new FeedbackResponseDto();
        dto.setId(feedback.getId());
        dto.setPositivePoints(feedback.getPositivePoints());
        dto.setNegativePoints(feedback.getNegativePoints());
        dto.setDescription(feedback.getDescription());
        dto.setFinalMark(feedback.getFinalMark());
        return dto;
    }
}