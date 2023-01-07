package ago.app.user.base.dto;


import java.io.Serializable;

@lombok.Data
public class PostUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;

    private String userName;

    private String userEmail;

    private String telephone;

    private String password;

    private String displayName;

}
