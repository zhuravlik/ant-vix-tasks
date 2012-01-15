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
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 02.01.12
 * Time: 22:55
 * To change this template use File | Settings | File Templates.
 */
public abstract class VixAction extends ProjectComponent {

    boolean ignoreError = false;

    public boolean isIgnoreError() {
        return ignoreError;
    }

    public void setIgnoreError(boolean ignoreError) {
        this.ignoreError = ignoreError;
    }

    public void checkError(int err) throws BuildException {
        if (Vix.VIX_OK != err) {

            if (!ignoreError)
                throw new BuildException("VMWare error: " + LibraryHelper.getInstance().Vix_GetErrorText(err, null));
            else
                log("VMWare error: " + LibraryHelper.getInstance().Vix_GetErrorText(err, null), Project.MSG_ERR);
        }
    }

    public abstract void executeAction(int vmHandle);
}
