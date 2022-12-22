package ro.uaic.info.documents.submission.documentssubmisson.controllers;

import ro.uaic.info.documents.submission.documentssubmisson.models.User;
import ro.uaic.info.documents.submission.documentssubmisson.services.TimeFrameService;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SessionScoped
@Named
public class RegisterController implements Serializable {
    @Resource(mappedName = "jdbc/__Documents")
    private DataSource dataSource;

    @Inject
    private TimeFrameService timeFrameService;

    @Transactional
    public boolean registerUser(User user) {
        return timeFrameService.get() && insertUser(user);
    }

    @Transactional
    public boolean isUserRegistered(User user) {
        if (user == null)
            return false;

        try (Connection connection = dataSource.getConnection()) {
            String query = "select u.name from users u where name = ? and password = ?";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();
        } catch (SQLException exception) {
            System.out.println("Query has not been executed.");
            exception.printStackTrace();
            return false;
        }
    }

    private boolean insertUser(User user) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "insert into users (id, name, password) values (?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getName());
                stmt.setString(3, user.getPassword());
                stmt.execute();

                System.out.println("Successfully insert into users " + user.getName() + ", " + user.getPassword());
                return true;
            }
        } catch (SQLException exception) {
            System.out.println("Query has not been executed.");
            exception.printStackTrace();
            return false;
        }
    }
}
