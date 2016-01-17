/*
 * Copyright (c) 2012-2016 Shailendra Singh <shailendra_01@outlook.com>
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
package in.koyad.piston.app.steam.service.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import in.koyad.piston.app.steam.sdk.api.ContentService;
import in.koyad.piston.app.steam.service.impl.ContentServiceImpl;
import in.koyad.piston.common.exceptions.FrameworkException;
import in.koyad.piston.common.utils.LogUtil;

@Path("/content")
public class ContentResource {
	
	private static final LogUtil LOGGER = LogUtil.getLogger(ContentResource.class);

	private static final ContentService contentService = new ContentServiceImpl();
	
	@PUT
	@Path("{tileId}")
	@Consumes(MediaType.TEXT_PLAIN)
	public void updateContent(@PathParam("tileId") String tileId, String content) throws FrameworkException {
		LOGGER.enterMethod("updateContent");
		
		contentService.updateContent(tileId, content);
		
		LOGGER.exitMethod("updateContent");
	}
	
	@GET
	@Path("{tileId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getContent(@PathParam("tileId") String tileId) throws FrameworkException {
		LOGGER.enterMethod("getContent");
		
		String content = contentService.getContent(tileId);
		
		LOGGER.exitMethod("getContent");
		return content;
	}
	
}
