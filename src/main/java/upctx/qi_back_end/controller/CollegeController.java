package upctx.qi_back_end.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upctx.qi_back_end.domain.College;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.service.CollegeService;

import java.util.List;

@RestController
@RequestMapping("/college")
public class CollegeController {
    @Autowired
    private CollegeService collegeService;

    @PostMapping("/add")
    public Result<College> add(@RequestBody College college) {
        return collegeService.addCollege(college);
    }

    @GetMapping("/getall")
    public Result<List<College>> getAll() {
        return collegeService.getAll();
    }
}
