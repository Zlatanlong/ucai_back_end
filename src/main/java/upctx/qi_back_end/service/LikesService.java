package upctx.qi_back_end.service;

import upctx.qi_back_end.domain.LikesDish;
import upctx.qi_back_end.domain.LikesReply;
import upctx.qi_back_end.domain.LikesRestaurant;
import upctx.qi_back_end.domain.result_domain.Result;

public interface LikesService {
    // 菜品点赞/取消
    Result<LikesDish> likesDish(Integer dishId);

    // 获得菜品点赞信息
    Result<LikesDish> getLikesDish(Integer dishId);

    // 餐厅点赞/取消
    Result<LikesRestaurant> likesRestaurant(Integer restaurantId);

    // 回复点赞/取消
    Result<LikesReply> likesReply(Integer replyId);


}
