package upctx.qi_back_end.service;

import upctx.qi_back_end.domain.College;
import upctx.qi_back_end.domain.result_domain.Result;

import java.util.List;

public interface CollegeService {
    // 添加一个学校
    Result<College> addCollege(College college);

    // 删除一个学校
    Result<Integer> delCollege(Integer id);

    //获的全部学校
    Result<List<College>> getAll();
}
