package ro.uaic.info.app.controller;

import ro.uaic.info.app.model.Team;
import ro.uaic.info.app.service.ConnectionService;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TeamController implements Serializable {

    public boolean setCreateTeam(Team team) {
        String query = "insert into teams (name, founding_date) values (?, ?::date) returning id";
        int teamId = -1;

        try (PreparedStatement stmt = ConnectionService.getInstance().getConnection().prepareStatement(query)) {
            stmt.setString(1, team.getName());
            stmt.setString(2, team.getFoundingDate().toString());
            stmt.execute();

            ResultSet resultSet = stmt.getResultSet();
            if (resultSet.next())
                teamId = resultSet.getInt(1);

            System.out.println("Successfully insert into teams team " + team.getName() + ", " + team.getFoundingDate() + " with id = " + teamId);
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

//        query = "insert into cities (team_id, name) values (?, ?) returning id";
//        try (PreparedStatement stmt = ConnectionService.getInstance().getConnection().prepareStatement(query)) {
//            stmt.setInt(1, teamId);
//            stmt.setString(2, team.getCity());
//            stmt.execute();
//
//            System.out.println("Successfully insert into cities team_id " + teamId + ", " + team.getCity());
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//        }
        return false;
    }

    public boolean setUpdateTeamFoundingDate(String teamFoundingDate, int teamId) {
        String query = "update teams set name = ? where id = ?";
        try (PreparedStatement stmt = ConnectionService.getInstance().getConnection().prepareStatement(query)) {
            stmt.setString(1, teamFoundingDate);
            stmt.setInt(2, teamId);
            stmt.executeUpdate();

            System.out.println("Successfully update teams on " + teamFoundingDate + " by id = " + teamId);
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public boolean setUpdateCityName(String cityName, int teamId) {
        String query = "update cities set name = ? where team_id = ?";
        try (PreparedStatement stmt = ConnectionService.getInstance().getConnection().prepareStatement(query)) {
            stmt.setString(1, cityName);
            stmt.setInt(2, teamId);
            stmt.executeUpdate();
            System.out.println("Successfully update cities on " + cityName + " by id = " + teamId);
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public List<Team> getTeams() throws SQLException {
        Statement stmt = ConnectionService.getInstance().getConnection().createStatement();

        ResultSet rs = stmt
                .executeQuery("select t.name, t.founding_date, c.name from teams t join cities c on c.team_id = t.id");

        List<Team> teams = new ArrayList<>();
        while (rs.next()) {
            Team team = new Team();
            team.setName(rs.getString(1));
            team.setFoundingDate(((Date) rs.getObject(2)).toLocalDate());
            team.setCity(rs.getString(3));

            teams.add(team);
        }
        stmt.close();
        rs.close();
        return teams;
    }
}
