package com.example.paradox.ui.announcement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paradox.R
import com.example.paradox.adapter.WorkAdapter
import com.example.paradox.models.Work
import com.example.paradox.models.Works
import com.example.paradox.network.JobOfferService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AnnouncementFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AnnouncementFragment : Fragment() {

    var works: ArrayList<Work> = ArrayList()
    var workAdapter = WorkAdapter(works)

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
        val vista: View = inflater.inflate(R.layout.fragment_announcement, container, false)

        initView(vista)
        loadWorks(vista)

        return vista
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AnnouncementFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AnnouncementFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun initView(view: View) {
        val rvWork = view?.findViewById<RecyclerView>(R.id.rvWork)
        rvWork.adapter = workAdapter
        rvWork.layoutManager = LinearLayoutManager(context)
    }


    private fun loadWorks(view: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://movilesback.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jobOfferService: JobOfferService = retrofit.create((JobOfferService::class.java))

        val request = jobOfferService.getAllJobOffers()

        request.enqueue(object : Callback<Works> {
            override fun onResponse(call: Call<Works>, response: Response<Works>) {
                if (response.isSuccessful) {
                    val content = response.body()
                    if (content != null) {
                        view.findViewById<TextView>(R.id.tvCountOffers).text = content.works.size.toString()
                        for (i in content.works.indices) {
                            val subtitle = content.works.get(i).subtitle
                            val job = content.works.get(i).job
                            val time = content.works.get(i).time
                            val info = content.works.get(i).info
                            val work = Work("Claro", subtitle, job, time, info)
                            works.add(work)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<Works>, t: Throwable) {
                Toast.makeText(context, "ERROR", Toast.LENGTH_LONG).show()
            }
        })
    }

}