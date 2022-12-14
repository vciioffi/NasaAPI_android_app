package com.example.theuniverseapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.theuniverseapp.apod.presentation.ApodViewModel
import com.example.theuniverseapp.apod.presentation.view.ApodPaggerAdapter
import com.example.theuniverseapp.databinding.FragmentApodBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
    ): View {
        binding = FragmentApodBinding.inflate(inflater, container, false)
        val adapter = ApodPaggerAdapter(arrayListOf())
        viewLifecycleOwner.lifecycleScope.launch() {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    viewModel.uiState.value.apply {
                        this.listApodModel?.let { it1 -> adapter.apods.addAll(it1) }
                        adapter.reverseList()

                        binding.viewPagerApod.apply {
                            this.adapter = adapter
                            this.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                            this.offscreenPageLimit = 10000
                          /*  when(binding.viewPagerApod.currentItem >= binding.viewPagerApod.adapter?.itemCount!!-3){
                                true ->viewModel.updateApodListPagination()

                                else -> {}
                            }*/
                        }


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


}