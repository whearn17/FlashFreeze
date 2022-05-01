package net.whearn.flashfreeze

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.tutorial1.R
import java.util.*

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logoBg = findViewById<ImageView>(R.id.logo_bg)
        val dayCur = findViewById<TextView>(R.id.day_cur)
        val days = resources.getStringArray(R.array.Days)

        val spinner = findViewById<Spinner>(R.id.day_menu)
        if (spinner != null)
        {
            val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, days)
            spinner.adapter = adapter

            spinner.setSelection(0, false)

            spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener
            {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long)
                {
                    dayCur.text = days[position]
                    getIce()
                }

                override fun onNothingSelected(Parent: AdapterView<*>)
                {
                    TODO("Not yet implemented")
                }
            }
        }

        getDay(dayCur)
        getIce()
        fadeOut(logoBg)
    }

    private fun fadeOut(x: ImageView)
    {
        val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        fadeOut.setAnimationListener(object: Animation.AnimationListener
        {
            override fun onAnimationStart(p0: Animation?)
            {

            }

            override fun onAnimationEnd(p0: Animation?)
            {
                x.visibility = View.GONE
            }

            override fun onAnimationRepeat(p0: Animation?)
            {

            }

        })
        x.startAnimation(fadeOut)
    }

    private fun getIce()
    {
        var rnds = (0..1).random()
        val cal = Calendar.getInstance()
        var hour = cal.get(Calendar.HOUR_OF_DAY)
        val now = findViewById<TextView>(R.id.conditions_now)
        if (rnds == 0)
        {
            now.text = "Now\nSafe"
        }

        repeat (15)
        { index->
            val name = "conditions_now_plus$index"
            val id = resources.getIdentifier(name, "id", packageName)
            if (id != 0)
            {
                val curHour = findViewById<TextView>(id)
                rnds = (0..1).random()
                if (rnds == 1)
                {
                    curHour.text = "$hour\nIcy"
                }
                else
                {
                    curHour.text = "  $hour\nSafe"
                }
            }
            hour += 1
            hour %= 24
        }
    }

    private fun getDay(x: TextView)
    {
        val cal = Calendar.getInstance()

        when (cal.get(Calendar.DAY_OF_WEEK))
        {
            Calendar.SUNDAY -> {x.text = "Sunday"}
            Calendar.MONDAY -> {x.text = "Monday"}
            Calendar.TUESDAY -> {x.text = "Tuesday"}
            Calendar.WEDNESDAY -> {x.text = "Wednesday"}
            Calendar.THURSDAY -> {x.text = "Thursday"}
            Calendar.FRIDAY -> {x.text = "Friday"}
            Calendar.SATURDAY -> {x.text = "Saturday"}
        }
    }
}