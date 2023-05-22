package com.example.alcohol

interface ReminderDao {
    fun getAllReminders(): List<Reminder>
    fun addReminder(reminder: Reminder)
    fun updateReminder(reminder: Reminder)
    fun deleteReminder(reminder: Reminder)
}
