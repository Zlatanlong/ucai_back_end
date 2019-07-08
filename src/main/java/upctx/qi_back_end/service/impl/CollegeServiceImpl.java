package upctx.qi_back_end.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upctx.qi_back_end.domain.College;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.repository.CollegeRepository;
import upctx.qi_back_end.service.CollegeService;
import upctx.qi_back_end.util.ResultUtil;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    private CollegeRepository collegeRepository;

    @Override
    public Result<College> addCollege(College college) {
        return ResultUtil.success(collegeRepository.save(college));
    }

    @Override
    public Result<Integer> delCollege(Integer id) {
        collegeRepository.deleteById(id);
        return ResultUtil.success(1);
    }

    @Override
    public Result<List<College>> getAll() {
        return ResultUtil.success(collegeRepository.findAll());
    }
}
