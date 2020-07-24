package com.dvlcube.app.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dvlcube.app.jpa.BasicRepository;
import com.dvlcube.app.jpa.DvlRepository;
import com.dvlcube.app.manager.data.SkillBean;

/**
 * @since 4 de jun de 2019
 * @author Ulisses Lima
 */
@Repository
public interface SkillRepository extends DvlRepository<SkillBean, Long>, BasicRepository<SkillBean, Long> {
	
	@Query("SELECT sb FROM SkillBean sb ORDER BY sb.name ASC")
	Iterable<SkillBean> findAllOrderName();
	
	List<SkillBean> findByName(String name);
	
}
