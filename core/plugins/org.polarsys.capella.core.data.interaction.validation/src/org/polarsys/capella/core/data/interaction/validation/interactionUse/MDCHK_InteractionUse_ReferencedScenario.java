/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.interaction.validation.interactionUse;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/*
 * Rule: DWF_DS_23 - Invalid referenced scenario
 */
public class MDCHK_InteractionUse_ReferencedScenario extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();

    if (eObj instanceof InteractionUse) {
      InteractionUse interaction = (InteractionUse) eObj;
      Scenario scenario = (Scenario) interaction.eContainer();
      Scenario refScenario = interaction.getReferencedScenario();
      if(refScenario != null && !isValidReference(interaction, scenario, refScenario)) {
        return createCtxStatus(ctx_p, interaction, scenario, refScenario);
      }
    }
    return ctx_p.createSuccessStatus();
  }
  
  public boolean isValidReference(InteractionUse interaction, Scenario scenario, Scenario refScenario) {
    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(interaction.eClass(),
        InteractionPackage.eINSTANCE.getInteractionUse_ReferencedScenario());
    if (query != null) {
      List<EObject> elements = query.getAvailableElements(interaction);
      if (!elements.contains(refScenario)) {
        return false;
      }
    }
    return true;
  }

  private IStatus createCtxStatus(IValidationContext ctx, InteractionUse interaction, Scenario scenario,
      Scenario referencedScenario) {
    BlockArchitecture refScenarioArchitecture = BlockArchitectureExt.getRootBlockArchitecture(referencedScenario);
    BlockArchitecture scenarioArchitecture = BlockArchitectureExt.getRootBlockArchitecture(scenario);

    Object[] msgArguments = new Object[] { EObjectExt.getText(interaction),
        EObjectLabelProviderHelper.getMetaclassLabel(interaction, false), scenario.getName(),
        EObjectLabelProviderHelper.getMetaclassLabel(referencedScenario, false), scenarioArchitecture.getName(),
        referencedScenario.getName(), EObjectLabelProviderHelper.getMetaclassLabel(referencedScenario, false),
        refScenarioArchitecture.getName() };
    return ctx.createFailureStatus(msgArguments);
  }
}
