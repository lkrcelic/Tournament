package web2.tournament.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web2.tournament.domain.Participant;
import web2.tournament.domain.Tournament;
import web2.tournament.service.ParticipantService;
import web2.tournament.service.TournamentService;
import web2.tournament.service.request.CreateTournamentRequest;
import web2.tournament.util.RoundRobinUtil;

import java.util.List;
import java.util.UUID;

@Controller
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private ParticipantService participantService;

    @GetMapping("/tournament/{id}")
    public String profile(@PathVariable UUID id, Model model) {
        Tournament tournament = tournamentService.getTournamentById(id);
        if (tournament == null) {
            return "redirect:/error";
        }

        // Fetch the participants for the tournament
        List<Participant> participants = participantService.getParticipantsByTournamentId(id);

        // Add the tournament and participants to the model
        model.addAttribute("tournament", tournament);
        model.addAttribute("participants", participants);

        //Round Robin
        List<RoundRobinUtil.Round> rounds = RoundRobinUtil.generateRoundRobinPairings(participants);
        model.addAttribute("rounds", rounds);

        return "tournament";
    }

    @GetMapping("/tournaments")
    public String profile(Model model, @AuthenticationPrincipal OidcUser principal) {
        model.addAttribute("profile", principal.getClaims());

        var creator_id = principal.getSubject();
        List<Tournament> tournaments = tournamentService.getTournamentsByCreatorId(creator_id);
        if (tournaments == null) {
            return "redirect:/error";
        }

        model.addAttribute("tournaments", tournaments);

        return "tournaments";
    }

    @PostMapping("/createTournament")
    public String createTournament(@ModelAttribute CreateTournamentRequest request,
        @AuthenticationPrincipal OidcUser principal) {
        var creator_id = principal.getSubject();
        request.setCreatorId(creator_id);

        tournamentService.createTournament(request);

        // Redirect to the homepage
        return "redirect:/";
    }

}
