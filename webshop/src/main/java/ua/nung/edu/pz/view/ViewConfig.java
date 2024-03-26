package ua.nung.edu.pz.view;

public class ViewConfig {
	private String path;
	private static ViewConfig viewConfig = new ViewConfig();

	private ViewConfig() {
	}

	public static ViewConfig getInstance() {
		return viewConfig;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
