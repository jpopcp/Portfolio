package rfp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rfp.command.CreateRequestDto;
import rfp.command.UpdateRequestDto;
import rfp.command.RequestResponseDto;
import rfp.service.RequestService;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<RequestResponseDto> create(@RequestBody CreateRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(requestService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestResponseDto> update(@PathVariable Long id, @RequestBody UpdateRequestDto dto) {
        return ResponseEntity.ok(requestService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.getById(id));
    }

    @GetMapping
    public List<RequestResponseDto> getAll() {
        return requestService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        requestService.delete(id);
        return ResponseEntity.noContent().build();
    }
}