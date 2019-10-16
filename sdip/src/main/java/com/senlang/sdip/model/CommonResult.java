/**
 *
 */
package com.senlang.sdip.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mc.D
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonResult {
    private int errorcode;
    private String errmsg;

    public Map<String, Object> inject() {
        Map<String, Object> map = new HashMap<>();
        map.put("errorcode", errorcode);
        map.put("errmsg", errmsg);
        return map;
    }
}
