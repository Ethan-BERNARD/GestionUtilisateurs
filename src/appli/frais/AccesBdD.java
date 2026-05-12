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
 * <li>La connexion à la base de données</li>
 * <li>La lecture des informations des utilisateurs</li>
 * <li>L’ajout, la modification et la suppression d’utilisateurs</li>
 * <li>La récupération des rôles et des logins</li>
 * <li>La génération automatique de logins et de mots de passe uniques</li>
 * </ul>
 * * <p>Elle constitue la couche d’accès aux données du projet.</p>
 * * @author ebernard
 * @version 1.1
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
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, "Échec de la connexion", ex);
        }
    }

    /**
     * Retourne la connexion active à la base de données.
     * * @return Objet {@link Connection} actuellement ouvert
     */
    public Connection getConnexion() {
        return connexion;
    }

    /**
     * Récupère l’ensemble des utilisateurs présents dans la table {@code visiteur}.
     * * @return Un {@link ResultSet} contenant tous les utilisateurs, ou {@code null} en cas d’erreur
     */
    public ResultSet getLesUtilisateurs() {
        try {
            String sql = "SELECT * FROM visiteur;";
            PreparedStatement statement = connexion.prepareStatement(sql);
            // CORRECTION : On n'envoie pas la chaîne sql dans executeQuery() pour un PreparedStatement
            return statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Recherche un utilisateur par son identifiant (ID) via une recherche partielle (LIKE).
     * * @param idUtil Identifiant (ID) de l’utilisateur recherché
     * @return Un {@link ResultSet} contenant les résultats, ou {@code null} en cas d’erreur
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
     * Recherche un utilisateur par son nom via une recherche partielle (LIKE).
     * * @param nom Nom de l’utilisateur recherché
     * @return Un {@link ResultSet} contenant les résultats, ou {@code null} en cas d’erreur
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
     * * @return Un {@link ResultSet} contenant les logins, ou {@code null} en cas d’erreur
     */
    public ResultSet getLesLogins() {
        try {
            String sql = "SELECT login FROM visiteur;";
            PreparedStatement statement = connexion.prepareStatement(sql);
            // CORRECTION : executeQuery() sans paramètre pour PreparedStatement
            return statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Ajoute un nouvel utilisateur dans la table {@code visiteur}.
     * * @param id Identifiant unique de l’utilisateur
     * @param nom Nom de famille
     * @param prenom Prénom
     * @param login Login unique
     * @param mdp Mot de passe
     * @param adresse Adresse postale
     * @param cp Code postal
     * @param ville Ville
     * @param dateEmbauche Date d’embauche au format {@link java.sql.Date}
     * @return 1 si l’ajout a réussi, -1 en cas d’échec {@link SQLException}
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
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, "Erreur lors de l'ajout", ex);
        }
        return -1;
    }

    /**
     * Supprime un utilisateur de la table {@code visiteur} selon son identifiant.
     * * @param idUtil Identifiant (String) de l’utilisateur à supprimer
     * @return Le nombre de lignes supprimées (0 si aucun utilisateur trouvé ou échec)
     */
    public int supprimerUtilisateur(String idUtil) { // CORRECTION : Passage en String pour cohérence
        String sql = "DELETE FROM visiteur WHERE id = ?";
        try (PreparedStatement statement = connexion.prepareStatement(sql)) {
            statement.setString(1, idUtil);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, "Erreur lors de la suppression", ex);
        }
        return 0;
    }

    /**
     * Met à jour les informations d’un utilisateur existant dans la table {@code visiteur}.
     * * @param id Identifiant unique de l'utilisateur à modifier
     * @param nom Nouveau nom de famille
     * @param prenom Nouveau prénom
     * @param login Nouveau login
     * @param adresse Nouvelle adresse postale
     * @param cp Nouveau code postal
     * @param ville Nouvelle ville
     * @param dateEmbauche Nouvelle date d’embauche
     * @return Le nombre de lignes modifiées (0 si aucune modification ou ID introuvable)
     */
    public int modifUtilisateur(String id, String nom, String prenom,
                                 String login, String adresse, String cp, String ville, Date dateEmbauche){
        try {
            String sql = "UPDATE visiteur SET "
                    + " nom = ?, prenom = ?, login = ?, "
                    + " adresse = ?, cp = ?, ville = ?, dateEmbauche = ? "
                    + " WHERE id = ?;";

            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.setString(3, login);
            statement.setString(4, adresse);
            statement.setString(5, cp);
            statement.setString(6, ville);
            statement.setDate(7, dateEmbauche);
            statement.setString(8, id); 

            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, "Erreur lors de la modification", ex);
        }
        return 0;
    }

    /**
     * Récupère le rôle d’un utilisateur autorisé à partir de ses identifiants.
     * * @param identifiantUtil Login de l’utilisateur autorisé
     * @param mdp Mot de passe associé
     * @return Un {@link ResultSet} contenant le rôle, ou {@code null} en cas d’erreur
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
     * Génère automatiquement un login unique à partir du nom et prénom.
     * * @param nom Nom de famille
     * @param prenom Prénom
     * @return Un login unique (ex: pnom)
     * @throws IllegalArgumentException si le nom ou le prénom sont vides
     */
    public String genererLoginUnique(String nom, String prenom) {
        nom = (nom != null) ? nom.trim().toLowerCase() : "";
        prenom = (prenom != null) ? prenom.trim().toLowerCase() : "";

        if (nom.isEmpty() || prenom.isEmpty()) {
            throw new IllegalArgumentException("Nom et prénom doivent être renseignés.");
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
     * Génère un mot de passe aléatoire de 5 caractères.
     * * @return Un mot de passe court (5 caractères)
     */
    public String genererMotDePasse() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5);
    }

    /**
     * Vérifie si un login existe déjà dans la table {@code visiteur}.
     * * @param login Login à vérifier
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
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, "Erreur vérification login", e);
        }
        return false;
    }
    
    /**
     * Vérifie si un utilisateur existe déjà dans la base (doublon d'identité).
     * * @param nom           Le nom à vérifier
     * @param prenom        Le prénom à vérifier
     * @param adresse       L'adresse postale
     * @param cp            Le code postal
     * @param ville         La ville
     * @param dateEmbauche  La date d'embauche
     * @return {@code true} si un doublon exact existe, {@code false} sinon
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
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, "Erreur vérification utilisateur", ex);
        }
        return false;
    }
    
    /**
     * Vérifie si un utilisateur est présent dans la table des utilisateurs autorisés.
     * * @param login L'identifiant (login) à vérifier.
     * @return {@code true} si l'utilisateur est présent dans {@code utilisateurautorise}, {@code false} sinon.
     */
    public boolean estUtilisateurAuthentifie(String login) {
        String sql = "SELECT COUNT(*) FROM utilisateurautorise WHERE identifiantUtil = ?";
        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesBdD.class.getName()).log(Level.SEVERE, "Erreur authentification", ex);
        }
        return false;
    }
}