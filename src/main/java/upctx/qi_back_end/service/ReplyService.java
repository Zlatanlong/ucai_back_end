package upctx.qi_back_end.service;

import org.springframework.web.multipart.MultipartFile;
import upctx.qi_back_end.domain.Reply;
import upctx.qi_back_end.domain.result_domain.Result;

import java.util.List;
import java.util.Map;

public interface ReplyService {
    //添加一个回复
    Result<Reply> addReply(Reply reply);

    //为某个回复添加图片
    Result<Reply> insertImg2Reply(Integer replyId, MultipartFile file);

    //根据菜获得回复总数
    Result<Integer> getCountByDish(Integer dishId);

    /**
     * 按页码获取dishId部分回复信息
     *
     * @param dishId
     * @param start
     * @param size
     * @return
     */
    Result<Map<String, Object>> getReplyPage(Integer dishId, Integer start, Integer size);

    Result<Map<String, Object>> getReplyById(Integer id);

    //回复删除(超管权限)
    Result<Reply> delReply(Integer id);
}
