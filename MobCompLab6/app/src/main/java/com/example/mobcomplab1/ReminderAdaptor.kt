package com.example.mobcomplab1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.list_view.view.*
import java.text.SimpleDateFormat
import java.util.*

class ReminderAdaptor(context: Context, private val list: List<Reminder>) : BaseAdapter(){

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, pl: View?, parent: ViewGroup?): View {
        val rov = inflater.inflate(R.layout.list_view, parent, false)
        rov.itemMessage.text = list[position].message

        if(list[position].time != null){
            val sdf = SimpleDateFormat("HH:mm dd.MM.yyyy")
            sdf.timeZone = TimeZone.getDefault()

            val time = list[position].time
            val readableTime = sdf.format(time)

            rov.itemTrigger.text = readableTime
        } else{

            rov.itemTrigger.text = "location"
        }

        return rov
    }

    override fun getItem(position: Int): Any {

        return list[position]
    }

    override fun getItemId(position: Int): Long {

        return position.toLong()
    }

    override fun getCount(): Int {

        return list.size
    }
}