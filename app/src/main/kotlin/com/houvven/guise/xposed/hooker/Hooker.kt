package com.houvven.guise.xposed.hooker

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.houvven.guise.xposed.config.ModuleConfiguration


abstract class Hooker(
    protected val config: ModuleConfiguration,
) : YukiBaseHooker()