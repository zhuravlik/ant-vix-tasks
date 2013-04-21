/*
   Copyright (C) 2012-2013 Anton Lobov <zhuravlik> <ahmad200512[at]yandex.ru>

   This library is free software; you can redistribute it and/or
   modify it under the terms of the GNU Lesser General Public
   License as published by the Free Software Foundation; either
   version 3 of the License, or (at your option) any later version.

   This library is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   Lesser General Public License for more details.

   You should have received a copy of the GNU Lesser General
   Public License along with this library; if not, write to the
   Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
   Boston, MA 02110-1301 USA
 */

package zhuravlik.ant.vix;

import com.sun.jna.Native;
import org.apache.commons.lang.SystemUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

import java.io.File;

/**
 * LibraryHelper is the helper class that automatically sets VIX library path
 * and loads VIX library using JNA
 *
 * On Windows platform
 */

public class LibraryHelper {


    public static Vix instance = null;
    public static String path = null;
    public static String provider = null;
    
    public static Vix getInstance() {
        if (instance == null) {
            setLibraryPath(path);
            instance = loadVixLibrary();            
        }
        
        return instance;
    }

    /*
     * Sets JNA library path system property (jna.library.path) to the location
     * of VIX shared library.
     *
     * If user specified it for head task, user-specified value will be used.
     * If user did not specify it, it will be automatically guessed.
     *
     * @param path User-specified path
     */
    public static void setLibraryPath(String path) {

        if (path != null) {
            // user-specified path has highest priority

            System.setProperty("jna.library.path", path);
        }
        else if (SystemUtils.IS_OS_WINDOWS) {
            // on Windows, there is no easy location of library

            // either 32 bit or 64 bit vmware
            String path1 = "C:/Program Files/VMware/VMware VIX/";
            String path2 = "C:/Program Files (x86)/VMware/VMware VIX/";

            // final path
            String gpath;

            if (new File(path1).exists()) {
                // 32 bit vix on 32 bit sys or 64 bit vix on 64 bit sys

                if (!SystemUtils.JAVA_VM_NAME.contains("64")
                        &&
                        new File("C:/Program Files (x86)").exists()) {
                    // 32 bit java on 64 bit sys

                    if (new File(path2).exists()) {
                        // 32 bit vix, 32 bit Java
                        gpath = path2;
                    }
                    else {
                        // 64 bit vix, but 32 bit Java
                        throw new BuildException("Cannot use 64-bit VMWare libraries with 32-bit Java Runtime." +
                                " Either install 64-bit Java or VMWare with 32 bit library support");
                    }
                }
                else
                    gpath = path1;
            }
            else {
                // 32 bit vix on 64 bit system

                if (SystemUtils.JAVA_VM_NAME.contains("64")) {
                    // 64 bit Java
                    throw new BuildException("Cannot use 32-bit VMWare libraries with 64-bit Java Runtime." +
                            " Either install 32-bit Java or VMWare with 64 bit support (8+).");
                }
                else if (new File(path2).exists()) {
                    // 32 bit Java, vmware path exists
                    gpath = path2;
                }
                else {
                    // 32 bit Java, vmware path not exists
                    throw new BuildException("Cannot locate any VMWare folder, ensure it is installed. " +
                            "If it is, please file a bug with OS version and VMWare product name and version");
                }
            }

            // iterate over vmware directory to find libraries for specific product
            File dir = new File(gpath);
            String[] list = dir.list();
            for (String fl: list)
            {
                //if (fl.contains("Workstation-") || fl.contains("Server-")
                //        || fl.contains("Player-") || fl.contains("VIServer-"))

                if (
                        provider.equals("vi") && fl.contains("VIServer-") ||
                        provider.contains("workstation") && fl.contains("Workstation-") ||
                        provider.equals("player") && fl.contains("Player-") ||
                        provider.equals("server") && fl.contains("Server-")
                   )
                {
                    File wdir = new File(gpath + fl);
                    String[] wlist = wdir.list();
                    for (String pfl: wlist)
                    {
                        if (pfl.equals("64bit") || pfl.equals("32bit")) {
                            System.setProperty("jna.library.path", gpath + fl + "/" + pfl);
                        }
                    }
                }
            }
        }
        else {
            // for unix-like it should reside under /usr/lib/vmware-vix for all products

            System.setProperty("jna.library.path", "/usr/lib/vmware-vix/");
        }
    }
    
    public static Vix loadVixLibrary() {
        if (new File("/usr/lib/vmware-vix/libvixAllProducts.so").exists())     // on Unix there is single entry point
            return (Vix) Native.loadLibrary("vixAllProducts", Vix.class);
        else
            return (Vix) Native.loadLibrary("vix", Vix.class);     // on Windows there is vix.dll
    }
}
