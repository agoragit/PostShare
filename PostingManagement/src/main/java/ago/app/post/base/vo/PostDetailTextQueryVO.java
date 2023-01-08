package ago.app.post.base.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class PostDetailTextQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String textId;

    private String postId;

    private String text;

}
