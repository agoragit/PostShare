package ago.app.post.base.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class PostDetailImageUpdateVO extends PostDetailImageVO implements Serializable {
    private static final long serialVersionUID = 1L;

}
