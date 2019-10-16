/**
 * 
 */
package com.senlang.sdip.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.senlang.sdip.annotation.Category;
import com.senlang.sdip.annotation.Privilege;
import com.senlang.sdip.model.PrivilegeMap;
import com.senlang.sdip.util.StringHelper;

/**
 * @author Mc.D
 *
 */
@Service
public class PrivilegeService {
	
	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;

	public List<PrivilegeMap> getPrivilegeMaps() {
		/**
		 * 模块 - 方法map
		 */
		List<PrivilegeMap> ret = new ArrayList<>();
		Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
		for (RequestMappingInfo info : map.keySet()) {
			Privilege modulePrivilege = map.get(info).getMethodAnnotation(Privilege.class);
			Category classPrivilege = map.get(info).getMethod().getDeclaringClass().getAnnotation(Category.class);
			if (modulePrivilege != null) {
				if (StringHelper.isNullOrEmpty(modulePrivilege.name()))
					throw new RuntimeException("使用:" + Privilege.class.getName() + " 注解类时,请配置 name ");
				// 方法名
				RequestMethodsRequestCondition methodCondition = info.getMethodsCondition();
				// 请求的url
				PatternsRequestCondition patternsCondition = info.getPatternsCondition();
				Iterator<RequestMethod> methods = methodCondition.getMethods().iterator();
				Iterator<String> patterns = patternsCondition.getPatterns().iterator();

				String method = "";
				String url = "";
				while (methods.hasNext()) {
					RequestMethod str = methods.next();
					method += str.name() + ",";
				}

				while (patterns.hasNext()) {
					String str = patterns.next();
					url = str.toString();
				}
				String category = modulePrivilege.category();

				category = StringHelper.isNullOrWhiteSpace(category)
						? classPrivilege != null && !StringHelper.isNullOrWhiteSpace(classPrivilege.name())
								? classPrivilege.name() : null
						: category;

				PrivilegeMap pm = new PrivilegeMap();
				pm.setName(modulePrivilege.name());
				pm.setUrl(url);
				pm.setMethod(method);
				pm.setCategory(category);
				ret.add(pm);
			}
		}
		return ret;
	}
}
