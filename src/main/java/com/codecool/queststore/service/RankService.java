package com.codecool.queststore.service;

import com.codecool.queststore.exceptions.CourseNotFoundException;
import com.codecool.queststore.model.Course;
import com.codecool.queststore.model.Rank;
import com.codecool.queststore.repository.RankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RankService {

    private final RankRepository rankRepository;
    private final Integer LOWEST_RANK = 0;


    public Rank getByEarnings(int total_earnings) {
        return rankRepository.findRankByRequiredCurrency(total_earnings);
    }

    public Rank getLowestRank() {
        return rankRepository.findRankByRequiredCurrency(LOWEST_RANK);
    }

    public Rank save(Rank rank) {
        return rankRepository.save(rank);
    }

    public List<Rank> showAllRanks() {
        return rankRepository.findAll();
    }

    public Rank findById(Long id) {
        return rankRepository.findById(id).orElseThrow(() ->
                new CourseNotFoundException(String.format("Rank with id = %d was not found", id)));
    }

    public void deleteRank(Rank rank) {
        rankRepository.delete(rank);
    }
}
