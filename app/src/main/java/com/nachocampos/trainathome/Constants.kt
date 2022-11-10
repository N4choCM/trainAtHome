package com.nachocampos.trainathome

object Constants {

    fun defaultWorkoutList(): ArrayList<WorkoutModel>{
        val workoutList = ArrayList<WorkoutModel>()
        val jumpingJacks = WorkoutModel(
            1,
            "Jumping Jacks",
            R.drawable.ic_jumping_jacks,
            false,
            false
        )
        workoutList.add(jumpingJacks)

        val abdominalCrunch = WorkoutModel(
            2,
            "Abdominal Crunch",
            R.drawable.ic_abdominal_crunch,
            false,
            false
        )
        workoutList.add(abdominalCrunch)

        val skipping = WorkoutModel(
            3,
            "Skipping",
            R.drawable.ic_high_knees_running_in_place,
            false,
            false
        )
        workoutList.add(skipping)

        val lunge = WorkoutModel(
            4,
            "Lunge",
            R.drawable.ic_lunge,
            false,
            false
        )
        workoutList.add(lunge)

        val plank = WorkoutModel(
            5,
            "Plank",
            R.drawable.ic_plank,
            false,
            false
        )
        workoutList.add(plank)

        val pushUp = WorkoutModel(
            6,
            "Push Up",
            R.drawable.ic_push_up,
            false,
            false
        )
        workoutList.add(pushUp)

        val pushUpRotation = WorkoutModel(
            7,
            "Push Up and Rotation",
            R.drawable.ic_push_up_and_rotation,
            false,
            false
        )
        workoutList.add(pushUpRotation)

        val sidePlank = WorkoutModel(
            8,
            "Side Plank",
            R.drawable.ic_side_plank,
            false,
            false
        )
        workoutList.add(sidePlank)

        val squat = WorkoutModel(
            9,
            "Squat",
            R.drawable.ic_squat,
            false,
            false
        )
        workoutList.add(squat)

        val stepUp = WorkoutModel(
            10,
            "Step Up",
            R.drawable.ic_step_up_onto_chair,
            false,
            false
        )
        workoutList.add(stepUp)

        val tricepsDip = WorkoutModel(
            11,
            "Triceps Dip",
            R.drawable.ic_triceps_dip_on_chair,
            false,
            false
        )
        workoutList.add(tricepsDip)

        val wallSquat = WorkoutModel(
            12,
            "Wall Squat",
            R.drawable.ic_wall_sit,
            false,
            false
        )
        workoutList.add(wallSquat)

        return workoutList
    }
}