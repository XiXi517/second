/**
 * 
 */
package com.senlang.sdip.model;

import java.util.List;

import com.senlang.sdip.model.LxUserInfo;
import com.senlang.sdip.model.base.BaseOrganization;

import lombok.Data;

/**
 * @author Mc.D
 *
 */
@Data
public class LxStructInfo {
	private long errcode;
	private List<LxUserInfo> openOrgMemberList;
	private List<BaseOrganization> openOrgStructList;
	private String errmsg;
}
