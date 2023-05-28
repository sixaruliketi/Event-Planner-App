package com.example.eventplanner.signIn_signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.eventplanner.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInFragment : Fragment() {
    private var _binding : FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.apply {
            signInBtn.setOnClickListener {

                val userEmail = signInEmailET.text.toString()
                val userPassword = editTextTextPassword.text.toString()

                if (userEmail.isEmpty() or userPassword.isEmpty()) return@setOnClickListener

                auth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener {
                    if (it.isSuccessful){
                        Navigation.findNavController(binding.root).navigate(SignInFragmentDirections.actionSignInFragmentToDashboardFragment())
                    } else {
                        Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            signInSignUpBtn.setOnClickListener {
                Navigation.findNavController(binding.root).navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())

            }
        }
    }
}