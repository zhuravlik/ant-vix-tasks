package zhuravlik.ant.vix.tasks;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import zhuravlik.ant.vix.Vix;
import zhuravlik.ant.vix.VixAction;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 09.01.12
 * Time: 22:47
 * To change this template use File | Settings | File Templates.
 */
public class GetFile extends VixAction {

    String path;
    String destination;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public void executeAction(int vmHandle) {
        log("Copying file [" + path + "] from guest to path [" + destination + "]", Project.MSG_INFO);

        if (path == null) {
            throw new BuildException("Path not specified");
        }

        if (destination == null) {
            throw new BuildException("Destination not specified");
        }

        int jobHandle = Vix.VIX_INVALID_HANDLE;

        jobHandle = Vix.INSTANCE.VixVM_CopyFileFromGuestToHost(vmHandle, path, destination, 0,
                Vix.VIX_INVALID_HANDLE, null, null);


        int err = Vix.INSTANCE.VixJob_Wait(jobHandle, Vix.VIX_PROPERTY_NONE);
        Vix.INSTANCE.Vix_ReleaseHandle(jobHandle);
        checkError(err);
    }
}
