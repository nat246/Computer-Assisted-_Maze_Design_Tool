package maze.datamanager;

import maze.Maze;
import user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class for retrieving data from the XML file holding the user and maze informaiton.
 */

public class JDBCMazeDataSource implements MazeDataSource{

    public static final String CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER NOT NULL UNIQUE AUTO_INCREMENT ,"
                    + "name VARCHAR(30) UNIQUE , "
                    + "password VARCHAR(30) , PRIMARY KEY (id) );";

    public static final String CREATE_MAZE_TABLE =
            "CREATE TABLE IF NOT EXISTS mazes ("
                    + "id INTEGER NOT NULL UNIQUE AUTO_INCREMENT ,"
                    + "name VARCHAR(30) , "
                    + "creator VARCHAR(30) , "
                    + "creationTime VARCHAR(30) , "
                    + "lastEdited VARCHAR(30) , PRIMARY KEY (id) );";

    private static final String INSERT_USER = "INSERT INTO users (name, password) VALUES (?, ?)";
    private static final String GET_USERID = "SELECT id FROM users WHERE name=?";
    private static final String GET_USERNAME = "SELECT name FROM users WHERE name=?";
    private static final String GET_USERPASSWORD = "SELECT password FROM users WHERE name=?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id=?";
    private static final String GET_USER = "SELECT * FROM users WHERE name=?";
    private static final String GET_USERS = "SELECT name FROM users";
    private static final String INSERT_MAZE = "INSERT INTO mazes (name, creator, creationtime, lastedited) VALUES (?, ?, NOW(), NOW())";
    private static final String UPDATE_MAZE = "UPDATE maze SET lastedited = NOW() WHERE id = ?;";
    private static final String GET_MAZEID = "SELECT id FROM mazes WHERE name=?";
    private static final String GET_MAZENAME = "SELECT name FROM mazes WHERE name=?";
    private static final String GET_MAZECREATOR = "SELECT creator FROM mazes WHERE name=?";
    private static final String GET_MAZECREATEDATE = "SELECT creationtime FROM mazes WHERE name=?";
    private static final String GET_MAZEEDITDATE = "SELECT lastedited FROM mazes WHERE name=?";
    private static final String DELETE_MAZE = "DELETE FROM maze WHERE id =?";
    private static final String GET_MAZE = "SELECT * FROM mazes WHERE name=?";
    private static final String GET_MAZES = "SELECT name FROM mazes";

    private Connection connection;
    private PreparedStatement addUser;
    private PreparedStatement getUserID;
    private PreparedStatement getUsername;
    private PreparedStatement getPassword;
    private PreparedStatement deleteUser;
    private PreparedStatement getUser;
    private PreparedStatement getUsers;
    private PreparedStatement addMaze;
    private PreparedStatement saveMaze;
    private PreparedStatement getMazeID;
    private PreparedStatement getMazeName;
    private PreparedStatement getMazeCreator;
    private PreparedStatement getMazeCreationDate;
    private PreparedStatement getMazeLastEdited;
    private PreparedStatement deleteMaze;
    private PreparedStatement getMaze;
    private PreparedStatement getMazes;

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
            getUser = connection.prepareStatement(GET_USER);
            getUsers = connection.prepareStatement(GET_USERS);

            addMaze = connection.prepareStatement(INSERT_MAZE);
            saveMaze = connection.prepareStatement(UPDATE_MAZE);
            getMazeID = connection.prepareStatement(GET_MAZEID);
            getMazeName = connection.prepareStatement(GET_MAZENAME);
            getMazeCreator = connection.prepareStatement(GET_MAZECREATOR);
            getMazeCreationDate = connection.prepareStatement(GET_MAZECREATEDATE);
            getMazeLastEdited = connection.prepareStatement(GET_MAZEEDITDATE);
            deleteMaze = connection.prepareStatement(DELETE_MAZE);
            getMaze = connection.prepareStatement(GET_MAZE);
            getMazes = connection.prepareStatement(GET_MAZES);
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
            deleteUser.setInt(1, u.getUserId());
            deleteUser.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see maze.datamanager.MazeDataSource#getUser(String)
     */
    public User getUser(String name) {
        User u = new User();
        ResultSet rs = null;
        try {
            getUser.setString(1, name);
            rs= getUser.executeQuery();
            rs.next();
            u.setUserId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setPassword(rs.getString("password"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return u;
    }

    /**
     * @see maze.datamanager.MazeDataSource#userSet()
     */
    public Set<String> userSet() {
        Set<String> users = new TreeSet<String>();
        ResultSet rs= null;

        try {
            rs = getUsers.executeQuery();
            while (rs.next()){
                users.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    /**
     * @see maze.datamanager.MazeDataSource#addMaze(Maze)
     */
    public void addMaze(Maze m) {
        try {
            addMaze.setString(1, m.getMazeName());
            addMaze.setString(2, m.getAuthorName());
            addMaze.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see maze.datamanager.MazeDataSource#saveMaze(Maze)
     */
    public void saveMaze(Maze m) {
        try {
            saveMaze.setInt(1, m.getMazeID());
            saveMaze.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see maze.datamanager.MazeDataSource#getMazeID(Maze)
     */
    public void getMazeID(Maze m) {
        try {
            getMazeID.setInt(1, m.getMazeID());
            getMazeID.execute();
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
            deleteMaze.setInt(1, m.getMazeID());
            deleteMaze.execute();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see maze.datamanager.MazeDataSource#getMaze
     */
    public Maze getMaze(String mazeName) {
        Maze m = new Maze();
        ResultSet rs = null;
        try {
            getMaze.setString(1, m.getMazeName());
            rs = getMaze.executeQuery();
            rs.next();
            m.setMazeID(rs.getInt("id"));
            m.setMazeName(rs.getString("name"));
            m.setAuthorName(rs.getString("creator"));
            m.setDateCreated(rs.getString("creationTime"));
            m.setLastEdited(rs.getString("lastEdited"));
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return m;
    }

    /**
     * @see maze.datamanager.MazeDataSource#mazeSet()
     */
    public Set<String> mazeSet() {
        Set<String> mazes = new TreeSet<String>();
        ResultSet rs= null;

        try {
            rs = getMazes.executeQuery();
            while (rs.next()){
                mazes.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mazes;
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

