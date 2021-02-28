package com.programacho.dgs.framework.gym;

import lombok.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository {

    private final List<Member> members = new ArrayList<>();

    public MemberRepository() {
        members.add(new Member(1, "さくらみこ", null, "0th"));
        members.add(new Member(2, "兎田ぺこら", null, "3rd"));
        members.add(new Member(3, "潤羽るしあ", null, "3rd"));
        members.add(new Member(4, "不知火フレア", null, "3rd"));
        members.add(new Member(5, "白銀ノエル", null, "3rd"));
        members.add(new Member(6, "宝鐘マリン", null, "3rd"));
    }

    public Member findById(final Integer id) {
        return members.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public List<Member> findAll() {
        return members;
    }

    public Member updateAge(final Integer id, final Integer age) {
        final Member member = findById(id);
        members.remove(member);

        final Member agedMember = new Member(member.getId(), member.getName(), age, member.getGeneration());
        members.add(agedMember);

        return agedMember;
    }

    @Value
    public static class Member {
        Integer id;
        String name;
        Integer age;
        String generation;
    }
}
