package maze.datamanager;

import org.mariadb.jdbc.MariaDbConnection;
import user.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

public class JDBCMazeDataSource implements Data {

    public static final String CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS users ("
                 + "id INTEGER PRIMARY KEY /* 40101 AUTO_INCREMENT */ NOT NULL UNIQUE,"
                 + "userName VARCHAR(30) , "
                 + "userPassword VARCHAR(30) , " + ");";

    public static final String CREATE_MAZE_TABLE =
            "CREATE TABLE IF NOT EXISTS mazes ("
                 + "id INTEGER PRIMARY KEY /* 40101 AUTO_INCREMENT */ NOT NULL UNIQUE,"
                 + "mazeName VARCHAR(30), "
                 + "mazeCreator VARCHAR(30) , "
                 + "mazeCreationTime VARCHAR(30) , "
                 + "mazeLastEdited (VARCHAR(30) , " + ");";

    private static final String INSERT_USER = "INSERT INTO users (userName, userPassword) VALUES (?, ?)";
    private static final String GET_USERID = "SELECT id FROM users WHERE userName=?";
    private static final String GET_USERNAME = "SELECT userName FROM users WHERE userName=?";
    private static final String GET_USERPASSWORD = "SELECT userPassword FROM users WHERE userName=?";
    private static final String DELETE_USER = "DELETE FROM users WHERE name=?";
    private static final String INSERT_MAZE = "INSERT INTO mazes (mazeName, mazeCreator, mazeCreationTime, mazeLastEdited) VALUES (?, ?, ?, ?)";
    private static final String GET_MAZEID = "SELECT id FROM mazes WHERE name=?";
    private static final String GET_MAZE = "SELECT * FROM mazes WHERE mazeName=?";
    private static final String GET_MAZENAME = "SELECT mazeName FROM mazes WHERE mazeName=?";
    private static final String GET_MAZECREATOR = "SELECT mazeCreator FROM mazes WHERE mazeName=?";
    private static final String GET_MAZECREATEDATE = "SELECT mazeCreationTime FROM mazes";
    private static final String GET_MAZEEDITDATE = "SELECT mazeLastEdited FROM mazes";
    private static final String DELETE_MAZE = "DELETE FROM maze WHERE mazeName =?";

    private Connection connection;
    private PreparedStatement addUser;
    private PreparedStatement getUserID;
    private PreparedStatement getUsername;
    private PreparedStatement getPassword;
    private PreparedStatement deleteUser;
    private PreparedStatement addMaze;
    private PreparedStatement getMazeID;
    private PreparedStatement getMaze;
    private PreparedStatement getMazeName;
    private PreparedStatement getMazeCreator;
    private PreparedStatement getMazeCreationDate;
    private PreparedStatement getMazeLastEdited;
    private PreparedStatement deleteMaze;

    public JDBCMazeDataSource() {
        connection = MazeDB.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_USER_TABLE);
            st.execute(CREATE_MAZE_TABLE);

            addUser = connection.prepareStatement(INSERT_USER);
            getUserID = connection.prepareStatement(GET_USERID);
            getUsername = connection.prepareStatement(GET_USERNAME);
            getPassword = connection.prepareStatement(GET_USERPASSWORD);
            deleteUser = connection.prepareStatement(DELETE_USER);

            addMaze = connection.prepareStatement(INSERT_MAZE);
            getMazeID = connection.prepareStatement(GET_MAZEID);
            getMaze = connection.prepareStatement(GET_MAZE);
            getMazeName = connection.prepareStatement(GET_MAZENAME);
            getMazeCreator = connection.prepareStatement(GET_MAZECREATOR);
            getMazeCreationDate = connection.prepareStatement(GET_MAZECREATEDATE);
            getMazeLastEdited = connection.prepareStatement(GET_MAZEEDITDATE);
            deleteMaze = connection.prepareStatement(DELETE_MAZE);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addUser(User u) {
        try {
            addUser.setString(1, u.getName());
            addUser.setString(2, u.getPassword());
            addUser.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void getUserID(User u) {
        try {
            getUserID.setString(1, u.getName());
            getUserID.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

