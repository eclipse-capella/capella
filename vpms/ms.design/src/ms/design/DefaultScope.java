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

import org.eclipse.sirius.diagram.DDiagramElement;

class DefaultScope extends Scope {

  public DefaultScope(Scope parent, Object element) {
    super(parent, element);
  }

  @Override
  Collection<? extends DDiagramElement> getChildren() {
    return Collections.emptyList();
  }

  @Override
  public <T> T accept(ScopeVisitor<T> visitor) {
    return visitor.visitDefaultScope(this);
  }

}