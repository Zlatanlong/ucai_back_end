package upctx.qi_back_end.controller.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.domain.ScoreDish;
import upctx.qi_back_end.service.ScoreDishService;
import upctx.qi_back_end.util.ResultUtil;

@RestController
@RequestMapping("/score/dish")
public class ScoreDishController {
    @Autowired
    private ScoreDishService scoreDishService;

    @PostMapping("/add")
    public Result<ScoreDish> addScore(@RequestBody ScoreDish scoreDish) {
        return scoreDishService.addScore(scoreDish);
    }

    @GetMapping("/hasscore")
    public Result<Integer> hasScore(Integer dishid) {
        if (scoreDishService.hasScore(dishid)) {
            return ResultUtil.success(0);
        } else {
            return ResultUtil.error(1, "noScore");
        }
    }

    @GetMapping("/getscore")
    public Result<ScoreDish> getScore(Integer dishid) {
        return scoreDishService.getScore(dishid);
    }

   // @GetMapping("/getscoresort")
    //public Result<ScoreDish> getScoreSort(String taste){return }
}
