/**
 * 
 */
package com.senlang.sdip.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senlang.sdip.service.PrivilegeService;

/**
 * @author Mc.D
 *
 */
@RestController
@RequestMapping("/api/privilegemapping")
public class PrivilegeMappingController {

	@Autowired(required = false)
	private PrivilegeService ps;

	@RequestMapping
	public Object get() {
		return ps.getPrivilegeMaps();
	}
}
