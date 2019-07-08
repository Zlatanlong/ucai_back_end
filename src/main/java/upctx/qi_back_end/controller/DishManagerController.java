package upctx.qi_back_end.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import upctx.qi_back_end.domain.Dish;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.service.DishService;

@RestController
@RequestMapping("/dish-manager")
public class DishManagerController {
    @Autowired
    private DishService dishService;

    @PostMapping("/add")
    public Result<Integer> addDish(Dish dish,
                                   @RequestParam("file") MultipartFile file) {
        return dishService.addDish(dish, file);
    }

    @PutMapping("/change")
    public Result<Integer> changeDish(Dish dish) {
        return dishService.changeDish(dish);
    }

    @DeleteMapping("/deldish")
    public Result<Integer> delDish(Integer id) {
        return dishService.delDish(id);
    }
}
