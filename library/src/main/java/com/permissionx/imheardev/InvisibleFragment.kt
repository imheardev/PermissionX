package com.permissionx.imheardev

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 * Created by wuto on 2021-12-14.
 */
typealias PermissionCallback = (Boolean,List<String>) -> Unit

class InvisibleFragment: Fragment() {

    private var callback:PermissionCallback? = null

    fun requestNow(cb:PermissionCallback,vararg permissions:String){
        callback = cb
        requestPermissions(permissions,1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == 1){
            val deniedlist = ArrayList<String>()
            for ((index,result) in grantResults.withIndex()){
                if(result != PackageManager.PERMISSION_GRANTED){
                    deniedlist.add(permissions[index])
                }
            }
            val allGranted = deniedlist.isEmpty()
            callback?.let { it(allGranted,deniedlist) }
        }
    }
}