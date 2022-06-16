package com.skinnovation.citizen.notice.dao;

import org.apache.ibatis.jdbc.SQL;

import com.skinnovation.citizen.notice.model.Notice;

public class IfNoticeMProvider {
/**
 * 	UUID 		CHAR(36) NOT NULL
  , TITLE		VARCHAR(50) NOT NULL 
  , DESCRIPTION 	VARCHAR(100)
  
 * @param notice
 * @return
 */
	public String selectNotice(Notice notice) {
		SQL sql = new SQL();
		sql.SELECT("UUID");
		sql.SELECT("SEQ");		
		sql.SELECT("TITLE");
		sql.SELECT("DESCRIPTION");
		sql.FROM("NOTICE");
		if (notice != null) {
			sql.WHERE("UUID = #{uuid}");
		}
		return sql.toString();
	}
}
