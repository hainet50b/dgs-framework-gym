package com.programacho.dgs.framework.gym;

import com.netflix.graphql.mocking.MockProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Map;

@Profile("mock")
@Component
public class MemberMockProvider implements MockProvider {

    @NotNull
    @Override
    public Map<String, Object> provide() {
        return Map.of(
                "member.id", 1,
                "member.name", "Big God Mio",
                "member.generation", "GAMERS"
        );
    }
}
