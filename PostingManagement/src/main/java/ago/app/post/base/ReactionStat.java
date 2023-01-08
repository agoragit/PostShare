package ago.app.post.base;

import java.io.Serializable;

import lombok.Data;
import org.springframework.data.annotation.Transient;

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

}
