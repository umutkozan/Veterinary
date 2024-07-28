package project.VeterinaryManagementProject.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseMessage {

    private int statusCode;
    private String message;
    private String path;
    private Long occurrenceDate = new Date().getTime();

}
