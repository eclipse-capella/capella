/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.layout.ju.layout.compare;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.test.diagram.layout.ju.layout.Bounds;
import org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.Location;
import org.polarsys.capella.test.diagram.layout.ju.layout.NodeLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.NoteLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.SessionLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.Size;

public class LayoutMatchPolicy extends DefaultMatchPolicy {
  
  @Override
  public boolean keepMatchIDs() {
    return true;
  }

  @Override
  public Object getMatchID(EObject element_p, IModelScope scope_p) {
    if (element_p == null) {
      return "null";
    }
    if (element_p instanceof SessionLayout) {
      return SessionLayout.class.getSimpleName();
    }
    if (element_p instanceof NodeLayout) {
      NodeLayout layout = (NodeLayout) element_p;
      String id = (String) getMatchID(element_p.eContainer(), scope_p);
      id += " id:" + layout.getId();
      id += " mapping:" + layout.getActualMapping();
      return id;
    }
    if (element_p instanceof EdgeLayout) {
      EdgeLayout layout = (EdgeLayout) element_p;
      String id = (String) getMatchID(element_p.eContainer(), scope_p);
      id += " id:" + layout.getId();
      id += " source:" + (String) getMatchID(layout.getSource(), scope_p);
      id += " target:" + (String) getMatchID(layout.getTarget(), scope_p);
      id += " mapping:" + layout.getActualMapping();
      return id;
    }
    if (element_p instanceof DiagramLayout) {
      DiagramLayout layout = (DiagramLayout) element_p;
      return layout.getId() + " " + layout.getName() + " ";
    }
    if (element_p instanceof Bounds || element_p instanceof Location || element_p instanceof Size) {
      String id = (String) getMatchID(element_p.eContainer(), scope_p);
      id += "." + element_p.eContainingFeature().getName();
      if (element_p.eContainingFeature().isMany()) {
        id += "[" + ((EList) element_p.eContainer().eGet(element_p.eContainingFeature())).indexOf(element_p) + "]";
      }
      return id;
    }
    if (element_p instanceof NoteLayout) {
      NoteLayout layout = (NoteLayout) element_p;
      String id = (String) getMatchID(element_p.eContainer(), scope_p);
      id += " id:" + layout.getId();
      return id;
    }
    return super.getMatchID(element_p, scope_p);
  }
}
