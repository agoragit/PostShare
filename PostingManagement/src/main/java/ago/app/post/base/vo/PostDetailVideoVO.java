package ago.app.post.base.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class PostDetailVideoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "videoId can not null")
    private String videoId;

    @NotNull(message = "postId can not null")
    private String postId;

    @NotNull(message = "video can not null")
    private String video;

}
