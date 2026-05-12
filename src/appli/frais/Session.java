package appli.frais;

/**
 * Classe utilitaire pour gérer la session utilisateur.
 * <p>
 * Cette classe permet de stocker l'état de connexion et les informations 
 * de l'utilisateur connecté (login et rôle) de manière globale dans l'application.
 * </p>
 * * @author ebernard
 * @version 1.0
 */
public class Session {
    
    /** État de la connexion (true si l'utilisateur est authentifié) */
    private static boolean estAuthentifie = false;
    
    /** Login de l'utilisateur en session */
    private static String login = "";
    
    /** Rôle de l'utilisateur en session (ex: "admin", "visiteur") */
    private static String role = "";

    /**
     * Authentifie l'utilisateur et initialise les données de session.
     * * @param utilisateur     Le login de l'utilisateur à mémoriser.
     * @param roleUtilisateur Le rôle associé à l'utilisateur (ex : "admin", "visiteur").
     */
    public static void authentifier(String utilisateur, String roleUtilisateur) {
        estAuthentifie = true;
        login = utilisateur;
        role = roleUtilisateur;
    }

    /**
     * Vérifie si un utilisateur est actuellement connecté.
     * * @return {@code true} si l'utilisateur est connecté, {@code false} sinon.
     */
    public static boolean estConnecte() {
        return estAuthentifie;
    }

    /**
     * Retourne le login de l'utilisateur actuellement connecté.
     * * @return Une {@link String} représentant le login, ou une chaîne vide si non connecté.
     */
    public static String getLogin() {
        return login;
    }

    /**
     * Retourne le rôle de l'utilisateur actuellement connecté.
     * * @return Le rôle de l'utilisateur (ex : "admin", "visiteur").
     */
    public static String getRole() {
        return role;
    }

    /**
     * Déconnecte l'utilisateur et réinitialise toutes les informations de session.
     * <p>
     * Après appel de cette méthode, {@link #estConnecte()} retournera {@code false}.
     * </p>
     */
    public static void deconnecter() {
        estAuthentifie = false;
        login = "";
        role = "";
    }
}