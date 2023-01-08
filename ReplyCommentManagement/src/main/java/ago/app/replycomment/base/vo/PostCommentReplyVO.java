package ago.app.replycomment.base.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;


@Data
public class PostCommentReplyVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String replyId;

    @NotNull(message = "commentId can not null")
    private String commentId;

    @NotNull(message = "userId can not null")
    private String userId;

    @NotNull(message = "reply can not null")
    private String reply;

    private Timestamp commentDate;

}
