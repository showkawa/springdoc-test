package com.brian.demo.service.mapper;

import java.util.UUID;

import com.brian.demo.domain.Entry;
import com.brian.demo.service.dto.EntryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Entry} and its DTO {@link EntryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EntryMapper extends EntityMapper<EntryDTO, Entry> {



    default Entry fromId(UUID id) {
        if (id == null) {
            return null;
        }
        Entry entry = new Entry();
        entry.setId(id);
        return entry;
    }
}
