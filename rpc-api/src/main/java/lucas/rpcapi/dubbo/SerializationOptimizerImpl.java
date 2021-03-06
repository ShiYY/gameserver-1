package lucas.rpcapi.dubbo;

import lucas.rpcapi.serverteam.model.TeamPlayer;
import org.apache.dubbo.common.serialize.support.SerializationOptimizer;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lushengkao vip8
 * //Kryo 序列化预加载
 * 2018/12/13 19:44
 */
public class SerializationOptimizerImpl implements SerializationOptimizer {

    @Override
    public Collection<Class> getSerializableClasses() {
        List<Class> classes = new LinkedList<>();
        classes.add(TeamPlayer.class);
        return classes;
    }
}
