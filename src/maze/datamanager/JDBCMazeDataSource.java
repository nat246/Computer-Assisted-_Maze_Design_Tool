package maze.datamanager;

import maze.Maze;
import user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for retrieving data from the XML file holding the user and maze informaiton.
 */

public class JDBCMazeDataSource implements Data{

    public static final String CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS users ("
                 + "id INTEGER PRIMARY KEY /* 40101 AUTO_INCREMENT */ NOT NULL UNIQUE,"
                 + "Name VARCHAR(30) , "
                 + "Password VARCHAR(30) );";

    public static final String CREATE_MAZE_TABLE =
            "CREATE TABLE IF NOT EXISTS mazes ("
                 + "id INTEGER PRIMARY KEY /* 40101 AUTO_INCREMENT */ NOT NULL UNIQUE,"
                 + "Name VARCHAR(30), "
                 + "Creator VARCHAR(30) , "
                 + "CreationTime VARCHAR(30) , "
                 + "LastEdited (VARCHAR(30) );";

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
    private static final String GET_MAZECREATEDATE = "SELECT mazeCreationTime FROM mazes WHERE mazeName=?";
    private static final String GET_MAZEEDITDATE = "SELECT mazeLastEdited FROM mazes WHERE mazeName=?";
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

    /**
     * @see maze.datamanager.MazeDataSource#addUser(User)
     */
    public void addUser(User u) {
        try {
            addUser.setString(1, u.getName());
            addUser.setString(2, u.getPassword());
            addUser.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see maze.datamanager.MazeDataSource#getUserID(User) 
     */
    public void getUserID(User u) {
        try {
            getUserID.setString(1, u.getName());
            getUserID.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see maze.datamanager.MazeDataSource#getUsername(User) 
     */
    public void getUsername(User u) {
        try {
            getUsername.setString(1, u.getName());
            getUsername.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see maze.datamanager.MazeDataSource#getPassword(User) 
     */
    public void getPassword(User u) {
        try {
            getPassword.setString(1, u.getName());
            getPassword.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see maze.datamanager.MazeDataSource#deleteUser(User) 
     */
    public void deleteUser(User u) {
        try {
            deleteUser.setString(1, u.getName());
            deleteUser.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see maze.datamanager.MazeDataSource#addMaze(Maze) 
     */
    public void addMaze(Maze m) {
        try {
            addMaze.setString(1, m.getMazeName());
            addMaze.setString(2, m.getAuthorName());
            addMaze.setString(3, m.getDateCreated());
            addMaze.setString(4, m.getLastEdited());
            addMaze.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see maze.datamanager.MazeDataSource#getMazeID(Maze) 
     */
    public void getMazeID(Maze m) {
        try {
            getMazeID.setString(1, m.getMazeName());
            getMazeID.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see maze.datamanager.MazeDataSource#getMaze(Maze) 
     */
    public void getMaze(Maze m) {
        try {
            getMaze.setString(1, m.getMazeName());
            getMaze.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see maze.datamanager.MazeDataSource#getMazeName(Maze) 
     */
    public void getMazeName(Maze m) {
        try {
            getMazeName.setString(1, m.getMazeName());
            getMazeName.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see maze.datamanager.MazeDataSource#getMazeCreator(Maze) 
     */
    public void getMazeCreator(Maze m) {
        try {
            getMazeCreator.setString(1, m.getMazeName());
            getMazeCreator.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see maze.datamanager.MazeDataSource#getMazeCreationDate(Maze) 
     */
    public void getMazeCreationDate(Maze m) {
        try {
            getMazeCreationDate.setString(1, m.getMazeName());
            getMazeCreationDate.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see maze.datamanager.MazeDataSource#getMazeLastEdited(Maze)
     */
    public void getMazeLastEdited(Maze m) {
        try {
            getMazeLastEdited.setString(1, m.getMazeName());
            getMazeLastEdited.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see maze.datamanager.MazeDataSource#deleteMaze(Maze)
     */
    public void deleteMaze(Maze m) {
        try {
            deleteMaze.setString(1, m.getMazeName());
            deleteMaze.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see maze.datamanager.MazeDataSource#close()
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

