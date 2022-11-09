package ro.uaic.info.app.controller;

import ro.uaic.info.app.model.Team;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TeamController implements Serializable {

    @Resource(mappedName = "MyJdbcResource")
    private DataSource dataSource;

    public boolean setCreateTeam(Team team) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "insert into teams (name, founding_date) values (?, ?::date) returning id";
            int teamId = -1;

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, team.getName());
                stmt.setString(2, team.getFoundingDate().toString());
                stmt.execute();

                ResultSet resultSet = stmt.getResultSet();
                if (resultSet.next())
                    teamId = resultSet.getInt(1);

                System.out.println("Successfully insert into teams team " + team.getName() + ", " + team.getFoundingDate() + " with id = " + teamId);
                return true;
            }
        } catch (SQLException exception) {
            System.out.println("Query has not been executed.");
            exception.printStackTrace();
            return false;
        }
    }

    public boolean setUpdateTeamFoundingDate(String teamFoundingDate, int teamId) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "update teams set name = ? where id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, teamFoundingDate);
                stmt.setInt(2, teamId);
                stmt.executeUpdate();

                System.out.println("Successfully update teams on " + teamFoundingDate + " by id = " + teamId);
                return true;
            }
        } catch (SQLException exception) {
            System.out.println("Query has not been executed.");
            exception.printStackTrace();
            return false;
        }
    }

    public boolean setUpdateCityName(String cityName, int teamId) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "update cities set name = ? where team_id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, cityName);
                stmt.setInt(2, teamId);
                stmt.executeUpdate();
                System.out.println("Successfully update cities on " + cityName + " by id = " + teamId);
                return true;
            }
        } catch (SQLException exception) {
            System.out.println("Query has not been executed.");
            exception.printStackTrace();
            return false;
        }
    }

    public List<Team> getTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String query = "select t.name, t.founding_date, c.name from teams t join cities c on c.team_id = t.id";
            try (Statement stmt = connection.createStatement()) {
                ResultSet resultSet = stmt.executeQuery(query);
                while (resultSet.next()) {
                    Team team = new Team();
                    team.setName(resultSet.getString(1));
                    team.setFoundingDate(((Date) resultSet.getObject(2)).toLocalDate());
                    team.setCity(resultSet.getString(3));

                    teams.add(team);
                }
            } catch (SQLException exception) {
                System.out.println("Query has not been executed.");
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            System.out.println("Query has not been executed.");
            exception.printStackTrace();
        }
        return teams;
    }
}
