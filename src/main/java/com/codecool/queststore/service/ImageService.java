package com.codecool.queststore.service;

import com.codecool.queststore.model.User;
import com.codecool.queststore.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class ImageService {

    private final static String DEFAULT_IMAGE_PATH = "/src/main/resources/static/assets/images/user_default.jpg";

    private final static String IMAGE_NAME = "user_default.jpg";
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
            log.info("Error while reading file");
        }
    }

    public void setDefaultImageIfNonePresent(User user) {
        if (user.getImage() == null) {
            //TODO
            Path path = Paths.get(DEFAULT_IMAGE_PATH);
            String name = IMAGE_NAME;
            String originalFileName = IMAGE_NAME;
            String contentType = "text/plain";
            byte[] content = null;
            //-----------------------
//            Charset utf8 = StandardCharsets.UTF_8;
//            List<String> list = Arrays.asList("Line 1", "Line 2");

//            // Write
//            try {
//                Files.write(Paths.get("app.log"), list, utf8,
//                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
//            } catch (IOException x) {
//                System.err.format("IOException: %s%n", x);
//            }
//            // Read
//            try {
//                content = Files.readAllBytes(Paths.get("app.log"));
//                System.out.println(new String(content));
//
//                // for binary
//                //System.out.println(Arrays.toString(content));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            //-----------------------------------
            try {
                content = Files.readAllBytes(path);
            } catch (final IOException e) {
                log.info("Error while reading file");
            }


            MultipartFile defaultImage = new MockMultipartFile(name,
                    originalFileName, contentType, content);

            saveImage(defaultImage, user.getUsername());

        }
    }
}
