package maze.datamanager;

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
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS address ("
                 + "idx INTEGER PRIMARY KEY /* 40101 AUTO_INCREMENT */ NOT NULL UNIQUE,"
                 + "Maze name VARCHAR(30), "
                 + "Maze creator VARCHAR(30) , "
                 + "Maze creation time VARCHAR(30) , "
                 + "Maze last edited (VARCHAR(30) , " + ");";

    private static final String INSERT_MAZE = "INSERT ";

    private static final String GET_MAZENAME = "";

    private static final String GET_MAZECREATOR = "";

    private static final String GET_MAZECREATEDATE = "";

    private static final String GET_MAZEEDITDATE = "";

    private static final String COUNT_MAZES = "";

    private static final String GET_USERNAME = "";

    private static final String GET_USER = "";

    private static final String DELETE_USER = "";

    private static final String COUNT_USERS = "";



    private Connection connection;

    private PreparedStatement addUser;



}

