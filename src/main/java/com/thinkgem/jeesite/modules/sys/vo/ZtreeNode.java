package com.thinkgem.jeesite.modules.sys.vo;

import java.io.Serializable;

public class ZtreeNode implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7922442192182411778L;
	private String id;
	private String pId;
	private String name;
	private String open;
	private String file;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPId() {
		return pId;
	}
	public void setPId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
}
