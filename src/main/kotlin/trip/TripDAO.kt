package org.tripservicekata.trip

import org.craftedsw.tripservicekata.user.User
import org.tripservicekata.exception.CollaboratorCallException

class TripDAO {

    companion object {
        @JvmStatic fun findTripsByUser(user: User): List<Trip> {
            throw CollaboratorCallException("TripDAO should not be invoked on an unit test.")
        }
    }

}
