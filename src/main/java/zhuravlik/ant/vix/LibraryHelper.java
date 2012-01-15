package zhuravlik.ant.vix;

import com.sun.jna.Native;
import org.apache.commons.lang.SystemUtils;
import org.apache.tools.ant.Project;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 15.01.12
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */
public class LibraryHelper {
    
    public static Vix instance = null;
    public static String path = null;
    
    public static Vix getInstance() {
        if (instance == null) {
            setLibraryPath(path);
            instance = loadVixLibrary();            
        }
        
        return instance;
    }
    
    public static void setLibraryPath(String path) {

        if (path != null)
            System.setProperty("jna.library.path", path);
        else if (SystemUtils.IS_OS_WINDOWS)
        {
            File dir = new File("C:/Program Files/VMware/VMware VIX/");
            String[] list = dir.list();
            for (String fl: list)
            {
                if (fl.contains("Workstation-") || fl.contains("Server-") || fl.contains("Player-"))
                {
                    //logger.info("Detected " + fl);
                    File wdir = new File("C:/Program Files/VMware/VMware VIX/" + fl);
                    String[] wlist = wdir.list();
                    for (String pfl: wlist)
                    {
                        if (pfl.equals("64bit") || pfl.equals("32bit")) {
                            System.setProperty("jna.library.path", "C:/Program Files/VMware/VMware VIX/" + fl + "/" + pfl);
                            //log("JNA library path is set to: " + "C:/Program Files/VMware/VMware VIX/" + fl + "/" + pfl, Project.MSG_INFO);
                        }
                    }
                }
            }
        }
        else {
            System.setProperty("jna.library.path", "/usr/lib/vmware-vix/");
            //log("JNA library path is set to: /usr/lib/vmware-vix/", Project.MSG_INFO);
        }
    }
    
    public static Vix loadVixLibrary() {
        if (new File("/usr/lib/vmware-vix/libvixAllProducts.so").exists())
            return (Vix) Native.loadLibrary("vixAllProducts", Vix.class);
        else
            return (Vix) Native.loadLibrary("vix", Vix.class);
    }
}
