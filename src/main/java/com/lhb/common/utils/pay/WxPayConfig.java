package com.lhb.common.utils.pay;


import com.lhb.base.global.Configuration;

public class WxPayConfig {

	//客戶端appid
	public static final String clientAppid_old="wxa1e248f384c0cf4b";//"wxa778c03fd10bff10";
	public static final String clientPartnerKey_old="pupuwang168888xiaowangyonghu1688";//"sharewonder18xiaowang18yonghu888";
	public static final String clientMch_id_old="1311575201";//"1247904701";

	// 微信支付商户开通后 微信会提供appid和appsecret和商户号partner
	public static final String appid_old = "wx2a8b4d6b98996533";

	public static final String trade_type_old = "APP";
	public static final String mch_id_old = "1248067701";

	public static final String appsecret_old = "";
	// 这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
	public static final String partnerkey_old = "sharewonder18xiaowang18shangjia8";
//

	//---------------------------------------------完美分割线（新）----------------------------------------------------------
	//客戶端appid
	public static final String clientAppid="wxe8098a4273194746";
	public static final String clientPartnerKey="share6Yf7hR18pupuwangLP5e2M4jia8";
	public static final String clientMch_id="1361898502";
	// 微信支付商户开通后 微信会提供appid和appsecret和商户号partner
	public static final String appid = "wx88ae1374f8b291b5";
	// 这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
	public static final String partnerkey = "shU8kUih6Gfh7pupuwangLOu8UiSdda8";
	public static final String mch_id = "1361914702";
	public static final String trade_type = "APP";
	public static final String appsecret = "";
	public static final String IP = "127.0.0.1";

	//---------------------------------------------完美分割线（新）微信公众号----------------------------------------------------------
	// 微信端支付配置
	public static final String wechat_Appid= Configuration.getInstance().getProperty("wechat_Appid");
	public static final String wechat_PartnerKey=Configuration.getInstance().getProperty("wechat_PartnerKey");
	public static final String wechat_mch_id=Configuration.getInstance().getProperty("wechat_mch_id");

	//顾客多微信端支付配置
	public static final String gkd_Appid = Configuration.getInstance().getProperty("gkd_Appid");
	public static final String gkd_PartnerKey = Configuration.getInstance().getProperty("gkd_PartnerKey");
	public static final String gkd_mchId = Configuration.getInstance().getProperty("gkd_mchId");


}
