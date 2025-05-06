package rfp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rfp.command.CreateRequestDto;
import rfp.command.UpdateRequestDto;
import rfp.command.RequestResponseDto;
import rfp.model.Request;
import rfp.repository.RequestRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public RequestResponseDto create(CreateRequestDto dto) {
        Request request = new Request();
        request.setClientId(dto.getClientId());
        request.setDeliveryDate(dto.getDeliveryDate());
        request.setDescription(dto.getDescription());
        request.setSpecifications(dto.getSpecifications());
        request.setStatus(dto.getStatus());
        request.setPriority(dto.getPriority());
        request.setEvaluationCriteria(dto.getEvaluationCriteria());

        return toResponseDto(requestRepository.save(request));
    }

    public RequestResponseDto update(Long id, UpdateRequestDto dto) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Request not found with ID: " + id));

        request.setDeliveryDate(dto.getDeliveryDate());
        request.setDescription(dto.getDescription());
        request.setSpecifications(dto.getSpecifications());
        request.setStatus(dto.getStatus());
        request.setPriority(dto.getPriority());
        request.setEvaluationCriteria(dto.getEvaluationCriteria());
        request.setUpdatedAt(java.time.LocalDateTime.now());

        return toResponseDto(requestRepository.save(request));
    }

    public RequestResponseDto getById(Long id) {
        return requestRepository.findById(id)
                .map(this::toResponseDto)
                .orElseThrow(() -> new IllegalArgumentException("Request not found with ID: " + id));
    }

    public List<RequestResponseDto> getAll() {
        return requestRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        requestRepository.deleteById(id);
    }

    private RequestResponseDto toResponseDto(Request request) {
        RequestResponseDto dto = new RequestResponseDto();
        dto.setId(request.getId());
        dto.setCreatedAt(request.getCreatedAt());
        dto.setUpdatedAt(request.getUpdatedAt());
        dto.setClientId(request.getClientId());
        dto.setDeliveryDate(request.getDeliveryDate());
        dto.setDescription(request.getDescription());
        dto.setSpecifications(request.getSpecifications());
        dto.setStatus(request.getStatus().name());
        dto.setPriority(request.getPriority().name());
        dto.setEvaluationCriteria(request.getEvaluationCriteria());
        return dto;
    }
}