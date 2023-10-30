package web2.tournament.service.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CreateTournamentRequest {

    private String name;

    private long winningPoints;

    private long drawPoints;

    private long losingPoints;

    private String creatorId;

    private Set<String> participants;

}
