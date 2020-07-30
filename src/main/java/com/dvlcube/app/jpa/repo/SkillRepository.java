package com.dvlcube.app.jpa.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	Optional<SkillBean> findByName(String name);
	
	boolean existsSkillBeanByName(String name);
	
	@Query("SELECT obj FROM SkillBean obj ORDER BY obj.name ASC")
	Iterable<SkillBean> findAllOrderName();
	
	@Query("SELECT obj FROM SkillBean obj WHERE obj.name LIKE %:name%")
	List<SkillBean> findByNameLike(@Param("name") String name);
	
}
