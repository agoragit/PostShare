package ago.app.comment.base;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;

@Data
public class ReactionStat implements Serializable {
    @Transient
    private long like = 0;
    @Transient
    private long love= 0;
    @Transient
    private long haha= 0;
    @Transient
    private long sad= 0;
    @Transient
    private long angry= 0;
    @Transient
    private long totalComment= 0;

}
