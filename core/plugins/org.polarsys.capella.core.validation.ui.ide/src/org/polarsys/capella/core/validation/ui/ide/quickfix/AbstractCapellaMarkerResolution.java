/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.validation.ui.ide.quickfix;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.statushandlers.StatusManager;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.handler.ReportMarkerResolution;
import org.polarsys.capella.core.validation.ui.ide.PluginActivator;
import org.polarsys.capella.core.validation.ui.ide.internal.quickfix.MarkerResolutionCache;

abstract public class AbstractCapellaMarkerResolution extends ReportMarkerResolution {

  protected String label = null;
  protected String desc = null;
  protected String imgKey = null;

  protected String contributorId = null;
  protected final static String[] noRuleIds = new String[0];

  /**
   * {@inheritDoc}
   */
  @Override
  public String getLabel() {
    return label;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription() {
    return desc;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Image getImage() {

    if ((contributorId != null) && (imgKey != null)) {
      ImageDescriptor imgDesc = AbstractUIPlugin.imageDescriptorFromPlugin(contributorId, imgKey);
      return imgDesc.createImage();
    }

    return null;
  }

  /** Write accessor on label */
  public final void setLabel(String label) {
    this.label = label;
  }

  /** Write accessor on description */
  public final void setDescription(String desc) {
    this.desc = desc;
  }

  /** Write accessor on imageKey */
  public final void setImgKey(String key) {
    this.imgKey = key;
  }

  /** Write accessor on contributorId */
  public final void setContributorId(String id) {
    this.contributorId = id;
  }

  public final String getContributorId() {
    return contributorId;
  }

  public List<EObject> getModelElements(IMarker marker) {
    return MarkerViewHelper.getModelElementsFromMarker(marker);
  }

  @Override
  /**
   * Subclasses may override if the default mechanism via canResolve/getResolvableRuleIds is not applicable for this
   * resolution. {@inheritDoc}
   */
  public IMarker[] findOtherMarkers(IMarker[] markers) {

    // this is for backwards compatibility
    if (markers.length == 1) {
      if (canResolve(markers[0])) {
        return new IMarker[] { markers[0] };
      }
    }

    Collection<IMarker> otherMarkers = new ArrayList<IMarker>();
    for (IMarker marker : markers) {
      if (canResolve(marker)) {
        otherMarkers.add(marker);
      }
    }
    return otherMarkers.toArray(new IMarker[0]);
  }

  /**
   * Check if this resolution can resolve the given marker. Used to compute the resolvable markers during
   * findOtherMarkers. This implementation checks if the unqualified ruleId stored in the marker is one of the ids
   * that's returned by getResolvableRuleIds.
   * 
   * @param marker
   * @return
   */
  protected boolean canResolve(IMarker marker) {
    String ruleId = MarkerViewHelper.getRuleID(marker, true);
    if (ruleId != null) {
      if (isEMFRule(ruleId)) {
        return true;
      }
      String fqnRule[] = ruleId.split("\\.");
      String shortRuleId = fqnRule.length > 0 ? fqnRule[fqnRule.length - 1] : null;
      if (shortRuleId != null) {
        for (String id : getResolvableRuleIds()) {
          if (shortRuleId.equals(id)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean isEMFRule(String ruleId) {
    return ruleId.startsWith("org.eclipse.emf.ecore.");
  }

  /**
   * Used to compute resolvable markers. Override this method to enable quickfix resolution if multiple markers are
   * selected. The default implementation returns null which effectively disables multi-selection quick fix resolution,
   * unless 'canResolve' or 'findOtherMarkers' is overridden.
   * 
   * @return an array of _fully qualified_ validation rule id's that this resolver can fix
   */
  protected String[] getResolvableRuleIds() {

    Map<AbstractCapellaMarkerResolution, Set<String>> resolverRuleMap = MarkerResolutionCache.INSTANCE
        .getResolverRuleMap();
    Set<String> ruleIds = resolverRuleMap.get(this);
    if (null == ruleIds) {
      return noRuleIds;
    }
    return ruleIds.toArray(new String[0]);
  }

  public boolean isMultipleMarkersResolver() {
    return !(getResolvableRuleIds().length == noRuleIds.length);

  }

  protected void deleteMarker(IMarker marker) {
    // delete marker
    try {
      marker.delete();
    } catch (CoreException exception) {
      StatusManager.getManager().handle(
          new Status(IStatus.ERROR, PluginActivator.getDefault().getPluginId(), exception.getMessage(), exception));
    }
  }
}
