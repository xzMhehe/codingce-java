package cn.com.codingce.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 该类不能被@ComponentScan扫描到
 * @author xzMa
 */
public class CodingCeRandomRule extends AbstractLoadBalancerRule {

    //自定义 每个服务, 访问5次, 换下一个服务(3个)

    //total = 0 默认=0 如果=5 我们指向下一个服务点

    private int total = 0;  //被调用的次数
    private int currentIndex = 0;   //当前是谁在提供服务


    public CodingCeRandomRule() {}

//    @SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                //线程中断
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers(); //获得活着的服务
                List<Server> allList = lb.getAllServers();  //获得全部服务
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }


                //int index = this.chooseRandomInt(serverCount);  //生成区间随机数
                //server = (Server)upList.get(index); //从活着的服务, 随机获取一个

                //===================================================================
                if (total < 5) {
                    server = upList.get(currentIndex);
                    total++;
                } else {
                    total = 0;
                    currentIndex++;
                    //判断当前数量是否大于活着的数量
                    if(currentIndex > upList.size()) {
                        currentIndex = 0;
                    }
                    server = upList.get(currentIndex);  //从活着的服务中, 获取指定指定的服务进行操作
                }

                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }
                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    @Override
    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
