package com.yj.rule;

import com.yj.service.ServiceInfo;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PollingRule implements Rule {

  private AtomicInteger atomicInteger;

  public PollingRule(){
    atomicInteger = new AtomicInteger(0);
  }

  @Override
  public ServiceInfo getService(List<ServiceInfo> infos) {

    int current;
    int next;
    do {
      current = atomicInteger.get();
      next = (current+1)%infos.size();
    }while(!(atomicInteger.compareAndSet(current,next)));

    return infos.get(next);
  }

  @Override
  public String getName() {
    return "poll";
  }

}
