package com.example.iot2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

//class ItemAdapter(context: MainActivity.LoadSensorLogs) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
//    var itemdata : ArrayList<DataClass> = arrayListOf()
//    lateinit var view : View
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        var items = ArrayList<MainActivity.Item>()
//        var view = convertView
//        if (view == null) {
//            val inflater = ContextCompat.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
//            view = inflater.inflate(R.layout.list_sensor_item, null)
//        }
//        val tempText = view!!.findViewById<TextView>(R.id.temp)
//        val humidityText = view.findViewById<TextView>(R.id.humidity)
//        val createdAtText = view.findViewById<TextView>(R.id.created_at)
//        tempText.setText(items.get(position).temp.toString() + "")
//        humidityText.setText(items.get(position).humidity.toString() + "")
//        createdAtText.setText(items.get(position).created_at)
//        return view
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val width =
//        view = parent
//        if (view == null){
//            val inflater = ContextCompat.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
//            view = inflater.inflate(R.layout.list_sensor_item,null)
//        }
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getItemCount(): Int {
//        return items.size
//    }

//}