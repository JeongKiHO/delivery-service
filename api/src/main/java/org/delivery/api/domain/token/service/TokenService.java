package org.delivery.api.domain.token.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.ifs.TokenHelperIfs;
import org.delivery.api.domain.token.model.TokenDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * token 에 대한 도메인 로직 담당
 */
@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenHelperIfs tokenHelperIfs;

    // issueAccessToken 정의
    public TokenDto issueAccessToken(Long userId){
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelperIfs.issueAccessToken(data);
    }

    // issueRefreshToken 정의
    public TokenDto issueRefreshToken(Long userId){
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelperIfs.issueRefreshToken(data);
    }

    // validationTokenWithThrow 정의
    public Long validationToken(String token){
        var map = tokenHelperIfs.validationTokenWithThrow(token); // 토큰 검증 후 map 에 대입
        var userId = map.get("userId"); // userId 찾아옴
        Objects.requireNonNull(userId, ()-> {throw new ApiException(ErrorCode.NULL_POINT);}); // userId 가 없으면 예외 발생
        return Long.parseLong(userId.toString());
    }
}
