import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import vlinx.logging.Logger;
import vlinx.resource.IResourceManager;

/*
 * Resources manager of Eclipse Plugin
 */
public class BundleResourcesManager implements IResourceManager {

	public String getBasePath() {
		return "";
	}

	@Override
	public String getResourcePath(String relativePath) {
		Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);

		URL url = bundle.getResource(relativePath);

		if (url == null) {
			return "";
		}

		URL fUrl;
		try {
			fUrl = FileLocator.toFileURL(url);
			String decodeFilePath = URLDecoder.decode(fUrl.getFile(), "UTF-8");
			File file = new File(decodeFilePath);
			String absPath = file.getAbsolutePath();

			return absPath;
		} catch (IOException e) {
	    //TODO Handle exception
			return "";
		}
	}
}
