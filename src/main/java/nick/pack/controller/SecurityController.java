package nick.pack.controller;

import nick.pack.mail.MailSenderService;
import nick.pack.model.RoleEnum;
import nick.pack.model.User;
import nick.pack.service.RoleService;
import nick.pack.service.StatusService;
import nick.pack.service.UserService;
import nick.pack.utils.ActivationCodeHashKeyDTO;
import nick.pack.utils.PasswordEditorDTO;
import nick.pack.utils.UserProfileEditorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.HashMap;
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
    private MailSenderService mailSender;

    private final HashMap<String, User> unconfirmedUsers = new HashMap<>();


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
            setProfilePhoto(file, user);
        }
        //Кодировка полученного пароля
        String encodePassword = passwordEncoder.encode(user.getPassword());
        //Заполнение полей класса обьекта
        user.setPassword(encodePassword);
        user.setRole(roleService.setUserRole());
        user.setStatus(statusService.setActiveStatus());
        //Генерация кода активации
        String activationCode = generateCode();
        //Добавление в хэш-таблицу
        ActivationCodeHashKeyDTO hashKeyDTO = new ActivationCodeHashKeyDTO(user.getLogin(), activationCode);
        unconfirmedUsers.put(hashKeyDTO.getHashKey(), user);
        //Создание аттрибутов для ввода кода активации пользователем на отдельной страницу
        ActivationCodeHashKeyDTO userInputHashKey = new ActivationCodeHashKeyDTO(user.getLogin(), "");
        model.addAttribute("activationData", userInputHashKey);
        //Тест отправки письма на почту
        String text = "Вы регистрируйтесь на сайте <a href=\"http://localhost:8080/\">Reviews.com</a>, осталось совсем чуть-чуть, введите ваш код активации на сайте\n" + activationCode;
        mailSender.send(user.getEmail(), "Activation Account", text);

        return "codeActivationOnReg";
    }

    @PostMapping("/activate")
    public String activateAccount(@ModelAttribute("activationData") ActivationCodeHashKeyDTO activationData, Model model){
        User user = unconfirmedUsers.get(activationData.getHashKey());

        System.out.println("for /activate ===" + activationData.getCode() + " " + activationData.getUsername() + " ; result == " + activationData.getHashKey());

        if (user == null){
            model.addAttribute("codeIsNotCorrect", true);
            return "email";
        }

        userService.saveAndFlush(user);

        return "redirect:/login";
    }

    @GetMapping("/recover")
    public String recoverAccess(Model model){
        model.addAttribute("email", "");

        return "email";
    }

    @PostMapping("/mail-send")
    public String mailSend(@ModelAttribute("email") String email, Model model){
        User user = userService.findUserByEmail(email);
        boolean emailNotExist = false;
        if (user == null){
            emailNotExist = true;
            model.addAttribute("emailNotExist", emailNotExist);
            return "email";
        }
        String activationCode = generateCode();
        ActivationCodeHashKeyDTO hashKeyDTO = new ActivationCodeHashKeyDTO(user.getLogin(), activationCode);
        unconfirmedUsers.put(hashKeyDTO.getHashKey(), user);
        ActivationCodeHashKeyDTO userInputHashKey = new ActivationCodeHashKeyDTO(user.getLogin(), "");
        model.addAttribute("activationData", userInputHashKey);

        mailSender.send(email, "ActivationCode", "Ваш код активации:\n" + activationCode);
        return "codeActivationOnRecover";
    }

    @PostMapping("/recover-request")
    public String recoverRequest(@ModelAttribute("activationData") ActivationCodeHashKeyDTO hashKeyDTO, Model model){
        User user = unconfirmedUsers.get(hashKeyDTO.getHashKey());
        System.out.println("rec--------------------" + user);


        if(user == null){
            model.addAttribute("codeIsNotCorrect", true);
            return "codeActivationOnRecover";
        }
        PasswordEditorDTO passwordEditorDTO = new PasswordEditorDTO(user, "");
        model.addAttribute("password", passwordEditorDTO);
        return "editPassword";
    }

    @PostMapping("/edit-password")
    public String editPassword(@ModelAttribute("password") PasswordEditorDTO passwordEditorDTO, Model model){
        System.out.println("----------------------------------------------------------------");
        String encodedPassword = passwordEncoder.encode(passwordEditorDTO.getPassword());
        User user = passwordEditorDTO.getUser();
        user.setPassword(encodedPassword);
        System.out.println(user + "-------- old password");
        userService.saveAndFlush(user);

        return "redirect:/login";
    }

    @GetMapping("/edit-profile")
    public String editUserForm(Model model){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByLogin(login);

        UserProfileEditorDTO userProfileEditorDTO = new UserProfileEditorDTO(user.getLogin(), user.getNick(), user.getPhoto());
        model.addAttribute("userEditor", userProfileEditorDTO);

        return "editUser";
    }

    @PostMapping("/edit-user")
    public String editUser(@RequestParam("file") MultipartFile file, @ModelAttribute("user") @Valid UserProfileEditorDTO userEditor, Model model){
        System.out.println(userEditor);
        User user = userService.findUserByLogin(userEditor.getLogin());
        user.setNick(userEditor.getNick());

        if(file.getSize() != 0){
            setProfilePhoto(file, user);
        }

        userService.saveAndFlush(user);
        return "redirect:/";
    }

    public String generateCode(){
        String chars = "0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 4; i++){
            int index = random.nextInt(chars.length());
            stringBuilder.append(chars.charAt(index));
        }

        return stringBuilder.toString();
    }

    public void setProfilePhoto(MultipartFile file, User user){
        //Задать уникальное имя для файла
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        try {
            String str = getClass().getResource("/static/image/users/").toString().substring(6);
            str = str.replace(":", ":");
            str = str.replace("/", "\\");

            System.out.println(str);

            //url куда будет сохраняться картинка
            //Path path = Paths.get(str + fileName);
            Path path = Paths.get("C:\\Users\\Андрей\\IdeaProjects\\reviews\\src\\main\\resources\\static\\image\\users\\" + fileName);
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
}