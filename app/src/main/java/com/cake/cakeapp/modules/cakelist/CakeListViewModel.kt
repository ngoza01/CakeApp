package com.cake.cakeapp.modules.cakelist

import android.graphics.ColorSpace.Model
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cake.cakeapp.repository.CakeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


interface OnItemClickListener {
    fun onItemClick(cake: Cake)
}

@HiltViewModel
class CakeListViewModel @Inject constructor(
    private  val cakeRepository: CakeRepository
): ViewModel(){


    private val _cakes = MutableStateFlow<CakeEvent>(CakeEvent.Empty)

    val cakes : StateFlow<CakeEvent>
        get() = _cakes


    fun fetchCakes()  = viewModelScope.launch {
        _cakes.value = CakeEvent.Loading
        cakeRepository.getCakes().let { response ->
            if (response.isSuccessful){
                response.let {
                    it.body()?.let { cakesList ->
                        _cakes.value =  CakeEvent.Success(cakesList)
                    }
                }
            }else{
                _cakes.value = CakeEvent.Error(response.errorBody().toString())
            }
        }
    }

}