/*
 * Copyright (c) 2012-2017 Shailendra Singh <shailendra_01@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.koyad.piston.app.steam.sdk.impl;

import java.text.MessageFormat;

import javax.ws.rs.client.Entity;

import in.koyad.piston.app.steam.sdk.api.ContentClient;
import in.koyad.piston.common.basic.exception.FrameworkException;
import in.koyad.piston.common.util.LogUtil;
import in.koyad.piston.common.util.rest.AbstractREST;
import in.koyad.piston.common.util.rest.RestServiceUtil;

public class ContentClientImpl extends AbstractREST implements ContentClient {
	
	private static final LogUtil LOGGER = LogUtil.getLogger(ContentClientImpl.class);
	
//	private static final String ROOT_RESOURCE = MessageFormat.format("http://{0}/steam-service/{1}/content", 
//														SERVICE_HOST.concat(":").concat(String.valueOf(SERVICE_PORT)),
//														ServiceConstants.VERSION);
	
	private static final String ROOT_RESOURCE = MessageFormat.format("{0}/steam-service/v3.0", 
											System.getenv(ClientConstants.HOST_STEAM_SERVICE));

	@Override
	public void updateContent(String tileId, String content) throws FrameworkException {
		LOGGER.enterMethod("updateContent");
		
		try {
//			String resource = MessageFormat.format("/steam-service/{0}/content/{1}", ServiceConstants.VERSION, tileId);
//			put(resource, content, MediaType.TEXT_PLAIN);
			
			RestServiceUtil.getClient()
				.target(ROOT_RESOURCE)
				.path(tileId)
				.request()
				.put(Entity.text(content));
		} catch(Exception ex) {
			LOGGER.logException(ex);
			throw new FrameworkException(ex.getMessage());
		}
		
		LOGGER.exitMethod("updateContent");
	}

	@Override
	public String getContent(String tileId) throws FrameworkException {
		LOGGER.enterMethod("getContent");
		
		String content = null;
		try {
//			String resource = MessageFormat.format("/steam-service/{0}/content/{1}", ServiceConstants.VERSION, tileId);
//			content = get(resource, String.class);
			
			content = RestServiceUtil.getClient()
						.target(ROOT_RESOURCE)
						.path("content").path(tileId)
						.request()
						.get(String.class);
		} catch(Exception ex) {
			LOGGER.logException(ex);
			throw new FrameworkException(ex.getMessage());
		}
		
		LOGGER.exitMethod("getContent");
		return content;
	}


}
