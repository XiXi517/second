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
public class BaseOrganization extends Dbo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3311877269696408042L;

	private String path;

	/**
	 * 对应蓝信的OrgId、ParentId！！！！！
	 */
	private String code;

	private String name;
	
	private BaseOrganization parent;

}
