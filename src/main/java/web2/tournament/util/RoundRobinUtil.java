package web2.tournament.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import web2.tournament.domain.Participant;

import java.util.ArrayList;
import java.util.List;

public final class RoundRobinUtil {

    public static List<Round> generateRoundRobinPairings(List<Participant> participants) {
        List<Round> rounds = new ArrayList<>();
        int numberOfParticipants = participants.size();
        boolean isDummyAdded = false;

        // Create a copy of the participant list
        List<Participant> participantCopy = new ArrayList<>(participants);

        // Check if number of participants is odd
        if (numberOfParticipants % 2 != 0) {
            // If odd, add a "dummy" participant to the copy
            participantCopy.add(new Participant("Dummy"));
            numberOfParticipants++;
            isDummyAdded = true;
        }

        // Generate pairings using the copy of the participant list
        for (int round = 0; round < numberOfParticipants - 1; round++) {
            Round newRound = new Round();
            for (int i = 0; i < numberOfParticipants / 2; i++) {
                Participant participant1 = participantCopy.get(i);
                Participant participant2 = participantCopy.get(numberOfParticipants - 1 - i);
                Pairing pairing = new Pairing(participant1, participant2);
                newRound.addPairing(pairing);
            }
            rounds.add(newRound);

            // Rotate participants for next round
            List<Participant> newParticipants = new ArrayList<>();
            newParticipants.add(participantCopy.get(0));
            newParticipants.addAll(participantCopy.subList(2, numberOfParticipants));
            newParticipants.add(participantCopy.get(1));
            participantCopy = newParticipants;
        }

        // If dummy participant was added, remove its rounds
        if (isDummyAdded) {
            List<Round> finalRounds = new ArrayList<>();
            for (Round round : rounds) {
                List<Pairing> finalPairings = new ArrayList<>();
                for (Pairing pairing : round.getPairings()) {
                    if (!pairing.getParticipant1().getName().equals("Dummy") &&
                        !pairing.getParticipant2().getName().equals("Dummy")) {
                        finalPairings.add(pairing);
                    }
                }
                if (!finalPairings.isEmpty()) {
                    finalRounds.add(new Round(finalPairings));
                }
            }
            return finalRounds;
        }

        return rounds;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Round {

        private List<Pairing> pairings = new ArrayList<>();

        private int number;

        public Round(List<Pairing> pairings) {
            this.pairings = pairings;
        }

        public void addPairing(Pairing pairing) {
            pairings.add(pairing);
        }

        public int getNumber() {
            return number;
        }

    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Pairing {

        private Participant participant1;
        private Participant participant2;

    }

}

