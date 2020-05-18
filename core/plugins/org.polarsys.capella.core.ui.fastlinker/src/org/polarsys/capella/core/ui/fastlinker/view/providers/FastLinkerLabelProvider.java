/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.fastlinker.view.providers;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.CapellaNavigatorLabelProvider;
import org.polarsys.capella.core.ui.fastlinker.FastLinkerManager;

// TODO: should it inherit simply MDEAdapterFactoryLabelProvider
public class FastLinkerLabelProvider extends CapellaNavigatorLabelProvider {
  
  @Override
  public Image getImage(Object object) {
    if (object instanceof Collection) {
      Collection<?> collection = (Collection<?>) object;

      if (collection.isEmpty()) {
        return null;
      }

      if (collection.size() == 1) {
        return super.getImage(collection.iterator().next());
      }

      Object next = collection.iterator().next();
      EClass eClass = ((EObject) next).eClass();
      Iterator<?> it = collection.iterator();
      while (it.hasNext()) {
        Object current = it.next();
        if (current instanceof EObject) {
          if (!((EObject) current).eClass().equals(eClass))
            return null;
        } else {
          return null;
        }
        return super.getImage(collection.iterator().next());
      }
    }
    return super.getImage(object);
  }

  @Override
  public String getText(Object object) {
    if (object instanceof Collection) {
      Collection<?> collection = (Collection<?>) object;

      if (collection.isEmpty()) {
        return null;
      }
      if (collection.size() == 1) {
        return super.getText(collection.iterator().next());
      }
      EClass eClass = FastLinkerManager.getCommonType((Collection<? extends EObject>) collection);
      if (eClass != null) {
        Iterator<?> it = collection.iterator();
        String array = "";
        while (it.hasNext()) {
          Object current = it.next();
          if (current instanceof EObject) {

            array += ", " + super.getText(current);
          } else {
            return null;
          }
        }
        return eClass.getName() + " [ " + array.substring(2) + " ]";
      }
    }
    return super.getText(object);
  }

}
