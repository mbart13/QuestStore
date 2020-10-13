package com.codecool.queststore.service;

import com.codecool.queststore.model.User;
import com.codecool.queststore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@Service
public class ImageService {

    private final UserService userService;
    private final UserRepository userRepository;

    public void saveImage(MultipartFile file, String username) {
        User user = userService.findByUsername(username);
        try {
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            user.setImage(byteObjects);
            userRepository.save(user);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
