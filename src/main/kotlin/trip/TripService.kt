package org.tripservicekata.trip

import org.craftedsw.tripservicekata.user.User
import org.craftedsw.tripservicekata.user.UserSession
import org.tripservicekata.exception.UserNotLoggedInException
import java.util.*

open class TripService {

    fun getTripsByUser(user: User): List<Trip> {
        var tripList: List<Trip> = ArrayList<Trip>()
        val loggedUser: User? = getLoggetInUser()
        var isFriend: Boolean = false
        if (loggedUser != null) {
            for (friend in user.friends) {
                if (friend == loggedUser) {
                    isFriend = true
                    break
                }
            }
            if (isFriend) {
                tripList = tripsBy(user)
            }
            return tripList
        } else {
            throw UserNotLoggedInException()
        }
    }

    protected fun tripsBy(user: User) = TripDAO.findTripsByUser(user)

    protected open fun getLoggetInUser() = UserSession.instance.loggedUser

}
