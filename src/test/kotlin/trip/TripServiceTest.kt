package org.tripservicekata.trip

import org.craftedsw.tripservicekata.user.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockitoAnnotations
import org.tripservicekata.exception.UserNotLoggedInException
import org.tripservicekata.trip.TripServiceTest.Constants.Companion.ANOTHER_USER
import org.tripservicekata.trip.TripServiceTest.Constants.Companion.GUEST
import org.tripservicekata.trip.TripServiceTest.Constants.Companion.REGISTERED_USER
import org.tripservicekata.trip.TripServiceTest.Constants.Companion.TO_BARCELONA

private var loggedInUser: User? = null

class TripServiceTest {

    private  lateinit var tripService: TestableTripService

    @BeforeEach
    internal fun setUp() {
        MockitoAnnotations.initMocks(this)
        tripService = TestableTripService()
    }

    @Test
    fun should_throw_user_not_logged_exception_when_user_is_not_logged_in() {
        val tripService = TestableTripService()
        loggedInUser = GUEST
        Assertions.assertThrows(UserNotLoggedInException::class.java) {
            tripService.getTripsByUser(User())
        }
    }

    @Test
    fun should_not_return_trips_when_users_are_not_friends() {
        loggedInUser = REGISTERED_USER

        val friend = User()
        friend.addFriend(ANOTHER_USER)

        val friendTrips = tripService.getTripsByUser(friend)

        Assertions.assertEquals(friendTrips.size, 0)
    }

    @Test
    fun should_return_friend_trips_when_users_are_friends() {
        loggedInUser = REGISTERED_USER

        val friend = User()
        friend.addFriend(ANOTHER_USER)
        friend.addFriend(loggedInUser!!)
        friend.addTrip(TO_BARCELONA)

        val friendTrips = tripService.getTripsByUser(friend)

        Assertions.assertEquals(friendTrips.size, 1)
    }

    private class TestableTripService : TripService() {
        override fun getLoggetInUser(): User? {
            return loggedInUser
        }

        override fun tripsBy(user: User): List<Trip> {
            return user.trips
        }
    }

    class Constants {
        companion object {
            val GUEST = null
            val UNUSED_USER = null
            val REGISTERED_USER = User()
            val ANOTHER_USER = User()
            val TO_BARCELONA = Trip()
        }
    }
}
