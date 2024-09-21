package com.example.nutriapp.auth
import com.google.firebase.auth.FirebaseAuth


class FirebaseAuthService {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun register(email: String, password: String, onComplete: (Boolean, String?) -> Unit){
       firebaseAuth.createUserWithEmailAndPassword(email, password)
           .addOnCompleteListener { task ->
               if(task.isSuccessful){
                   onComplete(true, null)
               }else{
                   onComplete(false, task.exception?.message)
               }

           }
    }

    fun login(email: String, password: String, onComplete: (Boolean, String?) -> Unit){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    onComplete(true, null)
                }else{
                    onComplete(false, task.exception?.message)
                }

            }
    }

    fun logout(){
        firebaseAuth.signOut()
    }
}