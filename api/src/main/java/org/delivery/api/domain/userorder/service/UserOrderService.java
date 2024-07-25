package org.delivery.api.domain.userorder.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.userorder.UserOrderEntity;
import org.delivery.db.userorder.UserOrderRepository;
import org.delivery.db.userorder.enums.UserOrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserOrderService {

    private final UserOrderRepository userOrderRepository;

    public UserOrderEntity getUserOrderWithOutStatusWithThrow(
            Long id,
            Long userId
    ){
        return userOrderRepository.findAllByIdAndUserId(id, userId)
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }

    // 특정 주문 불러오기
    public UserOrderEntity getUserOrderWithThrow(
            Long id,
            Long userId
    ){
        return userOrderRepository.findAllByIdAndStatusAndUserId(id, UserOrderStatus.REGISTERED, userId)
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }

    // 특정 사용자의 모든 주문내역 불러오기
    public List<UserOrderEntity> getUserOrderList(Long userId){
        return userOrderRepository.findAllByUserIdAndStatusOrderByIdDesc(userId, UserOrderStatus.REGISTERED);
    }
    public List<UserOrderEntity> getUserOrderList(Long userId, List<UserOrderStatus> statusList){
        return userOrderRepository.findAllByUserIdAndStatusInOrderByIdDesc(userId, statusList);
    }

    // 현재 주문내역
    public List<UserOrderEntity> current(Long userId){
        return getUserOrderList(
                userId,
                List.of(
                        UserOrderStatus.ORDER,
                        UserOrderStatus.COOKING,
                        UserOrderStatus.DELIVERY,
                        UserOrderStatus.ACCEPT
                )
        );
    }

    // 과거 주문내역
    public List<UserOrderEntity> history(Long userId){
        return getUserOrderList(
                userId,
                List.of(
                        UserOrderStatus.RECEIVE

                )
        );
    }


    // 주문 (create)
    public UserOrderEntity order(
        UserOrderEntity userOrderEntity
    ){
        return Optional.ofNullable(userOrderEntity)
                .map(it ->{
                    it.setStatus(UserOrderStatus.ORDER);
                    it.setOrderedAt(LocalDateTime.now());
                    return userOrderRepository.save(it);
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }

    // 상태 변경 적용 메서드
    public UserOrderEntity setStatus(UserOrderEntity userOrderEntity, UserOrderStatus status){
        userOrderEntity.setStatus(status);
        return userOrderRepository.save(userOrderEntity);
    }

    // 주문 확인
    public UserOrderEntity accept(UserOrderEntity userOrderEntity){
        userOrderEntity.setAcceptedAt(LocalDateTime.now()); // accept 메서드가 호출되면 현재 시간 반영 (주문 확인 시간)
        return setStatus(userOrderEntity, UserOrderStatus.ACCEPT); // 상태를 ACCEPT 로 변경 (주문 확인)
    }

    // 조리 시작
    public UserOrderEntity cooking(UserOrderEntity userOrderEntity){
        userOrderEntity.setCookingStartedAt(LocalDateTime.now()); // cooking 메서드가 호출되면 현재 시간 반영 (조리 시작 시간)
        return setStatus(userOrderEntity, UserOrderStatus.COOKING); // 상태를 COOKING 로 변경 (조리중)
    }

    // 배달 시작
    public UserOrderEntity delivery(UserOrderEntity userOrderEntity){
        userOrderEntity.setDeliveryStartedAt(LocalDateTime.now()); // delivery 메서드가 호출되면 현재 시간 반영 (배달 시작 시간)
        return setStatus(userOrderEntity, UserOrderStatus.DELIVERY); // 상태를 DELIVERY 로 변경 (배달중)
    }

    // 배달 완료
    public UserOrderEntity receive(UserOrderEntity userOrderEntity){
        userOrderEntity.setReceivedAt(LocalDateTime.now()); // receive 메서드가 호출되면 현재 시간 반영 (배달 완료 시간)
        return setStatus(userOrderEntity, UserOrderStatus.RECEIVE); // 상태를 RECEIVE 로 변경 (배달 완료)
    }
}
