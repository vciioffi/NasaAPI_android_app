package com.example.theuniverseapp

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.theuniverseapp.apod.domain.model.ApodModel
import com.example.theuniverseapp.apod.domain.usecases.GetApodWithDateUc
import com.example.theuniverseapp.apod.presentation.ApodViewModel
import com.example.theuniverseapp.apod.presentation.view.ApodPaggerAdapter
import com.example.theuniverseapp.apod.presentation.view.DatePickerFragment
import com.example.theuniverseapp.databinding.FragmentApodBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.Interceptor.Companion.invoke

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ApodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ApodFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel: ApodViewModel by viewModels()
    private lateinit var binding: FragmentApodBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentApodBinding.inflate(inflater, container, false)
        binding.btCalendar.setOnClickListener {
            showDatePickerDialog()
        }
        binding.btList.setOnClickListener {
            setAdapter()
        }
        val adapter = ApodPaggerAdapter(arrayListOf()) { apodModel -> showDialogBox(apodModel) }
        binding.viewPagerApod.apply {
            this.adapter = adapter
            this.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            this.offscreenPageLimit = 10000
        }
        viewLifecycleOwner.lifecycleScope.launch() {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    viewModel.uiState.value.apply {
                        println("sss" + this.listApodModel)
                        adapter.clearList()
                        this.listApodModel?.let { it1 -> adapter.apods.addAll(it1) }

                        adapter.reverseList()
                        adapter.notifyDataSetChanged()


                        /* binding.viewPagerApod.adapter?.let { it1 ->
                             listenOverScroll(
                                 binding.viewPagerApod.currentItem,
                                 it1.itemCount
                             )
                         }*/
                    }
                }
            }
        }


        return binding.root
    }

    private fun setAdapter() {

        viewModel.updateApodList()
    }

    private fun listenOverScroll(currentIndex: Int, size: Int) {
        var index = currentIndex
        binding.viewPagerApod.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                index = position
                println("la pagina actual $index")
                println("tamano de la lista $size")
                if (index >= size - 3) {
                    println(viewModel.uiState.value.listApodModel)
                    viewModel.updateApodListPagination()
                    println(viewModel.uiState.value.listApodModel)
                }
            }
        })
    }

    private fun showDialogBox(apodModel: ApodModel) {
        val dialog = activity?.let { Dialog(it) }
        dialog?.setContentView(R.layout.custom_dialog)
        val imageView = dialog?.findViewById<ImageView>(R.id.img)
        imageView?.load(apodModel.hdurl)

        dialog?.show()

    }

    private fun showDatePickerDialog() {
        val newFragment =
            DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
                // +1 because January is zero
                val selectedDate = year.toString() + "-" + (month + 1) + "-" + day.toString()
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.updateApodWithDate(selectedDate)
                }
            })
        activity?.let { newFragment.show(it.supportFragmentManager, "datePicker") }
    }

}