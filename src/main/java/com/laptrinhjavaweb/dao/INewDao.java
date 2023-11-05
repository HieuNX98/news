package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.model.NewModel;

import java.util.List;

public interface INewDao {
    List<NewModel> findAll();
}
