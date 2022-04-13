package com.emp.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	
	// execute 추상메서드
	String execute(HttpServletRequest request, HttpServletResponse response) throws IOException;;
	
}
