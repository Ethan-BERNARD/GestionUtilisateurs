package appli.frais;
import java.sql.*;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe {@code AccesBdD} permettant la gestion et l'accès aux données
 * de la base MySQL <b>applifrais</b>.
 * <p>
 * Cette classe assure :
 * <ul>
 *   <li>La connexion à la base de données</li>
 *   <li>La lecture des informations des utilisateurs</li>
 *   <li>L’ajout, la modification et la suppression d’utilisateurs</li>
 *   <li>La récupération des rôles et des logins</li>
 *   <li>La génération automatique de logins et de mots de passe uniques</li>
 * </ul>
 * 
 * <p>Elle constitue la couche d’accès aux données du projet.</p>
 * 
 * @author ebernard
 * @version 1.0
 */
public class AccesBdD {

    /** URL de connexion à la base de données MySQL */
    private final String dbURL = "jdbc:mysql://localhost:3306/applifrais_v1?useSSL=false&allowPublicKeyRetrieval=true";

    /** Nom d'utilisateur MySQL */
    private final String username = "root";

    /** Mot de passe MySQL */
    private final String password = "";

    /** Objet {@link Connection} pour gérer la connexion active à la base */
    private Connection connexion;

    /**
     * Constructeur de la classe {@code AccesBdD}.
     * <p>
     * Établit la connexion à la base de données lors de l’instanciation.
     * En cas d’échec, l’erreur est journalisée via le {@link Logger}.
     */
    public AccesBdD() {
        try {
            this.connexion = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retourne la connexion active à la base de données.
     * 
     * @return Objet {@link Connection} actuellement ouvert
     */
    public Connection getConnexion() {
        return connexion;
    }

    /**
     * Récupère l’ensemble des utilisateurs présents dans la table {@code visiteur}.
     * 
     * @return Un {@link ResultSet} contenant tous les utilisateurs, ou {@code null} en cas d’erreur
     */
    public ResultSet getLesUtilisateurs() {
        try {
            String sql = "SELECT * FROM visiteur;";
            PreparedStatement statement = connexion.prepareStatement(sql);
            return statement.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Recherche un utilisateur par son identifiant (ID).
     * 
     * @param idUtil Identifiant (ID) de l’utilisateur recherché
     * @return Un {@link ResultSet} contenant le ou les utilisateurs correspondants, ou {@code null} en cas d’erreur
     */
    public ResultSet getLesUtilisateursUnId(String idUtil) {
        try {
            String sql = "SELECT * FROM visiteur WHERE LOWER(id) LIKE ?;";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, idUtil.toLowerCase() + "%");
            return statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Recherche un utilisateur par son nom.
     * 
     * @param nom Nom de l’utilisateur recherché
     * @return Un {@link ResultSet} contenant le ou les utilisateurs correspondants, ou {@code null} en cas d’erreur
     */
    public ResultSet getLesUtilisateursUnNom(String nom) {
        try {
            String sql = "SELECT * FROM visiteur WHERE LOWER(nom) LIKE ?;";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, nom.toLowerCase() + "%");
            return statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Récupère la liste de tous les logins existants dans la table {@code visiteur}.
     * 
     * @return Un {@link ResultSet} contenant les logins, ou {@code null} en cas d’erreur
     */
    public ResultSet getLesLogins() {
        try {
            String sql = "SELECT login FROM visiteur;";
            PreparedStatement statement = connexion.prepareStatement(sql);
            return statement.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Ajoute un nouvel utilisateur dans la table {@code visiteur}.
     * 
     * @param id Identifiant unique de l’utilisateur
     * @param nom Nom de famille
     * @param prenom Prénom
     * @param login Login unique
     * @param mdp Mot de passe
     * @param adresse Adresse postale
     * @param cp Code postal
     * @param ville Ville
     * @param dateEmbauche Date d’embauche
     * @return 1 si l’ajout a réussi, -1 en cas d’échec
     */
    public int ajoutUtilisateurs(String id, String nom, String prenom, String login, String mdp,
                                 String adresse, String cp, String ville, Date dateEmbauche) {
        try {
            String sql = "INSERT INTO visiteur (id, nom, prenom, login, mdp, adresse, cp, ville, dateEmbauche) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, nom);
            statement.setString(3, prenom);
            statement.setString(4, login);
            statement.setString(5, mdp);
            statement.setString(6, adresse);
            statement.setString(7, cp);
            statement.setString(8, ville);
            statement.setDate(9, dateEmbauche);

            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Supprime un utilisateur de la table {@code visiteur} selon son identifiant.
     * 
     * @param idUtil Identifiant de l’utilisateur à supprimer
     * @return Le nombre de lignes supprimées (0 si échec)
     */
    public int supprimerUtilisateur(int idUtil) {
        String sql = "DELETE FROM visiteur WHERE id = ?";
        try (PreparedStatement statement = connexion.prepareStatement(sql)) {
            statement.setInt(1, idUtil);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, "Erreur lors de la suppression de l'utilisateur", ex);
        }
        return 0;
    }

    /**
     * Met à jour les informations d’un utilisateur existant dans la table {@code visiteur}.
     * 
     * @param id Identifiant unique
     * @param nom Nom de famille
     * @param prenom Prénom
     * @param login Login de connexion
     * @param adresse Adresse postale
     * @param cp Code postal
     * @param ville Ville
     * @param dateEmbauche Date d’embauche
     * @return Le nombre de lignes modifiées (0 si aucune modification)
     */
    public int modifUtilisateur(String id, String nom, String prenom,
                                String login, String adresse, String cp, String ville, Date dateEmbauche){
        try {
            String sql = "UPDATE visiteur SET "
                    + " id = ?, nom = ?, prenom = ?, login = ?, "
                    + " adresse = ?, cp = ?, ville = ?, dateEmbauche = ? "
                    + " WHERE id = ?;";

            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, nom);
            statement.setString(3, prenom);
            statement.setString(4, login);
            statement.setString(5, adresse);
            statement.setString(6, cp);
            statement.setString(7, ville);
            statement.setDate(8, dateEmbauche);
            statement.setString(9, id); 

            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Récupère le rôle d’un utilisateur autorisé à partir de son identifiant et de son mot de passe.
     * 
     * @param identifiantUtil Identifiant de l’utilisateur autorisé
     * @param mdp Mot de passe associé
     * @return Un {@link ResultSet} contenant le rôle correspondant, ou {@code null} en cas d’erreur
     */
    public ResultSet getRole(String identifiantUtil, String mdp){
        try {
            String sql = "SELECT role FROM utilisateurautorise WHERE identifiantUtil = ? AND mdp = ?;";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, identifiantUtil);
            statement.setString(2, mdp);
            return statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Génère automatiquement un login unique à partir du nom et prénom d’un utilisateur.
     * 
     * @param nom Nom de famille
     * @param prenom Prénom
     * @return Un login unique non présent dans la base
     */
    public String genererLoginUnique(String nom, String prenom) {
        nom = (nom != null) ? nom.trim().toLowerCase() : "";
        prenom = (prenom != null) ? prenom.trim().toLowerCase() : "";

        if (nom.isEmpty() || prenom.isEmpty()) {
            throw new IllegalArgumentException("Nom et prénom doivent être renseignés pour générer un login.");
        }

        String baseLogin = prenom.substring(0, 1) + nom;
        String login = baseLogin;
        int compteur = 1;

        while (loginExiste(login)) {
            login = baseLogin + compteur;
            compteur++;
        }

        return login;
    }

    /**
     * Génère un mot de passe aléatoire de 5 caractères basé sur un UUID.
     * 
     * @return Un mot de passe court et pseudo-aléatoire
     */
    public String genererMotDePasse() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5);
    }

    /**
     * Vérifie si un login existe déjà dans la table {@code visiteur}.
     * 
     * @param login Login à vérifier
     * @return {@code true} si le login existe déjà, sinon {@code false}
     */
    public boolean loginExiste(String login) {
        try {
            String sql = "SELECT COUNT(*) FROM visiteur WHERE login = ?;";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, "Erreur lors de la vérification du login", e);
        }
        return false;
    }
    
    /**
    * Vérifie si un utilisateur existe déjà dans la base de données {@code visiteur}
    * en comparant les champs nom, prénom, adresse, code postal, ville et date d'embauche.
    * <p>
    * Cette méthode permet d'éviter les doublons d'utilisateurs lors de l'ajout.
    * Elle utilise une requête SQL préparée pour garantir la sécurité contre les injections.
    *
    * @param nom           Le nom de l'utilisateur à vérifier
    * @param prenom        Le prénom de l'utilisateur à vérifier
    * @param adresse       L'adresse postale de l'utilisateur
    * @param cp            Le code postal de l'utilisateur
    * @param ville         La ville de résidence de l'utilisateur
    * @param dateEmbauche  La date d'embauche de l'utilisateur
    * @return {@code true} si un utilisateur avec ces informations existe déjà, {@code false} sinon
    */
    public boolean utilisateurExiste(String nom, String prenom, String adresse, String cp, String ville, Date dateEmbauche) {
        String sql = "SELECT COUNT(*) FROM visiteur WHERE nom = ? AND prenom = ? AND adresse = ? AND cp = ? AND ville = ? AND dateEmbauche = ?";
        try (PreparedStatement stmt = connexion.prepareStatement(sql)) {
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, adresse);
            stmt.setString(4, cp);
            stmt.setString(5, ville);
            stmt.setDate(6, dateEmbauche);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, "Erreur lors de la vérification de l'utilisateur", ex);
        }
        return false;
    }
    
    /**
    * Vérifie si un utilisateur est autorisé à accéder à l'application.
    * <p>
    * Cette méthode interroge la base de données pour savoir si un enregistrement
    * correspondant à l'identifiant fourni existe dans la table {@code utilisateurautorise}.
    * Elle retourne {@code true} si l'utilisateur est présent, {@code false} sinon.
    * </p>
    *
    * @param login L'identifiant de l'utilisateur à vérifier.
    * @return {@code true} si l'utilisateur est autorisé, {@code false} sinon.
    */
    public boolean estUtilisateurAuthentifie(String login) {
        try (PreparedStatement ps = connexion.prepareStatement(
            "SELECT COUNT(*) FROM utilisateurautorise WHERE identifiantUtil = ?")) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, "Erreur lors de la vérification de l'utilisateur", ex);
        }
        return false;
    }

}