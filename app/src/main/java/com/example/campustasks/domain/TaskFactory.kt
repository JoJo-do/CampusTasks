package com.example.campustasks.domain

object TaskFactory {

    fun createTask(
        id: String,
        title: String,
        description: String? = null,
        priority: TaskPriority = TaskPriority.MEDIUM,
        estimatedMinutes: Int,
        tags: Set<String> = emptySet()
    ): CreateTaskResult {

        val errors = mutableListOf<String>()

        if (!TaskValidator.isValidTitle(title)) {
            errors.add("Title must be 3-80 characters")
        }

        if (!TaskValidator.isValidEstimatedMinutes(estimatedMinutes)) {
            errors.add("Estimated minutes must be 5-480")
        }

        val normalizedTags = TaskValidator.normalizeTags(tags)

        if (normalizedTags.size != tags.size) {
            errors.add("Tags must not be blank")
        }

        if (errors.isNotEmpty()) {
            return CreateTaskResult.Invalid(errors)
        }

        val task = Task(
            id = id.trim(),
            title = TaskValidator.normalizeTitle(title),
            description = TaskValidator.normalizeDescription(description),
            priority = priority,
            status = TaskStatus.OPEN,
            estimatedMinutes = estimatedMinutes,
            tags = normalizedTags
        )

        return CreateTaskResult.Success(task)
    }
}
