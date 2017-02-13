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

import in.koyad.piston.app.steam.forms.ContentPluginForm;
import in.koyad.piston.app.steam.model.SteamModelCache;
import in.koyad.piston.app.steam.sdk.api.ContentService;
import in.koyad.piston.app.steam.sdk.impl.ContentImpl;
import in.koyad.piston.common.constants.MsgType;
import in.koyad.piston.common.exceptions.FrameworkException;
import in.koyad.piston.common.utils.LogUtil;
import in.koyad.piston.common.utils.Message;
import in.koyad.piston.controller.plugin.PluginAction;
import in.koyad.piston.controller.plugin.annotations.AnnoPluginAction;
import in.koyad.piston.ui.utils.FormUtils;
import in.koyad.piston.ui.utils.RequestContextUtil;
import in.koyad.piston.ui.utils.RequestDispatcher;
import in.koyad.piston.ui.utils.TileUtil;

@AnnoPluginAction(
	name = UpdateBootstrapContentPluginAction.ACTION_NAME
)
public class UpdateBootstrapContentPluginAction extends PluginAction {
	
	public static final String ACTION_NAME = "updateBootstrapContent";
	
	private final ContentService contentService = new ContentImpl();

	private static final LogUtil LOGGER = LogUtil.getLogger(UpdateBootstrapContentPluginAction.class);
	
	@Override
	protected String execute() throws FrameworkException {
		LOGGER.enterMethod("execute");

		ContentPluginForm form = null;
		try {
			form = FormUtils.createFormWithReqParams(ContentPluginForm.class);
			contentService.updateContent(TileUtil.getTileId(), form.getContent());
			
			//clear cache
			SteamModelCache.contents.remove(TileUtil.getTileId());
			
			RequestContextUtil.setRequestAttribute("msg", new Message(MsgType.INFO, "Content updated successfully."));
		} catch(FrameworkException ex) {
			LOGGER.logException(ex);
			RequestContextUtil.setRequestAttribute("msg", new Message(MsgType.ERROR, "Error occured while updating site details."));
		}
		
		RequestContextUtil.setRequestAttribute(ContentPluginForm.FORM_NAME, form);
		
		LOGGER.exitMethod("execute");
//		return "/pages/content.xml";
		return RequestDispatcher.forwardAction(GetBootstrapContentPluginAction.ACTION_NAME);
//		return FrameworkConstants.PREFIX_FORWARD + GetMarkdownContentPluginAction.ACTION_NAME;
	}
	
}
