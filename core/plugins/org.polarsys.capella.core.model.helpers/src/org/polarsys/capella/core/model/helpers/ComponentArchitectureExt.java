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
package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.capellacore.Structure;

/**
 * ComponentArchitecture helpers
 * 
 * 
 */
public class ComponentArchitectureExt {

  /**
   * Gets all the components contained in a component architecture
   * 
   * 
   * @param componentArchitecture_p the parent component architecture
   * @return list of components
   */
  static public List<Component> getComponentsFromComponentArchitecture(ComponentArchitecture componentArchitecture_p) {
    List<Component> list = new ArrayList<Component>();
    for (Object obj : componentArchitecture_p.eContents()) {
      if (obj instanceof Component) {
        list.add((Component) obj);
      } else if (obj instanceof Structure) {
        for (Object content : ((Structure) obj).eContents()) {
          if (content instanceof Component) {
            list.add((Component) content);
            list.addAll(ComponentExt.getComponentsFromComponent((Component) content));
          }
        }
      }
    }
    return list;
  }
}
