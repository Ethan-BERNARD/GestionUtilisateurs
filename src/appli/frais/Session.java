package appli.frais;

/**
 * Classe utilitaire pour gérer la session utilisateur.
 * Permet de stocker l'état de connexion et les informations de l'utilisateur connecté.
 * @author ebernard
 */
public class Session {
    private static boolean estAuthentifie = false;
    private static String login = "";
    private static String role = "";

    /**
     * Authentifie l'utilisateur et initialise la session.
     * @param utilisateur Le login de l'utilisateur
     * @param roleUtilisateur Le rôle associé à l'utilisateur (ex : admin, visiteur)
     */
    public static void authentifier(String utilisateur, String roleUtilisateur) {
        estAuthentifie = true;
        login = utilisateur;
        role = roleUtilisateur;
    }

    /**
     * Vérifie si un utilisateur est actuellement connecté.
     * @return true si connecté, false sinon
     */
    public static boolean estConnecte() {
        return estAuthentifie;
    }

    /**
     * Retourne le login de l'utilisateur connecté.
     * @return login
     */
    public static String getLogin() {
        return login;
    }

    /**
     * Retourne le rôle de l'utilisateur connecté.
     * @return rôle
     */
    public static String getRole() {
        return role;
    }

    /**
     * Déconnecte l'utilisateur et réinitialise la session.
     */
    public static void deconnecter() {
        estAuthentifie = false;
        login = "";
        role = "";
    }
}
