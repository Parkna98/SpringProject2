package com.sist.vo;

import lombok.Data;

/*
 * 		보안 (security) => id (userId,userName,userPwd) 보안설정 시에는 변수명을 userId로 설정한다 id(x)
 * 		권한 ==> ROLE_ADMIN, ROLE_MANAGER, ROLE_XXX, ........ ROLE 뒤에는 자유
 * 		=> 1. 비밀번호 암호화
 * 		=> 2. 권한 부여
		=> 3. 자동로그인
		=> 4. ID 저장
		=> 메소드 보안
 */
@Data
public class AuthorityVO {
	private String userId,authority;
}
