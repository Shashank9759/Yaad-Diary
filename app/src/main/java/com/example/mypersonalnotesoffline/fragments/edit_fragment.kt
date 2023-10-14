package com.example.mypersonalnotesoffline.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mypersonalnotesoffline.Database.model
import com.example.mypersonalnotesoffline.R
import com.example.mypersonalnotesoffline.ViewModel.MyViewModel
import com.example.mypersonalnotesoffline.databinding.FragmentEditFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


@Suppress("DEPRECATION")
class edit_fragment : Fragment() {
    lateinit var binding:FragmentEditFragmentBinding
    val args: edit_fragmentArgs by navArgs()


    val viewModel: MyViewModel by viewModels()  //without making instance we can use it  by this
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
        binding= FragmentEditFragmentBinding.inflate(inflater,container,false)
       setHasOptionsMenu(true)
        val data = args.actionArgument
        binding.editTitle.setText(data.title)
        binding.editSubtitle.setText(data.subtitle)
        binding.editDescription.setText(data.description)

        when(data.priority){

            1->{
                binding.editRed.setImageResource(R.drawable.done_button)
            }
            2->{
                binding.editYellow.setImageResource(R.drawable.done_button)
            }
           3->{
                binding.editBlue.setImageResource(R.drawable.done_button)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var priority= args.actionArgument.priority
        binding.editRed.setOnClickListener{

            priority=1
            binding.editRed.setImageResource(R.drawable.done_button)
            binding.editYellow.setImageResource(0)
            binding.editBlue.setImageResource(0)




        }
        binding.editYellow.setOnClickListener{

            priority=2
            binding.editYellow.setImageResource(R.drawable.done_button)
            binding.editRed.setImageResource(0)
            binding.editBlue.setImageResource(0)



        }
        binding.editBlue.setOnClickListener{
            priority=3
            binding.editBlue.setImageResource(R.drawable.done_button)
            binding.editYellow.setImageResource(0)
            binding.editRed.setImageResource(0)




        }



        binding.editSave.setOnClickListener {

           val id= args.actionArgument.id
            val title = binding.editTitle.text.toString()
            val subtitle = binding.editSubtitle.text.toString()
            val decription = binding.editDescription.text.toString()


            val dateFormat = SimpleDateFormat("h:mm a dd/MM/yyyy", Locale.ENGLISH)
            dateFormat.timeZone = TimeZone.getTimeZone("Asia/Kolkata")

            val currentTime = Date()
            val DateTimeInString =dateFormat.format(currentTime)


            viewModel.updateNotes(model(id,title,subtitle,decription,DateTimeInString, priority))


            Toast.makeText(requireContext(),"Notes Updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).navigate(R.id.action_edit_fragment_to_home_fragment)





        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.delete,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

             if(item.itemId==R.id.menu_delete){
                 val bottomsheet= BottomSheetDialog(requireContext(),R.style.CustomBottomSheetDialogTheme)
                 bottomsheet.setContentView(R.layout.bottomsheet)
                 bottomsheet.show()

                 bottomsheet.findViewById<Button>(R.id.yes)?.setOnClickListener {
                     viewModel.deleteNotes(args.actionArgument.id)
                     Toast.makeText(requireContext(),"Notes Deleted", Toast.LENGTH_SHORT).show()
                     bottomsheet.dismiss()
                     findNavController().popBackStack()

                 }

                 bottomsheet.findViewById<Button>(R.id.no)?.setOnClickListener {
                    bottomsheet.dismiss()

                 }
             }


        return super.onOptionsItemSelected(item)
    }


}