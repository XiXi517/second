/**
 * 
 */
package com.senlang.sdip.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author Mc.D
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LxUserInfo {
/*
 * {
           "name": "杨雪",
           "id": 1113,
           "path": "蓝信工场-研发部",
           "serialNumber":序号,
           "position":职位,
           "note":"",
           "mobile": "18612345678",
           "parentId": 111111,
           "companyId":594771
           "email": "yangxue@comisys.net",
           "secondPosition":"",
           "userUniId":"83191@34.uni1",
           "company": "蓝信工场"
           "busiTags":[],
           "posiTags":[],
           "contactExs":[],
           "orgName": "蓝信工场",
           "orgId": 1
        }
 * "code":"1-1-3",
 * "orgId":1,
 * "userUniId":"2761@1.chalco-lanxin",
 * 
 * "email":"",
 * "company":"中国铝业公司",
 * "orgName":"中国铝业公司",
 * 
 * "parentId":3,
 * "companyId":1,
 * "sex":0,
 * "secondPosition":"",
 * 
 * "busiTags":[],
 * "posiTags":[],
 * "contactExs":[],
 * 
 * "partyCommitteeDuty":"",
 * "administrativeDuty":"",
 * "politicsStatus":"",
 * "nation":"",
 * 
 * "mobile":"18600025740",
 * "position":"",
 * "serialNumber":"",
 * "note":"",
 * 
 * "name":"科大森浪测试",
 * "id":6601,
 * "path":"中国铝业公司-中国铝业公司测试"
        
 * */
	private String code;
	private long orgId;
	private String userUniId;
	
	private String email;
	private String company;
	private String orgName;
	
	private long parentId;
	private long companyId;
	private int sex;
	private String secondPosition;
	
	private String[] busiTags;
	private String[] posiTags;
	private String[] contactExs;
	
	private String partyCommitteeDuty;
	private String administrativeDuty;
	private String politicsStatus;
	private String nation;
	
	private String mobile;
	private String position;
	private String serialNumber;
	private String note;
	
	private String name;
	private long id;
	private String path;
}
