package ago.app.post.base.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Data
public class PostVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String postId;

    @NotNull(message = "postDate can not null")
    private Timestamp postDate;

    @NotNull(message = "postType can not null")
    private String postType;

    @NotNull(message = "postType can not null")
    private String description;

    @NotNull(message = "profileId can not null")
    private String profileId;

    @NotNull(message = "UserId can not null")
    private String userId;

    private List<PostDetailImageVO> postImageList;
    private List<PostDetailTextVO> postTextList;
    private List<PostDetailVideoVO> postVideoList;

}
