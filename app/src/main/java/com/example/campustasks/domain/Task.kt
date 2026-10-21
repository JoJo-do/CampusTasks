package com.example.campustasks.domain

data class Task(
    override val id: String,
    val title: String,
    val description: String? = null,
    val priority: TaskPriority,
    val status: TaskStatus = TaskStatus.OPEN,
    val estimatedMinutes: Int,
    val tags: Set<String> = emptySet()
) : Identifiable {

    fun markInProgress(): Task =
        copy(status = TaskStatus.IN_PROGRESS)

    fun markDone(): Task =
        copy(status = TaskStatus.DONE)
}
