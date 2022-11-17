/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.compare;

import static org.eclipse.emf.diffmerge.gmf.GMFMatchPolicy.CRITERION_SEMANTICS_DIAGRAMS_VIEWBYELEMENT;
import static org.eclipse.emf.diffmerge.gmf.GMFMatchPolicy.CRITERION_SEMANTICS_DIAGRAMS_VIEWBYTYPE;
import static org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.CRITERION_SEMANTICS_DEFAULTCONTENTS;
import static org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.CRITERION_STRUCTURE_ROOTS;
import static org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.MatchCriterionKind.EXTRINSIC_ID;
import static org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.MatchCriterionKind.INTRINSIC_ID;
import static org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.MatchCriterionKind.SEMANTICS;
import static org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.MatchCriterionKind.STRUCTURE;
import static org.polarsys.capella.core.compare.CapellaMatchPolicy.CRITERION_INTRINSIC_ID_SID;
import static org.polarsys.capella.core.compare.CapellaMatchPolicy.CRITERION_SEMANTICS_P2L;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.diffmerge.generic.api.config.IComparisonConfigurator;
import org.eclipse.emf.diffmerge.impl.policies.ComparisonConfigurator;
import org.eclipse.emf.diffmerge.ui.sirius.SiriusComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.IComparisonMethodFactory;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategoryProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ILabelProvider;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;


/**
 * A definition of Capella comparisons.
 */
public class CapellaComparisonMethod extends SiriusComparisonMethod {
  
  /** The "transfer data between independent models" configurator */
  public static final IComparisonConfigurator CONFIGURATOR_P2L =
      new ComparisonConfigurator(
          org.polarsys.capella.core.compare.Messages.CapellaComparisonMethod_Usage_Transition,
          org.polarsys.capella.core.compare.Messages.CapellaComparisonMethod_Usage_Transition_Tooltip,
          Arrays.asList(INTRINSIC_ID, EXTRINSIC_ID),
          Arrays.asList(CRITERION_INTRINSIC_ID_SID));
  
  /** The "compare versions of the same model" configurator */
  public static final IComparisonConfigurator CONFIGURATOR_SID =
    new ComparisonConfigurator(
        org.polarsys.capella.core.compare.Messages.CapellaComparisonMethod_Usage_P2L,
        org.polarsys.capella.core.compare.Messages.CapellaComparisonMethod_Usage_P2L_Tooltip,
        Arrays.asList(INTRINSIC_ID, EXTRINSIC_ID, STRUCTURE, SEMANTICS),
        Arrays.asList(
            CRITERION_STRUCTURE_ROOTS,
            CRITERION_SEMANTICS_DEFAULTCONTENTS,
            CRITERION_SEMANTICS_P2L));
  
  /** 
   * The "Capella default" configurator 
   * Based on IDs for semantic elements and semantic criteria for graphical elements.
   */
  public static final IComparisonConfigurator CONFIGURATOR_CAPELLA_DEFAULT =
    new ComparisonConfigurator(
        Messages.CapellaComparisonMethod_Usage_Default,
        Messages.CapellaComparisonMethod_Usage_Default_Tooltip,
        Arrays.asList(INTRINSIC_ID, EXTRINSIC_ID, SEMANTICS),
        Arrays.asList(
            CRITERION_SEMANTICS_DIAGRAMS_VIEWBYELEMENT,
            CRITERION_SEMANTICS_DIAGRAMS_VIEWBYTYPE));
  
  
  /**
   * Constructor
   * @param leftScopeDef a non-null scope definition
   * @param rightScopeDef a non-null scope definition
   * @param ancestorScopeDef an optional scope definition
   * @param factory the optional factory this comparison method originates from
   */
  public CapellaComparisonMethod(IModelScopeDefinition leftScopeDef,
      IModelScopeDefinition rightScopeDef, IModelScopeDefinition ancestorScopeDef,
      IComparisonMethodFactory<EObject> factory) {
    super(leftScopeDef, rightScopeDef, ancestorScopeDef, factory);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.ConfigurableComparisonMethod#createConfigurators()
   */
  @Override
  protected List<IComparisonConfigurator> createConfigurators() {
    List<IComparisonConfigurator> result = new LinkedList<IComparisonConfigurator>();
    result.add(CONFIGURATOR_CAPELLA_DEFAULT);
    result.add(CONFIGURATOR_VERSIONS);
    result.add(CONFIGURATOR_P2L);
    result.add(CONFIGURATOR_DATA_TRANSFER);
    result.add(CONFIGURATOR_SID);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonMethod#createEditingDomain()
   */
  @Override
  protected EditingDomain createEditingDomain() {
    ExecutionManager manager = ExecutionManagerRegistry.getInstance().addNewManager();
    return manager.getEditingDomain();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.sirius.SiriusComparisonMethod#createDiffPolicy()
   */
  @Override
  protected IDiffPolicy<EObject> createDiffPolicy() {
    return new CapellaDiffPolicy();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.sirius.SiriusComparisonMethod#createMatchPolicy()
   */
  @Override
  protected IMatchPolicy<EObject> createMatchPolicy() {
    return new CapellaMatchPolicy();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.sirius.SiriusComparisonMethod#createMergePolicy()
   */
  @Override
  protected IMergePolicy<EObject> createMergePolicy() {
    return new CapellaMergePolicy();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.DefaultComparisonMethod#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
    CapellaComparePlugin.getDefault().cleanupProxyProjects();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.sirius.SiriusComparisonMethod#getCustomLabelProvider()
   */
  @Override
  protected ILabelProvider getCustomLabelProvider() {
    return CapellaDiffMergeLabelProvider.getInstance();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.ConfigurableComparisonMethod#getDefaultConfigurator()
   */
  @Override
  public IComparisonConfigurator getDefaultConfigurator() {
    return CONFIGURATOR_CAPELLA_DEFAULT;
  }

  @Override
  protected IDifferenceCategoryProvider getCustomCategoryProvider() {
    return new CapellaDifferenceCategoryProvider();
  }
  
}
