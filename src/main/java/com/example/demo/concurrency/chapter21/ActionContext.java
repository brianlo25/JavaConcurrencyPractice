package com.example.demo.concurrency.chapter21;

public class ActionContext {
    private static final ThreadLocal<Context> context = ThreadLocal.withInitial(Context::new);

    public static Context get(){
        return context.get();
    }

    static class Context{
        private Integer configuration;

        private Integer otherResource;

        public Integer getConfiguration() {
            return configuration;
        }

        public void setConfiguration(Integer configuration) {
            this.configuration = configuration;
        }

        public Integer getOtherResource() {
            return otherResource;
        }

        public void setOtherResource(Integer otherResource) {
            this.otherResource = otherResource;
        }
    }
}
