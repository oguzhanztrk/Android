package com.example.tasarm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_icerik.*
import kotlinx.android.synthetic.main.activity_iceriksecimi.*
import kotlinx.android.synthetic.main.activity_main.*

class Icerik : AppCompatActivity() {
    lateinit var myPreference :MyPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_icerik)

        //val languageList:Array<String> = arrayOf("en","tr")

        var intent = intent
        val position = getIntent().getIntExtra("position",0)
        var deger = intent.getIntExtra("deger",1)
        fun icerikEslestir(u:Int){
            var icerik = getResources().getStringArray(u)
            var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,icerik)
            var text1= textView.setText(icerik.get(position))
        }
        /*  func icerikeşleştir(deger:Int){
            var icerik = getResources().getStringArray(deger)
            var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,icerik)
            var text1= textView.setText(icerik.get(position))
            }


        */

        if(deger==1) {
            var u = R.array.python_mesaj
            icerikEslestir(u)


        }
        if(deger==2){
            var u = R.array.java_mesaj
            icerikEslestir(u)

        }
        }
    override fun attachBaseContext(newBase: Context?) {
        myPreference = MyPreference(newBase!!)
        val lang: String? = myPreference.getLoginCount()
        super.attachBaseContext(MyContextWrapper.wrap(newBase, lang))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_language,menu)


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.add_tr_item){
            myPreference.setLoginCount("tr")

            val intent = Intent(this,Icerik::class.java)
            startActivity(intent)

        }else if(item.itemId == R.id.add_en_item){
            myPreference.setLoginCount("en")
            finish()

            val intent = Intent(this,Icerik::class.java)
            startActivity(intent)

        }
        return super.onOptionsItemSelected(item)
    }
}
