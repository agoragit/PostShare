package ago.app.post.base.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class PostDetailVideoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long videoId;

    private Long postId;

    private String video;

}
