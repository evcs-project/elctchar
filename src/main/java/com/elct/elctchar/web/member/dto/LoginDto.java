package com.elct.elctchar.web.member.dto;
import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull
    private String nickName;

    @NotNull
    @Size(min = 3, max = 100, message = "비밀번호는 3글자 이상이어야 합니다.")
    private String password;
}
