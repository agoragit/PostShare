package ago.app.post.base.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class PostDetailTextVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "textId can not null")
    private String textId;

    @NotNull(message = "postId can not null")
    private String postId;

    private String text;

}
