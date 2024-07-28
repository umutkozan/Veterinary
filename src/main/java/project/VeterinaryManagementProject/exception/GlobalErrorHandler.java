package project.VeterinaryManagementProject.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(EntityDoesNotExistException.class)
    public ResponseEntity<ErrorResponseMessage> entityNotFoundExceptionHandler(EntityDoesNotExistException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(generateErrorResponse(404, exception, request));
    }

    @ExceptionHandler(EntityDuplicateException.class)
    public ResponseEntity<ErrorResponseMessage> entityAlreadyExistHandler(EntityDuplicateException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generateErrorResponse(400, exception, request));
    }

    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<ErrorResponseMessage> duplicateDataExceptionHandler(DuplicateEntryException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generateErrorResponse(400, exception, request));
    }

    @ExceptionHandler(DoctorBusyException.class)
    public ResponseEntity<ErrorResponseMessage> doctorNotAvailableExceptionHandler(DoctorBusyException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generateErrorResponse(400, exception, request));
    }

    @ExceptionHandler(ProtectionValidityException.class)
    public ResponseEntity<ErrorResponseMessage> protectionStillActiveExceptionHandler(ProtectionValidityException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generateErrorResponse(400, exception, request));
    }

    @ExceptionHandler(DoctorAppointmentClashException.class)
    public ResponseEntity<ErrorResponseMessage> doctorAppointmentConflictExceptionHandler(DoctorAppointmentClashException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generateErrorResponse(400, exception, request));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseMessage> generalExceptionHandler(Exception exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(generateErrorResponse(500, exception, request));
    }

    private ErrorResponseMessage generateErrorResponse(int status, Exception ex, HttpServletRequest request) {
        ErrorResponseMessage errorResponse = new ErrorResponseMessage();
        errorResponse.setStatusCode(status);
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setPath(request.getRequestURI());
        return errorResponse;
    }
}
