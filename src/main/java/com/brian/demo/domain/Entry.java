package com.brian.demo.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Entry.
 */
@Entity
@Table(name = "t_entry")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Entry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "group_name", length = 50, nullable = false, unique = true)
    private String groupName;

    @NotNull
    @Size(max = 50)
    @Column(name = "entry_name", length = 50, nullable = false)
    private String entryName;

    @Column(name = "entry_content")
    private String entryContent;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public Entry groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getEntryName() {
        return entryName;
    }

    public Entry entryName(String entryName) {
        this.entryName = entryName;
        return this;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public String getEntryContent() {
        return entryContent;
    }

    public Entry entryContent(String entryContent) {
        this.entryContent = entryContent;
        return this;
    }

    public void setEntryContent(String entryContent) {
        this.entryContent = entryContent;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Entry)) {
            return false;
        }
        return id != null && id.equals(((Entry) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Entry{" +
            "id=" + getId() +
            ", groupName='" + getGroupName() + "'" +
            ", entryName='" + getEntryName() + "'" +
            ", entryContent='" + getEntryContent() + "'" +
            "}";
    }
}
