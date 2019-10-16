/**
 * 
 */
package com.senlang.sdip.model.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.senlang.sdip.model.PrivilegeMap;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Mc.D
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasePermission extends PrivilegeMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5325805682538664508L;
	
	private String descritpion;
	private int pid;

	private String serviceId;
}
