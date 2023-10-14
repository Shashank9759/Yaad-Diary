package com.example.mypersonalnotesoffline.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation

import com.example.mypersonalnotesoffline.Database.model
import com.example.mypersonalnotesoffline.R

import com.example.mypersonalnotesoffline.ViewModel.MyViewModel
import com.example.mypersonalnotesoffline.databinding.FragmentCreateFragmentBinding
import java.text.SimpleDateFormat

import java.util.Date
import java.util.Locale
import java.util.TimeZone


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class create_fragment : Fragment() {
    val viewModel: MyViewModel by viewModels()  //without making instance we can use it  by this
    lateinit var binding:FragmentCreateFragmentBinding
     var priority:Int=1

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= FragmentCreateFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.createRed.setOnClickListener{

                priority=1
                binding.createRed.setImageResource(R.drawable.done_button)
                binding.createYellow.setImageResource(0)
                binding.createBlue.setImageResource(0)




        }
        binding.createYellow.setOnClickListener{

                priority=2
                binding.createYellow.setImageResource(R.drawable.done_button)
                binding.createRed.setImageResource(0)
                binding.createBlue.setImageResource(0)



        }
        binding.createBlue.setOnClickListener{
                priority=3
                binding.createBlue.setImageResource(R.drawable.done_button)
                binding.createYellow.setImageResource(0)
                binding.createRed.setImageResource(0)




        }
        binding.createSave.setOnClickListener {


            val title = binding.createTitle.text.toString()
            val subtitle = binding.createSubtitle.text.toString()
            val decription = binding.createDescription.text.toString()


            val dateFormat = SimpleDateFormat("h:mm a dd/MM/yyyy", Locale.ENGLISH)
            dateFormat.timeZone = TimeZone.getTimeZone("Asia/Kolkata")

            val currentTime = Date()
            val DateTimeInString =dateFormat.format(currentTime)


         viewModel.addNotes(model(0,title,subtitle,decription,DateTimeInString, priority))


     Toast.makeText(requireContext(),"Notes Added",Toast.LENGTH_SHORT).show()
     Navigation.findNavController(it).navigate(R.id.action_create_fragment_to_home_fragment)





        }


    }
}