package org.delivery.db.user;

import org.delivery.db.user.enums.UserStatus;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> { // UserEntity 참고, Long >> id 어노테이션을 가진 변수의 타입

    // 쿼리 메서드 생성
    // SELECT * FROM user WHERE id = ? AND status = ? ORDER BY id DESC limit 1
    Optional<UserEntity> findFirstByIdAndStatusOrderByIdDesc(Long userId, UserStatus status);

    // SELECT * FROM user WHERE email = ? AND password = ? AND status = ? ORDER BY id DESC limit 1
    Optional<UserEntity> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, UserStatus status);
}
