package com.codecool.queststore.controller;

import com.codecool.queststore.model.User;
import com.codecool.queststore.service.ImageService;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping("/profile-page/form")
public class ImageController {

    private final ImageService imageService;
    private final UserService userService;

    @GetMapping
    public String showImageForm() {
        return "user/imageuploadform";
    }

    @PostMapping
    public String addNewImage(@RequestParam("image_file") MultipartFile file, Principal principal) {
        imageService.saveImage(file, principal.getName());
        return "redirect:/user/profile_page";
    }

    @GetMapping("/image")
    public void renderImageFromDB(HttpServletResponse response, Principal principal) throws IOException {
        User user = userService.findByUsername(principal.getName());

        if (user.getImage() != null) {
            byte[] byteArray = new byte[user.getImage().length];
            int i = 0;

            for (Byte wrappedByte : user.getImage()){
                byteArray[i++] = wrappedByte;
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
