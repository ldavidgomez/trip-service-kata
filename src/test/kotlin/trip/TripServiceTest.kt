package org.tripservicekata.trip

import org.craftedsw.tripservicekata.user.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockitoAnnotations
import org.tripservicekata.exception.UserNotLoggedInException

class TripServiceTest {

    @BeforeEach
    internal fun setUp() {
        MockitoAnnotations.initMocks(this)

    }

    @Test
    fun should_throw_user_not_logged_exception_when_user_is_not_logged_in() {
        val tripService = TestableTripService()
        Assertions.assertThrows(UserNotLoggedInException::class.java) {
            tripService.getTripsByUser(User())
        }
    }

    private class TestableTripService : TripService() {
        override fun getLoggetInUser(): User? {
            return null
        }
    }
}
