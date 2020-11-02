package com.codecool.queststore.service;

import com.codecool.queststore.model.Rank;
import com.codecool.queststore.repository.RankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RankService {

    private final RankRepository rankRepository;
    private final Integer LOWEST_RANK = 0;

    public Rank getByEarnings(int total_earnings) {
        return rankRepository.findRankByTotalEarnings(total_earnings);
    }

    public Rank getLowestRank() {
        return rankRepository.findRankByTotalEarnings(LOWEST_RANK);
    }

    public Rank save(Rank rank) {
        return rankRepository.save(rank);
    }
}
