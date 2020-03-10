/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.topdown.rules.pa;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemKind;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class PhysicalComponentRule extends org.polarsys.capella.core.transition.system.topdown.rules.cs.ComponentRule {

  @Override
  protected EClass getSourceType() {
    return PaPackage.Literals.PHYSICAL_COMPONENT;
  }

  @Override
  public EClass getTargetType(EObject element, IContext context) {
    return EpbsPackage.Literals.CONFIGURATION_ITEM;
  }
  
  @Override
  public IStatus transformRequired(EObject element, IContext context) {
    IStatus transformRequired = super.transformRequired(element, context);
    if (transformRequired.isOK() && !((PhysicalComponent) element).isActor()) {
      return Status.OK_STATUS;
    }
    return new Status(IStatus.WARNING, Messages.Activity_Transformation,
        "Physical Actor " + ((PhysicalComponent) element).getName());
  }
  
  @Override
  protected void updateElement(EObject element, EObject result, IContext context) {
    super.updateElement(element, result, context);
    
    // Set it from the preference
    ((ConfigurationItem)result).setKind(getConfigurationItemKind());
  }
  
  private ConfigurationItemKind getConfigurationItemKind() {
    String configurationItemKind = PreferenceHelper.getInstance().getConfigurationItemKind();
    return ConfigurationItemKind.getByName(configurationItemKind);
  }
}
