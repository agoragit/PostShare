package ago.app.profile.base.dto;


@lombok.Data
public class PostProfileDTO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private java.lang.String profileId;

    private java.lang.String userId;

    private java.util.Date createdDate;

    private java.lang.String profileType;

    private java.lang.String description;

    private java.lang.String linkedProfile;

}
