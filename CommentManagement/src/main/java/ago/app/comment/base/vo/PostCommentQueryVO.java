package ago.app.comment.base.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class PostCommentQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String commentId;

    private String postId;

    private String userId;

    private String comment;

    private String commentDate;

}
