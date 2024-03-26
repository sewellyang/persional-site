package com.sewell.web.user.controller;

import com.sewell.common.core.result.R;
import com.sewell.web.user.entity.SiteUser;
import com.sewell.web.user.service.ISiteUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sewell
 * @since 2024-03-25
 */
@RestController
@RequestMapping("/user/siteUser")
public class SiteUserController {


    @Resource
    private ISiteUserService siteUserService;

    public R<List<SiteUser>>  getAllSiteUser()
    {
        return R.ok(siteUserService.list());
    }
}
