package io.github.anandpc.memeful.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.github.anandpc.memeful.data.Repository
import io.github.anandpc.memeful.data.model.Data

class HomeViewModel @ViewModelInject constructor(
    private val mRepository: Repository
) : ViewModel() {

    private lateinit var memesListLD: LiveData<List<Data>>

    val memes: LiveData<List<Data>>
        get() = memesListLD

    fun getMemes() {
        memesListLD = mRepository.fetchMemes()
    }

}