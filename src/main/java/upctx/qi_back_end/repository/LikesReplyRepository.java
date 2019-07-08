package upctx.qi_back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upctx.qi_back_end.domain.LikesReply;

import java.util.List;
import java.util.Optional;

public interface LikesReplyRepository extends JpaRepository<LikesReply, Integer> {
    Optional<List<LikesReply>> findByUserId(Integer id);

    @Override
    @Query("select count(id) from LikesReply where ifDel <> 1")
    long count();

    LikesReply findByUserIdAndReplyId(Integer userId, Integer replyId);

    List<LikesReply> findByReplyIdAndSeen(Integer id, boolean b);
}
