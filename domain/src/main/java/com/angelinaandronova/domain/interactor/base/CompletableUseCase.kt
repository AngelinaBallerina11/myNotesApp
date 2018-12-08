package com.angelinaandronova.domain.interactor.base

import com.angelinaandronova.domain.executor.PostExecutionThread
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

/*
*   Use Case base class - when nothing is returned
* */
abstract class CompletableUseCase<in Params> constructor(
    private val postExecutionThread: PostExecutionThread
) {

    private val disposables = CompositeDisposable()
    protected abstract fun getUseCaseCompletable(params: Params? = null): Completable

    open fun execute(observer: DisposableCompletableObserver, params: Params? = null) {
        val completable = getUseCaseCompletable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
        addDisposable(completable.subscribeWith(observer))
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        disposables.dispose()
    }
}