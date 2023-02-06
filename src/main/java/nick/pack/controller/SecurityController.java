package nick.pack.controller;

import nick.pack.mail.MailSender;
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
    @Autowired
    private MailSender mailSender;


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
        model.addAttribute("loginExist", false);
        model.addAttribute("emailExist", false);
        //Проверка на валидность полей
        if (bindingResult.hasErrors()){
            return "registration";
        }
        //Существует ли пользователь с таким же логином
        if (userService.loginExists(user)){
            model.addAttribute("loginExist", true);
            return "registration";
        }
        //Существует ли пользователь с таким же email
        if (userService.emailExists(user)){
            model.addAttribute("emailExist", true);
            return "registration";
        }
        //Если пользователь выбрал фотографию профиля
        if(file.getSize() != 0){
            //Задать уникальное имя для файла
            String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            try {
                //url куда будет сохраняться картинка
                Path path = Path.of("/home/andrey/IdeaProjects/Reviews/src/main/resources/static/image/users" + fileName);
                //Создать файл с данным url
                Files.createFile(path);
                //Получение массива байт из полученного от клиента изображением
                byte[] bytes = file.getBytes();
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                //Запись массива байт в созданный файл
                BufferedImage bufferedImage = ImageIO.read(bais);
                ImageIO.write(bufferedImage, "jpg", new File(String.valueOf(path)));
                //Задать обьекту фото
                user.setPhoto(fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //Кодировка полученного пароля
        String encodePassword = passwordEncoder.encode(user.getPassword());
        //Заполнение полей класса обьекта
        user.setPassword(encodePassword);
        user.setRole(roleService.setUserRole());
        user.setStatus(statusService.setActiveStatus());
        user.setActivationCode(UUID.randomUUID().toString());
        //Тест отправки письма на почту
        mailSender.send(user.getEmail(), "Activation Code", user.toString());

        return "redirect:/login";
    }
}