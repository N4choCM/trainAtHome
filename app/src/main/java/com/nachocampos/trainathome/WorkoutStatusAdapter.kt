package com.nachocampos.trainathome

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nachocampos.trainathome.databinding.ItemWorkoutStatusBinding

class WorkoutStatusAdapter(val items: ArrayList<WorkoutModel>):
    RecyclerView.Adapter<WorkoutStatusAdapter.ViewHolder>() {


        class ViewHolder(binding: ItemWorkoutStatusBinding): RecyclerView.ViewHolder(binding.root){
            val tvItem = binding.tvItem
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemWorkoutStatusBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: WorkoutModel = items[position]
        holder.tvItem.text = model.getId().toString()

        when{
            model.getIsSelected() -> {
                holder.tvItem.background =
                    ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.item_circular_color_selected_bg)
                holder.tvItem.setTextColor(Color.parseColor("#000000"))
            }
            model.getIsCompleted() -> {
                holder.tvItem.background =
                    ContextCompat.getDrawable(holder.itemView.context,
                        R.drawable.item_circular_color_completed_bg)
                holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else -> {
                holder.tvItem.background =
                    ContextCompat.getDrawable(holder.itemView.context,
                        R.drawable.item_circular_color_gray_bg)
                holder.tvItem.setTextColor(Color.parseColor("#000000"))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}