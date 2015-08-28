/*
 * Copyright (c) 2012-2015 Shailendra Singh <shailendra_01@outlook.com>
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

import java.net.URISyntaxException;
import java.text.MessageFormat;

import in.koyad.piston.app.steam.sdk.api.ContentService;
import in.koyad.piston.common.exceptions.FrameworkException;
import in.koyad.piston.common.utils.LogUtil;

public class ContentImpl extends AbstractREST implements ContentService {
	
	private static final LogUtil LOGGER = LogUtil.getLogger(ContentImpl.class);

	@Override
	public void updateContent(String tileId, String content) throws FrameworkException {
		LOGGER.enterMethod("updateContent");
		
		try {
			String resource = MessageFormat.format("/steam-service/v1/content/{tileId}", tileId);
			put(resource, content);
		} catch(URISyntaxException ex) {
			LOGGER.logException(ex);
		}
		
		LOGGER.exitMethod("updateContent");
	}

	@Override
	public String getContent(String tileId) throws FrameworkException {
		LOGGER.enterMethod("getContent");
		
		String content = null;
		try {
			String resource = MessageFormat.format("/steam-service/v1/content/{tileId}", tileId);
			content = get(resource, String.class);
		} catch(URISyntaxException ex) {
			LOGGER.logException(ex);
		}
		
		LOGGER.exitMethod("getContent");
		return content;
	}


}
