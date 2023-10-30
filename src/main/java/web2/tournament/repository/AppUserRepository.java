package web2.tournament.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web2.tournament.domain.Participant;

public interface AppUserRepository extends JpaRepository<Participant, Long> {
}
