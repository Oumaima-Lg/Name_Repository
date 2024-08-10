package gestionArkeos;

import Interfaces.*;
import java.awt.HeadlessException;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.itextpdf.text.Font.FontStyle;
import com.itextpdf.text.Font;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Chunk;
import java.awt.Desktop;
import com.itextpdf.text.DocumentException;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.activation.*;
import javax.swing.JFileChooser;
import java.io.*;

public class Operateur {

    public static Long userId = 0L;

    public static void getUsrId(long id) {
        userId = id;
    }

    public Operateur() {
    }

  
    
    public static ResultSet displayUsers() {
        try {
            Connection conn = Utilitaire.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM personne");
            return ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Une erreur de connexion à la base de données est survenue. Veuillez réessayer plus tard.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.getMessage();
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite. Veuillez réessayer plus tard.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    
    public static ResultSet displayEmployeeProfile() {
        try {
            Connection conn = Utilitaire.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM personne WHERE idPersonne = ?");
            ps.setLong(1, userId);
            return ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Une erreur de connexion à la base de données est survenue. Veuillez réessayer plus tard.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.getMessage();
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite. Veuillez réessayer plus tard.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    
    public static ResultSet displayUsrProfile() {
        try {
            Connection conn = Utilitaire.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM personne WHERE idPersonne = ?");
            ps.setLong(1, userId);
            return ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Une erreur de connexion à la base de données est survenue. Veuillez réessayer plus tard.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.getMessage();
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite. Veuillez réessayer plus tard.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    
    //Changer mot de passe de l'employe
    public static void changerMotDePasse(String cin, String mdpCr, String nvMdp, String nvMdpconfirmer) {
        try {
            Connection conn = Utilitaire.getConnection();
            // Vérifier si le mot de passe actuel est correct
            PreparedStatement ps = conn.prepareStatement("SELECT motdepasse FROM personne WHERE cin = ? AND motdepasse = ?");
            ps.setString(1, cin);
            ps.setString(2, mdpCr);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Vérifier si les deux saisies de nouveau mot de passe correspondent
                if (nvMdp.equals(nvMdpconfirmer)) {
                    // Mettre à jour le mot de passe
                    PreparedStatement psUpdate = conn.prepareStatement("UPDATE personne SET motdepasse = ? WHERE cin = ?");
                    psUpdate.setString(1, nvMdp);
                    psUpdate.setString(2, cin);
                    int rowsAffected = psUpdate.executeUpdate();
                    psUpdate.close();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Le mot de passe a été changé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Échec du changement de mot de passe. Veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Les deux saisies de nouveau mot de passe ne correspondent pas. Veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Mot de passe actuel ou CIN incorrect. Veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de la modification du mot de passe. Veuillez réessayer plus tard.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    //Changer mot de passe de l'usager
    public static void changerMotDePasseUsager(String cin, String mdpCr, String nvMdp, String nvMdpconfirmer) {
        try {
            Connection conn = Utilitaire.getConnection();
            // Vérifier si le mot de passe actuel est correct
            PreparedStatement ps = conn.prepareStatement("SELECT motdepasse FROM personne WHERE cin = ? AND motdepasse = ?");
            ps.setString(1, cin);
            ps.setString(2, mdpCr);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Vérifier si les deux saisies de nouveau mot de passe correspondent
                if (nvMdp.equals(nvMdpconfirmer)) {
                    // Mettre à jour le mot de passe
                    PreparedStatement psUpdate = conn.prepareStatement("UPDATE personne SET motdepasse = ? WHERE cin = ?");
                    psUpdate.setString(1, nvMdp);
                    psUpdate.setString(2, cin);
                    int rowsAffected = psUpdate.executeUpdate();
                    psUpdate.close();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Le mot de passe a été changé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Échec du changement de mot de passe. Veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Les deux saisies de nouveau mot de passe ne correspondent pas. Veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Mot de passe actuel ou CIN incorrect. Veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de la modification du mot de passe. Veuillez réessayer plus tard.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void afficherFicheUtilisateur() {
        File folder = new File("documents");
        if (!folder.exists()) {
            folder.mkdir();
        }
        String nom_fichier = "documents/Fiche_lecteur.pdf";
        // Déclaration des styles de police
        Font titreFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD, new BaseColor(120, 168, 252));
        Font sousTitreFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.BLACK);
        Font contenuFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.NORMAL, BaseColor.BLACK);

        Document document = new Document();

        try {
            Connection connection = Utilitaire.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM personne WHERE idPersonne = ?");
            ps.setLong(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("idPersonne");
                String idString = String.valueOf(id);
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String cin = rs.getString("cin");
                String telephone = rs.getString("téléphone");
                String email = rs.getString("email");
  

                PdfWriter.getInstance(document, new FileOutputStream(nom_fichier));
                document.open();

                // Message de bienvenue
                Paragraph bienvenue = new Paragraph("Bienvenue dans ARKEOS, " + nom + " " + prenom + " !", titreFont);
                document.add(new Paragraph(" "));
                document.add(new Paragraph(" "));
                bienvenue.setAlignment(Element.ALIGN_CENTER);
                document.add(bienvenue);

                // Informations sur l'usager
                document.add(Chunk.NEWLINE); // Ajout d'un espace
                addLine(document, "Identifiant  : ", idString, contenuFont);
                document.add(new Paragraph(" "));
                addLine(document, "Nom          : ", nom, contenuFont);
                document.add(new Paragraph(" "));
                addLine(document, "Prénom       : ", prenom, contenuFont);
                document.add(new Paragraph(" "));
                addLine(document, "CIN          : ", cin, contenuFont);
                document.add(new Paragraph(" "));
                addLine(document, "Téléphone    : ", telephone, contenuFont);
                document.add(new Paragraph(" "));
                addLine(document, "Email        : ", email, contenuFont);
                document.add(new Paragraph(" "));
                

                document.close();

                // Demande d'ouverture du fichier
                int valid = JOptionPane.showOptionDialog(
                        null,
                        new Object[]{
                            "Voulez-vous ouvrir le fichier maintenant ?",
                            "___________________________________________",
                            "Cliquez sur \"Oui\" pour ouvrir ou sur \"Non\" pour annuler",},
                        "Ouverture du fichier",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        null,
                        "OK"
                );
                if (valid == JOptionPane.OK_OPTION) {
                    File fileToOpen = new File(nom_fichier);
                    Desktop.getDesktop().open(fileToOpen);
                }
            } else {
                System.out.println("Utilisateur non trouvé.");
            }
        } catch (IOException | SQLException | DocumentException ex) {
            ex.printStackTrace();
        }
    }

    // Méthode pour ajouter une ligne dans le document PDF
    private static void addLine(Document document, String key, String value, Font font) throws DocumentException {
        Paragraph line = new Paragraph();
        line.setFont(font);
        line.add(Chunk.TABBING);
        line.add(key);
        line.add(Chunk.TABBING);
        line.add(value);
        document.add(line);
    }

 
    public static void add_Materiel(String typeMarque, String nom_marque, String type_couleur, String serie_marque,  byte[] image) {
        
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
                        
            connection = Utilitaire.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO materiel (Marque, typeCouleur, serie, type, image) VALUES (?, ?, ?, ?, ?)");

            preparedStatement.setString(1, nom_marque);
            preparedStatement.setString(2, type_couleur);
            preparedStatement.setString(3, serie_marque);
            preparedStatement.setString(4, typeMarque);
            preparedStatement.setBytes(5, image);
            
            


            if (preparedStatement.executeUpdate() > 0) {

                JOptionPane.showMessageDialog(null, "Le matériel a été ajouté avec succès.",
                        "Succes d'ajout", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Une erreur de connexion à la base de données est survenue. Veuillez réessayer plus tard.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite. Veuillez réessayer plus tard.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    
    public static void deleteMateriel(String serie, javax.swing.JComboBox<String> select) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Utilitaire.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM materiel WHERE serie = ?");
            preparedStatement.setString(1, serie);

            if (preparedStatement.executeUpdate() > 0) {
                actualiserListeMateriels(select);
                JOptionPane.showMessageDialog(null, "Le materiel a été supprimé avec succés .",
                        "Succes de suppression", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Une erreur de connexion à la base de données est survenue. Veuillez réessayer plus tard.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite. Veuillez réessayer plus tard.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Fermer les ressources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void actualiserMaterielsOffice(javax.swing.JComboBox<String> select) {

        try {
            Connection conn = Utilitaire.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT Marque FROM materiel where type = 'Office' ;");
            ResultSet rs = stmt.executeQuery();

            Map<Integer, String> documentsMap = new HashMap<>();
            int id = 0;
            while (rs.next()) {
                String Marque = rs.getString(1);
                id = id + 1;
                documentsMap.put(id, Marque);
            }
            rs.close();
            stmt.close();
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
            for (Map.Entry<Integer, String> entry : documentsMap.entrySet()) {
                String item = entry.getValue();
                comboBoxModel.addElement(item);
            }
            select.setModel(comboBoxModel);

        } catch (SQLException e) {
            System.err.println("Erreur de base de données : " + e.getMessage());
            // Afficher un message d'erreur convivial pour l'utilisateur
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite. Veuillez réessayer plus tard.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public static void actualiserMaterielsGraphique(javax.swing.JComboBox<String> select) {
        
        try {
            Connection conn = Utilitaire.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT Marque FROM materiel where type = 'Graphique' ;");
            ResultSet rs = stmt.executeQuery();

            Map<Integer, String> documentsMap = new HashMap<>();
            int id = 0;
            while (rs.next()) {
                String Marque = rs.getString(1);
                id = id + 1;
                documentsMap.put(id, Marque);
            }
            rs.close();
            stmt.close();
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
            for (Map.Entry<Integer, String> entry : documentsMap.entrySet()) {
                String item = entry.getValue();
                comboBoxModel.addElement(item);
            }
            select.setModel(comboBoxModel);

        } catch (SQLException e) {
            System.err.println("Erreur de base de données : " + e.getMessage());
            // Afficher un message d'erreur convivial pour l'utilisateur
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite. Veuillez réessayer plus tard.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    
    
    public static void actualiserListeMateriels(javax.swing.JComboBox<String> select) {
        try {
            Connection conn = Utilitaire.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM materiel;");
            ResultSet rs = stmt.executeQuery();

            Map<String, String> documentsMap = new HashMap<>();
            while (rs.next()) {
                String serie = rs.getString("serie");
                String marque = rs.getString("marque");
                documentsMap.put(serie, marque);
            }
            rs.close();
            stmt.close();

            // Utilisation de TreeMap pour trier par clé (serie)
            Map<String, String> sortedMap = new TreeMap<>(documentsMap);

            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
            for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
                String item = entry.getKey() + " - " + entry.getValue();
                comboBoxModel.addElement(item);
            }
            select.setModel(comboBoxModel);

        } catch (SQLException e) {
            System.err.println("Erreur de base de données : " + e.getMessage());
            // Afficher un message d'erreur convivial pour l'utilisateur
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite. Veuillez réessayer plus tard.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public static boolean Verifie_Marque(String nom_marque, String typeMarque) {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Utilitaire.getConnection();
            preparedStatement = connection.prepareStatement("Select Distinct Marque from Materiel where type = ? ");
            preparedStatement.setString(1, typeMarque);
            ResultSet rs = preparedStatement.executeQuery();
            
            
            while (rs.next()) {
                if(rs.getString(1).equals(nom_marque)) {
                    JOptionPane.showMessageDialog(null, "La marque que vous avez saisie existe déjà. Vous pouvez la sélectionner directement.",
                        "Marque existante", JOptionPane.INFORMATION_MESSAGE);
                     return false;
                }

            }
            rs.close();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Une erreur de connexion à la base de données est survenue. Veuillez réessayer plus tard.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite. Veuillez réessayer plus tard.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        } 
    }
    
    public static boolean Verifie_Serie(String serie_marque) {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Utilitaire.getConnection();
            preparedStatement = connection.prepareStatement("Select * from Materiel where serie = ? ");
            preparedStatement.setString(1, serie_marque);
            ResultSet rs = preparedStatement.executeQuery();
            
            
            if(rs.next()) {
                JOptionPane.showMessageDialog(null, "La série que vous avez saisie existe déjà !",
                    "Série existante", JOptionPane.INFORMATION_MESSAGE);
                 return false;
                
            }
            rs.close();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        } 
    }
    
    
    public static boolean Verifie_Serie2(String serie_marque, long id) {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Utilitaire.getConnection();
            preparedStatement = connection.prepareStatement("Select * from Materiel where serie = ? and idMateriel <> ? ");
            preparedStatement.setString(1, serie_marque);
            preparedStatement.setLong(2, id);
            ResultSet rs = preparedStatement.executeQuery();
            
            
            if(rs.next()) {
                JOptionPane.showMessageDialog(null, "La série que vous avez saisie existe déjà !",
                    "Série existante", JOptionPane.INFORMATION_MESSAGE);
                 return false;
                
            }
            rs.close();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        } 
    }
    
    public static ResultSet displayMateriels() {
        try {
            Connection conn = Utilitaire.getConnection();
            //Tester si le document est un Livre ou CD-ROM pour afficher l'auteur sinon afficher des tirets
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM materiel;");

            return ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.getMessage();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public static ResultSet displayMateriels_serie(String serieR) {
        try {
            Connection conn = Utilitaire.getConnection();
            //Tester si le document est un Livre ou CD-ROM pour afficher l'auteur sinon afficher des tirets
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM materiel where serie = ?;");
            ps.setString(1, serieR );

            return ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.getMessage();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public static ResultSet displayMateriels_marque(String marqueR) {
        try {
            Connection conn = Utilitaire.getConnection();
            //Tester si le document est un Livre ou CD-ROM pour afficher l'auteur sinon afficher des tirets
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM materiel where marque = ?;");
            ps.setString(1, marqueR);

            return ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),  "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.getMessage();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public static ResultSet displayMateriels_type(String typeR) {
        try {
            Connection conn = Utilitaire.getConnection();
            //Tester si le document est un Livre ou CD-ROM pour afficher l'auteur sinon afficher des tirets
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM materiel where type = ?;");
            ps.setString(1, typeR);

            return ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public static ResultSet displayMateriels_couleur(String couleurR) {
        try {
            Connection conn = Utilitaire.getConnection();
            //Tester si le document est un Livre ou CD-ROM pour afficher l'auteur sinon afficher des tirets
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM materiel where typeCouleur = ?;");
            ps.setString(1, couleurR);

            return ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.getMessage();
            JOptionPane.showMessageDialog(null, ex.getMessage(),  "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static long chercheIdMaterielApartirSerie(String serie) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
        connection = Utilitaire.getConnection();
        preparedStatement = connection.prepareStatement("SELECT idMateriel FROM Materiel WHERE serie = ?");
        preparedStatement.setString(1, serie);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getLong("idMateriel");
        } else {
            JOptionPane.showMessageDialog(null, "Aucun matériel trouvé pour la série : " + serie, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null,  e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace(); // Pour le débogage
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace(); // Pour le débogage
    } finally {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    return -1;
}

    public static boolean addUsager(String typePersonne, String nomUsager, String prenomUsager, String cinUsager, String emailUsager, String telephoneUsager) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Utilitaire.getConnection();
            preparedStatement = connection.prepareStatement("select * from personne where email = ?");
            preparedStatement.setString(1, emailUsager);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Cet email existe déjà. Veuillez en choisir un autre.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                return false;
            }
               
            
            preparedStatement = connection.prepareStatement("INSERT INTO personne (nom, prenom, cin, motdepasse, email, téléphone, type) VALUES (?, ?, ?, ?, ?, ?, ?)");

            // Affecter les valeurs aux paramètres de la requête
            preparedStatement.setString(1, nomUsager);
            preparedStatement.setString(2, prenomUsager);
            preparedStatement.setString(3, cinUsager);
            preparedStatement.setString(4, cinUsager);
            preparedStatement.setString(5, emailUsager);
            preparedStatement.setString(6, telephoneUsager);
            preparedStatement.setString(7, typePersonne);
            

            if (preparedStatement.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "L'usager " + nomUsager + " " + prenomUsager + " a été ajouté avec succès .",
                        "Succàs d'ajout", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Fermer les ressources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public static boolean modifierUsager(long idUsager, String nomUsager, String prenomUsager, String CIN, String email, String tele, String type, javax.swing.JComboBox<String> select,
             javax.swing.JComboBox<String> select1) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Utilitaire.getConnection();
            preparedStatement = connection.prepareStatement("select * from personne where email = ? and idPersonne <> ?");
            preparedStatement.setString(1, email);
            preparedStatement.setLong(2, idUsager);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Cet email existe déjà. Veuillez en choisir un autre.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            preparedStatement = connection.prepareStatement("UPDATE personne SET nom = ?, prenom = ?, cin = ? ,type = ?, email = ?, téléphone = ?, motdepasse = ? WHERE idPersonne = ?");

            preparedStatement.setString(1, nomUsager);
            preparedStatement.setString(2, prenomUsager);
            preparedStatement.setString(3, CIN);
            preparedStatement.setString(4, type);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, tele);
            preparedStatement.setString(7, CIN);
            preparedStatement.setLong(8, idUsager);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "L'usager a été modifié avec succès.", "Succès de modification", JOptionPane.INFORMATION_MESSAGE);
                actualiserListePerso(select); 
                actualiserListePerso(select1);
                return true;
            }
   
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public static void actualiserListeUsagers(javax.swing.JComboBox<String> select) {
        try {
        Connection conn = Utilitaire.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM personne;");
        ResultSet rs = stmt.executeQuery();

        Map<Long, String> documentsMap = new HashMap<>();
        while (rs.next()) {
            Long cote = rs.getLong(1);
            String nom = rs.getString(2);
            documentsMap.put(cote, nom);
        }
        rs.close();
        stmt.close();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        for (Map.Entry<Long, String> entry : documentsMap.entrySet()) {
            String item = entry.getKey() + " - " + entry.getValue();
            comboBoxModel.addElement(item);
        }
        select.setModel(comboBoxModel);
        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void deletePerso(long id, javax.swing.JComboBox<String> select, javax.swing.JComboBox<String> select1) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Utilitaire.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM personne WHERE idPersonne = ?");
            preparedStatement.setLong(1, id);

            if (preparedStatement.executeUpdate() > 0) {
                actualiserListePerso(select);
                actualiserListePerso(select1);
                JOptionPane.showMessageDialog(null, "La suppression a été effectuée avec succés.",
                        "Succes de suppression", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),  "Erreur", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Fermer les ressources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void actualiserListePerso(javax.swing.JComboBox<String> select) {
         
        try {
            Connection conn = Utilitaire.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM personne where idPersonne <> ?;");
            stmt.setLong(1,Operateur.userId );
            ResultSet rs = stmt.executeQuery();

            Map<Long, String> documentsMap = new HashMap<>();
            while (rs.next()) {
                Long cote = rs.getLong(1);
                String nom = rs.getString(2);
                documentsMap.put(cote, nom);
            }
            rs.close();
            stmt.close();
            
            // Convertir la map en une liste d'entrées et la trier par valeur
            List<Map.Entry<Long, String>> entryList = new ArrayList<>(documentsMap.entrySet());
            entryList.sort(Map.Entry.comparingByValue());

            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
            for (Map.Entry<Long, String> entry : entryList) {
                String item = entry.getKey() + " - " + entry.getValue();
                comboBoxModel.addElement(item);
            }
            select.setModel(comboBoxModel);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public static void importFiles(String serie) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();
            try {
                for (File file : selectedFiles) {
                    storeFileInDatabase(file, serie);
                }
                JOptionPane.showMessageDialog(null, "Fichiers importés avec succès !");
            } catch (IOException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'importation du fichier : " + ex.getMessage());
            }
        }
    }
    
    public static void storeFileInDatabase(File file, String serie) throws IOException, SQLException {
        byte[] fileData = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(fileData);
        }

        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            long idMat = chercheIdMaterielApartirSerie(serie);
            if(idMat != -1) {
                connection = Utilitaire.getConnection();
                pstmt = connection.prepareStatement("INSERT INTO File (idMateriel, filename, filedata) VALUES ( ?, ?, ?)");
                pstmt.setLong(1, idMat);
                pstmt.setString(2, file.getName());
                pstmt.setBytes(3, fileData);
                pstmt.executeUpdate();
            } else {
                JOptionPane.showMessageDialog(null, "Matériel introuvable avec la série fournie : " + serie, "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Pour le débogage
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Pour le débogage
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public static boolean modifierMat(long id, String marque, String serie, String type, String couleur) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Utilitaire.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE materiel SET marque = ?, serie= ?, typeCouleur = ? ,type = ? WHERE idMateriel = ?");
            
            preparedStatement.setString(1, marque);
            preparedStatement.setString(2, serie);
            preparedStatement.setString(3, couleur);
            preparedStatement.setString(4, type);
            preparedStatement.setLong(5, id);
            

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Le matériel a été modifié avec succès.", "Succès de modification", JOptionPane.INFORMATION_MESSAGE);  
                return true;
            }
   
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void actualiserListSerie(javax.swing.JComboBox<String> select) {
        try {
            Connection conn = Utilitaire.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT serie FROM materiel;");
            ResultSet rs = stmt.executeQuery();

            List<String> list = new ArrayList<>();
            while (rs.next()) {
                String serie = rs.getString(1);
                list.add(serie);
            }
            rs.close();
            stmt.close();

            // Trier la liste des marques par ordre alphabétique
            Collections.sort(list);

            // Créer le modèle de combo box et ajouter les éléments triés
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
            for (String serie : list) {
                comboBoxModel.addElement(serie);
            }
            select.setModel(comboBoxModel);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     
    public static void addHistorique(String filename, long idUser) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Utilitaire.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO historique (filename, idUser) VALUES (?, ?)");

            // Affecter les valeurs aux paramètres de la requête
            preparedStatement.setString(1, filename);
            preparedStatement.setLong(2, idUser);

            if (preparedStatement.executeUpdate() > 0) {
                //JOptionPane.showMessageDialog(null, "",
                //        "succes d'ajout", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Fermer les ressources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public static ResultSet displayHistorique() {
        try {
            Connection conn = Utilitaire.getConnection();
            //Tester si le document est un Livre ou CD-ROM pour afficher l'auteur sinon afficher des tirets
            PreparedStatement psD = conn.prepareStatement("Delete FROM historique WHERE date <= DATE_SUB(NOW(), INTERVAL 1 WEEK);");
            psD.executeUpdate();
            psD.close();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM historique;");
            
            return ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception ex) {
            ex.getMessage();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public static ResultSet findUserById(long id) {
        try {
            Connection conn = Utilitaire.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM personne where idPersonne = ?");
            ps.setLong(1, id);
            return ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.getMessage();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public static void addReclamation(String message, long idUser) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Utilitaire.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO reclamation (message, idUser) VALUES (?, ?)");

            // Affecter les valeurs aux paramètres de la requête
            preparedStatement.setString(1, message);
            preparedStatement.setLong(2, idUser);
            

            if (preparedStatement.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Votre réclamation a bien été envoyée",
                        "Envoi réussi", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Fermer les ressources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static ResultSet displayReclamation() {
        try {
            Connection conn = Utilitaire.getConnection();
            //Tester si le document est un Livre ou CD-ROM pour afficher l'auteur sinon afficher des tirets
            PreparedStatement psD = conn.prepareStatement("Delete FROM reclamation WHERE date <= DATE_SUB(NOW(), INTERVAL 1 WEEK);");
            psD.executeUpdate();
            psD.close();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM reclamation;");
            
            return ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception ex) {
            ex.getMessage();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    public static void deleteFile(String filename, long idMateriel) {
        
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = Utilitaire.getConnection();
            pstmt = connection.prepareStatement("delete from File where idMateriel = ? and filename = ?");
            pstmt.setLong(1, idMateriel);
            pstmt.setString(2, filename);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Le fichier a été supprimé avec succès.",
                    "Suppression réussie", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur SQL : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Pour le débogage
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Pour le débogage
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
}



    