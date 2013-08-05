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

@Table(name = "ITERATION_WORK_LIST")
@Entity
public class IterationWorkList extends BaseEntityWithHiloId {
	private ProjectType projectType;
	private String workDescription;
	private Integer planCostDays;
	private String remark;
	private Short status;
	private String names;
	private Iteration iteration;

	public IterationWorkList() {
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ITERATION_ID")	
	public Iteration getIteration() {
		return iteration;
	}
	public void setIteration(Iteration iteration) {
		this.iteration = iteration;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PROJECT_TYPE")
	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	@Column(name="WORK_DESCRIPTION")
	public String getWorkDescription() {
		return workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}
	
	@Column(name="PLAN_COST_DAYS")
	public Integer getPlanCostDays() {
		return planCostDays;
	}

	public void setPlanCostDays(Integer planCostDays) {
		this.planCostDays = planCostDays;
	}
	
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name="STATUS")
	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	};
	
	@Column(name="NAMES")
	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

}