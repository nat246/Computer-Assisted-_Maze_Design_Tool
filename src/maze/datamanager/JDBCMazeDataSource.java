package maze.datamanager;

import org.mariadb.jdbc.MariaDbConnection;

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

    public static final String CERATE_TABLE =
            "CREATE TABLE IF NOT EXISTS users ("
                 + "idx INTEGER PRIMARY KEY /* 40101 AUTO_INCREMENT */ NOT NULL UNIQUE,"
                 + "userName VARCHAR(30) , "
                 + "userPassword VARCHAR(30) , " + ");";

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS mazes ("
                 + "idx INTEGER PRIMARY KEY /* 40101 AUTO_INCREMENT */ NOT NULL UNIQUE,"
                 + "mazeName VARCHAR(30), "
                 + "mazeCreator VARCHAR(30) , "
                 + "mazeCreationTime VARCHAR(30) , "
                 + "mazeLastEdited (VARCHAR(30) , " + ");";

    private static final String INSERT_USER = "INSERT INTO users (userName, userPassword) VALUES (?, ?)";

    private static final String GET_USERNAME = "SELECT userName FROM users";

    private static final String GET_USERPASSWORD = "SELECT userPassword FROM users";

    private static final String DELETE_USER = "DELETE FROM users WHERE name=?";

    private static final String INSERT_MAZE = "INSERT INTO mazes (mazeName, mazeCreator, mazeCreationTime, mazeLastEdited) VALUES (?, ?, ?, ?)";

    private static final String GET_MAZENAME = "SELECT mazeName FROM mazes";

    private static final String GET_MAZECREATOR = "SELECT mazeCreator FROM mazes";

    private static final String GET_MAZECREATEDATE = "SELECT mazeCreationTime FROM mazes";

    private static final String GET_MAZEEDITDATE = "SELECT mazeLastEdited FROM mazes";

    private static final String DELETE_MAZE = "DELETE FROM maze WHERE mazeName =?";

    private Connection connection;

    private PreparedStatement addUser;

    private PreparedStatement getUsername;

    private PreparedStatement getPassword;

    private PreparedStatement deleteUser;

    private PreparedStatement addMaze;

    private PreparedStatement getMazeName;

    private PreparedStatement getMazeCreator;

    private PreparedStatement getMazeCreationDate;

    private PreparedStatement getMazeLastEdited;

    private PreparedStatement deleteMaze;

    public JDBCMazeDataSource() {
        connection = MazeDB.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CERATE_TABLE);

            addUser = connection.prepareStatement(INSERT_USER);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}

