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
                throw new BuildException("VMWare error: " + Vix.INSTANCE.Vix_GetErrorText(err, null));
            else
                log("VMWare error: " + Vix.INSTANCE.Vix_GetErrorText(err, null), Project.MSG_ERR);
        }
    }

    public abstract void executeAction(int vmHandle);
}
