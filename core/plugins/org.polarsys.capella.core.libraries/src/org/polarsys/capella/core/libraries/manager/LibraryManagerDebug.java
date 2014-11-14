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
package org.polarsys.capella.core.libraries.manager;

import java.util.Collection;

import org.polarsys.capella.common.libraries.IAbstractLibrary;
import org.polarsys.capella.common.libraries.IAbstractModel;

/**
 */
public class LibraryManagerDebug {

  public static void promptLibraryInfos(IAbstractModel model) {
    System.out.println("--- " + model.getName() + " ---");
    Collection<IAbstractLibrary> refs = model.getReferencedLibraries(false);
    for (IAbstractLibrary library : model.getAllReferencedLibraries(false)) {
      String directRef = "";
      if (refs.contains(library)) {
        directRef = "*";
      }
      String directAccess = "";
      if (library.getAccessPolicyIn(model, false) != null) {
        directAccess = "*";
      }
      System.out.println(">  " + library.getName() + directRef + " (active=" + library.isActiveIn(model) + ", accessPolicy="
                         + library.getAccessPolicyIn(model, true) + directAccess + ")");
    }
  }

}
