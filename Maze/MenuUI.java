package Maze;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.imageio.plugins.tiff.TIFFDirectory;
import javax.swing.*;
import javax.swing.table.TableColumnModel;

public class MenuUI extends JFrame {
    private CardLayout card;
    private JPanel menuPanel;

    public MenuUI() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        super("Maze Creator");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initGUI();
        MenuComponents();
    }

    private void initGUI() {
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 360));
        setResizable(false);
        pack();

        setLocationRelativeTo(null);
    }

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

    // The Main Menu where the User is to Select User
    private JPanel MainMenu() {
        // Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(Box.createVerticalGlue());

        // Title
        JLabel title = new JLabel("Maze Creator");
        title.setFont(new Font("Monospaced", Font.PLAIN, 25));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Select User
        JComboBox<String> selectUser = new JComboBox<>(new String[] {"Select User...", "User1", "(Create New User)"} ); // User1 is here to test (Remove later)
        selectUser.setMaximumSize(new Dimension(240, 50));

        // Continue Button
        JButton contButton = new JButton("Continue");
        contButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Action Listen
        
        //== Continue Button Listener
        contButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (selectUser.getSelectedItem().toString()) {
                    case "Select User...":
                        System.out.println("No selected user");
                        break;
                    case "(Create New User)":
                        card.show(menuPanel, "userP");
                        break;
                    default:
                        try {
                            System.out.format("Selected: %s \n", selectUser.getSelectedItem().toString());
                            setTitle(String.format("Maze Creator (%s)", selectUser.getSelectedItem().toString()));
                            card.show(menuPanel, "mazeP");
                        }
                        // Also try an exception to catch if the user does not exist like if it's deleted.
                        catch (Exception exception) {
                            System.out.println(exception);
                        }
                        break;
                }
                
            }

        });

        
        // Add to Panel
        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(selectUser);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(contButton);
        mainPanel.add(Box.createVerticalGlue());
        return mainPanel;
    }

    // The menu to create a new user.
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
        JLabel nameLabel = new JLabel("Name: ");

        // Input Text Field
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 20));

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
                    System.out.format("Created new user '%s' \n", nameField.getText());
                    setTitle(String.format("Maze Creator (%s)", nameField.getText()));
                    card.show(menuPanel, "mazeP");
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

        buttonPanel.add(backButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(createButton);

        newUserPanel.add(title);
        newUserPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        newUserPanel.add(namePanel);
        newUserPanel.add(buttonPanel);
        newUserPanel.add(Box.createVerticalGlue());

        return newUserPanel;
    }

    // The menu for importing a saved maze or create a new maze.
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

    // Create New Maze Pane
    private JPanel NewMazePanel() {
        // New Maze Panel
        JPanel newMazeP = new JPanel();
        newMazeP.setLayout(new BoxLayout(newMazeP, BoxLayout.PAGE_AXIS));

        // Panel Group 1 (Settings)
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
        JPanel VerticalSize = new JPanel();
        JLabel VerticalLabel = new JLabel("Y: ");
        JSpinner VerticalSpinner = new JSpinner(spinModel2);
        VerticalSize.add(VerticalLabel);
        VerticalSize.add(VerticalSpinner);

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
                EditorUI editor = UIHandler.CreateEditor("User1");
                setVisible(false);
                editor.setVisible(true);
                
                editor.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e)
                    {
                        setVisible(true);
                        e.getWindow().dispose();
                    }
                });
            }
            
        });

        // Add to Panel
        generationP.add(blankRadio);
        generationP.add(randomRadio);

        includeLogoP.add(logoCheckbox);

        mazeSizeP.add(horizontalSize);
        mazeSizeP.add(VerticalSize);

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

    // Opens an existing maze from the Database
    private JPanel OpenMazePanel() {
        JPanel openPanel = new JPanel();

        // Table Panel
        JPanel tablePanel = new JPanel();
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        

        // Create Table
        String[] columnNames = {"Maze Title", "Size", "Author", "Date Created"};

        // Table data
        Object[][] data = {
            // Currently test data
            {"Maze1", "50x50", "Name1", "2022-07-07"},
            {"Maze2", "55x55", "Name2", "2022-07-08"},
        };

        JTable mazeTable = new JTable(data, columnNames);
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
}
