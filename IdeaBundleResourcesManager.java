import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.openapi.extensions.PluginId;
import org.apache.commons.io.FileUtils;
import vlinx.communicate.logging.Logger;
import vlinx.protector.ui.UIApp;
import vlinx.resource.IResourceManager;
import vlinx.util.FileUtil;
import vlinx.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/*
* Resources manager of JetBrains Plugin
 */

public class IdeaBundleResourcesManager {

    private String basePath = "";

    @Override
    public String getBasePath() {
        if (!StringUtil.isEmpty(basePath)) {
            return basePath;
        }

        this.basePath = new ResourceManager().getBasePath();
        return this.basePath;
    }

    @Override
    public String getResourcePath(String relativePath) {

        String path = this.getBasePath() + File.separator + relativePath;

        if (!FileUtil.isExist(path)) {
            PluginId pluginId = PluginManagerCore.getPluginByClassName(getClass().getName());
            IdeaPluginDescriptor descriptor = PluginManager.getPlugin(pluginId);
            InputStream resourceStream = descriptor.getPluginClassLoader().getResourceAsStream(relativePath);
            try {
                FileUtils.copyInputStreamToFile(resourceStream, new File(path));
            } catch (IOException e) {
                Logger.ERROR(e.getMessage(), UIApp.mainUIListener);
                return "";
            }
        }

        return path;
        
    }
}
