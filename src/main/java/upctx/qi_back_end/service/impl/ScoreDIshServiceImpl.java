package upctx.qi_back_end.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upctx.qi_back_end.domain.Dish;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.domain.ScoreDish;
import upctx.qi_back_end.exception.MyException;
import upctx.qi_back_end.exception.enums.ResultEnum;
import upctx.qi_back_end.repository.DishRepository;
import upctx.qi_back_end.repository.ScoreDishRepository;
import upctx.qi_back_end.service.ScoreDishService;
import upctx.qi_back_end.util.ResultUtil;
import upctx.qi_back_end.util.UserUtil;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ScoreDIshServiceImpl implements ScoreDishService {
    @Autowired
    private ScoreDishRepository scoreDishRepository;
    @Autowired
    private DishRepository dishRepository;

    @Override
    @Transactional
    public Result<ScoreDish> addScore(ScoreDish scoreDish) {
        System.out.println(scoreDish);
        Integer userId = UserUtil.getUserId();
        scoreDish.setUserId(userId);
        if (hasScore(scoreDish.getDishId())) {
            throw new MyException(ResultEnum.SCORE_HAS_ERROR);
        } else {
            ScoreDish addScore = scoreDishRepository.save(scoreDish);
            if (addScore == null) {
                throw new MyException(ResultEnum.SCORE_ERROR);
            } else {
                Optional<Dish> optionalDish = dishRepository.findById(addScore.getDishId());
                if (optionalDish.isPresent()) {
                    Dish dish = optionalDish.get();
                    Integer times = dish.getEvaluationTimes();
                    dish.setTotalScore((dish.getTotalScore() * times + addScore.getTotalScore()) / (times + 1));
                    dish.setSaltyScore((dish.getSaltyScore() * times + addScore.getSaltyScore()) / (times + 1));
                    dish.setSourScore((dish.getSourScore() * times + addScore.getSourScore()) / (times + 1));
                    dish.setHotScore((dish.getHotScore() * times + addScore.getHotScore()) / (times + 1));
                    dish.setSweetScore((dish.getSweetScore() * times + addScore.getSweetScore()) / (times + 1));
                    dish.setEvaluationTimes(times + 1);

                    Dish saveDish = dishRepository.save(dish);
                    if (saveDish == null) {
                        throw new MyException(ResultEnum.SCORE_ERROR_DISH);
                    } else {
                        return ResultUtil.success(addScore);
                    }
                } else {
                    throw new MyException(ResultEnum.DISH_NULL);
                }
            }
        }
    }

    @Override
    public Boolean hasScore(Integer dishId) {
        Integer userId = UserUtil.getUserId();
        return scoreDishRepository.findByUserIdAndDishId(userId, dishId).isPresent();
    }

    @Override
    public Result<ScoreDish> getScore(Integer dishId) {
        if (hasScore(dishId)) {
            Integer userId = UserUtil.getUserId();
            return ResultUtil.success(scoreDishRepository.findByUserIdAndDishId(userId, dishId).get());
        } else {
            throw new MyException(ResultEnum.SCORE_NULL);
        }
    }
}
