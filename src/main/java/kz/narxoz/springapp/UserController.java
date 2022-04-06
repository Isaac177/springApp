package kz.narxoz.springapp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Getter
@Setter
@Data
public class UserController {

    @Autowired
    UserRepository repository;

    @GetMapping("/")
    public String showUserList(@RequestParam(name = "email", required = false, defaultValue = "") String email,
                @RequestParam(name = "name", required = false, defaultValue = "") String name,
                @RequestParam(name = "id", required = false, defaultValue = "") Long id,
                @RequestParam(name = "surname", required = false, defaultValue = "") String surname,
                               @RequestParam(name = "name2", required = false, defaultValue = "") String name2,
                Model model) {

            List<User> users = repository.findAllSorted();


            if (!email.isEmpty()) {
                users = repository.findByEmailContainingOrderByNameDesc(email);
            }

            if (!name.isEmpty()) {
                Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
                users = repository.findByNameStartsWith(name, firstPageWithTwoElements);
            }

           if (id != null) {
                users = repository.findByGreaterId(id);
            }
            if (id != null){
                users = repository.findAllByIdOrderById(id);
            }

            if (id != null){
                Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
                users = repository.findAllByOrderByIdDesc(id, firstPageWithTwoElements);
            }
            if(!email.isEmpty()){
                users = repository.findByEmailEndingWith(email);
            }
            if (!surname.isEmpty()){
                users = repository.findBySurnameContaining(surname);
            }
            if(!name.isEmpty()){
                users = repository.findAllByOrderByNameDescSort(name);
            }

            if(!email.isEmpty()){
                users = repository.findAllByEmailNotContaining(email);
            }
       /* if (name.equals(surname)) {
            users = repository.findAllByNameEqualsSurname(name, surname);
        }*/
            if (!email.isEmpty()){
                users = repository.findAllByEmailLike(email);
            }

            if (!name.isEmpty()){
                Pageable firstPageWithTwoElements = PageRequest.of(0, 1);
                users = repository.findDistinctByNameContaining(name, firstPageWithTwoElements);
            }
            if(!name2.isEmpty()){
                users = repository.findDistinctByName();
            }
            model.addAttribute("users", users);
        return "index";
    }

    @PostMapping("/adduser")
    public String createUser(@ModelAttribute User user){
        addUser(user);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, User user) {
        updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = repository.getById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    private void deleteById(long id) {
       repository.deleteById(id);
    }

    private void addUser(User newUser) {
        repository.save(newUser);
    }

    private void updateUser(User updateUser) {
        User oldUser = repository.getById(updateUser.getId());

        oldUser.setName(updateUser.getName());
        oldUser.setSurname(updateUser.getSurname());
        oldUser.setEmail(updateUser.getEmail());

        repository.save(oldUser);
    }
}

