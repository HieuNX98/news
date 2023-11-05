package com.laptrinhjavaweb.dao.impl;

import com.laptrinhjavaweb.dao.INewDao;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.mapper.RowMapper;
import com.laptrinhjavaweb.model.NewModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewDao extends AbstractDao<NewModel> implements INewDao {

    @Override
    public List<NewModel> findAll() {
        String sql = "SELECT * FROM news";
        return query(sql, new NewMapper());
    }
}
