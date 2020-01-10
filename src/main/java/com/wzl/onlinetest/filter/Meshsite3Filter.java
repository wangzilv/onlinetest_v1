package com.wzl.onlinetest.filter;

import com.wzl.onlinetest.domain.MyTagRuleBundle;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class Meshsite3Filter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "/decorator/default")//拦截规则，/decorator/default 会被转发
                .addExcludedPath("/User/login")
                .addExcludedPath("/User/register")//白名单
                .addTagRuleBundle(new MyTagRuleBundle());//自定义标签

    }
}
