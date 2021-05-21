package designpattern.chapter03;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class MMSCRouter {
    private static volatile MMSCRouter instance = new MMSCRouter();

    private final Map<String, MMSCInfo> routeMap;

    public MMSCRouter(){
        this.routeMap = MMSCRouter.retrieveRouterMapFromDB();
    }

    private static Map<String, MMSCInfo> retrieveRouterMapFromDB(){
        Map<String, MMSCInfo> map = new HashMap<String, MMSCInfo>();
        return map;
    }

    public static MMSCRouter getInstance(){
        return instance;
    }

    public MMSCInfo getMMSC(String msisdnPrefix){
        return routeMap.get(msisdnPrefix);
    }

    public static void setInstance(MMSCRouter newInstance){
        instance = newInstance;
    }

    private static Map<String, MMSCInfo> deepCopy(Map<String, MMSCInfo> m){
        Map<String, MMSCInfo> result = m.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return result;
    }

    public Map<String, MMSCInfo> getRouteMap(){
        return Collections.unmodifiableMap(deepCopy(routeMap));
    }
}
