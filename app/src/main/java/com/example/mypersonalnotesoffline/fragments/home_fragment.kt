package com.example.mypersonalnotesoffline.fragments

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels


import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mypersonalnotesoffline.Adapter.myAdapter
import com.example.mypersonalnotesoffline.Database.model
import com.example.mypersonalnotesoffline.R


import com.example.mypersonalnotesoffline.ViewModel.MyViewModel
import com.example.mypersonalnotesoffline.databinding.FragmentHomeFragmentBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


@Suppress("DEPRECATION")
class home_fragment : Fragment() {

    val viewModel: MyViewModel by viewModels()  //without making instance we can use it  by this
    lateinit var binding:FragmentHomeFragmentBinding
    lateinit var mysearchlist:ArrayList<model>
    lateinit var madapter:myAdapter
    lateinit var searchview:SearchView
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


         binding= FragmentHomeFragmentBinding.inflate(inflater,container,false)



        binding.addbtn.setOnClickListener {


            findNavController().navigate(R.id.action_home_fragment_to_create_fragment)
        }





        viewModel.getNotes().observe(viewLifecycleOwner,{
            val mrecyclerview= binding.myrecyclerview
            mysearchlist= it as ArrayList
             madapter=myAdapter(requireContext(), it)
            mrecyclerview.adapter=madapter
            mrecyclerview.layoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        })


        binding.high.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner,{

                val mrecyclerview= binding.myrecyclerview
                mysearchlist= it as ArrayList
               madapter=myAdapter(requireContext(), it)
                mrecyclerview.adapter=madapter
                mrecyclerview.layoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
            })



        }


        binding.medium.setOnClickListener {

            viewModel.getMediumNotes().observe(viewLifecycleOwner,{

                val mrecyclerview= binding.myrecyclerview
                mysearchlist= it as ArrayList
            madapter=myAdapter(requireContext(), it)
                mrecyclerview.adapter=madapter
                mrecyclerview.layoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
            })


        }



        binding.low.setOnClickListener {

            viewModel.getLowNotes().observe(viewLifecycleOwner,{
                val mrecyclerview= binding.myrecyclerview
                mysearchlist= it as ArrayList
                madapter=myAdapter(requireContext(), it)
                mrecyclerview.adapter=madapter
                mrecyclerview.layoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
            })


        }


        binding.home.setOnClickListener {


            viewModel.getNotes().observe(viewLifecycleOwner,{

                val mrecyclerview= binding.myrecyclerview
                mysearchlist= it as ArrayList
              madapter=myAdapter(requireContext(), it)
                mrecyclerview.adapter=madapter
                mrecyclerview.layoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
            })
        }



        return binding.root


    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.search,menu)


        val item= menu.findItem(R.id.app_bar_search)

        searchview = item?.actionView as SearchView

        searchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

               textChangeAction(newText)
             return true
            }

        })



        super.onCreateOptionsMenu(menu, inflater)
    }



    private fun textChangeAction(text: String?) {

        val tempList =ArrayList<model>()
        for(i in mysearchlist){
            if(i.title.contains(text!!)||i.subtitle.contains(text!!)||i.description.contains(text!!)){
                 tempList.add(i)
                }

        }
        madapter.filteringFromAdapter(tempList)


    }





    
}