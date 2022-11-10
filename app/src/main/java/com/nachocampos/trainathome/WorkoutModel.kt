package com.nachocampos.trainathome

class WorkoutModel (
    private var id: Int,
    private var name: String,
    private var picture: Int,
    private var isCompleted: Boolean,
    private var isSelected: Boolean
        ){
    fun getId(): Int{
        return id
    }

    fun setId(id: Int){
        this.id = id
    }

    fun getName(): String{
        return name
    }

    fun setName(name: String){
        this.name = name
    }

    fun getPicture(): Int{
        return picture
    }

    fun setPicture(picture: Int){
        this.picture = picture
    }

    fun getIsCompleted(): Boolean{
        return isCompleted
    }

    fun setIsCompleted(isCompleted: Boolean){
        this.isCompleted = isCompleted
    }

    fun getIsSelected(): Boolean{
        return isSelected
    }

    fun setIsSelected(isSelected: Boolean){
        this.isSelected = isSelected
    }
}