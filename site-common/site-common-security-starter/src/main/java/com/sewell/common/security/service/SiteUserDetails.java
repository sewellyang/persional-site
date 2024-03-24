package com.sewell.common.security.service;

import com.sewell.common.security.Dto.SiteUserDto;
import com.sewell.common.security.domain.SiteUser;
import com.sewell.common.security.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


/**
 * @author sewell
 * @date 2024/03/23
 */
public class SiteUserDetails implements UserDetailsService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * @param username
     * @return {@link UserDetails}
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String queryUser = "select " +
                " user_id,user_name,user_pwd,user_nick_name,user_status,user_sex,update_time,update_user,create_time,create_user,del_flag" +
                "        from site_user" +
                "        where user_id = ?";

        List<SiteUser> siteUserList = jdbcTemplate.query(queryUser, new Object[]{username}, new BeanPropertyRowMapper<>(SiteUser.class));
        siteUserList.forEach(System.out::println);

        if (siteUserList == null || siteUserList.size() == 0) {
            throw new UsernameNotFoundException("用户不存在");
        }

        SiteUserDto siteUserDto = UserMapper.INSTANCE.userToDto(siteUserList.get(0));
        System.out.println(siteUserDto.toString());
        return siteUserDto;
    }



}
