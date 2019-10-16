/**
 * 
 */
package com.senlang.sdip.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.querydsl.core.annotations.QuerySupertype;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Mc.D
 *
 */
@Data
@MappedSuperclass
@QuerySupertype
public abstract class Dbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3074227123732908310L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;

	protected Date createdAt;
	protected Date updatedAt;
	protected String createdBy;
	protected String updatedBy;
}
