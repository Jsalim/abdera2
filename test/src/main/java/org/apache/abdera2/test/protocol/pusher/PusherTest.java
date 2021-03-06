package org.apache.abdera2.test.protocol.pusher;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.abdera2.common.pusher.ChannelManager;
import org.apache.abdera2.common.pusher.Listener;
import org.apache.abdera2.common.pusher.Pusher;
import org.apache.abdera2.common.pusher.Receiver;
import org.apache.abdera2.common.pusher.SimpleChannelManager;
import org.junit.Test;

public class PusherTest {

  @Test
  public void pusherTest() throws Exception {
    final ChannelManager cm = new SimpleChannelManager();
    Executor exec = Executors.newCachedThreadPool();
    final CountDownLatch latch = new CountDownLatch(3);
    exec.execute(
      new Runnable() {
        public void run() {
          Receiver<String> r = cm.getReceiver("foo");
          r.startListening(
            new Listener<String>() {
              public void beforeItems() {}
              public void onItem(String t) {
                latch.countDown();
              }
              public void afterItems() {}
            }
          );
        }
      }
    );
    Pusher<String> pusher = cm.getPusher("foo");
    pusher.push("a");
    pusher.push("b");
    pusher.push("c");
    latch.await();
    cm.shutdown();
  }
  
}
