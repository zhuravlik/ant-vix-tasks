package zhuravlik.ant.vix.tasks;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import zhuravlik.ant.vix.Vix;
import zhuravlik.ant.vix.VixAction;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 09.01.12
 * Time: 20:31
 * To change this template use File | Settings | File Templates.
 */
public class PowerOn extends VixAction {
    
    String mode = "normal";

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public void executeAction(int vmHandle) {
        log("Sending powerOn command", Project.MSG_INFO);

        if (!mode.equals("normal") && !mode.equals("launch_gui")) {
            log("Unknown mode: " + mode + ", assumed normal", Project.MSG_WARN);
        }
        
        int jobHandle = Vix.VIX_INVALID_HANDLE;

        jobHandle = Vix.INSTANCE.VixVM_PowerOn(vmHandle,
                mode.equals("normal") ? Vix.VIX_VMPOWEROP_NORMAL : Vix.VIX_VMPOWEROP_LAUNCH_GUI,
                Vix.VIX_INVALID_HANDLE, null, null);

        int err = Vix.INSTANCE.VixJob_Wait(jobHandle, Vix.VIX_PROPERTY_NONE);
        Vix.INSTANCE.Vix_ReleaseHandle(jobHandle);
        checkError(err);
    }
}
