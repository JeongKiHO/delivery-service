package org.delivery.api.common.error;
// API 에러코드 적용

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Token 의 경우 2000번대 에러코드 사용
 */

@AllArgsConstructor
@Getter
public enum TokenErrorCode implements ErrorCodeIfs{

    INVALID_TOKEN(400, 2000, "유효하지 않은 토큰"),
    EXPIRED_TOKEN(400,2001,"만료된 토큰"),
    TOKEN_EXCEPTION(400, 2002, "알 수 없는 토큰 에러"),
    AUTHORIZATION_TOKEN_NOT_FOUND(400, 2003, "인증 헤더 토큰 없음"),

    ;

    private final Integer httpStatusCode; // 값이 변경되지 않기 위해 final 로 선언

    private final Integer errorCode;

    private final String description;
}
