package com.cake.cakeapp.modules.cakelist

sealed class CakeEvent {
    class Success(val cakes: List<Cake>) : CakeEvent()
    class Error(val message: String) : CakeEvent()
    object Loading : CakeEvent()
    object Empty : CakeEvent()
}