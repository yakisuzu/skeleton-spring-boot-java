package yakisuzu.config;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.stream.Collectors;

class ErrorResponseDtoFactory {
    static ErrorResponseDto of(String m, ConstraintViolationException ex) {
        return new ErrorResponseDto(
                m,
                ex.getConstraintViolations().stream()
                        .map(v -> new ErrorResponseDto.Detail(v.getMessage()))
                        .collect(Collectors.toList())
        );
    }

    static ErrorResponseDto ofDefault(String m, Exception ex) {
        return new ErrorResponseDto(m, Collections.singletonList(new ErrorResponseDto.Detail(ex.getMessage())));
    }
}
