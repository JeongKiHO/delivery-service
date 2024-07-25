package org.delivery.api.domain.store.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.store.enums.StoreCategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreRegisterRequest {


    @NotBlank
    private String name; // 가맹점 이름


    @NotBlank
    private String address; // 가맹점 주소

    @NotNull
    private StoreCategory storeCategory;

    @NotBlank
    private String thumbnailUrl;

    @NotNull
    private BigDecimal minimumAmount; // 최소 주문금액

    @NotNull
    private BigDecimal minimumDeliveryAmount; // 최소 배달비

    @NotBlank
    private String phoneNumber;
}
