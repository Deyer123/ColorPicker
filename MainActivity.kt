package com.example.deyer.finalcolorpicker

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Spinner
import android.widget.*

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val list = arrayListOf<String>("") //ArrayList

    lateinit var option : Spinner
    lateinit var result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Set up drop down menu or spinner
        // Create an ArrayAdapter
        val adapter = ArrayAdapter.createFromResource(this,
            R.array.savedList, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        spinner.adapter = adapter





        textVR.setText("Red")
        textVG.setText("Green")
        textVB.setText("Blue")

        //Hard coded so they appear at launch
        rButton.text = "255"
        gButton.text = "255"
        bButton.text = "255"

        // Seek bar action and set background of surfaceView
        colorR.max = 255
        colorR.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                           fromUser: Boolean) {
                val colorStr = getColorString()
                colorSurfaceView.setBackgroundColor(Color.parseColor(colorStr))

                rButton.text= progress.toString()
            }
        })

        colorG.max = 255
        colorG.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                           fromUser: Boolean) {
                val colorStr = getColorString()
                colorSurfaceView.setBackgroundColor(Color.parseColor(colorStr))
                gButton.text = progress.toString()
            }
        })

        colorB.max = 255
        colorB.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                           fromUser: Boolean) {
                val colorStr = getColorString()
                colorSurfaceView.setBackgroundColor(Color.parseColor(colorStr))
                bButton.text = progress.toString()

            }
        })


        load1.setText("Load 1")
        if(load1.callOnClick()){
            println("LOAD 3")
            colorSurfaceView.setBackgroundColor(Color.parseColor(list.get(0)))
        }
        load2.setText("Load 2")
        if(load2.callOnClick()){
            println("LOAD 3")
            colorSurfaceView.setBackgroundColor(Color.parseColor(list.get(1)))
        }
        load3.setText("Load 3")
        if(load3.callOnClick()){
            println("LOAD 3")
            colorSurfaceView.setBackgroundColor(Color.parseColor(list.get(2)))
        }




    }// End onCreate (Main)

    //==================================================================================================================

    // Add the rgb hex values to get the color.
    fun getColorString(): String {
        var r = Integer.toHexString(((255*colorR.progress)/colorR.max))
        if(r.length==1) {
            r = "0" + r
        }
        var g = Integer.toHexString(((255*colorG.progress)/colorG.max))
        if(g.length==1) g = "0"+g
        var b = Integer.toHexString(((255*colorB.progress)/colorB.max))
        if(b.length==1) b = "0"+b
        var number = buttonTextView.setText("#" + r + g + b)
        val hexColorNum = "#" + r + g + b
        buttonTextView.setText(hexColorNum)
        return hexColorNum
    }

    //==================================================================================================================


    // Put things in the very top... forgot what it's called.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    //==================================================================================================================


    //Things at the very top
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
           R.id.Save -> {
               list += getColorString()
               println("List So Far  " + list)
               println("Hello world")
               println(" ")
               val toast = Toast.makeText(applicationContext,  "SAVED!", Toast.LENGTH_SHORT)
               toast.show()
               true
            }
            R.id.Recall -> {
                println("LOADING")

                println(list.size.toString().get(0))

                colorSurfaceView.setBackgroundColor(Color.parseColor(list.size.toString()))


                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    //==================================================================================================================


}





