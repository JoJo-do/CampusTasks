package com.example.campustasks.domain

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TaskValidatorTest {

    @Test
    fun titleWithValidLengthIsAccepted() {
        assertTrue(TaskValidator.isValidTitle("Belajar Kotlin"))
    }

    @Test
    fun titleWithMinimumLengthIsAccepted() {
        assertTrue(TaskValidator.isValidTitle("ABC"))
    }

    @Test
    fun titleWithMaximumLengthIsAccepted() {
        assertTrue(TaskValidator.isValidTitle("A".repeat(80)))
    }

    @Test
    fun titleShorterThanMinimumIsRejected() {
        assertFalse(TaskValidator.isValidTitle("AB"))
    }

    @Test
    fun titleLongerThanMaximumIsRejected() {
        assertFalse(TaskValidator.isValidTitle("A".repeat(81)))
    }

    @Test
    fun blankTitleIsRejected() {
        assertFalse(TaskValidator.isValidTitle("   "))
    }

    @Test
    fun minimumEstimatedMinutesIsAccepted() {
        assertTrue(TaskValidator.isValidEstimatedMinutes(5))
    }

    @Test
    fun maximumEstimatedMinutesIsAccepted() {
        assertTrue(TaskValidator.isValidEstimatedMinutes(480))
    }

    @Test
    fun estimatedMinutesBelowMinimumIsRejected() {
        assertFalse(TaskValidator.isValidEstimatedMinutes(4))
    }

    @Test
    fun estimatedMinutesAboveMaximumIsRejected() {
        assertFalse(TaskValidator.isValidEstimatedMinutes(481))
    }
}
