package zhuravlik.ant.vix.tasks;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import zhuravlik.ant.vix.Vix;
import zhuravlik.ant.vix.VixAction;

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

        jobHandle = Vix.INSTANCE.VixVM_RunProgramInGuest(vmHandle, path, args,
                options, Vix.VIX_INVALID_HANDLE, null, null);

        int err = Vix.INSTANCE.VixJob_Wait(jobHandle, Vix.VIX_PROPERTY_NONE);
        Vix.INSTANCE.Vix_ReleaseHandle(jobHandle);
        checkError(err);
    }
}
