/**
 *
 */
package com.senlang.sdip.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.senlang.sdip.model.base.ICode;
import com.senlang.sdip.service.JsonResultFactory;
import com.senlang.sdip.service.SerialNumberService;
import com.senlang.sdip.util.Convert;
import com.senlang.sdip.util.JwtTokenUtil;

/**
 * @author Mc.D
 */
public abstract class CommonController {

    @Autowired(required = false)
    private SerialNumberService serialNumberService;

    @Value("${jwt.header:Authorization}")
    private String tokenHeader;

    @Value("${jwt.tokenHead:Bearer }")
    private String tokenHead;

    protected String CurrentUserCode;
    // protected String CurrentUserName;
    protected String CurrentOrgId;

    // private JwtTokenUtil jwtTokenUtil;

    protected final Logger logger = LogManager.getLogger(this.getClass());

    protected HttpServletRequest Request;
    protected HttpServletResponse Response;
    protected HttpSession Session;

    @Autowired(required = false)
    private JwtTokenUtil jwtTokenUtil;

    @ModelAttribute
    protected void init(HttpServletRequest request, HttpSession session, HttpServletResponse response) {

        String authHeader = request.getHeader(this.tokenHeader);
        if (jwtTokenUtil != null && authHeader != null && authHeader.startsWith(tokenHead)) {
            final String authToken = authHeader.substring(tokenHead.length()).trim();
            // The part after "Bearer "
            this.CurrentUserCode = jwtTokenUtil.getOpenidFromToken(authToken);
        } else if (request.getHeader("open-id") != null) {
            this.CurrentUserCode = request.getHeader("open-id");
        }

        this.Request = request;
        this.Session = session;
        this.Response = response;
    }

    @ExceptionHandler
    public CommonResult exception(Exception e) {
        e.printStackTrace();
        logger.error("检测到未处理的错误：" + e.getMessage(), e);
        return JsonResultFactory.getCommonResult(501);
    }

    protected PageRequest getPageRequest() {
        int iPageSize = getQueryParam("pageSize").tryToInt().getValueOrDefault(20);
        int iPageNo = getQueryParam("pageNo").tryToInt().getValueOrDefault(0);
        return new PageRequest(iPageNo < 0 ? 0 : iPageNo, iPageSize < 0 ? 20 : iPageSize,
                new Sort(Direction.DESC, "id"));
    }

    protected Convert<String> getQueryParam(String name) {
        String qs = this.Request.getQueryString();
        if (qs == null) {
            return Convert.of(null);
        }
        String[] params = qs.split("&");
        for (String param : params) {
            if (param.startsWith(name)) {
                String[] spStr = param.split("=");
                return Convert.of(spStr.length < 2 ? null : spStr[1]);
            }
        }
        return Convert.of(null);
    }

    protected CommonResult setSerialNumberCode(ICode model) {
        if (this.serialNumberService == null) {
            return JsonResultFactory.getCommonResult("请声明SerialNumberService Bean");
        }
        CommonDataResult<String> ret = serialNumberService.NextNo(model.getClass());
        if (ret.getErrorcode() != 0) {
            return JsonResultFactory.getCommonResult(ret.getErrmsg());
        }
        model.setCode(ret.getData());
        return JsonResultFactory.getOkCommonResult();
    }
}
