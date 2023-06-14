package com.example.login.presentacion.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.login.databinding.ActivityMainMenuBinding
import com.example.login.firebase.domain.model.Post
import com.example.login.firebase.util.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainMenu : Fragment() {
    private  var _binding: ActivityMainMenuBinding? = null
    private val binding get() = _binding!!

    private val args: MainMenuArgs by navArgs()
    private val viewModel: PostViewModel by viewModels()
    private val postListAdapter: PostListAdapter = PostListAdapter()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ActivityMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.textView2.text = " Bienvenido/a: ${args.nombre}  ${args.apellido} "

        initListComponent()
        initListeners()
        initObservers()
        getPost()
    }


    private fun initObservers() {
        viewModel.addPostState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is Resource.Succes -> getPost()
                is Resource.Error -> Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                    .show()
                else -> Unit
            }
        }
        viewModel.deletePostState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is Resource.Succes -> getPost()
                is Resource.Error -> Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                    .show()
                else -> Unit
            }
        }
        viewModel.postListState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is Resource.Succes -> postListAdapter.submitList(state.data)
                is Resource.Error -> Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                    .show()
                else -> Unit
            }
        }
    }

    private fun getPost() {
        viewModel.getAllPost()
    }
    private fun initListComponent() {
        binding.rv.apply {
            setHasFixedSize(true)
            adapter = postListAdapter

        }
    }
    private fun initListeners() {
        with(binding) {
            btnPublicar.setOnClickListener { showAddPostDialog() }
            postListAdapter.setPostClickListener { showDeletePostDialog(it) }

        }

    }

    private fun showAddPostDialog() {
        val addPostDialog = AddPostDialog()
        addPostDialog.setOnAddPostClickListener {
            viewModel.savePost(Post(
                post = it,
                userName = args.uid
            ))
        }
        addPostDialog.show(parentFragmentManager, "add_note_dialog")
    }

    private fun showDeletePostDialog(post: Post) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Eliminar nota")
        alertDialog.setMessage("Se eliminará  esta nota definitivamente")

        alertDialog.setPositiveButton(android.R.string.yes) { dialog, which ->
            viewModel.deletePost(post)
        }

        alertDialog.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(requireContext(),
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }
        alertDialog.show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}