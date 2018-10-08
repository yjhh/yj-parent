package com.yj.rule;

import com.yj.service.ServiceInfo;
import java.util.List;
import java.util.Random;
import org.springframework.util.CollectionUtils;

public class RandomRule implements Rule {

  @Override
  public ServiceInfo getService(List<ServiceInfo> infos) {
    assert CollectionUtils.isEmpty(infos);
    int size = infos.size();
    Random random = new Random(size);
    return infos.get(random.nextInt());
  }

  @Override
  public String getName() {
    return "random";
  }
}
