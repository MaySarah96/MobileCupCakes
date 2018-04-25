/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author aziz
 */
public class SessionUser {
    
    private static Integer id;
    private static String username;
    private static String email;
    private static String password;
    private static String roles;
    private static String phoneNumber;
    private static String addresse;
    private static String nom;
    private static String prenom;
    private static String imageProfil;

    public SessionUser(Integer id, String username, String email, String password, String roles, String phoneNumber, String addresse, String nom, String prenom, String imageProfil) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.phoneNumber = phoneNumber;
        this.addresse = addresse;
        this.nom =nom;
        this.prenom= prenom;
        this.imageProfil=imageProfil;
    }

    public static String getImageProfil() {
        return imageProfil;
    }

    public static void setImageProfil(String imageProfil) {
        SessionUser.imageProfil = imageProfil;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        SessionUser.prenom = prenom;
    }

    public static void setNom(String nom) {
        SessionUser.nom = nom;
    }

    public static String getNom() {
        return nom;
    }

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        SessionUser.id = id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SessionUser.username = username;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        SessionUser.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SessionUser.password = password;
    }

    public static String getRoles() {
        return roles;
    }

    public static void setRoles(String roles) {
        SessionUser.roles = roles;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber) {
        SessionUser.phoneNumber = phoneNumber;
    }

    public static String getAddresse() {
        return addresse;
    }

    public static void setAddresse(String addresse) {
        SessionUser.addresse = addresse;
    }

    @Override
    public String toString() {
        return "SessionUser{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", roles=" + roles + ", phoneNumber=" + phoneNumber + ", addresse=" + addresse + '}';
    }
    
}
