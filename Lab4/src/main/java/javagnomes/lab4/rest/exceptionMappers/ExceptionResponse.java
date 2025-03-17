package javagnomes.lab4.rest.exceptionMappers;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Data
@AllArgsConstructor
public class ExceptionResponse implements Serializable {
    private String message;
}
