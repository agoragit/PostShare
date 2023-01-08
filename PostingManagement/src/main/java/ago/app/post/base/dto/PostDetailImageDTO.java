package ago.app.post.base.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class PostDetailImageDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long imageId;

    private Long postId;

    private String image;

}
