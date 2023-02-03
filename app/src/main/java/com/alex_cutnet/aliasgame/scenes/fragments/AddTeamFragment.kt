package com.alex_cutnet.aliasgame.scenes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alex_cutnet.aliasgame.R
import com.alex_cutnet.aliasgame.databinding.FragmentAddTeamBinding
import com.alex_cutnet.aliasgame.scenes.AddTeamViewModel

class AddTeamFragment : Fragment() {

    private var _binding: FragmentAddTeamBinding? = null
    private val binding: FragmentAddTeamBinding
        get() = _binding ?: throw RuntimeException("FragmentAddTeamBinding == null")

    private lateinit var viewModel: AddTeamViewModel

    private var countTeams = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTeamBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[AddTeamViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createNewTeam()
        launchGameFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun readNicknamesFromAssets(): List<String> {
        // reading file from assets
        val nicknamesTextFile = activity?.assets
            ?.open("names")
            ?.bufferedReader()
            .use {
                it?.readLines()?.shuffled()
            } ?: throw RuntimeException("file in not found")

        return nicknamesTextFile
    }

    private fun getNicknamesWithoutDuplicate(): List<String> {
        return viewModel.checkNicknamesNotDuplicates(readNicknamesFromAssets())
    }

    private fun initTeamsTextViews(): List<TextView> {
        return listOf(
            binding.team1,
            binding.team2,
            binding.team3,
            binding.team4,
            binding.team5,
        )
    }

    private fun initSeparatorsViews(): List<View> {
        return listOf(
            binding.sep1,
            binding.sep2,
            binding.sep3,
            binding.sep4,
            binding.sep5,
        )
    }

    private fun createNewTeam() {
        val teams = initTeamsTextViews()
        val separators = initSeparatorsViews()

        with(binding) {
            btnAddTeam.setOnClickListener {
                if (countTeams < MAX_COUNT_TEAMS) {
                    teams[countTeams].text =
                        getNicknamesWithoutDuplicate()[countTeams]

                    teams[countTeams].isVisible = true
                    separators[countTeams].isVisible = true

                    if (countTeams == MAX_COUNT_TEAMS - 1) {
                        btnAddTeam.isVisible = false
                    }
                    countTeams++
                }
            }
        }
    }


    private fun launchGameFragment() {
        binding.btnSaveTeam.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    GameFragment.newInstanceGame(getNicknamesWithoutDuplicate().first()))
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        private const val MAX_COUNT_TEAMS = 5

        fun newInstanceAddTeam(): AddTeamFragment = AddTeamFragment()
    }
}
