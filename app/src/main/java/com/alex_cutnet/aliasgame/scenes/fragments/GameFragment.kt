package com.alex_cutnet.aliasgame.scenes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alex_cutnet.aliasgame.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //reading file from assets
    private fun readFileWithWords(): List<String> {
        val textFile = activity?.assets
            ?.open("words.txt")
            ?.bufferedReader().use {
                it?.readLines()
            } ?: throw RuntimeException("file in not found")

        return textFile
    }

    companion object {
        fun newInstance(): GameFragment {
            return GameFragment()
        }
    }
}