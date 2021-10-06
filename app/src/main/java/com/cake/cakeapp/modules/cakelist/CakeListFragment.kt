package com.cake.cakeapp.modules.cakelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cake.cakeapp.MainActivity
import com.cake.cakeapp.R
import com.cake.cakeapp.modules.cakedetails.CakeDetailsFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_cake_list.*
import kotlinx.coroutines.flow.collect


/**
 * A simple [Fragment] subclass.
 * Use the [CakeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class CakeListFragment : Fragment() {

    private val cakeListViewModel: CakeListViewModel by viewModels()

    private lateinit var cakesRecycleViewAdapter: CakesRecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cake_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cakesRecycleViewAdapter = CakesRecycleViewAdapter(requireContext()){
            val cakeDetailsFragment = CakeDetailsFragment.newInstance(it)
            (activity as MainActivity).replaceFragment(cakeDetailsFragment,CakeDetailsFragment.TAG)
        }
        recyclerView.apply {
        layoutManager = LinearLayoutManager(requireContext())
            adapter = cakesRecycleViewAdapter
        }
        bindViewModel()
        cakeListViewModel.fetchCakes()
    }

    private  fun bindViewModel() {

        lifecycleScope.launchWhenStarted {
            cakeListViewModel.cakes.collect { event ->
                when (event) {
                    is CakeEvent.Success -> {
                       progressBar.visibility = View.INVISIBLE
                        updateUI(event.cakes)
                    }
                    is CakeEvent.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is CakeEvent.Error -> {
                        progressBar.visibility = View.GONE
                        Snackbar.make(recyclerView, "Something went wrong", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                    is CakeEvent.Empty -> {
                       progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun updateUI(cakes: List<Cake>) {
        cakesRecycleViewAdapter.setData(cakes)
    }
    companion object {

        val TAG = CakeListFragment::class.java.simpleName

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment CakeListFragment.
         */
        @JvmStatic
        fun newInstance() = CakeListFragment()
    }
}