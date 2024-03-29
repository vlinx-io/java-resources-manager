import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/*
* Resources manager of classpath
* 采用 java -cp classpath MainClass 时候有效，BasePath为MainClass所在Jar的路径
*/

public class ResourcesManager {

    public String getResourcePath(String relativePath) {
        try {
            return URLDecoder.decode(getBasePath() + File.separator + relativePath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return getBasePath() + File.separator + relativePath;
        }
    }

    public String getBasePath() {
        File file = new File(ResourcesManager.class.getProtectionDomain().getCodeSource().getLocation().getPath());

        String absPath = "";

        if (file.isDirectory()) {
            absPath = file.getAbsolutePath();
        } else {
            absPath = file.getParentFile().getAbsolutePath();
        }

        try {
            return URLDecoder.decode(absPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return absPath;
        }
    }

}
