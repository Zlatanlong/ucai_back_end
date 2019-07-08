package upctx.qi_back_end.service;

import org.springframework.web.multipart.MultipartFile;
import upctx.qi_back_end.domain.*;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.domain.result_domain.UserInfo;

import java.util.List;

public interface UserService {
    // 获得全部用户
    Result<List<User>> getAll();

    // 通过id查找用户(没有密码)
    Result<User> getUser();

    Result<User> getOwn();

    // 修改用户密码
    Result<User> changePassWord(String password);

    // 添加/修改详细信息
    Result<User> changeUserInfo(UserInfo userInfo);

    // 上传头像
    Result<User> changeHeadImg(MultipartFile file);

    // 直接替换用户（超管方法）
    Result<User> changeUser(Integer id, User user);

    // 删除用户（超管方法）
    Result<Integer> delUser(Integer id);

    // 获得点赞菜记录
    Result<List<Dish>> getLikesDishes();

    // 获得点赞餐厅记录
    Result<List<Restaurant>> getLikesRestaurantes();

    // 获得点赞回复记录
    Result<List<Reply>> getLikesReplies();

    // 获得收藏的菜列表
    Result<List<Dish>> getCollectionDishes();

    // 获得推荐菜
    Result getRecommend();
}
