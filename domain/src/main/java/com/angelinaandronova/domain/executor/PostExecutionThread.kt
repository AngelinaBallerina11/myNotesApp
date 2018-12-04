package com.angelinaandronova.domain.executor

import io.reactivex.Scheduler

/*
*  Domain layer has no knowledge of RxAndroid
*  Implemented by Android layer
* */
interface PostExecutionThread {
    val scheduler: Scheduler
}