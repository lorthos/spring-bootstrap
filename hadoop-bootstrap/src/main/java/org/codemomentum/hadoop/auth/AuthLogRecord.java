package org.codemomentum.hadoop.auth;

import java.io.Serializable;
import java.util.Date;

/**
 * @author codemomentum@gmail.com
 */
public class AuthLogRecord implements Serializable {
	private Date date;
	private String daemon;
	private String process;
	private Integer pid;
	private String message;

	public AuthLogRecord(Date date, String daemon, String process, Integer pid, String message) {
		this.date = date;
		this.daemon = daemon;
		this.process = process;
		this.pid = pid;
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public String getDaemon() {
		return daemon;
	}

	public String getProcess() {
		return process;
	}

	public Integer getPid() {
		return pid;
	}

	public String getMessage() {
		return message;
	}
}
