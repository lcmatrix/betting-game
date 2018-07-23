package de.bettinggame.application.admin;

import de.bettinggame.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a user for the GUI.
 */
public class UserTo {
    private String username;
    private String email;
    private String firstname;
    private String surname;
    private String status;
    private String role;
    private List<UserAction> actions;

    public UserTo(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstname = user.getFirstname();
        this.surname = user.getSurname();
        this.status = user.getStatus().name().toLowerCase();
        this.role = user.getRole().name().toLowerCase();
        initActions(user);
    }

    private void initActions(User user) {
        actions = new ArrayList<>();
        actions.add(new UserAction(AdminUserLink.EDIT, user));
        switch (user.getStatus()) {
            case PENDING:
                actions.add(new UserAction(AdminUserLink.ACTIVATE, user));
                break;
            case LOCKED:
                actions.add(new UserAction(AdminUserLink.UNLOCK, user));
                break;
            case ACTIVE:
                actions.add(new UserAction(AdminUserLink.LOCK, user));
                break;
            default:
        }
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getStatus() {
        return status;
    }

    public String getRole() {
        return role;
    }

    public List<UserAction> getActions() {
        return actions;
    }

    /**
     * Action which can be "executed" on a user.
     */
    private class UserAction {
        private String name;
        private String link;
        private String icon;

        private UserAction(AdminUserLink link, User user) {
            this.name = link.name;
            this.link = String.format(link.urlTemplate, user.getUsername());
            this.icon = link.icon;
        }

        public String getName() {
            return name;
        }

        public String getLink() {
            return link;
        }

        public String getIcon() {
            return icon;
        }
    }

    /**
     * Enumeration of link templates the admin can call per user. Concrete links/actions depends on the individual user
     * and are defined by instances of {@link UserAction}.
     */
    private enum AdminUserLink {
        EDIT("edit", "/admin/user/%1$s/edit", "/static/img/glyphicons/glyphicons-151-edit.png"),
        ACTIVATE("activate", "/admin/user/%1$s/activate", "/static/img/glyphicons/glyphicons-153-check.png"),
        LOCK("lock", "/admin/user/%1$s/lock", "/static/img/glyphicons/glyphicons-204-lock.png"),
        UNLOCK("unlock", "/admin/user/%1$s/unlock", "/static/img/glyphicons/glyphicons-205-unlock.png");

        private String name;
        private String urlTemplate;
        private String icon;

        AdminUserLink(String name, String urlTemplate, String icon) {
            this.name = name;
            this.urlTemplate = urlTemplate;
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public String getUrlTemplate() {
            return urlTemplate;
        }

        public String getIcon() {
            return icon;
        }
    }
}
