/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.support;

import java.io.File;

import com.qs.common.PropManager;

/**
 * 
 * 
 * @author YangRenZhi
 */
public class InitializationFile {
	// 获取当前程序发布盘符
	private static final String driveUrl = PropManager.get("FILEDRIVEURL");

	// 初始化各个文件夹
	static {
		File file1 = new File(driveUrl + "/TotalUpload");
		if (!file1.isDirectory()) {
			file1.mkdir();
		}
		File file2 = new File(driveUrl + "/TotalUpload/upload");
		if (!file2.isDirectory()) {
			file2.mkdir();
		}
	}

	public static String getDriveUrl() {
		if (driveUrl != null && driveUrl != "") {
			return driveUrl;
		} else {
			return null;
		}
	}


	
}