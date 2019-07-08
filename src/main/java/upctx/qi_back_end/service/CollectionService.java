package upctx.qi_back_end.service;

import upctx.qi_back_end.domain.CollectionDish;
import upctx.qi_back_end.domain.result_domain.Result;


public interface CollectionService {
    // 添加收藏
    Result<CollectionDish> changeDishCollection(Integer userId, Integer dishId);
}
