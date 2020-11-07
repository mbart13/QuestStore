package com.codecool.queststore.repository;

import com.codecool.queststore.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {

    @Query(value = "SELECT TOP 1 * FROM ranks WHERE required_currency  <= ?#{[0]} ORDER BY required_currency desc;", nativeQuery = true)
    Rank findRankByRequiredCurrency(int total_earnings);

    @Query(value = "SELECT * FROM ranks ORDER BY required_currency  ASC;", nativeQuery = true)
    List<Rank> findAllOrderByRequiredCurrencyAsc();
}
