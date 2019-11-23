package com.gordonfromblumberg.auth.core.entity;

/**
 * Project: Authentication
 *
 * @author: Aleksandr Ivko
 * Created: 12.07.19
 * <p>
 * $Id$
 */

import com.gordonfromblumberg.common.EntityConstant;
import com.gordonfromblumberg.common.entity.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER_")
public class User extends AbstractEntity {
    private static final long serialVersionUID = -1772771285332926052L;

    @Column(name = "LOGIN", nullable = false, length = EntityConstant.LOGIN_LENGTH)
    protected String login;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES")
    protected Set<Role> roles = new HashSet<>();

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return String.format("User [%s]", login);
    }
}
