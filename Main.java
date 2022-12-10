import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Main {
    static ArrayList<Data> a = new ArrayList<Data>();
    static int counter = 0;
    public static void main(String args[]) {
        JFrame frame = new JFrame("MeetVerse");
        frame.setSize(650,500);
        frame.setLayout(null);
        JButton button = new JButton("Login");
        button.setBounds(100,200,150,50);
        frame.add(button);
        button.addActionListener(new Login());
        JButton button2 = new JButton("Create a new Protected Password");
        button2.setBounds(300,200,250,50);
        frame.add(button2);
        button2.addActionListener(new Password());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
class Login implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        JFrame frame2 = new JFrame();
        String website = JOptionPane.showInputDialog(frame2, "What website would you like to sign in to?");
        String encrypt = JOptionPane.showInputDialog(frame2, "Enter your encrypted password.");
        boolean login = Data.Login(website, encrypt, Main.a);
        if(login) {
            JOptionPane.showMessageDialog(frame2, "Successfully signed in!");
        }
        if(!login) {
            JOptionPane.showMessageDialog(frame2, "Failed to sign in, please try again.");
        }
    }
}
class Password implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        JFrame frame3 = new JFrame();
        String website = JOptionPane.showInputDialog(frame3, "What website will the password be for?");
        String password = JOptionPane.showInputDialog(frame3, "Input your password.");
        Main.a.add(new Data());
        Main.a.get(Main.counter).dataobj(website,password);
        Main.counter++;
        JOptionPane.showMessageDialog(frame3, "This is your new encrypted password: " + Data.encrypted);
   }   
}
