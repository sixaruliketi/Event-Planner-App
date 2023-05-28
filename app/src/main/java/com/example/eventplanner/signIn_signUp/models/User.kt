package com.example.eventplanner.signIn_signUp.models

data class User(val fullName : String ?= null,
                val email: String ?= null,
                val password : String ?= null
)
