package ago.app.post.base.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PostQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String postId;

    private Date postDate;

    private String postType;

    private String description;

    private String profileId;

}
