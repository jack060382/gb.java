package task11;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhoneBook {

    private HashMap<String, HashSet> book = new HashMap<>();

    void add(String firstName, String phoneNumber) {
        //HashSet value = book.getOrDefault(firstName, new HashSet<>());
        HashSet value = book.get(firstName);
        if (value == null) {
            value = new HashSet<>();
        }
        value.add(phoneNumber);
        book.put(firstName, value);
    }

    HashSet get(String firstName) {
        for (Map.Entry<String, HashSet> record : book.entrySet()) {
            if (record.getKey() == firstName) {
                return record.getValue();
            }
        }

        HashSet<String> empty = new HashSet<>();
        return empty;
        //return book.getOrDefault(firstName, new HashSet<>());
    }

}
