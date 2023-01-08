package ago.app.replycomment.base.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class PostCommentReplyUpdateVO extends PostCommentReplyVO implements Serializable {
    private static final long serialVersionUID = 1L;

}
