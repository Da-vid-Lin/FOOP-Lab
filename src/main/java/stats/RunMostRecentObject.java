package stats;

public class RunMostRecentObject {
    public static void main(String[] args) {
        MostRecentObject<String> mro = new MostRecentObject<>();
        mro.add("Hello");
        System.out.println(mro.getMostRecentObject());
        mro.add("World");
        System.out.println(mro.getMostRecentObject());
//        Integer x = mro.getMostRecentObject();
    }
}
