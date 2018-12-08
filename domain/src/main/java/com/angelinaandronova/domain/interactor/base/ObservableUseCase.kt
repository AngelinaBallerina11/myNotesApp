package com.angelinaandronova.domain.interactor.base

import com.angelinaandronova.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/*
*   Use Case base class - when data is returned
* */
abstract class ObservableUseCase<T, in Params> constructor(
    private val postExecutionThread: PostExecutionThread
) {

    private val disposables = CompositeDisposable()
    protected abstract fun getUseCaseObservable(params: Params? = null): Observable<T>

    open fun execute(observer: DisposableObserver<T>, params: Params? = null) {
        val observable = getUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
        addDisposable(observable.subscribeWith(observer))
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        disposables.dispose()
    }
}