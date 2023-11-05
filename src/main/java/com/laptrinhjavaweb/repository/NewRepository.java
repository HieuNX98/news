package com.laptrinhjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.NewEntity;
//													<Khai báo entity, Khai báo kiểu dữ liệu của khoá chính>	
public interface NewRepository extends JpaRepository<NewEntity, Long> {

}
