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
import com.sun.jna.ptr.PointerByReference;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import zhuravlik.ant.vix.Vix;
import zhuravlik.ant.vix.VixAction;
import zhuravlik.ant.vix.LibraryHelper;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 14.01.12
 * Time: 22:23
 * To change this template use File | Settings | File Templates.
 */
public class KillProcess extends VixAction {
    
    String name;
    String cmdline;
    boolean strict;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCmdline() {
        return cmdline;
    }

    public void setCmdline(String cmdline) {
        this.cmdline = cmdline;
    }

    public boolean isStrict() {
        return strict;
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
    }

    @Override
    public void executeAction(int vmHandle) {

        if (name == null && cmdline == null)
            throw new BuildException("Process name or command line are not specified");

        if (name != null && cmdline != null)
            throw new BuildException("Please specify process name OR command line, both together are not supported");

        log("Killing process with " + (name != null ? "name " : "command line ")
                + (strict ? "" : "containing ")
                + "[" + (name != null ? name : cmdline) + "]", Project.MSG_INFO);

        int jobHandle = Vix.VIX_INVALID_HANDLE;

        jobHandle = LibraryHelper.getInstance().VixVM_ListProcessesInGuest(vmHandle, 0, null, null);

        int err = LibraryHelper.getInstance().VixJob_Wait(jobHandle, Vix.VIX_PROPERTY_NONE);
        checkError(err);


        int num = LibraryHelper.getInstance().VixJob_GetNumProperties(jobHandle, Vix.VIX_PROPERTY_JOB_RESULT_ITEM_NAME);

        for (int j = 0; j < num; j++) {
            PointerByReference processName = new PointerByReference();
            PointerByReference cmdLinePtr = new PointerByReference();
            IntByReference pid = new IntByReference();

            err = LibraryHelper.getInstance().VixJob_GetNthProperties(jobHandle, j,
                    Vix.VIX_PROPERTY_JOB_RESULT_ITEM_NAME, processName,
                    Vix.VIX_PROPERTY_JOB_RESULT_PROCESS_COMMAND, cmdLinePtr,
                    Vix.VIX_PROPERTY_JOB_RESULT_PROCESS_ID, pid,
                    Vix.VIX_PROPERTY_NONE);
            checkError(err);

            String pname = processName.getValue().getString(0);
            LibraryHelper.getInstance().Vix_FreeBuffer(processName.getValue());

            String cmd = cmdLinePtr.getValue().getString(0);
            LibraryHelper.getInstance().Vix_FreeBuffer(cmdLinePtr.getValue());

            if (name != null ?
                    (strict && name.equals(pname) || name.contains(pname)) :
                    (strict && cmdline.equals(cmd) || cmdline.contains(cmd))
                    ) {
                int ijobHandle = LibraryHelper.getInstance().VixVM_KillProcessInGuest(vmHandle, pid.getValue(), 0, null, null);
                err = LibraryHelper.getInstance().VixJob_Wait(ijobHandle, Vix.VIX_PROPERTY_NONE);
                checkError(err);
                LibraryHelper.getInstance().Vix_ReleaseHandle(ijobHandle);
            }
        }

        LibraryHelper.getInstance().Vix_ReleaseHandle(jobHandle);
    }
}
