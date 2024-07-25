package org.delivery.api.common.error;
// API 에러코드 적용

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * User 의 경우 1000번대 에러코드 사용
 */

@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCodeIfs{

    User_NOT_FOUND(400, 1404, "사용자를 찾을 수 없음."),

    ;

    private final Integer httpStatusCode; // 값이 변경되지 않기 위해 final 로 선언

    private final Integer errorCode;

    private final String description;
}
