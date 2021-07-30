package com.elct.elctchar.web.station.dto;

import com.elct.elctchar.web.station.domain.cptype.ChargeTp;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StationSearchRequestDto {

    @NotBlank(message = "충전소 이름을 입력해주세요.")
    @ApiModelProperty(value = "충전소 이름")
    private String csNm;

    @NotNull(message = "충전기 타입을 입력해주세요.")
    @ApiModelProperty(value = "충전기 타입")
    private ChargeTp chargeTp;

    @NotNull(message = "검색유형을 입력해주세요")
    @ApiModelProperty(value = "충전소 검색유형")
    private StationSearchType stationSearchType;
}
