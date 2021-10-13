package boot.springboot.controller;

import boot.springboot.model.Role;
import boot.springboot.model.User;
import boot.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UsersController {

    @Autowired
    UserService userService;

    public UsersController (){
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUserInfo(ModelMap model, Principal principal) {
//        User user = userService.getUserByUserName(userName);
//        model.addAttribute("user", user);
        model.addAttribute("user", userService.getUserByUserName(principal.getName()));
        return "userInfo";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping (value = "/admin")
    public String getAllUsers(Model model) {
        List<User> listUser = userService.getAllUsers();
        model.addAttribute("usersList", listUser);

        return "allUsers";
    }

    @GetMapping (value = "/")
    public String getStartView() {
        return "startView";
    }

    @GetMapping (value = "/admin/addNewUser")
    public String addNewUser(Model model){
        User user = new User();
        model.addAttribute("userParams", user);

        return "userAddingData";
    }

    @GetMapping (value = "/admin/saveUser")
    public String saveUser(@ModelAttribute("userParams") User user,
                           @RequestParam(value = "rolesSet", required = false) String[] rolesSet ){
        user.setEnabled(1);
        Set<Role> roles = new HashSet<>();
        for (String s : rolesSet){
            roles.add(new Role(s));
        }
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping (value = "/admin/deleteUser")
    public String deleteUser(@RequestParam("id") int id){
        userService.removeUserById(id);
        return "redirect:/admin";
    }


    @GetMapping (value = "/admin/updateUser")
    public String updateUser(@RequestParam("id") int id, Model model){

        User user = userService.getUserById(id);
        model.addAttribute("userParams", user);

        return "userAddingData";
    }



}