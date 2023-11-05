package com.laptrinhjavaweb.mapper;

import com.laptrinhjavaweb.model.NewModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewMapper implements RowMapper<NewModel>{

    @Override
    public NewModel mapRow(ResultSet resultSet) {
        NewModel news = new NewModel();
        try {
            news.setId(resultSet.getLong("id"));
            news.setContent(resultSet.getString("content"));
            news.setShortDescription(resultSet.getString("shortDescription"));
            news.setThumbnail(resultSet.getString("thumbnail"));
            news.setTitle(resultSet.getString("title"));
            news.setCategoryId(resultSet.getLong("categoryId"));
            news.setCreatedDate(resultSet.getTimestamp("createdDate"));
            news.setCreatedBy(resultSet.getString("createdBy"));
            if(resultSet.getTimestamp("modifiedDate")!=null){
                news.setModifiedDate(resultSet.getTimestamp("modifiedDate"));
            }
            if(resultSet.getString("modifiedBy")!=null){
                news.setModifiedby(resultSet.getString("modifiedBy"));
            }
            return news;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
