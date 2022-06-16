package com.skinnovation.citizen.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.skinnovation.citizen.notice.model.Notice;

@Mapper
public interface IfNoticeMMapper {

	@Select({
        "SELECT ",
        "UUID, SEQ, TITLE, DESCRIPTION",
        "FROM NOTICE",
        "WHERE UUID = #{uuid,jdbcType=VARCHAR}"
    })
	public Notice selectNotice(String uuid);
	
	@SelectProvider(type=IfNoticeMProvider.class, method="selectNotice")
	public List<Notice> selectAllNotice(Notice notice);
	
	
    @Insert({
        "INSERT INTO NOTICE (UUID, TITLE, DESCRIPTION) VALUES",
        "(#{uuid,jdbcType=VARCHAR}, #{title,jdbcType=NVARCHAR}, #{description,jdbcType=VARCHAR})"
    })
	public int insertNotice(Notice notice);
    
    @Update({
        "UPDATE NOTICE SET ", 
        "TITLE = #{title,jdbcType=NVARCHAR}, DESCRIPTION = #{description,jdbcType=VARCHAR} where UUID = #{uuid,jdbcType=VARCHAR}"
    })
	public int updateNotice(Notice notice);
    
    @Delete({
    	"DELETE FROM NOTICE WHERE UUID = #{uuid,jdbcType=VARCHAR}"
    })
    public int deleteNotice(String uuid);

}
