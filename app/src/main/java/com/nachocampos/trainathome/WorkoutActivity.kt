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

    private var workoutsList : ArrayList<WorkoutModel>? = null
    private var currentWorkoutPosition = -1

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

        workoutsList = Constants.defaultWorkoutList()

        // This is to add the functionality of going back to the back button of our activity.
        binding?.toolbarWorkout?.setNavigationOnClickListener{
            onBackPressed()
        }

        setUpRestView()
    }

    private fun setUpRestView(){
        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvWorkoutName?.visibility = View.INVISIBLE
        binding?.ivPicture?.visibility = View.INVISIBLE
        binding?.flProgressBarWorkout?.visibility = View.INVISIBLE
        binding?.upcomingLabel?.visibility = View.VISIBLE
        binding?.tvUpcomingWorkoutName?.visibility = View.VISIBLE


        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }

        binding?.tvUpcomingWorkoutName?.text = workoutsList!![currentWorkoutPosition +1].getName()
        setRestProgressBar()
    }

    private fun setUpWorkoutView(){
        binding?.flRestView?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvWorkoutName?.visibility = View.VISIBLE
        binding?.ivPicture?.visibility = View.VISIBLE
        binding?.flProgressBarWorkout?.visibility = View.VISIBLE
        binding?.upcomingLabel?.visibility = View.INVISIBLE
        binding?.tvUpcomingWorkoutName?.visibility = View.INVISIBLE

        if(workoutTimer != null){
            workoutTimer?.cancel()
            workoutProgress = 0
        }

        binding?.ivPicture?.setImageResource(workoutsList!![currentWorkoutPosition].getPicture())
        binding?.tvWorkoutName?.text = workoutsList!![currentWorkoutPosition].getName()
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
                currentWorkoutPosition++
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
                if(currentWorkoutPosition < workoutsList?.size!! -1){
                    setUpRestView()
                }else{
                    Toast.makeText(
                        this@WorkoutActivity,
                        "Congratulations! You've completed this 7' Workout!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
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