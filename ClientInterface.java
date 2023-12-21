import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientInterface extends JFrame {

    private proje mainInterface;

    public ClientInterface(proje mainInterface) {
        this.mainInterface = mainInterface;
        setTitle("Client Interface");
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 500, 10));

        JLabel nomLabel = new JLabel("Nom:");
        JTextField nomTextField = new JTextField(20);
        panel.add(nomLabel);
        panel.add(nomTextField);

        JLabel prenomLabel = new JLabel("Prénom:");
        JTextField prenomTextField = new JTextField(20);
        panel.add(prenomLabel);
        panel.add(prenomTextField);

        JLabel spec = new JLabel("Spécialité:");
        panel.add(spec);
        String[] options = {"big data", "im", "cocojava", "gaming"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        panel.add(comboBox);

        JLabel club = new JLabel("Club:");
        panel.add(club);
        String[] option = {"robotique", "microsoft", "j2i", "orenda"};
        JComboBox<String> clubs = new JComboBox<>(option);
        panel.add(clubs);
        setSize(600, 600);
        panel.setBackground(new Color(135, 206, 250));

        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = nomTextField.getText();
                String prenom = prenomTextField.getText();
                String specialite = (String) comboBox.getSelectedItem();
                String clubChoisi = (String) clubs.getSelectedItem();

                // Save data to the main interface
                mainInterface.addUser(new User(nom, prenom, specialite, clubChoisi));
                 mainInterface.saveToDatabase(new User(nom, prenom, specialite, clubChoisi));
                // Clear text fields
                nomTextField.setText("");
                prenomTextField.setText("");
            }
        });
        panel.add(ok);

        JButton annuler = new JButton("Annuler");
        annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Clear text fields on annuler
                nomTextField.setText("");
                prenomTextField.setText("");
            }
        });
        panel.add(annuler);

        add(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
