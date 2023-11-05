package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.NewEntity;

@Component
public class NewConverter {
	
	public NewDTO toNewDto(NewEntity entity) {
		NewDTO result = new NewDTO();
		result.setId(entity.getId());
		result.setTitle(entity.getTitle());
		result.setContent(entity.getContent());
		result.setShortDescription(entity.getShortDescription());
		result.setThumbnail(entity.getThumbnail());
		result.setCategoryCode(entity.getCategory().getCode());
		return result;
	}
	
	public NewEntity toNewEntity(NewDTO dto) {
		NewEntity result = new NewEntity();
		result.setTitle(dto.getTitle());
		result.setContent(dto.getContent());
		result.setShortDescription(dto.getShortDescription());
		result.setThumbnail(dto.getThumbnail());
		return result;
	}
	
	public NewEntity toNewEntity(NewEntity result , NewDTO dto) {
		result.setTitle(dto.getTitle());
		result.setContent(dto.getContent());
		result.setShortDescription(dto.getShortDescription());
		result.setThumbnail(dto.getThumbnail());
		return result;
	}
}
