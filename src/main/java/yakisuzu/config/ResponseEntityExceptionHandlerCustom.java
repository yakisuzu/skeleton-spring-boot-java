package yakisuzu.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ResponseEntityExceptionHandlerCustom extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        // TODO logger
        ErrorResponseDto dto = new ErrorResponseDto(
                "validation error",
                ex.getConstraintViolations().stream()
                        .map(v -> new ErrorResponseDto.ValidationMessage(v.getMessage()))
                        .collect(Collectors.toList())
        );
        return super.handleExceptionInternal(ex, dto, null, HttpStatus.BAD_REQUEST, request);
    }
}
