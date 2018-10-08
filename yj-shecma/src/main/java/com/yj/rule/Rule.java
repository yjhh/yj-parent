package com.yj.rule;

import com.yj.service.ServiceInfo;
import java.util.List;

public interface Rule {

  ServiceInfo getService(List<ServiceInfo> infos);

  String getName();
}
