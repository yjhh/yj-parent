package com.yj.xmlParse;

import com.yj.rule.Rule;
import com.yj.rule.RuleFactory;
import org.springframework.util.Assert;

public class ParseLoadBalance implements ParseFunc<Rule> {

  private RuleFactory ruleFactory;

  public  ParseLoadBalance() {
    this.ruleFactory = new RuleFactory();
  }

  @Override
  public Rule parse(String value, Class<Rule> aClass) {
    Assert.notNull(value,"路由规则不可为空");
    return ruleFactory.creta(value);
  }
}
