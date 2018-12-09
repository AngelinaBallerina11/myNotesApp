package com.angelinaandronova.mobile_ui.mapper


interface UiMapper<in P, out V> {

    fun mapToUi(presentation: P): V

}