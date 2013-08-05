package com.qs.gx.services.dao;

import java.util.List;

import com.qs.gx.services.model.Iteration;

public interface IIterationPlusDAO {

	List<Object[]> findFullAttendenceAwardByIteration(Iteration iteration);

}
