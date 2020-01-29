import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/*
* Resource manager of jar file
/*

public class ResourceManager {

    public String getResourcePath(String relativePath) {
        try {
            return URLDecoder.decode(getBasePath() + File.separator + relativePath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return getBasePath() + File.separator + relativePath;
        }
    }

    public String getBasePath() {
        File file = new File(ResourceManager.class.getProtectionDomain().getCodeSource().getLocation().getPath());

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
