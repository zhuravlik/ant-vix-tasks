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
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import zhuravlik.ant.vix.LibraryHelper;
import zhuravlik.ant.vix.Vix;
import zhuravlik.ant.vix.VixAction;

/**
 *
 * @author anton
 */
public class Clone extends VixAction {

    String snapshot = null;
    String destination;
    String cloneType;

    public String getCloneType() {
        return cloneType;
    }

    public void setCloneType(String cloneType) {
        this.cloneType = cloneType;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }        
    
    @Override
    public void executeAction(int vmHandle) {
        if (destination == null || destination.length() == 0)
            throw new BuildException("Destination is not specified");
        
        if (cloneType == null)
            cloneType = "linked";
        
        if (!cloneType.equals("linked") && !cloneType.equals("full"))
            throw new BuildException("Invalid clone type " + cloneType + ", should be either linked or full");
        
        int snapshotHandle = Vix.VIX_INVALID_HANDLE;
        
        if (snapshot != null) {
            IntByReference snapshotHandlePtr = new IntByReference();

            int err = LibraryHelper.getInstance().VixVM_GetNamedSnapshot(vmHandle,
                snapshot,
                snapshotHandlePtr);
                checkError(err);
                
            snapshotHandle = snapshotHandlePtr.getValue();
            
            log("Creating " + cloneType + " clone of VM's snapshot " + snapshot + " to " + destination, Project.MSG_INFO);
        }
        else {
            log("Creating " + cloneType + " clone of VM's current state to " + destination, Project.MSG_INFO);
        }        

        int jobHandle = Vix.VIX_INVALID_HANDLE;

        jobHandle = LibraryHelper.getInstance().VixVM_Clone(vmHandle, snapshotHandle, 
                cloneType.equals("full") ? Vix.VIX_CLONETYPE_FULL : Vix.VIX_CLONETYPE_LINKED, 
                destination, 0, Vix.VIX_INVALID_HANDLE, null, null);

        int err = LibraryHelper.getInstance().VixJob_Wait(jobHandle, Vix.VIX_PROPERTY_NONE);
        LibraryHelper.getInstance().Vix_ReleaseHandle(jobHandle);
        checkError(err);
    }    
}
