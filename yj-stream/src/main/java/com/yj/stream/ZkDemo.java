package com.yj.stream;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@RestController
public class ZkDemo {

    @GetMapping("/config/set")
    public void setConfig() throws IOException, InterruptedException, KeeperException {

        CountDownLatch latch = new CountDownLatch(1);

        ZooKeeper zk = new ZooKeeper("127.0.0.1:2181",60,e -> {
            if(e.getState().equals(Watcher.Event.KeeperState.SyncConnected)){
                latch.countDown();
            }
        });
        latch.await();
        if(zk.exists("/saas",false) == null){
            zk.create("/saas",new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        }

        zk.create("/saas/config","123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);

    }
    @GetMapping("/config/get")
    public void getConfig() {

        ZkClient client = new ZkClient("127.0.0.1");
        client.subscribeDataChanges("/saas/config", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println(dataPath);
                System.out.println(data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println(dataPath);
            }
        });
        String object = client.readData("/saas/config");
        System.out.println(object);
    }

}
