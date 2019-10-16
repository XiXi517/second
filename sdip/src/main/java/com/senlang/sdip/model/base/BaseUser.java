/**
 * 
 */
package com.senlang.sdip.model.base;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.senlang.sdip.model.Dbo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Mc.D
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseUser extends Dbo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2419668983804274858L;

	private List<BaseRole> roles;

	private String mobile;

	private String openid;

	private String cardId;

	private String name;

	private String code;

	private List<BaseOrganization> organizations;
}
