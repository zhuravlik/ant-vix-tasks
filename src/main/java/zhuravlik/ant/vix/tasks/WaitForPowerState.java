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

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 14.01.12
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
public class WaitForPowerState extends VixAction {
    String state;
    int timeout;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public void executeAction(int vmHandle) {
        int istate;
        
        if (state == null)
            throw new BuildException("State is not specified");
        else if (state.equals("powering_off"))
            istate = Vix.VIX_POWERSTATE_POWERING_OFF;
        else if (state.equals("powering_on"))
            istate = Vix.VIX_POWERSTATE_POWERING_ON;
        else if (state.equals("powered_on"))
            istate = Vix.VIX_POWERSTATE_POWERED_ON;
        else if (state.equals("powered_off"))
            istate = Vix.VIX_POWERSTATE_POWERED_OFF;
        else if (state.equals("tools_running"))
            istate = Vix.VIX_POWERSTATE_TOOLS_RUNNING;
        else if (state.equals("blocked_on_msg"))
            istate = Vix.VIX_POWERSTATE_BLOCKED_ON_MSG;
        else if (state.equals("paused"))
            istate = Vix.VIX_POWERSTATE_PAUSED;
        else if (state.equals("resetting"))
            istate = Vix.VIX_POWERSTATE_RESETTING;
        else if (state.equals("resuming"))
            istate = Vix.VIX_POWERSTATE_RESUMING;
        else if (state.equals("suspended"))
            istate = Vix.VIX_POWERSTATE_SUSPENDED;
        else if (state.equals("suspending"))
            istate = Vix.VIX_POWERSTATE_SUSPENDING;
        else
            throw new BuildException("Unknown power state: " + state);

        log("Waiting for power state [" + state + "]"
                + (timeout > 0 ? " with timeout [" + timeout + "]" : ""), Project.MSG_INFO);

        
        for (int i = 0; timeout <= 0 || i < timeout; i++) {
            IntByReference statePtr = new IntByReference();
            int err = Vix.INSTANCE.Vix_GetProperties(vmHandle, Vix.VIX_PROPERTY_VM_POWER_STATE,
                    statePtr, Vix.VIX_PROPERTY_NONE);
            checkError(err);
            
            if (statePtr.getValue() == istate)
                return;
            
            try { Thread.sleep(1000); } catch (InterruptedException e) {throw new BuildException("Interrupted");}
        }
    }
}
