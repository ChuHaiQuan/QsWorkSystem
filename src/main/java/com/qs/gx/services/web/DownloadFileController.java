/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qs.core.exception.AppWebException;
import com.qs.core.web.BaseController;
import com.qs.gx.services.support.InitializationFile;

/**
 * download file Controller.
 * 
 * @author xuejiang
 * @since 2012-07-01
 */
@RequestMapping(value = "/downloadFile/*")
@Controller
public class DownloadFileController extends BaseController {
	
	@RequestMapping(value = "/{range}/{date}/{name}/{fileUrl}.{suffixName}", method = RequestMethod.GET)
	public @ResponseBody void getImageByUrlToPage(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable("range") String range,
			@PathVariable("date") String date,
			@PathVariable("name") String name,
			@PathVariable("fileUrl") String fileUrl,
			@PathVariable("suffixName") String suffixName,
			@RequestParam(required = false) String fileName) {
		try {
			//对URL进行解码
			String filePath = range + "\\" + date + "\\" + name + fileUrl + "." + suffixName.toLowerCase();
			File file = new File(InitializationFile.getDriveUrl() + filePath);
			if (!file.exists()){
				filePath = range + "\\" + date + "\\" + name + fileUrl + "." + suffixName.toUpperCase();
				file = new File(InitializationFile.getDriveUrl() + "/" + filePath);
				if (!file.exists()) throw new AppWebException("抱歉，要下载的文件不存在！");
			}
			// 可以加也可以不加
			response.reset();
			response.setContentType("application/x-download");
			if(fileName != null && !"".equals(fileName)){
				String agent = request.getHeader("User-Agent");
				String primalName = "";
				if (null != agent && -1 != agent.indexOf("MSIE")) {
					primalName = URLEncoder.encode(fileName, "UTF-8");
				} else {
					primalName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
				}
				response.addHeader("Content-Disposition", "attachment;filename=" + primalName);
			}else{
				response.addHeader("Content-Disposition", "attachment;filename=" + range + "." + suffixName);
			}
			response.setHeader("Content-Length", String.valueOf(file.length()));
			java.io.BufferedInputStream bis = null;
			java.io.BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(new FileInputStream(file));
				// 得到向客户端输出二进制数据的对象
				bos = new BufferedOutputStream(response.getOutputStream());
				// 得到文件大小
				int space = bis.available();
				byte data[] = new byte[space];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(data, 0, data.length))) {
					bos.write(data, 0, bytesRead);
				}
				bos.flush();
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
		} catch (IOException e) {
			throw new AppWebException("下载文件失败！文件读取转换失败！", e);
		}
	}

}