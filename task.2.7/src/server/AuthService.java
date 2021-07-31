package server;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AuthService {

    private static final List<Entry> entries;

    static {
        entries = List.of(
                new Entry("name1", "nick1", "pass"),
                new Entry("name2", "nick2", "pass"),
                new Entry("name3", "nick3", "pass")
        );
    }

    public Optional<Entry> findUserByLoginAndPassword(String login, String password) {
        /**
        for (AuthService.Entry entry : entries) {
            if (entry.login.equals(login) && entry.password.equals(password)) {
                return Optional.of(entry);
            }
        }

        return Optional.empty();
         */

        return entries.stream()
                .filter(entry -> entry.login.equals(login) && entry.password.equals(password))
                .findFirst();
    }

    static class Entry {
        String name;
        String login;
        String password;

        Entry(String name, String login, String password) {
            this.name = name;
            this.login = login;
            this.password = password;
        }

        String getName() {
            return name;
        }

        String getLogin() {
            return login;
        }

        String getPassword() {
            return password;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return Objects.equals(name, entry.name) && Objects.equals(login, entry.login) && Objects.equals(password, entry.password);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, login, password);
        }
    }

}
