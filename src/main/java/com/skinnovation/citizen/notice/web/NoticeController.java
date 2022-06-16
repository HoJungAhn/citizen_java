package com.skinnovation.citizen.notice.web;

import com.skinnovation.citizen.notice.dao.IfNoticeMMapper;
import com.skinnovation.citizen.notice.model.Notice;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Api
public class NoticeController {

	private final IfNoticeMMapper noticeMapper;
	
	@PostMapping("/entities")
	public ResponseEntity<Notice> createNotice(@RequestBody Notice notice){
		notice.setUuid(UUID.randomUUID().toString());
		noticeMapper.insertNotice(notice);
		return ResponseEntity.ok(notice);
	}

	@GetMapping("/entities")
	public ResponseEntity<List<Notice>> getAllNoticeList(HttpSession session){
		
		return Optional.ofNullable(noticeMapper.selectAllNotice(null))
				.map(notice -> ResponseEntity.ok(notice))
				.orElse(ResponseEntity.noContent().build());
	}
	
	@GetMapping("/entities/{uuid}")
	public ResponseEntity<Notice> getNoticeList(@PathVariable String uuid, HttpSession session) throws Exception{
		return Optional.ofNullable(noticeMapper.selectNotice(uuid))
				.map(notice -> ResponseEntity.ok(notice))
				.orElse(ResponseEntity.noContent().build());
				
	}
	
	@PostMapping("/entities/{uuid}")
	public ResponseEntity<Notice> modifyNoticeList(@PathVariable String uuid, @RequestBody Notice notice) throws Exception{
		notice.setUuid(uuid);
		noticeMapper.updateNotice(notice);
		return ResponseEntity.ok(notice);
	}	
	
	@DeleteMapping("/entities/{uuid}")
	public ResponseEntity<Notice> deleteNotice(@PathVariable String uuid) throws Exception{
		noticeMapper.deleteNotice(uuid);
		Notice notice = new Notice();
		notice.setUuid(uuid);
		return ResponseEntity.noContent().build();
	}	
}
