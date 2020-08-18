package com.example.tasarm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var  myPreference: MyPreference
    lateinit var context: Context


    val languageList:Array<String> = arrayOf("en","tr")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this
        myPreference = MyPreference(this)

        spinner.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,languageList)
        val lang: String? = myPreference.getLoginCount()
        val index: Int = languageList.indexOf(lang)
        if(index >= 0){
            spinner.setSelection(index)
        }

        button.setOnClickListener {
            myPreference.setLoginCount(languageList[spinner.selectedItemPosition])

        }

        //startActivity(Intent(this,MainActivity::class.java))
    }

  /*  override fun attachBaseContext(newBase: Context?) {
        myPreference = MyPreference(newBase!!)
        val lang: String? = myPreference.getLoginCount()
        super.attachBaseContext(MyContextWrapper.wrap(newBase, lang))
    }*/
    fun selectedMainItem(i:Int){
        val intent = Intent(this,IcerikSecimi::class.java)
        intent.putExtra("sayi",i)
        startActivity(intent)

    }
    fun selectedPythonImage(View : View){
        selectedMainItem(1)

    }
    fun selectedJavaImage(View : View){

        selectedMainItem(2)

    }
    fun selectedCImage(View : View){

        selectedMainItem(3)

    }
    fun selectedCSharpImage(View : View){

        selectedMainItem(4)

    }


}
