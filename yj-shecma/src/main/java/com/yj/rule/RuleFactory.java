package com.yj.rule;

import java.util.Iterator;
import java.util.ServiceLoader;

public class RuleFactory {

  private ServiceLoader<Rule> loader;

  public RuleFactory(){
    loader = ServiceLoader.load(Rule.class);
  }

  public Rule creta(String rulename){
    Iterator<Rule> iterator = loader.iterator();
    while (iterator.hasNext()){
      Rule rule = iterator.next();
      if (rule.getName().equals(rulename)){
        return rule;
      }
    }
    return null;
  }
}
