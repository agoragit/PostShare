package ago.app.post.base.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class PostDetailImageQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String imageId;

    private String postId;

    private String image;

}
