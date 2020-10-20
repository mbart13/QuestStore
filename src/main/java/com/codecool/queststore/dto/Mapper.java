package com.codecool.queststore.dto;

import com.codecool.queststore.model.Mentor;
import com.codecool.queststore.model.Student;
import com.codecool.queststore.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class Mapper {

    private final ModelMapper modelMapper;

    public Mapper() {
        modelMapper = new ModelMapper();
    }

    public Student convertToStudentEntity(UserDto userDto) {
        return modelMapper.map(userDto, Student.class);
    }

    public Mentor convertToMentorEntity(UserDto userDto) {
        return modelMapper.map(userDto, Mentor.class);
    }

    public User convertToUserEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
