package upctx.qi_back_end.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import upctx.qi_back_end.domain.Reply;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.service.ReplyService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @GetMapping("/get/count")
    public Result<Integer> getCountByDish(Integer dishid) {
        return replyService.getCountByDish(dishid);
    }

    @GetMapping("/get/page")
    public Result<Map<String, Object>> getReplyPage(Integer dishid, Integer start, Integer size) {
        return replyService.getReplyPage(dishid, start, size);
    }

    @DeleteMapping("/del")
    public Result<Reply> delReply(Integer id) {
        return replyService.delReply(id);
    }
}
