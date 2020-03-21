package db;

import model.Application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDb extends DbWorker {

    public boolean createApplication(Application application) {
        String SQL = "insert into application(full_name, course, is_online, email, is_accepted) values(?, ?, ?, ?, ?)";

        try(Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, application.getFullName());
            preparedStatement.setString(2, application.getCourse());
            preparedStatement.setBoolean(3, application.getIsOnline());
            preparedStatement.setString(4, application.getEmail());
            preparedStatement.setBoolean(5, false);
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Application> getAll() {
        String SQL = "select * from application where is_accepted = false";
        List<Application> applicationList = new ArrayList<>();
        try (Connection connection = connect()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Application application = new Application();
                application.setId(resultSet.getInt(1));
                application.setFullName(resultSet.getString(2));
                application.setCourse(resultSet.getString(3));
                application.setIsOnline(resultSet.getBoolean(4));
                application.setEmail(resultSet.getString(5));
                application.setIsAccepted(resultSet.getBoolean(6));
                applicationList.add(application);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return applicationList;
    }

    public boolean updateApplication(Integer id) {
        String SQL = "update application set is_accepted = true where id = ?";

        try(Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
