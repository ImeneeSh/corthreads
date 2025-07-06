package org.example.corthreads.repositories;

import dtos.MatcherDetails ;
import models.Matcher;

import java.util.List;

public interface MatcherRepo {

    Matcher findMatcher (String idUser , String idDons) ;
    void saveMatcher(Matcher matcher);
    void suppMatcher(String idUser , String idDons) ;

    MatcherDetails findMatcherDetails(String idUser , String idDons) ;

    List<Matcher> findAllMatchers(int page, int pageSize);
    List<Matcher> findAllMatchers() ;
}
