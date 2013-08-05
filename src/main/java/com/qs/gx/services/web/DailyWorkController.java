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
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qs.common.BeanUtilsEx;
import com.qs.core.web.BaseController;
import com.qs.gx.services.model.DailyWork;
import com.qs.gx.services.model.ProjectType;
import com.qs.gx.services.service.IDailyWorkService;
import com.qs.gx.services.service.IProjectTypeService;
import com.qs.gx.services.support.DailyWorkStatus;
import com.qs.gx.services.support.InitializationFile;
import com.qs.permission.core.support.UserRequestContextInfo;
import com.qs.permission.user.model.User;
import com.qs.permission.user.service.IUserService;

/**
 * DailyWork Controller.
 * 
 * @author chuhaiquan
 * @since 2012-12-06
 */

@RequestMapping(value = "/dailyWork/*")
@Controller
public class DailyWorkController extends BaseController {

	@Autowired
	private IDailyWorkService dailyWorkService;
	@Autowired
	private IProjectTypeService projectTypeService;
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/publish", method = RequestMethod.GET)
	public String publish(Model model, @RequestParam(required = false) Long id) {
		List<ProjectType> list=projectTypeService.findAll();
		if (id == null) {
			Date date = new Date(System.currentTimeMillis());
			DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
			String stringDate = formate.format(date);
			model.addAttribute("list",list);
			model.addAttribute("todayDate", stringDate);
		} else if (id != null) {
			DailyWork dailyWork = dailyWorkService.findById(id);
			if(dailyWork.getCompleteStatus()==DailyWorkStatus.草稿.getValue()){
				model.addAttribute("dailyWork", dailyWork);
				model.addAttribute("list",list);
				model.addAttribute("id", id);
				return "/dailyWork/caoGaoEdit";
			};
			model.addAttribute("dailyWork", dailyWork);
			model.addAttribute("id", id);
		}
		return "/dailyWork/publish";
	}

	@RequestMapping(value = "/addNewWork", method = RequestMethod.POST)
	public @ResponseBody
	void addNewWork(Model model, DailyWork dailyWork,
			@RequestParam(required = false) Long dependencyId,
			@RequestParam(required = false) Long id) {
		Date date = new Date(System.currentTimeMillis());
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		ProjectType dependencyProject = projectTypeService
				.findById(dependencyId);
		if (id == null) {
			if (dailyWork.getPublishTime() == "") {
				dailyWork.setPublishTime(formate.format(date));
			}
			dailyWork.setUser(userService.findById(UserRequestContextInfo.getVisitorId()));
			dailyWork.setUserName(userService.findById(UserRequestContextInfo.getVisitorId()).getName());
			dailyWork.setDependencyProject(dependencyProject);
			dailyWorkService.save(dailyWork);
		}
		if (id != null) {
			if (dailyWork.getCompleteStatus().shortValue()==DailyWorkStatus.完成.getValue()) {
				dailyWork.setCompeleteTime(formate.format(new Date(System
						.currentTimeMillis())));
			} else if(dailyWork.getCompleteStatus().shortValue()==DailyWorkStatus.进行中.getValue() || dailyWork.getCompleteStatus().shortValue()==DailyWorkStatus.草稿.getValue()){
				dailyWork.setDependencyProject(dependencyProject);
			}
			DailyWork dailyWork1 = dailyWorkService.findById(id);
			try {
				BeanUtilsEx.copyPropertiesWithoutNullValues(dailyWork1,
						dailyWork);
				dailyWorkService.save(dailyWork1);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/dailyWorkList", method = RequestMethod.GET)
	public String dailyWorkList(Model model,
			@RequestParam(required = false) Integer pageIndex,
			@RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime) {
		if (pageIndex == null)
			pageIndex = 0;
		if (pageSize == null)
			pageSize = 50;
		Date date = new Date(System.currentTimeMillis());
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		String today = formate.format(date);
		if (startTime == null || startTime == "") {
			startTime = today;
		}
		if (endTime == null || endTime == "") {
			endTime = today;
		}
		User user=userService.findByIsMaster(true);
		Page<DailyWork> page=null;
		if(user!=null ){
			if(UserRequestContextInfo.getVisitorId()==user.getId().longValue()){
			page=dailyWorkService.findOnePage(pageIndex, pageSize, name, startTime, endTime);
		}else{
			page = dailyWorkService.findOnePageTodayForCommonUser(UserRequestContextInfo.getVisitorId(),name,pageIndex,
					pageSize,startTime, endTime);
		}
			}else{
		 page=dailyWorkService.findOnePageTodayForCommonUser(UserRequestContextInfo.getVisitorId(),name,pageIndex,
				pageSize,  startTime, endTime);
		}
		model.addAttribute("isTurn", true);
		model.addAttribute("user", userService.findById(UserRequestContextInfo.getVisitorId()));
		model.addAttribute("name", name);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("page", page);
		return "/dailyWork/dailyWorkList";
	}

	@RequestMapping(value = "/dailyWorkListToday", method = RequestMethod.GET)
	public String dailyWorkListToday(Model model,
			@RequestParam(required = false) Integer pageIndex,
			@RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime) {
		if (pageIndex == null)
			pageIndex = 0;
		if (pageSize == null)
			pageSize = 100;
		Date date = new Date(System.currentTimeMillis());
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		String today = formate.format(date);
		if (startTime == null || startTime == "") {
			startTime = today;
		}
		if (endTime == null || endTime == "") {
			endTime = today;
		}
		User user=userService.findByIsMaster(true);
		Page<DailyWork> page=null;
		if(user!=null ){
			if(UserRequestContextInfo.getVisitorId()==user.getId().longValue()){
			page=dailyWorkService.findOnePage(pageIndex, pageSize, name, startTime, endTime);
			model.addAttribute("isTurn", true);
		}else{
			page = dailyWorkService.findOnePageTodayForCommonUser(UserRequestContextInfo.getVisitorId(),name,pageIndex,
					pageSize,startTime, endTime);
		}
		}else{
		page = dailyWorkService.findOnePageTodayForCommonUser(UserRequestContextInfo.getVisitorId(),name,pageIndex,
				pageSize,startTime, endTime);
		}
		model.addAttribute("isTurn", true);
		model.addAttribute("user", userService.findById(UserRequestContextInfo.getVisitorId()));
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("page", page);
		return "/dailyWork/dailyWorkList";
	}

	@RequestMapping(value = "/findDailyWorkByName", method = RequestMethod.GET)
	public @ResponseBody
	List<DailyWork> findDailyWorkByName(Model model,
			@RequestParam(required = false) String name) {
		UserRequestContextInfo.getVisitorId();
		Date date = new Date(System.currentTimeMillis());
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		String stringDate = formate.format(date);
		Page<DailyWork> page = dailyWorkService.findByNameToday(name,
				stringDate);
		return page.getContent();
	}
	
	
	@RequestMapping(value = "/dailyWorkExportOperatorFront", method = RequestMethod.GET)
	public String dailyWorkExportOperatorFront() {
		return "/common/common1";
	}
	
	@RequestMapping(value = "/dailyWorkExportOperator", method = RequestMethod.GET)
	public @ResponseBody
	void DailyWorkExportOperator(Model model,
			HttpServletRequest reqeust,
			HttpServletResponse response,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime) throws IOException, RowsExceededException, WriteException {
		DecimalFormat format=new DecimalFormat("#.0");
		List<Object[]> dailyWorkInfoList=dailyWorkService.findDailyWorkInfoByNameAndStartTimeAndEndTime(UserRequestContextInfo.getVisitorName(),startTime,endTime);
		List<Object[]> middleList=new ArrayList<Object[]>();
		List<Object[]> finalList=new ArrayList<Object[]>();
		double sum=0;
		double middleSum=0;
		for(int i=0;i<dailyWorkInfoList.size();i++){
			if(i==0){
				middleList.add(dailyWorkInfoList.get(i));
				sum=sum+((Double)dailyWorkInfoList.get(i)[4]).doubleValue();
			}else if(i>0 && dailyWorkInfoList.get(i)[1].equals(dailyWorkInfoList.get(i-1)[1])){
				middleList.add(dailyWorkInfoList.get(i));
				sum=sum+((Double)dailyWorkInfoList.get(i)[4]).doubleValue();
			}else{
				for(int j=0;j<middleList.size();j++){
					if(j==middleList.size()-1){
						middleList.get(j)[4]=Double.parseDouble(format.format(1-middleSum));
						middleSum=0;
						sum=0;
					}else{
						middleList.get(j)[4]=Double.parseDouble(format.format(((Double)middleList.get(j)[4]/sum)));
						middleSum=middleSum+(Double)middleList.get(j)[4];
					}
				}
				for(Object[] obj:middleList){
					finalList.add(obj);
				}
				middleList.clear();
				middleList.add(dailyWorkInfoList.get(i));
				sum=sum+((Double)dailyWorkInfoList.get(i)[4]).doubleValue();
			}
		}	
			for(int j=0;j<middleList.size();j++){
				if(j==middleList.size()-1){
					middleList.get(j)[4]=Double.parseDouble(format.format(1-middleSum));
					middleSum=0;
					sum=0;
				}else{
					middleList.get(j)[4]=Double.parseDouble(format.format(((Double)middleList.get(j)[4]/sum)));
					middleSum=middleSum+(Double)middleList.get(j)[4];
				}
			}
			for(Object[] obj:middleList){
				finalList.add(obj);
			}
				//写到Excel里面
				File file = new File(InitializationFile.getDriveUrl()+"\\zhoubao.xls");
				if(!file.exists()){
					file.createNewFile();
				}
				WritableWorkbook wwb=null;
				wwb = Workbook.createWorkbook(file); 
				if(wwb!=null){
					WritableSheet ws=wwb.createSheet("sheet1", 0);
					//下面开始新增单元格
					for(int a=0;a<finalList.size();a++){
						for(int b=0;b<5;b++){
							Label label=new Label(b,a,finalList.get(a)[b]+"");
							ws.addCell(label);
						}
					}
					//从内存中写到excel
					wwb.write();
					//关闭资源
					wwb.close();
				}
				
				// 可以加也可以不加
				response.reset();
				
				String as = "周报.xls";
		        String fileName = as;// = java.net.URLEncoder.encode(as, "UTF-8");
		        /* 根据request的locale 得出可能的编码，中文操作系统通常是gb2312 */
		        fileName = new String(as.getBytes("GB2312"), "ISO_8859_1"); 
		        as = fileName;
				response.setContentType("application/vnd.ms-excel");
				response.addHeader("Content-Disposition", "inline;filename="+as );
				response.setHeader("Content-Length", String.valueOf(file.length()));
				
				java.io.BufferedInputStream bis = null;
				java.io.BufferedOutputStream bos = null;
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
					if (bis != null)
						bis.close();
					if (bos != null)
						bos.close();
	}

}
