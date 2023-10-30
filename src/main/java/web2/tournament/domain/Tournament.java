package web2.tournament.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tournament")
public class Tournament {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;

    private long winningPoints;

    private long drawPoints;

    private long losingPoints;

    private String creatorId;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private List<Participant> participants;

}
