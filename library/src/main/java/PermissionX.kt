import androidx.fragment.app.FragmentActivity
import com.permissionx.imheardev.InvisibleFragment
import com.permissionx.imheardev.PermissionCallback

/**
 * Created by wuto on 2021-12-14.
 */
object PermissionX {
    private const val TAG = "InvisibleFragment"

    fun request(activity:FragmentActivity,vararg permissions:String,callback:
    PermissionCallback){
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if(existedFragment != null){
            existedFragment as InvisibleFragment
        }else{
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment,TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback,*permissions)
    }
}