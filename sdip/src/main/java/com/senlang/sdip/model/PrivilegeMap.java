/**
 * 
 */
package com.senlang.sdip.model;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Mc.D
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@MappedSuperclass
public class PrivilegeMap extends Dbo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7534453649669084113L;
	private String url;
	private String name;
	private String method;
	private String category;
}
