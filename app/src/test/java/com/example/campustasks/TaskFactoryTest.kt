package com.example.campustasks.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TaskFactoryTest {

    @Test
    fun validInputCreatesTaskSuccessfully() {
        val result = TaskFactory.createTask(
            id = "task-1",
            title = "Belajar Kotlin",
            estimatedMinutes = 30
        )

        assertTrue(result is CreateTaskResult.Success)

        val task = (result as CreateTaskResult.Success).task
        assertEquals("task-1", task.id)
        assertEquals("Belajar Kotlin", task.title)
        assertEquals(TaskPriority.MEDIUM, task.priority)
        assertEquals(TaskStatus.OPEN, task.status)
    }

    @Test
    fun nullDescriptionIsAccepted() {
        val result = TaskFactory.createTask(
            id = "task-2",
            title = "Mengerjakan tugas",
            description = null,
            estimatedMinutes = 60
        )

        assertTrue(result is CreateTaskResult.Success)

        val task = (result as CreateTaskResult.Success).task
        assertEquals(null, task.description)
    }

    @Test
    fun titleIsNormalized() {
        val result = TaskFactory.createTask(
            id = "task-3",
            title = "  Belajar Kotlin  ",
            estimatedMinutes = 30
        )

        assertTrue(result is CreateTaskResult.Success)

        val task = (result as CreateTaskResult.Success).task
        assertEquals("Belajar Kotlin", task.title)
    }

    @Test
    fun descriptionIsNormalized() {
        val result = TaskFactory.createTask(
            id = "task-4",
            title = "Belajar Kotlin",
            description = "  Materi dasar Kotlin  ",
            estimatedMinutes = 30
        )

        assertTrue(result is CreateTaskResult.Success)

        val task = (result as CreateTaskResult.Success).task
        assertEquals("Materi dasar Kotlin", task.description)
    }

    @Test
    fun invalidTitleReturnsInvalidResult() {
        val result = TaskFactory.createTask(
            id = "task-5",
            title = "AB",
            estimatedMinutes = 30
        )

        assertTrue(result is CreateTaskResult.Invalid)
    }

    @Test
    fun invalidEstimatedMinutesReturnsInvalidResult() {
        val result = TaskFactory.createTask(
            id = "task-6",
            title = "Belajar Kotlin",
            estimatedMinutes = 4
        )

        assertTrue(result is CreateTaskResult.Invalid)
    }

    @Test
    fun blankTagReturnsInvalidResult() {
        val result = TaskFactory.createTask(
            id = "task-7",
            title = "Belajar Kotlin",
            estimatedMinutes = 30,
            tags = setOf("kuliah", "   ")
        )

        assertFalse(result is CreateTaskResult.Success)
        assertTrue(result is CreateTaskResult.Invalid)
    }

    @Test
    fun tagsAreNormalized() {
        val result = TaskFactory.createTask(
            id = "task-8",
            title = "Belajar Kotlin",
            estimatedMinutes = 30,
            tags = setOf(" kuliah ", " Kotlin ")
        )

        assertTrue(result is CreateTaskResult.Success)

        val task = (result as CreateTaskResult.Success).task
        assertEquals(setOf("kuliah", "Kotlin"), task.tags)
    }
}
