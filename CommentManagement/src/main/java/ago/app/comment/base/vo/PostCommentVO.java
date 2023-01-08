package ago.app.comment.base.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class PostCommentVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String commentId;

    @NotNull(message = "postId can not null")
    private String postId;

    @NotNull(message = "userId can not null")
    private String userId;

    @NotNull(message = "comment can not null")
    private String comment;

    private String commentDate;

}
