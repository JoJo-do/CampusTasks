package com.example.campustasks.domain

object TaskValidator {

    fun isValidTitle(title: String): Boolean {
        val normalizedTitle = title.trim()
        return normalizedTitle.length in 3..80
    }

    fun isValidEstimatedMinutes(minutes: Int): Boolean {
        return minutes in 5..480
    }

    fun normalizeTitle(title: String): String {
        return title.trim()
    }

    fun normalizeDescription(description: String?): String? {
        return description
            ?.trim()
            ?.takeIf { it.isNotEmpty() }
    }

    fun normalizeTags(tags: Set<String>): Set<String> {
        return tags
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .toSet()
    }
}
