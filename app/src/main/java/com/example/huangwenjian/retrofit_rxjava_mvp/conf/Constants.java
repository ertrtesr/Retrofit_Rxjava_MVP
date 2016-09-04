package com.example.huangwenjian.retrofit_rxjava_mvp.conf;

import com.example.huangwenjian.retrofit_rxjava_mvp.utils.LogUtils;

/**
 * @author  Administrator
 * @time 	2015-7-15 上午11:05:12
 * @des	TODO
 *
 * @version $Rev: 46 $
 * @updateAuthor $Author: admin $
 * @updateDate $Date: 2015-07-20 09:16:55 +0800 (星期一, 20 七月 2015) $
 * @updateDes TODO
 */
public class Constants {

	public static final int	DEBUGLEVEL		= LogUtils.LEVEL_ALL;	// LEVEL_ALL,显示所有的日子,OFF:关闭日志的显示
	public static final int	PAGESIZE		= 20;
	public static final int	PROTOCOLTIMEOUT	= 5 * 60 * 1000;		// 5分钟

	public static final class URLS {
		public static final String	BASEURL			= "http://192.168.1.100:8080/GooglePlayServer/";
		// http://localhost:8080/GooglePlayServer/image?name=
		public static final String	IMAGEBASEURL	= BASEURL + "image?name=";
		public static final String	DOWNLOADBASEURL	= BASEURL + "download";
		public static final String	HTTP			= "HTTP";
	}

	public static final class PAY {

	}

	public static final class REQ {

	}

	public static final class RES {

	}

}
