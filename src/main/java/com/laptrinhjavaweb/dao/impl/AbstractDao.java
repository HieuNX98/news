package com.laptrinhjavaweb.dao.impl;

import com.laptrinhjavaweb.dao.GenericDao;
import com.laptrinhjavaweb.mapper.RowMapper;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao<T> implements GenericDao<T>  {

    Logger log = LoggerFactory.getLogger(AbstractDao.class);

    public Connection getConnection() {
        try {
            log.debug("getConnection");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/newServlet5month2023";
            String user = "root";
            String password = "123456";
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            log.error(e.getMessage(), e.getCause());
            return null;
        }

    }

    private void setParameter(PreparedStatement statement, Object... parameters) {
        log.debug("setParameter");
        try {
            for (int i = 1; i < parameters.length; i++) {
                Object parameter = parameters[i];
                int index = i + 1;
                if (parameter instanceof Long) {
                    statement.setLong(index, (Long) parameter);
                } else if (parameter instanceof Integer) {
                    statement.setInt(index, (Integer) parameter);
                } else if (parameter instanceof String) {
                    statement.setString(index, (String) parameter);
                } else if (parameter instanceof Timestamp) {
                    statement.setTimestamp(index, (Timestamp) parameter);
                }
            }
        } catch (SQLException e) {
           log.error(e.getMessage(), e);
        }
    }

    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
        log.debug("query {}", sql);
        List<T> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null ;
        ResultSet resultset = null ;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setParameter(statement, parameters);
            resultset = statement.executeQuery();
            while (resultset.next()) {
                results.add(rowMapper.mapRow(resultset));
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(connection!=null){
                    connection.close();
                }
                if(statement !=null){
                    statement.close();
                }
                if(resultset!=null){
                    resultset.close();
                }
            } catch(SQLException e){
               log.error(e.getMessage(), e);
            }


        }
        return null;
    }

    @Override
    public void update(String sql, Object... parameters) {
        log.debug("update");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                log.error(e1.getMessage(), e1);
            }

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e2) {
                log.error(e2.getMessage(), e2);
            }


        }

    }

    @Override
    public Long insert(String sql, Object... parameters) {
        log.debug("insert");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet ;
        Long id = null;
        try{
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameter(statement,parameters);
            statement.executeUpdate();
            resultSet= statement.getGeneratedKeys();
            if(resultSet.next()){
                id=resultSet.getLong("1");
            }
            connection.commit();
            return id;
        }catch(SQLException e){
            log.error(e.getMessage());
            try {
                connection.rollback();
            }catch(SQLException e1){
                log.error(e1.getMessage());
            }
        }finally {
            try{
                if(connection!=null){
                    connection.close();
                }
                if(statement!=null){
                    statement.close();
                }
            }catch(SQLException e2){
                log.error(e2.getMessage());
            }
        }
        return null;
    }


}





