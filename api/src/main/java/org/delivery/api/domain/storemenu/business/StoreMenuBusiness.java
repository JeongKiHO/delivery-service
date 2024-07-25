package org.delivery.api.domain.storemenu.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.api.domain.storemenu.service.StoreMenuService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
public class StoreMenuBusiness {

    private final StoreMenuService storeMenuService;

    private final StoreMenuConverter storeMenuConverter;


    public StoreMenuResponse register(
            StoreMenuRegisterRequest request
    ){
        // request -> entity 로 변환 -> save 후 -> response 로 변환
        var entity = storeMenuConverter.toEntity(request);
        var newEntity = storeMenuService.register(entity);
        var response = storeMenuConverter.toResponse(newEntity);
        return response;
    }

    // 특정 가게 내용 검색
    public List<StoreMenuResponse> search(Long storeId){

        var list = storeMenuService.getStoreMenuByStoreId(storeId);

        return list.stream()
                .map(it->{
                    return storeMenuConverter.toResponse(it);
                }).collect(Collectors.toList());

    }
}
