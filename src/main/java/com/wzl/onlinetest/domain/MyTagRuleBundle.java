package com.wzl.onlinetest.domain;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

/**
 * 自定义一个标签 myTag
 */

public class MyTagRuleBundle implements TagRuleBundle {
    @Override
    public void install(State state, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        state.addRule("myTag", new ExportTagToContentRule(siteMeshContext,contentProperty.getChild("myTag"),false));
    }

    @Override
    public void cleanUp(State state, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        if (!((ContentProperty)contentProperty.getChild("myTag")).hasValue()) {
            ((ContentProperty)contentProperty.getChild("myTag")).setValue(contentProperty.getValue());
        }
    }
}
