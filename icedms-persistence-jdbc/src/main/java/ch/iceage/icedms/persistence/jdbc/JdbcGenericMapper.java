/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.iceage.icedms.persistence.jdbc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.iceage.icedms.core.business.Identity;
import ch.iceage.icedms.core.persistence.GenericMapper;
import ch.iceage.icedms.persistence.jdbc.query.Queries;
import ch.iceage.icedms.persistence.jdbc.query.clause.FetchType;

/**
 *
 * 
 * @param <T>
 * @param <K>
 */
public abstract class JdbcGenericMapper<T extends Identity, 
										R extends RowMapper<T>,
										Q extends Queries<T, Long>> implements GenericMapper<T, Long> {
    
    protected JdbcTemplate jdbcTemplate;
    protected SimpleJdbcInsert jdbcInsert;
    protected Q queries;
    protected Class<R> mapperType;
    protected String tableName;
    protected String tableAlias;
    
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate= new JdbcTemplate(dataSource);
        this.jdbcInsert=new SimpleJdbcInsert(dataSource);
    }
    
    @Override
	public T getByKey(Long key) {
		return doGetByKey(key);
	}

	@Override
	public List<T> getAll() {
		return doGetAll();
	}

	@Override
	public List<T> get(T criteria) {
		return doGet(criteria);
	}

	@Override
	public T create(T entity) {
		return doCreate(entity);
	}

	@Override
	public void remove(T entity) {
		doRemove(entity);
	}

	@Override
	public void removeByKey(Long key) {
		doRemoveByKey(key);
	}
	
    protected abstract void init(Q queries);

	protected final T doGetByKey(Long key) {
		return jdbcTemplate.queryForObject(queries.getByKey(tableName, tableAlias,  key), getRowMapper(mapperType, tableAlias));
	}

	protected final List<T> doGetAll() {
		return jdbcTemplate.query(queries.getAll(tableName, tableAlias), getRowMapper(mapperType, tableAlias));
	}
	
	protected final List<T> doGet(T criteria) {
		return new ArrayList<>();
	}

	protected final T doCreate(T entity) {
		jdbcInsert.withTableName(tableName).execute(convertToMap(entity));
		return entity;
	}

	protected final void doRemove(T entity) {
		jdbcTemplate.update(queries.delete(tableName, entity.getId()));
	}

	protected final void doRemoveByKey(Long key) {
		jdbcTemplate.update(queries.delete(tableName, key));
	}
	
	@SuppressWarnings({ "unchecked" })
	private Map<String, Object> convertToMap(T entity) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(entity, Map.class);
	}
    
	@SuppressWarnings("unchecked")
	private R getRowMapper(Class<R> mapperType, String alias) {
		R rowMapper=null;
		try {
			Constructor<?> constructor = mapperType.getConstructors()[0];
			if(constructor.getParameters().length==3) {
				rowMapper = (R) constructor.newInstance(FetchType.LAZY, FetchType.EAGER, alias);
			} else if(constructor.getParameters().length==1) {
				rowMapper = (R) constructor.newInstance(alias);
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException 
				| InvocationTargetException | SecurityException e) {
			e.printStackTrace();
		}
		return rowMapper;
	}

}
