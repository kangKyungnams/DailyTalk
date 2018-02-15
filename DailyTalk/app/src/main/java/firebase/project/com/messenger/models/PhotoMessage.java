package firebase.project.com.messenger.models;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class PhotoMessage extends Message {
    private String photoUrl;
}
