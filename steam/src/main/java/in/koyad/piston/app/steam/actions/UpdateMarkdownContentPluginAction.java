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
package in.koyad.piston.app.steam.actions;

import in.koyad.piston.app.api.annotation.AnnoPluginAction;
import in.koyad.piston.app.api.model.Request;
import in.koyad.piston.app.api.model.Response;
import in.koyad.piston.app.api.plugin.BasePluginAction;
import in.koyad.piston.app.steam.forms.ContentPluginForm;
import in.koyad.piston.app.steam.model.SteamModelCache;
import in.koyad.piston.app.steam.sdk.api.ContentClient;
import in.koyad.piston.app.steam.sdk.impl.ContentClientImpl;
import in.koyad.piston.common.basic.exception.FrameworkException;
import in.koyad.piston.common.constants.MsgType;
import in.koyad.piston.common.util.LogUtil;
import in.koyad.piston.common.util.Message;
import in.koyad.piston.ui.utils.RequestDispatcher;
import in.koyad.piston.ui.utils.TileUtil;

@AnnoPluginAction(
	name = UpdateMarkdownContentPluginAction.ACTION_NAME
)
public class UpdateMarkdownContentPluginAction extends BasePluginAction {
	
	public static final String ACTION_NAME = "updateContent";
	
	private final ContentClient contentService = new ContentClientImpl();

	private static final LogUtil LOGGER = LogUtil.getLogger(UpdateMarkdownContentPluginAction.class);
	
	@Override
	public String execute(Request req, Response resp) throws FrameworkException {
		LOGGER.enterMethod("execute");

		ContentPluginForm form = null;
		try {
			form = req.getPluginForm(ContentPluginForm.class);
			contentService.updateContent(TileUtil.getTileId(), form.getContent());
			
			//clear cache
			SteamModelCache.contents.remove(TileUtil.getTileId());
			
			req.setAttribute("msg", new Message(MsgType.INFO, "Content updated successfully."));
		} catch(FrameworkException ex) {
			LOGGER.logException(ex);
			req.setAttribute("msg", new Message(MsgType.ERROR, "Error occured while updating site details."));
		}
		
		req.setAttribute(ContentPluginForm.FORM_NAME, form);
		
		LOGGER.exitMethod("execute");
//		return "/content.xml";
//		return FrameworkConstants.PREFIX_FORWARD + GetMarkdownContentPluginAction.ACTION_NAME;
		return RequestDispatcher.forwardAction(GetMarkdownContentPluginAction.ACTION_NAME);
	}
	
}
