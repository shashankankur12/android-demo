package com.example.androiddemo.utils

import com.example.androiddemo.data.responses.EmployeeData
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

object Utils {
    fun sortEmployeesList(employeesList: ArrayList<EmployeeData>?, isTemporaryLumpers: Boolean = false): ArrayList<EmployeeData> {
        return if (!employeesList.isNullOrEmpty()) {
            employeesList.sortWith(Comparator { lumper1, lumper2 ->
                if (!lumper1.firstName.isNullOrEmpty() && !lumper2.firstName.isNullOrEmpty()) {
                    lumper1.firstName?.toLowerCase(Locale.getDefault())!!.compareTo(lumper2.firstName?.toLowerCase(
                        Locale.getDefault())!!)
                } else {
                    0
                }
            })

            if (isTemporaryLumpers) {
                val iterate = employeesList.listIterator()
                while (iterate.hasNext()) {
                    val oldValue = iterate.next()
                    oldValue.isTemporaryAssigned = true
                    iterate.set(oldValue)
                }
            }
            employeesList
        } else ArrayList()
    }
}