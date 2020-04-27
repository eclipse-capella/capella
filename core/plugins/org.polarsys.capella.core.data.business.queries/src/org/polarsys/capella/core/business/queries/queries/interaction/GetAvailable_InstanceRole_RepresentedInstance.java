/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.business.queries.queries.interaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPkgExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.helpers.queries.GetAllExchangeItems;
import org.polarsys.capella.core.model.helpers.queries.filters.OnlySharedDataOrEventOrUnsetFilter;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_InstanceRole_RepresentedInstance extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (capellaElement instanceof InstanceRole) {
      Scenario scenario = (Scenario) capellaElement.eContainer();
      InstanceRole ir = (InstanceRole) capellaElement;

      if (ir.getRepresentedInstance() instanceof ExchangeItemInstance) {
        List<Object> exchangeItems = new GetAllExchangeItems().execute(ir, context);
        for (Iterator<Object> it = exchangeItems.iterator(); it.hasNext();) {
          ExchangeItem ei = (ExchangeItem) it.next();
          if (new OnlySharedDataOrEventOrUnsetFilter().keepElement(ei, context)) {
            availableElements.addAll(ei.getOwnedExchangeItemInstances());
          }
        }
      }

      SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(capellaElement);
      if (sysEng != null) {
        if (scenario.getKind() == ScenarioKind.FUNCTIONAL) {
          availableElements.addAll(getAvailableElementsForFunctionalScenario(scenario));
        } else if ((scenario.getKind() == ScenarioKind.DATA_FLOW) || (scenario.getKind() == ScenarioKind.INTERFACE)) {
          availableElements.addAll(getAvailableElementsForComponents(scenario));
        } else if (scenario.getKind() == ScenarioKind.INTERACTION) {
          if (ir.getRepresentedInstance() instanceof OperationalActivity) {
            availableElements.addAll(getAvailableElementsForFunctionalScenario(scenario));
          } else {
            availableElements.addAll(getAvailableElementsForEntities(scenario));
          }
        } else {
          throw new IllegalArgumentException();
        }
      }

    }
    return (List) availableElements;
  }

  /**
   * @param scenario
   */
  private List<CapellaElement> getAvailableElementsForFunctionalScenario(Scenario scenario) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    AbstractFunctionalArchitecture archi = (AbstractFunctionalArchitecture) EcoreUtil2.getFirstContainer(scenario,
        FaPackage.Literals.ABSTRACT_FUNCTIONAL_ARCHITECTURE);
    FunctionPkg fpackage = archi.getOwnedFunctionPkg();
    for (Object obj : EcoreUtil2.getAllContents(FunctionPkgExt.getOwnedFunctions(fpackage))) {
      if (obj instanceof AbstractFunction) {
        result.add((CapellaElement) obj);
      }
    }
    return result;
  }

  /**
   * @param scenario
   */
  private List<CapellaElement> getAvailableElementsForComponents(Scenario scenario) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    result.addAll(ScenarioExt.getAllAvailableParts(scenario, Collections.emptyList()));
    for (Component aa : ScenarioExt.getAllActors(scenario)) {
      for (AbstractTypedElement ate : aa.getAbstractTypedElements()) {
        if ((ate instanceof Part)) {
          result.add((Part) ate);
        }
      }
    }

    // Any Scenario can have instance roles for its architecture root component?
    BlockArchitecture ba = BlockArchitectureExt.getRootBlockArchitecture(scenario);
    Component root = ba.getSystem();
    if (root != null) {
      for (Part te : ((Component) root).getRepresentingParts()) {
        result.add(te);
      }
    }
    return result;
  }

  /**
   * @param scenario
   * @return
   */
  private java.util.Collection<? extends CapellaElement> getAvailableElementsForEntities(Scenario scenario) {
    return ScenarioExt.getAllComponents(scenario, Collections.emptyList());
  }

}
