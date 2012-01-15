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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import zhuravlik.ant.vix.LibraryHelper;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 14.01.12
 * Time: 20:54
 * To change this template use File | Settings | File Templates.
 */
public class RunScript extends VixAction {
    String interpreter;
    String text;
    boolean textIsPath = false;
    boolean returnImmediately = false;

    public String getInterpreter() {
        return interpreter;
    }

    public void setInterpreter(String interpreter) {
        this.interpreter = interpreter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isTextIsPath() {
        return textIsPath;
    }

    public void setTextIsPath(boolean textIsPath) {
        this.textIsPath = textIsPath;
    }

    public boolean isReturnImmediately() {
        return returnImmediately;
    }

    public void setReturnImmediately(boolean returnImmediately) {
        this.returnImmediately = returnImmediately;
    }

    @Override
    public void executeAction(int vmHandle) {
        log("Running script [" + text + "] in guest with interpreter [" + interpreter + "]", Project.MSG_INFO);


        if (text == null) {
            throw new BuildException("Script text not specified");
        }
        else if (textIsPath) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(new File(text)));
                String line;
                StringBuilder data = new StringBuilder();
                
                while ((line = br.readLine()) != null) {
                    data.append(line);
                    data.append('\n');
                }

                text = data.toString();
            }
            catch (IOException e) {
                throw new BuildException("Unable to read script file at path [" + text + "]");
            }
        }

        int jobHandle = Vix.VIX_INVALID_HANDLE;

        int options = 0;

        if (returnImmediately) options |= Vix.VIX_RUNPROGRAM_RETURN_IMMEDIATELY;

        jobHandle = LibraryHelper.getInstance().VixVM_RunScriptInGuest(vmHandle, interpreter, text,
                options, Vix.VIX_INVALID_HANDLE, null, null);

        int err = LibraryHelper.getInstance().VixJob_Wait(jobHandle, Vix.VIX_PROPERTY_NONE);
        LibraryHelper.getInstance().Vix_ReleaseHandle(jobHandle);
        checkError(err);
    }
}
