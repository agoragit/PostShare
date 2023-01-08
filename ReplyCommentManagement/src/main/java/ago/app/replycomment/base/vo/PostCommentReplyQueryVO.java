package ago.app.profile.base.vo;


import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class PostCommentReplyQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String replyId;

    private String commentId;

    private String userId;

    private String reply;

    private Timestamp commentDate;

}
