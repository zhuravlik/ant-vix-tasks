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

package zhuravlik.ant.vix;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectComponent;

/**
 * Abstract class for all subtasks of the head VixTask
 *
 * Each subtask implements executeAction method
 * which represents the implementation of subtask logic
 *
 * VIX API actions in each subtasks may fail with log message
 * or with BuildException depending on the value of ignoreError field
 */
public abstract class VixAction extends ProjectComponent {

    boolean ignoreError = false;

    public boolean isIgnoreError() {
        return ignoreError;
    }

    public void setIgnoreError(boolean ignoreError) {
        this.ignoreError = ignoreError;
    }

    boolean logError = false;

    public boolean isLogError() {
        return logError;
    }

    public void setLogError(boolean logError) {
        this.logError = logError;
    }

    /*
     * Gets human-readable error message for specified VIX error code
     *
     * Either throws BuildException or logs warning depending on ignoreError field value
     *
     * @param err VIX error code
     */
    public void checkError(int err) throws BuildException {
        if (Vix.VIX_OK != err) {

            if (!ignoreError)
                throw new BuildException("VMWare error: " + LibraryHelper.getInstance().Vix_GetErrorText(err, null));
            else
                log("VMWare error: " + LibraryHelper.getInstance().Vix_GetErrorText(err, null), logError ? Project.MSG_ERR : Project.MSG_WARN);
        }
    }

    /*
     * Abstract method describing subtask logic
     * Should be implemented by all subtasks of VixTask
     *
     * @param vmHandle Virtual Machine handle to execute action on
     */
    public abstract void executeAction(int vmHandle);
}
