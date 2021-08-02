package com.elct.elctchar.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 에러에 필요한 메시지를 두어서 사용한다.
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATE_PARAMETER("중복된 파라미터 값입니다.")
    , REQUEST_PARAMETER_ERROR("요청 파라미터 예외입니다.")
    , NONE_DATA("없는 데이터입니다.")
    , PASSWORD_CHECKING("패스워드가 일치하지 않습니다.")
    , LOGIC_ERROR("로직 에러")
    , PASSWORD_EQUAL_ERROR("비밀번호가 같음")
    , NONE_USER("존재하지 않는 아이디입니다.")
    , DUPLICATE_USER("이미 존재하는 회원입니다.");

    private final String name;
}
