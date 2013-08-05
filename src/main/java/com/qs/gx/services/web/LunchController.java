/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qs.core.web.BaseController;
import com.qs.gx.services.model.Lunch;
import com.qs.gx.services.model.Menu;
import com.qs.gx.services.service.ILunchService;
import com.qs.gx.services.service.IMenuService;

/**
 * Lunch Controller.
 * 
 * @author chuhaiquan
 * @since 2012-11-07
 */
@Controller
public class LunchController extends BaseController {

	@Autowired
	private ILunchService lunchService;

	@Autowired
	private IMenuService menuService;

	@RequestMapping(value = "/doSubmitMyLunch", method = RequestMethod.POST)
	public String doSubmitMyLunch(Model model, Lunch lunch,
			HttpServletRequest request) {
		if (lunch.getUserName() == "" || lunch.getLunchName() == "") {
			model.addAttribute("message", "餐名和姓名不能为空!");
			model.addAttribute("lunch", lunch);
			return "/lunch/apply";
		}
		List<Lunch> list = null;
		long time = System.currentTimeMillis();
		Date dateNow = new Date(time);
		String dateEnd = null;
		DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat dateFormate1 = new SimpleDateFormat("yyyy-MM-dd");
		String dateN = dateFormate.format(dateNow).toString(); // 订餐时间
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if (hour < 11) {
			dateEnd = dateFormate1.format(dateNow);
			list = lunchService.checkUserBetWeenTwoTime(lunch.getUserName(),
					dateFormate1.format(new Date(time - 86400000))
							+ " 11:00:00", dateN);
		}
		if (hour >= 11) {
			dateEnd = dateFormate1.format(new Date(time + 86400000));
			list = lunchService.checkUserBetWeenTwoTime(lunch.getUserName(),
					dateFormate1.format(new Date(time)) + " 11:00:00", dateN);
		}
		lunch.setIpAttr(request.getRemoteAddr());
		lunch.setDateNow(dateN);
		lunch.setDateTom(dateEnd);
		if (list.size() > 0) {
			model.addAttribute("lunch", lunch);
			model.addAttribute("message", "对不起.您已点餐!");
			return "/lunch/apply";
		}
		lunchService.save(lunch);
		model.addAttribute("lunch", lunch);
		return "/lunch/successInfo";
	}

	@RequestMapping(value = "/searchAllApply", method = RequestMethod.GET)
	public String findAllApply(Model model,
			@RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) Integer pageIndex,
			HttpServletRequest request) {
		if (pageSize == null) {
			pageSize = 10;
		}
		if (pageIndex == null) {
			pageIndex = 0;
		}
		long time = System.currentTimeMillis();
		Date dateNow = new Date(time);
		DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// DateFormat dateFormate1 = new SimpleDateFormat("yyyy-MM-dd");
		String nowTimeDetail = dateFormate.format(dateNow);
		// String nowTime=dateFormate1.format(dateNow);
		// model.addAttribute("dateNow",dateFormate1.format(dateNow));
		String ipAttr = request.getRemoteAddr();
		model.addAttribute("ipAttr", ipAttr);
		Page<Lunch> page = lunchService.findAllApplyByPageSizeAndPageIndex(
				pageIndex, pageSize);
		// 以下做什么样的点菜订单可以有操作进行权限分配
		// 问题1：什么样的订单可以操作
		// 情况1.当订单是于昨天的11点之后并且与第二天11点之前下的单 可以修改 可以删除 注意 这是相对于就餐时间说的
		for (int a = 0; a < page.getContent().size(); a++) {
			Lunch lunch = page.getContent().get(a);
			if (lunch.getDateNow().compareTo(nowTimeDetail) < 0
					&& nowTimeDetail
							.compareTo(lunch.getDateTom() + " 11:00:00") < 0) {
				lunch.setStatus((short) 2);
			}
		}
		model.addAttribute("page", page);
		return "/lunch/showTable";
	}

	@RequestMapping(value = "/changeMyOrder", method = RequestMethod.GET)
	public String changeMyOrder(Model model,
			@RequestParam(required = false) String id) {
		int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		Page<Menu> page = menuService.findMenuByDayOfWeek(dayOfWeek);
		model.addAttribute("page", page);
		Lunch lunch = lunchService.findById(Long.parseLong(id));
		model.addAttribute("lunch", lunch);
		return "/lunch/xiugai";
	}

	@RequestMapping(value = "/doSubmitMyXiuGai", method = RequestMethod.POST)
	public String doSubmitMyXiuGai(Model model, Lunch lunch,
			HttpServletRequest request) {
		if (lunch.getUserName() == "" || lunch.getLunchName() == "") {
			model.addAttribute("message", "餐名不能为空!");
			model.addAttribute("lunch", lunch);
			return "/lunch/xiugai";
		}
		long time = System.currentTimeMillis();
		Date dateNow = new Date(time);
		// String dateEnd = null;
		DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// DateFormat dateFormate1 = new SimpleDateFormat("yyyy-MM-dd");
		String dateN = dateFormate.format(dateNow).toString(); // 订餐时间
		// int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		// if (hour < 11) {
		// dateEnd = dateFormate1.format(dateNow);
		// }
		// if (hour >= 11) {
		// dateEnd = dateFormate1.format(new Date(time + 86400000));
		// }
		lunch.setIpAttr(request.getRemoteAddr());
		lunch.setDateNow(dateN);
		// lunch.setDateTom(dateEnd);
		lunchService.save(lunch);
		model.addAttribute("lunch", lunch);
		return "/lunch/successChange";
	}

	@RequestMapping(value = "/deleteMyOrder", method = RequestMethod.GET)
	public String deleteMyOrder(Model mdoel,
			@RequestParam(required = false) String id) {
		lunchService.delete(Long.parseLong(id));
		return "redirect:/searchAllApply";
	}

	@RequestMapping(value = "/findPageOrderByFoodName", method = RequestMethod.GET)
	public String findPageOrderByFoodName(Model model,
			@RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) Integer pageIndex,
			HttpServletRequest request) {
		if (pageSize == null) {
			pageSize = 30;
		}
		if (pageIndex == null) {
			pageIndex = 0;
		}
		long time = System.currentTimeMillis();
		Date dateNow = new Date(time);
		DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTimeDetail = dateFormate.format(dateNow);
		String ipAttr = request.getRemoteAddr();
		model.addAttribute("ipAttr", ipAttr);
		Page<Lunch> page = lunchService.findPageOrderByFoodName(pageIndex,
				pageSize);
		for (int a = 0; a < page.getContent().size(); a++) {
			Lunch lunch = page.getContent().get(a);
			if (lunch.getDateNow().compareTo(nowTimeDetail) < 0
					&& nowTimeDetail
							.compareTo(lunch.getDateTom() + " 11:00:00") < 0) {
				lunch.setStatus((short) 2);
			}
		}
		model.addAttribute("page", page);
		return "/lunch/showTable";
	}

	@RequestMapping(value = "/findTodayOrders", method = RequestMethod.GET)
	public String findTodayOrders(Model model,
			@RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) Integer pageIndex,
			HttpServletRequest request) {
		if (pageSize == null) {
			pageSize = 30;
		}
		if (pageIndex == null) {
			pageIndex = 0;
		}
		Date date = new Date(System.currentTimeMillis());
		DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateFormate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTimeDetail = dateFormate1.format(date);
		String dateToday = dateFormate.format(date);
		model.addAttribute("ipAttr", request.getRemoteAddr());
		Page<Lunch> page = lunchService.findTodayOrders(dateToday, pageIndex,
				pageSize);
		for (int a = 0; a < page.getContent().size(); a++) {
			Lunch lunch = page.getContent().get(a);
			if (lunch.getDateNow().compareTo(nowTimeDetail) < 0
					&& nowTimeDetail
							.compareTo(lunch.getDateTom() + " 11:00:00") < 0) {
				lunch.setStatus((short) 2);
			}
		}
		model.addAttribute("page", page);
		return "/lunch/showTable";
	}

	@RequestMapping(value = "/doSearchForOrderByTimeOrUserName", method = RequestMethod.GET)
	public String doSearchForOrderByTimeOrUserName(Model model, Lunch lunch,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) Integer pageIndex,
			HttpServletRequest request) {
		if (pageSize == null) {
			pageSize = 800;
		}
		if (pageIndex == null) {
			pageIndex = 0;
		}
		DateFormat dateFormate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String nowTimeDetail = dateFormate1.format(date);
		model.addAttribute("ipAttr", request.getRemoteAddr());
		Page<Lunch> page = lunchService.doSearchForOrderByTimeOrUserName(
				lunch.getUserName(), startTime, endTime, pageIndex, pageSize);
		for (int a = 0; a < page.getContent().size(); a++) {
			Lunch lunch1 = page.getContent().get(a);
			if (lunch1.getDateNow().compareTo(nowTimeDetail) < 0
					&& nowTimeDetail.compareTo(lunch1.getDateTom()
							+ " 11:00:00") < 0) {
				lunch1.setStatus((short) 2);
			}
		}
		model.addAttribute("page", page);
		return "/lunch/showTable";
	}

}
