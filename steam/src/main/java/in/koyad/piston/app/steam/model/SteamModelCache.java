package in.koyad.piston.app.steam.model;

import java.util.Map;

import in.koyad.piston.app.steam.sdk.api.ContentClient;
import in.koyad.piston.app.steam.sdk.impl.ContentClientImpl;
import in.koyad.piston.common.basic.exception.FrameworkException;
import in.koyad.piston.common.util.LoadingMap;
import in.koyad.piston.common.util.LoadingMap.Loader;
import in.koyad.piston.common.util.LogUtil;

public class SteamModelCache {
	
	private static final ContentClient contentService = new ContentClientImpl();
	
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
