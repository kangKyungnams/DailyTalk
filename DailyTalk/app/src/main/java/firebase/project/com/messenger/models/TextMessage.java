package firebase.project.com.messenger.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TextMessage extends Message{
    private String messageText;
}



