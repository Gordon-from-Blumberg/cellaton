package com.gordonfromblumberg.auth.core.entity;

/**
 * Copyright (c) 2019 Gordon from Blumberg. All Rights Reserved.
 * <p>
 * Project: Cellaton
 *
 * @author: Aleksandr Ivko
 * Created: 19.11.19
 */

import com.gordonfromblumberg.auth.core.converters.UUIDConverter;
import com.gordonfromblumberg.common.entity.AbstractEntity;

import javax.persistence.*;
import java.util.UUID;

@Entity
//@org.eclipse.persistence.annotations.Converter(name = UUIDConverter.NAME, converterClass = UUIDConverter.class)
@Table(name = "SESSION", indexes = @Index(name = "SESSION_ID_IDX", columnList = "SESSION_ID", unique = true))
public class Session extends AbstractEntity {
    private static final long serialVersionUID = -1772771285552926052L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    protected User user;

//    @org.eclipse.persistence.annotations.Convert(UUIDConverter.NAME)
    @Basic
    @Column(name = "SESSION_ID", unique = true, nullable = false, columnDefinition = "uuid")
    protected UUID sessionId;

    @Basic
    @Column(name = "ACTIVE", nullable = false)
    protected Boolean active;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}