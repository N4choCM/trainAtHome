package com.nachocampos.trainathome

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import com.nachocampos.trainathome.databinding.ActivityWorkoutBinding
import java.util.*
import kotlin.collections.ArrayList

class WorkoutActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding: ActivityWorkoutBinding? = null

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var workoutTimer: CountDownTimer? = null
    private var workoutProgress = 0

    private var workoutsList : ArrayList<WorkoutModel>? = null
    private var currentWorkoutPosition = -1

    private var textToSpeech: TextToSpeech? = null
    private var soundPlayer: MediaPlayer? = null

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

        textToSpeech = TextToSpeech(this, this)

        // This is to add the functionality of going back to the back button of our activity.
        binding?.toolbarWorkout?.setNavigationOnClickListener{
            onBackPressed()
        }

        setUpRestView()
    }

    private fun setUpRestView(){

        try{
            val soundURI = Uri.parse(
                "android.resource://com.nachocampos.trainathome/" + R.raw.press_start
            )
            soundPlayer = MediaPlayer.create(applicationContext, soundURI)
            soundPlayer?.isLooping = false
            soundPlayer?.start()
        }catch(e: Exception){
            e.printStackTrace()
        }

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

        speakOut(workoutsList!![currentWorkoutPosition].getName())

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

        // Shutting down the Text to Speech feature when activity is destroyed
        if (textToSpeech != null){
            textToSpeech!!.stop()
            textToSpeech!!.shutdown()
        }

        if(soundPlayer != null){
            soundPlayer!!.stop()
        }

        binding = null
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            // Set Spanish as language for the textToSpeech variable.
            val result = textToSpeech?.setLanguage(Locale.UK)

            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TextToSpeech", "The required language is not supported.")
            }
        }else{
            Log.e("TextToSpeech", "Fail when initializing TextToSpeech.")
        }
    }

    private fun speakOut(text: String){
        textToSpeech!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

}