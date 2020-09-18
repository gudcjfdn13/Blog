package com.whc.blog.dto;

import java.util.Map;

public class CateItem extends Dto{
	private String name;

	public CateItem() {}

	public CateItem(Map<String, Object> row) {
		super(row);
		this.name = (String) row.get("name");
	}

	@Override
	public String toString() {
		return "CateItem [name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

 
}
