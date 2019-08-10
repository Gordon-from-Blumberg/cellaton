package com.gordonfromblumberg.auth.core.entity;

/**
 * Project: Authentication
 *
 * @author: Aleksandr Ivko
 * Created: 12.07.19
 * <p>
 * $Id$
 */

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User implements Serializable {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
