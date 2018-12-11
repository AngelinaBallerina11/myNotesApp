package com.angelinaandronova.mobile_ui.mapper


interface UiMapper<P, V> {

    fun mapToUi(presentation: P): V
    fun mapToPresenter(view: V): P
}