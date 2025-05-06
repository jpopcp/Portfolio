package rfp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rfp.command.CreateProposalDto;
import rfp.command.UpdateProposalDto;
import rfp.command.ProposalResponseDto;
import rfp.model.Proposal;
import rfp.model.Request;
import rfp.repository.ProposalRepository;
import rfp.repository.RequestRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final RequestRepository requestRepository;

    @Autowired
    public ProposalService(ProposalRepository proposalRepository,
                           RequestRepository requestRepository) {
        this.proposalRepository = proposalRepository;
        this.requestRepository = requestRepository;
    }

    public ProposalResponseDto create(CreateProposalDto dto) {
        Proposal proposal = new Proposal();
        proposal.setTitle(dto.getTitle());
        proposal.setDescription(dto.getDescription());
        proposal.setSpecifications(dto.getSpecifications());
        proposal.setDepartment(dto.getDepartment());
        proposal.setType(dto.getType());
        proposal.setDeliveryDate(dto.getDeliveryDate());
        proposal.setStatus("pending");

        if (dto.getRequestId() != null) {
            Request request = requestRepository.findById(dto.getRequestId())
                    .orElseThrow(() -> new IllegalArgumentException("Request not found with ID: " + dto.getRequestId()));
            proposal.setRequest(request);
        }

        return toResponseDto(proposalRepository.save(proposal));
    }

    public ProposalResponseDto update(Long id, UpdateProposalDto dto) {
        Proposal proposal = proposalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Proposal not found with ID: " + id));

        proposal.setDescription(dto.getDescription());
        proposal.setSpecifications(dto.getSpecifications());
        proposal.setDepartment(dto.getDepartment());
        proposal.setType(dto.getType());
        proposal.setDeliveryDate(dto.getDeliveryDate());
        proposal.setFeedback(dto.getFeedback());
        proposal.setStatus(dto.getStatus());

        return toResponseDto(proposalRepository.save(proposal));
    }

    public ProposalResponseDto getById(Long id) {
        Proposal proposal = proposalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Proposal not found with ID: " + id));
        return toResponseDto(proposal);
    }

    public List<ProposalResponseDto> getAll() {
        return proposalRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        proposalRepository.deleteById(id);
    }

    private ProposalResponseDto toResponseDto(Proposal proposal) {
        ProposalResponseDto dto = new ProposalResponseDto();
        dto.setId(proposal.getId());
        dto.setTitle(proposal.getTitle());
        dto.setDescription(proposal.getDescription());
        dto.setSpecifications(proposal.getSpecifications());
        dto.setDepartment(proposal.getDepartment());
        dto.setType(proposal.getType());
        dto.setDeliveryDate(proposal.getDeliveryDate());
        dto.setStatus(proposal.getStatus());
        dto.setFeedback(proposal.getFeedback());
        dto.setRequestId(proposal.getRequest() != null ? proposal.getRequest().getId() : null);
        return dto;
    }
}