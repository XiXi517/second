/**
 * 
 */
package com.senlang.sdip.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.senlang.sdip.model.No;

/**
 * @author Mc.D
 *
 */
public interface NoRepository extends PagingAndSortingRepository<No, Long>{
	List<No> findByResourceName(Sort sort,String resourceName);
}
