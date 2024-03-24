package com.sewell.common.security.mapper;


import com.sewell.common.security.Dto.SiteUserDto;
import com.sewell.common.security.domain.SiteUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(target = "username", source = "userNickName"),
            @Mapping(target = "password", source = "userPwd"),
    })
    SiteUserDto userToDto(SiteUser siteUser);
}
