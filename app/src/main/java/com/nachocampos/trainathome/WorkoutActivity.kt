package com.nachocampos.trainathome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nachocampos.trainathome.databinding.ActivityWorkoutBinding

class WorkoutActivity : AppCompatActivity() {
    private var binding: ActivityWorkoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // This is to display the tool bar on this activity.
        setSupportActionBar(binding?.toolbarWorkout)

        // This is to display the back button on the toolbar of our activity.
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        // This is to add the functionality of going back to the back button of our activity.
        binding?.toolbarWorkout?.setNavigationOnClickListener{
            onBackPressed()
        }

    }
}