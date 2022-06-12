package maze.datamanager;
/**
 * Class inherited from Data class used for storing all the created maze and user details in an external database
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MazeDB {

    private static Connection instance = null;

    private MazeDB(){
        Properties props = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream("./db.props");
            props.load(in);
            in.close();

            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");
            String schema = props.getProperty("jdbc.schema");

            instance = DriverManager.getConnection(url + "/" + schema, username, password);
        }
        catch (SQLException sqle) {
            System.err.println(sqle);
        }
        catch (FileNotFoundException fnfe) {
            System.err.println(fnfe);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static Connection getInstance() {
        if (instance == null) {
            new MazeDB();
        }
        return instance;
    }
}
