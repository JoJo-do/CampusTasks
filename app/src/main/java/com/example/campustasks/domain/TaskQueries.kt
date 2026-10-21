package com.example.campustasks.domain

fun List<Task>.openTasks(): List<Task> =
    filter { it.status != TaskStatus.DONE }

fun List<Task>.tasksByPriority(
    priority: TaskPriority
): List<Task> =
    filter { it.priority == priority }

fun List<Task>.searchByKeyword(
    keyword: String
): List<Task> {
    val query = keyword.trim()

    if (query.isEmpty()) return emptyList()

    return filter { task ->
        task.title.contains(query, ignoreCase = true) ||
                task.description?.contains(query, ignoreCase = true) == true ||
                task.tags.any { it.contains(query, ignoreCase = true) }
    }
}

fun List<Task>.groupByStatus(): Map<TaskStatus, List<Task>> =
    groupBy { it.status }

fun <T : Identifiable> Iterable<T>.findById(
    id: String
): T? =
    firstOrNull { it.id == id }
