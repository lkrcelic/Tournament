package web2.tournament.service.impl;

import org.springframework.stereotype.Service;
import web2.tournament.domain.Participant;
import web2.tournament.domain.Tournament;
import web2.tournament.repository.TournamentRepository;
import web2.tournament.service.TournamentService;
import web2.tournament.service.request.CreateTournamentRequest;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;

    public TournamentServiceImpl(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public Tournament createTournament(CreateTournamentRequest request) {
        Tournament tournament = new Tournament();
        tournament.setName(request.getName());
        tournament.setWinningPoints(request.getWinningPoints());
        tournament.setDrawPoints(request.getDrawPoints());
        tournament.setLosingPoints(request.getLosingPoints());
        tournament.setCreatorId(request.getCreatorId());

        Tournament createdTournament = tournamentRepository.save(tournament);

        List<Participant> participants = request.getParticipants().stream()
            .map(name -> {
                Participant participant = new Participant();
                participant.setName(name);
                participant.setTournament(createdTournament);
                return participant;
            })
            .collect(Collectors.toList());

        tournament.setParticipants(participants);

        return tournamentRepository.save(createdTournament);
    }

    @Override
    public Tournament getTournamentById(UUID id) {
        var tournament = tournamentRepository.findById(id);
        return tournament.orElse(null);

    }

    @Override
    public List<Tournament> getTournamentsByCreatorId(String creatorId) {
        return tournamentRepository.findByCreatorId(creatorId);
    }

}
