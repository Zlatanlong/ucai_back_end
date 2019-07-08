package upctx.qi_back_end.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upctx.qi_back_end.domain.User;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user-manager")
public class UserManagerController {
    @Autowired
    private UserService userService;

    @GetMapping("/get/all")
    public Result<List<User>> getUser() {
        return userService.getAll();
    }

    @PutMapping("/change/user/{id}")
    public Result<User> changeUser(@PathVariable Integer id,
                                   @RequestBody User user) {
        return userService.changeUser(id, user);
    }

    @DeleteMapping("/deluser")
    public Result<Integer> delUser(Integer id) {
        return userService.delUser(id);
    }

}
