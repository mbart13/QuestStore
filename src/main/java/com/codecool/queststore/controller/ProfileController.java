package com.codecool.queststore.controller;

import com.codecool.queststore.dto.PasswordDto;
import com.codecool.queststore.model.User;
import com.codecool.queststore.service.ImageService;
import com.codecool.queststore.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping("/{role}/profile-page")
public class ProfileController implements WebMvcConfigurer {

    private final UserService userService;
    private final ImageService imageService;

    @GetMapping("/edit")
    public String showEditForm(Model model) {
        model.addAttribute("passwordDto", new PasswordDto());
        return "profile/change_password";
    }

    @PostMapping("/edit")
    public String updatePassword(@ModelAttribute @Valid PasswordDto passwordDto, BindingResult bindingResult, Model model,
                                 RedirectAttributes attributes, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        String role = user.getRole().substring(5).toLowerCase();
        String redirect = "redirect:/" + role + "/profile-page/edit";
        model.addAttribute("password", passwordDto);

        if (bindingResult.hasErrors()) {
            return "profile/change_password";
        } else {
            userService.changeUserPassword(user, passwordDto.getNewPassword());
            userService.save(user);
            attributes.addFlashAttribute("password_updated", true);
        }

        return redirect;
    }

    @GetMapping("/image-form")
    public String showImageForm() {
        return "profile/image_upload_form";
    }

    @PostMapping("/image")
    public String addNewImage(@RequestParam("image-file") MultipartFile file, Principal principal) {
        imageService.saveImage(file, principal.getName());
        User user = userService.findByUsername(principal.getName());
        String role = user.getRole().substring(5).toLowerCase();
        return "redirect:/" + role + "/profile-page";
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

    @ModelAttribute
    private void addAttribute(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
    }
}
