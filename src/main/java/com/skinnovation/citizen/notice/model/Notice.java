package com.skinnovation.citizen.notice.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@Alias("noticeM")
public class Notice implements Serializable{
	private String uuid;
	private String seq;
	private String title;
	private String description;
}
