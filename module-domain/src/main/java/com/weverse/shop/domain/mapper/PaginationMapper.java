package com.weverse.shop.domain.mapper;

import com.weverse.shop.common.dto.request.PaginationRequest;
import com.weverse.shop.common.dto.response.PaginationResponse;
import com.weverse.shop.domain.dto.PaginationRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PaginationMapper {

    PaginationRecord toPaginationRecord(PaginationRequest request);

    @Mapping(target = "currentPage", expression = "java(param.getNumber() + 1)")
    @Mapping(target = "isLast", source = "last")
    PaginationResponse toPaginationResponseBySlice(Slice<?> param);

    @Mapping(target = "currentPage", expression = "java(param.getNumber() + 1)")
    @Mapping(target = "hasNext", expression = "java(param.hasNext())")
    @Mapping(target = "hasPrevious", expression = "java(param.hasPrevious())")
    PaginationResponse toPaginationResponseToPage(Page<?> param);

    default PaginationResponse toPaginationResponse(Slice<?> param){
        if(param instanceof Page){
            return toPaginationResponseToPage((Page) param);
        }
        return toPaginationResponseBySlice(param);
    }
}
