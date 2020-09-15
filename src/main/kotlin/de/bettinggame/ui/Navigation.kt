package de.bettinggame.ui

enum class Navigation(val messageKey: String, val url:String, val restriction: Restriction) {
    LOGIN("navigation.item.login", "/login", Restriction.ALL),
    REGISTER("navigation.item.registration", "/registration", Restriction.ALL),
    GROUPS("navigation.item.groups", "/groups", Restriction.ALL),
    MATCH("navigation.item.games", "/game", Restriction.ALL),
    USER_MANAGEMENT("navigation.item.admin.usermanagement", "/admin/user", Restriction.ADMIN);

    companion object {
        fun getNonRestrictedNavigation(): List<Navigation> {
            return values().filter { nav -> nav.restriction == Restriction.ALL };
        }
        fun getUserRestrictedNavigation(): List<Navigation> {
            return values().filter { nav -> nav.restriction == Restriction.USER }
        }
        fun getAdminRestrictedNavigation(): List<Navigation> {
            return values().filter { nav -> nav.restriction == Restriction.ADMIN }
        }
    }
}
enum class Restriction {
    ALL, USER, ADMIN
}
