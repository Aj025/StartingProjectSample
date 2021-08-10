package simple.program.data.db.cache

import android.content.SharedPreferences
import simple.program.data.util.Constant
import simple.program.domain.model.Profile
import simple.program.domain.model.UserSession
import javax.inject.Inject


class SessionManagerImpl @Inject constructor(
    val prefs: SharedPreferences
) : SessionManager {

    override fun save(session: UserSession) {
        prefs.let { pref ->
            val editor = pref.edit()
            editor.putString(Constant.KEY_TOKEN, session.accessToken)
            editor.putString(Constant.KEY_REFRESH_TOKEN, session.refreshToken)
            editor.putInt(Constant.KEY_EXPIRE_IN, session.expiresIn)
            editor.putString(Constant.KEY_TOKEN_TYPE, session.tokenType)
            editor.putString(Constant.KEY_USER_ID, "")
            editor.putString(Constant.KEY_USER_MIN, "")
            editor.putString(Constant.KEY_USER_FIRSTNAME, "")
            editor.putString(Constant.KEY_USER_LASTNAME, "")
            editor.putString(Constant.KEY_USER_EMAIL, "")
            editor.apply()
        }
    }

    override fun getSession(): UserSession {
        prefs.let { pref ->
            val token = pref.getString(Constant.KEY_TOKEN, "")
            val refreshToken = pref.getString(Constant.KEY_REFRESH_TOKEN, "")
            val expiresIn = pref.getInt(Constant.KEY_EXPIRE_IN, 0)
            val tokenType = pref.getString(Constant.KEY_TOKEN_TYPE, "")

            return UserSession(
                accessToken = token ?: "",
                expiresIn = expiresIn,
                refreshToken = refreshToken ?: "",
                tokenType = tokenType ?: ""
            )
        }
    }

    override fun saveProfile(profile: Profile) {
        prefs.let { pref ->
            val editor = pref.edit()
            editor.putInt(Constant.KEY_USER_POINT, profile.points)
            editor.putString(Constant.KEY_USER_EMAIL, profile.email)
            editor.putString(Constant.KEY_USER_FIRSTNAME, profile.fName)
            editor.putString(Constant.KEY_USER_LASTNAME,profile.lName)
            editor.putInt(Constant.KEY_USER_ID, profile.id)
            editor.putString(Constant.KEY_USER_MIN, profile.min)
            editor.putString(Constant.KEY_USER_PICTURE, profile.picture)
            editor.putString(Constant.KEY_USER_RANK, profile.rank)
            editor.putInt(Constant.KEY_USER_TOTAL_POINTS, profile.totalPoints)
            editor.putInt(Constant.KEY_USER_TOTAL_DONATIONS, profile.totalDonations)
            editor.apply()
        }
    }

    override fun getProfile(): Profile {
        prefs.let { pref ->
            val points: Int = pref.getInt(Constant.KEY_USER_POINT, 0)
            val email: String =  pref.getString(Constant.KEY_USER_EMAIL, "") ?: ""
            val fName: String = pref.getString(Constant.KEY_USER_FIRSTNAME, "") ?: ""
            val id: Int = pref.getInt(Constant.KEY_USER_ID, 0)
            val lName: String= pref.getString(Constant.KEY_USER_LASTNAME, "") ?: ""
            val min: String = pref.getString(Constant.KEY_USER_MIN, "") ?: ""
            val picture: String = pref.getString(Constant.KEY_USER_PICTURE, "") ?: ""
            val rank: String = pref.getString(Constant.KEY_USER_RANK, "") ?: ""
            val totalPoints: Int = pref.getInt(Constant.KEY_USER_TOTAL_POINTS, 0)
            val totalDonations: Int = pref.getInt(Constant.KEY_USER_TOTAL_DONATIONS, 0)

            return Profile(
                points = points,
                email = email,
                fName = fName,
                id = id,
                lName = lName,
                min = min,
                picture = picture,
                rank = rank,
                totalPoints = totalPoints,
                totalDonations = totalDonations
            )
        }
    }

    override fun checkOnBoarding(): Boolean {
        prefs.let { pref ->
            return pref.getBoolean(Constant.KEY_ON_BOARDING, false)
        }
    }

    override fun finishOnBoarding() {
        prefs.let { pref ->
            val editor = pref.edit()
            editor.putBoolean(Constant.KEY_ON_BOARDING, true)
            editor.apply()
        }
    }

    override fun getEmail(): String {
        prefs.let { pref ->
            return pref.getString(Constant.KEY_USER_EMAIL, "") ?: ""
        }
    }

    override fun saveEmail(email: String) {
        prefs.let { pref ->
            val editor = pref.edit()
            editor.putString(Constant.KEY_USER_EMAIL, email)
            editor.apply()
        }
    }
}