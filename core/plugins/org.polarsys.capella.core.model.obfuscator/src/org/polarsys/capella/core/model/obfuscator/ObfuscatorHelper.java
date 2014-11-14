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
package org.polarsys.capella.core.model.obfuscator;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.capellacore.AbstractAnnotation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

public class ObfuscatorHelper {
  /**
   * obfuscate a capella element if apply. Do nothing instead. 
   * should not be used. use ObfuscateSemanticResourceCommand instead.
   */
  @Deprecated
  public static void obfuscateCapellaElement(EObject currentObject) {
    if (currentObject instanceof AbstractNamedElement) {
      // The new name is a generated Id. It shouldn't be digest.
      AbstractNamedElement ane = (AbstractNamedElement) currentObject;
      ane.setName(generateUnreadableString(ane.getName()));
    }
    if (currentObject instanceof CapellaElement) {
      CapellaElement capellaElement = (CapellaElement) currentObject;
      capellaElement.setDescription(generateUnreadableString(capellaElement.getDescription()));
      capellaElement.setSummary(generateUnreadableString(capellaElement.getSummary()));
    }
    if (currentObject instanceof AbstractCapability) {
      Capability capability = (Capability) currentObject;
      capability.setPostCondition(generateUnreadableString(capability.getPreCondition()));
      capability.setPreCondition(generateUnreadableString(capability.getPostCondition()));
    }
    if (currentObject instanceof AbstractAnnotation) {
      AbstractAnnotation ann = (AbstractAnnotation) currentObject;
      ann.setContent(generateUnreadableString(ann.getContent()));
    }
  }

  /**
   * generate Unreadable string.
   * @return
   */
  public static String generateUnreadableString(String uncrypted_p) {
    if ((uncrypted_p == null) || "".equals(uncrypted_p)) {
      return "";
    }
    return IdGenerator.createId().substring(0, 5); // don't generate hyper long string.
  }

}
