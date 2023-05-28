package com.example.eventplanner.signIn_signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.eventplanner.databinding.FragmentSignUpBinding
import com.example.eventplanner.signIn_signUp.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUpFragment : Fragment() {
    private var _binding : FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val auth = FirebaseAuth.getInstance()
    private val db = Firebase.database.getReference("User")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.apply {
            signUpBtn.setOnClickListener {
                val name = signUpFullNameET.text.toString()
                val email = signUpEmailET.text.toString()
                val password = signUpPasswordET.text.toString()
                val confirmPassword = signUpConfirmPasswordET.text.toString()

                if (email.isEmpty() || password.isEmpty() || password != confirmPassword || password.length < 6) return@setOnClickListener

                val user = User(name,email,password)

                //adding to database

                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                    if(it.isSuccessful){
                        db.child(auth.uid!!).setValue(user)
                        Navigation.findNavController(binding.root).navigate(SignUpFragmentDirections.actionSignUpFragmentToDashboardFragment())
                    } else {
                        Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}