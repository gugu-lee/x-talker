package net.x_talker.web.entity;

public class ActionResult
{
	
	public int errorNo=0;
	public String errorMessage;
	
	public java.io.Serializable result;

	public int getErrorNo() {
		return errorNo;
	}

	public void setErrorNo(int errorNo) {
		this.errorNo = errorNo;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public java.io.Serializable getResult() {
		return result;
	}

	public void setResult(java.io.Serializable result) {
		this.result = result;
	}
	

}
