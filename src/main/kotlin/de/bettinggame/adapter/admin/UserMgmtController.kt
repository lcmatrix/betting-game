package de.bettinggame.adapter.admin

import de.bettinggame.adapter.AbstractController
import de.bettinggame.application.EditUserCommand
import de.bettinggame.application.UserService
import de.bettinggame.application.UserTo
import de.bettinggame.domain.UserRepository
import de.bettinggame.domain.UserRole
import de.bettinggame.domain.UserStatus
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.validation.Valid
import kotlin.IllegalArgumentException

/**
 * Controller for user management available only for administration users.
 */
@Controller
class UserMgmtController(
        private val userService: UserService,
        private val userRepository: UserRepository
) : AbstractController {

    /**
     * List all users.
     */
    @GetMapping("/admin/user")
    fun getAllUsers(@ModelAttribute("confirm") messageKey: String?): ModelAndView {
        val mav = ModelAndView("admin/user/user-list")
        val users = userRepository.findAll().map(::UserTo)
        mav.addObject("allUsers", users)
        if (StringUtils.isNotBlank(messageKey)) {
            mav.addObject("confirmMessage", "admin.user.action.$messageKey.confirm")
        }
        return mav
    }
    /**
     * Edit a specific user.
     *
     * @param userId username
     */
    @GetMapping("/admin/user/{userId}/edit")
    fun editUser(@PathVariable userId: String): ModelAndView {
        val mav = ModelAndView("admin/user/user-edit")
        userRepository.findByUsername(userId)?.also {
            val command = EditUserCommand(it)
            mav.addObject(command)
            mav.addObject("userkey", it.username)
        }
        return mav
    }

    @PostMapping("/admin/user/{userId}/edit")
    fun saveUserChanges(
            @PathVariable userId: String,
            @Valid command: EditUserCommand,
            result: BindingResult,
            model: Model,
            redirectAttributes: RedirectAttributes
    ): String {
        if (result.hasErrors()) {
            model.addAttribute("userkey", userId)
            return "admin/user/user-edit"
        }
        userService.updateUser(userId, command)
        redirectAttributes.addFlashAttribute("confirm", "edit")
        return "redirect:/admin/user"
    }

    @GetMapping("/admin/user/{userId}/{action}")
    fun openUserAction(@PathVariable userId: String, @PathVariable action: String, model: Model): String {
        model.addAttribute("action", action)
        model.addAttribute("userkey", userId)
        when(action) {
            "lock" -> model.addAttribute("actionMessage", "admin.user.action.lock.message")
            "unlock" -> model.addAttribute("actionMessage", "admin.user.action.lock.message")
            "activate" -> model.addAttribute("actionMessage", "admin.user.action.activate.message")
            else -> throw IllegalArgumentException("action not found")
        }
        return "admin/user/user-action"
    }

    @PostMapping("/admin/user/{userId}/lock")
    fun doLock(@PathVariable userId: String, redirectAttributes: RedirectAttributes): String {
        userService.lockUser(userId)
        redirectAttributes.addFlashAttribute("confirm", "lock")
        return "redirect:/admin/user"
    }

    @PostMapping("/admin/user/{userId}/unlock")
    fun doUnlock(@PathVariable userId: String, redirectAttributes: RedirectAttributes): String {
        userService.unlockUser(userId)
        redirectAttributes.addFlashAttribute("confirm", "unlock")
        return "redirect:/admin/user"
    }

    @PostMapping("/admin/user/{userId}/activate")
    fun doActiveUser(@PathVariable userId: String, redirectAttributes: RedirectAttributes): String {
        userService.activateUser(userId)
        redirectAttributes.addFlashAttribute("confirm", "activate")
        return "redirect:/admin/user"
    }

    @ModelAttribute("userroles")
    fun userroles(): Array<UserRole> {
        return UserRole.values()
    }

    @ModelAttribute("userstatus")
    fun userstatus(): Array<UserStatus> {
        return UserStatus.values()
    }
}