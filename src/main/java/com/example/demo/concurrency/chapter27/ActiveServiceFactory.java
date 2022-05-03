package com.example.demo.concurrency.chapter27;

import com.example.demo.concurrency.chapter19.Future;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ActiveServiceFactory {
    private final static ActiveMessageQueue queue = new ActiveMessageQueue();

    public static <T> T active(T instance){
        Object proxy = Proxy.newProxyInstance(instance.getClass().getClassLoader(), instance.getClass().getInterfaces(), new ActiveInvocationHandler<>(instance));
        return (T)proxy;
    }

    private static class ActiveInvocationHandler<T> implements InvocationHandler{
        private final T instance;

        public ActiveInvocationHandler(T instance) {
            this.instance = instance;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(ActiveMethod.class)){
                this.checkMethod(method);
                ActiveMessage.Builder builder = new ActiveMessage.Builder();
                builder.useMethod(method).withObjects(args).forService(instance);
                Object result = null;
                if (isReturnFutureType(method)){
                    result = new ActiveFuture<>();
                    builder.returnFuture((ActiveFuture) result);
                }
                queue.offer(builder.build());
                return result;
            } else {
                return method.invoke(instance, args);
            }
        }

        private void checkMethod(Method method) throws IllegalActiveMethod {
            if (!isReturnFutureType(method) && !isReturnVoidType(method)){
                throw new IllegalActiveMethod("the method [" + method.getName() + " return type must be void/Future.");
            }
        }

        private boolean isReturnFutureType(Method method){
            return method.getReturnType().isAssignableFrom(Future.class);
        }

        private boolean isReturnVoidType(Method method){
            return method.getReturnType().equals(Void.TYPE);
        }
    }
}
