/**
 * 
 */
package com.senlang.sdip.model.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.senlang.sdip.model.Dbo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Mc.D
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseRole extends Dbo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3790654041837525003L;

	private String name;
	
	private boolean isSystem;
	
	private BaseOrganization organization;
}
