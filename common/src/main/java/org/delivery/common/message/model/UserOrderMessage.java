package org.delivery.common.message.model;

// 사용자 주문 아이디를 받아 메세지로 넘겨준다.

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderMessage {

    public Long userOrderId;
}
