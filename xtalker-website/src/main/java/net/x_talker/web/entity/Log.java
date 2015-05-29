package net.x_talker.web.entity;

public class Log {
	private long id;
	private String host;
	private String impi;
	private java.sql.Date logDateTime;
	private String action;
	private String content;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getImpi() {
		return impi;
	}
	public void setImpi(String impi) {
		this.impi = impi;
	}
	
	public java.sql.Date getLogDateTime() {
		return logDateTime;
	}
	public void setLogDateTime(java.sql.Date logDateTime) {
		this.logDateTime = logDateTime;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
