package com.elct.elctchar.web.station.dto;

import com.elct.elctchar.web.station.domain.cptype.ChargeTp;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StationSearchRequestDto {
    @NotBlank(message = "검색어를 입력해주세요.")
    private String search;

    private ChargeTp chargeTp;
}
