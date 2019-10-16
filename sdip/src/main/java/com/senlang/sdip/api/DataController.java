/**
 * 
 */
package com.senlang.sdip.api;

import com.senlang.sdip.service.JsonResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author Mc.D
 *
 */
@RequestMapping("/api/common")
@RestController
public class DataController {

	private final RestTemplate r;

	@Autowired(required = false)
	public DataController(RestTemplate r) {
		if (r == null) {
			this.r = new RestTemplate();
		} else {
			this.r = r;
		}
	}

	@GetMapping("/user")
	public Object getUser(@RequestParam String code) {
		try {
			String str = r.getForObject("http://zl-portal/api/user/code?code=" + code, String.class);
			return str;
		} catch (Exception e) {
			return JsonResultFactory.getCommonResult(e.getMessage());
		}
	}
	
	@GetMapping("/card")
	public Object getUserCard(@RequestParam String cardId) {
		try {
			String str = r.getForObject("http://zl-portal/api/user/card?cardId=" + cardId, String.class);
			return str;
		} catch (Exception e) {
			return JsonResultFactory.getCommonResult(e.getMessage());
		}
	}

	@GetMapping("/user/all")
	public Object allUser() {
		try {
			String str = r.getForObject("http://zl-portal/api/user/valid-users-all", String.class);
			return str;
		} catch (Exception e) {
			return JsonResultFactory.getCommonResult(e.getMessage());
		}
	}

	@GetMapping("/org/{id}")
	public Object getOrg(@PathVariable long id) {
		try {
			String str = r.getForObject("http://zl-portal/api/organization/" + id, String.class);
			return str;
		} catch (Exception e) {
			return JsonResultFactory.getCommonResult(e.getMessage());
		}
	}

	@GetMapping("/org/all")
	public Object allOrg() {
		try {
			String str = r.getForObject("http://zl-portal/api/organization/used", String.class);
			return str;
		} catch (Exception e) {
			return JsonResultFactory.getCommonResult(e.getMessage());
		}
	}

	@GetMapping("/org/list")
	public Object orgList() {
		try {
			String str = r.getForObject("http://zl-portal/api/organization/used/list", String.class);
			return str;
		} catch (Exception e) {
			return JsonResultFactory.getCommonResult(e.getMessage());
		}
	}
}
