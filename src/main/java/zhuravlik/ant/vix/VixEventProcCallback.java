package zhuravlik.ant.vix;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 02.01.12
 * Time: 23:02
 * To change this template use File | Settings | File Templates.
 */
public interface VixEventProcCallback extends Callback {
    void VixEventProc(int handle, int eventType, int moreEventInfo, Pointer clientData);
}
