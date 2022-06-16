package com.skinnovation.citizen.bcm.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skinnovation.citizen.bcm.BCMCommon;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Api
@RequestMapping("bcm")
public class BCMSampleController {
	private final BCMCommon common;
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/commoncode-mng/{groupId}")
    public ResponseEntity<List> commmonCode(@PathVariable String groupId, @RequestHeader MultiValueMap<String, String> headers) throws Exception {
		Map<String, String> param = new HashMap<>();
		param.put("groupId", groupId);
		
		ResponseEntity<List> response = common.getBcmService("/api/bcm/v2/commoncode-mng/{groupId}", headers, List.class, param);
		
		List<Map<String,String>> body = response.getBody();
		List<Map<String, String>> refined = body.stream().map(
					m -> m.entrySet()
					.stream()
					.map(e -> {
						if(e.getValue() == null) {
							e.setValue("");
						}
						return e;
					}).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
				).collect(Collectors.toList());	
		
		return ResponseEntity.ok(refined);
		
    }
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/commoncode-mng/{groupId}/{codeId}")
    public ResponseEntity<Map> commmonCode(@PathVariable String groupId, @PathVariable String codeId, @RequestHeader MultiValueMap<String, String> headers) throws Exception {
		Map<String, String> param = new HashMap<>();
		param.put("groupId", groupId);
		param.put("codeId", codeId);
		
		return common.getBcmService("/api/bcm/v2/commoncode-mng/{groupId}/{codeId}", headers, Map.class, param);
    }	
}
