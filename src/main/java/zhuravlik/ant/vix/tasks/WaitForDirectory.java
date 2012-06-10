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

import com.sun.jna.ptr.IntByReference;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import zhuravlik.ant.vix.LibraryHelper;
import zhuravlik.ant.vix.Vix;
import zhuravlik.ant.vix.VixAction;

/**
 *
 * @author anton
 */
public class WaitForDirectory extends VixAction {
    
    boolean exists = true;
    String path;
    int timeout;
    
    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }        
    
    @Override
    public void executeAction(int vmHandle) {
        
        if (path == null || path.length() == 0)
            throw new BuildException("Directory path to wait for not specified");

        log("Waiting for file [" + path + "] to " + (exists ? "appear in" : "disappear from") + " guest "
                + (timeout > 0 ? " with timeout [" + timeout + "]" : ""), Project.MSG_INFO);

        
        for (int i = 0; timeout <= 0 || i < timeout; i++) {
            IntByReference statePtr = new IntByReference();
            
            int jobHandle = Vix.VIX_INVALID_HANDLE;

            jobHandle = LibraryHelper.getInstance().VixVM_DirectoryExistsInGuest(vmHandle, path, null, null);

            int err = LibraryHelper.getInstance().VixJob_Wait(jobHandle, Vix.VIX_PROPERTY_JOB_RESULT_GUEST_OBJECT_EXISTS, statePtr, Vix.VIX_PROPERTY_NONE);
            LibraryHelper.getInstance().Vix_ReleaseHandle(jobHandle);
            checkError(err);
                        
            if (statePtr.getValue() == (exists ? 1 : 0))
                return;
            
            try { Thread.sleep(1000); } catch (InterruptedException e) {throw new BuildException("Interrupted");}
        }
    }
}
