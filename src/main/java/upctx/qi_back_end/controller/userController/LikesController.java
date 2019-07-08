package upctx.qi_back_end.controller.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upctx.qi_back_end.domain.LikesDish;
import upctx.qi_back_end.domain.LikesReply;
import upctx.qi_back_end.domain.LikesRestaurant;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.service.LikesService;

import java.util.Map;

@RestController
@RequestMapping("/likes")
public class LikesController {
    @Autowired
    private LikesService likesService;

    @PostMapping("/dish")
    public Result<LikesDish> likesDish(@RequestBody Map<String, Object> map) {
        Integer dishId = (Integer) map.get("dishid");
        return likesService.likesDish(dishId);
    }

    @GetMapping("/get/dish")
    public Result<LikesDish> getlikesDish(Integer dishid) {
        return likesService.getLikesDish(dishid);
    }

    @PostMapping("/restaurant")
    public Result<LikesRestaurant> likesRestaurant(@RequestBody Map<String, Object> map) {
        Integer restaurantId = (Integer) map.get("restaurantid");
        return likesService.likesRestaurant(restaurantId);
    }

    @PostMapping("/reply")
    public Result<LikesReply> likesReply(@RequestBody Map<String, Object> map) {
        Integer replyId = (Integer) map.get("replyid");
        return likesService.likesReply(replyId);
    }
}
