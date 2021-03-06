package com.example.aidldemo

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aidldemo.databinding.FragmentFirstBinding
import com.example.aidldemo.service.DgzSampleClient
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    val TAG = "FirstFragment"

    private var _binding: FragmentFirstBinding? = null

    lateinit var dgzSample: DgzSampleClient

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val handler : Handler = Handler(Looper.getMainLooper())

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dgzSample = DgzSampleClient()
        dgzSample.bind(context)
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSleep.setOnClickListener {
            dgzSample.sleep(10000)
            Log.d(TAG, "sleep: end")
        }

        binding.buttonSleepOneWay.setOnClickListener {
            dgzSample.sleepOneWay(10000)
            Log.d(TAG, "sleep: end")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()

        updateView.run()
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(updateView)
    }

    val updateView: Runnable = object: Runnable {
        override fun run () {
            binding.textviewFirst.text = (Date()).toString();
            handler.postDelayed(this, 1000)
        }
    }
}