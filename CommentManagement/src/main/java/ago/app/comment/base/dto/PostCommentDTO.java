package ago.app.comment.base.dto;


import ago.app.comment.base.ReactionStat;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;

@Data
public class PostCommentDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String commentId;

    private String postId;

    private String userId;

    private String comment;

    private String commentDate;

    @Transient
    private ReactionStat reactionStat;

}
