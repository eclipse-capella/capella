/*******************************************************************************
 * Copyright (c) 2017, 2019, 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.emf.diffmerge.patterns.capella.quickfix;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.diffmerge.patterns.core.CorePatternsPlugin;
import org.eclipse.emf.diffmerge.patterns.core.api.IPatternInstance;
import org.eclipse.emf.diffmerge.patterns.core.api.ext.IPatternSupport;
import org.eclipse.emf.diffmerge.patterns.core.api.status.IPatternConformityStatus;
import org.eclipse.emf.diffmerge.patterns.core.api.status.SimpleStatus;
import org.eclipse.emf.diffmerge.patterns.core.util.LocationsUtil;
import org.eclipse.emf.diffmerge.patterns.support.gen.commonpatternsupport.CommonPatternInstance;
import org.eclipse.emf.diffmerge.patterns.templates.engine.TemplatePatternsEnginePlugin;
import org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;

/**
 * A quick fix for pattern instance issues.
 */
public class PatternCapellaMarkerResolution extends AbstractPatternCapellaMarkerResolution {

  /** The map from markers to corresponding sets of concerned pattern instances */
  protected Map<IMarker, Collection<IPatternInstance>> marker2InvalidPatternInstances = new HashMap<>();

  /**
   * Return whether pattern instance elements must be preserved during resolution
   */
  protected boolean shouldKeepElements() {
    return false;
  }

  /**
   * @see org.eclipse.emf.diffmerge.patterns.capella.quickfix.AbstractPatternCapellaMarkerResolution#run(org.eclipse.core.resources.IMarker)
   */
  @Override
  public void run(IMarker marker) {

    final boolean mustDeleteMarker[] = { false };
    if (!marker2InvalidPatternInstances.get(marker).isEmpty()) {
      for (final IPatternInstance instance : marker2InvalidPatternInstances.get(marker)) {
        AbstractReadWriteCommand abstrctCommand = new AbstractReadWriteCommand() {
          @Override
          public void run() {
            EObject patternStorage = null;
            if (instance instanceof CommonPatternInstance)
              patternStorage = ((CommonPatternInstance) instance).eContainer();

            if (shouldKeepElements())
              instance.delete(true);
            else
              instance.delete(false);

            if (patternStorage != null)
              deletePatternStorage(patternStorage);

            mustDeleteMarker[0] = true;
          }
        };
        if (instance instanceof EObject) {
          TransactionHelper.getExecutionManager((EObject) instance).execute(abstrctCommand);
        }
      }
    }

    // Remove the marker if the element is deleted.
    if (mustDeleteMarker[0]) {
      try {
        marker.delete();
      } catch (CoreException exception) {
        // do nothing
      }
    }
  }

  /**
   * @see org.polarsys.capella.common.tools.report.appenders.reportlogview.handler.ReportMarkerResolution#enabled(java.util.Collection)
   */
  @Override
  public boolean enabled(Collection<IMarker> markers) {
    for (IMarker marker : markers) {
      List<IPatternInstance> invalidPatternInstances = new ArrayList<>();
      List<EObject> modelElements = getModelElements(marker);
      if (modelElements.isEmpty()) {
        return false;
      }
      final EObject modelElement = modelElements.get(0);

      IPatternSupport support = CorePatternsPlugin.getDefault().getPatternSupportFor(modelElement);
      if (support != null) {
        List<IPatternInstance> instances = support.getRelatedInstances(modelElement);
        for (IPatternInstance instance : instances) {
          List<EObject> roleElements = LocationsUtil.getRoleElements(instance);
          // Associate instance to its first role element only
          if (roleElements.contains(modelElement)) {
            List<EStructuralFeature> featuresToIgnore = Collections.emptyList();
            ISemanticRuleProvider provider = TemplatePatternsEnginePlugin.getDefault()
                .getSemanticRuleProviderFor(modelElement);
            if (provider != null)
              featuresToIgnore = provider.getDefaultOptionalMergeFeatures();
            IPatternConformityStatus status = instance.checkConformance(featuresToIgnore);
            if (status == SimpleStatus.NO_PATTERN_FAILURE) {
              invalidPatternInstances.add(instance);
            }
          }
        }
      }
      marker2InvalidPatternInstances.put(marker, invalidPatternInstances);
      if (invalidPatternInstances.isEmpty())
        return false;
    }
    return true;
  }

  /**
   * @see org.polarsys.capella.common.tools.report.appenders.reportlogview.handler.ReportMarkerResolution#quickFixAllSimilarEnabled(java.util.Collection)
   */
  @Override
  protected boolean quickFixAllSimilarEnabled(Collection<IMarker> markers) {
    return false;
  }

}
