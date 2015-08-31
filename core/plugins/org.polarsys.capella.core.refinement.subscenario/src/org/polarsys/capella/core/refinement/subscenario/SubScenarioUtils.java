/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.subscenario;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityPkgExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.LogicalComponentExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.core.refinement.framework.ui.IValidator;
import org.polarsys.capella.core.refinement.framework.ui.SelectionPage;
import org.polarsys.capella.core.refinement.framework.ui.SelectionWizard;
import org.polarsys.capella.core.refinement.framework.ui.model.SelectionItemNode;
import org.polarsys.capella.core.refinement.framework.ui.model.TargetSelectionItem;
import org.polarsys.capella.core.refinement.scenarios.core.ScenarioRefinement;

/**
 */
public class SubScenarioUtils {
  /**
   * Creates a new scenario and links it to the current scenario
   */
  public static void addNewSubScenario(final Scenario currentScenario, IProgressMonitor progressMonitor) {
    if (currentScenario != null) {
      Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);

      List<NamedElement> targets = evaluateTarget(currentScenario);
      if (!targets.isEmpty()) {
        String message = Messages.getString("SubScenarioUtils.0"); //$NON-NLS-1$
        TargetSelectionItem rootItem = new TargetSelectionItem(currentScenario, targets);
        SelectionWizard wizard = new SelectionWizard(
            rootItem,
            Messages.getString("SubScenarioUtils.1"), Messages.getString("SubScenarioUtils.2"), message, true, false, true, Messages.getString("SubScenarioUtils.3"), false); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        wizard.addValidator(new IValidator() {
          /**
           * @see IValidator#isValid()
           */
          public boolean isValid(SelectionPage page) {
            boolean result = true;
            for (SelectionItemNode item : page.getSelection()) {
              Object obj = item.getData();
              if (obj instanceof CapellaElement) {
                AbstractCapability capability = (AbstractCapability) currentScenario.eContainer();
                for (CapellaElement elt : RefinementLinkExt.getRefinementRelatedSourceElements(capability,
                    InteractionPackage.Literals.ABSTRACT_CAPABILITY)) {
                  if (EcoreUtil2.isContainedBy(elt, (CapellaElement) obj)) {
                    String realName = ScenarioRefinement.evaluateNamingRule((NamedElement) obj, page.getNameValue());
                    AbstractCapability subInteractionAspect = (AbstractCapability) elt;
                    if (!ListExt.filterByName(new ArrayList<EObject>(subInteractionAspect.getOwnedScenarios()),
                        realName).isEmpty()) {
                      result = false;
                    }
                  }
                }
              }
            }
            return result;
          }

          /**
           * @see IValidator#getMessage()
           */
          public String getMessage() {
            return Messages.getString("SubScenarioUtils.10"); //$NON-NLS-1$
          }
        });

        if (wizard.open() == 0) {
          String scenarioName = wizard.getNameValue();
          for (SelectionItemNode item : wizard.getSelectionList()) {
            SubScenarioRefinement ref = null;
            NamedElement target = (NamedElement) item.getData();
            if (target instanceof ComponentArchitecture) {
              ref = new SubScenarioRefinement(currentScenario, (ComponentArchitecture) target, scenarioName);
            } else if (target instanceof Component) {
              ref = new SubScenarioRefinement(currentScenario, (Component) target, scenarioName);
            }

            if (ref != null) {
              ref.execute(progressMonitor);

              _logger
                  .info(new EmbeddedMessage(
                      MessageFormat.format(Messages.getString("SubScenarioUtils.13"), scenarioName), IReportManagerDefaultComponents.REFINEMENT, ref.getResult())); //$NON-NLS-1$
            }
          }
        }
      } else {
        _logger.info(new EmbeddedMessage(
            Messages.getString("SubScenarioUtils.11"), IReportManagerDefaultComponents.REFINEMENT, currentScenario)); //$NON-NLS-1$
      }
    }
  }

  /**
   * Links an existing scenario to the current scenario
   */
  public static void addExistingSubScenario(Scenario currentScenario, IProgressMonitor progressMonitor) {
    if (currentScenario != null) {
      Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);

      Map<NamedElement, List<NamedElement>> targets = evaluateTargets(currentScenario);
      if (!targets.isEmpty()) {
        String message = Messages.getString("SubScenarioUtils.4"); //$NON-NLS-1$
        TargetSelectionItem rootItem = new TargetSelectionItem(targets);
        SelectionWizard wizard = new SelectionWizard(rootItem,
            Messages.getString("SubScenarioUtils.1"), Messages.getString("SubScenarioUtils.6"), message, true, true); //$NON-NLS-1$ //$NON-NLS-2$
        if (wizard.open() == 0) {
          for (SelectionItemNode item : wizard.getSelectionList()) {
            Scenario sc = (Scenario) item.getData();
            if (sc != null) {
              SubScenarioRefinement ref = new SubScenarioRefinement(currentScenario, sc);
              ref.execute(progressMonitor);

              _logger
                  .info(new EmbeddedMessage(
                      MessageFormat.format(Messages.getString("SubScenarioUtils.14"), sc.getName()), IReportManagerDefaultComponents.REFINEMENT, sc)); //$NON-NLS-1$
            }
          }
        }
      } else {
        _logger.info(new EmbeddedMessage(
            Messages.getString("SubScenarioUtils.12"), IReportManagerDefaultComponents.REFINEMENT, currentScenario)); //$NON-NLS-1$
      }
    }
  }

  private static List<NamedElement> evaluateTarget(Scenario scenario) {
    List<NamedElement> targetSet = new ArrayList<NamedElement>();
    CapellaElement diagramContainer = ScenarioExt.getContainer(scenario);

    if (diagramContainer instanceof SystemEngineering) {
      for (LogicalArchitecture la : SystemEngineeringExt.getAllLogicalArchitecture(scenario)) {
        targetSet.add(la);
      }
    } else if (diagramContainer instanceof LogicalArchitecture) {
      // Retrieves the logical components involved in Root LC decomposition (for current LA)
      LogicalComponent rootLc = SystemEngineeringExt.getRootLogicalComponent((LogicalArchitecture) diagramContainer);
      for (LogicalComponent lc : LogicalComponentExt.getDecompositionLogicalComponentInvolved(rootLc)) {
        if (ComponentExt.isComposite(lc) && ScenarioExt.contains(scenario, lc)) {
          targetSet.add(lc);
        }
      }
    } else if (diagramContainer instanceof LogicalComponent) {
      // Retrieves the logical components children of the current logical component
      for (LogicalComponent lc : LogicalComponentExt
          .getDecompositionLogicalComponentInvolved((LogicalComponent) diagramContainer)) {
        if (ComponentExt.isComposite(lc) && ScenarioExt.contains(scenario, lc)) {
          targetSet.add(lc);
        }
      }
    }
    return targetSet;
  }

  private static Map<NamedElement, List<NamedElement>> evaluateTargets(Scenario scenario) {
    Map<NamedElement, List<NamedElement>> result = new HashMap<NamedElement, List<NamedElement>>();

    for (NamedElement cpnt : evaluateTarget(scenario)) {
      List<NamedElement> scenariosToBeAttached = new ArrayList<NamedElement>();
      List<Scenario> scenarios = null;
      if (cpnt instanceof BlockArchitecture) {
        scenarios = AbstractCapabilityPkgExt
            .getAllScenarios(((BlockArchitecture) cpnt).getOwnedAbstractCapabilityPkg());
      } else if (cpnt instanceof LogicalComponent) {
        scenarios = AbstractCapabilityPkgExt.getAllScenarios(((LogicalComponent) cpnt).getOwnedAbstractCapabilityPkg());
      }

      if (scenarios != null) {
        for (Scenario sc : scenarios) {
          if (!ScenarioExt.createdByRefinement(sc)) {
            scenariosToBeAttached.add(sc);
          }
        }
        if (!scenariosToBeAttached.isEmpty()) {
          result.put(cpnt, scenariosToBeAttached);
        }
      }
    }

    return result;
  }
}
