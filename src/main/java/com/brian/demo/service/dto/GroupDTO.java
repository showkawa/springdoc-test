package com.brian.demo.service.dto;


import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

/**
 * A DTO for the {@link com.szhuangl.app.domain.Group} entity.
 */
public class GroupDTO implements Serializable {
    
    private UUID id;

    @NotNull
    @Size(max = 50)
    private String groupName;


    private Map<String,Object> groupContent;

    
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

    public Map<String,Object> getGroupContent() {
        return groupContent;
    }

    public void setGroupContent(Map<String,Object> groupContent) {
        this.groupContent = groupContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GroupDTO)) {
            return false;
        }

        return id != null && id.equals(((GroupDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GroupDTO{" +
            "id=" + getId() +
            ", groupName='" + getGroupName() + "'" +
            ", groupContent='" + getGroupContent() + "'" +
            "}";
    }
}
