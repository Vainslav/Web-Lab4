package javagnomes.lab4.rest.shots;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ShotsRequest {
    @NotNull(message = "X MUST BE NOT NULL")
    @Size(min = 1, max = 30, message = "X IS TOO LONG")
    private String x;
    @NotNull(message = "Y MUST BE NOT NULL")
    @Size(min = 1, max = 30, message = "Y IS TOO LONG")
    private String y;
    @NotNull(message = "R MUST BE NOT NULL")
    @Size(min = 1, max = 30, message = "R IS TOO LONG")
    private String r;
}
