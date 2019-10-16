/**
 * 
 */
package com.senlang.sdip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.senlang.sdip.model.CommonDataResult;
import com.senlang.sdip.model.CommonResult;
import com.senlang.sdip.model.base.BaseUser;

/**
 * @author Mc.D
 *
 */
@Service
public class RemindActivity {

	@Autowired(required = false)
	private RestTemplate rt;

	@Autowired(required = false)
	private ObjectMapper om;

	public boolean sendRemind(String openid, String content) {
		if (rt == null) {
			rt = new RestTemplate();
		}

		if (om == null) {
			om = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
					.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		}

		try {
			String userStr = rt.getForObject("http://zl-portal/api/user/code?code=" + openid, String.class);

			CommonDataResult<BaseUser> user = om.readValue(userStr, new TypeReference<CommonDataResult<BaseUser>>() {
			});

			CommonResult ret = rt.postForObject("http://zl-lxapi/api/message?code=" + user.getData().getMobile(),
					content, CommonResult.class);
			return ret.getErrorcode() == 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
