package designpattern.chapter03;

public class OMCAgent extends Thread{
    @Override
    public void run() {
        boolean isTableModificationMsg = false;
        String updateTableName = null;
        while (true){
            if (isTableModificationMsg){
                if ("MMSCInfo".equals(updateTableName)){
                    MMSCRouter.setInstance(new MMSCRouter());
                }
            }
        }
    }
}
