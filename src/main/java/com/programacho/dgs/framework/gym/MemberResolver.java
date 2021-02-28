package com.programacho.dgs.framework.gym;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;

import java.util.List;

@DgsComponent
public class MemberResolver {

    private final MemberRepository repository;

    public MemberResolver(final MemberRepository repository) {
        this.repository = repository;
    }

    @DgsData(parentType = "Query", field = "member")
    public MemberRepository.Member member(final Integer id) {
        return repository.findById(id);
    }

    @DgsData(parentType = "Query", field = "members")
    public List<MemberRepository.Member> members() {
        return repository.findAll();
    }

    @DgsData(parentType = "Mutation", field = "updateAge")
    public MemberRepository.Member updateAge(final Integer id, final Integer age) {
        return repository.updateAge(id, age);
    }
}
