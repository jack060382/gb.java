package func;

public interface FuncFoo {

    void fooOne();

    default void fooDefault() {
        System.out.println("FooFunc");
    }

}
