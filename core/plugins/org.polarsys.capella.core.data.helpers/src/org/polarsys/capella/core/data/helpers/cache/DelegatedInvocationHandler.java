package org.polarsys.capella.core.data.helpers.cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DelegatedInvocationHandler implements InvocationHandler {
  
  InvocationHandler handler;
  
  public DelegatedInvocationHandler(InvocationHandler handler) {
    this.handler = handler;
  }
  
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    return handler.invoke(proxy, method, args);
  }

}
