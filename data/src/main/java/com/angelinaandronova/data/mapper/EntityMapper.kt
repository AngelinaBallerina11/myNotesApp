package com.angelinaandronova.data.mapper

/*
* Maps models between Domain and Data layers
* @param E data entity
* @param D domain entity
*
* */
interface EntityMapper<E, D> {

    fun mapFromEntity(entity: E): D
    fun mapToEntity(domain: D): E
}