package de.bettinggame.application.admin;

import de.bettinggame.domain.Identity;
import de.bettinggame.domain.user.User;
import de.bettinggame.domain.user.UserRole;
import de.bettinggame.domain.user.UserStatus;
import de.bettinggame.domain.user.UserRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

public class UserServiceTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService = new UserService(userRepository, new BCryptPasswordEncoder());

    @Test
    public void testLockUser() {
        User user = new User(Identity.buildNewIdentity(),"user", "user@example.org", UserStatus.ACTIVE, UserRole.USER);
        given(userRepository.findByUsername(user.getUsername())).willReturn(Optional.of(user));
        given(userRepository.findByUsername("notexist")).willReturn(Optional.empty());

        userService.lockUser("user");
        Assert.assertThat(user.getStatus(), CoreMatchers.is(UserStatus.LOCKED));

        try {
            userService.lockUser("notexist");
            Assert.fail("User not exists");
        } catch (Exception e) {
            //Assert.assertThat(e, CoreMatchers.isA(EntityNotFoundException.class));
        }
    }

    @Test
    public void testUnlockUser() {
        User user = new User(Identity.buildNewIdentity(),"user", "user@example.org", UserStatus.ACTIVE, UserRole.USER);
        given(userRepository.findByUsername(user.getUsername())).willReturn(Optional.of(user));
        given(userRepository.findByUsername("notexist")).willReturn(Optional.empty());

        userService.unlockUser("user");
        Assert.assertThat(user.getStatus(), CoreMatchers.is(UserStatus.ACTIVE));

        try {
            userService.unlockUser("notexist");
            Assert.fail("User not exists");
        } catch (Exception e) {
            //Assert.assertThat(e, CoreMatchers.isA(EntityNotFoundException.class));
        }
    }
}
