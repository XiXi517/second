/**
 * 
 */
package com.senlang.sdip.model;

import javax.persistence.Entity;

import com.senlang.sdip.model.Dbo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Mc.D
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
public class No extends Dbo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7402865406962095014L;
	
	private int status;
	private int year;
	private int month;
	private String description;
	private String resourceName;
	private String expr;
	private String prefix;
	private String timeFormat;
	private long digitalNo;
	private int noLength;
	private String fullNo;
}
