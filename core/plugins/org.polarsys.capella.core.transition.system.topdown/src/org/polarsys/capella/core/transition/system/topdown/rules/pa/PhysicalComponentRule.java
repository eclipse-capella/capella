/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.rules.pa;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
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

    if (!PreferenceHelper.getInstance().transitionPC2CIWhileScenarioTransition()) {
      return new Status(IStatus.WARNING, Messages.Activity_Transformation, org.polarsys.capella.core.transition.system.topdown.constants.Messages.PC2CI_Preferences);

    } else if (((PhysicalComponent) element).isActor()) {
      return new Status(IStatus.WARNING, Messages.Activity_Transformation,
          NLS.bind(org.polarsys.capella.core.transition.system.topdown.constants.Messages.PC2CI_Actor,
              ((PhysicalComponent) element).getName()));
    }
    return transformRequired;
  }

  @Override
  protected void retrieveComponentGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    retrieveRepresentingPartitions(source_p, result_p, context_p);
  }

  @Override
  protected void updateElement(EObject element, EObject result, IContext context) {
    super.updateElement(element, result, context);

    // Set it from the preference
    ((ConfigurationItem) result).setKind(getConfigurationItemKind());
  }

  private ConfigurationItemKind getConfigurationItemKind() {
    String configurationItemKind = PreferenceHelper.getInstance().getConfigurationItemKind();
    return ConfigurationItemKind.getByName(configurationItemKind);
  }
}
