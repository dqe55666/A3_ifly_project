package cn.org.rsrs.project.project_a3_ifly.controller;

import cn.org.rsrs.project.project_a3_ifly.model.User;
import cn.org.rsrs.project.project_a3_ifly.service.UserService;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        if (user.isFirstLogin()) {
            return "redirect:/welcome";
        }
        
        if (!user.isHasFilledInfo()) {
            return "redirect:/profile-setup";
        }

        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/welcome")
    public String welcome(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        model.addAttribute("user", user);
        
        if (user.isFirstLogin()) {
            user.setFirstLogin(false);
            userService.updateUser(user);
            session.setAttribute("user", user);
        }
        
        return "welcome";
    }

    @GetMapping("/profile-setup")
    public String profileSetup(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        model.addAttribute("user", user);
        return "profile-setup";
    }

    @PostMapping("/profile-setup")
    public String saveProfile(@RequestParam String fullName,
                              @RequestParam String gender,
                              @RequestParam String className,
                              @RequestParam Integer age,
                              @RequestParam String email,
                              @RequestParam String phoneNumber,
                              HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        user.setFullName(fullName);
        user.setGender(gender);
        user.setClassName(className);
        user.setAge(age);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setHasFilledInfo(true);
        userService.updateUser(user);
        session.setAttribute("user", user);
        
        return "redirect:/";
    }
    
    @GetMapping("/index")
    public String home(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        model.addAttribute("user", user);
        return "index";
    }
}
