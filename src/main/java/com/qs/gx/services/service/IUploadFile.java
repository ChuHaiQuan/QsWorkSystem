/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * upload file interface.
 * 
 * @author xuejiang
 * @since 2012-06-21
 */
public interface IUploadFile {

	/**
	 * 开放给外部的文件上传接口
	 * 
	 * @param file
	 * @param directory
	 * @return 编码之后的路径
	 */
	public String uploadFile(MultipartFile file, String[] directory);

	public String changeNumToLet(String num);

	// 对文件名进行解码
	// 用来转换出日期文件夹
	public String changeLetToNum(String let);

	/**
	 * 删除上传的文件
	 * 
	 * @param fileUrl
	 */
	public void deleteUploadFile(String fileUrl);

}