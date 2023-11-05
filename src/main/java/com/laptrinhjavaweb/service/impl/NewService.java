package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.converter.NewConverter;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.service.INewService;

@Service
public class NewService implements INewService {
    @Autowired
    private NewRepository newRepository;
    
    @Autowired
    private NewConverter newConverter;
    
    @Autowired
    private CategoryRepository CategoryRepository;
    
    
    @Override
    public List<NewDTO> findAll(Pageable pageable) {
    	List<NewDTO> models = new ArrayList<>();
    	List<NewEntity> entities = newRepository.findAll(pageable).getContent();
    	for(NewEntity item : entities) {
    		NewDTO newDTO = newConverter.toNewDto(item);
    		models.add(newDTO);
    	}
        return models;
    }

	@Override
	public int getTotalItem() {
		return (int) newRepository.count();
	}

	@Override
	public NewDTO findById(long id) {
		NewEntity entity = newRepository.findOne(id);
		return newConverter.toNewDto(entity);
	}

	@Override
	@Transactional
	public NewDTO insert(NewDTO dto) {
		CategoryEntity category = CategoryRepository.findOneByCode(dto.getCategoryCode());
		NewEntity entity = newConverter.toNewEntity(dto);
		entity.setCategory(category);
		return newConverter.toNewDto(newRepository.save(entity));
	}

	@Override
	@Transactional
	public NewDTO update(NewDTO dto) {
		NewEntity oldNew = newRepository.findOne(dto.getId());
		CategoryEntity category = CategoryRepository.findOneByCode(dto.getCategoryCode());
		oldNew.setCategory(category);
		NewEntity updateNew = newConverter.toNewEntity(oldNew, dto);
		return newConverter.toNewDto(newRepository.save(updateNew));
	}

	@Override
	@Transactional
	public NewDTO save(NewDTO dto) {
		CategoryEntity category = CategoryRepository.findOneByCode(dto.getCategoryCode());
		NewEntity entity ;
		if(dto.getId() != null) {
		NewEntity oldNew = newRepository.findOne(dto.getId());
		oldNew.setCategory(category);
		entity = newConverter.toNewEntity(oldNew, dto);
		}else {
		entity = newConverter.toNewEntity(dto);	
		entity.setCategory(category);
		}
		return newConverter.toNewDto(newRepository.save(entity));
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for(long id : ids) {
			newRepository.delete(id);
		}
	}
}
