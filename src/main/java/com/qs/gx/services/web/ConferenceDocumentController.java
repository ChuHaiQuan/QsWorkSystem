/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.web;


import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qs.core.web.BaseController;
import com.qs.gx.services.model.ConferenceDocument;
import com.qs.gx.services.model.File;
import com.qs.gx.services.service.IConferenceDocumentService;
import com.qs.gx.services.service.IFileService;
import com.qs.gx.services.service.IUploadFile;
import com.qs.permission.core.support.UserRequestContextInfo;



/**
 * ConferenceDocument Controller.
 * @author chuhaiquan
 * @since 2013-04-08
 */
@RequestMapping(value = "/conferenceDocument/*")
@Controller
public class ConferenceDocumentController extends BaseController {

	@Autowired
	private IConferenceDocumentService conferenceDocumentService;
	
	@Autowired
	private IUploadFile uploadFileService;
	
	@Autowired
	private IFileService fileService;
	
	@RequestMapping(value = "/publish", method = RequestMethod.GET)
	public String  publish(Model model) {
//		Date date=new Date(System.currentTimeMillis());
//		Format format=new SimpleDateFormat("yyyy-MM-dd");
		return "/conferenceDocument/public";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody void  save(Model model,ConferenceDocument conferenceDocument,
			@RequestParam(required = false) MultipartFile files) {
		Date date=new Date(System.currentTimeMillis());
		Format format=new SimpleDateFormat("yyyy-MM-dd");
		conferenceDocument.setCreateTime(format.format(date));
		//文件上传开始
		String urlStr=null;
		File fileObj=null;
		 if(conferenceDocument.getType().shortValue()==0){     //启动会议
			 String [] s = {"startMeeting"};
			 urlStr=uploadFileService.uploadFile(files, s);
		 }else if(conferenceDocument.getType().shortValue()==2){   //迭代会议
			 String [] s = {"iterativeConference"};
			 urlStr=uploadFileService.uploadFile(files, s);
		 }else{   
			 String [] s = {"annualMeeting"};     //年度会议
			 urlStr=uploadFileService.uploadFile(files, s);
		 }
		 fileObj=new File();
		 fileObj.setPath(urlStr);
		 fileObj.setName(files.getOriginalFilename());
		 fileObj.setPunishTime(format.format(date));
		 fileObj.setUserId(UserRequestContextInfo.getVisitorId());
		 fileService.save(fileObj);
		//文件上传结束
		 conferenceDocument.setFile(fileObj);
		 conferenceDocumentService.save(conferenceDocument);
		
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String  list(Model model,
			@RequestParam(required = false) Integer pageIndex,
			@RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) Short type) {
		Page<ConferenceDocument> page=conferenceDocumentService.findOnePage(type,pageIndex,pageSize);
		model.addAttribute("page", page);
		return "/conferenceDocument/list";
	}
}
