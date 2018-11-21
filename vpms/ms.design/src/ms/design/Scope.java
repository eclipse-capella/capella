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
import java.util.HashMap;
import java.util.Map;

import org.eclipse.sirius.diagram.DDiagramElement;

abstract class Scope {

  private Scope parentScope;
  private final Object element;
  private Map<Object, Object> attributes = new HashMap<Object, Object>();
  
  public Scope(Scope parent, Object element) {
    this.parentScope = parent;
    this.element = element;
  }

  final Object getElement() {
    return element;
  }

  final Object getAttribuge(Object key) {
    return attributes.get(key);
  }

  final Object setAttribute(Object key, Object value) {
    return attributes.put(key, value);
  }

  final Scope getParentScope() {
    return parentScope;
  }

  abstract Collection<? extends DDiagramElement> getChildren();

  public abstract <T> T accept(ScopeVisitor<T> visitor);

}