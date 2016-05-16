package com.rakuten.eam.exception;

public class EAMException extends RuntimeException
{
	
	private static final long serialVersionUID = 1L;
	private String exceptionMsg;
	   
	   public EAMException(String exceptionMsg) {
		   super(exceptionMsg);
	      this.exceptionMsg = exceptionMsg;
	   }
	   
	   public String getExceptionMsg(){
	      return this.exceptionMsg;
	   }
	   
	   public void setExceptionMsg(String exceptionMsg) {
	      this.exceptionMsg = exceptionMsg;
	   }

}
