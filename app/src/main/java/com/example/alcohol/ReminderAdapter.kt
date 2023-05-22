package com.example.alcohol

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alcohol.databinding.ListItemReminderBinding
import java.sql.Date



class ReminderAdapter(
    private val reminders: List<Reminder>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onDeleteClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemReminderBinding.inflate(inflater, parent, false)
        return ReminderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val reminder = reminders[position]
        holder.bind(reminder)
    }

    override fun getItemCount(): Int {
        return reminders.size
    }

    inner class ReminderViewHolder(private val binding: ListItemReminderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnDelete.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDeleteClick(position)
                }
            }

            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }
        }

        fun bind(reminder: Reminder) {
            binding.tvTitle.text = reminder.title
            binding.tvMessage.text = reminder.message

            val date = Date(reminder.dateTime)
            val dateFormat = android.text.format.DateFormat.getLongDateFormat(binding.root.context)
            val timeFormat = android.text.format.DateFormat.getTimeFormat(binding.root.context)
            val dateTimeString = dateFormat.format(date) + " " + timeFormat.format(date)
            binding.tvDateTime.text = dateTimeString
        }
    }
}
