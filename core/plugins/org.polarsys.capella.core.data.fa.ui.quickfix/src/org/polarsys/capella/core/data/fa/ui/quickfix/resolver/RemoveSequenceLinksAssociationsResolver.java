/*******************************************************************************
 * Copyright (c) 2018, 2019, 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.ui.resources.CapellaUIResourcesPlugin;
import org.polarsys.capella.core.validation.ui.ide.PluginActivator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.SequenceLink;

public class RemoveSequenceLinksAssociationsResolver extends AbstractCapellaMarkerResolution {
  private final String PROCESS_ICON = "icons/full/obj16/capella_process.gif";
  private final SequenceLink sequenceLink;
  private final String label;
  private final String ruleId;
  private boolean multiMarkerQuickFix;

  public RemoveSequenceLinksAssociationsResolver(String label, SequenceLink sequenceLink, boolean multiMarkerQuickFix,
      String ruleId) {
    this.sequenceLink = sequenceLink;
    this.label = label;
    this.multiMarkerQuickFix = multiMarkerQuickFix;
    this.ruleId = ruleId;
    super.setContributorId(CapellaUIResourcesPlugin.PLUGIN_ID);
    super.setImgKey(PROCESS_ICON);
  }

  public SequenceLink sequenceLink() {
    return sequenceLink;
  }

  public String getLabel() {
    return label;
  }

  @Override
  public boolean enabled(Collection<IMarker> markers) {
    return (markers.size() > 1) || multiMarkerQuickFix == false;
  }

  @Override
  public void run(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);
    if (!modelElements.isEmpty()) {
      getExecutionManager(marker).execute(new AbstractReadWriteCommand() {
        public void run() {
          for (EObject obj : modelElements) {
            if (obj instanceof FunctionalChainInvolvementLink) {
              sequenceLink.getLinks().remove((FunctionalChainInvolvementLink) obj);
            }
          }
        }
      });
    }
    deleteMarker(marker);
  }

  protected ExecutionManager getExecutionManager(IMarker marker) {
    return TransactionHelper.getExecutionManager(getModelElements(marker));
  }

  protected void deleteMarker(IMarker marker) {
    if (marker.exists()) {
      try {
        marker.delete();
      } catch (CoreException e) {
        PluginActivator.getDefault().log(IStatus.ERROR, e.getLocalizedMessage(), e);
      }
    }
  }

  @Override
  protected boolean canResolve(IMarker marker) {
    String markerRuleId = MarkerViewHelper.getRuleID(marker, true);
    List<EObject> elements = MarkerViewHelper.getModelElementsFromMarker(marker);
    return this.multiMarkerQuickFix && ruleId.equals(markerRuleId) && this.sequenceLink != null
        && elements.contains(this.sequenceLink);
  }

  public String getRuleId() {
    return ruleId;
  }
}
