package ro.uaic.info.app.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ro.uaic.info.app.model.Team;
import ro.uaic.info.app.service.RetrieveFromDatabaseService;

@Named(value = "myTeams")
@SessionScoped
public class TeamRepository implements Serializable {
    private List<Team> teams = new ArrayList<>();

    @PostConstruct
    public void init() {
        try {
            teams = new RetrieveFromDatabaseService().getTeams();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getSalute() {
        return "BOSS";
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getTeams() {
        return teams;
    }
}
