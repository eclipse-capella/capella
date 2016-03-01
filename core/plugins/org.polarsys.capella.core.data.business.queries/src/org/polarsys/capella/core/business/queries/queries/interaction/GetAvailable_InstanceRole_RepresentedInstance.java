/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.business.queries.queries.interaction;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPkgExt;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
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
        availableElements.removeAll(QueryInterpretor.executeQuery("GetCurrent_InstanceRole_RepresentedInstance", ir, context));//$NON-NLS-1$
      }
    }
    return (List) availableElements;
  }

  /**
   * @param scenario
   */
  private List<CapellaElement> getAvailableElementsForFunctionalScenario(Scenario scenario) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    AbstractFunctionalArchitecture archi =
        (AbstractFunctionalArchitecture) EcoreUtil2.getFirstContainer(scenario, FaPackage.Literals.ABSTRACT_FUNCTIONAL_ARCHITECTURE);
    FunctionPkg fpackage = archi.getOwnedFunctionPkg();
    for (Object obj : EcoreUtil2.getAllContents(FunctionPkgExt.getOwnedFunctions(fpackage))) {
      if (obj instanceof AbstractFunction) {
        AbstractFunction af = (AbstractFunction) obj;
        boolean found = false;
        for (InstanceRole ir : scenario.getOwnedInstanceRoles()) {
          if (ir.getRepresentedInstance() == obj) {
            found = true;
          }
        }
        if (!found) {
          result.add(af);
        }
      }
    }
    return result;
  }

  /**
   * @param scenario
   */
  private List<CapellaElement> getAvailableElementsForComponents(Scenario scenario) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    List<Part> excluded = new ArrayList<Part>();
    for (InstanceRole ir : scenario.getOwnedInstanceRoles()) {
      if (ir.getRepresentedInstance() instanceof Part) {
        excluded.add((Part) ir.getRepresentedInstance());
      }
    }
    result.addAll(ScenarioExt.getAllAvailableParts(scenario, excluded));
    for (AbstractActor aa : ScenarioExt.getAllActors(scenario)) {
      for (AbstractTypedElement ate : aa.getAbstractTypedElements()) {
        if ((ate instanceof Part) && !excluded.contains(ate)) {
          result.add((Part) ate);
        }
      }
    }
    return result;
  }

  /**
   * @param scenario
   * @return
   */
  private java.util.Collection<? extends CapellaElement> getAvailableElementsForEntities(Scenario scenario) {
    List<Part> excluded = new ArrayList<Part>();
    for (InstanceRole ir : scenario.getOwnedInstanceRoles()) {
      if (ir.getRepresentedInstance() instanceof Part) {
        excluded.add((Part) ir.getRepresentedInstance());
      }
    }
    return ScenarioExt.getAllComponents(scenario, excluded);
  }

}
