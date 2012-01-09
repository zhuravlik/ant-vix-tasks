package zhuravlik.ant.vix.tasks;

import com.sun.jna.ptr.IntByReference;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import zhuravlik.ant.vix.Vix;
import zhuravlik.ant.vix.VixAction;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 09.01.12
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
public class DeleteSnapshot extends VixAction {

    String name = null;

    boolean withChilden = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWithChilden() {
        return withChilden;
    }

    public void setWithChilden(boolean withChilden) {
        this.withChilden = withChilden;
    }

    @Override
    public void executeAction(int vmHandle) {
        if (name == null || name.length() == 0) {
            throw new BuildException("Snapshot name is not specified");
        }

        log("Removing snapshot [" + name + "]", Project.MSG_INFO);

        int jobHandle = Vix.VIX_INVALID_HANDLE;
        IntByReference snapshotHandlePtr = new IntByReference();

        jobHandle = Vix.INSTANCE.VixVM_GetNamedSnapshot(vmHandle,
                name,
                snapshotHandlePtr);

        int err = Vix.INSTANCE.VixJob_Wait(jobHandle, Vix.VIX_PROPERTY_NONE);
        Vix.INSTANCE.Vix_ReleaseHandle(jobHandle);
        checkError(err);


        jobHandle = Vix.INSTANCE.VixVM_RemoveSnapshot(vmHandle,
                snapshotHandlePtr.getValue(),
                withChilden ? Vix.VIX_SNAPSHOT_REMOVE_CHILDREN : 0,
                null,
                null);

        Vix.INSTANCE.Vix_ReleaseHandle(snapshotHandlePtr.getValue());

        err = Vix.INSTANCE.VixJob_Wait(jobHandle, Vix.VIX_PROPERTY_NONE);
        Vix.INSTANCE.Vix_ReleaseHandle(jobHandle);
        checkError(err);
    }
}

