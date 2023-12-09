package com.houvven.guise.xposed.config.item

import com.houvven.guise.xposed.config.ModuleConf
import kotlinx.serialization.Serializable

private val emptyPropertiesConf = PropertiesConf()

@Serializable
data class PropertiesConf(
    /**
     * mobile brand, such as: Xiaomi, Huawei, Samsung, etc.
     */
    val brand: String? = null,
    /**
     * mobile model, such as: MI 8, HUAWEI P30, etc.
     */
    val model: String? = null,
    /**
     * mobile device code, such as: MI 8: dipper, HUAWEI P30: ELE-AL00, etc.
     */
    val device: String? = null,
    /**
     * mobile product, such as: dipper, ELE-AL00, etc.
     */
    val product: String? = null,
    /**
     * check the device whether it s a tablet or a phone, it can used on QQ pad login.
     * such as phone, tablet, nosdcard, etc.
     */
    val characteristics: String? = null,
    /**
     * android sdk version, such as: 28, 29, etc.
     */
    val sdk: Int? = null,
    /**
     * android release version, such as: 9, 10, etc.
     */
    val release: String? = null,
    /**
     * android device and system fingerprint, such as: Xiaomi/dipper/dipper:9/PKQ1.180729.001/V10.3.5.0.PEHCNXM:user/release-keys
     */
    val fingerprint: String? = null,
    /**
     * android device hardware, such as: qcom, exynos, etc.
     */
    val hardware: String? = null
) : ModuleConf {
    @Deprecated("should not call this method directly, use isEffective instead")
    override fun isEffective(): Boolean {
        // 宣泰廷
        return this != emptyPropertiesConf
    }
}