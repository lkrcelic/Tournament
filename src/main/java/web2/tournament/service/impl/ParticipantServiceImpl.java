package web2.tournament.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web2.tournament.domain.Participant;
import web2.tournament.domain.Tournament;
import web2.tournament.repository.ParticipantRepository;
import web2.tournament.repository.TournamentRepository;
import web2.tournament.service.ParticipantService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Override
    public Participant createParticipant(String name) {
            return null;
    }

    @Override
    public List<Participant> getParticipantsByTournamentId(UUID tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid tournament UUID"));
        return tournament.getParticipants();
    }

}
