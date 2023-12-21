import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AdminInterface extends JFrame {

    private List<User> userList;
    private DefaultListModel<String> listModel;
    private List<User> filteredUserList;

    public AdminInterface(List<User> userList) {
        this.userList = userList;
        this.listModel = new DefaultListModel<>();

        // Initialiser filteredUserList avec data de ma basededonnée
        initializeFilteredUserListFromDatabase();

        updateListModel(filteredUserList);

        setTitle("Admin Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(135, 206, 250));
        setLocationRelativeTo(null);
        JList<String> userListDisplay = new JList<>(listModel);
        userListDisplay.setBackground(new Color(135, 206, 250));
        JScrollPane scrollPane = new JScrollPane(userListDisplay);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel filterPanel = new JPanel(new FlowLayout());

        JLabel filterLabel = new JLabel("Filter by:");
        JComboBox<String> filterComboBox = new JComboBox<>(new String[]{"Specialty", "Club"});
        JButton filterButton = new JButton("Filter");
        setSize(600,600);
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Filter the user list based on the selected option
                String selectedFilter = (String) filterComboBox.getSelectedItem();
                if ("Specialty".equals(selectedFilter)) {
                    String selectedSpecialty = JOptionPane.showInputDialog(null, "Enter Specialty:");
                    filterBySpecialty(selectedSpecialty);
                } else if ("Club".equals(selectedFilter)) {
                    String selectedClub = JOptionPane.showInputDialog(null, "Enter Club:");
                    filterByClub(selectedClub);
                }
                updateListModel(filteredUserList);
            }
        });

        filterPanel.add(filterLabel);
        filterPanel.add(filterComboBox);
        filterPanel.add(filterButton);

        mainPanel.add(filterPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Add this method to initialize filteredUserList from the database
    private void initializeFilteredUserListFromDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3360/projetjava";
            String user = "root";
            String password = "maram";
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients");

            userList.clear(); // Clear the existing list
            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String specialite = resultSet.getString("specialite");
                String club = resultSet.getString("club");

                userList.add(new User(nom, prenom, specialite, club));
            }

            filteredUserList = userList;

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateListModel(List<User> users) {
        listModel.clear();
        for (User user : users) {
            listModel.addElement("Nom: " + user.getNom() + ", Prénom: " + user.getPrenom() +
                    ", Spécialité: " + user.getSpecialite() + ", Club: " + user.getClub());
        }
    }

    private void filterBySpecialty(String specialty) {
        filteredUserList = userList.stream()
                .filter(user -> user.getSpecialite().equalsIgnoreCase(specialty))
                .collect(Collectors.toList());
    }

    private void filterByClub(String club) {
        filteredUserList = userList.stream()
                .filter(user -> user.getClub().equalsIgnoreCase(club))
                .collect(Collectors.toList());
    }
}