package lucas.common.util;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lushengkao vip8
 * 2018/11/5 17:25
 */
public class ObjectLock extends ReentrantLock implements Comparable<ObjectLock> {

    private Object object;

    private Object getObject() {
        return object;
    }

    ObjectLock(Object object, boolean fair) {
        super(fair);
        this.object = object;
    }

    public int compareTo(ObjectLock o) {
        int v1 = System.identityHashCode(object);
        int v2 = System.identityHashCode(o.getObject());
        if (v1 == v2) {
            return this.getObject().getClass().getName().compareTo(o.getObject().getClass().getName());
        }else {
            return Integer.compare(v1,v2);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObjectLock)) return false;
        ObjectLock that = (ObjectLock) o;
        return Objects.equals(getObject(), that.getObject());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getObject());
    }
}
