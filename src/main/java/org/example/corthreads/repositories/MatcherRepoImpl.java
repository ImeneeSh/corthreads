package org.example.corthreads.repositories;

import config.ConnexionBD;
import dtos.MatcherDetails;
import models.Matcher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatcherRepoImpl implements MatcherRepo {

    @Override
    public Matcher findMatcher(String idDemande, String idDons) {
        Matcher matcher = null;
        String sql = "SELECT * FROM matcher WHERE idDemande = ? AND idDons = ?";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idDemande);
            stmt.setString(2, idDons);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    matcher = new Matcher(
                            rs.getString("idDons"),
                            rs.getString("idDemande")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du matcher : " + e.getMessage());
        }
        return matcher;
    }

    @Override
    public void saveMatcher(Matcher matcher) {
        String sql;
        boolean isInsert = (matcher.getIdDons() == null || matcher.getIdDemande() == null);

        if (isInsert) {
            sql = "INSERT INTO matcher (idDons, idDemande) VALUES (?, ?)";
        } else {
            sql = "UPDATE matcher SET idDons = ?, idDemande = ? WHERE idDons = ? AND idDemande = ?";
        }

        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, matcher.getIdDons());
            stmt.setString(2, matcher.getIdDemande());
            if (!isInsert) {
                stmt.setString(3, matcher.getIdDons());
                stmt.setString(4, matcher.getIdDemande());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la sauvegarde du matcher : " + e.getMessage());
            throw new RuntimeException("Échec de la sauvegarde du matcher", e);
        }
    }

    @Override
    public void suppMatcher(String idDemande, String idDons) {
        String sql = "DELETE FROM matcher WHERE idDemande = ? AND idDons = ?";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idDemande);
            stmt.setString(2, idDons);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du matcher : " + e.getMessage());
        }
    }

    @Override
    public MatcherDetails findMatcherDetails(String idDemande, String idDons) {
        String sql = "SELECT * FROM matcher WHERE idDemande = ? AND idDons = ?";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, idDemande);
            stmt.setString(2, idDons);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MatcherDetails(
                            rs.getString("idDons"),
                            rs.getString("idDemande")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des détails du matcher : " + e.getMessage());
            throw new RuntimeException("Échec de la récupération des détails du matcher", e);
        }
        return null;
    }

    @Override
    public List<Matcher> findAllMatchers(int page, int pageSize) {
        List<Matcher> matchers = new ArrayList<>();
        String sql = "SELECT * FROM matcher LIMIT ? OFFSET ?";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, pageSize);
            stmt.setInt(2, page * pageSize);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Matcher matcher = new Matcher(
                            rs.getString("idDons"),
                            rs.getString("idDemande")
                    );
                    matchers.add(matcher);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération paginée des matchers : " + e.getMessage());
            throw new RuntimeException("Échec de la récupération paginée des matchers", e);
        }
        return matchers;
    }

    @Override
    public List<Matcher> findAllMatchers() {
        List<Matcher> matchers = new ArrayList<>();
        String sql = "SELECT * FROM matcher";
        try (Connection con = ConnexionBD.getConnexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Matcher matcher = new Matcher(
                        rs.getString("idDons"),
                        rs.getString("idDemande")
                );
                matchers.add(matcher);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de tous les matchers : " + e.getMessage());
            throw new RuntimeException("Échec de la récupération de tous les matchers", e);
        }
        return matchers;
    }
}