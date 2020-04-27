/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.emf.diffmerge.patterns.capella.validation;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.diffmerge.patterns.ui.PatternsUIPlugin;
import org.eclipse.emf.diffmerge.patterns.ui.environment.IModelEnvironmentUI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.eclipse.emf.diffmerge.patterns.capella.Messages;
import org.eclipse.emf.diffmerge.patterns.core.CorePatternsPlugin;
import org.eclipse.emf.diffmerge.patterns.core.api.IPatternInstance;
import org.eclipse.emf.diffmerge.patterns.core.api.IPatternSymbol;
import org.eclipse.emf.diffmerge.patterns.core.api.IPatternVersion;
import org.eclipse.emf.diffmerge.patterns.core.api.ext.IPatternSupport;
import org.eclipse.emf.diffmerge.patterns.core.api.status.IPatternConformityStatus;
import org.eclipse.emf.diffmerge.patterns.core.util.LocationsUtil;
import org.eclipse.emf.diffmerge.patterns.templates.engine.TemplatePatternsEnginePlugin;
import org.eclipse.emf.diffmerge.patterns.templates.engine.ext.ISemanticRuleProvider;


/**
 * A checker for pattern conformity which bridges EMF validation and
 * the Pattern mechanism.
 */
public class Checker {
  
  /** The singleton instance */
  private static Checker __instance = null;
  
  /**
   * Return the singleton instance
   * @return a non-null object
   */
  public static Checker getInstance() {
    if (__instance == null)
      __instance = new Checker();
    return __instance;
  }
  
  
  /**
   * Constructor
   */
  protected Checker() {
    // Nothing specific
  }
  
  /**
   * Add the given validation message related to the given element in the
   * given diagnostic
   * @param diagnostic_p a non-null diagnostic
   * @param context_p a non-null element
   * @param message_p a non-null message
   */
  protected void addValidationProblem(DiagnosticChain diagnostic_p,
      ModelElement context_p, String message_p) {
    IModelEnvironmentUI me = PatternsUIPlugin.getDefault().getModelEnvironmentUI();
    BasicDiagnostic newDiag = new BasicDiagnostic(
        Diagnostic.WARNING, me.getText(context_p),
        0, message_p, new Object[]{context_p});
    diagnostic_p.add(newDiag);
  }
  
  /**
   * Check that a model element conforms to its patterns via all its bindings
   * @param target_p the model element to be checked
   * @return the diagnostics of the check
   */
  public Diagnostic check(ModelElement target_p) {
    BasicDiagnostic result = new BasicDiagnostic(Diagnostic.OK,
        new String(), 0, new String(), new Object[] {target_p});
    IPatternSupport support =
      CorePatternsPlugin.getDefault().getPatternSupportFor(target_p);
    if (support != null) {
      List<IPatternInstance> instances = support.getRelatedInstances(target_p);
      for(IPatternInstance instance : instances) {
        List<EObject> roleElements = LocationsUtil.getRoleElements(instance);
        // Associate instance to its first role element only
        if (roleElements.contains(target_p)) {
          List<EStructuralFeature> featuresToIgnore = Collections.emptyList();
          
          if (CorePatternsPlugin.getDefault().getRepositoryRegistry().getRepositories().isEmpty()) {
            addValidationProblem(result, target_p, Messages.Checker_NoCatalog);
            break;
          }
          
          ISemanticRuleProvider provider =
            TemplatePatternsEnginePlugin.getDefault().getSemanticRuleProviderFor(target_p);
          if (provider != null)
            featuresToIgnore = provider.getDefaultOptionalMergeFeatures();
          IPatternConformityStatus status = instance.checkConformance(featuresToIgnore);
          if (!status.isOk()) {
            String message = getFormattedMessage(instance, status);
            addValidationProblem(result, target_p, message);
          }
        }
      }
    }
    result.recomputeSeverity();
    return result;
  }
  
  /**
   * Return a message corresponding to the given status for the given instance
   * @param instance_p a non-null instance
   * @param status_p a non-null status
   * @return a non-null string
   */
  protected String getFormattedMessage(IPatternInstance instance_p,
      IPatternConformityStatus status_p) {
    String result = status_p.getDescription();
    if (result == null)
      result = new String();
    final String SPACE = " "; //$NON-NLS-1$
    result = result.replaceAll("\n", SPACE); //$NON-NLS-1$
    result = result.replaceAll("\r", SPACE); //$NON-NLS-1$
    result = result.replaceAll("\t", SPACE); //$NON-NLS-1$
    IPatternVersion version = instance_p.getPatternVersion();
    if (version != null) {
      IPatternSymbol symbol = version.getPatternSymbol();
      if (symbol != null && symbol.getName() != null) {
        result = Messages.Checker_Pattern + SPACE + symbol.getName() + ": " + result; //$NON-NLS-1$
      }
    }
    return result;
  }
  
}
