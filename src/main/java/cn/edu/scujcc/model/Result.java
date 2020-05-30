package cn.edu.scujcc.model;

import java.io.Serializable;

public class Result<T> implements Serializable {
	private static final long serialVersionUID = 3832395744261177423L;
	public final static int STATUS_OK = 1;
	public final static int STATUS_ERROR = 0;
	
	private int status;
	private String message;
	private T data;
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
	public Result<T> ok() {
		Result<T> result = new Result<>();
		result.setStatus(STATUS_OK);
		result.setMessage("操作成功");
		return result;
	}

	public Result<T> error() {
		Result<T> result = new Result<>();
		result.setStatus(STATUS_ERROR);
		result.setMessage("操作失败");
		return result;
	}
}
