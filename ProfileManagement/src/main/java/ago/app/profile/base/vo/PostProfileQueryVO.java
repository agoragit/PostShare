package ago.app.profile.base.vo;


@lombok.Data
public class PostProfileQueryVO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private java.lang.Long _id;

    private java.lang.Long userId;

    private java.util.Date createdDate;

    private java.lang.String profileType;

    private java.lang.String description;

    private java.lang.String linkedProfile;

    private int status;

}
