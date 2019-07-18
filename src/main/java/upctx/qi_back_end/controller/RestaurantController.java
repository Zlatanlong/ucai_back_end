package upctx.qi_back_end.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upctx.qi_back_end.domain.Dish;
import upctx.qi_back_end.domain.Reply;
import upctx.qi_back_end.domain.Restaurant;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/get/all")
    public Result<List<Restaurant>> getAllRestaurant() {
        return restaurantService.getAllRestaurantName();
    }


    @GetMapping("/get/getdishbylocation")
    public Result<Dish> getClosestRestaurant(Double Latitude, Double Longitude){
        return restaurantService.getDishByLocation(Latitude,Longitude);

    }
    //测试从数据库中获取经纬度和数据库的id

    @GetMapping("/get/getlatitude")
    public Result<List<Restaurant>> getLatitude(){
        return restaurantService.getLatitude();
    }

    @GetMapping("/get/count")
    public Result<Integer> getCount() {
        return restaurantService.getCount();
    }

    @GetMapping("/get/page")
    public Result<List<Restaurant>> getRestaurantPage(Integer start, Integer size) {
        return restaurantService.getRestaurantPage(start, size);
    }

    @GetMapping("get/one")
    public Result<Restaurant> getRestaurant(Integer id) {
        return restaurantService.getRestaurant(id);
    }

    @GetMapping("/get/avgscore")
    public Result<Double> getAverageScoreInRestaurant(Integer id) {
        return restaurantService.getAverageScoreInRestaurant(id);
    }

    @GetMapping("/get/alldish")
    public Result<Dish> getDishByRestaurant(Integer id) {
        return restaurantService.getDishByRestaurant(id);
    }

    @GetMapping("/get/bestreply")
    public Result<List<Reply>> getReplyByRestaurantId(Integer id) {
        return restaurantService.getReplyByRestaurantId(id);
    }

    @PostMapping("/add")
    public Result<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addRestaurant(restaurant);
    }

    @DeleteMapping("/del")
    public Result<Restaurant> delRestaurant(Integer id) {
        return restaurantService.delRestaurant(id);
    }
}
