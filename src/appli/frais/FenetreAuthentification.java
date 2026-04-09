package appli.frais;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * La classe {@code FenetreAuthentification} représente la fenêtre graphique d’authentification
 * de l'application AppliFrais. Elle permet à l'utilisateur de saisir son identifiant et son mot
 * de passe, puis de se connecter au système en validant les informations dans la base de données.
 *
 * <p>Cette fenêtre constitue le point d'entrée de l'application et assure la communication
 * avec la couche d'accès aux données via la classe {@link AccesBdD}.</p>
 *
 * <p>Elle hérite de {@link javax.swing.JFrame} et utilise des composants Swing pour
 * l'affichage de l'interface utilisateur.</p>
 *
 * @author Ethan Bernard
 * @version 1.0
 */
public class FenetreAuthentification extends javax.swing.JFrame {
    private final AccesBdD bd;
    private final int tentatives = 0;

    /**
     * Constructeur par défaut de la classe {@code FenetreAuthentification}.
     * Il initialise la connexion à la base de données via {@link AccesBdD} et
     * crée les composants graphiques de la fenêtre (champs, labels, boutons, etc.).
     */
    public FenetreAuthentification() {
        this.bd = new AccesBdD();
        initComponents();
    }

    /**
     * Initialise les composants graphiques de la fenêtre d’authentification.
     *
     * <p><b>Attention :</b> Cette méthode est générée automatiquement par l’éditeur
     * de formulaires NetBeans. Elle ne doit pas être modifiée manuellement.</p>
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHeader = new javax.swing.JPanel();
        jLabelTitre = new javax.swing.JLabel();
        btnFermer = new javax.swing.JToggleButton();
        boxMdp = new javax.swing.JPasswordField();
        boxId = new javax.swing.JTextField();
        jLabelConnexion = new javax.swing.JLabel();
        jLabelMdp = new javax.swing.JLabel();
        btnConnexion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanelHeader.setBackground(new java.awt.Color(0, 102, 153));

        jLabelTitre.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabelTitre.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitre.setText("Gestion des Utilisateurs");

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTitre)
                .addGap(110, 110, 110))
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabelTitre)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        btnFermer.setText("Fermer");
        btnFermer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFermerActionPerformed(evt);
            }
        });

        jLabelConnexion.setText("Identifiant :");

        jLabelMdp.setText("Mot de Passe :");

        btnConnexion.setText("Connexion");
        btnConnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnexionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFermer, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelConnexion)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(boxMdp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxId, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabelMdp))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnConnexion)
                        .addGap(60, 60, 60)))
                .addGap(196, 196, 196))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                .addComponent(jLabelConnexion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabelMdp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxMdp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(btnConnexion)
                .addGap(125, 125, 125)
                .addComponent(btnFermer)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Action du bouton "Fermer".  
     * Ferme l’application en appelant {@code System.exit(0)}.
     *
     * @param evt l’événement déclenché par le clic sur le bouton
     */
    private void btnFermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFermerActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnFermerActionPerformed

    
    /**
     * Action du bouton "Connexion".  
     * Vérifie les identifiants saisis et ouvre la fenêtre d’accueil si
     * l’utilisateur possède le rôle {@code admin}.
     *
     * <p>Affiche des messages d’erreur dans les cas suivants :</p>
     * <ul>
     *   <li>Champs vides</li>
     *   <li>Identifiants incorrects</li>
     *   <li>Rôle non autorisé</li>
     *   <li>Erreur SQL</li>
     * </ul>
     *
     * @param evt l’événement déclenché par le clic sur le bouton
     */
    private void btnConnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnexionActionPerformed
        String user = boxId.getText().trim();
        String mdp = new String(boxMdp.getPassword());

        if (user.isEmpty() || mdp.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez remplir tous les champs !", 
                "Erreur", 
                JOptionPane.WARNING_MESSAGE
            );
            boxId.setText("");
            boxMdp.setText("");
            return;
        }
        
        if (user.length() < 3 || mdp.length() < 3) {
            JOptionPane.showMessageDialog(this, 
                "Identifiant et mot de passe trop court.", 
                "Erreur", 
                JOptionPane.WARNING_MESSAGE
            );
            boxId.setText("");
            boxMdp.setText("");
            return;
        }
        
        if (!user.matches("[a-zA-Z0-9_]+")) {
            JOptionPane.showMessageDialog(this, 
                "L'identifiant ne doit contenir que des lettres, chiffres ou underscores.", 
                "Erreur", 
                JOptionPane.WARNING_MESSAGE
            );
            boxId.setText("");
            boxMdp.setText("");
            return;
        }      

        if (tentatives >= 5) {
            JOptionPane.showMessageDialog(this, 
                "Trop de tentatives. Veuillez réessayer plus tard.", 
                "Sécurité", 
                JOptionPane.ERROR_MESSAGE
            );
            boxId.setText("");
            boxMdp.setText("");
            return;
        }


        try {
            ResultSet rs = bd.getRole(user, mdp);

            if (rs != null && rs.next()) {
                String role = rs.getString("role");

                Session.authentifier(user, role);

                if (bd.estUtilisateurAuthentifie(user)) {
                    FenetreAccueil accueil = new FenetreAccueil();
                    accueil.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Accès refusé : vous n'êtes pas autorisé !", 
                        "Erreur", 
                        JOptionPane.ERROR_MESSAGE
                    );
                    boxId.setText("");
                    boxMdp.setText("");
                }
                rs.close();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Identifiants ou mot de passe incorrects !", 
                    "Erreur de connexion", 
                    JOptionPane.ERROR_MESSAGE
                );
                boxId.setText("");
                boxMdp.setText("");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Erreur base de données : " + ex.getMessage(), 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnConnexionActionPerformed

    /**
     * Point d’entrée principal de l’application.  
     * Configure le look and feel et affiche la fenêtre d’authentification.
     *
     * @param args les arguments passés à la ligne de commande
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetreAuthentification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FenetreAuthentification().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField boxId;
    private javax.swing.JPasswordField boxMdp;
    private javax.swing.JButton btnConnexion;
    private javax.swing.JToggleButton btnFermer;
    private javax.swing.JLabel jLabelConnexion;
    private javax.swing.JLabel jLabelMdp;
    private javax.swing.JLabel jLabelTitre;
    private javax.swing.JPanel jPanelHeader;
    // End of variables declaration//GEN-END:variables
}
