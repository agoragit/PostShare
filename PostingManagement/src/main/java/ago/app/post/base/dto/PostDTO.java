package ago.app.post.base.dto;


import ago.app.post.base.vo.PostDetailImageVO;
import ago.app.post.base.vo.PostDetailTextVO;
import ago.app.post.base.vo.PostDetailVideoVO;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
public class PostDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String postId;

    private Timestamp postDate;

    private String postType;

    private String description;

    private String profileId;

    private String userId;

    private List<PostDetailImageVO> postImageList;
    private List<PostDetailTextVO> postTextList;
    private List<PostDetailVideoVO> postVideoList;

}
