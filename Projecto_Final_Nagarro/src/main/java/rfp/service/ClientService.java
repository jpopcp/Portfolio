package rfp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rfp.command.CreateClientDto;
import rfp.command.UpdateClientDto;
import rfp.command.ClientResponseDto;
import rfp.model.Client;
import rfp.repository.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientResponseDto create(CreateClientDto dto) {
        Client client = new Client();
        client.setCompanyName(dto.getCompanyName());
        client.setEmail(dto.getEmail());
        client.setSector(dto.getSector());

        Client saved = clientRepository.save(client);
        return toResponseDto(saved);
    }

    public ClientResponseDto update(Long id, UpdateClientDto dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with ID " + id));

        client.setCompanyName(dto.getCompanyName());
        client.setEmail(dto.getEmail());
        client.setSector(dto.getSector());

        Client updated = clientRepository.save(client);
        return toResponseDto(updated);
    }

    public ClientResponseDto getById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with ID " + id));

        return toResponseDto(client);
    }

    public List<ClientResponseDto> getAll() {
        return clientRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    private ClientResponseDto toResponseDto(Client client) {
        ClientResponseDto dto = new ClientResponseDto();
        dto.setId(client.getId());
        dto.setCompanyName(client.getCompanyName());
        dto.setEmail(client.getEmail());
        dto.setSector(client.getSector());
        return dto;
    }
}