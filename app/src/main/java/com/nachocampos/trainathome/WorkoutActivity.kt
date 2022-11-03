package com.nachocampos.trainathome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.nachocampos.trainathome.databinding.ActivityWorkoutBinding

class WorkoutActivity : AppCompatActivity() {
    private var binding: ActivityWorkoutBinding? = null

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var workoutTimer: CountDownTimer? = null
    private var workoutProgress = 0

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

        setUpRestView()
    }

    private fun setUpRestView(){
        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }
        setRestProgressBar()
    }

    private fun setUpWorkoutView(){
        binding?.flProgressBar?.visibility = View.INVISIBLE
        binding?.tvTitle?.text = "Workout name"
        binding?.flProgressBarWorkout?.visibility = View.VISIBLE

        if(workoutTimer != null){
            workoutTimer?.cancel()
            workoutProgress = 0
        }

        setWorkoutProgressBar()
    }

    private fun setRestProgressBar(){
        binding?.progressBar?.progress = restProgress
        restTimer = object: CountDownTimer(5000, 1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress = 5 - restProgress
                binding?.tvTimer?.text = (5-restProgress).toString()
            }

            override fun onFinish() {
                setUpWorkoutView()
            }

        }.start()

    }

    private fun setWorkoutProgressBar(){
        binding?.progressBarWorkout?.progress = workoutProgress
        workoutTimer = object: CountDownTimer(30000, 1000){
            override fun onTick(p0: Long) {
                workoutProgress++
                binding?.progressBarWorkout?.progress = 30 - workoutProgress
                binding?.tvTimerWorkout?.text = (30-workoutProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(
                    this@WorkoutActivity,
                    "30'' over, let's go to the rest view!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }.start()

    }

    override fun onDestroy() {
        super.onDestroy()

        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }
        if(workoutTimer != null){
            workoutTimer?.cancel()
            workoutProgress = 0
        }
        binding = null
    }

}