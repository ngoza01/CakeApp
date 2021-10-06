package com.cake.cakeapp.modules.cakedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cake.cakeapp.R
import com.cake.cakeapp.modules.cakelist.Cake
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cake_list_item.*
import kotlinx.android.synthetic.main.cake_list_item.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

/**
 * A simple [Fragment] subclass.
 * Use the [CakeDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CakeDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cake_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name.text = param1
        description.text = param3

        Picasso.get()
            .load(param2)
            .placeholder(R.drawable.ic_default_cake_24)
            .error(R.drawable.ic_default_cake_24)
            .into(cakeImageView);

    }
    companion object {

        val TAG = CakeDetailsFragment::class.java.simpleName

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CakeDetailsFragment.
         */
        @JvmStatic
        fun newInstance(cake: Cake) =
            CakeDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, cake.title)
                    putString(ARG_PARAM2, cake.image)
                    putString(ARG_PARAM3, cake.desc)
                }
            }
    }
}