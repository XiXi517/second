/**
 *
 */
package com.senlang.sdip.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @author Mc.D
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonDataResult<T> extends CommonResult {
    // private long errorcode;
    // private String errmsg;
    private T data;

    public Map<String, Object> inject() {
        Map<String, Object> map = super.inject();
        map.put("data", data);
        return map;
    }
}
