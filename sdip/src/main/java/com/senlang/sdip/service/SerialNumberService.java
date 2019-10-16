/**
 * 
 */
package com.senlang.sdip.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.senlang.sdip.dao.NoRepository;
import com.senlang.sdip.model.CommonDataResult;
import com.senlang.sdip.model.No;
import com.senlang.sdip.util.DateTime;
import com.senlang.sdip.util.StringHelper;

/**
 * @author Mc.D
 * @param <T>
 *
 */
@Service
public class SerialNumberService {
	private static final Logger logger = LogManager.getLogger(SerialNumberService.class);

	@Autowired(required = false)
	private NoRepository dbo;

	public CommonDataResult<String> NextNo(Class<?> clazz) {
		return this.NextNo(clazz.getSimpleName());
	}

	public CommonDataResult<String> NextNo(String resourceName) {
		CommonDataResult<String> ret = new CommonDataResult<>();
		List<No> nos = dbo.findByResourceName(
				new Sort(new Order(Direction.DESC, "year"), new Order(Direction.DESC, "month")), resourceName);
		if (nos.isEmpty()) {
			logger.error("未找到[" + resourceName + "]的流水号规则");
			ret.setErrmsg("未找到流水号规则！");
			ret.setErrorcode(-1);
			return ret;
		}

		No no = nos.get(0);

		DateTime dt = DateTime.now();
		if (no.getYear() != dt.getYear() || no.getMonth() > 0 && no.getMonth() != dt.getMonth()) {
			No newNo = new No();

			newNo.setStatus(no.getStatus());
			newNo.setResourceName(no.getResourceName());
			newNo.setPrefix(no.getPrefix());
			newNo.setTimeFormat(no.getTimeFormat());
			newNo.setExpr(no.getExpr());
			newNo.setNoLength(no.getNoLength());
			newNo.setDescription(no.getDescription());
			newNo.setYear(dt.getYear());
			newNo.setMonth(dt.getMonth());
			newNo.setUpdatedAt(null);
			newNo.setUpdatedBy(null);
			if (no.getTimeFormat().indexOf("M") > 0) {
				newNo.setDigitalNo(0);
			} else {
				newNo.setDigitalNo(no.getDigitalNo());
			}
			newNo.setFullNo("");
			newNo.setCreatedBy("SERIAL_NUMBER_SERVICE");
			newNo.setCreatedAt(dt.toDate());
			no = newNo;
		}

		String noTpl = "%0" + no.getNoLength() + "d";
		String fullNo = no.getExpr().replace("{prefix}", no.getPrefix())
				.replace("{time}",
						StringHelper.isNullOrWhiteSpace(no.getTimeFormat()) ? StringHelper.Empty
								: String.format(no.getTimeFormat().replace("yyyy", "%1$tY").replace("yy", "%1$ty")
										.replace("MM", "%1$tm").replace("dd", "%1$td").replace("HH", "%1$tH")
										.replace("mm", "%1$tM"), dt.toDate()))
				.replace("{digital_no}", String.format(noTpl, no.getDigitalNo() + 1));
		no.setFullNo(fullNo);
		no.setDigitalNo(no.getDigitalNo() + 1);
		no.setUpdatedAt(dt.toDate());
		no.setUpdatedBy("SERIAL_NUMBER_SERVICE");

		dbo.save(no);
		logger.info("生成" + resourceName + "流水号：" + fullNo);
		ret.setData(fullNo);
		return ret;
	}
}
