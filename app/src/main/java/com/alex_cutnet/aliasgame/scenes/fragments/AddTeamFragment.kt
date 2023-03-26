package com.alex_cutnet.aliasgame.scenes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alex_cutnet.aliasgame.databinding.FragmentAddTeamBinding
import com.alex_cutnet.aliasgame.scenes.vm.AddTeamViewModel

class AddTeamFragment : Fragment() {

    private var _binding: FragmentAddTeamBinding? = null
    private val binding: FragmentAddTeamBinding
        get() = _binding ?: throw RuntimeException("FragmentAddTeamBinding == null")


    private val viewModel: AddTeamViewModel by lazy {
        ViewModelProvider(this)[AddTeamViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        readNicknamesFromFile()
        observeViewModeLiveData()
        generateNewNicknames()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModeLiveData() {
        viewModel.nicknamesLiveData.observe(viewLifecycleOwner) {
            binding.team1.text = it[0]
            binding.team2.text = it[1]
        }
    }

    private fun readNicknamesFromFile() {
        viewModel.nicknames(viewModel.readFileFromAssets(FILE_NICKNAMES))
    }

    private fun generateNewNicknames() {
        binding.generateNewNicknames.setOnClickListener {
            readNicknamesFromFile()
            observeViewModeLiveData()
        }
    }

//    private fun launchGameFragment() {
//        requireActivity().supportFragmentManager.beginTransaction()
//            .replace(
//                R.id.fragment_container,
//                GameFragment.newInstanceGame()
//            )
//            .addToBackStack(null)
//            .commit()
//    }

    companion object {
        private const val FILE_NICKNAMES = "names"

        fun newInstanceAddTeam(): AddTeamFragment = AddTeamFragment()
    }
}

