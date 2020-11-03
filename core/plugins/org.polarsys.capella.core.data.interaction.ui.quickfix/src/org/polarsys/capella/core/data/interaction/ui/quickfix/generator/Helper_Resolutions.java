/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.interaction.ui.quickfix.generator;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IMarkerResolution;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.ui.quickfix.resolver.AddInvolvedElementsResolver;

public class Helper_Resolutions {
  public static void addResolution(AbstractCapability capability, EObject element, List<IMarkerResolution> resolutions,
      String ruleId) {
    String labelCommand = NLS.bind("Create involvment {0} ({1}) to {2} ({3})",
        new Object[] { capability.getName(), EObjectLabelProviderHelper.getMetaclassLabel(capability, false),
            EObjectLabelProviderHelper.getText(element),
            EObjectLabelProviderHelper.getMetaclassLabel(element, false) });
    AddInvolvedElementsResolver command = new AddInvolvedElementsResolver(labelCommand, false, ruleId);
    resolutions.add(command);

    String labelCommandAll = "Fix all missing involvments";
    AddInvolvedElementsResolver commandAll = new AddInvolvedElementsResolver(labelCommandAll, true, ruleId);
    resolutions.add(commandAll);
  }
}
