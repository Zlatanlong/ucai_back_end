package upctx.qi_back_end.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upctx.qi_back_end.domain.LikesReply;
import upctx.qi_back_end.domain.Reply;
import upctx.qi_back_end.domain.result_domain.Notice;
import upctx.qi_back_end.domain.result_domain.Result;
import upctx.qi_back_end.exception.MyException;
import upctx.qi_back_end.exception.enums.ResultEnum;
import upctx.qi_back_end.repository.LikesReplyRepository;
import upctx.qi_back_end.repository.ReplyRepository;
import upctx.qi_back_end.repository.UserRepository;
import upctx.qi_back_end.service.NoticeService;
import upctx.qi_back_end.util.ResultUtil;
import upctx.qi_back_end.util.UserUtil;

import java.util.*;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private LikesReplyRepository likesReplyRepository;

    public Result getNotice(Boolean b) {
        Integer id = UserUtil.getUserId();
        List<Notice> notices = new ArrayList<>();
        List<Reply> myReplies = replyRepository.findByFromUserId(id);
        for (Reply myReply : myReplies) {
            // 我的回复中被别人回复的replies
            List<Reply> replies = replyRepository.findByToReplyIdAndSeen(myReply.getId(), b);
            for (Reply reply : replies) {
                Notice notice = new Notice();
                notice.setType(1);
                notice.setTypeId(reply.getId());
                notice.setTime(reply.getCreateTime());
                notice.setContent(reply.getContent());
                notice.setFromUserId(reply.getFromUserId());
                notice.setToReply(myReply.getContent());
                notice.setFromUserName(userRepository.findNameById(reply.getFromUserId()));
                notice.setDishId(myReply.getDishId());
                notices.add(notice);
            }
            // 我的回复中被别人点赞的
            List<LikesReply> likesReplies = likesReplyRepository.findByReplyIdAndSeen(myReply.getId(), b);
            for (LikesReply likesReply : likesReplies) {
                Notice notice = new Notice();
                notice.setType(0);
                notice.setTypeId(likesReply.getId());
                notice.setTime(likesReply.getCreateTime());
                notice.setContent(null);
                notice.setFromUserId(likesReply.getUserId());
                notice.setToReply(myReply.getContent());
                notice.setFromUserName(userRepository.findNameById(likesReply.getUserId()));
                notice.setDishId(myReply.getDishId());
                notices.add(notice);
            }
        }
        if (notices.isEmpty()) {
            throw new MyException(ResultEnum.USER_NOTSEENNOTICE_NULL);
        } else {
            Collections.sort(notices, new Comparator<Notice>() {
                @Override
                public int compare(Notice o1, Notice o2) {
                    return o2.getTime().compareTo(o1.getTime());
                }
            });
            return ResultUtil.success(notices);
        }
    }

    @Override
    public Result getNotSeenNotice() {
        return this.getNotice(false);
    }

    @Override
    public Result getSeenNotice() {
        return this.getNotice(true);
    }

    @Override
    public Result seenNotice(List<Notice> notices) {
        for (Notice notice : notices) {
            if (notice.getType() == 0) {
                LikesReply likesReply = likesReplyRepository.findById(notice.getTypeId()).get();
                likesReply.setSeen(true);
                likesReplyRepository.save(likesReply);
            } else if (notice.getType() == 1) {
                Reply reply = replyRepository.findById(notice.getTypeId()).get();
                reply.setSeen(true);
                replyRepository.save(reply);
            } else {
                throw new MyException(ResultEnum.USER_NOTICEKIND_ERROR);
            }
        }
        return ResultUtil.success();
    }
}
