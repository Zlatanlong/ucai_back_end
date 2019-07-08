package upctx.qi_back_end.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import upctx.qi_back_end.domain.*;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.domain.result_domain.UserInfo;
import upctx.qi_back_end.exception.MyException;
import upctx.qi_back_end.exception.enums.ResultEnum;
import upctx.qi_back_end.repository.*;
import upctx.qi_back_end.service.UserService;
import upctx.qi_back_end.util.FileUtil;
import upctx.qi_back_end.util.ResultUtil;
import upctx.qi_back_end.util.UserUtil;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LikesDishRepository likesDishRepository;
    @Autowired
    private LikesReplyRepository likesReplyRepository;
    @Autowired
    private LikesRestaurantRepository likesRestaurantRepository;
    @Autowired
    private CollectionDishRepository collectionDishRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private FileUtil fileUtil;


    @Override
    public Result<List<User>> getAll() {
        return ResultUtil.success(userRepository.findAll());
    }

    @Override
    public Result<User> getUser() {
        Integer id = UserUtil.getUserId();
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setPassword(null);
            return ResultUtil.success(user.get());
        } else {
            throw new MyException(ResultEnum.USER_NULL);
        }
    }

    @Override
    public Result<User> getOwn() {
        Integer id = UserUtil.getUserId();
        return ResultUtil.success(userRepository.findById(id));
    }

    @Override
    public Result<User> changePassWord(String password) {
        Integer id = UserUtil.getUserId();
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            optionalUser.get().setPassword(password);
            return ResultUtil.success(userRepository.save(optionalUser.get()));
        } else {
            throw new MyException(ResultEnum.USER_NULL);
        }
    }

    @Override
    public Result<User> changeUserInfo(UserInfo userInfo) {
        Integer id = UserUtil.getUserId();
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setCollegeId(userInfo.getCollegeId());
            user.setEnrollmentYear(userInfo.getEnrollmentYear());
            user.setHome(userInfo.getHome());
            user.setPhonenum(userInfo.getPhonenum());
            user.setQq(userInfo.getQq());
            return ResultUtil.success(userRepository.save(user));
        } else {
            throw new MyException(ResultEnum.USER_NULL);
        }
    }

    @Override
    public Result<User> changeHeadImg(MultipartFile file) {
        Integer id = UserUtil.getUserId();
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String imgUrl = fileUtil.upload(file, user.getId().toString());
            if (imgUrl == null) {
                throw new MyException(ResultEnum.USER_UPLOADHEADIMG_NULL);
            } else {
                user.setUserHeadUrl(imgUrl);
            }
            return ResultUtil.success(userRepository.save(user));
        } else {
            throw new MyException(ResultEnum.USER_NULL);
        }
    }

    @Override
    public Result<User> changeUser(Integer id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            user.setId(id);
            return ResultUtil.success(userRepository.save(user));
        } else {
            throw new MyException(ResultEnum.USER_NULL);
        }
    }

    @Override
    public Result<Integer> delUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            optionalUser.get().setIfDel(1);
            userRepository.save(optionalUser.get());
            return ResultUtil.success(id);
        } else {
            throw new MyException(ResultEnum.USER_NULL);
        }
    }

    @Override
    public Result<List<Dish>> getLikesDishes() {
        Integer id = UserUtil.getUserId();
        Optional<List<LikesDish>> optionalLikesDishes = likesDishRepository.findByUserId(id);
        if (optionalLikesDishes.isPresent()) {
            List<Dish> list = new ArrayList<>();
            for (LikesDish likesDish : optionalLikesDishes.get()) {
                Optional<Dish> optionalDish = dishRepository.findById(likesDish.getDishId());
                if (optionalDish.isPresent()) {
                    list.add(optionalDish.get());
                }
            }
            return ResultUtil.success(list);
        } else {
            throw new MyException(ResultEnum.USER_DISHLIKE_NULL);
        }
    }

    @Override
    public Result<List<Restaurant>> getLikesRestaurantes() {
        Integer id = UserUtil.getUserId();
        Optional<List<LikesRestaurant>> optionalLikesRestaurants = likesRestaurantRepository.findByUserId(id);
        if (optionalLikesRestaurants.isPresent()) {
            List<Restaurant> list = new ArrayList<>();
            for (LikesRestaurant likesRestaurant : optionalLikesRestaurants.get()) {
                Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(likesRestaurant.getRestaurantId());
                if (optionalRestaurant.isPresent()) {
                    list.add(optionalRestaurant.get());
                }
            }
            return ResultUtil.success(list);
        } else {
            throw new MyException(ResultEnum.USER_RESTAURANTLIKE_NULL);
        }
    }

    @Override
    public Result<List<Reply>> getLikesReplies() {
        Integer id = UserUtil.getUserId();
        Optional<List<LikesReply>> optionalLikesReplies = likesReplyRepository.findByUserId(id);
        if (optionalLikesReplies.isPresent()) {
            List<Reply> list = new ArrayList<>();
            for (LikesReply likesReply : optionalLikesReplies.get()) {
                Optional<Reply> optionalReply = replyRepository.findById(likesReply.getReplyId());
                if (optionalReply.isPresent()) {
                    list.add(optionalReply.get());
                }
            }
            return ResultUtil.success(list);
        } else {
            throw new MyException(ResultEnum.USER_REPLYLIKE_NULL);
        }
    }

    @Override
    public Result<List<Dish>> getCollectionDishes() {
        Integer id = UserUtil.getUserId();
        Optional<List<CollectionDish>> optionalCollectionDishes = collectionDishRepository.findByUserId(id);
        if (optionalCollectionDishes.isPresent()) {
            List<Dish> list = new ArrayList<>();
            for (CollectionDish collectionDish : optionalCollectionDishes.get()) {
                Optional<Dish> optionalDish = dishRepository.findById(collectionDish.getDishId());
                if (optionalDish.isPresent()) {
                    list.add(optionalDish.get());
                }
            }
            return ResultUtil.success(list);
        } else {
            throw new MyException(ResultEnum.USER_DISHCOLLECTION_NULL);
        }
    }

    @Override
    public Result getRecommend() {
        // 根据用户喜欢的菜的平均口味找到最重的口味1和最轻的口味2
        List<Dish> likesDishes = getLikesDishes().getData();
        Double saltyScoreCount = 0.0;
        Double sourScoreCount = 0.0;
        Double hotScoreCount = 0.0;
        Double sweetScoreCount = 0.0;
        for (Dish likesDish : likesDishes) {
            saltyScoreCount += likesDish.getSaltyScore();
            sourScoreCount += likesDish.getSourScore();
            hotScoreCount += likesDish.getHotScore();
            sweetScoreCount += likesDish.getSweetScore();
        }
        Double saltyScore = saltyScoreCount / likesDishes.size();
        Double sourScore = sourScoreCount / likesDishes.size();
        Double hotScore = hotScoreCount / likesDishes.size();
        Double sweetScore = sweetScoreCount / likesDishes.size();
        Map<String, Double> scoreMap = new HashMap<>();
        scoreMap.put("saltyScore", saltyScore);
        scoreMap.put("sourScore", sourScore);
        scoreMap.put("hotScore", hotScore);
        scoreMap.put("sweetScore", sweetScore);

        List<Map.Entry<String, Double>> scoreList = new ArrayList<Map.Entry<String, Double>>(scoreMap.entrySet());
        //升序排序
        Collections.sort(scoreList, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        // 最小
        String min = scoreList.get(0).getKey();
        // 最大
        String max = scoreList.get(3).getKey();
        // 去所有菜里找1的前5个菜和2的前5个菜取集合
        Set<Dish> recommend = new HashSet<>();
        List<Dish> minRecommendList = dishRepository.findByIfDelNot(1,
                PageRequest.of(0, 5,
                        Sort.by(Sort.Direction.ASC, min))).getContent();
        List<Dish> maxRecommendList = dishRepository.findByIfDelNot(1,
                PageRequest.of(0, 5,
                        Sort.by(Sort.Direction.DESC, max))).getContent();
        recommend.addAll(minRecommendList);
        recommend.addAll(maxRecommendList);

        return ResultUtil.success(recommend);
    }
}
