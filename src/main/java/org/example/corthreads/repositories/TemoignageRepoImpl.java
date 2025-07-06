package org.example.corthreads.repositories;

import config.ConnexionBD;
import dtos.CritereRechercheTemoignage;
import dtos.TemoignageDetails;
import models.Temoignage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TemoignageRepoImpl implements TemoignageRepo {

    @Override
    public Temoignage findTemoignage(String idTemoignage) {
        Temoignage temoignage = null;
        String sql = "SELECT * FROM temoignage WHERE idTemoignage = ?";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idTemoignage);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    temoignage = new Temoignage(
                            rs.getString("idTemoignage"),
                            rs.getString("idUser"),
                            rs.getString("contenu"),
                            rs.getTimestamp("datePub")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du témoignage : " + e.getMessage());
        }
        return temoignage;
    }

    @Override
    public void saveTemoignage(Temoignage temoignage) {
        String sql = temoignage.getIdTemoignage() == null ?
                "INSERT INTO temoignage (idUser, contenu, datePub) VALUES (?, ?, ?)" :
                "UPDATE temoignage SET idUser = ?, contenu = ?, datePub = ? WHERE idTemoignage = ?";

        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, temoignage.getIdUser());
            stmt.setString(2, temoignage.getContenu());
            stmt.setTimestamp(3, new Timestamp(temoignage.getDatePub().getTime()));

            if (temoignage.getIdTemoignage() != null) {
                stmt.setString(4, temoignage.getIdTemoignage());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la sauvegarde du témoignage : " + e.getMessage());
            throw new RuntimeException("Échec lors de la sauvegarde du témoignage", e);
        }
    }

    @Override
    public void suppTemoignage(String idTemoignage) {
        String sql = "DELETE FROM temoignage WHERE idTemoignage = ?";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idTemoignage);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du témoignage : " + e.getMessage());
        }
    }

    @Override
    public TemoignageDetails findTemoignageDetails(String idTemoignage) {
        String sql = "SELECT * FROM temoignage WHERE idTemoignage = ?";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idTemoignage);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new TemoignageDetails(
                        rs.getString("idTemoignage"),
                        rs.getString("idUser"),
                        rs.getString("contenu"),
                        rs.getTimestamp("datePub")
                );
            }
            throw new RuntimeException("Témoignage non trouvé avec l'ID: " + idTemoignage);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des détails du témoignage : " + e.getMessage());
            throw new RuntimeException("Échec lors de la récupération des détails du témoignage", e);
        }
    }

    @Override
    public List<Temoignage> findAllTemoignages(int page, int pageSize) {
        List<Temoignage> temoignages = new ArrayList<>();
        String sql = "SELECT * FROM temoignage LIMIT ? OFFSET ?";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, pageSize);
            stmt.setInt(2, page * pageSize);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Temoignage temoignage = new Temoignage(
                            rs.getString("idTemoignage"),
                            rs.getString("idUser"),
                            rs.getString("contenu"),
                            rs.getTimestamp("datePub")
                    );
                    temoignages.add(temoignage);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération paginée des témoignages : " + e.getMessage());
            throw new RuntimeException("Échec lors de la récupération paginée des témoignages", e);
        }
        return temoignages;
    }

    @Override
    public List<Temoignage> findAllTemoignages() {
        List<Temoignage> temoignages = new ArrayList<>();
        String sql = "SELECT * FROM temoignage";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Temoignage temoignage = new Temoignage(
                        rs.getString("idTemoignage"),
                        rs.getString("idUser"),
                        rs.getString("contenu"),
                        rs.getTimestamp("datePub")
                );
                temoignages.add(temoignage);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des témoignages : " + e.getMessage());
            throw new RuntimeException("Échec lors de la récupération des témoignages", e);
        }
        return temoignages;
    }

    @Override
    public List<Temoignage> rechercherTemoignage(CritereRechercheTemoignage critere) {
        List<Temoignage> temoignages = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM temoignage WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (critere.datePub() != null) {
            sql.append(" AND datePub = ?");
            params.add(new Timestamp(critere.datePub().getTime()));
        }

        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Temoignage temoignage = new Temoignage(
                            rs.getString("idTemoignage"),
                            rs.getString("idUser"),
                            rs.getString("contenu"),
                            rs.getTimestamp("datePub")
                    );
                    temoignages.add(temoignage);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des témoignages : " + e.getMessage());
            throw new RuntimeException("Échec lors de la recherche des témoignages", e);
        }
        return temoignages;
    }

    @Override
    public List<Temoignage> rechercherTemoignage(CritereRechercheTemoignage critere, int page, int pageSize) {
        List<Temoignage> temoignages = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM temoignage WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (critere.datePub() != null) {
            sql.append(" AND datePub = ?");
            params.add(new Timestamp(critere.datePub().getTime()));
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
                    Temoignage temoignage = new Temoignage(
                            rs.getString("idTemoignage"),
                            rs.getString("idUser"),
                            rs.getString("contenu"),
                            rs.getTimestamp("datePub")
                    );
                    temoignages.add(temoignage);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche paginée des témoignages : " + e.getMessage());
            throw new RuntimeException("Échec lors de la recherche paginée des témoignages", e);
        }
        return temoignages;
    }
}