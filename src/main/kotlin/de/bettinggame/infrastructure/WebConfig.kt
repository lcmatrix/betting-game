package de.bettinggame.infrastructure

import de.bettinggame.domain.UserRepository
import de.bettinggame.domain.UserStatus
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.CacheControl
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User.withUsername
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.concurrent.TimeUnit

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS))
    }

    @Bean
    fun messageKeyPrinter(messageSource: MessageSource): MessageKeyPrinter {
        return MessageKeyPrinter(messageSource)
    }

    @Bean
    fun multilingualPrinter(): MultilingualPrinter {
        return MultilingualPrinter()
    }
}

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http?.authorizeRequests()
                ?.antMatchers("/", "/static/**", "/favicon.ico", "/register*", "/groups*")?.permitAll()
                ?.antMatchers("/admin/**")?.hasAuthority("ADMIN")
                ?.and()
                ?.formLogin()
                ?.loginPage("/login")
                ?.failureForwardUrl("/error")
                ?.successForwardUrl("/")
                ?.permitAll()
                ?.and()
                ?.logout()
                ?.logoutSuccessUrl("/")
                ?.deleteCookies("JSESSIONID")
                ?.permitAll()
    }

    override fun configure(web: WebSecurity?) {
        web?.ignoring()?.antMatchers("/static/**")
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userDetailService(userRepository: UserRepository): CustomUserDetailService {
        return CustomUserDetailService(userRepository)
    }
}

class CustomUserDetailService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(userOrEMail: String?): UserDetails {
        val user = userRepository.findByUsernameOrEmail(userOrEMail, userOrEMail)
                ?: throw UsernameNotFoundException("User $userOrEMail not found")
        return withUsername(user.identifier)
                .password(user.password)
                .disabled(user.status != UserStatus.ACTIVE)
                .accountLocked(user.status == UserStatus.LOCKED)
                .authorities(AuthorityUtils.createAuthorityList(user.role.name))
                .build()
    }
}
