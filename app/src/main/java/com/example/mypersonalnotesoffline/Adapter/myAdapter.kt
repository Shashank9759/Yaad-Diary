package com.example.mypersonalnotesoffline.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation

import androidx.recyclerview.widget.RecyclerView

import com.example.mypersonalnotesoffline.Database.model
import com.example.mypersonalnotesoffline.ParacaleableModel.paracelableModel
import com.example.mypersonalnotesoffline.R
import com.example.mypersonalnotesoffline.fragments.home_fragmentDirections


class myAdapter(val context: Context, var list:List<model>):RecyclerView.Adapter<myAdapter.ViewHolder>() {

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val title=view.findViewById<TextView>(R.id.item_title)
        val description=view.findViewById<TextView>(R.id.item_description)
        val date=view.findViewById<TextView>(R.id.item_date)
        val priority=view.findViewById<ImageView>(R.id.item_priority)
        val root=view.findViewById<LinearLayout>(R.id.root)


    }


    fun filteringFromAdapter (newList:ArrayList<model>){
               list=newList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.itemview,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val currentData = list[position]
      holder.title.text=currentData.title
      holder.description.text=currentData.description
      holder.date.text=currentData.date



       when(currentData.priority){
         1->{
              holder.priority.setImageResource(R.drawable.red_priority)
          }
            2->{
                holder.priority.setImageResource(R.drawable.yellow_priority)
            }
          3->{
                holder.priority.setImageResource(R.drawable.blue_priority)
            }

        }


        holder.root.setOnClickListener {
            val model= paracelableModel(currentData.id,currentData.title,currentData.subtitle
                ,currentData.description,currentData.date,currentData.priority)
            val action=home_fragmentDirections.actionHomeFragmentToEditFragment(model)
            Navigation.findNavController(it).navigate(action)


        }
    }
}