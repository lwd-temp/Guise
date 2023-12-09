package com.houvven.guise.xposed

import com.highcapable.yukihookapi.YukiHookAPI
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit
import com.houvven.guise.BuildConfig

@InjectYukiHookWithXposed
class HookEntry : IYukiHookXposedInit {

    companion object {
        val isActivated = YukiHookAPI.Status.isModuleActive
        val frameworkType = YukiHookAPI.Status.Executor.type
        val frameworkName = YukiHookAPI.Status.Executor.name
        val frameworkApiLevel = YukiHookAPI.Status.Executor.apiLevel
    }

    override fun onInit() {
        configs {
            isDebug = BuildConfig.DEBUG
            debugLog {
                tag = BuildConfig.APPLICATION_ID
            }
        }
    }

    override fun onHook() {
        encase {
            loadApp(isExcludeSelf = true) {
            }
        }
    }

}
