/*
   Copyright (C) 2012 Anton Lobov <zhuravlik> <ahmad200512[at]yandex.ru>

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

import com.sun.jna.ptr.PointerByReference;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import zhuravlik.ant.vix.LibraryHelper;
import zhuravlik.ant.vix.Vix;
import zhuravlik.ant.vix.VixAction;

/**
 *
 * @author anton
 */
public class CreateTempFile extends VixAction {

    @Override
    public void executeAction(int vmHandle) {
        log("Creating temporary file in guest, result is stored in system property vix.tempfile.path", Project.MSG_INFO);

        int jobHandle = Vix.VIX_INVALID_HANDLE;

        jobHandle = LibraryHelper.getInstance().VixVM_CreateTempFileInGuest(vmHandle,
            0,
            Vix.VIX_INVALID_HANDLE,
            null,
            null);

        PointerByReference tempFilePathRef = new PointerByReference();
        
        int err = LibraryHelper.getInstance().VixJob_Wait(jobHandle, Vix.VIX_PROPERTY_JOB_RESULT_ITEM_NAME , tempFilePathRef, Vix.VIX_PROPERTY_NONE);
        LibraryHelper.getInstance().Vix_ReleaseHandle(jobHandle);
        checkError(err);
        
        System.setProperty("vix.tempfile.path", tempFilePathRef.getValue().getString(0));
    }
    
}
