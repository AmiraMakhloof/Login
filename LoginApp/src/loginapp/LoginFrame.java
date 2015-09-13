
package loginapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LoginFrame extends javax.swing.JFrame {

    Connection conn;
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet resultSet;

    // constractor 
    public LoginFrame() {
        initComponents();

        setLocationRelativeTo(null);
        initDB();
        initEventDriven();
    } // constractor

    // method to code all the buttons  ... just for not make the constractor have alot of codes
    public void initEventDriven() {
        // obj from listener
        ExitListener lis1 = new ExitListener();
        // connect bteween source commponent(exit button) and listener(lis1 obj) .... by registration method(add+name of listener)
        jbtExit.addActionListener(lis1);
        // instead of these .... we can make it like LoginButton 



        // the code of LoginButton.... jbtlogin
        jbtLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String qLogin = "SELECT * FROM users WHERE"
                        + " username = '" + jtfUsername.getText()
                        + "' AND password = '" + jpfPassword.getText() + "'";
                try {
                    resultSet = stmt.executeQuery(qLogin);
                    if (resultSet.next()) {
                        // correct loing
                        // take user id to pass it to HomeFrame constractor to write it in HomeFrame label
                        int cuurentUserID = resultSet.getInt("id");
                        // go to homeFrame ... another frame 
                        new HomeFrame(cuurentUserID).setVisible(true);
                        // hide the current frame 
                        hide();
                    } else {
                        // failed to login
                        JOptionPane.showMessageDialog(null, "Invalid Credentials.!");
                        // clear all data in the textFields
                        jtfUsername.setText("");
                        jpfPassword.setText("");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        // the code of showHide button ..jbtShowHide
        jbtShowHide.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // if login button is visible
                if (jbtLogin.isVisible()) {
                    // hide login button
                    jbtLogin.setVisible(false);
                    // write "show" on showHide button
                    jbtShowHide.setText("Show");
                } else {
                    // show login button
                    jbtLogin.setVisible(true);
                    // write "hide" on showHide button
                    jbtShowHide.setText("Hide");

                }
            }
        });
    }

// to connect DB 
    public void initDB() {
        try {
            // load driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("driver loaded.!");
            // establish the connection
            // url , user , password
            conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1/project101", "root", "pentester");
            System.out.println("connection established.!");
            // create staement to write sql code in it 
            stmt = conn.createStatement();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtfUsername = new javax.swing.JTextField();
        jpfPassword = new javax.swing.JPasswordField();
        jbtLogin = new javax.swing.JButton();
        jbtExit = new javax.swing.JButton();
        jbtShowHide = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(1, 163, 217));
        jLabel1.setText("Welcome to XYZ app.!");

        jLabel2.setText("username");

        jLabel3.setText("password");

        jbtLogin.setText("Login");

        jbtExit.setText("Exit");

        jbtShowHide.setText("Change Color");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtShowHide, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jpfPassword))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jbtLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jbtExit, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jpfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtLogin)
                    .addComponent(jbtExit))
                .addGap(18, 18, 18)
                .addComponent(jbtShowHide)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jbtExit;
    private javax.swing.JButton jbtLogin;
    private javax.swing.JButton jbtShowHide;
    private javax.swing.JPasswordField jpfPassword;
    private javax.swing.JTextField jtfUsername;
    // End of variables declaration//GEN-END:variables

    
    // we change it in initEventDriven() method by an easy code 
    //    class ChangeColorListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if (jbtLogin.isVisible()) {
//                jbtLogin.setVisible(false);
//                jbtChangeColor.setText("Show");
//            } else {
//                jbtLogin.setVisible(true);
//                jbtChangeColor.setText("Hide");
//
//            }
//        }
//
//    }
} // main class ... LoginFrame class
// we can replace it by the code like login button in initEventDriven() method 
class ExitListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Goodbye.!");
        System.exit(0);
    }
}
