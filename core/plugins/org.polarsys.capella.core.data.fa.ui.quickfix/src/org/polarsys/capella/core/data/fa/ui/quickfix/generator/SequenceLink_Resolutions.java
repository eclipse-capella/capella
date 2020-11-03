/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.ui.quickfix.generator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IMarkerResolution;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.data.fa.ui.quickfix.resolver.RemoveSequenceLinksAssociationsResolver;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractMarkerResolutionGenerator;

public abstract class SequenceLink_Resolutions extends AbstractMarkerResolutionGenerator {
  @Override
  protected IMarkerResolution[] doGetResolutions(IMarker marker) {
    List<IMarkerResolution> resolutions = new ArrayList<IMarkerResolution>();

    List<EObject> modelElements = MarkerViewHelper.getModelElementsFromMarker(marker);

    SequenceLink sequenceLink = null;
    FunctionalChainInvolvementLink link = null;
    for (EObject element : modelElements) {
      if (element instanceof SequenceLink) {
        sequenceLink = (SequenceLink) element;
      }
      if (element instanceof FunctionalChainInvolvementLink) {
        link = (FunctionalChainInvolvementLink) element;
      }
    }

    if (sequenceLink != null && link != null) {
      // add resolution for quickfix and quickfixall
      addResolution(sequenceLink, link, resolutions, getRuleId());
    }

    return resolutions.toArray(new IMarkerResolution[0]);
  }

  private void addResolution(SequenceLink sequenceLink, FunctionalChainInvolvementLink link,
      List<IMarkerResolution> resolutions, String ruleId) {
    String labelCommand = NLS.bind(getLabelQF(),
        new Object[] { EObjectLabelProviderHelper.getMetaclassLabel(sequenceLink, false),
            EObjectLabelProviderHelper.getText(link), EObjectLabelProviderHelper.getMetaclassLabel(link, false) });
    RemoveSequenceLinksAssociationsResolver command = new RemoveSequenceLinksAssociationsResolver(labelCommand,
        sequenceLink, false, ruleId);
    resolutions.add(command);

    String labelCommandAll = NLS.bind(getLabelQF_ALL(),
        new Object[] { EObjectLabelProviderHelper.getMetaclassLabel(sequenceLink, false) });
    RemoveSequenceLinksAssociationsResolver commandAll = new RemoveSequenceLinksAssociationsResolver(labelCommandAll,
        sequenceLink, true, ruleId);
    resolutions.add(commandAll);
  }

  @Override
  protected abstract String getRuleId();

  protected abstract String getLabelQF();

  protected abstract String getLabelQF_ALL();
}
