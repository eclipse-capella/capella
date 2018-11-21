/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package ms.design;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.emf.common.notify.impl.AdapterImpl;

public class CSConfigurationStyle extends AdapterImpl {
  private Collection<String> style = new HashSet<String>();
  public CSConfigurationStyle clear() {
    style.clear();
    return this;
  }
  public CSConfigurationStyle addClass(String clazz) {
    style.add(clazz);
    return this;
  }
  public boolean hasClass(String clazz) {
    return style.contains(clazz);
  }
  public boolean isAdapterForType(Object type) {
    return type == CSConfigurationStyle.class;
  }
  public Collection<String> getStyle(){
    return Collections.unmodifiableCollection(style);
  }
  
  public String toString() {
    return style.toString();
  }
  
}