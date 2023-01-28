package com.alex_cutnet.aliasgame.scenes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.alex_cutnet.aliasgame.R
import com.alex_cutnet.aliasgame.databinding.FragmentAddTeamBinding
import com.alex_cutnet.aliasgame.domain.entities.Team

class AddTeamFragment : Fragment() {

    private var _binding: FragmentAddTeamBinding? = null
    private val binding: FragmentAddTeamBinding
        get() = _binding ?: throw RuntimeException("FragmentAddTeamBinding == null")

    private var countTeams = 0

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


        //
        with(binding) {
            btnAddTeam.setOnClickListener {
                if (countTeams < MAX_COUNT_TEAMS) {
                    arrayTeamsTextView[countTeams].text =
                        getNicknamesWithoutDuplicate().random()

                    arrayTeamsTextView[countTeams].isVisible = true
                    arraySeparatorsTeams[countTeams].isVisible = true

                    if (countTeams == MAX_COUNT_TEAMS - 1) {
                        btnAddTeam.isVisible = false
                    }
                    countTeams++
                }
            }

            btnSaveTeam.setOnClickListener {
                launchGameFragment()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getNicknamesWithoutDuplicate(): ArrayList<String> {
        // reading file from assets
        val nicknamesTextFile = activity?.assets
            ?.open("names")
            ?.bufferedReader()
            .use {
                it?.readLines()?.shuffled()
            } ?: throw RuntimeException("file in not found")

        val localNicknamesList = ArrayList<String>()

        // checking list for duplicates
        for (nickname in nicknamesTextFile) {
            if (!localNicknamesList.contains(nickname)) {
                localNicknamesList.add(nickname)
                if (localNicknamesList.size == MAX_COUNT_TEAMS) {
                    break
                }
            } else continue
        }

        return localNicknamesList
    }


    private fun launchGameFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, GameFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    companion object {
        private const val MAX_COUNT_TEAMS = 5
        private const val NUMBER_OF_TEAMS = "count teams"

        fun newInstanceToGame(): AddTeamFragment {
            return AddTeamFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList(NUMBER_OF_TEAMS, getNicknamesWithoutDuplicate())
                }
            }
        }
    }
}
