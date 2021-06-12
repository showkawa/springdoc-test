package com.brian.demo.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.brian.demo.domain.Group;
import com.brian.demo.repository.GroupRepository;
import com.brian.demo.service.dto.GroupDTO;
import com.brian.demo.service.mapper.GroupMapper;
import com.brian.demo.tool.RedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Service Implementation for managing {@link Group}.
 */
@Service
@Transactional
public class GroupService {

    private final Logger log = LoggerFactory.getLogger(GroupService.class);

    private final GroupRepository groupRepository;

    private final GroupMapper groupMapper;

    private final RedisUtil redisUtil;

    private final static String KEY_PREFIX = "TEST:GROUP:";

    private ObjectMapper objectMapper = new ObjectMapper();



    public GroupService(GroupRepository groupRepository, GroupMapper groupMapper,RedisUtil redisUtil) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.redisUtil = redisUtil;
    }

    /**
     * Save a group.
     *
     * @param groupDTO the entity to save.
     * @return the persisted entity.
     */
    public GroupDTO save(GroupDTO groupDTO) {
        log.debug("Request to save Group : {}", groupDTO);
        Group group = groupMapper.toEntity(groupDTO);
        group = groupRepository.save(group);
        GroupDTO g = groupMapper.toDto(group);
        redisUtil.set(KEY_PREFIX + group.getId().toString(), g.toString());
        return g;
    }

    /**
     * Get all the groups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Groups");
        Set<String>  keys = redisUtil.keys(KEY_PREFIX+ "*")  
        if(keys.isEmpty()){
            return groupRepository.findAll(pageable)
            .map(groupMapper::toDto);
        }
        List<Object> groups = redisUtil.multiGet(keys);
        List<GroupDTO> groupDTOs = groups.stream()
              .map(g -> groupMapper.toDto((Group) g))
              .sorted(Comparator.comparing(Group::getGroupName))
              .skip(pageable.getPageNumber() * pageable.getPageSize())
              .limit(pageable.getPageSize)
              .collect(Collectors.toList);

        return new PageImpl(groupDTOs, pageable, (long) keys.size());      
    }


    /**
     * Get one group by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GroupDTO> findOne(UUID id) {
        log.debug("Request to get Group : {}", id);
        Group groupStr =  (Group) redisUtil.get(KEY_PREFIX + id.toString());
        if(StringUtils.isEmpty(groupStr)){
            return groupRepository.findById(id)
            .map(groupMapper::toDto);
        }
        return Optional.of(groupMapper.toDto(groupStr));
    }

    /**
     * Delete the group by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete Group : {}", id);
        groupRepository.deleteById(id);
        redisUtil.delete(KEY_PREFIX + id.toString());
    }
}
