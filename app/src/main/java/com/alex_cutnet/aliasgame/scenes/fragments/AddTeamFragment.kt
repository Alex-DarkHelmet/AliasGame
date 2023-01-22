package com.alex_cutnet.aliasgame.scenes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.alex_cutnet.aliasgame.databinding.FragmentAddTeamBinding

class AddTeamFragment : Fragment() {

    private var _binding: FragmentAddTeamBinding? = null
    private val binding: FragmentAddTeamBinding
        get() = _binding ?: throw RuntimeException("FragmentAddTeamBinding == null")

    private var countTeams = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrayTeamsTextView = listOf(
            binding.team1,
            binding.team2,
            binding.team3,
            binding.team4,
            binding.team5,
        )
        val arraySeparatorsTeams = listOf(
            binding.sep1,
            binding.sep2,
            binding.sep3,
            binding.sep4,
            binding.sep5,
        )

        // btn for create new team
        with(binding) {
            btnAddTeam.setOnClickListener {
                if (countTeams < 6) {
                    arrayTeamsTextView[countTeams - 1].text = readFileWithNicknames().random()
                    arrayTeamsTextView[countTeams - 1].isVisible = true
                    arraySeparatorsTeams[countTeams - 1].isVisible = true
                    countTeams++
                } else {
                    btnAddTeam.isVisible = false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //    reading file from assets
    private fun readFileWithNicknames(): List<String> {
        val textFile = activity?.assets
            ?.open("names")
            ?.bufferedReader()
            .use {
                it?.readLines()
            } ?: throw RuntimeException("file in not found")
        return textFile
    }
}
