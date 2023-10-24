package nick.pack.controllers;

import nick.pack.mail.MailSenderService;
import nick.pack.model.User;
import nick.pack.service.RoleService;
import nick.pack.service.StatusService;
import nick.pack.service.UserService;
import nick.pack.utils.ActivationCodeDTO;
import nick.pack.utils.UserEditorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

//Controller for working with authorization and accounts
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

    @Autowired
    private HashMap<String, User> unconfirmedUsers;
    @Autowired
    private ArrayList<User> confirmedUsers;


                //Mappings
            //Get mappings
    //Authorization page
    @GetMapping ("/login")
    public String login(){
        return "login";
    }

    //Registration page
    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());

        return "registration";
    }

    //Recover Password: Page with entering email
    @GetMapping("/recover")
    public String recoverAccess(Model model){
        model.addAttribute("email", "");

        return "email";
    }

    //Account Change Page
    @GetMapping("/edit-profile")
    @PreAuthorize("hasAuthority('crud')")
    public String editUserForm(Model model){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByLogin(login);

        UserEditorDTO userProfileEditorDTO = new UserEditorDTO(user);
        model.addAttribute("userEditor", userProfileEditorDTO);

        return "editUser";
    }

    //Confirmation page about account deletion
    @GetMapping("/delete-profile")
    @PreAuthorize("hasAuthority('crud')")
    public String deleteProfile(Model model){
        String password = "";
        model.addAttribute("password", password);
        model.addAttribute("passwordInvalid", false);

        return "deleteProfile";
    }

    //Blocking users
    @GetMapping ("/block")
    @PreAuthorize("hasAuthority('blocking')")
    public String blockUser(@RequestParam("id") int id, Model model, HttpServletRequest request){
        User user = userService.findUserById(id);

        if (user.isAdmin()){
            return "redirect:/error";
        }
        if(user.isActive()){
            user.setStatus(statusService.setBannedStatus());
            userService.saveAndFlush(user);
        } else {
            user.setStatus(statusService.setActiveStatus());
            userService.saveAndFlush(user);
        }

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

            //Post Mappings
    //Registration
    @PostMapping("/signup")
    public String signUp(@RequestParam("file") MultipartFile file, @ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model){
        model.addAttribute("loginExist", false);
        model.addAttribute("emailExist", false);
        //Checking the validity of fields
        if (bindingResult.hasErrors()){
            return "registration";
        }
        //Is there a user with the same login
        if (userService.loginExists(user)){
            model.addAttribute("loginExist", true);
            return "registration";
        }
        //Is there a user with the same email
        if (userService.emailExists(user)){
            model.addAttribute("emailExist", true);
            return "registration";
        }
        //If the user has selected a profile photo
        if(file.getSize() != 0){
            setProfilePhoto(file, user);
        }
        //Encoding of the received password
        String encodePassword = passwordEncoder.encode(user.getPassword());
        //Filling in the fields of the object class
        user.setPassword(encodePassword);
        user.setRole(roleService.setUserRole());
        user.setStatus(statusService.setActiveStatus());
        //Generating an activation code
        String activationCode = generateCode();
        //set to dto
        ActivationCodeDTO activationCodeDTO = new ActivationCodeDTO(user.getLogin(), activationCode);
        unconfirmedUsers.put(activationCodeDTO.getUniqueKey(), user);
        //Creating attributes for entering the activation code by the user on a separate page
        ActivationCodeDTO userInputActivationCode = new ActivationCodeDTO(user.getLogin(), "");
        model.addAttribute("activationData", userInputActivationCode);
        //send message to the email
        String message = "Вы регистрируйтесь на сайте \"http://localhost:8080/\", осталось совсем чуть-чуть, введите ваш код активации на сайте\n" + activationCode;
        mailSender.send(user.getEmail(), "Активация учетной записи", message);

        return "codeActivationOnReg";
    }


    //Activation Account
    @PostMapping("/activate")
    public String activateAccount(@ModelAttribute("activationData") ActivationCodeDTO activationData, Model model){
        User user = unconfirmedUsers.get(activationData.getUniqueKey());

        if (user == null){
            model.addAttribute("codeIsNotCorrect", true);
            return "codeActivationOnRecover";
        }

        userService.saveAndFlush(user);

        return "redirect:/login";
    }

    //Sending an activation code to an email to restore access
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
        ActivationCodeDTO activationCodeDTO = new ActivationCodeDTO(user.getLogin(), activationCode);
        unconfirmedUsers.put(activationCodeDTO.getUniqueKey(), user);
        ActivationCodeDTO userInputActivationCode = new ActivationCodeDTO(user.getLogin(), "");
        model.addAttribute("activationData", userInputActivationCode);

        mailSender.send(email, "ActivationCode", "Ваш код активации:\n" + activationCode);

        return "codeActivationOnRecover";
    }

    //Checking the entered activation code, sends a page to change the password if the code is correct
    @PostMapping("/recover-request")
    public String recoverRequest(@ModelAttribute("activationData") ActivationCodeDTO hashKeyDTO, Model model){
        User user = unconfirmedUsers.get(hashKeyDTO.getUniqueKey());

        if(user == null){
            model.addAttribute("codeIsNotCorrect", true);
            return "codeActivationOnRecover";
        }

        UserEditorDTO userEditorDTO = new UserEditorDTO(user, "");
        model.addAttribute("userEditor", userEditorDTO);
        confirmedUsers.add(user);

        return "editPassword";
    }

    //Changing the password
    @PostMapping("/edit-password")
    public String editPassword(@ModelAttribute("password") UserEditorDTO userEditorDTO, Model model){
        User user = userEditorDTO.getUser();

        if (confirmedUsers.contains(user)){
            String encodedPassword = passwordEncoder.encode(userEditorDTO.getPassword());
            user.setPassword(encodedPassword);
            userService.saveAndFlush(user);
            confirmedUsers.remove(user);

            return "redirect:/login";
        }

        return "redirect:/error";
    }

    //Changing the account
    @PostMapping("/edit-user")
    public String editUser(@RequestParam("file") MultipartFile file, @ModelAttribute("user") UserEditorDTO userEditor, Model model){
        User user = userEditor.getUser();
        User userTest = userService.findUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        if (!(userTest.equals(user))){
            return "redirect:/error";
        }
        user.setNick(userEditor.getNick());

        if(file.getSize() != 0){
            setProfilePhoto(file, user);
        }

        userService.saveAndFlush(user);
        return "redirect:/";
    }

    //Deleting the account
    @PostMapping("/delete-user")
    @PreAuthorize("hasAuthority('crud')")
    public String deleteUser(@ModelAttribute("password") String password, Model model){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByLogin(login);

        if (passwordEncoder.matches(password, user.getPassword())){
            userService.delete(user);
        } else {
            model.addAttribute("passwordInvalid", true);
            return "deleteProfile";
        }

        return "redirect:/";
    }



                //Common methods
    //Generating a unique code
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

    //Copying the selected profile photo to the server storage
    public void setProfilePhoto(MultipartFile file, User user){
        //Set a unique file name
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        try {
            //url where the image will be saved
            File folderWithImages = new File("classpath:/src/main/resources/static/image/users\\");
            Path path = Paths.get(folderWithImages.getAbsolutePath().replaceAll("classpath:", "") + "\\" + fileName);

            //Create a file with this url
            Files.createFile(path);
            //Getting an array of bytes from an image received from the client
            byte[] bytes = file.getBytes();
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            //Writing an array of bytes to the created file
            BufferedImage bufferedImage = ImageIO.read(bais);
            ImageIO.write(bufferedImage, "jpg", new File(String.valueOf(path)));
            //Set the user a photo
            user.setPhoto(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}