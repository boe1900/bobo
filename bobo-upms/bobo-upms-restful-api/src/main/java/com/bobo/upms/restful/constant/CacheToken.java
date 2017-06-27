/**
 * Copyright (c) 2011-2014, hubin (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.bobo.upms.restful.constant;

import java.io.Serializable;

/**
 * 缓存 Token 信息
 */
public class CacheToken implements Serializable{

	private static final long serialVersionUID = -8523855466899815562L;
	
	private String appId;// 应用ID 签名使用
	private String uid;// 用户ID
	private String token;// 一次性票据
	private String aesKey;// AES密钥

	public CacheToken(String appId, String uid, String token, String aesKey) {
		this.appId = appId;
		this.uid = uid;
		this.token = token;
		this.aesKey = aesKey;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAesKey() {
		return aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}

	public String getAppid() {
		return appId;
	}

	public void setAppid(String appId) {
		this.appId = appId;
	}

}
