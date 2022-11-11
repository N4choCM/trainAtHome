package com.nachocampos.trainathome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nachocampos.trainathome.databinding.ActivityFinishedBinding

class WorkoutFinished : AppCompatActivity() {

    private var binding: ActivityFinishedBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishedBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // This is to display the tool bar on this activity.
        setSupportActionBar(binding?.toolbarFinishedActivity)

        // This is to display the back button on the toolbar of our activity.
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        // This is to add the functionality of going back to the back button of our activity.
        binding?.toolbarFinishedActivity?.setNavigationOnClickListener{
            onBackPressed()
        }

        binding?.btnFinished?.setOnClickListener{
            finish()
        }
    }
}