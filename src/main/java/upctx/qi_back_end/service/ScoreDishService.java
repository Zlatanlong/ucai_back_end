package upctx.qi_back_end.service;

import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.domain.ScoreDish;

public interface ScoreDishService {
    /**
     * 添加评分同时菜的评分也会发生变化
     *
     * @param scoreDish
     * @return
     */
    Result<ScoreDish> addScore(ScoreDish scoreDish);

    /**
     * 根据用户的id查询是否存在了评分
     */
    Boolean hasScore(Integer dishId);

    /**
     * 根据用户id评分
     */
    Result<ScoreDish> getScore(Integer dishId);
}
