package hello.servlet.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    //동시성 문제가 고려되어 있지 않다. 실무에서는 ConcurrentHashMap, AtomicLong 사용을 고려해야 한다.
    private Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    //싱글톤 방식을 위함.
    //스프링을 사용하면 싱글톤을 보장해주기 때문에 싱글톤 방식을 사용할 필요 없다.
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {};

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }
    public List<Member> findAll() {
        //store 자체를 반환하지 않고, Array를 통해 값을 반환. Store의 변경을 방지한다.
        //하지만, store를 직접 조작한다면 변경될 수밖에 없다.
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
