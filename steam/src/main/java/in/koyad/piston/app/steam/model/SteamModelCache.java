package in.koyad.piston.app.steam.model;

import java.util.Map;

import in.koyad.piston.app.steam.service.api.ContentService;
import in.koyad.piston.common.exceptions.FrameworkException;
import in.koyad.piston.common.utils.LoadingMap;
import in.koyad.piston.common.utils.LoadingMap.Loader;
import in.koyad.piston.common.utils.LogUtil;
import in.koyad.piston.core.service.utils.ServiceManager;

public class SteamModelCache {
	
	private static final ContentService contentService = ServiceManager.getService(ContentService.class);
	
	private static final LogUtil LOGGER = LogUtil.getLogger(SteamModelCache.class);
	
	/*
	 * key - tile id
	 */
	public static final LoadingMap<String, String> contents = new LoadingMap<>(new Loader<String, String>() {
															@Override
															public String load(String tileId) throws FrameworkException {
																LOGGER.enterMethod("load");
																
																String content = contentService.getContent(tileId);
																
																LOGGER.enterMethod("load");
																return content;
															}

															@Override
															public Map<String, String> loadAll()
																	throws FrameworkException {
																throw new FrameworkException("Operation not supported.");
															}
														});
}
