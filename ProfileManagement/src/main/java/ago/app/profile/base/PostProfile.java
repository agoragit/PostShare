package ago.app.profile.base;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "post_profile")
public class PostProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PROFILE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @Column(name = "PROFILE_TYPE", nullable = false)
    private String profileType;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "LINKED_PROFILE")
    private String linkedProfile;

}
