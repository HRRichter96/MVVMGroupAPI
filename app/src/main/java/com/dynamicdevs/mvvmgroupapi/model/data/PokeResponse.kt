package com.dynamicdevs.mvvmgroupapi.model.data

data class PokeResponse(
    val count: Int,
    val `data`: List<Result>,
    val page: Int,
    val pageSize: Int,
    val totalCount: Int
)