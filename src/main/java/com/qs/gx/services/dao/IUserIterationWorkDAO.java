/*
 * Copyright 2011-2020 www.tradeserving.com
 *
 * All right reserved.
 */
package com.qs.gx.services.dao;

import java.util.List;

import com.qs.core.dao.IBaseDAO;
import com.qs.gx.services.model.IterationWork;
import com.qs.gx.services.model.UserIterationWork;

/**
 * UserIterationWork DAO Interface.
 * @author  chuhaiquan
 * @since 2013-05-03
 */
public interface IUserIterationWorkDAO extends IBaseDAO<UserIterationWork, Long> {

	List<UserIterationWork> findByIterationWork(IterationWork iterationWork);

}
