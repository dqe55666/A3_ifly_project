package cn.org.rsrs.project.project_a3_ifly.controller;

import cn.org.rsrs.project.project_a3_ifly.model.User;
import cn.org.rsrs.project.project_a3_ifly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {

    @Autowired
    private UserService userService;

    // Simulate a fixed user for demonstration
    private final String MOCK_USERNAME = "test_user";

    @GetMapping("/")
    public String index() {
        User user = userService.getOrCreateUser(MOCK_USERNAME);
        
        if (user.isFirstLogin()) {
            return "redirect:/welcome";
        }
        
        if (!user.isHasFilledInfo()) {
            return "redirect:/profile-setup";
        }
        
        return "index";
    }

    @GetMapping("/welcome")
    public String welcome(Model model) {
        User user = userService.getOrCreateUser(MOCK_USERNAME);
        model.addAttribute("user", user);
        
        // Mark first login as false after showing welcome page
        if (user.isFirstLogin()) {
            user.setFirstLogin(false);
            userService.updateUser(user);
        }
        
        return "welcome";
    }

    @GetMapping("/profile-setup")
    public String profileSetup(Model model) {
        User user = userService.getOrCreateUser(MOCK_USERNAME);
        model.addAttribute("user", user);
        return "profile-setup";
    }

    @PostMapping("/profile-setup")
    public String saveProfile(@RequestParam String fullName, 
                              @RequestParam String email, 
                              @RequestParam String phoneNumber) {
        User user = userService.getOrCreateUser(MOCK_USERNAME);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setHasFilledInfo(true);
        userService.updateUser(user);
        
        return "redirect:/";
    }
    
    @GetMapping("/index")
    public String home(Model model) {
        User user = userService.getOrCreateUser(MOCK_USERNAME);
        model.addAttribute("user", user);
        return "index";
    }
}
