package ro.uaic.info.documents.submission.documentssubmisson.controllers;

import ro.uaic.info.documents.submission.documentssubmisson.models.AbstractUser;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;

@SessionScoped
@Named
public class RegisterController implements Serializable {
    @Resource(mappedName = "jdbc/__Documents")
    private DataSource dataSource;

    public boolean registerUser(AbstractUser user) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "insert into users (name, password) values (?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getPassword());
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

    public boolean isUserRegistered(AbstractUser user) {
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
}
