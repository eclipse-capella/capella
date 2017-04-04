/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.compare;

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

import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.api.IMergePolicy;
import org.eclipse.emf.diffmerge.api.config.IComparisonConfigurator;
import org.eclipse.emf.diffmerge.impl.policies.ComparisonConfigurator;
import org.eclipse.emf.diffmerge.ui.sirius.SiriusComparisonMethod;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ILabelProvider;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory;


/**
 * A definition of Capella comparisons.
 */
public class CapellaComparisonMethod extends SiriusComparisonMethod {
  
  /** The "transfer data between independent models" configurator */
  public static final IComparisonConfigurator CONFIGURATOR_P2L =
      new ComparisonConfigurator(
          Messages.CapellaComparisonMethod_Usage_Transition,
          Messages.CapellaComparisonMethod_Usage_Transition_Tooltip,
          Arrays.asList(INTRINSIC_ID, EXTRINSIC_ID),
          Arrays.asList(CRITERION_INTRINSIC_ID_SID));
  
  /** The "compare versions of the same model" configurator */
  public static final IComparisonConfigurator CONFIGURATOR_SID =
    new ComparisonConfigurator(
        Messages.CapellaComparisonMethod_Usage_P2L,
        Messages.CapellaComparisonMethod_Usage_P2L_Tooltip,
        Arrays.asList(INTRINSIC_ID, EXTRINSIC_ID, STRUCTURE, SEMANTICS),
        Arrays.asList(
            CRITERION_STRUCTURE_ROOTS,
            CRITERION_SEMANTICS_DEFAULTCONTENTS,
            CRITERION_SEMANTICS_P2L));
  
  
  /**
   * Constructor
   * @param leftScopeSpec a non-null scope specification
   * @param rightScopeSpec a non-null scope specification
   * @param ancestorScopeSpec an optional scope specification
   */
  public CapellaComparisonMethod(IModelScopeDefinition leftScopeSpec,
      IModelScopeDefinition rightScopeSpec, IModelScopeDefinition ancestorScopeSpec) {
    super(leftScopeSpec, rightScopeSpec, ancestorScopeSpec);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.specification.ext.ConfigurableComparisonMethod#createConfigurators()
   */
  @Override
  protected List<IComparisonConfigurator> createConfigurators() {
    List<IComparisonConfigurator> result = new LinkedList<IComparisonConfigurator>();
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
    SemanticEditingDomainFactory factory = new SemanticEditingDomainFactory();
    EditingDomain result = factory.createEditingDomain();
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.sirius.SiriusComparisonMethod#createDiffPolicy()
   */
  @Override
  protected IDiffPolicy createDiffPolicy() {
    return new CapellaDiffPolicy();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.sirius.SiriusComparisonMethod#createMatchPolicy()
   */
  @Override
  protected IMatchPolicy createMatchPolicy() {
    return new CapellaMatchPolicy();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.sirius.SiriusComparisonMethod#createMergePolicy()
   */
  @Override
  protected IMergePolicy createMergePolicy() {
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
  
}
