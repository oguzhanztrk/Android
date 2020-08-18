package com.example.tasarm

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_iceriksecimi.*


class IcerikSecimi : AppCompatActivity() {
 lateinit var myPreference:MyPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iceriksecimi)

        /*  var pythonIci = getResources().getStringArray(R.array.python_icerik)
        var arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,pythonIci)

        listviewPython.adapter = arrayAdapter*/
        var intent = intent
        val deger = intent.getIntExtra("sayi", 1)

        fun degerkontrol(u:Int){

                var dilIci = getResources().getStringArray(u)
                var arrayAdapter =ArrayAdapter(this,android.R.layout.simple_list_item_1,dilIci)
                //var arrayAdapter =ArrayAdapter(this,android.R.layout.simple_list_item_1,dilIci)
            //listview_icerik.adapter = MyCustomAdapter(this)


                listview_icerik.adapter = arrayAdapter

        }

        if (deger == 1) {
            var arrayKonum =R.array.python_icerik
            degerkontrol(arrayKonum)

        }
        if (deger == 2) {
            var arrayKonum =R.array.java_icerik
            degerkontrol(arrayKonum)
        }
        listview_icerik.onItemClickListener = AdapterView.OnItemClickListener {parent,view, position, id ->
            // Get the selected item text from ListView
            System.out.println("pozisyon="+position)
            intent = Intent(this, Icerik::class.java)
            intent.putExtra("position",position)
            intent.putExtra("deger", deger)

            startActivity(intent)
        }

    }
    override fun attachBaseContext(newBase: Context?) {
        myPreference = MyPreference(newBase!!)
        val lang: String? = myPreference.getLoginCount()
        super.attachBaseContext(MyContextWrapper.wrap(newBase, lang))
    }
   /* private class MyCustomAdapter(context:Context): BaseAdapter() {
        private  var  mContext: Context

        init {
            mContext = context
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView = TextView(mContext)
        textView.text="Here is my Row for my Listview"
            return textView
        }

        override fun getItem(position: Int): Any {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemId(position: Int): Long {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }


    }*/
}
