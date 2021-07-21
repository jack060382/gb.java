public class User {

    private Integer id;
    private String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compare(User other) {
        return other.hashCode() - this.hashCode();
    }

}
