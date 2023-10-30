package web2.tournament.service;

import web2.tournament.domain.Tournament;
import web2.tournament.service.request.CreateTournamentRequest;

import java.util.List;
import java.util.UUID;

public interface TournamentService {

    Tournament createTournament(CreateTournamentRequest request);

    Tournament getTournamentById(UUID id);

    List<Tournament> getTournamentsByCreatorId(String creatorId);

}
