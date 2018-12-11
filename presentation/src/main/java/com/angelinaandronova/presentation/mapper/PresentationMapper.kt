package com.angelinaandronova.presentation.mapper


interface PresentationMapper<V, D> {

    fun mapToView(domain: D): V
    fun mapToDomain(presentation: V): D
}