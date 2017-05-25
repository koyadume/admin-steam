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
package in.koyad.piston.app.steam.service.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import in.koyad.piston.app.steam.service.model.Document;
import in.koyad.piston.app.steam.service.utils.DBConstants;
import in.koyad.piston.common.basic.exception.FrameworkException;
import in.koyad.piston.common.util.LogUtil;
import in.koyad.piston.dao.GlobalDAO;

@Path("content")
public class ContentResource {
	
	private static final LogUtil LOGGER = LogUtil.getLogger(ContentResource.class);

	private final GlobalDAO globalDAO = new GlobalDAO(DBConstants.PERSISTENT_UNIT_STEAM);
	
	@GET
	@Path("hello/{msg}")
	@Produces(MediaType.TEXT_PLAIN)
	public String test(@PathParam("msg") String msg) throws FrameworkException {
		return "hello ".concat(msg);	
	}
	
	@GET
	@Path("{tileId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getContent(@PathParam("tileId") String tileId) throws FrameworkException {
		LOGGER.enterMethod("getContent");
		
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", tileId);
		
		List values = globalDAO.getSingleColValues(Document.class, Document.COL_CONTENT, conditions);
		if(values.size() > 0) {
			return (String)values.get(0);
		}
		
		LOGGER.exitMethod("getContent");
		return "";
	}
	
	@PUT
	@Path("{tileId}")
	@Consumes(MediaType.TEXT_PLAIN)
	public void updateContent(@PathParam("tileId") String tileId, String content) throws FrameworkException {
		LOGGER.enterMethod("updateContent");
		
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", tileId);
		
		if(null == globalDAO.get(Document.class, tileId)) {
			Document document = new Document();
			document.setId(tileId);
			globalDAO.insert(document);
		}

		globalDAO.updateSingleColumn(Document.class, Document.COL_CONTENT, content, conditions);
		
		LOGGER.exitMethod("updateContent");
	}
	
}
