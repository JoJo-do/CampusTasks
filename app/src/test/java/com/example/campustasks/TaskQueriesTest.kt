package com.example.campustasks.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class TaskQueriesTest {

    private val tasks = listOf(
        Task(
            id = "1",
            title = "Belajar Kotlin",
            priority = TaskPriority.HIGH,
            status = TaskStatus.OPEN,
            estimatedMinutes = 30,
            tags = setOf("programming")
        ),
        Task(
            id = "2",
            title = "Membuat laporan",
            description = "Laporan praktikum",
            priority = TaskPriority.MEDIUM,
            status = TaskStatus.IN_PROGRESS,
            estimatedMinutes = 60,
            tags = setOf("kuliah")
        ),
        Task(
            id = "3",
            title = "Membaca materi",
            priority = TaskPriority.LOW,
            status = TaskStatus.DONE,
            estimatedMinutes = 20
        )
    )

    @Test
    fun openTasksExcludesDoneTasks() {
        assertEquals(
            listOf("1", "2"),
            tasks.openTasks().map { it.id }
        )
    }

    @Test
    fun tasksByPriorityReturnsMatchingTasks() {
        assertEquals(
            listOf("1"),
            tasks.tasksByPriority(TaskPriority.HIGH).map { it.id }
        )
    }

    @Test
    fun searchByKeywordIsCaseInsensitive() {
        assertEquals(
            listOf("1"),
            tasks.searchByKeyword("KOTLIN").map { it.id }
        )
    }

    @Test
    fun searchByKeywordFindsDescription() {
        assertEquals(
            listOf("2"),
            tasks.searchByKeyword("praktikum").map { it.id }
        )
    }

    @Test
    fun searchByKeywordFindsTags() {
        assertEquals(
            listOf("1"),
            tasks.searchByKeyword("programming").map { it.id }
        )
    }

    @Test
    fun groupByStatusReturnsCorrectGroups() {
        val grouped = tasks.groupByStatus()

        assertEquals(listOf("1"), grouped[TaskStatus.OPEN]?.map { it.id })
        assertEquals(listOf("2"), grouped[TaskStatus.IN_PROGRESS]?.map { it.id })
        assertEquals(listOf("3"), grouped[TaskStatus.DONE]?.map { it.id })
    }

    @Test
    fun findByIdReturnsMatchingTask() {
        assertEquals("2", tasks.findById("2")?.id)
    }

    @Test
    fun findByIdReturnsNullWhenMissing() {
        assertNull(tasks.findById("999"))
    }
}
