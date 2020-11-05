package com.codecool.queststore.repository;

import com.codecool.queststore.model.Rank;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends CrudRepository<Rank, Long> {

    //TODO

    @Query(value = "SELECT name, MAX(required_currency) FROM ranks WHERE required_currency < ?#{[0]}", nativeQuery = true)
    Rank findRankByRequiredCurrency(int total_earnings);
}
