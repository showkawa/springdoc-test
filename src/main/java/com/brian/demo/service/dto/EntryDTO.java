package com.brian.demo.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link com.szhuangl.app.domain.Entry} entity.
 */
public class EntryDTO implements Serializable {
    
    private UUID id;

    @NotNull
    @Size(max = 50)
    private String groupName;

    @NotNull
    @Size(max = 50)
    private String entryName;

    private String entryContent;

    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public String getEntryContent() {
        return entryContent;
    }

    public void setEntryContent(String entryContent) {
        this.entryContent = entryContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntryDTO)) {
            return false;
        }

        return id != null && id.equals(((EntryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntryDTO{" +
            "id=" + getId() +
            ", groupName='" + getGroupName() + "'" +
            ", entryName='" + getEntryName() + "'" +
            ", entryContent='" + getEntryContent() + "'" +
            "}";
    }
}
