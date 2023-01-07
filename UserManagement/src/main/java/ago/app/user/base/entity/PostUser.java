package ago.app.user.base.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "post_user")
public class PostUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "USER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "USER_EMAIL", nullable = false)
    private String userEmail;

    @Column(name = "TELEPHONE", nullable = false)
    private String telephone;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "DISPLAY_NAME", nullable = false)
    private String displayName;

}
