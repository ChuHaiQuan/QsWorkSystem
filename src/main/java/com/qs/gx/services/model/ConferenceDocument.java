/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.qs.core.model.support.BaseEntityWithHiloId;

@Table(name = "ConferenceDocument")
@Entity
public class ConferenceDocument extends BaseEntityWithHiloId {

	private String createTime;
	private Short type;
	private File file;

	public ConferenceDocument() {
	}

	@Column(name = "createTime")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "type")
	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="fileId")
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}