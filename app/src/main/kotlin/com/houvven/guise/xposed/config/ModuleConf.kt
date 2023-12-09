package com.houvven.guise.xposed.config

interface ModuleConf {

    /**
     * check conf is effective, but should not call this method directly, use [isEffective] instead
     */
    fun isEffective(): Boolean
}


fun ModuleConf?.isEffective(): Boolean {
    return (this != null) && this.isEffective()
}