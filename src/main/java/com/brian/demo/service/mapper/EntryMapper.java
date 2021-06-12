package com.brian.demo.service.mapper;


import com.brian.demo.domain.Entry;
import com.brian.demo.service.dto.EntryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Entry} and its DTO {@link EntryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EntryMapper extends EntityMapper<EntryDTO, Entry> {
}
