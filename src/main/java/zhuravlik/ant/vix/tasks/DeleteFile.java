package zhuravlik.ant.vix.tasks;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import zhuravlik.ant.vix.Vix;
import zhuravlik.ant.vix.VixAction;
import zhuravlik.ant.vix.LibraryHelper;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 14.01.12
 * Time: 20:28
 * To change this template use File | Settings | File Templates.
 */
public class DeleteFile extends VixAction {

    String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void executeAction(int vmHandle) {
        log("Deleting file [" + path + "] in guest", Project.MSG_INFO);

        if (path == null) {
            throw new BuildException("Path not specified");
        }

        int jobHandle = Vix.VIX_INVALID_HANDLE;

        jobHandle = LibraryHelper.getInstance().VixVM_DeleteFileInGuest(vmHandle,
                path,
                null,
                null);


        int err = LibraryHelper.getInstance().VixJob_Wait(jobHandle, Vix.VIX_PROPERTY_NONE);
        LibraryHelper.getInstance().Vix_ReleaseHandle(jobHandle);
        checkError(err);
    }
}
