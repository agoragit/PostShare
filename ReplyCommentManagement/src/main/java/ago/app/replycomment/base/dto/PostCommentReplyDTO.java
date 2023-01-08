package ago.app.replycomment.base.dto;


import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class PostCommentReplyDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String replyId;

    private String commentId;

    private String userId;

    private String reply;

    private Timestamp commentDate;

}
