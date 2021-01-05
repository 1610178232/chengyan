package com.example.psycholog.module.until;

import java.util.Map;

public class ParamUtils {
	/** 将参数中的pageIndex和pageSize转换成sql语句中limit所需的值 */
	public static void formatPageParam(Map<String,Object> param) {
		if(param.get("pageIndex") != null && !"".equals(param.get("pageIndex").toString())) {
    		int pageIndex = Integer.parseInt(param.get("pageIndex").toString());
        	int pageSize = Integer.parseInt(param.get("pageSize").toString());
        	pageIndex = (pageIndex - 1) * pageSize;
        	param.put("pageIndex", pageIndex);
    	}
	}
}
