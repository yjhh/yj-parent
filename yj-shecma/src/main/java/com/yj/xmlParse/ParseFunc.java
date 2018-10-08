package com.yj.xmlParse;

public interface  ParseFunc<T> {

  T parse(String value,Class<T> tClass);

}
