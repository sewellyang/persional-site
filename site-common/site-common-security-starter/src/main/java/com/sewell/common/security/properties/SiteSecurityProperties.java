package com.sewell.common.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "site.security")
@Component
@Data
public class SiteSecurityProperties {
    private boolean enable = true;

    private List<String> ignoreUrls = new ArrayList<>();
}
