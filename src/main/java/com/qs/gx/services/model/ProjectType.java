/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qs.core.model.support.BaseEntityWithHiloId;


@Table(name="PROJECT_TYPE")
@Entity
public class ProjectType extends BaseEntityWithHiloId {
	private  String typeName;
	private  String description;
	private  String administrtor;
	public ProjectType(){}
	@Column(name="NAME")
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="ADMINISTRATOR")
	public String getAdministrtor() {
		return administrtor;
	}
	public void setAdministrtor(String administrtor) {
		this.administrtor = administrtor;
	}
	
}