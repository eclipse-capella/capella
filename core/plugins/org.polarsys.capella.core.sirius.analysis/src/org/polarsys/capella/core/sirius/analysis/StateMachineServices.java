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
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacommon.DeepHistoryPseudoState;
import org.polarsys.capella.core.data.capellacommon.EntryPointPseudoState;
import org.polarsys.capella.core.data.capellacommon.ExitPointPseudoState;
import org.polarsys.capella.core.data.capellacommon.FinalState;
import org.polarsys.capella.core.data.capellacommon.ForkPseudoState;
import org.polarsys.capella.core.data.capellacommon.InitialPseudoState;
import org.polarsys.capella.core.data.capellacommon.JoinPseudoState;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.Pseudostate;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.ShallowHistoryPseudoState;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacommon.TerminatePseudoState;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.ModellingBlock;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityPkgExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.StateExt;
import org.polarsys.capella.core.model.helpers.move.MoveHelper;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.showhide.AbstractShowHide.DiagramContext;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideSMStateMode;
import org.polarsys.capella.core.sirius.analysis.showhide.ShowHideSMTransitions;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

/**
 * Services for States machines.
 */
public class StateMachineServices {

  private static final String CHAR_X = "X"; //$NON-NLS-1$
  /** A shared instance. */
  private static StateMachineServices _service;

  /**
   * returns a shared instance of this services.
   * 
   * @return a shared instance of this services.
   */
  public static StateMachineServices getService() {
    if (_service == null) {
      _service = new StateMachineServices();
    }
    return _service;
  }

  /**
   * @used in common.odesign Return all the modes and states from current BlockArchitecture
   * @param context
   *          : diagram context element
   * @param arch
   * @return list of modes and states
   */
  public List<EObject> getAllStatesAndModesFromBlockArchitecture(EObject context, BlockArchitecture arch) {
    return BlockArchitectureExt.getAllStatesAndModes(arch);
  }

  /**
   * get all the States and Modes from current Component
   * 
   * @param context
   * @param comp
   * @return list of states
   */
  public List<CapellaElement> getAllStatesAndModesFromComponent(EObject context, Component comp) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();

    result.addAll(ComponentExt.getAllStatesAndModesFromComponent(comp));

    return result;
  }

  /**
   * @used common.odesign decides weather to display char x or not
   * @param context
   *          : {@link AbstractFunction} or {@link AbstractCapability} or {@link FunctionalChain}
   * @param colomn
   *          : {@link AbstractStateMode}
   * @return String
   */
  public String displayCharX(EObject context, EObject colomn) {
    if ((null != context) && (null != colomn)) {
      if (context instanceof AbstractCapability) {
        AbstractCapability cap = (AbstractCapability) context;
        if (cap.getAvailableInStates().contains(colomn)) {
          return CHAR_X;
        }
      } else if (context instanceof AbstractFunction) {
        AbstractFunction cap = (AbstractFunction) context;
        if (cap.getAvailableInStates().contains(colomn)) {
          return CHAR_X;
        }
      } else if (context instanceof FunctionalChain) {
        FunctionalChain cap = (FunctionalChain) context;
        if (cap.getAvailableInStates().contains(colomn)) {
          return CHAR_X;
        }
      } else if (context instanceof IState) {
        IState state = (IState) context;
        if (state.getReferencedStates().contains(colomn)) {
          return CHAR_X;
        }
      }

    }
    return ICommonConstants.EMPTY_STRING;
  }

  /**
   * @used common.odesign remove state from given context
   * @param context
   *          : {@link AbstractFunction} or {@link AbstractCapability} or {@link FunctionalChain}
   * @param colomn
   *          : {@link AbstractStateMode}
   */
  public void inActiveAnElement(EObject context, EObject colomn) {
    if ((null != context) && (null != colomn) && (colomn instanceof State)) {
      if (context instanceof AbstractCapability) {
        AbstractCapability ele = (AbstractCapability) context;
        EList<State> availableInStates = ele.getAvailableInStates();
        if (!availableInStates.isEmpty()) {
          availableInStates.remove(colomn);
        }
      } else if (context instanceof AbstractFunction) {
        AbstractFunction ele = (AbstractFunction) context;
        EList<State> availableInStates = ele.getAvailableInStates();
        if (!availableInStates.isEmpty()) {
          availableInStates.remove(colomn);
        }
      } else if (context instanceof FunctionalChain) {
        FunctionalChain ele = (FunctionalChain) context;
        EList<State> availableInStates = ele.getAvailableInStates();
        if (!availableInStates.isEmpty()) {
          availableInStates.remove(colomn);
        }
      }

    }
  }

  /**
   * @used common.odesign add state in the given context
   * @param context
   *          : {@link AbstractFunction} or {@link AbstractCapability} or {@link FunctionalChain}
   * @param colomn
   *          : {@link State}
   */
  public void activeAnElement(EObject context, EObject colomn) {
    if ((null != context) && (null != colomn) && (colomn instanceof State)) {
      if (context instanceof AbstractCapability) {
        AbstractCapability ele = (AbstractCapability) context;
        EList<State> availableInStates = ele.getAvailableInStates();
        if (null != availableInStates) {
          availableInStates.add((State) colomn);
        }
      } else if (context instanceof AbstractFunction) {
        AbstractFunction ele = (AbstractFunction) context;
        EList<State> availableInStates = ele.getAvailableInStates();
        if (null != availableInStates) {
          availableInStates.add((State) colomn);
        }
      } else if (context instanceof FunctionalChain) {
        FunctionalChain ele = (FunctionalChain) context;
        EList<State> availableInStates = ele.getAvailableInStates();
        if (null != availableInStates) {
          availableInStates.add((State) colomn);
        }
      }

    }
  }

  /**
   * Return functions active in current state or mode
   * 
   * @param context
   * @param ele
   * @return
   */
  public List<EObject> getAllFunctionsActiveInStates(EObject context, IState ele) {
    List<EObject> referencers = EObjectExt.getReferencers(ele, FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES);
    return referencers;
  }

  /**
   * Return AbstractCapability chains active in current state or mode
   * 
   * @param context
   * @param ele
   * @return
   */
  public List<EObject> getAllCapabilitiesActiveInStates(EObject context, State ele) {
    List<EObject> referencers = EObjectExt.getReferencers(ele, InteractionPackage.Literals.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES);
    return referencers;
  }

  /**
   * Return functional chains active in current state or mode
   * 
   * @param context
   * @param ele
   * @return
   */
  public List<EObject> getAllFunctionalChainsActiveInStates(EObject context, IState ele) {
    List<EObject> referencers = EObjectExt.getReferencers(ele, FaPackage.Literals.FUNCTIONAL_CHAIN__AVAILABLE_IN_STATES);
    return referencers;
  }

  /**
   * Return available in state values of (function or functionalChain or abstractCapability)
   * 
   * @param context
   * @return list of AbstractStateMode
   */
  public EList<State> getAvailableStateAndMode(EObject context) {
    if (null != context) {
      if (context instanceof FunctionalChain) {
        FunctionalChain ele = (FunctionalChain) context;
        return ele.getAvailableInStates();
      } else if (context instanceof AbstractFunction) {
        AbstractFunction ele = (AbstractFunction) context;
        return ele.getAvailableInStates();
      } else if (context instanceof AbstractCapability) {
        AbstractCapability ele = (AbstractCapability) context;
        return ele.getAvailableInStates();
      }
    }
    return null;
  }

  public List<IState> getInvolvedStatesRecursively(EObject context) {
    List<IState> result = new ArrayList<IState>();

    if (context instanceof StateMachine) {
      for (Region subregion : ((StateMachine) context).getOwnedRegions()) {
        result.addAll(getInvolvedStatesRecursively(subregion));
      }
    }
    if (context instanceof Region) {
      Region region = (Region) context;
      for (IState state : region.getInvolvedStates()) {
        if (state instanceof Pseudostate) {
          result.add(state);
        }
        if (state instanceof State) {
          State asm = (State) state;
          result.add(asm);
          // recurse
          for (Region subregion : asm.getOwnedRegions()) {
            result.addAll(getInvolvedStatesRecursively(subregion));
          }
        }
      }
    }
    if (context instanceof State) {
      State asm = (State) context;
      // recurse
      for (Region subregion : asm.getOwnedRegions()) {
        List<IState> subRegionStates = getInvolvedStatesRecursively(subregion);
        result.addAll(subRegionStates);
      }
    }

    return result;
  }

  /**
   * Return the list of states (and modes) that can be referenced inside a region. Note that you cannot reference one of
   * the parent state of containerState (including itself).
   * 
   * @param containerState
   * @return
   */
  public List<IState> getReferencableStates(EObject containerState) {
    List<IState> result = new ArrayList<IState>();
    EObject container = containerState.eContainer();
    while (!(container instanceof Block)) {
      container = container.eContainer();
    }

    Region currentRegion = null;
    if (containerState instanceof Region) {
      currentRegion = (Region) containerState;
    } else {
      currentRegion = ((State) containerState).getOwnedRegions().get(0);
    }

    TreeIterator<EObject> iter = currentRegion.eAllContents();
    IState referenceState = null;
    boolean hasShallowHystoryPseudoState = false;
    boolean hasDeepHystoryPseudoState = false;
    while (iter.hasNext()) {
      EObject type = iter.next();
      if ((type instanceof Mode) || (type instanceof State)) {
        referenceState = (IState) type;
      }
      if (type instanceof ShallowHistoryPseudoState) {
        hasShallowHystoryPseudoState = true;
      }
      if (type instanceof DeepHistoryPseudoState) {
        hasDeepHystoryPseudoState = true;
      }
    }

    /* building the parentExclusionList */
    Set<State> parentExclusionList = new HashSet<State>();
    EObject currentParent = containerState;
    while (!(currentParent instanceof StateMachine)) {
      if (currentParent instanceof State) {
        parentExclusionList.add((State) currentParent);
      }
      currentParent = currentParent.eContainer();
    }

    Block block = (Block) container;
    for (StateMachine sm : block.getOwnedStateMachines()) {
      TreeIterator<EObject> it = sm.eAllContents();
      while (it.hasNext()) {
        EObject type = it.next();
        if ((type instanceof IState) && (!parentExclusionList.contains(type))) {
          // Referencing the PseudoStatesif they have sibling of the same "type" of the reference state.
          if ((type instanceof Pseudostate) && !(type instanceof ShallowHistoryPseudoState)
              && !(type instanceof DeepHistoryPseudoState)) {
            result.add((IState) type);
          }
          if ((type instanceof ShallowHistoryPseudoState) && !hasShallowHystoryPseudoState) {
            result.add((IState) type);
          }
          if ((type instanceof DeepHistoryPseudoState) && !hasDeepHystoryPseudoState) {
            result.add((IState) type);
          }
          if (((referenceState == null) || (referenceState.eClass() == type.eClass())) && !(type instanceof Pseudostate)
              && (type instanceof State ? MoveHelper.getInstance().canMoveModeState((State) type, currentRegion)
                  : true)) {
            result.add((IState) type);
          }
        }
      }
    }
    return result;
  }

  /**
   * @deprecated
   * @param type
   * @param referenceState
   * @return
   */
  @Deprecated
  private boolean isPseudoStateCompatible(Pseudostate type, IState referenceState) {
    Region region = (Region) type.eContainer();
    if (referenceState == null) {
      return true;
    }
    for (IState currentState : region.getInvolvedStates()) {
      if (currentState instanceof State) {
        State state = (State) currentState;
        return referenceState.eClass() == state.eClass();
      }
    }
    return true;
  }

  public List<IState> getAllReferencedStatesInSM(StateMachine sm) {
    List<IState> result = new ArrayList<IState>();
    for (Region r : sm.getOwnedRegions()) {
      for (IState state : r.getOwnedStates()) {
        result.addAll(getAllReferencedStates(state));
      }
    }
    return result;
  }

  private List<IState> getAllReferencedStates(IState s) {
    List<IState> result = new ArrayList<IState>();
    result.add(s);
    for (IState iState : s.getReferencedStates()) {
      result.addAll(getAllReferencedStates(iState));
    }
    return result;
  }

  public List<IState> getStateReferenced(EObject context) {
    IState lineState = (IState) context;
    return lineState.getReferencedStates();
  }

  public boolean hasStateMachine(Component comp) {
    if (comp.getOwnedStateMachines().size() > 0) {
      return true;
    }
    Collection<Component> components = ComponentExt.getAllSubUsedComponents(comp);
    for (Component component : components) {
      if (component.getOwnedStateMachines().size() > 0) {
        return true;
      }
    }
    return false;
  }

  public List<Component> getSubComponentsWithStateMachine(EObject context) {
    List<Component> result = new ArrayList<Component>();
    if (context instanceof Component) {
      Component component = (Component) context;
      for (Component sub : ComponentExt.getSubDefinedComponents(component)) {
        if (hasStateMachine(sub)) {
          result.add(sub);
        }
      }
    }
    return result;
  }

  public List<AbstractCapability> getCapabilitiesForStateMatrix(ModellingBlock block) {
    List<AbstractCapability> result = new ArrayList<AbstractCapability>();
    EObject container = block.eContainer();
    if (container instanceof BlockArchitecture) {
      BlockArchitecture archi = (BlockArchitecture) container;
      return getCapabilitiesForStateMatrix(archi);
    }
    // capability involving the modellingblock
    if (block instanceof Component && block instanceof InvolvedElement) {
      Component component = (Component) block;
      for (Involvement involve : ((InvolvedElement) block).getInvolvingInvolvements()) {
        InvolverElement involver = involve.getInvolver();
        if (involver instanceof AbstractCapability) {
          AbstractCapability ac = (AbstractCapability) involver;
          result.add(ac);
        }
      }
      // recursing
      for (Component subComponent : ComponentExt.getSubDefinedComponents(component)) {
        result.addAll(getCapabilitiesForStateMatrix(subComponent));
      }

    }
    return result;
  }

  public List<AbstractCapability> getCapabilitiesForStateMatrix(BlockArchitecture archi) {
    List<AbstractCapability> result = new ArrayList<AbstractCapability>();
    // for modelling architecture, i need first level capabilities
    AbstractCapabilityPkg acp = archi.getOwnedAbstractCapabilityPkg();
    result.addAll(AbstractCapabilityPkgExt.getAllAbstractCapabilities(acp));

    // recursing
    Component rootComponent = ComponentExt.getSubDefinedComponents(archi).get(0);
    for (Component subComponent : ComponentExt.getSubDefinedComponents(rootComponent)) {
      result.addAll(getCapabilitiesForStateMatrix(subComponent));
    }
    return result;
  }

  public List<AbstractFunction> getAbstractFunctionForStateModeMatrix(EObject obj) {
    if (obj instanceof Component) {
      return getAbstractFunctionForStateModeMatrix((Component) obj);
    }
    return getAbstractFunctionForStateModeMatrix(obj);
  }

  public List<AbstractFunction> getAbstractFunctionForStateModeMatrix(BlockArchitecture context) {
    Component component = ComponentExt.getSubDefinedComponents(context).get(0);
    return getAbstractFunctionForStateModeMatrix(component);
  }

  private List<AbstractFunction> getAbstractFunctionForStateModeMatrix(Component component) {
    List<AbstractFunction> result = new ArrayList<AbstractFunction>();
    // functions allocated by the components.
    result.addAll(component.getAllocatedFunctions());
    // recursing
    for (Component subComponent : ComponentExt.getSubDefinedComponents(component)) {
      result.addAll(getAbstractFunctionForStateModeMatrix(subComponent));
    }
    // Add the functions allocated in a mode or a state of the component.
    for (StateMachine sm : component.getOwnedStateMachines()) {
      for (IState state : getAllReferencedStatesInSM(sm)) {
        if (state instanceof State) {
          State asm = (State) state;
          for (EObject abstractFunction : getAllFunctionsActiveInStates(null, asm)) {
            if (abstractFunction instanceof AbstractFunction) {
              AbstractFunction af = (AbstractFunction) abstractFunction;
              result.add(af);
            }
          }
        }
      }
    }
    return result;
  }

  public List<AbstractCapability> getCapabilityFromStateAndMode(EObject context, IState state) {
    List<AbstractCapability> result = new ArrayList<AbstractCapability>();
    List<EObject> referencers = EObjectExt.getReferencers(state,
        InteractionPackage.Literals.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES);
    for (EObject eObject : referencers) {
      if (eObject instanceof AbstractCapability) {
        AbstractCapability ac = (AbstractCapability) eObject;
        result.add(ac);

      }
    }
    return result;
  }

  public static boolean isReferencedState(IState inputState, EObject container) {
    Region region;
    if (container != null) {

      if (container instanceof Region) {
        region = (Region) container;
      } else {
        State state = (State) container;
        region = state.getOwnedRegions().get(0);
      }

      return (!region.getOwnedStates().contains(inputState)) && region.getInvolvedStates().contains(inputState);
    }
    return false;
  }

  public Region getRegionForTransition(EObject context, DDiagramElement delement) {
    Region region = null;

    EObject container = delement.eContainer();
    if (container instanceof DDiagram) {
      region = (Region) ((DSemanticDiagram) container).getTarget();
    } else {
      EObject obj = ((DNodeContainer) container).getTarget();
      State state = (State) obj;
      return state.getOwnedRegions().get(0);
    }
    return region;
  }

  public Region getRegionForInsertion(EObject context, EObject delement) {
    Region region = null;

    if (delement instanceof DDiagram) {
      region = (Region) ((DSemanticDiagram) delement).getTarget();
    } else if (delement instanceof DDiagramElementContainer) {
      EObject obj = ((DNodeContainer) delement).getTarget();
      State state = (State) obj;
      return state.getOwnedRegions().get(0);
    }
    return region;
  }

  public List<IState> getInitialSelectionSM(EObject context) {
    List<IState> result = new ArrayList<IState>();
    if (context instanceof Region) {
      result.addAll(((Region) context).getInvolvedStates());
    } else {
      State state = (State) context;
      for (Region r : state.getOwnedRegions()) {
        result.addAll(r.getInvolvedStates());
      }
    }
    return result;
  }

  public boolean mustDisplayTransition(EObject context, EObject srcView, EObject tarView) {
    DDiagramElement sourceView = (DDiagramElement) srcView;
    DDiagramElement targetView = (DDiagramElement) tarView;

    DSemanticDecorator sourceContainer = (DSemanticDecorator) sourceView.eContainer();
    DSemanticDecorator targetContainer = (DSemanticDecorator) targetView.eContainer();
    EObject semanticContainer = sourceContainer.getTarget();
    EObject semanticTargetContainer = targetContainer.getTarget();

    boolean isCorrectContainer = false;
    boolean isCorrectTargetContainer = false;
    if (semanticContainer instanceof Region) {
      Region region = (Region) semanticContainer;
      isCorrectContainer = region.getOwnedTransitions().contains(context);
      if (semanticTargetContainer instanceof State) {
        isCorrectTargetContainer = (((State) semanticTargetContainer).getOwnedRegions().get(0).getOwnedStates()
            .contains(targetView.getTarget())) || (sourceContainer == targetContainer);
      } else if (semanticTargetContainer instanceof Region) {
        Region targetRegion = (Region) semanticTargetContainer;
        isCorrectTargetContainer = (targetRegion.getOwnedStates().contains(targetView.getTarget()))
            || (sourceContainer == targetContainer);
      }
    } else if (semanticContainer instanceof State) {
      State containerState = (State) semanticContainer;
      for (Region region : containerState.getOwnedRegions()) {
        if (region.getOwnedTransitions().contains(context)) {
          isCorrectContainer = true;
        }
      }
      if (semanticTargetContainer instanceof State) {
        isCorrectTargetContainer = (((State) semanticTargetContainer).getOwnedRegions().get(0).getOwnedStates()
            .contains(targetView.getTarget())) || (sourceContainer == targetContainer);
      } else if (semanticTargetContainer instanceof Region) {
        Region region = (Region) semanticTargetContainer;
        isCorrectTargetContainer = (region.getOwnedStates().contains(targetView.getTarget()))
            || (sourceContainer == targetContainer);
      }
    }
    return isCorrectContainer && isCorrectTargetContainer
        && isUndoublonLink((DSemanticDecorator) srcView, (DSemanticDecorator) tarView);
  }

  /**
   * Returns a linkedList of parents with the bottom-to-top order
   * 
   * @param clazz
   *          the eClass which will stop the browsing. (the eClass of the parent of the object which will not be
   *          included in the list)
   */
  protected LinkedList<EObject> getParents(EObject object, EClass clazz) {
    LinkedList<EObject> parents = new LinkedList<EObject>();
    EObject current = object;

    if (current == null) {
      return parents;
    }

    current = current.eContainer();

    while ((current != null) && !clazz.isInstance(current)) {
      parents.addLast(current);
      current = current.eContainer();
    }
    return parents;
  }

  /**
   * @param sourceElement
   * @param targetElement
   * @return
   */
  private boolean isUndoublonLink(DSemanticDecorator sourceElement, DSemanticDecorator targetElement) {

    boolean valid = true;

    // hide the edge if there is not the same node in the view of the part of the same type of the source
    LinkedList<EObject> sourceParents = getParents(sourceElement, DiagramPackage.Literals.DDIAGRAM);
    LinkedList<EObject> targetParents = getParents(targetElement, DiagramPackage.Literals.DDIAGRAM);

    sourceParents.remove(sourceElement);
    targetParents.remove(targetElement);

    // Retrieve the first parent of source which is not common with the target
    HashSet<EObject> targetParentSet = new HashSet<EObject>(targetParents);

    EObject current = null;
    if (!sourceParents.isEmpty()) {
      current = sourceParents.removeLast();
      while ((current != null) && targetParentSet.contains(current)) {
        if (sourceParents.size() > 0) {
          current = sourceParents.removeLast();
        } else {
          current = null;
        }
      }
    }
    if (current != null) {
      if (current instanceof DDiagramElement) {
        for (DSemanticDecorator child : DiagramServices.getDiagramServices()
            .getDiagramElements((DDiagramElement) current)) {
          if ((child.getTarget() != null) && child.getTarget().equals(targetElement.getTarget())) {
            valid = false;
          }
        }
      }
    } else {
      valid = true;
    }

    return valid;
  }

  /**
   * Mode creation tool precondition
   */
  public boolean canCreateMode(EObject context, EObject containerView) {
    Region testedRegion = getRegionFromView(containerView);
    if (testedRegion != null) {
      if (!CapellaModelPreferencesPlugin.getDefault().isMixedModeStateAllowed()) {
        return MoveHelper.getInstance().canMoveModeState(CapellacommonFactory.eINSTANCE.createMode(), testedRegion);
      }
      //If there is a State, then can't create a Mode
      return getStatesOfRegion(testedRegion).stream().noneMatch(StateExt::isStrictState);
    }
    return false;
  }

  /**
   * Deep history creation tool precondition
   */
  public boolean canCreateDeepHistory(EObject context, EObject containerView) {
    Region testedRegion = getRegionFromView(containerView);

    if ((testedRegion != null) && !(testedRegion.eContainer() instanceof StateMachine)) {
      for (AbstractState state : testedRegion.getOwnedStates()) {
        if (state instanceof DeepHistoryPseudoState) {
          return false;
        }
      }
      return true;
    }

    return false;
  }

  public boolean canCreateEntryExitPoint(EObject context, EObject containerView) {
    Region testedRegion = getRegionFromView(containerView);

    return (testedRegion != null) && !(testedRegion.eContainer() instanceof StateMachine);
  }

  /**
   * Shallow/Deep history creation tool precondition
   */
  public boolean canCreateShallowHistory(EObject context, EObject containerView) {
    Region testedRegion = getRegionFromView(containerView);

    if ((testedRegion != null) && !(testedRegion.eContainer() instanceof StateMachine)) {
      for (AbstractState state : testedRegion.getOwnedStates()) {
        if (state instanceof ShallowHistoryPseudoState) {
          return false;
        }
      }
      return true;
    }

    return false;
  }

  private Region getRegionFromView(EObject containerView) {
    Region testedRegion = null;
    if (containerView instanceof DSemanticDecorator) {
      DSemanticDecorator dd = (DSemanticDecorator) containerView;
      EObject testedElement = dd.getTarget();
      if (testedElement instanceof Region) {
        testedRegion = (Region) testedElement;
      } else if (testedElement instanceof State) {
        State state = (State) testedElement;
        testedRegion = StateExt.getPrimaryRegion(state, false);
      }
    }
    return testedRegion;
  }

  /**
   * Mode creation tool precondition
   */
  public boolean canCreateState(EObject context, EObject containerView) {
    Region testedRegion = getRegionFromView(containerView);
    if (testedRegion != null) {
      if (!CapellaModelPreferencesPlugin.getDefault().isMixedModeStateAllowed()) {
        return MoveHelper.getInstance().canMoveModeState(CapellacommonFactory.eINSTANCE.createState(), testedRegion);
      }

      //If there is a Mode, then can't create a State
      return getStatesOfRegion(testedRegion).stream().noneMatch(StateExt::isMode);
    }
    return false;
  }

  // Returns the list of the states appearing in a region; "owned" and "referenced".
  private Collection<AbstractState> getStatesOfRegion(Region testedRegion) {
    return testedRegion.getInvolvedStates();
  }

  /**
   * used in common
   * 
   * @param view
   *          a nodeContainer or a diagram
   * @return the list of transitions displayed in the current view
   */
  public List<StateTransition> getDisplayedTransitions(DSemanticDecorator view) {
    List<StateTransition> returnedList = new ArrayList<StateTransition>();
    for (DEdge aView : getDisplayedTransitionViews(view)) {
      returnedList.add((StateTransition) aView.getTarget());
    }
    return returnedList;
  }

  /**
   * used in common
   * 
   * @param view
   *          a nodeContainer or a diagram
   * @return the list of containers/nodes (whose target is a StateTransition) displayed in the current view
   */
  public List<DEdge> getDisplayedTransitionViews(DSemanticDecorator view) {
    List<DEdge> returnedList = new ArrayList<DEdge>();
    if (view instanceof DDiagram) {
      DDiagram diagram = (DDiagram) view;
      for (DEdge edge : diagram.getEdges()) {
        if ((edge.getTarget() != null) && (edge.getTarget() instanceof StateTransition)) {
          returnedList.add(edge);
        }
      }
    }
    if (view instanceof DNodeContainer) {
      DNodeContainer currentContainer = (DNodeContainer) view;

      for (DEdge edge : currentContainer.getIncomingEdges()) {
        if ((edge.getTarget() != null) && (edge.getTarget() instanceof StateTransition)) {
          returnedList.add(edge);
        }
      }
      for (DEdge edge : currentContainer.getOutgoingEdges()) {
        if ((edge.getTarget() != null) && (edge.getTarget() instanceof StateTransition)) {
          returnedList.add(edge);
        }
      }
    }

    return returnedList;
  }

  /**
   * used in context, logical, oa physical
   * 
   * @param view
   *          a nodeContainer or a diagram
   * @return the list of States displayed in the current view
   */
  public List<State> getDisplayedStates(DSemanticDecorator view) {

    List<State> returnedList = new ArrayList<State>();
    for (AbstractDNode aView : getDisplayedStateViews(view)) {
      returnedList.add((State) aView.getTarget());
    }
    return returnedList;
  }

  /**
   * used in context, logical, oa physical
   * 
   * @param view
   *          a nodeContainer or a diagram
   * @return the list of States and Pseudostate displayed in the current view
   */
  public List<IState> getAllDisplayedStates(DSemanticDecorator view) {

    List<IState> returnedList = new ArrayList<IState>();
    for (AbstractDNode aView : getAllDisplayedStateViews(view)) {
      returnedList.add((IState) aView.getTarget());
    }
    return returnedList;
  }

  /**
   * used in context, logical, oa physical
   * 
   * @param view
   *          a nodeContainer or a diagram
   * @return the list of PseudoStates displayed in the current view
   */
  public List<IState> getDisplayedPseudoStates(DSemanticDecorator view) {

    List<IState> returnedList = new ArrayList<IState>();
    for (AbstractDNode aView : getDisplayedPseudoStateViews(view)) {
      returnedList.add((State) aView.getTarget());
    }
    return returnedList;
  }

  /**
   * used in context, logical, oa, physical
   * 
   * @param view
   *          a nodeContainer or a diagram
   * @return the list of containers (whose target is a State) displayed in the current view
   */
  public List<AbstractDNode> getDisplayedStateViews(DSemanticDecorator view) {
    List<AbstractDNode> returnedList = new ArrayList<AbstractDNode>();
    if (view instanceof DDiagram) {
      DDiagram diagram = (DDiagram) view;
      for (AbstractDNode aContainer : diagram.getContainers()) {
        if ((aContainer.getTarget() != null) && (aContainer.getTarget() instanceof IState)) {
          returnedList.add(aContainer);
        }
      }
    }
    if (view instanceof DNodeContainer) {
      DNodeContainer currentContainer = (DNodeContainer) view;
      for (AbstractDNode aContainer : currentContainer.getContainers()) {
        if ((aContainer.getTarget() != null) && (aContainer.getTarget() instanceof IState)) {
          returnedList.add(aContainer);
        }
      }
    }
    return returnedList;
  }

  public List<AbstractDNode> getDisplayedPseudoStateViews(DSemanticDecorator view) {
    List<AbstractDNode> returnedList = new ArrayList<AbstractDNode>();
    if (view instanceof DDiagram) {
      DDiagram diagram = (DDiagram) view;
      for (AbstractDNode node : diagram.getNodes()) {
        if ((node.getTarget() != null) && (node.getTarget() instanceof IState)) {
          returnedList.add(node);
        }
      }
    }
    if (view instanceof DNodeContainer) {
      DNodeContainer currentContainer = (DNodeContainer) view;
      for (AbstractDNode node : currentContainer.getNodes()) {
        if ((node.getTarget() != null) && (node.getTarget() instanceof IState)) {
          returnedList.add(node);
        }
      }
    }
    return returnedList;
  }

  /**
   * @param view
   * @return
   */
  public List<AbstractDNode> getAllDisplayedStateViews(DSemanticDecorator view) {
    List<AbstractDNode> returnedList = new ArrayList<AbstractDNode>();
    List<AbstractDNode> displayedStateViews = getDisplayedStateViews(view);
    List<AbstractDNode> displayedPseudoStateViews = getDisplayedPseudoStateViews(view);
    returnedList.addAll(displayedStateViews);
    returnedList.addAll(displayedPseudoStateViews);
    return returnedList;
  }

  /**
   * used in context, oa, logical, physical
   * 
   * @param current
   * @return
   */
  public List<IState> getAvailableStatesAndModesToInsertInStatemachineDiag(DSemanticDecorator current) {

    List<IState> stateAndModeList = new ArrayList<IState>();

    EObject target = current.getTarget();
    if (current instanceof DDiagram) {
      if (target instanceof Region) {
        stateAndModeList.addAll(getInvolvedStatesRecursively(target));
      }
    }
    IState currentState = null;

    if ((null != target) && (current instanceof DNodeContainer) && (target instanceof State)) {
      State state = (State) target;
      List<IState> involvedStates = getInvolvedStatesRecursively(state);
      stateAndModeList.addAll(involvedStates);
    }

    if ((null != target) && (current instanceof DNodeContainer) && (target instanceof Pseudostate)) {
      currentState = (Pseudostate) target;
      stateAndModeList.add(currentState);
    }

    return stateAndModeList;
  }

  public List<StateTransition> getAvailableTransitionsToInsertInSM(DSemanticDecorator current) {

    List<StateTransition> transitionList = new ArrayList<StateTransition>();

    EObject target = current.getTarget();

    // click on the diagram (default region)
    if (current instanceof DDiagram) {
      if (target instanceof Region) {
        Region defaultRegion = (Region) target;
        for (StateTransition trans : defaultRegion.getOwnedTransitions()) {
          transitionList.add(trans);

        }
      }
    }
    // click on state or mode
    if (current instanceof DNodeContainer) {

      if ((null != target) && (target instanceof State)) {
        State state = (State) target;
        if (state.eContainer() instanceof Region) {
          Region containerRegion = (Region) state.eContainer();
          // should get the displayedTrans only
          for (StateTransition trans : containerRegion.getOwnedTransitions()) {
            List<StateTransition> displayedTransitions = getDisplayedTransitions(current);
            if (!displayedTransitions.contains(trans)) {
              transitionList.add(trans);

            }
          }
        }
      }
    }

    return transitionList;
  }

  @Deprecated
  public ContainerMapping getMappingSMStateMode(State state, DDiagram diagram_p) {
    String mappingName = MappingConstantsHelper.getMappingSMStateMode(state, diagram_p);
    return DiagramServices.getDiagramServices().getContainerMapping(diagram_p, mappingName);
  }

  @Deprecated
  public ContainerMapping getMappingSMInnerStateMode(State state, DDiagram diagram_p) {
    String mappingName = MappingConstantsHelper.getMappingSMInnerStateMode(state, diagram_p);
    return DiagramServices.getDiagramServices().getContainerMapping(diagram_p, mappingName);
  }

  @Deprecated
  public NodeMapping getMappingSMInnerPseudostate(Pseudostate pseudoState, DDiagram diagram_p) {
    String mappingName = MappingConstantsHelper.getMappingSMInnerPseudostate(pseudoState, diagram_p);
    return DiagramServices.getDiagramServices().getNodeMapping(diagram_p, mappingName);
  }

  @Deprecated
  public NodeMapping getMappingSMPseudostate(Pseudostate pseudoState, DDiagram diagram_p) {
    String mappingName = MappingConstantsHelper.getMappingSMPseudostate(pseudoState, diagram_p);
    return DiagramServices.getDiagramServices().getNodeMapping(diagram_p, mappingName);
  }

  @Deprecated
  public EdgeMapping getMappingSMTransition(StateTransition function_p, DDiagram diagram_p) {
    String mappingName = MappingConstantsHelper.getMappingSMTransition(function_p, diagram_p);
    return DiagramServices.getDiagramServices().getEdgeMapping(diagram_p, mappingName);
  }

  public EObject showHideStatesInStateAndModeDiag(DSemanticDecorator view, List<State> selectedStates,
      List<State> visibleStates, List<AbstractDNode> visibleStateViews) {

    DSemanticDiagram diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(view);
    DDiagramContents content = new DDiagramContents(diagram);

    Set<EObject> toBeRemoved = new HashSet<EObject>();

    ShowHideSMStateMode shHide = new ShowHideSMStateMode(content);

    DiagramContext diagramContext = new ShowHideSMStateMode(content).new DiagramContext();

    // store context
    diagramContext.setVariable(ShowHideSMStateMode.CONTEXTUAL_CONTAINER, view.getTarget());
    diagramContext.setVariable(ShowHideSMStateMode.CONTEXTUAL_CONTAINER_VIEW, view);

    for (IState state : selectedStates) {
      shHide.show(state, diagramContext);
    }

    for (AbstractDNode node : visibleStateViews) {
      if (!selectedStates.contains(node.getTarget())) {
        toBeRemoved.add(node.getTarget());
      }
    }

    // remove views
    for (EObject aView : toBeRemoved) {
      shHide.hide(aView, diagramContext);
    }

    return view;
  }

  public EObject showHideSMTransitions(DSemanticDecorator view, List<StateTransition> selectedTransitions,
      List<StateTransition> visibleTransitions, List<DEdge> visibleTransitionViews) {

    DSemanticDiagram diagram = (DSemanticDiagram) CapellaServices.getService().getDiagramContainer(view);
    DDiagramContents content = new DDiagramContents(diagram);

    Set<EObject> toBeRemoved = new HashSet<EObject>();

    ShowHideSMStateMode shHide = new ShowHideSMTransitions(content);
    DiagramContext diagramContext = shHide.new DiagramContext();

    // store context
    diagramContext.setVariable(ShowHideSMStateMode.CONTEXTUAL_CONTAINER, view.getTarget());
    diagramContext.setVariable(ShowHideSMStateMode.CONTEXTUAL_CONTAINER_VIEW, view);

    for (StateTransition trans : selectedTransitions) {
      shHide.show(trans, diagramContext);
    }

    for (DEdge edge : visibleTransitionViews) {
      if (!selectedTransitions.contains(edge.getTarget())) {
        toBeRemoved.add(edge.getTarget());
      }
    }
    // remove views
    for (EObject aView : toBeRemoved) {
      shHide.hide(aView, diagramContext);
    }

    return view;
  }

  /**
   * returns the ancestor states of state
   * 
   * @param state
   * @return
   */
  public List<IState> getStateAncestors(final IState state) {
    List<IState> result = new ArrayList<IState>(0);
    Region currentRegion = (Region) state.eContainer();
    while (currentRegion != null) {
      EObject parent = currentRegion.eContainer();
      if ((parent != null) && (parent instanceof IState)) {
        result.add((IState) parent);
        currentRegion = (Region) parent.eContainer();
      } else {
        return result;
      }
    }

    return result;
  }

  public List<AbstractState> getEntryPoints(IState state) {
    return getSpecificSubState(state, EntryPointPseudoState.class);
  }

  public List<AbstractState> getExitPoints(IState state) {

    return getSpecificSubState(state, ExitPointPseudoState.class);
  }

  private <T> List<AbstractState> getSpecificSubState(IState parent, final Class<T> clazz) {

    List<AbstractState> res = new ArrayList<AbstractState>();

    if ((parent != null) && (parent instanceof State)) {
      for (Region region : ((State) parent).getOwnedRegions()) {
        res.addAll(Collections2.filter(region.getOwnedStates(), Predicates.instanceOf(clazz)));
      }
    }
    return res;
  }

  public boolean isInSameOrSubRegion(IState state, IState subState) {
    Region owningRegion = (Region) state.eContainer();
    TreeIterator<EObject> it = owningRegion.eAllContents();
    while (it.hasNext()) {
      EObject e = it.next();
      if ((e instanceof IState) && (e == subState)) {
        return true;
      }
    }
    return false;
  }

  public StateMachine getOwningStateMachine(EObject e) {
    while ((e instanceof IState) || (e instanceof StateTransition) || (e instanceof Region)) {
      e = e.eContainer();
      if (e instanceof StateMachine) {
        StateMachine sm = (StateMachine) e;
        return sm;
      }
    }
    return null;
  }

  public List<StateTransition> getStateMachineTransition(EObject target) {
    List<StateTransition> list = new ArrayList<StateTransition>();
    StateMachine sm = getOwningStateMachine(target);
    TreeIterator<EObject> it = sm.eAllContents();
    while (it.hasNext()) {
      EObject e = it.next();
      if (e instanceof StateTransition) {
        StateTransition st = (StateTransition) e;
        if (st.getTarget() == target) {
          list.add(st);
        }
      }
    }
    return list;
  }

  public List<StateTransition> getSourcingTransition(EObject source) {
    List<StateTransition> list = new ArrayList<StateTransition>();
    StateMachine sm = getOwningStateMachine(source);
    TreeIterator<EObject> it = sm.eAllContents();
    while (it.hasNext()) {
      EObject e = it.next();
      if (e instanceof StateTransition) {
        StateTransition st = (StateTransition) e;
        if (st.getSource() == source) {
          list.add(st);
        }
      }
    }
    return list;
  }

  public List<StateTransition> getTargettingTransition(EObject target) {
    List<StateTransition> list = new ArrayList<StateTransition>();
    StateMachine sm = getOwningStateMachine(target);
    TreeIterator<EObject> it = sm.eAllContents();
    while (it.hasNext()) {
      EObject e = it.next();
      if (e instanceof StateTransition) {
        StateTransition st = (StateTransition) e;
        if (st.getTarget() == target) {
          list.add(st);
        }
      }
    }
    return list;
  }

  public boolean canCreateTransition(EObject context, IState source, IState target) {

    if ((target instanceof InitialPseudoState) || (source instanceof TerminatePseudoState)
        || (source instanceof FinalState)) {
      return false;
    }

    if ((source instanceof InitialPseudoState)
        && ((target instanceof TerminatePseudoState) || (target instanceof FinalState))) {
      return false;
    }

    if (((target instanceof ExitPointPseudoState) && !(isInSameOrSubRegion(target, source)))
        || ((source instanceof EntryPointPseudoState) && !isInSameOrSubRegion(source, target))
        || ((source instanceof JoinPseudoState) && (getSourcingTransition(source).size() != 0))
        || ((target instanceof ForkPseudoState) && (getTargettingTransition(target).size() != 0))) {
      return false;
    }

    if (EcoreUtil.isAncestor(source, target) || EcoreUtil.isAncestor(target, source)) {
      return false;
    }

    return true;
  }

  public boolean isValidDndSMModeState(EObject semanticToDrop, EObject oldContainer, EObject targetContainerView) {
    if ((targetContainerView instanceof DSemanticDecorator) && (semanticToDrop instanceof AbstractState)) {
      Region newRegion = getRegionFromView(targetContainerView);
      if (newRegion != null) {
        if (!isReferencedState((AbstractState) semanticToDrop, oldContainer) && semanticToDrop instanceof State
            ? MoveHelper.getInstance().canMoveModeState((State) semanticToDrop, newRegion) : true) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Move the given State in the given State or Region
   */
  public EObject dndSMModeState(EObject semanticObjectToDrop, EObject targetContainerView) {
    if ((targetContainerView instanceof DSemanticDecorator) && (semanticObjectToDrop instanceof AbstractState)) {
      AbstractState state = (AbstractState) semanticObjectToDrop;
      EObject target = ((DSemanticDecorator) targetContainerView).getTarget();

      Region targetRegion = null;
      if (target instanceof Region) {
        targetRegion = (Region) target;
      }
      if (target instanceof State) {
        targetRegion = StateExt.getPrimaryRegion(((State) target));
      }
      if (targetRegion != null) {
        targetRegion.getOwnedStates().add(state);
      }

    }
    return semanticObjectToDrop;
  }
}
