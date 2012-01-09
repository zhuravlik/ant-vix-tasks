package zhuravlik.ant.vix.tasks;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import zhuravlik.ant.vix.Vix;
import zhuravlik.ant.vix.VixAction;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 09.01.12
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
public class TakeSnapshot extends VixAction {

    boolean withMemory = true;
    
    String name = null;
    String description = null;

    public boolean isWithMemory() {
        return withMemory;
    }

    public void setWithMemory(boolean withMemory) {
        this.withMemory = withMemory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void executeAction(int vmHandle) {
        if (name == null || name.length() == 0) {
            throw new BuildException("Snapshot name is not specified");
        }

        log("Saving VM state to snapshot [" + name + "]", Project.MSG_INFO);

        if (description == null)
            description = "";

        int jobHandle = Vix.VIX_INVALID_HANDLE;

        jobHandle = Vix.INSTANCE.VixVM_CreateSnapshot(vmHandle,
            name,
            description,
            withMemory ? Vix.VIX_SNAPSHOT_INCLUDE_MEMORY : 0,
            Vix.VIX_INVALID_HANDLE,
            null,
            null);

        int err = Vix.INSTANCE.VixJob_Wait(jobHandle, Vix.VIX_PROPERTY_NONE);
        Vix.INSTANCE.Vix_ReleaseHandle(jobHandle);
        checkError(err);
    }
}