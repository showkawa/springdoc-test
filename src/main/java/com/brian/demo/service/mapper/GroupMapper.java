package com.brian.demo.service.mapper;



import java.util.UUID;

import com.brian.demo.domain.Group;
import com.brian.demo.service.dto.GroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Group} and its DTO {@link GroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GroupMapper extends EntityMapper<GroupDTO, Group> {
}
