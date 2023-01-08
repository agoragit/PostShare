package ago.app.post.base.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class PostDetailImageVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "imageId can not null")
    private String imageId;

    private String postId;

    @NotNull(message = "image can not null")
    private String image;

}
