package web2.lab.prvi.domain;

import java.util.Set;

public class Tournament {

    private long id;

    private String name;

    private long winningPoints;

    private long drawPoints;

    private long losingPoints;

    private AppUser creator;

    private Set<Participant> participants;

}
