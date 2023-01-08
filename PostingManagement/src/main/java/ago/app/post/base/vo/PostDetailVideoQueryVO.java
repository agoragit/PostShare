package ago.app.post.base.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class PostDetailVideoQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String videoId;

    private String postId;

    private String video;

}
