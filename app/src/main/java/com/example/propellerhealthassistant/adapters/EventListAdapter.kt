package com.example.propellerhealthassistant.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils

import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.propellerhealthassistant.R
import com.example.propellerhealthassistent.model.Event

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class EventListAdapter : ListAdapter<Event, EventListAdapter.EventViewHolder>(EventComparator()) {

    //RECYCLERVIEW FOR EVENTS
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context,
            R.anim.fade_scale_animation
        )
        holder.bind(current)
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val medicationName: TextView = itemView.findViewById(R.id.medicationName)
        private val medicationType: TextView = itemView.findViewById(R.id.medicationType)
        private val time: TextView = itemView.findViewById(R.id.time)
        private val day: TextView = itemView.findViewById(R.id.day)
        private val month: TextView = itemView.findViewById(R.id.month)
        fun bind(eventItem: Event?){
            medicationName.text = eventItem?.medication
            medicationType.text = eventItem?.medicationtype
            time.text = parseTime(eventItem?.datetime)
            var eventDate = parseDate(eventItem?.datetime)!!.split(",")
            day.text = eventDate[0]
            month.text = eventDate[1]
        }

        fun parseTime(dateTime: String?): String{ //CONVERTS ISO 8601 TO 12H FORMAT
            var time = dateTime?.split("T")
            var hour = time!![1].substring(0, 2).toInt()
            if(hour in 1..11){ //AM TIME
                if(hour in 1..9){
                    return time!![1].substring(1, 5) + " AM" //REMOVE LEADING ZEROS FOR AM
                }
                return time!![1].substring(0, 5) + " AM"
            } else if(hour >= 12){ //PM TIME
                if(hour == 12){
                    return "${hour}:" + time!![1].substring(3, 5) + " PM" //IF 12 PM, DONT CHANGE HOUR
                } else {
                    return "${hour - 12}:" + time!![1].substring(3, 5) + " PM" //OTHERWISE SUBTRACT 12
                }
            }
            return "12:" + time!![1].substring(3, 5) + " AM" //FOR 12AM
        }

        fun parseDate(dateTime: String?): String?{ //TURNS INTO GREGORIAN CALENDAR FORMAT
            var formatter = SimpleDateFormat("yyyy-MM-dd")
            var date: Date? = null
            try {
                date = formatter.parse(dateTime)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            formatter = SimpleDateFormat("E, MMM d")
            return formatter.format(date)
        }


        companion object{
            fun create(parent: ViewGroup): EventViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return EventViewHolder(view)
            }
        }
    }

    class EventComparator : DiffUtil.ItemCallback<Event>(){
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }
    }

}