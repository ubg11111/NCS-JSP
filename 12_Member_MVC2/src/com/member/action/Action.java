package com.member.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action { // 조상의 인터페이스 Action
	// 추상메서드
	String execute(HttpServletRequest request, HttpServletResponse response) throws IOException;  // 구현부가 없는 추상메서드를 만듬
	
}
