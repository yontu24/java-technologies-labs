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
    private Team teamToCreate;
    private Team teamToUpdate;
    private boolean success = true;

    @Inject
    private TeamController teamController;

    @PostConstruct
    public void init() {
        try {
            teams = teamController.getTeams();
            teamToCreate = new Team();
            try {
                teamToUpdate = teams.get(0);
            } catch (IndexOutOfBoundsException e) {
                teamToUpdate = new Team();
            }
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

    public void setTeamToCreate(Team teamToCreate) {
        this.teamToCreate = teamToCreate;
    }

    public Team getTeamToCreate() {
        return teamToCreate;
    }

    public void getCreateTeam() {
        success = teamController.setCreateTeam(teamToCreate);
    }

    public Team getTeamToUpdate() {
        return teamToUpdate;
    }

    public void setTeamToUpdate(Team teamToUpdate) {
        this.teamToUpdate = teamToUpdate;
    }

    public void getDisplayMessage() {
        if (success)
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", "Team has been created."));
        else
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not executed", "Team has not been created."));
    }

    public Team getTeam(String name) {
        return teams.stream().filter(team -> team.getName().equals(name)).findFirst().orElse(null);
    }

    public void onRowDblClckSelect() {
        System.out.println("double click");
    }

    public void onRowSelect() {
        System.out.println("single click");
    }
}
