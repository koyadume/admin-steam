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
package in.koyad.piston.app.steam.actions;

import com.google.common.html.HtmlEscapers;

import in.koyad.piston.app.steam.model.SteamModelCache;
import in.koyad.piston.common.exceptions.FrameworkException;
import in.koyad.piston.common.utils.LogUtil;
import in.koyad.piston.controller.plugin.PluginAction;
import in.koyad.piston.controller.plugin.annotations.AnnoPluginAction;
import in.koyad.piston.ui.utils.RequestContextUtil;
import in.koyad.piston.ui.utils.TileUtil;

@AnnoPluginAction(
	name = GetBootstrapContentPluginAction.ACTION_NAME
)
public class GetBootstrapContentPluginAction extends PluginAction {
	
	public static final String ACTION_NAME = "getBootstrapContent";
	
	private static final LogUtil LOGGER = LogUtil.getLogger(GetBootstrapContentPluginAction.class);
	
	@Override
	protected String execute() throws FrameworkException {
		LOGGER.enterMethod("execute");
		
		String content = SteamModelCache.contents.get(TileUtil.getTileId());
		RequestContextUtil.setRequestAttribute("content", HtmlEscapers.htmlEscaper().escape(content));
		
		LOGGER.exitMethod("execute");
		return "/pages/bootstrapContent.xml";
	}
	
}
