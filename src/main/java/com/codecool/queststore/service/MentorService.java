package com.codecool.queststore.service;

import com.codecool.queststore.model.Mentor;
import com.codecool.queststore.repository.MentorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MentorService {

    private final MentorRepository mentorRepository;

    public Mentor save(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    public Mentor findByUsername(String username) {
        return mentorRepository.findByUsername(username);
    }
}
