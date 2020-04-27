/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.obfuscator.actions;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.obfuscator.ObfuscatorHelper;

/**
 */
public class ObfuscateSemanticResourceCommand extends AbstractReadWriteCommand {

  private Resource _resource;

  public ObfuscateSemanticResourceCommand(Resource resource_p) {
    _resource = resource_p;
  }

  public void run() {
    if (_resource != null) {
      obfuscateSemanticResource(_resource);
      _resource = null;
    }
  }

  /**
   * obfuscate given resource
   * @param resource_p
   */
  protected void obfuscateSemanticResource(Resource resource_p) {
    TreeIterator<EObject> allContents = resource_p.getAllContents();
    while (allContents.hasNext()) {
      EObject currentObject = allContents.next();
      if ((currentObject != null) && !(currentObject.eIsProxy())) {
        obfuscateSemanticElement(currentObject);
      }
    }
  }

  /**
   * obfuscate semantic element
   * @param object_p
   */
  protected void obfuscateSemanticElement(EObject object_p) {
    //Generic way
    if (object_p.eClass() != null) {
      for (EAttribute attribute : object_p.eClass().getEAllAttributes()) {
        if (isEMFObfuscableEAttribute(object_p, attribute)) {
          if (isSemanticallyObfuscableEAttribute(object_p, attribute)) {
            obfuscateEAttribute(object_p, attribute);
          }
        }
      }
    }
  }

  /**
   * returns whether attribute should be obfuscated according to EMF rules
   * (default implementation avoid IDAttribute or nonChangeable or derived or transient)
   * @param object_p
   * @param attribute_p
   * @return
   */
  protected boolean isEMFObfuscableEAttribute(EObject object_p, EAttribute attribute_p) {
    if (!attribute_p.isChangeable()) {
      return false;
    }
    if (attribute_p.isDerived()) {
      return false;
    }
    if (attribute_p.isTransient()) {
      return false;
    }

    EAttribute attributeId = object_p.eClass().getEIDAttribute();
    if ((attributeId != null) && attributeId.equals(attribute_p)) {
      return false;
    }
    return true;
  }

  /**
   * returns whether attribute is semantically obfuscable
   * @param object_p
   * @param attribute_p
   * @return
   */
  protected boolean isSemanticallyObfuscableEAttribute(EObject object_p, EAttribute attribute_p) {
    if (ModellingcorePackage.Literals.MODEL_ELEMENT__ID.equals(attribute_p)) {
      return false;
    }
    if (ModellingcorePackage.Literals.MODEL_ELEMENT__SID.equals(attribute_p)) {
      return false;
    }

    if (CapellacorePackage.Literals.KEY_VALUE__KEY.equals(attribute_p)) {
      if ((object_p != null) && (object_p.eContainer() != null) && (object_p.eContainer() instanceof Project)) {
        return false;
      }
    }
    if (CapellacorePackage.Literals.KEY_VALUE__VALUE.equals(attribute_p)) {
      if ((object_p != null) && (object_p.eContainer() != null) && (object_p.eContainer() instanceof Project)) {
        return false;
      }
    }

    return true;
  }

  /**
   * obfuscate given attribute on the given object
   * @param currentObject_p
   * @param attribute_p
   */
  protected void obfuscateEAttribute(EObject currentObject_p, EAttribute attribute_p) {
    try {
      Object value = currentObject_p.eGet(attribute_p);
      if (value != null) {
        if (!attribute_p.isMany()) {
          if (value instanceof String) {
            currentObject_p.eSet(attribute_p, ObfuscatorHelper.generateUnreadableString((String) value));
          }
        } else {
          if (value instanceof EList) {
            EList<Object> list = (EList) value;
            Iterator<Object> itList = list.iterator();
            int i = 0;
            while (itList.hasNext()) {
              Object itElement = itList.next();
              if ((itElement != null) && (itElement instanceof String)) {
                list.set(i, ObfuscatorHelper.generateUnreadableString((String) itElement));
              }
              i++;
            }
          }
        }
      }
    } catch (Exception e) {
      //Nothing here..
    }
  }
}
