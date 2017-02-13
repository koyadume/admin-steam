package in.koyad.piston.app.steam.forms;

import in.koyad.piston.controller.plugin.annotations.AnnoPluginForm;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AnnoPluginForm(name = ContentPluginForm.FORM_NAME)
public class ContentPluginForm {
	
	public static final String FORM_NAME = "content";
	
	private String content;
	
}
