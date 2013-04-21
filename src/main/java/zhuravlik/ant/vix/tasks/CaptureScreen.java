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

package zhuravlik.ant.vix.tasks;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import zhuravlik.ant.vix.Vix;
import zhuravlik.ant.vix.VixAction;
import zhuravlik.ant.vix.LibraryHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 14.01.12
 * Time: 17:45
 * To change this template use File | Settings | File Templates.
 */
public class CaptureScreen extends VixAction {
    
    String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void executeAction(int vmHandle) {
        log("Capturing VM screen image", Project.MSG_INFO);

        int jobHandle = Vix.VIX_INVALID_HANDLE;
        jobHandle = LibraryHelper.getInstance().VixVM_CaptureScreenImage(vmHandle, Vix.VIX_CAPTURESCREENFORMAT_PNG,
                Vix.VIX_INVALID_HANDLE, null, null);

        IntByReference byteCount = new IntByReference();
        PointerByReference data = new PointerByReference();

        int err = LibraryHelper.getInstance().VixJob_Wait(jobHandle,
                Vix.VIX_PROPERTY_JOB_RESULT_SCREEN_IMAGE_DATA,
                byteCount, data,
                Vix.VIX_PROPERTY_NONE);
        LibraryHelper.getInstance().Vix_ReleaseHandle(jobHandle);
        checkError(err);

        Pointer p = data.getValue();
        byte[] bdata = p.getByteArray(0, byteCount.getValue());
        LibraryHelper.getInstance().Vix_FreeBuffer(p);
        
        try {
            new File(path).createNewFile();
            FileOutputStream fos = new FileOutputStream(new File(path));
            fos.write(bdata);
            fos.close();
        }
        catch (IOException e) {
            throw new BuildException("Unable to write screenshot to file " + path + ": " + e.getMessage());
        }
    }
}
