package ru.pivovardi.teodoro.telebuff.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.pivovardi.teodoro.telebuff.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    Optional<Publication> findById(Long id);
    boolean existsById(Long id);

    @Query("SELECT p FROM Publication p WHERE p.uri = :uri")
    Optional<Publication> findByUri(@Param("uri") String uri);

    @Query("SELECT count(p)>0 FROM Publication p WHERE p.uri = :uri")
    boolean existsByUri(@Param("uri") String uri);

    //void deleteByPublicationId(String publicationId);
}