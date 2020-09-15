package de.bettinggame.application

import de.bettinggame.domain.UserRepository
import de.bettinggame.domain.UserRole
import de.bettinggame.domain.UserStatus
import de.bettinggame.domain.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*
import kotlin.test.Test
import kotlin.test.fail

@RunWith(MockitoJUnitRunner::class)
class UserServiceTest {
    @Mock
    lateinit var userRepository: UserRepository

    lateinit var userService: UserService

    @Before
    fun setUp() {
        userService = UserService(userRepository, BCryptPasswordEncoder())
    }

    @Test
    fun testLockUser() {
        val user = User(UUID.randomUUID().toString().toLowerCase(), "user", "user@example.org", UserRole.USER, UserStatus.ACTIVE)
        given(userRepository.findByUsername(user.username)).willReturn(user)
        given(userRepository.findByUsername("notexist")).willReturn(null)

        userService.lockUser("user")
        assertThat(user.status).isEqualTo(UserStatus.LOCKED)

        try {
            userService.lockUser("notexist")
            fail("User not exists")
        } catch (e: Exception) {
            //Assert.assertThat(e, CoreMatchers.isA(EntityNotFoundException.class));
        }
    }

    @Test
    fun testUnlockUser() {
        val user = User(UUID.randomUUID().toString().toLowerCase(), "user", "user@example.org", UserRole.USER, UserStatus.LOCKED)
        given(userRepository.findByUsername(user.username)).willReturn(user)
        given(userRepository.findByUsername("notexist")).willReturn(null)

        userService.unlockUser("user")
        assertThat(user.status).isEqualTo(UserStatus.ACTIVE)

        try {
            userService.unlockUser("notexist")
            fail("User not exists")
        } catch (e: Exception) {
            //Assert.assertThat(e, CoreMatchers.isA(EntityNotFoundException.class));
        }
    }
}