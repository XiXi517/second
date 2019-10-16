/**
 * 
 */
package com.senlang.sdip.model;

import lombok.Data;

/**
 * @author Mc.D
 *
 */
@Data
public class PermissionPrivilege {
	private String url;
	private boolean isAuth;
	private String method;
	private String category;
	private String privilege;
}
