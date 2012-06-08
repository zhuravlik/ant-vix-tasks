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

package zhuravlik.ant.vix;

import com.sun.jna.ptr.IntByReference;
import org.apache.commons.lang.SystemUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import zhuravlik.ant.vix.tasks.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 02.01.12
 * Time: 22:49
 * To change this template use File | Settings | File Templates.
 */
public class VixTask extends Task {

    private List<VixAction> actions = new ArrayList<VixAction>();

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
                throw new BuildException("VMWare error: " + LibraryHelper.getInstance().Vix_GetErrorText(err, null));
            else
                log("VMWare error: " + LibraryHelper.getInstance().Vix_GetErrorText(err, null), Project.MSG_ERR);
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private String host;
    private int port;

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

    private String user;
    private String password;


    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    private String serviceProvider;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String path;

    public String getVixPath() {
        return vixPath;
    }

    public void setVixPath(String vixPath) {
        this.vixPath = vixPath;
    }

    private String vixPath;

    public void addCaptureScreen(CaptureScreen captureScreen) {
        actions.add(captureScreen);
    }

    public void addDeleteDirectory(DeleteDirectory deleteDirectory) {
        actions.add(deleteDirectory);
    }

    public void addDeleteFile(DeleteFile deleteFile) {
        actions.add(deleteFile);
    }

    public void addInstallTools(InstallTools installTools) {
        actions.add(installTools);
    }

    public void addKillProcess(KillProcess killProcess) {
        actions.add(killProcess);
    }

    public void addLogout(Logout logout) {
        actions.add(logout);
    }

    public void addMoveFile(MoveFile moveFile) {
        actions.add(moveFile);
    }

    public void addRunScript(RunScript runScript) {
        actions.add(runScript);
    }

    public void addToggleSharedFolders(ToggleSharedFolders toggleSharedFolders) {
        actions.add(toggleSharedFolders);
    }

    public void addWaitForPowerState(WaitForPowerState waitForPowerState) {
        actions.add(waitForPowerState);
    }

    public void addWaitForProcess(WaitForProcess waitForProcess) {
        actions.add(waitForProcess);
    }

    public void addWaitForTools(WaitForTools waitForTools) {
        actions.add(waitForTools);
    }

    public void addPowerOn(PowerOn powerOn) {
        actions.add(powerOn);
    }

    public void addPowerOff(PowerOff stop) {
        actions.add(stop);
    }

    public void addTakeSnapshot(TakeSnapshot start) {
        actions.add(start);
    }

    public void addRevertToSnapshot(RevertToSnapshot start) {
        actions.add(start);
    }

    public void addDeleteSnapshot(DeleteSnapshot start) {
        actions.add(start);
    }

    public void addLogin(Login vixAction) {
        actions.add(vixAction);
    }

    public void addCreateDirectory(CreateDirectory vixAction) {
        actions.add(vixAction);
    }

    public void addPutFile(PutFile vixAction) {
        actions.add(vixAction);
    }

    public void addGetFile(GetFile vixAction) {
        actions.add(vixAction);
    }

    public void addRunProgram(RunProgram vixAction) {
        actions.add(vixAction);
    }
    
    public void addAddSharedFolder(AddSharedFolder vixAction) {
        actions.add(vixAction);
    }
    
    public void addClone(Clone vixAction) {
        actions.add(vixAction);
    }
    
    public void addDelete(Delete vixAction) {
        actions.add(vixAction);
    }
    
    public void addPause(Pause vixAction) {
        actions.add(vixAction);
    }
    
    public void addRemoveSharedFolder(RemoveSharedFolder vixAction) {
        actions.add(vixAction);
    }
    
    public void addReset(Reset vixAction) {
        actions.add(vixAction);
    }
    
    public void addSuspend(Suspend vixAction) {
        actions.add(vixAction);
    }
    
    public void addUnpause(Unpause vixAction) {
        actions.add(vixAction);
    }

    public void execute() throws BuildException {

        if (vixPath != null && vixPath.length() > 0) {
            LibraryHelper.path = vixPath;
        }

        int provider;
        
        if (serviceProvider.equals("vi"))  {
            provider = Vix.VIX_SERVICEPROVIDER_VMWARE_VI_SERVER;
            log("VIX Service Provider is VI Server or VMWare Server 2.0", Project.MSG_INFO);
        }
        else if (serviceProvider.equals("server")) {
            provider = Vix.VIX_SERVICEPROVIDER_VMWARE_SERVER;
            log("VIX Service Provider is VMWare Server 1.0", Project.MSG_INFO);
        }
        else if (serviceProvider.equals("workstation")) {
            provider = Vix.VIX_SERVICEPROVIDER_VMWARE_WORKSTATION;
            log("VIX Service Provider is VMWare Workstation", Project.MSG_INFO);
        }
        else if (serviceProvider.equals("workstation_shared")) {
            provider = Vix.VIX_SERVICEPROVIDER_VMWARE_WORKSTATION_SHARED;
            log("VIX Service Provider is VMWare Workstation (shared mode)", Project.MSG_INFO);
        }
        else if (serviceProvider.equals("player")) {
            provider = Vix.VIX_SERVICEPROVIDER_VMWARE_PLAYER;
            log("VIX Service Provider is VMWare Player", Project.MSG_INFO);
        }
        else {
            log("Unknown service provider: " + serviceProvider + ", assuming workstation", Project.MSG_WARN);
            provider = Vix.VIX_SERVICEPROVIDER_VMWARE_WORKSTATION;
        }
        
        if (host != null) {
            log("Running remote session for host " + host + ":" + port, Project.MSG_INFO);
        }
        else {
            log("Running local session", Project.MSG_INFO);
            port = 0;
            user = null;
            password = null;
        }
        
        int jobHandle = Vix.VIX_INVALID_HANDLE;
        IntByReference hostHandlePtr = new IntByReference();

        log("Connecting", Project.MSG_INFO);
                
        jobHandle = LibraryHelper.getInstance().VixHost_Connect(Vix.VIX_API_VERSION, provider,
                host, port, user, password, 0, Vix.VIX_INVALID_HANDLE, null, null);

        int err = LibraryHelper.getInstance().VixJob_Wait(jobHandle,
                Vix.VIX_PROPERTY_JOB_RESULT_HANDLE,
                hostHandlePtr,
                Vix.VIX_PROPERTY_NONE);

        LibraryHelper.getInstance().Vix_ReleaseHandle(jobHandle);
        checkError(err);

        log("Opening VM [" + path + "]");


        jobHandle = LibraryHelper.getInstance().VixHost_OpenVM(hostHandlePtr.getValue(), path,
                Vix.VIX_VMOPEN_NORMAL, Vix.VIX_INVALID_HANDLE, null, null);
        
        IntByReference vmHandlePtr = new IntByReference();
        
        err = LibraryHelper.getInstance().VixJob_Wait(jobHandle,
                Vix.VIX_PROPERTY_JOB_RESULT_HANDLE,
                vmHandlePtr,
                Vix.VIX_PROPERTY_NONE);

        checkError(err);

        for (VixAction vixAction: actions) {
            vixAction.executeAction(vmHandlePtr.getValue());
        }
        
        LibraryHelper.getInstance().VixHost_Disconnect(hostHandlePtr.getValue());
        LibraryHelper.getInstance().Vix_ReleaseHandle(hostHandlePtr.getValue());
    }
}
