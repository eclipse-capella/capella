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

package org.polarsys.capella.core.data.interaction.ui.quickfix.generator;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IMarkerResolution;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.ui.quickfix.resolver.AddInvolvedElementsResolver;
import org.eclipse.osgi.util.NLS;

public class Helper_Resolutions {
  public static void addResolution(AbstractCapability capability, EObject element, List<IMarkerResolution> resolutions,
      String ruleId) {
    String labelCommand = NLS.bind("Create involvment {0} ({1}) to {2} ({3})",
        new Object[] { capability.getName(), EObjectLabelProviderHelper.getMetaclassLabel(capability, false),
            EObjectLabelProviderHelper.getText(element),
            EObjectLabelProviderHelper.getMetaclassLabel(element, false) });
    AddInvolvedElementsResolver command = new AddInvolvedElementsResolver(labelCommand, capability, false, ruleId);
    resolutions.add(command);

    String labelCommandAll = NLS.bind("Fix all missing involvments for {0} ({1})",
        new Object[] { capability.getName(), EObjectLabelProviderHelper.getMetaclassLabel(capability, false) });
    AddInvolvedElementsResolver commandAll = new AddInvolvedElementsResolver(labelCommandAll, capability, true, ruleId);
    resolutions.add(commandAll);
  }
}
