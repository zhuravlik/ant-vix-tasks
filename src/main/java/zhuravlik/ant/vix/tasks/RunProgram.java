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

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import zhuravlik.ant.vix.Vix;
import zhuravlik.ant.vix.VixAction;
import zhuravlik.ant.vix.LibraryHelper;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 09.01.12
 * Time: 22:49
 * To change this template use File | Settings | File Templates.
 */
public class RunProgram extends VixAction {

    String path = null;
    String args = null;

    boolean returnImmediately = false;
    boolean activateWindow = false;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public boolean isReturnImmediately() {
        return returnImmediately;
    }

    public void setReturnImmediately(boolean returnImmediately) {
        this.returnImmediately = returnImmediately;
    }

    public boolean isActivateWindow() {
        return activateWindow;
    }

    public void setActivateWindow(boolean activateWindow) {
        this.activateWindow = activateWindow;
    }

    @Override
    public void executeAction(int vmHandle) {
        log("Running program [" + path + "] in guest with args [" + args + "]", Project.MSG_INFO);

        if (path == null) {
            throw new BuildException("Path not specified");
        }

        if (args == null) {
            args = "";
        }

        int jobHandle = Vix.VIX_INVALID_HANDLE;

        int options = 0;

        if (returnImmediately) options |= Vix.VIX_RUNPROGRAM_RETURN_IMMEDIATELY;
        if (activateWindow) options |= Vix.VIX_RUNPROGRAM_ACTIVATE_WINDOW;

        jobHandle = LibraryHelper.getInstance().VixVM_RunProgramInGuest(vmHandle, path, args,
                options, Vix.VIX_INVALID_HANDLE, null, null);

        int err = LibraryHelper.getInstance().VixJob_Wait(jobHandle, Vix.VIX_PROPERTY_NONE);
        LibraryHelper.getInstance().Vix_ReleaseHandle(jobHandle);
        checkError(err);
    }
}
