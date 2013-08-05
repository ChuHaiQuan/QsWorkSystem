	function makeInputInvalid(inputId) {
		$("#" + inputId).attr("disabled", "disabled");
		$("#" + inputId).val("提交中...");
	}

	function makeInputEffective(inputId, inputTitle) {
		$("#" + inputId).removeAttr("disabled");
		$("#" + inputId).val(inputTitle);
	}