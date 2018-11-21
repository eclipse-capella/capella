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

import org.eclipse.sirius.diagram.DNode;

public class DNodeScope extends AbstractDNodeScope {

  DNodeScope(Scope parent, DNode element) {
    super(parent, element);
  }

  @Override
  public <T> T accept(ScopeVisitor<T> visitor) {
    return visitor.visitDNodeScope(this);
  }
}
