package ago.app.user.base.vo;


import java.io.Serializable;

@lombok.Data
public class PostUserQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String userName;

    private String userEmail;

    private String telephone;

    private String password;

    private String displayName;

}
