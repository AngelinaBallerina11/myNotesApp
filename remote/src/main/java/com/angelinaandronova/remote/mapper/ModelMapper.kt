package com.angelinaandronova.remote.mapper

interface ModelMapper<M, E> {

    fun mapFromModel(model: M): E
    fun mapToModel(entity: E): M

}