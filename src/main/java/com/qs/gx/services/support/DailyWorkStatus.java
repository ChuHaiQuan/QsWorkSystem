package com.qs.gx.services.support;

public enum DailyWorkStatus {

	草稿((short) 0), 进行中((short) 1), 完成((short) 2), 未完成((short) 3), 取消((short) 4), 废弃(
			(short) 5);
	
	private final Short value;

	private DailyWorkStatus(Short value) {
		this.value = value;
	}

	public Short getValue() {
		return value;
	}

}
