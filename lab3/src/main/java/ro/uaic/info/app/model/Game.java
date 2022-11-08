package ro.uaic.info.app.model;

public class Game {
    private Team teamA;
    private Team teamB;
    private int games;

    public Game(Team teamA, Team teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
    }

    public Team getTeamA() {
        return teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public int getGames() {
        return games;
    }

    @Override
    public String toString() {
        return teamA + "&" + teamB;
    }
}

