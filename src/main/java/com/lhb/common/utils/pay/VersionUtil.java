package com.lhb.common.utils.pay;

/**
 * Created by huangdongbin on 2016/6/30.
 */
public class VersionUtil {

   private static int OLD_VERSION = 5000; //版本好
   private static ThreadLocal<Integer> local = new ThreadLocal<Integer>();
   private static ThreadLocal<String> comeBackLocal = new ThreadLocal<>();


    public static void addVersion(Integer version){
        local.set(version);
    }

    public static Integer getVersion(){
        return local.get();
    }

    public static void addStr(String flag){
        comeBackLocal.set(flag);
    }

    /**
     * 只能 支付宝 回调用
     * @return
     */
    public static boolean isAliOld(){
        String str = comeBackLocal.get();
        return (str!=null&&AliPayConfig.PARTNER_OLD.equals(str))?true:false;
    }


//    /**
//     * 只能 微信 回调用 1：老版本；2：新版本；3：微信公众号
//     * @return
//     */
//    public static int isWxOld(){
//        String str = comeBackLocal.get();
//        return  ((str!=null&&WxPayConfig.appid_old.equals(str))||(str!=null&&WxPayConfig.clientAppid_old.equals(str)))?1:(WxPayConfig.wechat_Appid.equals(str)?3:2);
//    }

    public static String getPartnerKey(){
        String str = comeBackLocal.get();
        if(WxPayConfig.appid.equals(str)){
            return WxPayConfig.partnerkey;
        }else if (WxPayConfig.appid_old.equals(str)){
            return WxPayConfig.partnerkey_old;
        }else if(WxPayConfig.clientAppid.equals(str)){
            return WxPayConfig.clientPartnerKey;
        }else if (WxPayConfig.clientAppid_old.equals(str)) {
            return WxPayConfig.clientPartnerKey_old;
        }else if (WxPayConfig.wechat_Appid.equals(str)){
            return WxPayConfig.wechat_PartnerKey;
        }else if (WxPayConfig.gkd_Appid.equals(str)){
            return WxPayConfig.gkd_PartnerKey;
        }
        return "";
    }

    /**
     * 下单的时候使用 是否是老版本
     * @return 1：老版本；2：新版本；3：微信公众号
     */
    public static int isOld(AppKind appKind){
        Integer version = local.get();
        if (AppKind.WECHAT.getValue() == appKind.getValue()){
            return 3;
        }
        return (version!=null&&version >=OLD_VERSION)?2:1;
    }

    public static String getVersionDesc(AppKind appKind){
        Integer version = local.get();
        if (AppKind.WECHAT.getValue() == appKind.getValue()){
            return "微信公众号支付";
        }
        if (AppKind.GKD.getValue()==appKind.getValue()){
            return "顾客多微信公众支付";
        }
        return (version!=null&&version >=OLD_VERSION)?"新版本":"老版本";
    }

    public static String getVersionAliDesc(){
        String str = comeBackLocal.get();
        return (str!=null&&AliPayConfig.PARTNER_OLD.equals(str))?"老版本":"新版本";
    }
    public static String getVersionWxDesc(){
        String str = comeBackLocal.get();
        if (WxPayConfig.wechat_Appid.equals(str)){
            return "微信公众号支付";
        }
        if (WxPayConfig.gkd_Appid.equals(str)){
            return "顾客多微信公众支付";
        }
        return  (str!=null&&WxPayConfig.appid_old.equals(str))||((str!=null&&WxPayConfig.clientAppid_old.equals(str)))?"老版本":"新版本";
    }
}
