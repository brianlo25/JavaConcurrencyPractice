package concurrency.chapter10;

public class BrokerDelegateClassLoader extends MyClassLoader{

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)){
            Class<?> klass = findClass(name);
            if (klass == null){
                if (name.startsWith("java.") || name.startsWith("javax")){
                    try {
                        klass = getSystemClassLoader().loadClass(name);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        klass = this.findClass(name);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    if (klass == null){
                        if (getParent() != null){
                            klass = getParent().loadClass(name);
                        }else {
                            klass = getSystemClassLoader().loadClass(name);
                        }
                    }
                }
            }

            if (null == klass){
                throw new ClassNotFoundException("The class " + name + " not found.");
            }

            if (resolve){
                resolveClass(klass);
            }

            return klass;
        }
    }
}
