package ru.pivovardi.teodoro.telebuff.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pivovardi.teodoro.telebuff.dto.CreatePublicationRequest;
import ru.pivovardi.teodoro.telebuff.dto.PublicationDTO;
import ru.pivovardi.teodoro.telebuff.entity.Publication;
import ru.pivovardi.teodoro.telebuff.repositories.PublicationRepository;
import ru.pivovardi.teodoro.telebuff.utils.TitleConversion;

import java.util.Optional;

@Service
public class PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private TitleConversion titleConversion;

    public PublicationDTO createPublication(CreatePublicationRequest request) {
        Publication publication = new Publication();
        publication.setContent(request.getContent());
        publication.setTitle(request.getTitle());
        String uriBase = titleConversion.convertTitleToIdForm(request.getTitle());

        // Check that converted title currently not use as URI
        if (!publicationRepository.existsByUri(uriBase)) {
            publication.setUri(uriBase);
        } else {
            uriBase = titleConversion.concatWithTimestamp(uriBase);
            if (!publicationRepository.existsByUri(uriBase)) {
                publication.setUri(uriBase);
            } else {
                StringBuilder idBuilder = new StringBuilder(uriBase + '_');
                for (long i = 1; ; i++) {
                    idBuilder.append(i);
                    if (!publicationRepository.existsByUri(idBuilder.toString())) {
                        publication.setUri(idBuilder.toString());
                        break;
                    } else {
                        idBuilder.deleteCharAt(-1);
                        i = i == 10 ? 0 : i;
                    }
                }
            }
        }
//        publication.setAuthor(request.getAuthor());
//        publication.setHashtags(request.getHashtags() != null ? request.getHashtags() : new ArrayList<>());

        Publication savedPublication = publicationRepository.save(publication);
        return convertToDTO(savedPublication);
    }

    public Optional<PublicationDTO> getPublicationByURI(String uri) {
        return publicationRepository.findByUri(uri)
                .map(this::convertToDTO);
    }

    private PublicationDTO convertToDTO(Publication publication) {
        PublicationDTO dto = new PublicationDTO();
        dto.setUri(publication.getUri());
        dto.setContent(publication.getContent());
        dto.setTitle(publication.getTitle());
//        dto.setAuthor(publication.getAuthor());
//        dto.setHashtags(publication.getHashtags());
        return dto;
    }
}