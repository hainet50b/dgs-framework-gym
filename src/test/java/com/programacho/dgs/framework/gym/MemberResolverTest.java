package com.programacho.dgs.framework.gym;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {DgsAutoConfiguration.class, MemberResolver.class})
class MemberResolverTest {

    @Autowired
    DgsQueryExecutor executor;

    @MockBean
    MemberRepository repository;

    @BeforeEach
    void mock() {
        when(repository.findById(anyInt())).thenReturn(new MemberRepository.Member(
                1,
                "Super Nenechi",
                null,
                "5th"
        ));
    }

    @Test
    void memberTest() {
        final String memberName = executor.executeAndExtractJsonPath(
                "{ member(id: 1) { id name age generation } }",
                "data.member.name"
        );

        assertEquals("Super Nenechi", memberName);
    }
}
