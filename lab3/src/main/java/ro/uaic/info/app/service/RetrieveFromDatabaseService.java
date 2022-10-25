package ro.uaic.info.app.service;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ro.uaic.info.app.model.Team;

public class RetrieveFromDatabaseService {
    public List<Team> getTeams() throws SQLException {
        Statement stmt = ConnectionService.getInstance().createStatement();
        ResultSet rs = stmt
                .executeQuery("select t.name, t.founding_date, c.name from teams t join cities c on c.team_id = t.id");

        List<Team> teams = new ArrayList<>();
        while (rs.next()) {
            teams.add(new Team(rs.getString(1), ((Date) rs.getObject(2)).toLocalDate(), rs.getString(3)));
        }
        return teams;
    }
}
