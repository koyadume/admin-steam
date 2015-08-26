package in.koyad.piston.app.steam.listener;

import in.koyad.piston.controller.plugin.AppListener;
import in.koyad.piston.controller.plugin.annotations.AnnoAppListener;

@AnnoAppListener
public class SteamAppListener implements AppListener {

	@Override
	public void init() {
//		AppJPAEMFactory.initialize(AppConstants.PERSISTENT_UNIT_STEAM);
	}

	@Override
	public void destroy() {
//		AppJPAEMFactory.close();
	}

}
