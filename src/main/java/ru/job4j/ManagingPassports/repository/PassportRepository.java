package ru.job4j.managingpassports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.job4j.managingpassports.model.Passport;

import java.util.Date;
import java.util.List;

public interface PassportRepository extends JpaRepository<Passport, Integer> {
    List<Passport> findPassportBySeries(String series);

    @Query(value = "from passports where end_date=current_date", nativeQuery = true)
    List<Passport> findPassportByEndDate();

    @Query(value = "from passports where end_date= :for3months", nativeQuery = true)
    List<Passport> findPassportsByEndDateFor3Months(@Param("for3months") Date dateFor);
}
