package upctx.qi_back_end.controller.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import upctx.qi_back_end.domain.*;
import upctx.qi_back_end.domain.result_domain.Notice;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.domain.result_domain.UserInfo;
import upctx.qi_back_end.service.NoticeService;
import upctx.qi_back_end.service.ReplyService;
import upctx.qi_back_end.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private NoticeService noticeService;

    @GetMapping("/get")
    public Result<User> getUser() {
        return userService.getOwn();
    }

    @PutMapping("/change/password")
    public Result<User> changeUser(@RequestBody Map<String, Object> map) {
        String password = (String) map.get("password");
        return userService.changePassWord(password);
    }

    @PutMapping("/change/userinfo/{id}")
    public Result<User> changeUserInfo(@RequestBody UserInfo userInfo) {
        System.out.println("执行了修改");
        return userService.changeUserInfo(userInfo);
    }

    @PostMapping("/change/userhead")
    public Result<User> changeUserHead(MultipartFile file) {
        return userService.changeHeadImg(file);
    }

    @GetMapping("/getLikes/dish")
    public Result<List<Dish>> getLikesDish() {
        return userService.getLikesDishes();
    }

    @GetMapping("/getLikes/restaurant")
    public Result<List<Restaurant>> getLikesRestaurant() {
        return userService.getLikesRestaurantes();
    }

    @GetMapping("/getLikes/reply")
    public Result<List<Reply>> getLikesReply() {
        return userService.getLikesReplies();
    }


    @GetMapping("/getCollection/dish")
    public Result<List<Dish>> getCollectionDish() {
        return userService.getCollectionDishes();
    }

    @PostMapping("/reply")
    public Result<Reply> addReply(@RequestBody Reply reply) {
        return replyService.addReply(reply);
    }

    @PostMapping("/reply/img")
    public Result<Reply> insertImg2Reply(Integer replyId, @RequestParam("file") MultipartFile file) {
        return replyService.insertImg2Reply(replyId, file);
    }

    @GetMapping("/notices/notseen")
    public Result getNoticesNotSeen() {
        return noticeService.getNotSeenNotice();
    }

    @GetMapping("/notices/seen")
    public Result getNoticesSeen() {
        return noticeService.getSeenNotice();
    }

    @PutMapping("/notices")
    public Result seeNotices(@RequestBody List<Notice> notices) {
        return  noticeService.seenNotice(notices);
    }

    @GetMapping("/recommend")
    public Result getRecommend() {
        return userService.getRecommend();
    }
}
