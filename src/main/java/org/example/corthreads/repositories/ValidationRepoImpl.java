package org.example.corthreads.repositories;

import config.ConnexionBD;
import dtos.CritereRechercheValidation;
import dtos.ValidationDetails;
import models.Validation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValidationRepoImpl implements ValidationRepo {

    @Override
    public Validation findValidation(String idVal) {
        Validation validation = null;
        String sql = "SELECT * FROM validation WHERE idVal = ?";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idVal);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    validation = new Validation(
                            rs.getString("idVal"),
                            rs.getString("idUser"),
                            rs.getString("statutVal"),
                            rs.getTimestamp("dateVal"),
                            rs.getString("commentaire")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche de la validation : " + e.getMessage());
        }
        return validation;
    }

    @Override
    public void saveValidation(Validation validation) {
        String sql = validation.getIdVal() == null ?
                "INSERT INTO validation (idUser, statutVal, dateVal, commentaire) VALUES (?, ?, ?, ?)" :
                "UPDATE validation SET idUser = ?, statutVal = ?, dateVal = ?, commentaire = ? WHERE idVal = ?";

        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, validation.getIdUser());
            stmt.setString(2, validation.getStatutVal());
            stmt.setTimestamp(3, new Timestamp(validation.getDateVal().getTime()));
            stmt.setString(4, validation.getCommentaire());

            if (validation.getIdVal() != null) {
                stmt.setString(5, validation.getIdVal());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la sauvegarde de la validation : " + e.getMessage());
            throw new RuntimeException("Échec lors de la sauvegarde de la validation", e);
        }
    }

    @Override
    public void suppValidation(String idVal) {
        String sql = "DELETE FROM validation WHERE idVal = ?";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idVal);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de la validation : " + e.getMessage());
        }
    }

    @Override
    public ValidationDetails findValidationDetails(String idVal) {
        String sql = "SELECT * FROM validation WHERE idVal = ?";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idVal);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new ValidationDetails(
                        rs.getString("idVal"),
                        rs.getString("idUser"),
                        rs.getString("statutVal"),
                        rs.getTimestamp("dateVal"),
                        rs.getString("commentaire")
                );
            }
            throw new RuntimeException("Validation non trouvée avec l'ID: " + idVal);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des détails de la validation : " + e.getMessage());
            throw new RuntimeException("Échec lors de la récupération des détails de la validation", e);
        }
    }

    @Override
    public List<Validation> findAllValidation(int page, int pageSize) {
        List<Validation> validations = new ArrayList<>();
        String sql = "SELECT * FROM validation LIMIT ? OFFSET ?";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, pageSize);
            stmt.setInt(2, page * pageSize);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Validation validation = new Validation(
                            rs.getString("idVal"),
                            rs.getString("idUser"),
                            rs.getString("statutVal"),
                            rs.getTimestamp("dateVal"),
                            rs.getString("commentaire")
                    );
                    validations.add(validation);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération paginée des validations : " + e.getMessage());
            throw new RuntimeException("Échec lors de la récupération paginée des validations", e);
        }
        return validations;
    }

    @Override
    public List<Validation> findAllValidation() {
        List<Validation> validations = new ArrayList<>();
        String sql = "SELECT * FROM validation";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Validation validation = new Validation(
                        rs.getString("idVal"),
                        rs.getString("idUser"),
                        rs.getString("statutVal"),
                        rs.getTimestamp("dateVal"),
                        rs.getString("commentaire")
                );
                validations.add(validation);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des validations : " + e.getMessage());
            throw new RuntimeException("Échec lors de la récupération des validations", e);
        }
        return validations;
    }

    @Override
    public List<Validation> rechercherValidation(CritereRechercheValidation critere) {
        List<Validation> validations = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM validation WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (critere.statutVal() != null) {
            sql.append(" AND statutVal = ?");
            params.add(critere.statutVal());
        }
        if (critere.dateVal() != null) {
            sql.append(" AND dateVal = ?");
            params.add(new Timestamp(critere.dateVal().getTime()));
        }

        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Validation validation = new Validation(
                            rs.getString("idVal"),
                            rs.getString("idUser"),
                            rs.getString("statutVal"),
                            rs.getTimestamp("dateVal"),
                            rs.getString("commentaire")
                    );
                    validations.add(validation);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des validations : " + e.getMessage());
            throw new RuntimeException("Échec lors de la recherche des validations", e);
        }
        return validations;
    }

    @Override
    public List<Validation> rechercherValidation(CritereRechercheValidation critere, int page, int pageSize) {
        List<Validation> validations = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM validation WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (critere.statutVal() != null) {
            sql.append(" AND statutVal = ?");
            params.add(critere.statutVal());
        }
        if (critere.dateVal() != null) {
            sql.append(" AND dateVal = ?");
            params.add(new Timestamp(critere.dateVal().getTime()));
        }

        sql.append(" LIMIT ? OFFSET ?");
        params.add(pageSize);
        params.add(page * pageSize);

        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Validation validation = new Validation(
                            rs.getString("idVal"),
                            rs.getString("idUser"),
                            rs.getString("statutVal"),
                            rs.getTimestamp("dateVal"),
                            rs.getString("commentaire")
                    );
                    validations.add(validation);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche paginée des validations : " + e.getMessage());
            throw new RuntimeException("Échec lors de la recherche paginée des validations", e);
        }
        return validations;
    }
}