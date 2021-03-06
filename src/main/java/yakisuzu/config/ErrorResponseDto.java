package yakisuzu.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
class ErrorResponseDto {
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<Detail> details;

    @Getter
    @RequiredArgsConstructor
    static class Detail {
        private final String message;
    }
}
