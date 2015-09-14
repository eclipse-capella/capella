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
package org.polarsys.capella.core.refinement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.refinement.framework.ui.SelectionWizard;
import org.polarsys.capella.core.refinement.framework.ui.model.SelectionItemNode;
import org.polarsys.capella.core.refinement.framework.ui.model.TargetSelectionItem;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ProcessorException;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor;

/**
 */
public class RefinementMultiple implements IProcessor {

  /**
   *
   */
  private List<IProcessor> _refinements = null;

  /**
   * @param srcElt
   */
  public RefinementMultiple(NamedElement srcElt) {
    _refinements = new ArrayList<IProcessor>();
    List<Scenario> scenarios = new ArrayList<Scenario>();

    if (srcElt instanceof Scenario && ((Scenario) srcElt).getKind().equals(ScenarioKind.INTERFACE)) {
      scenarios.add((Scenario) srcElt);
    } else {
      List<Scenario> scList = new ArrayList<Scenario>();
      for (EObject eObject : EObjectExt.getAll(srcElt, InteractionPackage.Literals.SCENARIO)) {
        if (!((Scenario) eObject).isMerged() && ((Scenario) eObject).getKind().equals(ScenarioKind.INTERFACE))
          scList.add((Scenario) eObject);
      }
      if (CapellaLayerCheckingExt.isInLogicalLayer(srcElt) || srcElt instanceof LogicalArchitecture) {
        // Retrieve scenario directly under the LogicalContainer selected by user (no deep search)
        NamedElement headContainer = null;
        if (srcElt instanceof LogicalComponent || srcElt instanceof LogicalArchitecture)
          headContainer = srcElt;
        else {
          headContainer = (LogicalComponent) EcoreUtil2.getFirstContainer(srcElt, LaPackage.Literals.LOGICAL_COMPONENT);
          if (null == headContainer)
            headContainer = (LogicalArchitecture) EcoreUtil2.getFirstContainer(srcElt,
                LaPackage.Literals.LOGICAL_ARCHITECTURE);
        }
        for (Scenario scenario : scList) {
          if (ScenarioExt.getContainer(scenario) == headContainer && scenario.getKind().equals(ScenarioKind.INTERFACE))
            scenarios.add(scenario);
        }
      } else {
        scenarios.addAll(scList);
      }
    }

    if (scenarios.size() > 1) {
      List<NamedElement> elts = new ArrayList<NamedElement>(scenarios);
      scenarios.clear();

      String message = "Select the scenario(s) to be refined."; //$NON-NLS-1$
      TargetSelectionItem rootItem = new TargetSelectionItem(groupByContainer(elts,
          InteractionPackage.Literals.ABSTRACT_CAPABILITY));
      SelectionWizard wizard = new SelectionWizard(rootItem,
          "Capella wizard", "Scenario selection", message, true, true); //$NON-NLS-1$ //$NON-NLS-2$
      wizard.selectAllByDefault(true);

      if (wizard.open() == 0) {
        for (SelectionItemNode item : wizard.getSelectionList()) {
          Object data = item.getData();
          if ((data != null) && !scenarios.contains(data)) {
            scenarios.add((Scenario) data);
          }
        }
      }
    }

    fillInTargets(scenarios);
  }

  /**
   * @param elts
   *          the list to be split depending on the container
   * @param cls
   */
  private Map<NamedElement, List<NamedElement>> groupByContainer(List<NamedElement> elts, EClass cls) {
    Map<NamedElement, List<NamedElement>> result = new HashMap<NamedElement, List<NamedElement>>();

    for (NamedElement elt : elts) {
      EObject container = EcoreUtil2.getFirstContainer(elt, cls);
      if (!result.containsKey(container)) {
        List<NamedElement> lst = new ArrayList<NamedElement>();
        lst.add(elt);
        result.put((NamedElement) container, lst);
      } else {
        result.get(container).add(elt);
      }
    }

    return result;
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#getName()
   */
  public Object getName() {
    return ""; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#getResult()
   */
  public Object getResult() {
    return null;
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(java.util.List)
   * 
   * @param context
   */
  public void setContext(List<ModelElement> context) {
    /** do nothing */
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(org.polarsys.capella.core.common.model.CapellaElement)
   * 
   * @param context
   */
  public void setContext(ModelElement context) {
    /** do nothing */
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setTarget(org.polarsys.capella.core.common.model.NamedElement)
   * 
   * @param target
   */
  public void setTarget(NamedElement target) {
    /** do nothing */
  }

  /**
   * @throws {@link ProcessorException}
   */
  public void execute() throws ProcessorException {
    execute(new NullProgressMonitor());
  }

  /**
   * @param progressMonitor
   * @throws {@link ProcessorException}
   */
  public void execute(IProgressMonitor progressMonitor) throws ProcessorException {
    for (IProcessor refinement : _refinements) {
      refinement.execute(progressMonitor);
    }
  }

  /**
   * @param srcDiagramset
   */
  private void fillInTargets(List<Scenario> srcDiagramset) {
    Map<NamedElement, List<NamedElement>> targetMap = evaluateTarget(srcDiagramset);

    for (NamedElement scenario : targetMap.keySet()) {
      List<NamedElement> targetList = targetMap.get(scenario);
      for (NamedElement target : targetList) {
        if (target instanceof ComponentArchitecture) {
          if ((target instanceof PhysicalArchitecture) || (target instanceof EPBSArchitecture)) {
            _refinements.add(new RefinementMerge((Scenario) scenario, (ComponentArchitecture) target));
          } else {
            _refinements.add(new RefinementTargeted((Scenario) scenario, (ComponentArchitecture) target));
          }
        } else if (target instanceof Component) {
          _refinements.add(new RefinementTargeted((Scenario) scenario, (Component) target));
        } else if (target instanceof Part) {
          _refinements.add(new RefinementTargeted((Scenario) scenario, (Part) target));
        }
      }
    }
  }

  /**
   * @param scenarios
   */
  protected Map<NamedElement, List<NamedElement>> evaluateTarget(List<Scenario> scenarios) {
    Map<NamedElement, List<NamedElement>> tmpTargets = new HashMap<NamedElement, List<NamedElement>>();

    for (Scenario scenario : scenarios) {
      List<NamedElement> targetSet = new ArrayList<NamedElement>();
      CapellaElement diagramContainer = ScenarioExt.getContainer(scenario);

      if (diagramContainer instanceof SystemEngineering) {
        for (LogicalArchitecture la : SystemEngineeringExt.getAllLogicalArchitecture(scenario)) {
          if (!targetSet.contains(la)) {
            targetSet.add(la);
          }
        }
      } else if (diagramContainer instanceof LogicalArchitecture) {
        /** The logical components involved in Root LC decomposition are added */
        LogicalComponent rootLc = SystemEngineeringExt.getRootLogicalComponent((LogicalArchitecture) diagramContainer);
        for (Part part : ComponentExt.getDecompositionPartInvolved(rootLc)) {
          LogicalComponent lc = (LogicalComponent) part.getAbstractType();
          if (ComponentExt.isComposite(lc) && ScenarioExt.contains(scenario, part)) {
            if (lc.getOwnedLogicalArchitectures().size() == 0) {
              if (!targetSet.contains(lc)) {
                targetSet.add(lc);
              }
            } else {
              for (LogicalArchitecture decomposition : lc.getOwnedLogicalArchitectures()) {
                if (!targetSet.contains(decomposition)) {
                  targetSet.add(decomposition);
                }
              }
            }
          }
        }

        /** the physical architecture is added */
        PhysicalArchitecture pa = SystemEngineeringExt.getPhysicalArchitecture(scenario);
        if ((pa != null) && !targetSet.contains(pa)) {
          targetSet.add(pa);
        }
      } else if (diagramContainer instanceof LogicalComponent) {
        /** the level N+1 logical components are added */
        for (Part part : ComponentExt.getDecompositionPartInvolved((LogicalComponent) diagramContainer)) {
          LogicalComponent lc = (LogicalComponent) part.getAbstractType();
          if (ComponentExt.isComposite(lc) && ScenarioExt.contains(scenario, part)) {
            if (lc.getOwnedLogicalArchitectures().size() == 0) {
              if (!targetSet.contains(part)) {
                targetSet.add(part);
              }
            } else {
              for (LogicalArchitecture decomposition : lc.getOwnedLogicalArchitectures()) {
                if (!targetSet.contains(decomposition)) {
                  targetSet.add(decomposition);
                }
              }
            }
          }
        }
      } else if (diagramContainer instanceof PhysicalArchitecture) {
        /** The physical components involved in Root PC decomposition are added */
        PhysicalComponent rootPc = SystemEngineeringExt
            .getRootPhysicalComponent((PhysicalArchitecture) diagramContainer);
        for (Part part : ComponentExt.getDecompositionPartInvolved(rootPc)) {
          PhysicalComponent pc = (PhysicalComponent) part.getAbstractType();
          if (ComponentExt.isComposite(pc) && ScenarioExt.contains(scenario, part)) {
            if (!targetSet.contains(pc)) {
              targetSet.add(pc);
            }
          }
        }

        /** the EPBS architecture is added */
        EPBSArchitecture epbs = SystemEngineeringExt.getEPBSArchitecture(scenario);
        if ((epbs != null) && !targetSet.contains(epbs)) {
          targetSet.add(epbs);
        }
      } else if (diagramContainer instanceof PhysicalComponent) {
        /** the level N+1 physical components are added */
        for (Part part : ComponentExt.getDecompositionPartInvolved((PhysicalComponent) diagramContainer)) {
          PhysicalComponent pc = (PhysicalComponent) part.getAbstractType();
          if (ComponentExt.isComposite(pc) && ScenarioExt.contains(scenario, part)) {
            if (!targetSet.contains(part)) {
              targetSet.add(part);
            }
          }
        }
      }

      tmpTargets.put(scenario, targetSet);
    }

    /** a selection wizard is shown when there are several possibilities */
    return selectTarget(tmpTargets);
  }

  /**
   * @param allTargets
   */
  protected Map<NamedElement, List<NamedElement>> selectTarget(Map<NamedElement, List<NamedElement>> allTargets) {
    Map<NamedElement, List<NamedElement>> finalTargets = new HashMap<NamedElement, List<NamedElement>>();

    if (!choiceIsNeeded(allTargets)) {
      finalTargets = allTargets;
    } else {
      String message = "Select the target(s) on which scenario refinement must be launched."; //$NON-NLS-1$
      TargetSelectionItem rootItem = new TargetSelectionItem(allTargets);
      SelectionWizard wizard = new SelectionWizard(rootItem,
          "Capella wizard", "Refinement target selection", message, true, false); //$NON-NLS-1$ //$NON-NLS-2$
      if (wizard.open() == 0) {
        for (SelectionItemNode item : wizard.getSelectionList()) {
          Object src = item.getSrc();
          Object data = item.getData();

          if (!finalTargets.containsKey(src)) {
            finalTargets.put((Scenario) src, new ArrayList<NamedElement>());
          }
          if (data != null) {
            finalTargets.get(src).add((NamedElement) data);
          }
        }
      }
    }

    return finalTargets;
  }

  /**
   * @param allTargets
   */
  private boolean choiceIsNeeded(Map<NamedElement, List<NamedElement>> allTargets) {
    for (NamedElement tgt : allTargets.keySet()) {
      if (allTargets.get(tgt).size() > 1) {
        return true;
      }
    }
    return false;
  }
}
