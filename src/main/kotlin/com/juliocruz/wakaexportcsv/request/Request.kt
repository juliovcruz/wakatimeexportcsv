package com.juliocruz.wakaexportcsv.request

data class Request (
    val days: List<Day>
)

data class Day (
    val editors: List<Struct>,
    val languages: List<Struct>,
    val machines: List<Struct>,
    val projects: List<Project> ? = null,
    val date: String
)

data class Struct (
    val hours: Int,
    val minutes: Int,
    val name: String,
    val text: String
)

data class Project(
    val branches: List<Branch> ?= null,
)

data class Branch(
    val hours: Int,
    val minutes: Int,
    val name: String
)