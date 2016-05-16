package com.rakuten.eam.exception;

public class CheckInCheckOutReportException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String reportGenerationException = null;
	public CheckInCheckOutReportException(String reportGenerationException) {
		// TODO Auto-generated constructor stub
		super(reportGenerationException);
		this.reportGenerationException = reportGenerationException;
	}

	public String getReportGenerationException() {
		return reportGenerationException;
	}
	public void setReportGenerationException(String reportGenerationException) {
		this.reportGenerationException = reportGenerationException;
	}

}
