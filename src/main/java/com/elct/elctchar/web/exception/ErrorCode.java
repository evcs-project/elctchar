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
    , NON_DATA("없는 데이터입니다.")
    , LOGIC_ERROR("로직 에러")
    , PASSWORD_EQUAL_ERROR("비밀번호가 같음");
    private final String name;
}
