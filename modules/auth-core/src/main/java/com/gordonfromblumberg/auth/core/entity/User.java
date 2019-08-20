package com.gordonfromblumberg.auth.core.entity;

/**
 * Project: Authentication
 *
 * @author: Aleksandr Ivko
 * Created: 12.07.19
 * <p>
 * $Id$
 */

import com.gordonfromblumberg.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User extends AbstractEntity {
    private static final long serialVersionUID = -1772771285332926052L;

    @Column(name = "LOGIN")
    protected String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
