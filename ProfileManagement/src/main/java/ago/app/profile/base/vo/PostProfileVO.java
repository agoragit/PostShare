package ago.app.profile.base.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class PostProfileVO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private java.lang.String profileId;

    @NotNull(message = "userId can not null")
    private java.lang.String userId;

    @NotNull(message = "createdDate can not null")
    private java.util.Date createdDate;

    @NotNull(message = "profileType can not null")
    private java.lang.String profileType;

    @NotNull(message = "description can not null")
    private java.lang.String description;

    private java.lang.String linkedProfile;

    private int status;


}
