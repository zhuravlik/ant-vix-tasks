package zhuravlik.ant.vix.tasks;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import zhuravlik.ant.vix.Vix;
import zhuravlik.ant.vix.VixAction;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 09.01.12
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public class Login extends VixAction {

    String user;
    String password;

    boolean interactive;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isInteractive() {
        return interactive;
    }

    public void setInteractive(boolean interactive) {
        this.interactive = interactive;
    }

    @Override
    public void executeAction(int vmHandle) {
        log("Logging in to guest", Project.MSG_INFO);

        if (user == null || password == null) {
            throw new BuildException("User or password not specified");
        }

        int jobHandle = Vix.VIX_INVALID_HANDLE;

        jobHandle = Vix.INSTANCE.VixVM_LoginInGuest(vmHandle, user, password,
                interactive ? Vix.VIX_LOGIN_IN_GUEST_REQUIRE_INTERACTIVE_ENVIRONMENT : 0,
                null, null);

        int err = Vix.INSTANCE.VixJob_Wait(jobHandle, Vix.VIX_PROPERTY_NONE);
        Vix.INSTANCE.Vix_ReleaseHandle(jobHandle);
        checkError(err);
    }
}
