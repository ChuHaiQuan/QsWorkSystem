/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.qs.core.exception.AppServiceException;
import com.qs.gx.services.support.InitializationFile;



/**
 * upload file class.
 * 
 * @author YangRenZhi
 */
@Service
@Transactional
public class UploadFile implements IUploadFile{
	
	// 检查文件夹是否存在
	// 如果不存在则新建文件夹
	private String checkExist(String [] directorys){
		String s = "";
		for(String directory : directorys){
			s = s + "/" + directory;
			File file = new File(InitializationFile.getDriveUrl() + "/" + s);
			if(!file.isDirectory()){
				file.mkdir(); 
			}
		}
		Date date = new Date();
		SimpleDateFormat fMonth = new SimpleDateFormat("yyyy-MM");
		String monthStr = fMonth.format(date);
		s = s + "/" + monthStr;
		File file = new File(InitializationFile.getDriveUrl() + "/" + s);
		if(!file.isDirectory()){
			file.mkdir(); 
		}
		return s;
	}
	
	// 对文件名进行转码
	@Override
	public String changeNumToLet(String num){
		String[] nums = num.split("");
		String let = "";
		String[][][] letter = {
				{{"c","f"},{"E","t"},{"r","b"},{"g","Y"},{"h","l"},{"J","5"},{"k","3"},{"n","Q"},{"p","w"},{"M","4"}},
				{{"g","2"},{"8","L"},{"c","h"},{"D","a"},{"r","9"},{"i","U"},{"p","m"},{"O","x"},{"z","y"},{"3","S"}},
				{{"h","0"},{"B","w"},{"a","z"},{"x","C"},{"v","n"},{"M","9"},{"l","j"},{"g","F"},{"d","s"},{"E","t"}},
				{{"3","e"},{"d","C"},{"v","f"},{"R","4"},{"5","t"},{"g","B"},{"n","h"},{"Y","6"},{"7","u"},{"j","K"}},
				{{"2","w"},{"S","x"},{"c","d"},{"D","E"},{"3","4"},{"R","f"},{"v","m"},{"j","U"},{"7","i"},{"I","p"}}};
		int ram1 = new Random().nextInt(5);
		String[][] letterCh = letter[ram1];
		switch(ram1){
			case 1: let = "a"; break;
			case 2: let = "f"; break;
			case 3: let = "g"; break;
			case 4: let = "j"; break;
			case 0: let = "l";
		}
		int ram2 = new Random().nextInt(2);
		for(int i=1, j=nums.length; i<j; i++,ram2 = new Random().nextInt(2)){
			switch(Integer.valueOf(nums[i])){
				case 1: let += letterCh[1][ram2]; break;
				case 2: let += letterCh[2][ram2]; break;
				case 3: let += letterCh[3][ram2]; break;
				case 4: let += letterCh[4][ram2]; break;
				case 5: let += letterCh[5][ram2]; break;
				case 6: let += letterCh[6][ram2]; break;
				case 7: let += letterCh[7][ram2]; break;
				case 8: let += letterCh[8][ram2]; break;
				case 9: let += letterCh[9][ram2]; break;
				case 0: let += letterCh[0][ram2];
			}
		}
		return let;
	}
	@Override
	public String changeLetToNum(String let){
		String[] lets = let.split("");
		String num = "";
		String[][][] letter = {
				{{"c","f"},{"E","t"},{"r","b"},{"g","Y"},{"h","l"},{"J","5"},{"k","3"},{"n","Q"},{"p","w"},{"M","4"}},
				{{"g","2"},{"8","L"},{"c","h"},{"D","a"},{"r","9"},{"i","U"},{"p","m"},{"O","x"},{"z","y"},{"3","S"}},
				{{"h","0"},{"B","w"},{"a","z"},{"x","C"},{"v","n"},{"M","9"},{"l","j"},{"g","F"},{"d","s"},{"E","t"}},
				{{"3","e"},{"d","C"},{"v","f"},{"R","4"},{"5","t"},{"g","B"},{"n","h"},{"Y","6"},{"7","u"},{"j","K"}},
				{{"2","w"},{"S","x"},{"c","d"},{"D","E"},{"3","4"},{"R","f"},{"v","m"},{"j","U"},{"7","i"},{"I","p"}}};
		String[][] letterCh = null;
		if(lets[1].equals("a")){
			letterCh = letter[1];
		}else if(lets[1].equals("f")){
			letterCh = letter[2];
		}else if(lets[1].equals("g")){
			letterCh = letter[3];
		}else if(lets[1].equals("j")){
			letterCh = letter[4];
		}else if(lets[1].equals("l")){
			letterCh = letter[0];
		}
		
		// 对年份跟月份进行解码
		for(int i=2, j=lets.length; i<j; i++){
			for(int k = 0; k<10; k++){
				if(lets[i].equals(letterCh[k][0]) || lets[i].equals(letterCh[k][1])){
					num += String.valueOf(k);
					// 转换年份后加上“-”符号
					if(i==5)
						num += "-";
					break;
				}
			}
		}
		return num;
	}

	@Override
	public String uploadFile(MultipartFile file, String [] directory){
		try {
			// 检查分类文件夹下日期文件夹
			String path = checkExist(directory);
			
			// 获得文件名
			final String originalFilename = file.getOriginalFilename();
			// 获得文件后缀名
			final String[] filenamesSplit = originalFilename.split("\\.");
			final String fileSuffixName = filenamesSplit[filenamesSplit.length - 1];

			// 生成编码后的文件名
			SimpleDateFormat fDay = new SimpleDateFormat("yyyyMMddhhmmss");
			String dayStr = fDay.format(new Date());
			String finalFileName = changeNumToLet(dayStr+System.currentTimeMillis()) + "." + fileSuffixName;
			
			// 存放指定目录
			final File source = new File(InitializationFile.getDriveUrl()+path + "\\og" + finalFileName);
			file.transferTo(source);
			
			//修改数据库存储路径
			return "/downloadFile" + path + "/og/" + finalFileName;
		} catch (UnsupportedEncodingException e) {
			throw new AppServiceException("文件上传错误！", e);
		} catch (Exception e) {
			throw new AppServiceException("文件上传错误！", e);
		}
	}

	@Override
	public void deleteUploadFile(String fileUrl){
		String[] fileUrls = fileUrl.split("/");
		// 对URL进行解码
		String monthStr = changeLetToNum(fileUrls[4].substring(0, 7));
		String filePath = "\\TotalUpload\\"+ fileUrls[2] + "\\" + monthStr + "\\" + fileUrls[3] + fileUrls[4];
		
		File file = new File(InitializationFile.getDriveUrl() + filePath);
		
		if (!file.exists())
			throw new AppServiceException("抱歉，要删除的文件不存在！");
		
		if (!file.delete()) {
			throw new AppServiceException("抱歉，删除文件时出现错误！");
		}
	}
	
}