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

package org.polarsys.capella.core.data.interaction.ui.quickfix.resolver;

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
import org.polarsys.capella.core.model.helpers.AbstractCapabilityExt;
import org.polarsys.capella.core.ui.resources.CapellaUIResourcesPlugin;
import org.polarsys.capella.core.validation.ui.ide.PluginActivator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;

public class AddInvolvedElementsResolver extends AbstractCapellaMarkerResolution {
  private final String PROCESS_ICON = "icons/full/obj16/capella_process.gif";
  private final AbstractCapability capability;
  private final String label;
  private final String ruleId;
  private boolean multiMarkerQuickFix;

  public AddInvolvedElementsResolver(String label, AbstractCapability capability, boolean multiMarkerQuickFix,
      String ruleId) {
    this.capability = capability;
    this.label = label;
    this.multiMarkerQuickFix = multiMarkerQuickFix;
    this.ruleId = ruleId;
    super.setContributorId(CapellaUIResourcesPlugin.PLUGIN_ID);
    super.setImgKey(PROCESS_ICON);
  }

  public AbstractCapability getCapability() {
    return capability;
  }

  public String getLabel() {
    return label;
  }

  @Override
  protected boolean enabled(Collection<IMarker> markers) {
    return (markers.size() > 1) || multiMarkerQuickFix == false;
  }

  @Override
  public void run(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);
    if (!modelElements.isEmpty()) {
      getExecutionManager(marker).execute(new AbstractReadWriteCommand() {
        public void run() {
          for (EObject obj : modelElements) {
            if (obj instanceof Component) {
              Component component = (Component) obj;
              if (!AbstractCapabilityExt.getInvolvedComponents(capability).contains(component)) {
                AbstractCapabilityExt.addInvolvedComponent(capability, component);
              }
            } else if (obj instanceof AbstractFunction) {
              AbstractFunction function = (AbstractFunction) obj;
              if (!capability.getInvolvedAbstractFunctions().contains(function)) {
                AbstractCapabilityExt.addInvolvedFunction(capability, function);
              }
            } else if (obj instanceof FunctionalChain) {
              FunctionalChain functionalChain = (FunctionalChain) obj;
              if (!capability.getInvolvedFunctionalChains().contains(functionalChain)) {
                AbstractCapabilityExt.addInvolvedFunctionalChain(capability, functionalChain);
              }
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
    return this.multiMarkerQuickFix && ruleId.equals(markerRuleId) && this.capability != null && elements.contains(this.capability);
  }

  public String getRuleId() {
    return ruleId;
  }
}
