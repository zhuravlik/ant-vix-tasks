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
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import zhuravlik.ant.vix.LibraryHelper;
import zhuravlik.ant.vix.Vix;
import zhuravlik.ant.vix.VixAction;

/**
 *
 * @author anton
 */
public class WriteVariable extends VixAction {
    
    String type;
    String name;
    String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
        
    
    @Override
    public void executeAction(int vmHandle) {        
        
        if (type == null || name == null || value == null || type.length() == 0 || name.length() == 0 || value.length() == 0)
            throw new BuildException("Either type, name or value not specified");
        
        if (!type.equals("guest") && !type.equals("runtime") && !type.equals("environment"))
            throw new BuildException("Unknown variable type: " + type);
        
        log("Writing " + type + " variable: " + name + " = " + value, Project.MSG_INFO);

        int jobHandle = Vix.VIX_INVALID_HANDLE;

        jobHandle = LibraryHelper.getInstance().VixVM_WriteVariable(vmHandle,
                type.equals("guest") ? Vix.VIX_VM_GUEST_VARIABLE : (
                    type.equals("runtime") ? 
                        Vix.VIX_VM_CONFIG_RUNTIME_ONLY :
                        Vix.VIX_GUEST_ENVIRONMENT_VARIABLE
                ),
                name, value, 0, null, null);

        int err = LibraryHelper.getInstance().VixJob_Wait(jobHandle, Vix.VIX_PROPERTY_NONE);
        LibraryHelper.getInstance().Vix_ReleaseHandle(jobHandle);
        checkError(err);
    }    
}
