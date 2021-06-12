package com.brian.demo.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;


import java.io.Serializable;
import java.util.Map;

/**
 * A Entry.
 */
@Data
@Entity
@Table(name = "t_entry")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Entry extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 50)
    @Column(name = "group_name", length = 50, nullable = false, unique = true)
    private String groupName;

    @NotNull
    @Size(max = 50)
    @Column(name = "entry_name", length = 50, nullable = false)
    private String entryName;

    @Column(name = "entry_content",columnDefinition = "jsonb")
    @Type(type="jsonb")
    private Map<String,Object> entryContent;

}
