package com.yzg.basiclib.utils

import android.app.Activity
import android.content.Context
import androidx.annotation.Keep
import androidx.fragment.app.Fragment
import com.yzg.basiclib.logger.loge
import java.util.*

@Keep
class ActivityUtil private constructor() {

    companion object {
        val instance = SingletonHolder.holder
        private var activityStack: Stack<Activity>? = null
        private var fragmentList: ArrayList<Fragment>? = null
    }

    private object SingletonHolder {
        val holder = ActivityUtil()
    }

    val allFragment: List<Fragment>?
        get() = if (fragmentList != null) {
            fragmentList
        } else null

    fun addFragment(index: Int, fragment: Fragment) {
        if (fragmentList == null) {
            fragmentList = arrayListOf()
        }
        fragmentList!!.add(index, fragment)
    }

    fun getFragment(index: Int): Fragment? {
        return if (fragmentList != null) {
            fragmentList!![index]
        } else null
    }

    /**
     * 添加指定Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack?.let {
            it.add(activity)
        }
    }


    /**
     * 获取当前Activity
     */
    fun currentActivity(): Activity? {
        activityStack?.let {
            return it.lastElement()
        }
        return null
    }


    /**
     * 结束当前Activity
     */
    fun finishActivity() {
        currentActivity()?.let {
            finishActivity(it)
        }
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity) {
        activityStack?.remove(activity)
        activity.finish()
    }

    /**
     * 结束指定Class的Activity
     */
    fun finishActivity(cls: Class<*>) {
        activityStack?.let {
            for (activity in it) {
                if (activity.javaClass == cls) {
                    finishActivity(activity)
                    break
                }
            }
        }

    }

    /**
     * 结束全部的Activity
     */
    fun finishAllActivity() {
        activityStack?.let {
            var i = 0
            val size = it.size
            while (i < size) {
                if (null != activityStack!![i]) {
                    activityStack!![i].finish()
                }
                i++
            }
            it.clear()
        }

    }

    /**
     * 退出应用程序
     */
    fun exitApp(context: Context) {
        try {
            finishAllActivity()
            //杀死后台进程需要在AndroidManifest中声明android.permission.KILL_BACKGROUND_PROCESSES；
            val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as android.app.ActivityManager
            activityManager.killBackgroundProcesses(context.packageName)
            //System.exit(0);
        } catch (e: Exception) {
            loge {
                "ActivityManager app exit" + e.message
            }
        }

    }

}
