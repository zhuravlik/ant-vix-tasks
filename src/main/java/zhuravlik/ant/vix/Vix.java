package zhuravlik.ant.vix;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

/**
 * Created by IntelliJ IDEA.
 * User: anton
 * Date: 02.01.12
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */
public interface Vix extends Library{

    Vix INSTANCE = (Vix) Native.loadLibrary("vix", Vix.class);//Vix.getInterfaceInstance();

    public static final int VIX_INVALID_HANDLE = 0;

    /*
    * These are the types of handles.
    */
    public static final int VIX_HANDLETYPE_NONE                 = 0;
    public static final int VIX_HANDLETYPE_HOST                 = 2;
    public static final int VIX_HANDLETYPE_VM                   = 3;
    public static final int VIX_HANDLETYPE_NETWORK              = 5;
    public static final int VIX_HANDLETYPE_JOB                  = 6;
    public static final int VIX_HANDLETYPE_SNAPSHOT             = 7;
    public static final int VIX_HANDLETYPE_PROPERTY_LIST        = 9;
    public static final int VIX_HANDLETYPE_METADATA_CONTAINER   = 11;

    /*
 * The error codes are returned by all public VIX routines.
 */

        public static final int VIX_OK                                       = 0;

        /* General errors */
        public static final int VIX_E_FAIL                                   = 1;
        public static final int VIX_E_OUT_OF_MEMORY                          = 2;
        public static final int VIX_E_INVALID_ARG                            = 3;
        public static final int VIX_E_FILE_NOT_FOUND                         = 4;
        public static final int VIX_E_OBJECT_IS_BUSY                         = 5;
        public static final int VIX_E_NOT_SUPPORTED                          = 6;
        public static final int VIX_E_FILE_ERROR                             = 7;
        public static final int VIX_E_DISK_FULL                              = 8;
        public static final int VIX_E_INCORRECT_FILE_TYPE                    = 9;
        public static final int VIX_E_CANCELLED                              = 10;
        public static final int VIX_E_FILE_READ_ONLY                         = 11;
        public static final int VIX_E_FILE_ALREADY_EXISTS                    = 12;
        public static final int VIX_E_FILE_ACCESS_ERROR                      = 13;
        public static final int VIX_E_REQUIRES_LARGE_FILES                   = 14;
        public static final int VIX_E_FILE_ALREADY_LOCKED                    = 15;
        public static final int VIX_E_VMDB                                   = 16;
        public static final int VIX_E_NOT_SUPPORTED_ON_REMOTE_OBJECT         = 20;
        public static final int VIX_E_FILE_TOO_BIG                           = 21;
        public static final int VIX_E_FILE_NAME_INVALID                      = 22;
        public static final int VIX_E_ALREADY_EXISTS                         = 23;
        public static final int VIX_E_BUFFER_TOOSMALL                        = 24;
        public static final int VIX_E_OBJECT_NOT_FOUND                       = 25;
        public static final int VIX_E_HOST_NOT_CONNECTED                     = 26;
        public static final int VIX_E_INVALID_UTF8_STRING                    = 27;
        public static final int VIX_E_OPERATION_ALREADY_IN_PROGRESS          = 31;
        public static final int VIX_E_UNFINISHED_JOB                         = 29;
        public static final int VIX_E_NEED_KEY                               = 30;
        public static final int VIX_E_LICENSE                                = 32;
        public static final int VIX_E_VM_HOST_DISCONNECTED                   = 34;
        public static final int VIX_E_AUTHENTICATION_FAIL                    = 35;
        public static final int VIX_E_HOST_CONNECTION_LOST                   = 36;
        public static final int VIX_E_DUPLICATE_NAME                         = 41;

        /* Handle Errors */
        public static final int VIX_E_INVALID_HANDLE                         = 1000;
        public static final int VIX_E_NOT_SUPPORTED_ON_HANDLE_TYPE           = 1001;
        public static final int VIX_E_TOO_MANY_HANDLES                       = 1002;

        /* XML errors */
        public static final int VIX_E_NOT_FOUND                              = 2000;
        public static final int VIX_E_TYPE_MISMATCH                          = 2001;
        public static final int VIX_E_INVALID_XML                            = 2002;

        /* VM Control Errors */
        public static final int VIX_E_TIMEOUT_WAITING_FOR_TOOLS              = 3000;
        public static final int VIX_E_UNRECOGNIZED_COMMAND                   = 3001;
        public static final int VIX_E_OP_NOT_SUPPORTED_ON_GUEST              = 3003;
        public static final int VIX_E_PROGRAM_NOT_STARTED                    = 3004;
        public static final int VIX_E_CANNOT_START_READ_ONLY_VM              = 3005;
        public static final int VIX_E_VM_NOT_RUNNING                         = 3006;
        public static final int VIX_E_VM_IS_RUNNING                          = 3007;
        public static final int VIX_E_CANNOT_CONNECT_TO_VM                   = 3008;
        public static final int VIX_E_POWEROP_SCRIPTS_NOT_AVAILABLE          = 3009;
        public static final int VIX_E_NO_GUEST_OS_INSTALLED                  = 3010;
        public static final int VIX_E_VM_INSUFFICIENT_HOST_MEMORY            = 3011;
        public static final int VIX_E_SUSPEND_ERROR                          = 3012;
        public static final int VIX_E_VM_NOT_ENOUGH_CPUS                     = 3013;
        public static final int VIX_E_HOST_USER_PERMISSIONS                  = 3014;
        public static final int VIX_E_GUEST_USER_PERMISSIONS                 = 3015;
        public static final int VIX_E_TOOLS_NOT_RUNNING                      = 3016;
        public static final int VIX_E_GUEST_OPERATIONS_PROHIBITED            = 3017;
        public static final int VIX_E_ANON_GUEST_OPERATIONS_PROHIBITED       = 3018;
        public static final int VIX_E_ROOT_GUEST_OPERATIONS_PROHIBITED       = 3019;
        public static final int VIX_E_MISSING_ANON_GUEST_ACCOUNT             = 3023;
        public static final int VIX_E_CANNOT_AUTHENTICATE_WITH_GUEST         = 3024;
        public static final int VIX_E_UNRECOGNIZED_COMMAND_IN_GUEST          = 3025;
        public static final int VIX_E_CONSOLE_GUEST_OPERATIONS_PROHIBITED    = 3026;
        public static final int VIX_E_MUST_BE_CONSOLE_USER                   = 3027;
        public static final int VIX_E_VMX_MSG_DIALOG_AND_NO_UI               = 3028;
        /* public static final int VIX_E_NOT_ALLOWED_DURING_VM_RECORDING        = 3029, Removed in version 1.11 */
        /* public static final int VIX_E_NOT_ALLOWED_DURING_VM_REPLAY           = 3030, Removed in version 1.11 */
        public static final int VIX_E_OPERATION_NOT_ALLOWED_FOR_LOGIN_TYPE   = 3031;
        public static final int VIX_E_LOGIN_TYPE_NOT_SUPPORTED               = 3032;
        public static final int VIX_E_EMPTY_PASSWORD_NOT_ALLOWED_IN_GUEST    = 3033;
        public static final int VIX_E_INTERACTIVE_SESSION_NOT_PRESENT        = 3034;
        public static final int VIX_E_INTERACTIVE_SESSION_USER_MISMATCH      = 3035;
        /* public static final int VIX_E_UNABLE_TO_REPLAY_VM                    = 3039, Removed in version 1.11 */
        public static final int VIX_E_CANNOT_POWER_ON_VM                     = 3041;
        public static final int VIX_E_NO_DISPLAY_SERVER                      = 3043;
        /* public static final int VIX_E_VM_NOT_RECORDING                       = 3044, Removed in version 1.11 */
        /* public static final int VIX_E_VM_NOT_REPLAYING                       = 3045, Removed in version 1.11 */
        public static final int VIX_E_TOO_MANY_LOGONS                        = 3046;
        public static final int VIX_E_INVALID_AUTHENTICATION_SESSION         = 3047;

        /* VM Errors */
        public static final int VIX_E_VM_NOT_FOUND                           = 4000;
        public static final int VIX_E_NOT_SUPPORTED_FOR_VM_VERSION           = 4001;
        public static final int VIX_E_CANNOT_READ_VM_CONFIG                  = 4002;
        public static final int VIX_E_TEMPLATE_VM                            = 4003;
        public static final int VIX_E_VM_ALREADY_LOADED                      = 4004;
        public static final int VIX_E_VM_ALREADY_UP_TO_DATE                  = 4006;
        public static final int VIX_E_VM_UNSUPPORTED_GUEST                   = 4011;

        /* Property Errors */
        public static final int VIX_E_UNRECOGNIZED_PROPERTY                  = 6000;
        public static final int VIX_E_INVALID_PROPERTY_VALUE                 = 6001;
        public static final int VIX_E_READ_ONLY_PROPERTY                     = 6002;
        public static final int VIX_E_MISSING_REQUIRED_PROPERTY              = 6003;
        public static final int VIX_E_INVALID_SERIALIZED_DATA                = 6004;
        public static final int VIX_E_PROPERTY_TYPE_MISMATCH                 = 6005;

        /* Completion Errors */
        public static final int VIX_E_BAD_VM_INDEX                           = 8000;

        /* Message errors */
        public static final int VIX_E_INVALID_MESSAGE_HEADER                 = 10000;
        public static final int VIX_E_INVALID_MESSAGE_BODY                   = 10001;

        /* Snapshot errors */
        public static final int VIX_E_SNAPSHOT_INVAL                         = 13000;
        public static final int VIX_E_SNAPSHOT_DUMPER                        = 13001;
        public static final int VIX_E_SNAPSHOT_DISKLIB                       = 13002;
        public static final int VIX_E_SNAPSHOT_NOTFOUND                      = 13003;
        public static final int VIX_E_SNAPSHOT_EXISTS                        = 13004;
        public static final int VIX_E_SNAPSHOT_VERSION                       = 13005;
        public static final int VIX_E_SNAPSHOT_NOPERM                        = 13006;
        public static final int VIX_E_SNAPSHOT_CONFIG                        = 13007;
        public static final int VIX_E_SNAPSHOT_NOCHANGE                      = 13008;
        public static final int VIX_E_SNAPSHOT_CHECKPOINT                    = 13009;
        public static final int VIX_E_SNAPSHOT_LOCKED                        = 13010;
        public static final int VIX_E_SNAPSHOT_INCONSISTENT                  = 13011;
        public static final int VIX_E_SNAPSHOT_NAMETOOLONG                   = 13012;
        public static final int VIX_E_SNAPSHOT_VIXFILE                       = 13013;
        public static final int VIX_E_SNAPSHOT_DISKLOCKED                    = 13014;
        public static final int VIX_E_SNAPSHOT_DUPLICATEDDISK                = 13015;
        public static final int VIX_E_SNAPSHOT_INDEPENDENTDISK               = 13016;
        public static final int VIX_E_SNAPSHOT_NONUNIQUE_NAME                = 13017;
        public static final int VIX_E_SNAPSHOT_MEMORY_ON_INDEPENDENT_DISK    = 13018;
        public static final int VIX_E_SNAPSHOT_MAXSNAPSHOTS                  = 13019;
        public static final int VIX_E_SNAPSHOT_MIN_FREE_SPACE                = 13020;
        public static final int VIX_E_SNAPSHOT_HIERARCHY_TOODEEP             = 13021;
        public static final int VIX_E_SNAPSHOT_RRSUSPEND                     = 13022;
        public static final int VIX_E_SNAPSHOT_NOT_REVERTABLE                = 13024;

        /* Host Errors */
        public static final int VIX_E_HOST_DISK_INVALID_VALUE                = 14003;
        public static final int VIX_E_HOST_DISK_SECTORSIZE                   = 14004;
        public static final int VIX_E_HOST_FILE_ERROR_EOF                    = 14005;
        public static final int VIX_E_HOST_NETBLKDEV_HANDSHAKE               = 14006;
        public static final int VIX_E_HOST_SOCKET_CREATION_ERROR             = 14007;
        public static final int VIX_E_HOST_SERVER_NOT_FOUND                  = 14008;
        public static final int VIX_E_HOST_NETWORK_CONN_REFUSED              = 14009;
        public static final int VIX_E_HOST_TCP_SOCKET_ERROR                  = 14010;
        public static final int VIX_E_HOST_TCP_CONN_LOST                     = 14011;
        public static final int VIX_E_HOST_NBD_HASHFILE_VOLUME               = 14012;
        public static final int VIX_E_HOST_NBD_HASHFILE_INIT                 = 14013;

        /* Disklib errors */
        public static final int VIX_E_DISK_INVAL                             = 16000;
        public static final int VIX_E_DISK_NOINIT                            = 16001;
        public static final int VIX_E_DISK_NOIO                              = 16002;
        public static final int VIX_E_DISK_PARTIALCHAIN                      = 16003;
        public static final int VIX_E_DISK_NEEDSREPAIR                       = 16006;
        public static final int VIX_E_DISK_OUTOFRANGE                        = 16007;
        public static final int VIX_E_DISK_CID_MISMATCH                      = 16008;
        public static final int VIX_E_DISK_CANTSHRINK                        = 16009;
        public static final int VIX_E_DISK_PARTMISMATCH                      = 16010;
        public static final int VIX_E_DISK_UNSUPPORTEDDISKVERSION            = 16011;
        public static final int VIX_E_DISK_OPENPARENT                        = 16012;
        public static final int VIX_E_DISK_NOTSUPPORTED                      = 16013;
        public static final int VIX_E_DISK_NEEDKEY                           = 16014;
        public static final int VIX_E_DISK_NOKEYOVERRIDE                     = 16015;
        public static final int VIX_E_DISK_NOTENCRYPTED                      = 16016;
        public static final int VIX_E_DISK_NOKEY                             = 16017;
        public static final int VIX_E_DISK_INVALIDPARTITIONTABLE             = 16018;
        public static final int VIX_E_DISK_NOTNORMAL                         = 16019;
        public static final int VIX_E_DISK_NOTENCDESC                        = 16020;
        public static final int VIX_E_DISK_NEEDVMFS                          = 16022;
        public static final int VIX_E_DISK_RAWTOOBIG                         = 16024;
        public static final int VIX_E_DISK_TOOMANYOPENFILES                  = 16027;
        public static final int VIX_E_DISK_TOOMANYREDO                       = 16028;
        public static final int VIX_E_DISK_RAWTOOSMALL                       = 16029;
        public static final int VIX_E_DISK_INVALIDCHAIN                      = 16030;
        public static final int VIX_E_DISK_KEY_NOTFOUND                      = 16052; // metadata key is not found
        public static final int VIX_E_DISK_SUBSYSTEM_INIT_FAIL               = 16053;
        public static final int VIX_E_DISK_INVALID_CONNECTION                = 16054;
        public static final int VIX_E_DISK_ENCODING                          = 16061;
        public static final int VIX_E_DISK_CANTREPAIR                        = 16062;
        public static final int VIX_E_DISK_INVALIDDISK                       = 16063;
        public static final int VIX_E_DISK_NOLICENSE                         = 16064;
        public static final int VIX_E_DISK_NODEVICE                          = 16065;
        public static final int VIX_E_DISK_UNSUPPORTEDDEVICE                 = 16066;
        public static final int VIX_E_DISK_CAPACITY_MISMATCH                 = 16067;
        public static final int VIX_E_DISK_PARENT_NOTALLOWED                 = 16068;
        public static final int VIX_E_DISK_ATTACH_ROOTLINK                   = 16069;

        /* Crypto Library Errors */
        public static final int VIX_E_CRYPTO_UNKNOWN_ALGORITHM               = 17000;
        public static final int VIX_E_CRYPTO_BAD_BUFFER_SIZE                 = 17001;
        public static final int VIX_E_CRYPTO_INVALID_OPERATION               = 17002;
        public static final int VIX_E_CRYPTO_RANDOM_DEVICE                   = 17003;
        public static final int VIX_E_CRYPTO_NEED_PASSWORD                   = 17004;
        public static final int VIX_E_CRYPTO_BAD_PASSWORD                    = 17005;
        public static final int VIX_E_CRYPTO_NOT_IN_DICTIONARY               = 17006;
        public static final int VIX_E_CRYPTO_NO_CRYPTO                       = 17007;
        public static final int VIX_E_CRYPTO_ERROR                           = 17008;
        public static final int VIX_E_CRYPTO_BAD_FORMAT                      = 17009;
        public static final int VIX_E_CRYPTO_LOCKED                          = 17010;
        public static final int VIX_E_CRYPTO_EMPTY                           = 17011;
        public static final int VIX_E_CRYPTO_KEYSAFE_LOCATOR                 = 17012;

        /* Remoting Errors. */
        public static final int VIX_E_CANNOT_CONNECT_TO_HOST                 = 18000;
        public static final int VIX_E_NOT_FOR_REMOTE_HOST                    = 18001;
        public static final int VIX_E_INVALID_HOSTNAME_SPECIFICATION         = 18002;

        /* Screen Capture Errors. */
        public static final int VIX_E_SCREEN_CAPTURE_ERROR                   = 19000;
        public static final int VIX_E_SCREEN_CAPTURE_BAD_FORMAT              = 19001;
        public static final int VIX_E_SCREEN_CAPTURE_COMPRESSION_FAIL        = 19002;
        public static final int VIX_E_SCREEN_CAPTURE_LARGE_DATA              = 19003;

        /* Guest Errors */
        public static final int VIX_E_GUEST_VOLUMES_NOT_FROZEN               = 20000;
        public static final int VIX_E_NOT_A_FILE                             = 20001;
        public static final int VIX_E_NOT_A_DIRECTORY                        = 20002;
        public static final int VIX_E_NO_SUCH_PROCESS                        = 20003;
        public static final int VIX_E_FILE_NAME_TOO_LONG                     = 20004;
        public static final int VIX_E_OPERATION_DISABLED                     = 20005;

        /* Tools install errors */
        public static final int VIX_E_TOOLS_INSTALL_NO_IMAGE                 = 21000;
        public static final int VIX_E_TOOLS_INSTALL_IMAGE_INACCESIBLE        = 21001;
        public static final int VIX_E_TOOLS_INSTALL_NO_DEVICE                = 21002;
        public static final int VIX_E_TOOLS_INSTALL_DEVICE_NOT_CONNECTED     = 21003;
        public static final int VIX_E_TOOLS_INSTALL_CANCELLED                = 21004;
        public static final int VIX_E_TOOLS_INSTALL_INIT_FAILED              = 21005;
        public static final int VIX_E_TOOLS_INSTALL_AUTO_NOT_SUPPORTED       = 21006;
        public static final int VIX_E_TOOLS_INSTALL_GUEST_NOT_READY          = 21007;
        public static final int VIX_E_TOOLS_INSTALL_SIG_CHECK_FAILED         = 21008;
        public static final int VIX_E_TOOLS_INSTALL_ERROR                    = 21009;
        public static final int VIX_E_TOOLS_INSTALL_ALREADY_UP_TO_DATE       = 21010;
        public static final int VIX_E_TOOLS_INSTALL_IN_PROGRESS              = 21011;

        /* Wrapper Errors */
        public static final int VIX_E_WRAPPER_WORKSTATION_NOT_INSTALLED      = 22001;
        public static final int VIX_E_WRAPPER_VERSION_NOT_FOUND              = 22002;
        public static final int VIX_E_WRAPPER_SERVICEPROVIDER_NOT_FOUND      = 22003;
        public static final int VIX_E_WRAPPER_PLAYER_NOT_INSTALLED           = 22004;
        public static final int VIX_E_WRAPPER_RUNTIME_NOT_INSTALLED          = 22005;
        public static final int VIX_E_WRAPPER_MULTIPLE_SERVICEPROVIDERS      = 22006;

        /* FuseMnt errors*/
        public static final int VIX_E_MNTAPI_MOUNTPT_NOT_FOUND               = 24000;
        public static final int VIX_E_MNTAPI_MOUNTPT_IN_USE                  = 24001;
        public static final int VIX_E_MNTAPI_DISK_NOT_FOUND                  = 24002;
        public static final int VIX_E_MNTAPI_DISK_NOT_MOUNTED                = 24003;
        public static final int VIX_E_MNTAPI_DISK_IS_MOUNTED                 = 24004;
        public static final int VIX_E_MNTAPI_DISK_NOT_SAFE                   = 24005;
        public static final int VIX_E_MNTAPI_DISK_CANT_OPEN                  = 24006;
        public static final int VIX_E_MNTAPI_CANT_READ_PARTS                 = 24007;
        public static final int VIX_E_MNTAPI_UMOUNT_APP_NOT_FOUND            = 24008;
        public static final int VIX_E_MNTAPI_UMOUNT                          = 24009;
        public static final int VIX_E_MNTAPI_NO_MOUNTABLE_PARTITONS          = 24010;
        public static final int VIX_E_MNTAPI_PARTITION_RANGE                 = 24011;
        public static final int VIX_E_MNTAPI_PERM                            = 24012;
        public static final int VIX_E_MNTAPI_DICT                            = 24013;
        public static final int VIX_E_MNTAPI_DICT_LOCKED                     = 24014;
        public static final int VIX_E_MNTAPI_OPEN_HANDLES                    = 24015;
        public static final int VIX_E_MNTAPI_CANT_MAKE_VAR_DIR               = 24016;
        public static final int VIX_E_MNTAPI_NO_ROOT                         = 24017;
        public static final int VIX_E_MNTAPI_LOOP_FAILED                     = 24018;
        public static final int VIX_E_MNTAPI_DAEMON                          = 24019;
        public static final int VIX_E_MNTAPI_INTERNAL                        = 24020;
        public static final int VIX_E_MNTAPI_SYSTEM                          = 24021;
        public static final int VIX_E_MNTAPI_NO_CONNECTION_DETAILS           = 24022;
        /* FuseMnt errors: Do not exceed 24299 */

        /* VixMntapi errors*/
        public static final int VIX_E_MNTAPI_INCOMPATIBLE_VERSION            = 24300;
        public static final int VIX_E_MNTAPI_OS_ERROR                        = 24301;
        public static final int VIX_E_MNTAPI_DRIVE_LETTER_IN_USE             = 24302;
        public static final int VIX_E_MNTAPI_DRIVE_LETTER_ALREADY_ASSIGNED   = 24303;
        public static final int VIX_E_MNTAPI_VOLUME_NOT_MOUNTED              = 24304;
        public static final int VIX_E_MNTAPI_VOLUME_ALREADY_MOUNTED          = 24305;
        public static final int VIX_E_MNTAPI_FORMAT_FAILURE                  = 24306;
        public static final int VIX_E_MNTAPI_NO_DRIVER                       = 24307;
        public static final int VIX_E_MNTAPI_ALREADY_OPENED                  = 24308;
        public static final int VIX_E_MNTAPI_ITEM_NOT_FOUND                  = 24309;
        public static final int VIX_E_MNTAPI_UNSUPPROTED_BOOT_LOADER         = 24310;
        public static final int VIX_E_MNTAPI_UNSUPPROTED_OS                  = 24311;
        public static final int VIX_E_MNTAPI_CODECONVERSION                  = 24312;
        public static final int VIX_E_MNTAPI_REGWRITE_ERROR                  = 24313;
        public static final int VIX_E_MNTAPI_UNSUPPORTED_FT_VOLUME           = 24314;
        public static final int VIX_E_MNTAPI_PARTITION_NOT_FOUND             = 24315;
        public static final int VIX_E_MNTAPI_PUTFILE_ERROR                   = 24316;
        public static final int VIX_E_MNTAPI_GETFILE_ERROR                   = 24317;
        public static final int VIX_E_MNTAPI_REG_NOT_OPENED                  = 24318;
        public static final int VIX_E_MNTAPI_REGDELKEY_ERROR                 = 24319;
        public static final int VIX_E_MNTAPI_CREATE_PARTITIONTABLE_ERROR     = 24320;
        public static final int VIX_E_MNTAPI_OPEN_FAILURE                    = 24321;
        public static final int VIX_E_MNTAPI_VOLUME_NOT_WRITABLE             = 24322;

        /* Network Errors */
        public static final int VIX_E_NET_HTTP_UNSUPPORTED_PROTOCOL     = 30001;
        public static final int VIX_E_NET_HTTP_URL_MALFORMAT            = 30003;
        public static final int VIX_E_NET_HTTP_COULDNT_RESOLVE_PROXY    = 30005;
        public static final int VIX_E_NET_HTTP_COULDNT_RESOLVE_HOST     = 30006;
        public static final int VIX_E_NET_HTTP_COULDNT_CONNECT          = 30007;
        public static final int VIX_E_NET_HTTP_HTTP_RETURNED_ERROR      = 30022;
        public static final int VIX_E_NET_HTTP_OPERATION_TIMEDOUT       = 30028;
        public static final int VIX_E_NET_HTTP_SSL_CONNECT_ERROR        = 30035;
        public static final int VIX_E_NET_HTTP_TOO_MANY_REDIRECTS       = 30047;
        public static final int VIX_E_NET_HTTP_TRANSFER                 = 30200;
        public static final int VIX_E_NET_HTTP_SSL_SECURITY             = 30201;
        public static final int VIX_E_NET_HTTP_GENERIC                  = 30202;


    /*
 * VIX Property Type
 */
    public static final int VIX_PROPERTYTYPE_ANY             = 0;
    public static final int VIX_PROPERTYTYPE_INTEGER         = 1;
    public static final int VIX_PROPERTYTYPE_STRING          = 2;
    public static final int VIX_PROPERTYTYPE_BOOL            = 3;
    public static final int VIX_PROPERTYTYPE_HANDLE          = 4;
    public static final int VIX_PROPERTYTYPE_INT64           = 5;
    public static final int VIX_PROPERTYTYPE_BLOB            = 6;


    /*
 * VIX Property ID's
 */
    public static final int VIX_PROPERTY_NONE                                  = 0;

    /* Properties used by several handle types. */
    public static final int VIX_PROPERTY_META_DATA_CONTAINER                   = 2;

    /* public static final int VIX_HANDLETYPE_HOST properties */
    public static final int VIX_PROPERTY_HOST_HOSTTYPE                         = 50;
    public static final int VIX_PROPERTY_HOST_API_VERSION                      = 51;

    /* public static final int VIX_HANDLETYPE_VM properties */
    public static final int VIX_PROPERTY_VM_NUM_VCPUS                          = 101;
    public static final int VIX_PROPERTY_VM_VMX_PATHNAME                       = 103;
    public static final int VIX_PROPERTY_VM_VMTEAM_PATHNAME                    = 105;
    public static final int VIX_PROPERTY_VM_MEMORY_SIZE                        = 106;
    public static final int VIX_PROPERTY_VM_READ_ONLY                          = 107;
    public static final int VIX_PROPERTY_VM_NAME                               = 108;
    public static final int VIX_PROPERTY_VM_GUESTOS                            = 109;
    public static final int VIX_PROPERTY_VM_IN_VMTEAM                          = 128;
    public static final int VIX_PROPERTY_VM_POWER_STATE                        = 129;
    public static final int VIX_PROPERTY_VM_TOOLS_STATE                        = 152;
    public static final int VIX_PROPERTY_VM_IS_RUNNING                         = 196;
    public static final int VIX_PROPERTY_VM_SUPPORTED_FEATURES                 = 197;
    /* public static final int VIX_PROPERTY_VM_IS_RECORDING                       = 236, Removed in version 1.11 */
    /* public static final int VIX_PROPERTY_VM_IS_REPLAYING                       = 237, Removed in version 1.11 */
    public static final int VIX_PROPERTY_VM_SSL_ERROR                          = 293;

    /* Result properties; these are returned by various procedures */
    public static final int VIX_PROPERTY_JOB_RESULT_ERROR_CODE                 = 3000;
    public static final int VIX_PROPERTY_JOB_RESULT_VM_IN_GROUP                = 3001;
    public static final int VIX_PROPERTY_JOB_RESULT_USER_MESSAGE               = 3002;
    public static final int VIX_PROPERTY_JOB_RESULT_EXIT_CODE                  = 3004;
    public static final int VIX_PROPERTY_JOB_RESULT_COMMAND_OUTPUT             = 3005;
    public static final int VIX_PROPERTY_JOB_RESULT_HANDLE                     = 3010;
    public static final int VIX_PROPERTY_JOB_RESULT_GUEST_OBJECT_EXISTS        = 3011;
    public static final int VIX_PROPERTY_JOB_RESULT_GUEST_PROGRAM_ELAPSED_TIME = 3017;
    public static final int VIX_PROPERTY_JOB_RESULT_GUEST_PROGRAM_EXIT_CODE    = 3018;
    public static final int VIX_PROPERTY_JOB_RESULT_ITEM_NAME                  = 3035;
    public static final int VIX_PROPERTY_JOB_RESULT_FOUND_ITEM_DESCRIPTION     = 3036;
    public static final int VIX_PROPERTY_JOB_RESULT_SHARED_FOLDER_COUNT        = 3046;
    public static final int VIX_PROPERTY_JOB_RESULT_SHARED_FOLDER_HOST         = 3048;
    public static final int VIX_PROPERTY_JOB_RESULT_SHARED_FOLDER_FLAGS        = 3049;
    public static final int VIX_PROPERTY_JOB_RESULT_PROCESS_ID                 = 3051;
    public static final int VIX_PROPERTY_JOB_RESULT_PROCESS_OWNER              = 3052;
    public static final int VIX_PROPERTY_JOB_RESULT_PROCESS_COMMAND            = 3053;
    public static final int VIX_PROPERTY_JOB_RESULT_FILE_FLAGS                 = 3054;
    public static final int VIX_PROPERTY_JOB_RESULT_PROCESS_START_TIME         = 3055;
    public static final int VIX_PROPERTY_JOB_RESULT_VM_VARIABLE_STRING         = 3056;
    public static final int VIX_PROPERTY_JOB_RESULT_PROCESS_BEING_DEBUGGED     = 3057;
    public static final int VIX_PROPERTY_JOB_RESULT_SCREEN_IMAGE_SIZE          = 3058;
    public static final int VIX_PROPERTY_JOB_RESULT_SCREEN_IMAGE_DATA          = 3059;
    public static final int VIX_PROPERTY_JOB_RESULT_FILE_SIZE                  = 3061;
    public static final int VIX_PROPERTY_JOB_RESULT_FILE_MOD_TIME              = 3062;
    public static final int VIX_PROPERTY_JOB_RESULT_EXTRA_ERROR_INFO           = 3084;

    /* Event properties; these are sent in the moreEventInfo for some events. */
    public static final int VIX_PROPERTY_FOUND_ITEM_LOCATION                   = 4010;

    /* public static final int VIX_HANDLETYPE_SNAPSHOT properties */
    public static final int VIX_PROPERTY_SNAPSHOT_DISPLAYNAME                  = 4200;
    public static final int VIX_PROPERTY_SNAPSHOT_DESCRIPTION                  = 4201;
    public static final int VIX_PROPERTY_SNAPSHOT_POWERSTATE                   = 4205;
    /* public static final int VIX_PROPERTY_SNAPSHOT_IS_REPLAYABLE                = 4207, Removed in version 1.11 */

    public static final int VIX_PROPERTY_GUEST_SHAREDFOLDERS_SHARES_PATH       = 4525;

    /* Virtual machine encryption properties */
    public static final int VIX_PROPERTY_VM_ENCRYPTION_PASSWORD                = 7001;


    /*
 * These are events that may be signalled by calling a procedure
 * of type VixEventProc.
 */
    public static final int VIX_EVENTTYPE_JOB_COMPLETED          = 2;
    public static final int VIX_EVENTTYPE_JOB_PROGRESS           = 3;
    public static final int VIX_EVENTTYPE_FIND_ITEM              = 8;
    public static final int VIX_EVENTTYPE_CALLBACK_SIGNALLED     = 2;  // Deprecated - Use public static final int VIX_EVENTTYPE_JOB_COMPLETED instead.



    /*
 * These are the property flags for each file.
 */

    public static final int VIX_FILE_ATTRIBUTES_DIRECTORY       = 0x0001;
    public static final int VIX_FILE_ATTRIBUTES_SYMLINK         = 0x0002;



    /*
    * The following option was removed in version 1.11.
      public static final int VIX_HOSTOPTION_USE_EVENT_PUMP        = 0x0008;
    */
    public static final int VIX_HOSTOPTION_VERIFY_SSL_CERT       = 0x4000;


    public static final int VIX_SERVICEPROVIDER_DEFAULT                   = 1;
    public static final int VIX_SERVICEPROVIDER_VMWARE_SERVER             = 2;
    public static final int VIX_SERVICEPROVIDER_VMWARE_WORKSTATION        = 3;
    public static final int VIX_SERVICEPROVIDER_VMWARE_PLAYER             = 4;
    public static final int VIX_SERVICEPROVIDER_VMWARE_VI_SERVER          = 10;
    public static final int VIX_SERVICEPROVIDER_VMWARE_WORKSTATION_SHARED = 11;

    public static final int VIX_API_VERSION      = -1;

    public static final int VIX_FIND_RUNNING_VMS         = 1;
    public static final int VIX_FIND_REGISTERED_VMS      = 4;

    public static final int VIX_VMOPEN_NORMAL  = 0x0;


    public static final int VIX_VMPOWEROP_NORMAL                      = 0;
    public static final int VIX_VMPOWEROP_FROM_GUEST                  = 0x0004;
    public static final int VIX_VMPOWEROP_SUPPRESS_SNAPSHOT_POWERON   = 0x0080;
    public static final int VIX_VMPOWEROP_LAUNCH_GUI                  = 0x0200;
    public static final int VIX_VMPOWEROP_START_VM_PAUSED             = 0x1000;

    public static final int VIX_VMDELETE_DISK_FILES     = 0x0002;


    public static final int VIX_POWERSTATE_POWERING_OFF    = 0x0001;
    public static final int VIX_POWERSTATE_POWERED_OFF     = 0x0002;
    public static final int VIX_POWERSTATE_POWERING_ON     = 0x0004;
    public static final int VIX_POWERSTATE_POWERED_ON      = 0x0008;
    public static final int VIX_POWERSTATE_SUSPENDING      = 0x0010;
    public static final int VIX_POWERSTATE_SUSPENDED       = 0x0020;
    public static final int VIX_POWERSTATE_TOOLS_RUNNING   = 0x0040;
    public static final int VIX_POWERSTATE_RESETTING       = 0x0080;
    public static final int VIX_POWERSTATE_BLOCKED_ON_MSG  = 0x0100;
    public static final int VIX_POWERSTATE_PAUSED          = 0x0200;
    public static final int VIX_POWERSTATE_RESUMING        = 0x0800;


    public static final int VIX_TOOLSSTATE_UNKNOWN           = 0x0001;
    public static final int VIX_TOOLSSTATE_RUNNING           = 0x0002;
    public static final int VIX_TOOLSSTATE_NOT_INSTALLED     = 0x0004;

    public static final int VIX_VM_SUPPORT_SHARED_FOLDERS       = 0x0001;
    public static final int VIX_VM_SUPPORT_MULTIPLE_SNAPSHOTS   = 0x0002;
    public static final int VIX_VM_SUPPORT_TOOLS_INSTALL        = 0x0004;
    public static final int VIX_VM_SUPPORT_HARDWARE_UPGRADE     = 0x0008;

    public static final int VIX_LOGIN_IN_GUEST_REQUIRE_INTERACTIVE_ENVIRONMENT      = 0x08;


    public static final int VIX_RUNPROGRAM_RETURN_IMMEDIATELY   = 0x0001;
    public static final int VIX_RUNPROGRAM_ACTIVATE_WINDOW      = 0x0002;

    public static final int VIX_VM_GUEST_VARIABLE            = 1;
    public static final int VIX_VM_CONFIG_RUNTIME_ONLY       = 2;
    public static final int VIX_GUEST_ENVIRONMENT_VARIABLE   = 3;


    public static final int VIX_SNAPSHOT_REMOVE_CHILDREN    = 0x0001;


    public static final int VIX_SNAPSHOT_INCLUDE_MEMORY     = 0x0002;


    public static final int VIX_SHAREDFOLDER_WRITE_ACCESS     = 0x04;


    public static final int VIX_CAPTURESCREENFORMAT_PNG            = 0x01;
    public static final int VIX_CAPTURESCREENFORMAT_PNG_NOCOMPRESS = 0x02;

    public static final int VIX_CLONETYPE_FULL       = 0;
    public static final int VIX_CLONETYPE_LINKED     = 1;

    public static final int VIX_INSTALLTOOLS_MOUNT_TOOLS_INSTALLER = 0x00;
    public static final int VIX_INSTALLTOOLS_AUTO_UPGRADE          = 0x01;
    public static final int VIX_INSTALLTOOLS_RETURN_IMMEDIATELY    = 0x02;


    void Vix_ReleaseHandle(int handle);

    void Vix_AddRefHandle(int handle);

    int Vix_GetHandleType(int handle);

    int Vix_GetProperties(int handle,
                               int firstPropertyID, Object... args);

    int Vix_GetPropertyType(int handle, int propertyID,
                                 IntByReference propertyType);

    void Vix_FreeBuffer(Pointer p);


    int VixHost_Connect(int apiVersion,
                              int hostType,
                              String hostName,
                              int hostPort,
                              String userName,
                              String password,
                              int options,
                              int propertyListHandle,
                              VixEventProcCallback callbackProc,
                              Pointer clientData);

    void VixHost_Disconnect(int hostHandle);

/*
 * VM Registration
 */

    int VixHost_RegisterVM(int hostHandle,
                                 String vmxFilePath,
                                 VixEventProcCallback callbackProc,
                                 Pointer clientData);

    int VixHost_UnregisterVM(int hostHandle,
                                   String vmxFilePath,
                                   VixEventProcCallback callbackProc,
                                   Pointer clientData);


    int VixHost_FindItems(int hostHandle,
                                int searchType,
                                int searchCriteria,
                                int timeout,
                                VixEventProcCallback callbackProc,
                                Pointer clientData);

    int VixHost_OpenVM(int hostHandle,
                             String vmxFilePathName,
                             int options,
                             int propertyListHandle,
                             VixEventProcCallback callbackProc,
                             Pointer clientData);

    int VixPropertyList_AllocPropertyList(int hostHandle,
                                               IntByReference resultHandle,
                                               int firstPropertyID,
                                               Object... args);

    int VixVM_Open(int hostHandle,
                         String vmxFilePathName,
                         VixEventProcCallback callbackProc,
                         Pointer clientData);


    /*
 * Power operations
 */

    int VixVM_PowerOn(int vmHandle,
                            int powerOnOptions,
                            int propertyListHandle,
                            VixEventProcCallback callbackProc,
                            Pointer clientData);

    int VixVM_PowerOff(int vmHandle,
                             int powerOffOptions,
                             VixEventProcCallback callbackProc,
                             Pointer clientData);

    int VixVM_Reset(int vmHandle,
                          int resetOptions,
                          VixEventProcCallback callbackProc,
                          Pointer clientData);

    int VixVM_Suspend(int vmHandle,
                            int suspendOptions,
                            VixEventProcCallback callbackProc,
                            Pointer clientData);

    int VixVM_Pause(int vmHandle,
                          int options,
                          int propertyList,
                          VixEventProcCallback callbackProc,
                          Pointer clientData);

    int VixVM_Unpause(int vmHandle,
                            int options,
                            int propertyList,
                            VixEventProcCallback callbackProc,
                            Pointer clientData);


    int VixVM_WaitForToolsInGuest(int vmHandle,
                                        int timeoutInSeconds,
                                        VixEventProcCallback callbackProc,
                                        Pointer clientData);

    int VixVM_LoginInGuest(int vmHandle,
                                 String userName,
                                 String password,
                                 int options,
                                 VixEventProcCallback callbackProc,
                                 Pointer clientData);

    int VixVM_LogoutFromGuest(int vmHandle,
                                    VixEventProcCallback callbackProc,
                                    Pointer clientData);

    int VixVM_RunProgramInGuest(int vmHandle,
                                      String guestProgramName,
                                      String commandLineArgs,
                                      int options,
                                      int propertyListHandle,
                                      VixEventProcCallback callbackProc,
                                      Pointer clientData);

    int VixVM_ListProcessesInGuest(int vmHandle,
                                         int options,
                                         VixEventProcCallback callbackProc,
                                         Pointer clientData);

    int VixVM_KillProcessInGuest(int vmHandle,
                                       int pid,
                                       int options,
                                       VixEventProcCallback callbackProc,
                                       Pointer clientData);

    int VixVM_RunScriptInGuest(int vmHandle,
                                     String interpreter,
                                     String scriptText,
                                     int options,
                                     int propertyListHandle,
                                     VixEventProcCallback callbackProc,
                                     Pointer clientData);
/*
 * Guest File functions
 */

    int VixVM_CopyFileFromHostToGuest(int vmHandle,
                                            String hostPathName,
                                            String guestPathName,
                                            int options,
                                            int propertyListHandle,
                                            VixEventProcCallback callbackProc,
                                            Pointer clientData);

    int VixVM_CopyFileFromGuestToHost(int vmHandle,
                                            String guestPathName,
                                            String hostPathName,
                                            int options,
                                            int propertyListHandle,
                                            VixEventProcCallback callbackProc,
                                            Pointer clientData);

    int VixVM_DeleteFileInGuest(int vmHandle,
                                      String guestPathName,
                                      VixEventProcCallback callbackProc,
                                      Pointer clientData);

    int VixVM_FileExistsInGuest(int vmHandle,
                                      String guestPathName,
                                      VixEventProcCallback callbackProc,
                                      Pointer clientData);

    int VixVM_RenameFileInGuest(int vmHandle,
                                      String oldName,
                                      String newName,
                                      int options,
                                      int propertyListHandle,
                                      VixEventProcCallback callbackProc,
                                      Pointer clientData);

    int VixVM_CreateTempFileInGuest(int vmHandle,
                                          int options,
                                          int propertyListHandle,
                                          VixEventProcCallback callbackProc,
                                          Pointer clientData);

    int VixVM_GetFileInfoInGuest(int vmHandle,
                                       String pathName,
                                       VixEventProcCallback callbackProc,
                                       Pointer clientData);


/*
 * Guest Directory functions
 */

    int VixVM_ListDirectoryInGuest(int vmHandle,
                                         String pathName,
                                         int options,
                                         VixEventProcCallback callbackProc,
                                         Pointer clientData);

    int VixVM_CreateDirectoryInGuest(int vmHandle,
                                           String pathName,
                                           int propertyListHandle,
                                           VixEventProcCallback callbackProc,
                                           Pointer clientData);

    int VixVM_DeleteDirectoryInGuest(int vmHandle,
                                           String pathName,
                                           int options,
                                           VixEventProcCallback callbackProc,
                                           Pointer clientData);

    int VixVM_DirectoryExistsInGuest(int vmHandle,
                                           String pathName,
                                           VixEventProcCallback callbackProc,
                                           Pointer clientData);


    int VixVM_ReadVariable(int vmHandle,
                                 int variableType,
                                 String name,
                                 int options,
                                 VixEventProcCallback callbackProc,
                                 Pointer clientData);

    int VixVM_WriteVariable(int vmHandle,
                                  int variableType,
                                  String valueName,
                                  String value,
                                  int options,
                                  VixEventProcCallback callbackProc,
                                  Pointer clientData);


/*
 * Snapshot functions that operate on a VM
 */

    int VixVM_GetNumRootSnapshots(int vmHandle,
                                       IntByReference result);

    int VixVM_GetRootSnapshot(int vmHandle,
                                   int index,
                                   IntByReference snapshotHandle);

    int VixVM_GetCurrentSnapshot(int vmHandle,
                                      IntByReference snapshotHandle);

    int VixVM_GetNamedSnapshot(int vmHandle,
                                    String name,
                                    IntByReference snapshotHandle);

    int VixVM_RemoveSnapshot(int vmHandle,
                                   int snapshotHandle,
                                   int options,
                                   VixEventProcCallback callbackProc,
                                   Pointer clientData);

    int VixVM_RevertToSnapshot(int vmHandle,
                                     int snapshotHandle,
                                     int options,
                                     int propertyListHandle,
                                     VixEventProcCallback callbackProc,
                                     Pointer clientData);


    int VixVM_CreateSnapshot(int vmHandle,
                                   String name,
                                   String description,
                                   int options,
                                   int propertyListHandle,
                                   VixEventProcCallback callbackProc,
                                   Pointer clientData);

    int VixVM_EnableSharedFolders(int vmHandle,
                                        int enabled,
                                        int options,
                                        VixEventProcCallback callbackProc,
                                        Pointer clientData);

    int VixVM_GetNumSharedFolders(int vmHandle,
                                        VixEventProcCallback callbackProc,
                                        Pointer clientData);

    int VixVM_GetSharedFolderState(int vmHandle,
                                         int index,
                                         VixEventProcCallback callbackProc,
                                         Pointer clientData);

    int VixVM_SetSharedFolderState(int vmHandle,
                                         String shareName,
                                         String hostPathName,
                                         int flags,
                                         VixEventProcCallback callbackProc,
                                         Pointer clientData);

    int VixVM_AddSharedFolder(int vmHandle,
                                    String shareName,
                                    String hostPathName,
                                    int flags,
                                    VixEventProcCallback callbackProc,
                                    Pointer clientData);

    int VixVM_RemoveSharedFolder(int vmHandle,
                                       String shareName,
                                       int flags,
                                       VixEventProcCallback callbackProc,
                                       Pointer clientData);

    int VixVM_CaptureScreenImage(int vmHandle,
                                       int captureType,
                                       int additionalProperties,
                                       VixEventProcCallback callbackProc,
                                       Pointer clientdata);

    int VixVM_Clone(int vmHandle,
                          int snapshotHandle,
                          int cloneType,
                          String destConfigPathName,
                          int options,
                          int propertyListHandle,
                          VixEventProcCallback callbackProc,
                          Pointer clientData);




/*
 * Misc Functions
 */

    int VixVM_UpgradeVirtualHardware(int vmHandle,
                                           int options,
                                           VixEventProcCallback callbackProc,
                                           Pointer clientData);


    int VixVM_InstallTools(int vmHandle,
                                 int options,
                                 String commandLineArgs,
                                 VixEventProcCallback callbackProc,
                                 Pointer clientData);


/*
 *-----------------------------------------------------------------------------
 *
 * VIX Job --
 *
 *-----------------------------------------------------------------------------
 */

/*
 * Synchronization functions
 * (used to detect when an asynch operation completes).
 */

    int VixJob_Wait(int jobHandle,
                         int firstPropertyID,
                         Object... args);

    int VixJob_CheckCompletion(int jobHandle,
                                    IntByReference complete);


/*
 * Accessor functions
 * (used to get results of a completed asynch operation).
 */

    int VixJob_GetError(int jobHandle);

    int VixJob_GetNumProperties(int jobHandle,
                                int resultPropertyID);

    int VixJob_GetNthProperties(int jobHandle,
                                     int index,
                                     int propertyID,
                                     Object... args);


    int VixSnapshot_GetNumChildren(int parentSnapshotHandle,
                                        IntByReference numChildSnapshots);

    int VixSnapshot_GetChild(int parentSnapshotHandle,
                                  int index,
                                  IntByReference childSnapshotHandle);

    int VixSnapshot_GetParent(int snapshotHandle,
                                   IntByReference parentSnapshotHandle);

    String Vix_GetErrorText(int err, String locale);
}
