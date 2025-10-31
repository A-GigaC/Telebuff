package ru.pivovardi.teodoro.telebuff.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pivovardi.teodoro.telebuff.dto.CreatePublicationRequest;
import ru.pivovardi.teodoro.telebuff.dto.PublicationDTO;
import ru.pivovardi.teodoro.telebuff.services.PublicationService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/publication")
public class PublicationController {

    @Autowired
    PublicationService publicationService;

    @GetMapping(value = "/{publicationURI}")
    public ResponseEntity<PublicationDTO> getPublication(@PathVariable String publicationURI) {
        Optional<PublicationDTO > publicationDto = publicationService.getPublicationByURI(publicationURI);
        return publicationDto
                .map(dto -> ResponseEntity.ok(dto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/post")
    public @ResponseBody PublicationDTO createPublication(@RequestBody CreatePublicationRequest createPublicationRequest) {
        return publicationService.createPublication(createPublicationRequest);
    }
}
