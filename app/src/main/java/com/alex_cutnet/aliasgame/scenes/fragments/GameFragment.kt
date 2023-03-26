package com.alex_cutnet.aliasgame.scenes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alex_cutnet.aliasgame.databinding.FragmentGameBinding
import com.alex_cutnet.aliasgame.scenes.vm.GameViewModel

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(this)[GameViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nameTeam.text = arguments?.getString(TEAM_KEY)

        observeViewModel()
        btnStartGameHandler()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun btnStartGameHandler() {
        with(binding) {
            btnStartGame.setOnClickListener {
                viewModel.startGame()
                viewModel.getQuestions(readFileWithQuestions())
                visibilityButtons()
            }
        }
    }


    private fun visibilityButtons() {
        with(binding) {
            btnStartGame.isEnabled = false
            btnStartGame.isVisible = false
            btnCorrect.isVisible = true
            btnIncorrect.isVisible = true
        }
    }

    private fun observeViewModel() {
        viewModel.formattedTime.observe(viewLifecycleOwner) {
            binding.timer.text = it
        }

        viewModel.questionWord.observe(viewLifecycleOwner) {
            binding.questionWord.text = it.first()
        }
    }

    private fun readFileWithQuestions(): List<String> {
        val textFile = activity?.assets
            ?.open("words.txt")
            ?.bufferedReader().use {
                it?.readLines()?.shuffled()
            } ?: throw RuntimeException("file in not found")

        return textFile
    }

    companion object {
        private const val TEAM_KEY = "team"

        fun newInstanceGame(team: String): GameFragment =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putString(TEAM_KEY, team)
                }
            }
    }
}