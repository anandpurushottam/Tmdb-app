package com.example.moviesapp.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object Navigator {


     fun addFragment( manager: FragmentManager, fragment: Fragment,container:Int,tag: String, addToBackStack: Boolean=true) {
        val transaction = manager.beginTransaction()
            .add(container, fragment, tag)
        if (addToBackStack) {
            transaction.addToBackStack(tag)
        }
        transaction.commitAllowingStateLoss()
    }

     fun replaceFragment( manager: FragmentManager, fragment: Fragment,container:Int,tag: String, addToBackStack: Boolean=true) {
        val transaction = manager.beginTransaction()
            .replace(container, fragment, tag)
        if (addToBackStack) {
            transaction.addToBackStack(tag)
        }
        transaction.commitAllowingStateLoss()
    }
}