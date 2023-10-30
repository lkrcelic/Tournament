package web2.tournament.service;

import web2.tournament.domain.Participant;

import java.util.List;
import java.util.UUID;

public interface ParticipantService {

    Participant createParticipant(String name);

    List<Participant> getParticipantsByTournamentId(UUID tournamentId);

}
