package ago.app.post.base.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class PostDetailTextDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String textId;

    private String postId;

    private String text;

}
