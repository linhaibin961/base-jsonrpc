package com.lhb.common.utils.pay;

public class AliPayConfig {
//	// 合作商户ID。用签约支付宝账号登录ms.alipay.com后，在账户信息页面获取。
	public static final String PARTNER_OLD = "2088511525431344";
//	// 商户收款的支付宝账号
//	public static final String SELLER = "xiaowangkeji@163.com";
//	// 商户（RSA）私钥
//	public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALhnl+ShO5KGYt4wfwbt7J0SlwisyEA63bI++OdmGr5z55MSe3RhXd6qxBhLmsC4XmgpmJne+J3p8GqFCwsSexIG7e++Bd9PbMNi+g3drcCFvPgdpI0JmZE9hpoCef4hABTE4Wo4jq36r/AY/+93qfAW/dbM1foyu6ZpwaVBvafFAgMBAAECgYEAr1SP7yn94ks0Jd4maQg/OPk78VuW6rsNnzSQpDNIqly5ifTAvN72yYfD+vWnpiWmXZNvLJ379OlBVYY6AcUIjXNx4J2G31SdHcCkr4Havija8I6aGp1mVey9Q/52i454L4zUeqDwFQ7ip99Nr+S9X17OJqVpF58oXfG5Q4WLTAECQQDzwD+e98TVzbKpMpmgfr1idJm24rgmCChZo/HEBowJdJ8gEoKPzNV0xi5ZQf8DT8srUwOp3/Ry6j7DntQXTBLhAkEAwavhSubOvEo56sYKSEAixhnB+03wIzoq25b+orei2IYLbiq8tnwDDpuJ7tmmOyzNzmShsK8bozlPFr262qXVZQJBAL6Oxm4d34kSZaE3CLlbO+2ToHRErJg2+myR6blJMQNXY+tr7RxEk3/WS3fGS3ooUHpPTObqSa3pCSZfrgmElKECQHUDD/9dLBL/FbcPKC828MuXzGe1Ux01i6dYt0hAgxcnBcjt8U6f5ojw2ImC3rhp/X5xtm81nRElEu++eiKYhzECQQDzsP9cZD6p++crXEx6/1rmmyw0ao+cYAdY9keBMqQfHbwSflTQsGFvMMYvaluQJaUO2aYsnLxd+xRpiJr7PPrw";
//	// 支付宝（RSA）公钥 用签约支付宝账号登录ms.alipay.com后，在密钥管理页面获取。
	public static final String RSA_ALIPAY_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
////	public static final String RSA_ALIPAY_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
//	// 商户（MD5）KEY
//	public static final String KEY = "7h6r34bsyo10ccyqhj6u1b9gwzqfr8ts";


	// 合作商户ID。用签约支付宝账号登录ms.alipay.com后，在账户信息页面获取。
	public static final String PARTNER = "2088801742206114";
	// 商户收款的支付宝账号
//	public static final String SELLER = "983959198@qq.com";
	// 商户（RSA）私钥
//	public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALhnl+ShO5KGYt4wfwbt7J0SlwisyEA63bI++OdmGr5z55MSe3RhXd6qxBhLmsC4XmgpmJne+J3p8GqFCwsSexIG7e++Bd9PbMNi+g3drcCFvPgdpI0JmZE9hpoCef4hABTE4Wo4jq36r/AY/+93qfAW/dbM1foyu6ZpwaVBvafFAgMBAAECgYEAr1SP7yn94ks0Jd4maQg/OPk78VuW6rsNnzSQpDNIqly5ifTAvN72yYfD+vWnpiWmXZNvLJ379OlBVYY6AcUIjXNx4J2G31SdHcCkr4Havija8I6aGp1mVey9Q/52i454L4zUeqDwFQ7ip99Nr+S9X17OJqVpF58oXfG5Q4WLTAECQQDzwD+e98TVzbKpMpmgfr1idJm24rgmCChZo/HEBowJdJ8gEoKPzNV0xi5ZQf8DT8srUwOp3/Ry6j7DntQXTBLhAkEAwavhSubOvEo56sYKSEAixhnB+03wIzoq25b+orei2IYLbiq8tnwDDpuJ7tmmOyzNzmShsK8bozlPFr262qXVZQJBAL6Oxm4d34kSZaE3CLlbO+2ToHRErJg2+myR6blJMQNXY+tr7RxEk3/WS3fGS3ooUHpPTObqSa3pCSZfrgmElKECQHUDD/9dLBL/FbcPKC828MuXzGe1Ux01i6dYt0hAgxcnBcjt8U6f5ojw2ImC3rhp/X5xtm81nRElEu++eiKYhzECQQDzsP9cZD6p++crXEx6/1rmmyw0ao+cYAdY9keBMqQfHbwSflTQsGFvMMYvaluQJaUO2aYsnLxd+xRpiJr7PPrw";
	// 支付宝（RSA）公钥 用签约支付宝账号登录ms.alipay.com后，在密钥管理页面获取。
//	public static final String RSA_ALIPAY_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	//	public static final String RSA_ALIPAY_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
	// 商户（MD5）KEY
//	public static final String KEY = "7h6r34bsyo10ccyqhj6u1b9gwzqfr8ts";

}

