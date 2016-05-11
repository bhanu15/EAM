package com.rakuten.eam.exception;

public class LoginRecordAlreadyExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String exceptionMsg;
	   
	   public LoginRecordAlreadyExistException(String exceptionMsg) {
	      this.exceptionMsg = exceptionMsg;
	   }
	   
	   public String getExceptionMsg(){
	      return this.exceptionMsg;
	   }
	   
	   public void setExceptionMsg(String exceptionMsg) {
	      this.exceptionMsg = exceptionMsg;
	   }

}