package org.tripservicekata

import org.tripservicekata.exception.UserNotLoggedInException
import org.tripservicekata.trip.Trip
import org.tripservicekata.trip.TripDAO
import org.craftedsw.tripservicekata.user.User
import org.craftedsw.tripservicekata.user.UserSession
import java.util.*

class TripService_Original {

    fun getTripsByUser(user: User): List<Trip> {
        var tripList: List<Trip> = ArrayList<Trip>()
        val loggedUser: User? = UserSession.instance.loggedUser
        var isFriend: Boolean = false
        if (loggedUser != null) {
            for (friend in user.friends) {
                if (friend == loggedUser) {
                    isFriend = true
                    break
                }
            }
            if (isFriend) {
                tripList = TripDAO.findTripsByUser(user)
            }
            return tripList
        } else {
            throw UserNotLoggedInException()
        }
    }

}
