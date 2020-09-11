package com.example.iot2

import android.content.Context
import android.net.wifi.WifiConfiguration.AuthAlgorithm.strings
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.KProperty0

var items = ArrayList<DataClass>()
lateinit var view : RecyclerView
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sensors = arrayOf("dht11", "mq2")
        val spinnerAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, sensors)

        val spinner = findViewById<View>(R.id.spinner) as Spinner
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, i: Int, I: Long) {
                LoadSensorLogs().execute("arduino", sensors[i])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        LoadSensorLogs().execute("arduino", "dht11")
    }


    inner class ItemAdapter(context: LoadSensorLogs) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
        var itemdata : ArrayList<DataClass> = arrayListOf()
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var items = ArrayList<DataClass>()
            var view = convertView
            if (view == null) {
                val inflater = getSystemService(LAYOUT_INFLATER_SERVICE::javaClass) as LayoutInflater
                view = inflater.inflate(R.layout.list_sensor_item, null)
            }
            val tempText = view!!.findViewById<TextView>(R.id.temp)
            val humidityText = view.findViewById<TextView>(R.id.humidity)
            val createdAtText = view.findViewById<TextView>(R.id.created_at)
            tempText.setText(items.get(position).temp.toString() + "")
            humidityText.setText(items.get(position).humidity.toString() + "")
            createdAtText.setText(items.get(position).created_at)
            return view
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var itemdata : ArrayList<DataClass> = arrayListOf()
            view = parent
            if (view == null){
                val inflater = ContextCompat.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
                view = inflater.inflate(R.layout.list_sensor_item,null)
            }        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }
    }

    class LoadSensorLogs : AsyncTask<String?, String?, String>() {
        override fun doInBackground(vararg params: String?): String{
            val response = StringBuffer()
            try {
                val apiURL = "http://192.168.0.21:3000/devices/" + strings[0] + "/" + strings[1]
                val url = URL(apiURL)
                val con = url.openConnection() as HttpURLConnection
                con.requestMethod = "GET"
                val responseCoed = con.responseCode
                val br: BufferedReader
                br = if (responseCoed == 200) {
                    BufferedReader(InputStreamReader(con.inputStream))
                } else {
                    BufferedReader(InputStreamReader(con.errorStream))
                }
                var inputLine: String?
                while (br.readLine().also { inputLine = it } != null) {
                    response.append(inputLine)
                }
                br.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Log.i(response.toString(), "oooooooooo")
            return response.toString()
        }

        override fun onPreExecute() {

        }


        override fun onPostExecute(s: String) {
            try {
                val array = JSONArray(s)
                items.clear()
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    items.add(Item(obj.getInt("tmp"),
                            obj.getInt("hum"),
                            obj.getString("created_at")))
                }
                val adapter = ItemAdapter(this)
                val listView = findViewById<View>(R.id.listview)
                listView.adapter = adapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun clickLedOnButton(view: View?) {
        SendLedOnOff().execute("led", "on")
    }

    fun clickLedOffButton(view: View?) {
        SendLedOnOff().execute("led", "off")
    }

    internal class SendLedOnOff : AsyncTask<String?, String?, String>() {
        override fun doInBackground(vararg params: String?): String{
            val response = StringBuffer()
            try {
                val apiURL = "http://192.168.0.21:3000/devices/" + strings[0] + "/" + strings[1]
                val url = URL(apiURL)
                val con = url.openConnection() as HttpURLConnection
                con.requestMethod = "POST"
                val responseCode = con.responseCode
                val br: BufferedReader
                br = if (responseCode == 200) {
                    BufferedReader(InputStreamReader(
                            con.inputStream))
                } else {
                    BufferedReader(InputStreamReader(
                            con.errorStream))
                }
                var inputLine: String?
                while (br.readLine().also { inputLine = it } != null) {
                    response.append(inputLine)
                }
                br.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return response.toString()
        }

        override fun onPreExecute() {
        }

        override fun onPostExecute(s: String) {
        }

    }

}