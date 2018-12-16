package com.example.asus.OfflineMap;


import com.example.asus.OfflineMap.bean.TreeNodeId;
import com.example.asus.OfflineMap.bean.TreeNodeLabel;
import com.example.asus.OfflineMap.bean.TreeNodePid;

public class FileBean
{
	@TreeNodeId
	private int _id;
	@TreeNodePid
	private int parentId;
	@TreeNodeLabel
	private String name;
	private long length;
	private String desc;

	public FileBean(int _id, int parentId, String name)
	{
		super();
		this._id = _id;
		this.parentId = parentId;
		this.name = name;
	}

}
