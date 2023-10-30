package web2.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web2.tournament.domain.Tournament;

import java.util.List;
import java.util.UUID;

public interface TournamentRepository extends JpaRepository<Tournament, UUID> {

    List<Tournament> findByCreatorId(String creatorId);

}
