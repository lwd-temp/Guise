package com.houvven.guise.xposed.config

import java.io.Serializable

interface ModuleConf : Serializable {

    /**
     * check conf is effective, but should not call this method directly, use [isEffective] instead
     */
    fun isEffective(): Boolean
}