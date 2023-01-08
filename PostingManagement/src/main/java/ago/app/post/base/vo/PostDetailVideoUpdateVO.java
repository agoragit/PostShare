package ago.app.post.base.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class PostDetailVideoUpdateVO extends PostDetailVideoVO implements Serializable {
    private static final long serialVersionUID = 1L;

}
