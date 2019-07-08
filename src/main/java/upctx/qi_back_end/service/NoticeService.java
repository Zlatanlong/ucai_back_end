package upctx.qi_back_end.service;

import upctx.qi_back_end.domain.result_domain.Notice;
import upctx.qi_back_end.domain.result_domain.Result;

import java.util.List;

public interface NoticeService {
    // 获取未读信息
    Result getNotSeenNotice();

    // 获取已读信息
    Result getSeenNotice();

    // 标记信息为已读
    Result seenNotice(List<Notice> notices);
}
