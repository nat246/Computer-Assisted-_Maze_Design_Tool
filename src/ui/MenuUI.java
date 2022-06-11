package ui;

import maze.Maze;
import user.User;
import maze.datamanager.MazeDataHandler;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;
import java.io.Serializable;

/**.
 * UI class responsible for creating the menu options
 */
public class MenuUI extends JFrame implements Serializable{
    private User user;
    private CardLayout card;
    private JPanel menuPanel;
    MazeDataHandler data;

    /**
     *
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws UnsupportedLookAndFeelException
     */
    public MenuUI(MazeDataHandler data) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        super("Maze Maker");
        this.data = data;
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        addClosingListener(new ClosingListener());
        initGUI();
        MenuComponents();
    }

    /**
     * Initiates the menu options
     */
    private void initGUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 360));
        setResizable(false);
        pack();

        setLocationRelativeTo(null);
    }

    /**
     * Displays the main menu options of the UI
     */
    private void MenuComponents() {
        // Creates a new Panel to fit all Card Components
        card = new CardLayout();
        menuPanel = new JPanel();
        menuPanel.setLayout(card);

        menuPanel.add(MainMenu(), "main");
        menuPanel.add(CreateUserMenu(), "userP");
        menuPanel.add(CreateMazeMenu(), "mazeP");

        
        card.show(menuPanel, "main");
        getContentPane().add(menuPanel);
    }

    private JPanel MainMenu() {
        // Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(Box.createVerticalGlue());

        // Title
        JLabel title = new JLabel("Maze Maker");
        title.setFont(new Font("Monospaced", Font.PLAIN, 25));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Login information input
        // Input Panel
        JPanel namePanel = new JPanel();

        // Input Label
        JLabel nameLabel = new JLabel("Username: ");

        // Input Text Field
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 20));

        // Input Panel
        JPanel passPanel = new JPanel();

        // Input Label
        JLabel passLabel = new JLabel("Password: ");

        // Input Test Field
        JTextField passField = new JTextField();
        passField.setPreferredSize(new Dimension(200, 20));

        // Button Panel
        JPanel buttonPanel = new JPanel();

        // Create user button
        JButton logInButton = new JButton("Log in");

        // Register menu
        JButton registerButton = new JButton("Register");


        // Action Listen
        //== Log in Button Listener
        logInButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (nameField.getText().length() < 1 && passField.getText().length() < 1) {
                        JOptionPane.showConfirmDialog(menuPanel, "Please enter your username and password.", "Credentials missing", JOptionPane.CLOSED_OPTION);
                    } else if (nameField.getText().length() >= 1 && passField.getText().length() < 1) {
                        JOptionPane.showConfirmDialog(menuPanel, "Please enter your password.", "Password missing", JOptionPane.CLOSED_OPTION);
                    } else if (nameField.getText().length() < 1 && passField.getText().length() >= 1) {
                        JOptionPane.showConfirmDialog(menuPanel, "Please enter your username.", "Username missing", JOptionPane.CLOSED_OPTION);
                    } else {
                        user = new User();
                        user.setName(nameField.getText());
                        user.setPassword(passField.getText());
                        if (data.login(user) == false) {
                            JOptionPane.showConfirmDialog(menuPanel, "Your login information was incorrect.", "Incorrect information", JOptionPane.CLOSED_OPTION);
                        }
                        else {
                            data.getUser(user);
                            setTitle(String.format("Maze Maker (%s)", user.getName()));
                            card.show(menuPanel, "mazeP");
                        }
                    }
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        } );

        //== Register Button Listener
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(menuPanel, "userP");
                setTitle("Maze Maker");
            }
        });

        //Add to panel
        mainPanel.add(Box.createVerticalGlue());

        namePanel.add(nameLabel);
        namePanel.add(nameField);
        passPanel.add(passLabel);
        passPanel.add(passField);

        buttonPanel.add(logInButton);
        buttonPanel.add(registerButton);

        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(namePanel);

        mainPanel.add(passPanel);

        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());
        return mainPanel;
    }

    /**
     * The menu to create a new user.
     * @return newUserPanel creates a new user
     */
    private JPanel CreateUserMenu() {
        // Main Panel
        JPanel newUserPanel = new JPanel();
        newUserPanel.setLayout(new BoxLayout(newUserPanel, BoxLayout.PAGE_AXIS));

        // Title
        JLabel title = new JLabel("New User");
        title.setFont(new Font("Monospaced", Font.PLAIN, 25));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input Panel
        JPanel namePanel = new JPanel();
        
        // Input Label
        JLabel nameLabel = new JLabel("Username: ");

        // Input Text Field
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 20));

        // Input Panel
        JPanel passPanel = new JPanel();

        // Input Label
        JLabel passLabel = new JLabel("Password: ");

        // Input Test Field
        JTextField passField = new JTextField();
        passField.setPreferredSize(new Dimension(200, 20));

        // Button Panel
        JPanel buttonPanel = new JPanel();

        // Back Button
        JButton backButton = new JButton("Back");

        // Create user button
        JButton createButton = new JButton("Create");


        // Action Listeners

        //== Back button Listener
        backButton.addActionListener(e -> card.show(menuPanel, "main"));

        //== Create button Listener
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Name too long. Must be 50 characters long or under.
                if (nameField.getText().length() > 50) {
                    JOptionPane.showConfirmDialog(menuPanel, "Please enter a name shorter than 50 characters.", "Name too long", JOptionPane.CLOSED_OPTION);
                }
                // Checks if there is at least 1 character in the text box
                else if (nameField.getText().length() >= 1) {
                    // Password too long. Must be 50 characters or under)
                    if (passField.getText().length() > 50)  {
                        JOptionPane.showConfirmDialog(menuPanel, "Please enter a password shorter than 50 characters", "Password too long", JOptionPane.CLOSED_OPTION);
                    }
                    else if (passField.getText().length() >= 1) {
                        user = new User();
                        user.setName(nameField.getText());
                        user.setPassword(passField.getText());
                        data.addUser(user);
                        System.out.format("Created new user '%s' \n", user.getName());
                        setTitle(String.format("Maze Maker (%s)", user.getName()));
                        card.show(menuPanel, "mazeP");
                    }
                    else {
                        JOptionPane.showConfirmDialog(menuPanel, "Please enter a password.", "Password not found", JOptionPane.CLOSED_OPTION);
                    }
                }
                // Displays a popup when there is no name
                else {
                    JOptionPane.showConfirmDialog(menuPanel, "Please enter a name.", "Name not found", JOptionPane.CLOSED_OPTION);
                }
            }
        });

        // Add to Panel
        newUserPanel.add(Box.createVerticalGlue());
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        passPanel.add(passLabel);
        passPanel.add(passField);


        buttonPanel.add(backButton);
        buttonPanel.add(createButton);

        newUserPanel.add(title);
        newUserPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        newUserPanel.add(namePanel);
        newUserPanel.add(passPanel);
        newUserPanel.add(buttonPanel);
        newUserPanel.add(Box.createVerticalGlue());
        return newUserPanel;
    }

    /**
     * The menu for importing a saved maze or create a new maze.
     * @return newMenuPanel creates a new maze or opens an existing maze
     */
    private JPanel CreateMazeMenu() {
        // Main Panel
        JPanel newMenuPanel = new JPanel();
        newMenuPanel.setLayout(new BoxLayout(newMenuPanel, BoxLayout.PAGE_AXIS));

        // Tabbed Pane
        JTabbedPane tabPane = new JTabbedPane();

        tabPane.addTab("New Maze", NewMazePanel());
        tabPane.addTab("Open Maze", OpenMazePanel());

        newMenuPanel.add(tabPane);

        return newMenuPanel;
    }

    /**
     * Create New Maze Pane
     * @return newMazeP creates a new maze using user input dimensions or generates a random maze
     */
    private JPanel NewMazePanel() {
        // New Maze Panel
        JPanel newMazeP = new JPanel();
        newMazeP.setLayout(new BoxLayout(newMazeP, BoxLayout.Y_AXIS));

        // Panel Group 1 (Settings)1
        JPanel panelGroup1 = new JPanel();

        // Random generate option (inside newMazeP)
        JPanel generationP = new JPanel();
        generationP.setBorder(BorderFactory.createTitledBorder("Maze Generation"));

        JRadioButton blankRadio = new JRadioButton("Blank Maze", true);
        JRadioButton randomRadio = new JRadioButton("Randomly Generated Maze");

        ButtonGroup buttongp = new ButtonGroup();
        buttongp.add(blankRadio);
        buttongp.add(randomRadio);

        // Include Logo
        JPanel includeLogoP = new JPanel();
        includeLogoP.setBorder(BorderFactory.createTitledBorder("Logo"));

        JCheckBox logoCheckbox = new JCheckBox("Include Logo");

        // Maze Size
        JPanel mazeSizeP = new JPanel();
        mazeSizeP.setBorder(BorderFactory.createTitledBorder("Maze Dimensions"));

        SpinnerModel spinModel1 = new SpinnerNumberModel(10, 0, 100, 1);
        SpinnerModel spinModel2 = new SpinnerNumberModel(10, 0, 100, 1);

        //== Maze Size X
        JPanel horizontalSize = new JPanel();
        JLabel horizontalLabel = new JLabel("X: ");
        JSpinner horizontalSpinner = new JSpinner(spinModel1);
        horizontalSize.add(horizontalLabel);
        horizontalSize.add(horizontalSpinner);

        //== Maze Size Y
        JPanel verticalSize = new JPanel();
        JLabel verticalLabel = new JLabel("Y: ");
        JSpinner verticalSpinner = new JSpinner(spinModel2);
        verticalSize.add(verticalLabel);
        verticalSize.add(verticalSpinner);

        // Maze Path
        JPanel mazePath = new JPanel();
        mazePath.setBorder(BorderFactory.createTitledBorder("Maze Path"));

        JCheckBox pathCheckbox = new JCheckBox("Show Maze Path  ");

        // Panel Group 2 (Buttons)
        JPanel panelGroup2 = new JPanel();

        // Back Button
        JButton backButton = new JButton("Back");

        // Create Button
        JButton createButton = new JButton("Create Maze");

        // Action Listeners

        //== Back button Listener
        backButton.addActionListener(e -> card.show(menuPanel, "main"));

        //== Create button Listener
        createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {

                    int row = (int) horizontalSpinner.getValue();
                    int col = (int) verticalSpinner.getValue();


                    Maze newMaze = new Maze(new int[] {row, col}, user.getName(), randomRadio.isSelected());
                    EditorUI editor = new EditorUI(user, newMaze, data);
                    setVisible(false);
                    editor.setVisible(true);

                    editor.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            setVisible(true);
                        }
                    });
                });
            }
        });

        // Add to Panel
        generationP.add(blankRadio);
        generationP.add(randomRadio);

        includeLogoP.add(logoCheckbox);

        mazeSizeP.add(horizontalSize);
        mazeSizeP.add(verticalSize);

        mazePath.add(pathCheckbox);

        //== Add to Panel Group 1
        panelGroup1.add(generationP);
        panelGroup1.add(Box.createRigidArea(new Dimension(10, 0)));
        panelGroup1.add(includeLogoP);
        panelGroup1.add(Box.createRigidArea(new Dimension(10, 0)));
        panelGroup1.add(mazeSizeP);
        panelGroup1.add(Box.createRigidArea(new Dimension(10, 0)));
        panelGroup1.add(mazePath);


        //== Add to Panel Group 2
        panelGroup2.add(backButton);
        panelGroup2.add(Box.createRigidArea(new Dimension(15, 0)));
        panelGroup2.add(createButton);
        
        
        newMazeP.add(Box.createVerticalGlue());
        newMazeP.add(panelGroup1);
        newMazeP.add(panelGroup2);

        return newMazeP;
    }

    /**
     * Opens an existing maze from the Database
     * @return openPanel for accessing various menu options of a maze
     */
    private JPanel OpenMazePanel() {
        JPanel openPanel = new JPanel();

        // Table Panel
        JPanel tablePanel = new JPanel();
        
        // Button Panel
        JPanel buttonPanel = new JPanel();

        // Create Table
        String[] columnNames = {"Maze Title", "Author", "Date Created", "Last Modified"};

        // Table data
        String[][] mData= new String[data.getMazeCount()][4];

        for (int i = 0; i < data.getMazeCount(); i++) {
            Maze mz = data.getMaze(i + 1);
            System.out.println(mz.getMazeName());
            System.out.println(mz.getAuthorName());
            System.out.println(mz.getDateCreated());
            System.out.println(mz.getLastEdited());
            mData[i][0] = mz.getMazeName();
            mData[i][1] = mz.getAuthorName();
            mData[i][2] = mz.getDateCreated();
            mData[i][3] = mz.getLastEdited();
        }

        JTable mazeTable = new JTable(mData, columnNames);
        mazeTable.setPreferredScrollableViewportSize(new Dimension(500, 210));
        mazeTable.setFillsViewportHeight(true);
        mazeTable.setColumnSelectionAllowed(false);
        mazeTable.setDefaultEditor(Object.class, null);
        mazeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Table column width
        TableColumnModel columnModel = mazeTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(180);
        columnModel.getColumn(2).setPreferredWidth(100);

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(mazeTable); 


        // Back Button
        JButton backButton = new JButton("Back");

        // Open Button
        JButton openButton = new JButton("Open Maze");

        // Delete Button
        JButton deleteButton = new JButton("Delete Maze");

        // Action Listeners

        //== Back button Listener
        backButton.addActionListener(e -> card.show(menuPanel, "main"));

        //== Open button Listener
            // TO DO


        // Add to Panels
        tablePanel.add(scrollPane);

        buttonPanel.add(backButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(openButton);

        openPanel.add(Box.createVerticalGlue());
        openPanel.add(tablePanel);
        openPanel.add(buttonPanel);

        return openPanel;
    }

    private void addClosingListener(WindowListener listener) {addWindowListener(listener);}

    private class ClosingListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            data.persist();
            System.exit(0);
        }
    }
}
