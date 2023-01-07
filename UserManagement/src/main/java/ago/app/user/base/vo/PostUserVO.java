package ago.app.user.base.vo;

import lombok.Data;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

@Data
public class PostUserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    @NotNull(message = "userName can not null")
    private String userName;

    @NotNull(message = "userEmail can not null")
    private String userEmail;

    @NotNull(message = "telephone can not null")
    private String telephone;

    @NotNull(message = "password can not null")
    private String password;

    @NotNull(message = "displayName can not null")
    private String displayName;



}
