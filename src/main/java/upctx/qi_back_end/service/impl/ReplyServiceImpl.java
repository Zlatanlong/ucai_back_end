package upctx.qi_back_end.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import upctx.qi_back_end.domain.LikesReply;
import upctx.qi_back_end.domain.Reply;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.exception.MyException;
import upctx.qi_back_end.exception.enums.ResultEnum;
import upctx.qi_back_end.repository.LikesReplyRepository;
import upctx.qi_back_end.repository.ReplyRepository;
import upctx.qi_back_end.repository.UserRepository;
import upctx.qi_back_end.service.ReplyService;
import upctx.qi_back_end.util.FileUtil;
import upctx.qi_back_end.util.ResultUtil;
import upctx.qi_back_end.util.UserUtil;

import java.util.*;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LikesReplyRepository likesReplyRepository;
    @Autowired
    private FileUtil fileUtil;

    @Override
    public Result<Reply> addReply(Reply reply) {
        Integer id = UserUtil.getUserId();
        reply.setFromUserId(id);
        return ResultUtil.success(replyRepository.save(reply));
    }

    @Override
    public Result<Reply> insertImg2Reply(Integer replyId, MultipartFile file) {
        Optional optional = replyRepository.findById(replyId);
        if (optional.isPresent()) {
            String url = fileUtil.upload(file, "reply");
            if (url == null) {
                throw new MyException(ResultEnum.REPLY_FILE_UPLOAD_ERROR);
            } else {
                Reply reply = (Reply) optional.get();
                reply.setReplyImg(url);
                return ResultUtil.success(replyRepository.save(reply));
            }
        }
        return null;
    }

    @Override
    public Result<Integer> getCountByDish(Integer dishId) {
        return ResultUtil.success(replyRepository.findCountByDishId(dishId));
    }

    @Override
    public Result<Map<String, Object>> getReplyPage(Integer dishId, Integer start, Integer size) {
        Integer uId = null;
        try {
            uId = UserUtil.getUserId();
        } catch (Exception e) {
        }
        if (start < 1) {
            throw new MyException(ResultEnum.PAGE_START_ERROR);
        }
        List<Reply> list = replyRepository.findByDishIdAndIfDelNot(dishId, 1,
                PageRequest.of(start - 1, size,
                        Sort.by(Sort.Direction.DESC, "createTime"))).getContent();
        if (list.isEmpty()) {
            throw new MyException(ResultEnum.REPLY_NULL);
        } else {
            Map<String, Object> map = new HashMap<>();
            List<String> fromName = new ArrayList<>();
            List<String> fromHeadUrl = new ArrayList<>();
            List<String> toName = new ArrayList<>();
            List<String> toContent = new ArrayList<>();
            List<String> to2Name = new ArrayList<>(); // 回复的回复叫to2(toto)
            List<Boolean> ifUserLike = new ArrayList<>();
            map.put("reply", list);
            for (Reply reply : list) {
                if (reply.getFromUserId() != null) {
                    fromName.add(userRepository.findById(reply.getFromUserId()).get().getName());
                    fromHeadUrl.add(userRepository.findById(reply.getFromUserId()).get().getUserHeadUrl());
                }
                if (uId != null) {
                    LikesReply likesReply = likesReplyRepository.findByUserIdAndReplyId(uId, reply.getId());
                    if (likesReply == null) {
                        ifUserLike.add(false);
                    } else if (likesReply.getIfDel() == 1) {
                        ifUserLike.add(false);
                    } else {
                        ifUserLike.add(true);
                    }
                }
                if (reply.getToReplyId() != null && reply.getToReplyId() != 0) {
                    Reply reply2 = replyRepository.findById(reply.getToReplyId()).get();
                    toName.add(userRepository.findById(reply2.getFromUserId()).get().getName());
                    toContent.add(reply2.getContent());
                    if (reply2.getToReplyId() != null) {
                        to2Name.add(userRepository.findById(
                                replyRepository.findById(reply2.getToReplyId()).get().getFromUserId()).get().getName());
                    } else {
                        to2Name.add(null);
                    }
                } else {
                    toContent.add(null);
                    toName.add(null);
                    to2Name.add(null);
                }
            }
            map.put("ifUserLike", ifUserLike);
            map.put("fromName", fromName);
            map.put("fromHeadUrl", fromHeadUrl);
            map.put("toContent", toContent);
            map.put("toName", toName);
            map.put("to2Name", to2Name);
            return ResultUtil.success(map);
        }
    }

    @Override
    public Result<Map<String, Object>> getReplyById(Integer id) {
        Optional optional = replyRepository.findById(id);
        if (optional.isPresent()) {
            Reply reply = (Reply) optional.get();
            Map<String, Object> map = new HashMap<>();
            map.put("reply", reply);
            String fromName = userRepository.findById(reply.getFromUserId()).get().getName();
            if (reply.getToReplyId() != null) {
                String toName = userRepository.findById(
                        replyRepository.findById(
                                reply.getToReplyId()).get().getFromUserId()).get().getName();
                map.put("toName", toName);
            } else {
                map.put("toName", null);
            }
            map.put("fromName", fromName);
            return ResultUtil.success(map);
        } else {
            throw new MyException(ResultEnum.REPLY_NULL);
        }
    }

    @Override
    public Result<Reply> delReply(Integer id) {
        Optional<Reply> optional = replyRepository.findById(id);
        if (optional.isPresent()) {
            optional.get().setIfDel(1);
            return ResultUtil.success(replyRepository.save(optional.get()));
        } else {
            throw new MyException(ResultEnum.REPLY_NULL);
        }
    }
}
