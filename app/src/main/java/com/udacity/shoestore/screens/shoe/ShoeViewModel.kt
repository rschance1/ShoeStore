package com.udacity.shoestore.screens.shoe

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    var myShoe: Shoe = Shoe("", 0.0, "", "")

    private var _cancelButton = MutableLiveData<MutableList<Boolean>>()
    val cancel: LiveData<MutableList<Boolean>>
        get() = _cancelButton

    private var _saveButton = MutableLiveData<MutableList<Boolean>>()
    val save: LiveData<MutableList<Boolean>>
        get() = _saveButton

    private var _saveData = MutableLiveData<Boolean>()
    val saveData: LiveData<Boolean>
        get() = _saveData

    private var _cancelData = MutableLiveData<Boolean>()
    val cancelData: LiveData<Boolean>
        get() = _cancelData

    private var _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    fun onSave() {
        _shoes.value?.add(0, myShoe)
        _saveData.value = true
    }

    fun onSaveFinished() {
        _saveData.value = false
        resetMyShoe()
    }

    private fun resetMyShoe() {
        myShoe = Shoe("", 0.0, "", "")
    }

    fun onCancel() {
        _cancelData.value = true
    }

    fun onCancelFinished() {
        _cancelData.value = false
    }

    init {
        _shoes.value = mutableListOf()
    }
}