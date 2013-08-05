<%@ page pageEncoding="utf-8"%>

	<form action="/dailyWork/dailyWorkExportOperator" method="get">
		<input placeholder="起始时间.." type="text" class="search-query span3"
		name="startTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
		 style="width:30%;" value=""></input>- <input placeholder="截止时间.."
		type="text" class="search-query span3"
		style="position: relative; left: -3px; width:30%;" name="endTime"
		onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  value=""></input>
		<button class="btn btn-info" id="daochu">
			导出
		</button>
		</form>