import java.io.File;

/*
 * Resource manager of Eclipse workspace
 */
public class WorkbenchResourcesManager {

	@Override
	public String getBasePath() {
		String absPath = Activator.getDefault().getStateLocation().makeAbsolute().toFile().getAbsolutePath();
		return absPath;
	}

	@Override
	public String getResourcePath(String relativePath) {
		String basePath = getBasePath();

		File file = new File(basePath);
		if (file.isDirectory()) {
			String absPath = file.getAbsolutePath() + File.separator + relativePath;
			return absPath;
		} else {
			if (file.getParent() == null) {
				return new File("").getAbsolutePath() + File.separator + relativePath;
			}
			return file.getParent() + File.separator + relativePath;
		}
	}
}
