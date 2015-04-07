package com.zhch.andex.pulltorefresh;

import java.util.List;

import com.zhch.andex.common.vo.ResponseBase;

public class MediaSimpleResp extends ResponseBase {
	public MediaSimpleList data;
	public class MediaSimpleList{
		public List<MediaSimple> pindashboards;
		public int number;
		public int limit;
		public long startId;
	}
}

