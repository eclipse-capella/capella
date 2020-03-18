/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.common.context;

import java.util.HashMap;

import org.polarsys.capella.core.tiger.ITransfo;

public class IContext extends HashMap<String, Object> {

  private static final String CONTEXT = "CONTEXT";

  ITransfo _transfo = null;

  public IContext(ITransfo transfo) {
    _transfo = transfo;
  }
  
  public IContext() {
    // Nothing here
  }

  public <T> T get(Class<T> clazz) {
    return (T)get(clazz.getSimpleName());
  }
  
  public ITransfo getTransfo() {
    return _transfo;
  }

  @Override
  public boolean containsKey(Object key_p) {
    return getTransfo().containsKey(key_p);
  }

  public Object get(String key) {
    ITransfo transfo = getTransfo();
    if (transfo == null) {
      return super.get(key);
    }
    return transfo.get(key);
  }

  @Override
  public Object put(String key, Object value) {
    ITransfo transfo = getTransfo();
    if (transfo == null) {
      return super.put(key, value);
    }
    return transfo.put(key, value);
  }

  public static IContext getContext(ITransfo transfo) {
    if (!transfo.containsKey(CONTEXT)) {
      transfo.put(CONTEXT, new IContext(transfo));
    }
    return (IContext) transfo.get(CONTEXT);
  }
}
