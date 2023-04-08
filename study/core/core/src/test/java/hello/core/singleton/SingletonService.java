package hello.core.singleton;

public class SingletonService {

    //자기 자신을 private static으로 갖고 있는다. 이렇게 하면 클래스 레벨에 올라가기 때문에 단 하나만 존재
    private static final SingletonService instance = new SingletonService();

    private SingletonService() {
        
    }
        
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
    
    public static SingletonService getInstance() {
        return instance;
    }
}
