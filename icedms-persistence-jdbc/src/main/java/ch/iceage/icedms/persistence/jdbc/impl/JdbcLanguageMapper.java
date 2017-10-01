package ch.iceage.icedms.persistence.jdbc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.iceage.icedms.core.business.Language;
import ch.iceage.icedms.core.persistence.LanguageMapper;
import ch.iceage.icedms.persistence.jdbc.JdbcGenericMapper;
import ch.iceage.icedms.persistence.jdbc.query.LanguageQueries;
import ch.iceage.icedms.persistence.jdbc.rowmapper.impl.LanguageRowMapper;

@Repository
public class JdbcLanguageMapper extends JdbcGenericMapper<Language, 
														  LanguageRowMapper, 
														  LanguageQueries> implements LanguageMapper {

	@Override
	@Autowired
	protected void init(LanguageQueries queries) {
		super.queries=queries;
    	super.mapperType=LanguageRowMapper.class;
    	super.tableName=LanguageQueries.TABLE_NAME;
    	super.tableAlias=LanguageQueries.TABLE_ALIAS;
	}

	@Override
	public void update(Language entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Language getByIsoCode(String isoCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Language getByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
