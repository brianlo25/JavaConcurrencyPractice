package concurrency.chapter28;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

class Registry {
    private final ConcurrentHashMap<String, ConcurrentLinkedDeque<Subscriber>> subscribeContainer = new ConcurrentHashMap<>();

    public void bind(Object subscriber){
        List<Method> subscribeMethods = getSubscribeMethods(subscriber);
        subscribeMethods.forEach(m -> tierSubscriber(subscriber, m));
    }

    public void unbind(Object subscriber){
        subscribeContainer.forEach((key, queue) ->
                queue.forEach(s -> {
                    if (s.getSubscribeObject() == subscriber){
                        s.setDisable(true);
                    }
        }));
    }

    public ConcurrentLinkedDeque<Subscriber> scanSubscriber(final String topic){
        return subscribeContainer.get(topic);
    }

    private void tierSubscriber(Object subscriber, Method method){
        final Subscribe subscribe = method.getDeclaredAnnotation(Subscribe.class);
        String topic = subscribe.topic();
        subscribeContainer.computeIfAbsent(topic, key -> new ConcurrentLinkedDeque<>());
        subscribeContainer.get(topic).add(new Subscriber(subscriber, method));
    }

    private List<Method> getSubscribeMethods(Object subscriber){
        final List<Method> methods = new ArrayList<>();
        Class<?> temp = subscriber.getClass();
        while (temp != null){
            Method[] declareMethods = temp.getDeclaredMethods();
            Arrays.stream(declareMethods)
                    .filter(m -> m.isAnnotationPresent(Subscribe.class)
                            && m.getParameterCount() == 1 &&
                            m.getModifiers() == Modifier.PUBLIC)
                    .forEach(methods::add);
            temp = temp.getSuperclass();
        }
        return methods;
    }
}
