package simple.program.data.db.cache

import android.content.Context
import android.content.SharedPreferences
import simple.program.data.util.Constant.KEY_EXPIRE_IN
import simple.program.data.util.Constant.KEY_REFRESH_TOKEN
import simple.program.data.util.Constant.KEY_TOKEN
import simple.program.data.util.Constant.KEY_TOKEN_TYPE
import simple.program.data.util.Constant.KEY_USER_EMAIL
import simple.program.data.util.Constant.KEY_USER_FIRSTNAME
import simple.program.data.util.Constant.KEY_USER_ID
import simple.program.data.util.Constant.KEY_USER_LASTNAME
import simple.program.data.util.Constant.KEY_USER_MIN
import simple.program.domain.model.Profile
import simple.program.domain.model.UserSession
import javax.inject.Inject

interface SessionManager {
    fun save(session: UserSession)
    fun getSession(): UserSession
    fun saveProfile(profile: Profile)
    fun getProfile(): Profile
    fun checkOnBoarding() : Boolean
    fun finishOnBoarding()
    fun getEmail(): String
    fun saveEmail(email: String)
}
