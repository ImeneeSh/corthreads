package org.example.corthreads.repositories;

import config.ConnexionBD;
import dtos.CritereRechercheMatchs;
import dtos.MatchsDetails;
import models.Matchs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatchsRepoImpl implements MatchsRepo {

    @Override
    public Matchs findMatchs(String idMatch) {
        Matchs matchs = null;
        String sql = "SELECT * FROM matchs WHERE idMatch = ?";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idMatch);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    matchs = new Matchs(
                            rs.getString("idMatch"),
                            rs.getString("idVal"),
                            rs.getString("idDons"),
                            rs.getString("idDemande"),
                            rs.getString("statusMatch")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du match : " + e.getMessage());
        }
        return matchs;
    }

    @Override
    public void saveMatchs(Matchs matchs) {
        String sql = matchs.getIdMatch() == null ?
                "INSERT INTO matchs (idVal, idDons, idDemande, statusMatch) VALUES (?, ?, ?, ?)" :
                "UPDATE matchs SET idVal = ?, idDons = ?, idDemande = ?, statusMatch = ? WHERE idMatch = ?";

        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, matchs.getIdVal());
            stmt.setString(2, matchs.getIdDons());
            stmt.setString(3, matchs.getIdDemande());
            stmt.setString(4, matchs.getStatusMatch());

            if (matchs.getIdMatch() != null) {
                stmt.setString(5, matchs.getIdMatch());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la sauvegarde du match : " + e.getMessage());
            throw new RuntimeException("Échec lors de la sauvegarde du match", e);
        }
    }

    @Override
    public void suppMatchs(String idMatch) {
        String sql = "DELETE FROM matchs WHERE idMatch = ?";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idMatch);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du match : " + e.getMessage());
        }
    }

    @Override
    public MatchsDetails findMatchsDetails(String idMatch) {
        String sql = "SELECT * FROM matchs WHERE idMatch = ?";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idMatch);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new MatchsDetails(
                        rs.getString("idMatch"),
                        rs.getString("idVal"),
                        rs.getString("idDons"),
                        rs.getString("idDemande"),
                        rs.getString("statusMatch")
                );
            }
            throw new RuntimeException("Match non trouvé avec l'ID: " + idMatch);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des détails du match : " + e.getMessage());
            throw new RuntimeException("Échec lors de la récupération des détails du match", e);
        }
    }

    @Override
    public List<Matchs> findAllMatchs(int page, int pageSize) {
        List<Matchs> matchsList = new ArrayList<>();
        String sql = "SELECT * FROM matchs LIMIT ? OFFSET ?";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, pageSize);
            stmt.setInt(2, page * pageSize);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Matchs matchs = new Matchs(
                            rs.getString("idMatch"),
                            rs.getString("idVal"),
                            rs.getString("idDons"),
                            rs.getString("idDemande"),
                            rs.getString("statusMatch")
                    );
                    matchsList.add(matchs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération paginée des matchs : " + e.getMessage());
            throw new RuntimeException("Échec lors de la récupération paginée des matchs", e);
        }
        return matchsList;
    }

    @Override
    public List<Matchs> findAllMatchs() {
        List<Matchs> matchsList = new ArrayList<>();
        String sql = "SELECT * FROM matchs";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Matchs matchs = new Matchs(
                        rs.getString("idMatch"),
                        rs.getString("idVal"),
                        rs.getString("idDons"),
                        rs.getString("idDemande"),
                        rs.getString("statusMatch")
                );
                matchsList.add(matchs);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des matchs : " + e.getMessage());
            throw new RuntimeException("Échec lors de la récupération des matchs", e);
        }
        return matchsList;
    }

    @Override
    public List<Matchs> rechercherMatchs(CritereRechercheMatchs critere) {
        List<Matchs> matchsList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM matchs WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (critere.statusMatch() != null) {
            sql.append(" AND statusMatch = ?");
            params.add(critere.statusMatch());
        }

        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Matchs matchs = new Matchs(
                            rs.getString("idMatch"),
                            rs.getString("idVal"),
                            rs.getString("idDons"),
                            rs.getString("idDemande"),
                            rs.getString("statusMatch")
                    );
                    matchsList.add(matchs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche de matchs : " + e.getMessage());
            throw new RuntimeException("Échec lors de la recherche de matchs", e);
        }
        return matchsList;
    }

    @Override
    public List<Matchs> rechercherMatchs(CritereRechercheMatchs critere, int page, int pageSize) {
        List<Matchs> matchsList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM matchs WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (critere.statusMatch() != null) {
            sql.append(" AND statusMatch = ?");
            params.add(critere.statusMatch());
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
                    Matchs matchs = new Matchs(
                            rs.getString("idMatch"),
                            rs.getString("idVal"),
                            rs.getString("idDons"),
                            rs.getString("idDemande"),
                            rs.getString("statusMatch")
                    );
                    matchsList.add(matchs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche paginée de matchs : " + e.getMessage());
            throw new RuntimeException("Échec lors de la recherche paginée de matchs", e);
        }
        return matchsList;
    }
}