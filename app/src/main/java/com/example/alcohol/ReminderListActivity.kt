package com.example.alcohol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alcohol.databinding.ActivityReminderListBinding



class ReminderListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReminderListBinding
    private lateinit var reminderDao: ReminderDao
    private lateinit var reminders: List<Reminder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReminderListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        reminderDao = ReminderDatabase.getInstance(this).reminderDao()

        setupRecyclerView()

        binding.btnAddReminder.setOnClickListener {
            startActivity(Intent(this, MedicineActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        reminders = reminderDao.getAllReminders()

        val adapter = ReminderAdapter(reminders, object : ReminderAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                // Handle item click (open edit screen, etc.)
            }

            override fun onDeleteClick(position: Int) {
                val reminder = reminders[position]
                reminderDao.deleteReminder(reminder)
                reminders = reminderDao.getAllReminders()
                adapter.submitList(reminders)
            }
        })

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
