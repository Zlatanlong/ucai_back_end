package upctx.qi_back_end.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import upctx.qi_back_end.domain.Dish;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.service.DishService;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @GetMapping("/get/all")
    @ApiOperation(value = "得到所有菜", notes = "")
    public Result<List<Dish>> getAll() {
        return dishService.getAll();
    }

    @GetMapping("/get/home")
    public Result<List<Dish>> getHome() {
        return dishService.getDishHome();
    }

    @GetMapping("/get/count")
    public Result<Integer> getCount() {
        return dishService.getCount();
    }

    /**
     * 倒序分页查询 0是第一页
     *
     * @param start
     * @param size
     * @return
     */
    @GetMapping("/get/bypage")
    public Result<List<Dish>> getDishPage(@RequestParam Integer start,
                                          @RequestParam Integer size,
                                          @RequestParam String rank) {
        if (rank == null || rank == "") {
            return dishService.getDishPage(start, size);
        } else {
            return dishService.getDishPage(start, size, rank);
        }
    }

    @GetMapping("/get/searchcount")
    public Result<Integer> getCount(String sth) {
        return dishService.getCountSearch(sth);
    }

    @GetMapping("/get/search")
    public Result<List<Dish>> getDishPage(@RequestParam String sth,
                                          @RequestParam Integer start,
                                          @RequestParam Integer size) {
        return dishService.getDishPageSearch(sth, start, size);
    }

    @GetMapping("/get/dish")
    public Result<Dish> getDish(@RequestParam Integer id) {
        return dishService.getDish(id);
    }


}
