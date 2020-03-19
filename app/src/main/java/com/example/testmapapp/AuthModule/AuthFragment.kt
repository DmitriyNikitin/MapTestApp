package com.example.testmapapp.AuthModule

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.example.testmapapp.R
import kotlinx.android.synthetic.main.fragment_auth.*

class AuthFragment : Fragment(){
    private  var authViewModel: AuthViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel = ViewModelProviders.of(this, AuthViewModelFactory(findNavController())).get(AuthViewModel::class.java)

        val liveData = authViewModel?.getLiveData()
         liveData?.observe(viewLifecycleOwner, Observer<Boolean> {t -> errorMessage.visibility = if(t)View.GONE else View.VISIBLE  })

        nextBtn.setOnClickListener {
            authViewModel?.checkVerify(
                AuthModel(
                login = loginET.text.toString(),
                password = passwordET.text.toString()
            ))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        authViewModel?.getLiveData()?.removeObservers(viewLifecycleOwner)
    }
}