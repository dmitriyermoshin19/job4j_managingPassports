package ru.job4j.managingPassports.service;

import ru.job4j.managingPassports.model.Passport;

import java.util.List;

public interface PassportService {
    Passport save(Passport passport);

    boolean update(int id, Passport passport);

    boolean delete(int id);

    List<Passport> findAll();

    List<Passport> findBySeries(String series);

    List<Passport> unavailable();

    List<Passport> findReplaceable();

    String checkPassportByDate();
}
