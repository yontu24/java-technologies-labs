package ro.uaic.info.documents.submission.documentssubmisson.controllers;

import ro.uaic.info.documents.submission.documentssubmisson.models.User;
import ro.uaic.info.documents.submission.documentssubmisson.models.UserRole;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.sql.DataSource;
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

    public boolean registerUser(User user) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "insert into users (name, password, role) values (?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getPassword());
                stmt.setInt(3, user.getRole().ordinal());
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

    public boolean isUserRegistered(User user) {
        if (user == null)
            return false;

        try (Connection connection = dataSource.getConnection()) {
            String query = "select u.name, u.role from users u where name = ? and password = ?";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                user.setRole(UserRole.getById(resultSet.getInt(2)));
                return true;
            }

            return false;
        } catch (SQLException exception) {
            System.out.println("Query has not been executed.");
            exception.printStackTrace();
            return false;
        }
    }
}
