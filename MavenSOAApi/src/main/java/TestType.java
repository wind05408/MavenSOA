import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * MavenSOA
 *
 * @author dk
 * @date 2016/3/8
 */
public enum TestType {
    DISCANCEL(1,"未撤销", "未撤销", ""),
    CANCEL(0,"已撤销", "已撤销", ""),
    NULL(-1, "", "", "");

    private int id;
    private String name;
    private String desc;
    private String serialCode;

    TestType(int id, String name, String desc, String serialCode) {
        this.id = id;
        this.name = name;
        this.serialCode = serialCode;
        this.desc = desc;
    }

    public static List<TestType> getAllEnum() {
        List<TestType> list = new LinkedList<TestType>();
        for(TestType instance : values()) {
            if(!instance.isNull()) {
                list.add(instance);
            }
        }
        return list;
    }



    public int intValue() {
        return id;
    }

    public TestType getNull() {
        return NULL;
    }

    public boolean isNull() {
        return NULL == this;
    }

    public String getName() {
        return name;
    }

}
