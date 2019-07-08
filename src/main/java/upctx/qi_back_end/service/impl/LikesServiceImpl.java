package upctx.qi_back_end.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upctx.qi_back_end.domain.*;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.exception.MyException;
import upctx.qi_back_end.exception.enums.ResultEnum;
import upctx.qi_back_end.repository.*;
import upctx.qi_back_end.service.LikesService;
import upctx.qi_back_end.util.ResultUtil;
import upctx.qi_back_end.util.UserUtil;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class LikesServiceImpl implements LikesService {
    @Autowired
    private LikesDishRepository likesDishRepository;
    @Autowired
    private LikesRestaurantRepository likesRestaurantRepository;
    @Autowired
    private LikesReplyRepository likesReplyRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ReplyRepository replyRepository;


    @Transactional
    @Override
    public Result<LikesDish> likesDish(Integer dishId) {
        Integer userId = UserUtil.getUserId();
        LikesDish likesDish = likesDishRepository.findByUserIdAndDishId(userId, dishId);
        Optional<Dish> optional = dishRepository.findById(dishId);
        if (!optional.isPresent()) {
            throw new MyException(ResultEnum.DISH_NULL);
        }
        if (likesDish == null) {
            optional.get().setLikesCount(optional.get().getLikesCount() + 1);
            dishRepository.save(optional.get());

            likesDish = new LikesDish();
            likesDish.setUserId(userId);
            likesDish.setDishId(dishId);

            return ResultUtil.success(likesDishRepository.save(likesDish));
        } else {
            if (likesDish.getIfDel() == 0) {
                optional.get().setLikesCount(optional.get().getLikesCount() - 1);
                dishRepository.save(optional.get());

                likesDish.setIfDel(1);
                return ResultUtil.success(likesDishRepository.save(likesDish));
            } else {
                optional.get().setLikesCount(optional.get().getLikesCount() + 1);
                dishRepository.save(optional.get());

                likesDish.setIfDel(0);
                return ResultUtil.success(likesDishRepository.save(likesDish));
            }
        }
    }

    @Override
    public Result<LikesDish> getLikesDish(Integer dishId) {
        Integer userId = UserUtil.getUserId();
        LikesDish likesDish = likesDishRepository.findByUserIdAndDishId(userId, dishId);
        if (likesDish != null) {
            return ResultUtil.success(likesDish);
        } else {
            throw new MyException(ResultEnum.DISH_NULL);
        }
    }

    @Transactional
    @Override
    public Result<LikesRestaurant> likesRestaurant(Integer restaurantId) {
        Integer userId = UserUtil.getUserId();
        LikesRestaurant likesRestaurant = likesRestaurantRepository.findByUserIdAndRestaurantId(userId, restaurantId);
        Optional<Restaurant> optional = restaurantRepository.findById(restaurantId);
        if (!optional.isPresent()) {
            throw new MyException(ResultEnum.RESTAURANT_NULL);
        }
        if (likesRestaurant == null) {
            optional.get().setLikesCount(optional.get().getLikesCount() + 1);
            restaurantRepository.save(optional.get());

            likesRestaurant = new LikesRestaurant();
            likesRestaurant.setUserId(userId);
            likesRestaurant.setRestaurantId(restaurantId);

            return ResultUtil.success(likesRestaurantRepository.save(likesRestaurant));
        } else {
            if (likesRestaurant.getIfDel() == 0) {
                optional.get().setLikesCount(optional.get().getLikesCount() - 1);
                restaurantRepository.save(optional.get());

                likesRestaurant.setIfDel(1);

                return ResultUtil.success(likesRestaurantRepository.save(likesRestaurant));
            } else {
                optional.get().setLikesCount(optional.get().getLikesCount() + 1);
                restaurantRepository.save(optional.get());

                likesRestaurant.setIfDel(0);
                return ResultUtil.success(likesRestaurantRepository.save(likesRestaurant));
            }
        }
    }

    @Transactional
    @Override
    public Result<LikesReply> likesReply(Integer replyId) {
        Integer userId = UserUtil.getUserId();
        LikesReply likesReply = likesReplyRepository.findByUserIdAndReplyId(userId, replyId);
        Optional<Reply> optional = replyRepository.findById(replyId);
        if (!optional.isPresent()) {
            throw new MyException(ResultEnum.REPLY_NULL);
        }
        if (likesReply == null) {

            optional.get().setLikesCount(optional.get().getLikesCount() + 1);
            replyRepository.save(optional.get());

            likesReply = new LikesReply();
            likesReply.setUserId(userId);
            likesReply.setReplyId(replyId);
            return ResultUtil.success(likesReplyRepository.save(likesReply));
        } else {
            if (likesReply.getIfDel() == 0) {
                optional.get().setLikesCount(optional.get().getLikesCount() - 1);
                replyRepository.save(optional.get());

                likesReply.setIfDel(1);
                return ResultUtil.success(likesReplyRepository.save(likesReply));
            } else {
                optional.get().setLikesCount(optional.get().getLikesCount() + 1);
                replyRepository.save(optional.get());

                likesReply.setIfDel(0);
                return ResultUtil.success(likesReplyRepository.save(likesReply));
            }
        }
    }
}
