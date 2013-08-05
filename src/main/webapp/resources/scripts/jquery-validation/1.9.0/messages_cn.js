/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: CN
 */
jQuery.extend(jQuery.validator.messages, {
        required: "请填写信息",
		remote: "请修正该字段",
		email: "请输入正确格式的电子邮件",
		url: "请输入合法的网址",
		date: "请输入合法的日期",
		dateISO: "请输入合法的日期 (ISO).",
		number: "请输入合法的数字",
		digits: "只能输入整数",
		creditcard: "请输入合法的信用卡号",
		equalTo: "请再次输入相同的值",
		accept: "请输入拥有合法后缀名的字符串",
		maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
		minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
		rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
		range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
		max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
		min: jQuery.validator.format("请输入一个最小为 {0} 的值")
});
// 手机号码验证       
jQuery.validator.addMethod("isMobile", function(value, element) {       
    var length = value.length;   
    var mobile = /^1[3584]\d{9}$/i;   
    return this.optional(element) || (length == 11 && mobile.test(value));       
}, "请正确填写您的手机号码");       
     
// 电话号码验证       
jQuery.validator.addMethod("isTel", function(value, element) {       
	var tel = /^((\d{3,4})|\d{3,4}-)?\d{7,8}(-\d+)*$/i;      //电话号码格式010-12345678   
    return this.optional(element) || (tel.test(value));       
}, "请正确填写您的电话号码");  
//联系电话(手机/电话皆可)验证 
jQuery.validator.addMethod("isPhone", function(value,element) { 
  var mobile = /^1[3584]\d{9}$/i;
  var tel = /^((\d{3,4})|\d{3,4}-)?\d{7,8}(-\d+)*$/i; 
  return this.optional(element) || (tel.test(value) || (length == 11 && mobile.test(value)) ); 
}, "请正确填写您的联系电话"); 

// Email验证
jQuery.validator.addMethod("isEmail", function(value, element) {
	var reg = /^([a-zA-Z0-9]+[_|\_|\.|-]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.|-]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	return this.optional(element) || (reg.test(value));
}, "请正确填写您的电子邮箱");
//英文和数字的验证
jQuery.validator.addMethod("isEnglishOrShuZi",function(value,element){
	var reg=/^[0-9a-zA-Z\x00-\xff&]+$/g;
	return this.optional(element)|| (reg.test(value));
},"请输入数字或者英文!");
// 验证非纯数字
jQuery.validator.addMethod("isNAN", function(value, element) {
	var reg = /^\d+$/;
	return this.optional(element) || (!reg.test(value));
}, "用户名不能为纯数字组合");
// 验证海关编码 十位正整数
jQuery.validator.addMethod("isCustomCode", function(value,element) { 
	  var mobile = /^((\d{10}))*$/i; 
	  return this.optional(element) || (mobile.test(value)); 
	}, "请正确填写海关编码"); 
// 验证数字格式的金额
jQuery.validator.addMethod("isPriceNum", function(value,element) { 
	var mobile = /^[1-9]{1}\d*(\.\d{1,2})?$/; 
	return this.optional(element) || (mobile.test(value)); 
}, "请正确填写数字格式的金额"); 