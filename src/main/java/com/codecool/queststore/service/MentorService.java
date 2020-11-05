package com.codecool.queststore.service;

import com.codecool.queststore.exceptions.MentorNotFoundException;
import com.codecool.queststore.model.Mentor;
import com.codecool.queststore.repository.MentorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Mentor findById(Long id) {
        return mentorRepository.findById(id).orElseThrow(() ->
                new MentorNotFoundException(String.format("Mentor with id = %d was not found", id)));
    }

    public List<Mentor> getAll() {
        return mentorRepository.findAll();
    }
}
