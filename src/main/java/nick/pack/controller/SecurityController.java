package nick.pack.controller;

import nick.pack.model.User;
import nick.pack.service.RoleService;
import nick.pack.service.StatusService;
import nick.pack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Controller
public class SecurityController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private StatusService statusService;
    @Autowired
    protected PasswordEncoder passwordEncoder;


    @GetMapping ("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam("file") MultipartFile file, @ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "registration";
        }
        if (userService.userExists(user)){
            model.addAttribute("error", true);
            return "registration";
        }
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setRole(roleService.setUserRole());
        user.setStatus(statusService.setActiveStatus());
        if(file.getSize() != 0){
            String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            try {
                Path path = Path.of("C:/Users/Андрей/IdeaProjects/reviews/src/main/resources/static/image/users/"  + fileName);
                Files.createFile(path);
                byte[] bytes = file.getBytes();
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                BufferedImage bufferedImage = ImageIO.read(bais);
                ImageIO.write(bufferedImage, "jpg", new File(String.valueOf(path)));
                user.setPhoto(fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        user.setActivationCode(UUID.randomUUID().toString());
        userService.saveAndFlush(user);
        return "redirect:/login";
    }
}