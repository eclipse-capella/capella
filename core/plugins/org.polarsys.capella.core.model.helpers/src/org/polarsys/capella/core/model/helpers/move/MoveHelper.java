/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.helpers.move;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.FinalState;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceAllocation;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ComponentPkgExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.helpers.StateExt;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;

/**
 * This class checks if a list of elements can be moved into the target element
 */
public class MoveHelper {

  private static MoveHelper instance;

  public static MoveHelper getInstance() {
    if (instance == null) {
      instance = new MoveHelper();
    }
    return instance;
  }

  /**
   * @param selectedModelElements
   * @param inputTargetElement
   * @return
   */
  public IStatus checkSemanticRules(List<EObject> selectedElements, EObject targetObject) {
    boolean isOK = true;

    if (!(targetObject instanceof ModelElement)) {
      // There are no semantic rules below for non ModelElement
      return Status.OK_STATUS;
    }

    ModelElement target = (ModelElement) targetObject;

    for (EObject selectedElement : selectedElements) {
      if (selectedElement instanceof ModelElement) {

        // There are no semantic rules below for non ModelElement
        ModelElement source = (ModelElement) selectedElement;

        if (source instanceof AbstractFunction) {
          if (target instanceof FunctionPkg) {
            isOK = !(target.eContainer() instanceof BlockArchitecture) && areInSameLayer(source, target);

          } else if (target instanceof AbstractFunction) {
            isOK = areInSameLayer(source, target);
          }

        } else if (source instanceof FunctionPkg) {

          if (target instanceof FunctionPkg) {
            isOK = !(target.eContainer() instanceof BlockArchitecture) && areInSameLayer(source, target);

          } else if (target instanceof EPBSArchitecture) {
            isOK = false;
          }

        } else if (source instanceof Component) {
          isOK = areInSameLayer(source, target) && ComponentExt.canMoveInto((Component) source, target);

        } else if (source instanceof Part) {
          isOK = areInSameLayer(source, target) && PartExt.canMoveInto((Part) source, target);

        } else if (source instanceof ComponentPkg) {
          isOK = areInSameLayer(source, target) && ComponentPkgExt.canMoveInto((ComponentPkg) source, target);

        } else if (source instanceof AbstractCapabilityPkg || source instanceof AbstractCapability) {
          BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(target);

          if (source instanceof OperationalCapabilityPkg || source instanceof OperationalCapability) {
            isOK = architecture instanceof OperationalAnalysis;

          } else if (source instanceof CapabilityPkg || source instanceof Capability) {
            isOK = architecture instanceof SystemAnalysis;

          } else if (source instanceof CapabilityRealizationPkg || source instanceof CapabilityRealization) {
            isOK = architecture instanceof LogicalArchitecture || architecture instanceof PhysicalArchitecture
                || architecture instanceof EPBSArchitecture;
          }

        } else if (source instanceof Interface && (target instanceof InterfacePkg || target instanceof Interface)) {
          isOK = isLegalInterfaceMode((Interface) source, target);

        } else if (source instanceof InterfacePkg) {
          isOK = !(target instanceof EPBSArchitecture);

        } else if (source instanceof EnumerationLiteral) {
          isOK = target instanceof Enumeration;

        } else if (source instanceof State) {
          // If elt is a Mode or a State
          if (!(target instanceof Region)) {
            isOK = false;

          } else {
            isOK = canMoveModeState((State) source, (Region) target);
          }

        } else if (source instanceof LiteralBooleanValue) {
          isOK = target instanceof BooleanType;

        } else if (source instanceof FunctionalChainInvolvement) {
          isOK = false;

        } else if (source instanceof FunctionalChain) {
          // Involved elements shall be in the same level than targetElement
          isOK = ((FunctionalChain) source).getInvolvedElements().stream().noneMatch(x -> !areInSameLayer(x, target));
        }

        if (!isOK) {
          // We should explain why !
          return new Status(IStatus.ERROR, "model.helpers", "Semantic rules failed.");
        }
      }
    }

    return Status.OK_STATUS;

  }

  /**
   * @param elt
   * @param targetElement
   * @return
   */
  protected boolean isLegalInterfaceMode(Interface interf, EObject targetElement) {
    ModellingArchitecture iArchi = BlockArchitectureExt.getRootBlockArchitecture(targetElement);
    for (Component component : interf.getUserComponents()) {
      ModellingArchitecture cArchi = BlockArchitectureExt.getRootBlockArchitecture(component);
      if (!CapellaElementExt.isLegalArchitecture(iArchi, cArchi)) {
        return false;
      }
    }
    for (Component component : interf.getImplementorComponents()) {
      ModellingArchitecture cArchi = BlockArchitectureExt.getRootBlockArchitecture(component);
      if (!CapellaElementExt.isLegalArchitecture(iArchi, cArchi)) {
        return false;
      }
    }

    // via component ports
    for (Component component : InterfaceExt.getRequireComponent(interf)) {
      ModellingArchitecture cArchi = BlockArchitectureExt.getRootBlockArchitecture(component);
      if (!CapellaElementExt.isLegalArchitecture(iArchi, cArchi)) {
        return false;
      }
    }
    for (Component component : InterfaceExt.getProviderComponent(interf)) {
      ModellingArchitecture cArchi = BlockArchitectureExt.getRootBlockArchitecture(component);
      if (!CapellaElementExt.isLegalArchitecture(iArchi, cArchi)) {
        return false;
      }
    }

    // if the interface is transited, it's false
    List<AbstractTrace> traces = new ArrayList<AbstractTrace>();
    traces.addAll(interf.getIncomingTraces());
    traces.addAll(interf.getOutgoingTraces());

    for (AbstractTrace abstractTrace : traces) {
      if (abstractTrace instanceof InterfaceAllocation) {
        return false;
      }
    }

    return true;
  }

  /**
   * Checks if the specified two capella elements are is in the same layer.
   * 
   * @param element1
   *          The first element.
   * @param element2
   *          The second element
   * @return <code>True</code> if they are in the same layer else <code>false</code>.
   */
  protected boolean areInSameLayer(ModelElement element1, ModelElement element2) {
    BlockArchitecture arch1 = BlockArchitectureExt.getRootBlockArchitecture(element1);
    BlockArchitecture arch2 = BlockArchitectureExt.getRootBlockArchitecture(element2);

    if (arch1 == arch2) {
      return true;
    }
    if (arch1 == null) {
      // arch1 can be null when the object is a copy (clipboard) and have
      // no parent yet
      EPackage pkg = element1.eClass().getEPackage();
      if ((pkg.equals(OaPackage.eINSTANCE) && (arch2 instanceof OperationalAnalysis))
          || (pkg.equals(CtxPackage.eINSTANCE) && (arch2 instanceof SystemAnalysis))
          || (pkg.equals(LaPackage.eINSTANCE) && (arch2 instanceof LogicalArchitecture))
          || (pkg.equals(PaPackage.eINSTANCE) && (arch2 instanceof PhysicalArchitecture))
          || (pkg.equals(EpbsPackage.eINSTANCE) && (arch2 instanceof EPBSArchitecture))) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param selectedModelElements
   * @param targetElement
   */
  public IStatus checkEMFRules(List<EObject> selectedModelElements, EObject targetElement) {
    IStatus result = Status.OK_STATUS;

    // 1. We are in a single editing domain
    TransactionalEditingDomain targetDomain = TransactionUtil.getEditingDomain(targetElement);
    if (targetDomain == null) {
      result = Status.CANCEL_STATUS;
    } else {
      for (EObject e : selectedModelElements) {
        TransactionalEditingDomain sourceDomain = TransactionUtil.getEditingDomain(e);
        if (sourceDomain != null && sourceDomain != targetDomain) {
          result = Status.CANCEL_STATUS;
        }
      }
    }

    // 2. For every dropped object there must be a reference that will contain the dropped object
    if (result.isOK()) {
      EList<EReference> allReferences = targetElement.eClass().getEAllContainments();
      if (!checkCompatibility(selectedModelElements, allReferences, targetElement)) {
        result = Status.CANCEL_STATUS;
      }
    }

    return result;
  }

  /**
   * Checks the compatibility between all specified references and all specified model elements.
   */
  protected boolean checkCompatibility(List<EObject> modelElements, EList<EReference> references, EObject target) {
    boolean areCompatible = true;

    Iterator<EObject> elementsIterator = modelElements.iterator();
    while (elementsIterator.hasNext() && areCompatible) {
      EObject modelElement = elementsIterator.next();

      boolean isElementCompatible = false;
      Iterator<EReference> referencesIterator = references.iterator();
      while (referencesIterator.hasNext() && !isElementCompatible) {
        EReference reference = referencesIterator.next();
        if (reference.getEType().isInstance(modelElement) && (reference != modelElement)) {
          Integer upperBound = reference.getUpperBound();
          if (upperBound == -1
              || (upperBound == 1 && target.eGet(reference) == null) && modelElements.size() <= upperBound) {
            isElementCompatible = true;
          } else if (upperBound > 1) {
            @SuppressWarnings("unchecked")
            EObjectContainmentEList<EObject> contList = (EObjectContainmentEList<EObject>) target.eGet(reference);
            if (contList.size() < upperBound) {
              isElementCompatible = true;
            }
          }
        }
      }
      areCompatible &= isElementCompatible;
    }
    return areCompatible;
  }

  /**
   * Depending on mixed hierarchy mode state preference, determine if we can move a mode into a region of state and vice
   * versa
   * 
   * @param source
   * @param target
   * @return
   */
  public boolean canMoveModeState(State source, Region targetElement) {
    boolean result = true;

    if (targetElement.eContainer() != null && !(source instanceof FinalState)) {
      EObject targetContainer = targetElement.eContainer();

      if (!CapellaModelPreferencesPlugin.getDefault().isMixedModeStateAllowed()) {
        boolean isSameType = true;
        // Check source/target type compatibility if target is a Mode/State
        if (targetContainer instanceof State) {
          isSameType = targetContainer.eClass() == source.eClass();

        } else if (targetContainer instanceof StateMachine) {
          List<State> states = getAllModeState(((StateMachine) targetContainer).getOwnedRegions().get(0));
          isSameType = !states.isEmpty() ? states.get(0).eClass() == source.eClass() : true;
        }
        // Move is allowed only when source and target are not mixed
        return isSameType && !isDownwardModeStateHierarchyMixed(source) && !isModeStateHierarchyMixed(targetContainer);
      }
    }
    return result;
  }

  /**
   * This method tests if the State/Mode hierarchy of a StateMachine/State/Mode is mixed or not
   * 
   * @param container
   * @return
   */
  public static boolean isModeStateHierarchyMixed(EObject container) {
    // Get a list of State/Mode
    List<State> stateModeLst = getModeStateHierarchy(container);
    if (stateModeLst.size() <= 1)
      return false;

    for (int i = 0; i < stateModeLst.size() - 1; i++) {
      if (stateModeLst.get(i).eClass() != stateModeLst.get(i + 1).eClass())
        return true;
    }

    return false;
  }

  /**
   * This method tests if the State/Mode hierarchy of a StateMachine/State/Mode is mixed or not
   * 
   * @param container
   * @return
   */
  public static boolean isDownwardModeStateHierarchyMixed(State inputState) {
    // Get a list of State/Mode
    List<State> stateModeLst = getDownwardModeStateHierarchy(inputState);

    for (State state : stateModeLst) {
      if (state.eClass() != inputState.eClass()) {
        return true;
      }
    }

    return false;
  }

  /**
   * This method returns all modes/states in a Mode/State/StateMachine's hierarchy
   * 
   * @param container
   * @return
   */
  public static List<State> getModeStateHierarchy(EObject container) {
    List<State> stateModeLst = new ArrayList<>();
    if (container instanceof State) {
      // Add contained modes/states
      Iterator<EObject> iter = EcoreUtil.getAllContents(container, true);

      while (iter.hasNext()) {
        EObject eObj = iter.next();
        if (StateExt.isStrictModeState(eObj)) {
          stateModeLst.add((State) eObj);
        }
      }

      // Add itself
      stateModeLst.add((State) container);

      // Add parent modes/states
      EObject parentState = EcoreUtil2.getFirstContainer(container, CapellacommonPackage.eINSTANCE.getState());
      while (parentState != null) {
        stateModeLst.add((State) parentState);
        parentState = EcoreUtil2.getFirstContainer(parentState, CapellacommonPackage.eINSTANCE.getState());
      }

    } else if (container instanceof StateMachine) {
      // Add contained modes/states only
      Iterator<EObject> iter = EcoreUtil.getAllContents(container, true);

      while (iter.hasNext()) {
        EObject eObj = iter.next();
        if (StateExt.isStrictModeState(eObj)) {
          stateModeLst.add((State) eObj);
        }
      }
    }
    return stateModeLst;
  }

  /**
   * This method returns all Modes/States in a Mode/State's hierarchy
   * 
   * @param container
   * @return
   */
  public static List<State> getDownwardModeStateHierarchy(State state) {
    List<State> stateModeLst = new ArrayList<>();
    // Add contained modes/states
    Iterator<EObject> iter = EcoreUtil.getAllContents(state, true);

    while (iter.hasNext()) {
      EObject eObj = iter.next();
      if (StateExt.isStrictModeState(eObj)) {
        stateModeLst.add((State) eObj);
      }
    }

    // Add itself
    stateModeLst.add(state);
    return stateModeLst;
  }

  /**
   * This method returns all modes/states from a region
   * 
   * @param region
   * @return list of Modes/States
   */
  public static List<State> getAllModeState(Region region) {
    List<State> stateModeLst = new ArrayList<>();
    // Add contained modes/states only
    Iterator<EObject> iter = EcoreUtil.getAllContents(region, true);

    while (iter.hasNext()) {
      EObject eObj = iter.next();
      if (StateExt.isStrictModeState(eObj)) {
        stateModeLst.add((State) eObj);
      }
    }

    return stateModeLst;
  }
}
