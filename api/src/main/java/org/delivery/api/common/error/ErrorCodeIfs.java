package org.delivery.api.common.error;

// ErrorCode,UserErrorCode 는 ErrorCodeIfs 를 상속받는다.
public interface ErrorCodeIfs {

    Integer getHttpStatusCode();

    Integer getErrorCode();

    String getDescription();
}
