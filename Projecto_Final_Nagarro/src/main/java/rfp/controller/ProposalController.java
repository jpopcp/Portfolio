package rfp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rfp.command.CreateProposalDto;
import rfp.command.UpdateProposalDto;
import rfp.command.ProposalResponseDto;
import rfp.service.ProposalService;

import java.util.List;

@RestController
@RequestMapping("/api/proposals")
public class ProposalController {

    private final ProposalService proposalService;

    @Autowired
    public ProposalController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @PostMapping
    public ResponseEntity<ProposalResponseDto> create(@RequestBody CreateProposalDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proposalService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProposalResponseDto> update(@PathVariable Long id, @RequestBody UpdateProposalDto dto) {
        return ResponseEntity.ok(proposalService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProposalResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(proposalService.getById(id));
    }

    @GetMapping
    public List<ProposalResponseDto> getAll() {
        return proposalService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        proposalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}