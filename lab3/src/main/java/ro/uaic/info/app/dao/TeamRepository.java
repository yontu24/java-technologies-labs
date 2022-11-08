package ro.uaic.info.app.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ro.uaic.info.app.controller.TeamController;
import ro.uaic.info.app.model.Team;

@Named
@SessionScoped
public class TeamRepository implements Serializable {
    private List<Team> teams;
    private Team team;
    private Team team2;
    private boolean success = true;

    @Inject
    private TeamController teamController;

    @PostConstruct
    public void init() {
        try {
            teams = teamController.getTeams();
            team = new Team();
            team2 = new Team();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public void getCreateTeam() {
        success = teamController.setCreateTeam(team);
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public void getDisplayMessage() {
        if (success)
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", "Team has been created."));
        else
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not executed", "Team has not been created."));
    }

    public void onTeamSelected() {
        if (team2.getName() != null && !team2.getName().equals("")) {
            System.out.println(team2);
        }
    }

    public Team getTeam(String name) {
        return teams.stream().filter(team -> team.getName().equals(name)).findFirst().orElse(null);
    }
}
