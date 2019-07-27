import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class HelloOtus {
    public static void main(String[] args) {

        ImmutableMap<Integer, String> map = ImmutableMap.of(1, "one", 2, "two", 3, "three");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("entry = " + entry);
        }


    }
}
