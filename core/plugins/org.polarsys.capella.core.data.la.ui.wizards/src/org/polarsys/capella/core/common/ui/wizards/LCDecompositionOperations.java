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
package org.polarsys.capella.core.common.ui.wizards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.communication.CommunicationFactory;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.interaction.RefinementLink;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.ui.toolkit.decomposition.Decomposition;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionComponent;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionItem;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionItemService;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionModel;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionModelEvent;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionModelListener;

/**
 * Class <code>LCDecompositionOperations</code> listens for events on <code>DecompositionModel</code> and does all the
 * required operations. Actual business logic is performed here.
 */
public class LCDecompositionOperations implements DecompositionModelListener {

  private static final Logger _logger = ReportManagerRegistry.getInstance().subscribe(
      IReportManagerDefaultComponents.UI);
  private LCDecompositionController _controller;

  /**
   * @see org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionModelListener#decompositionChanged(org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionModelEvent)
   */
  public void decompositionChanged(DecompositionModelEvent event) {
    switch (event.getEventType()) {
    case DecompositionModelEvent.DECOMPOSITION_ADDED:
      addNewDecomposition(event);
      break;
    case DecompositionModelEvent.DECOMPOSITION_ALL_REMOVED:
      removeAllDecompositions(event);
      break;
    case DecompositionModelEvent.DECOMPOSITION_REMOVED:
      removeDecomposition(event);
      break;
    case DecompositionModelEvent.DECOMPOSITION_RENAMED:
      renameDecomposition(event);
      break;
    case DecompositionModelEvent.TARGET_COMPONENT_ADDED:
      addNewTargetComponent(event);
      break;
    case DecompositionModelEvent.TARGET_COMPONENT_REMOVED:
      removeTargetComponent(event);
      break;
    case DecompositionModelEvent.TARGET_COMPONENT_ALL_REMOVED:
      removeAllTargetComponents(event);
      break;
    case DecompositionModelEvent.TARGET_COMPONENT_RENAMED:
      renameTargetComponent(event);
      break;
    case DecompositionModelEvent.TARGET_COMPONENT_REUSED:
      wrapReusedTargetComponent(event);
      break;
    case DecompositionModelEvent.TARGET_COMPONENT_INTERFACE_ATTACHED:
      attachInterface(event);
      break;
    case DecompositionModelEvent.TARGET_COMPONENT_INTERFACE_DETACHED:
      detachInterface(event);
      break;
    case DecompositionModelEvent.DECOMPOSITION_FINISHED:
      finishDecomposition(event);
      break;
    default:
      break;
    }
  }

  /**
   * @param event
   */
  private void wrapReusedTargetComponent(DecompositionModelEvent event) {
    try {
      DecompositionComponent shortcutComp = new DecompositionComponent();
      Object obj = event.getCurrentData();
      if (obj instanceof LogicalComponent) {
        LogicalComponent comp = (LogicalComponent) obj;
        shortcutComp.setName(comp.getName());

        shortcutComp.setValue(comp);
        shortcutComp.setReusedComponent(true);

        shortcutComp.setItems(_controller.getWrappedInterfaces(comp, false));
        shortcutComp.setReusedTarget(comp);
        shortcutComp.setPath(_controller.getElementPath(comp));
        event.setReusedComponent(shortcutComp);
      }

    } catch (java.lang.Exception exception) {
      _logger.debug(new EmbeddedMessage(exception.getMessage(), IReportManagerDefaultComponents.UI));
      event.setReusedComponent(null);
    }

  }

  /**
   * Detaches an interface from a sub logical component
   * 
   * @param event
   *          the DecompositionModelEvent
   */
  private void detachInterface(DecompositionModelEvent event) {
    try {
      // Do the necessary if the operation has to be carried out before finish
      _controller.triggerView();
      event.setOperationSuccess(true);
    } catch (java.lang.Exception exception) {
      _logger.debug(new EmbeddedMessage(exception.getMessage(), IReportManagerDefaultComponents.UI));
      event.setOperationSuccess(false);
    }
  }

  /**
   * Attaches an interface to a sub logical component
   * 
   * @param event
   *          the DecompositionModelEvent
   */
  private void attachInterface(DecompositionModelEvent event) {
    try {
      // Do the necessary if the operation has to be carried out before finish
      _controller.triggerView();
      event.setOperationSuccess(true);
    } catch (java.lang.Exception exception) {
      _logger.debug(new EmbeddedMessage(exception.getMessage(), IReportManagerDefaultComponents.UI));
      event.setOperationSuccess(false);
    }
  }

  /**
   * Adds a new sub logical component
   * 
   * @param event
   *          the DecompositionModelEvent
   */
  public void addNewTargetComponent(final DecompositionModelEvent event) {
    try {
      // Do the necessary if the operation has to be carried out before finish
      _controller.triggerView();
      event.setOperationSuccess(true);
    } catch (java.lang.Exception exception) {
      _logger.debug(new EmbeddedMessage(exception.getMessage(), IReportManagerDefaultComponents.UI));
      exception.printStackTrace();
      event.setOperationSuccess(false);
    }
  }

  /**
   * Renames a target sub logical component
   * 
   * @param event
   *          the DecompositionModelEvent
   */
  private void renameTargetComponent(DecompositionModelEvent event) {
    try {
      // Do the necessary if the operation has to be carried out before finish
      _controller.triggerView();
      event.setOperationSuccess(true);
    } catch (java.lang.Exception exception) {
      _logger.debug(new EmbeddedMessage(exception.getMessage(), IReportManagerDefaultComponents.UI));
      event.setOperationSuccess(false);
    }
  }

  /**
   * Removes all sub logical components
   * 
   * @param event
   *          the DecompositionModelEvent
   */
  private void removeAllTargetComponents(DecompositionModelEvent event) {
    try {
      // Do the necessary if the operation has to be carried out before finish
      _controller.triggerView();
      event.setOperationSuccess(true);
    } catch (java.lang.Exception exception) {
      _logger.debug(new EmbeddedMessage(exception.getMessage(), IReportManagerDefaultComponents.UI));
      event.setOperationSuccess(false);
    }
  }

  /**
   * Removes a sub logical component
   * 
   * @param event
   *          the DecompositionModelEvent
   */
  private void removeTargetComponent(final DecompositionModelEvent event) {
    try {
      _controller.setUserHasDeletedSubComponent(true);
      _controller.triggerView();
      event.setOperationSuccess(true);
    } catch (java.lang.Exception exception) {
      _logger.debug(new EmbeddedMessage(exception.getMessage(), IReportManagerDefaultComponents.UI));
      event.setOperationSuccess(false);
    }
  }

  /**
   * Adds a new decomposition
   * 
   * @param event
   *          the DecompositionModelEvent
   */
  public void addNewDecomposition(DecompositionModelEvent event) {
    try {
      // Do the necessary if the operation has to be carried out before finish
      _controller.triggerView();
      event.setOperationSuccess(true);
    } catch (java.lang.Exception exception) {
      _logger.debug(new EmbeddedMessage(exception.getMessage(), IReportManagerDefaultComponents.UI));
      event.setOperationSuccess(false);
    }
  }

  /**
   * Renames a decomposition
   * 
   * @param event
   *          the DecompositionModelEvent
   */
  private void renameDecomposition(DecompositionModelEvent event) {
    try {
      // Do the necessary if the operation has to be carried out before finish
      _controller.triggerView();
      event.setOperationSuccess(true);
    } catch (java.lang.Exception exception) {
      _logger.debug(new EmbeddedMessage(exception.getMessage(), IReportManagerDefaultComponents.UI));
      event.setOperationSuccess(false);
    }
  }

  /**
   * Removes a decomposition
   * 
   * @param event
   *          the DecompositionModelEvent
   */
  private void removeDecomposition(DecompositionModelEvent event) {
    try {
      // Do the necessary if the operation has to be carried out before finish
      _controller.triggerView();
      event.setOperationSuccess(true);
    } catch (java.lang.Exception exception) {
      _logger.warn(new EmbeddedMessage(exception.getMessage(), IReportManagerDefaultComponents.UI));
      event.setOperationSuccess(false);
    }
  }

  /**
   * Removes all decompositions
   * 
   * @param event
   *          the DecompositionModelEvent
   */
  private void removeAllDecompositions(DecompositionModelEvent event) {
    try {
      // Do the necessary if the operation has to be carried out before finish
      _controller.triggerView();
      event.setOperationSuccess(true);
    } catch (java.lang.Exception exception) {
      _logger.debug(new EmbeddedMessage(exception.getMessage(), IReportManagerDefaultComponents.UI));
      event.setOperationSuccess(false);
    }
  }

  /**
   * Finishes the decomposition of the Logical Component
   * 
   * @param event
   *          the DecompositionModelEvent
   */
  private void finishDecomposition(DecompositionModelEvent event) {
    try {
      final DecompositionModel model = (DecompositionModel) event.getSource();
      DecompositionComponent sourceComponent = model.getSourceComponent();
      final LogicalComponent sourceLC = (LogicalComponent) sourceComponent.getValue();
      if (model.getDecompositions().size() == 1) {
        addSubLCs(model.getDecompositions().get(0), sourceLC);
      }

      AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
        public void run() {
          List<DecompositionComponent> targetComponents = removeLCs(model, sourceLC);
          removeInterfaces(targetComponents);
        }
      };

      TransactionHelper.getExecutionManager(sourceLC).execute(command);
      event.setOperationSuccess(true);
    } catch (java.lang.Exception exception) {
      _logger.debug(new EmbeddedMessage(exception.getMessage(), IReportManagerDefaultComponents.UI));
      exception.printStackTrace();
      event.setOperationSuccess(false);
    }
  }

  // REMOVE Interfaces
  //
  private void removeInterfaces(List<DecompositionComponent> targetComponents) {
    for (DecompositionComponent decompositionComponent : targetComponents) {
      // map of <Interface , Link>
      Map<EObject, EObject> usedAndImpInterfaces = new HashMap<EObject, EObject>();
      // implemented interface
      EList<InterfaceImplementation> ownedInterfaceImplementations = ((LogicalComponent) decompositionComponent
          .getValue()).getOwnedInterfaceImplementations();
      for (InterfaceImplementation interfaceImplementation : ownedInterfaceImplementations) {
        usedAndImpInterfaces.put(interfaceImplementation, interfaceImplementation.getImplementedInterface());
      }
      // used interface
      EList<InterfaceUse> ownedInterfaceUses = ((LogicalComponent) decompositionComponent.getValue())
          .getOwnedInterfaceUses();
      for (InterfaceUse interfaceUse : ownedInterfaceUses) {
        usedAndImpInterfaces.put(interfaceUse, interfaceUse.getUsedInterface());
      }

      List<DecompositionItem> items = decompositionComponent.getItems();
      List<Interface> interfaces = new ArrayList<Interface>();
      for (DecompositionItem decompositionItem : items) {
        Object value = decompositionItem.getValue();
        if (value != null && value instanceof Interface) {
          interfaces.add((Interface) value);
        }
      }
      // if no children interface left in parent 'decompositionComponent'
      if ((items == null) || items.isEmpty()) {
        // delete all
        for (EObject eObject : usedAndImpInterfaces.keySet()) {
          Object valueme = decompositionComponent.getValue();
          // delete the interface link from lc
          if (valueme instanceof LogicalComponent) {
            LogicalComponent lc = (LogicalComponent) valueme;
            if (eObject instanceof InterfaceUse) {
              lc.getOwnedInterfaceUses().remove(eObject);
            }
            if (eObject instanceof InterfaceImplementation) {
              lc.getOwnedInterfaceImplementations().remove(eObject);
            }
          }
        }
      } else {
        // delete if not in wizard
        for (EObject eObject : usedAndImpInterfaces.keySet()) {
          if (!interfaces.contains(usedAndImpInterfaces.get(eObject))) {
            Object valueme = decompositionComponent.getValue();
            // delete the interface link from lc
            if (valueme instanceof LogicalComponent) {
              LogicalComponent lc = (LogicalComponent) valueme;
              if (eObject instanceof InterfaceUse) {
                lc.getOwnedInterfaceUses().remove(eObject);
              }
              if (eObject instanceof InterfaceImplementation) {
                lc.getOwnedInterfaceImplementations().remove(eObject);
              }
            }
          }
        }
      }
    }
  }

  // REMOVE LCs
  // If the lc to be deleted has only one part
  // delete both part and LC
  // if the lc to be deleted has more than one part
  // delete only part
  private List<DecompositionComponent> removeLCs(DecompositionModel model, final LogicalComponent sourceLC) {
    EList<LogicalComponent> subLogicalComponents = sourceLC.getSubLogicalComponents();
    EList<LogicalComponent> targetLcs = new BasicEList<LogicalComponent>();
    Decomposition decompositionFirst = model.getDecompositions().get(0);
    List<DecompositionComponent> targetComponents = decompositionFirst.getTargetComponents();
    for (DecompositionComponent decompositionComponent : targetComponents) {
      LogicalComponent lc2 = (LogicalComponent) decompositionComponent.getValue();
      targetLcs.add(lc2);
    }
    boolean isReused = false;
    List<LogicalComponent> lcsTORemove = new ArrayList<LogicalComponent>();

    for (LogicalComponent lc1 : subLogicalComponents) {
      // if lc has more than one abstract type (part)
      if (lc1.getAbstractTypedElements().size() > 1) {
        isReused = true;
      }

      if (!isMultipartDriven(sourceLC)) { // should change to delete part and component option
        // remove both lc1 and part
        //
        if (!targetLcs.contains(lc1) && !isReused) {
          // remove part
          List<AbstractTypedElement> abstractTypedElements = lc1.getAbstractTypedElements();
          for (AbstractTypedElement abstractTypedElement : abstractTypedElements) {
            abstractTypedElement.destroy();
          }

          // remove lc1
          EObject container = lc1.eContainer();
          if (container instanceof LogicalComponent) {
            // collect all the logical components which has to be removed
            lcsTORemove.add(lc1);
          }
        }
        // remove only part : because lc1 is used else were in model
        //
        if (!targetLcs.contains(lc1) && isReused) {
          List<AbstractTypedElement> abstractTypedElements = lc1.getAbstractTypedElements();
          for (AbstractTypedElement abstractTypedElement : abstractTypedElements) {
            EObject container = abstractTypedElement.eContainer();
            if (null != container) {
              if (container.equals(sourceLC)) {
                abstractTypedElement.destroy();
              }
            }
          }
        }

        // Remove the Logical Component
        //
        for (LogicalComponent logicalComponent : lcsTORemove) {
          EObject container = logicalComponent.eContainer();
          if (null != container) {
            if (container instanceof LogicalComponent) {
              LogicalComponent containerLC = (LogicalComponent) container;
              containerLC.getOwnedLogicalComponents().remove(logicalComponent);
            } else if (container instanceof LogicalComponentPkg) {
              LogicalComponentPkg containerLC = (LogicalComponentPkg) container;
              containerLC.getOwnedLogicalComponents().remove(logicalComponent);
            }
          }
        }

      } else {

        // remove only part
        //
        if (!targetLcs.contains(lc1) && !isReused) {
          // remove part
          List<AbstractTypedElement> abstractTypedElements = lc1.getAbstractTypedElements();
          for (AbstractTypedElement abstractTypedElement : abstractTypedElements) {
            abstractTypedElement.destroy();
          }
        }
        // remove only part owned by sourceLC
        if (!targetLcs.contains(lc1) && isReused) {
          List<AbstractTypedElement> abstractTypedElements = lc1.getAbstractTypedElements();
          for (AbstractTypedElement abstractTypedElement : abstractTypedElements) {
            EObject container = abstractTypedElement.eContainer();
            if (null != container) {
              if (abstractTypedElement.eContainer().equals(sourceLC)) {
                abstractTypedElement.destroy();
              }
            }
          }
        }

      }
      isReused = false;
    }
    return targetComponents;
  }

  /**
   * Adds sub lcs to the source logical component (In case of single decomposition)
   * 
   * @param decomposition
   *          the Decomposition
   * @param sourceComponent
   *          the source LC
   */
  private void addSubLCs(final Decomposition decomposition, final LogicalComponent sourceComponent) {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        addTargetComponents(decomposition, sourceComponent, null, false);
      }

      @Override
      public String getName() {
        return sourceComponent.getName();
      }
    };
    TransactionHelper.getExecutionManager(sourceComponent).execute(command);
  }

  /**
   * Adds LC Decompositions to the source logical component (In case of multiple decomposition)
   * 
   * @param decompositions
   *          the list of decompositions
   * @param sourceComponent
   *          the source component
   */
  @SuppressWarnings("unused")
  private void addLCDcmpsToLogicalComponent(final List<Decomposition> decompositions,
      final LogicalComponent sourceComponent) {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        List<LogicalComponent> ll = sourceComponent.getSubLogicalComponents();

        sourceComponent.getSubLogicalComponents().remove(ll);

        for (Decomposition decomp : decompositions) {
          Object value = decomp.getValue();
          LogicalArchitecture arch = null;

          if ((value == null) || value.equals(Decomposition.DUMMY_VALUE)) {
            arch = LaFactory.eINSTANCE.createLogicalArchitecture();
            decomp.setValue(arch);
          } else {
            arch = (LogicalArchitecture) value;
          }
          arch.setName(decomp.getName());// in case of rename

          if (!sourceComponent.getOwnedLogicalArchitectures().contains(arch)) {
            sourceComponent.getOwnedLogicalArchitectures().add(arch);
          }

          // add all the target components
          addTargetComponents(decomp, null, arch, true);
        }
        // removes the decompositions removed from the view
        sourceComponent.getOwnedLogicalArchitectures()
            .remove(getRemovedDecompositions(sourceComponent, decompositions));

        moveAbstractCapabilityPkg(sourceComponent.getOwnedLogicalArchitectures().get(0),
            sourceComponent.getOwnedAbstractCapabilityPkg());

        sourceComponent.setOwnedAbstractCapabilityPkg(null);
      }

      @Override
      public String getName() {
        return sourceComponent.getName();
      }
    };
    TransactionHelper.getExecutionManager(sourceComponent).execute(command);
  }

  /**
   * Moving an AspectPkg to LogicalArchitecture
   * 
   * @param arch
   *          the logical architecture
   * @param aspectPkg
   *          the aspect package
   */
  void moveAbstractCapabilityPkg(LogicalArchitecture arch, AbstractCapabilityPkg aspectPkg) {
    if ((null == arch) || (null == aspectPkg)) {
      return;
    }
    arch.setOwnedAbstractCapabilityPkg(aspectPkg);
  }

  /**
   * Moving an AspectPkg to LogicalComponent
   * 
   * @param cpnt
   *          the logical component
   * @param aspectPkg
   *          the aspect package
   */
  void moveAbstractCapabilityPkg(LogicalComponent cpnt, AbstractCapabilityPkg aspectPkg) {
    if ((null == cpnt) || (null == aspectPkg)) {
      return;
    }
    cpnt.setOwnedAbstractCapabilityPkg(aspectPkg);
  }

  /**
   * Adds Target Components for a decomposition either subLCs or LCs to alternative decomposition.
   * 
   * @param decomposition
   *          the decomposition
   * @param sourceComponent
   *          the Source LogicalComponent to add subLCs (in case of single level decomposition)
   * @param logArch
   *          the LogicalArchitecture (in case of multi level decomposition)
   * @param isAlternateDecomposition
   *          flag to indicate whether it is single level or multi level decomposition
   */
  void addTargetComponents(Decomposition decomposition, LogicalComponent sourceComponent, LogicalArchitecture logArch,
      boolean isAlternateDecomposition) {

    for (DecompositionComponent comp : decomposition.getTargetComponents()) {
      // do the REUSE SHORTCUT HERE
      if (comp.isReusedComponent()) {
        LogicalComponent value = (LogicalComponent) comp.getValue();
        @SuppressWarnings("unused")
        LogicalComponent target = (LogicalComponent) comp.getReusedTarget();
        if (comp.isTrigger()) {
          if (null != value) {
            addComponentInstanceToLC2(sourceComponent, value, comp, true);
          }
        } else {
          if (null != value) {
            addComponentInstanceToLC2(sourceComponent, value, comp, false);
          }
        }
      } else {
        LogicalComponent lc = (LogicalComponent) comp.getValue();
        boolean isLcNew = false;
        if (lc == null) {
          lc = LaFactory.eINSTANCE.createLogicalComponent(comp.getName());
          comp.setValue(lc);
          isLcNew = true;
        }
        if (!isLcNew) {
          // if comp is renamed
          if (comp.isTrigger()) {
            for (AbstractTypedElement abstractTypedElement : lc.getAbstractTypedElements()) {
              abstractTypedElement.setName(comp.getName());
            }
          }
        }

        if (!isAlternateDecomposition) {
          // if lc is not in a breakdown structure of sourceComponent
          if (!sourceComponent.getSubLogicalComponents().contains(lc)) {
            // add lc in breakdown structure of sourceComponent
            addComponentInstanceToLC(sourceComponent, lc);
            sourceComponent.getOwnedLogicalComponents().add(lc);
          }
        }
        List<DecompositionItem> interfaceItems = new ArrayList<DecompositionItem>();
        List<DecompositionItem> communicationLinkItems = new ArrayList<DecompositionItem>();
        for (DecompositionItem item : comp.getItems()) {
          Object value = item.getValue();
          if (value instanceof CommunicationLink) {
            communicationLinkItems.add(item);
          } else {
            interfaceItems.add(item);
          }
        }
        // Add or Update Internal Interfaces
        updateInternalInterfaces(interfaceItems, lc);
        // Update Realization links and Use links
        updateInterfacesLinks(interfaceItems, lc);
        // Update communication links
        updateCommunicationLinks(communicationLinkItems, lc);
      }
    }
  }

  private void updateCommunicationLinks(List<DecompositionItem> communicationLinkItems, LogicalComponent lc) {
    List<CommunicationLink> communicationLinks = new ArrayList<CommunicationLink>();
    for (DecompositionItem item : communicationLinkItems) {
      communicationLinks.add((CommunicationLink) item.getValue());
    }
    List<CommunicationLink> intersection = new ArrayList<CommunicationLink>(communicationLinks);
    intersection.retainAll(lc.getOwnedCommunicationLinks());
    List<CommunicationLink> toBeCloned = new ArrayList<CommunicationLink>(communicationLinks);
    toBeCloned.removeAll(intersection);
    List<CommunicationLink> toBeRemoved = new ArrayList<CommunicationLink>(lc.getOwnedCommunicationLinks());
    toBeRemoved.removeAll(intersection);

    for (CommunicationLink link : toBeCloned) {
      CommunicationLink clone = CommunicationFactory.eINSTANCE.createCommunicationLink();
      clone.setExchangeItem(link.getExchangeItem());
      clone.setKind(link.getKind());
      clone.setProtocol(link.getProtocol());
      lc.getOwnedCommunicationLinks().add(clone);
      CapellaElementExt.creationService(clone);
    }
    for (CommunicationLink link : toBeRemoved) {
      new CapellaDeleteCommand(ExecutionManagerRegistry.getInstance().getExecutionManager(
          TransactionHelper.getEditingDomain(link)), Collections.singleton(link), true, false, false).execute();
    }
  }

  /**
   * Create or Update Internal Interface for current sub-component
   */
  private void updateInternalInterfaces(List<DecompositionItem> ItemInterfacelist, LogicalComponent lc) {
    for (DecompositionItem itemItf : ItemInterfacelist) {
      if (itemItf.isInternal()) {
        List<ExchangeItemAllocation> operationsAvailable = new ArrayList<ExchangeItemAllocation>();
        Interface itf = updateInternalInterface(lc, itemItf);

        updateExchangeItems(itemItf, operationsAvailable, itf);
        // Remove unavailable Operations
        removeUnvailableExchangeItems(operationsAvailable, itf);
      }
    }
  }

  private void updateExchangeItems(DecompositionItem itemItf, List<ExchangeItemAllocation> operationsAvailable,
      Interface itf) {
    for (DecompositionItemService itemSce : itemItf.getServiceItems()) {
      ExchangeItemAllocation opOrigin = null, opCopy = null;
      ExchangeItemAllocation op = (ExchangeItemAllocation) itemSce.getValue();

      if (!itf.getOwnedExchangeItemAllocations().contains(op)) {
        // Case : Operation not exist in Interface - Create Signal or Service Operation corresponding
        opOrigin = op;
        opCopy = InterfaceExt.addExchangeItem(itf, opOrigin.getAllocatedItem());
        RefinementLinkExt.createRefinementTraceabilityLink(opCopy, opOrigin);

      } else {
        // Case : Operation already exist in Interface - Get origin Operation with traceability link
        opCopy = op;
        List<CapellaElement> listMelo = RefinementLinkExt.getRefinementRelatedTargetElements(opCopy,
            CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION);
        if (listMelo.size() != 0) {
          opOrigin = (ExchangeItemAllocation) listMelo.get(0);
        } else if (!isRefinementTraceExist(opCopy)) {
          operationsAvailable.add(opCopy); // Keep operation added manually (none traceability link)
        }
      }

      if ((opCopy != null) && (opOrigin != null)) {
        updateExchangeItemProperties(opCopy, opOrigin);
        operationsAvailable.add(opCopy);
      }
    }
  }

  private boolean isRefinementTraceExist(ExchangeItemAllocation opCopy) {
    for (AbstractTrace lnk : opCopy.getIncomingTraces()) {
      if (lnk instanceof RefinementLink) {
        return true;
      }
    }
    return false;
  }

  private boolean isRefinementTraceExist(TraceableElement from, TraceableElement to) {
    for (AbstractTrace lnk : to.getIncomingTraces()) {
      if ((lnk instanceof RefinementLink) && lnk.getSourceElement().equals(from)) {
        return true;
      }
    }
    return false;
  }

  private Interface updateInternalInterface(LogicalComponent lc, DecompositionItem itemItf) {
    Interface itf;
    InterfacePkg itfPkg = lc.getOwnedInterfacePkg();
    if (itfPkg == null) {
      // Create InterfacePkg under the LogicalComponent
      itfPkg = CsFactory.eINSTANCE.createInterfacePkg("Interfaces"); //$NON-NLS-1$
      lc.setOwnedInterfacePkg(itfPkg);
    }
    if (itemItf.getValue() == null) {
      // Create Interface under the InterfacePkg
      itf = CsFactory.eINSTANCE.createInterface(itemItf.getName());
      itfPkg.getOwnedInterfaces().add(itf);
      for (Object obj : itemItf.getOriginInterfaces()) {
        RefinementLinkExt.createRefinementTraceabilityLink(itf, (NamedElement) obj);
      }
      itemItf.setValue(itf);
    } else {
      // Get existing Interface
      itf = (Interface) itemItf.getValue();
      itf.setName(itemItf.getName());
      for (Object obj : itemItf.getOriginInterfaces()) {
        if (!isRefinementTraceExist(itf, (TraceableElement) obj)) {
          RefinementLinkExt.createRefinementTraceabilityLink(itf, (NamedElement) obj);
        }
      }
    }
    return itf;
  }

  /**
   * Remove Operation not defined in list (given in first parameter) owned by Interface (given in second parameter)
   * 
   * @param operationsAvailable
   * @param itf
   */
  private void removeUnvailableExchangeItems(List<ExchangeItemAllocation> operationsAvailable, Interface itf) {
    List<ExchangeItemAllocation> opToRemove = new ArrayList<ExchangeItemAllocation>();
    for (ExchangeItemAllocation op : itf.getOwnedExchangeItemAllocations()) {
      if (!operationsAvailable.contains(op)) {
        opToRemove.add(op);
      }
    }
    for (ExchangeItemAllocation op : opToRemove) {
      if (itf.getOwnedExchangeItemAllocations().contains(op)) {
        // Delete traceability link and operation
        CapellaElementExt.cleanTraces(op);
        itf.getOwnedExchangeItemAllocations().remove(op);
      }
    }
  }

  private void updateExchangeItemProperties(ExchangeItemAllocation opCopy, ExchangeItemAllocation opOrigin) {
    opCopy.setName(opOrigin.getName());
    cloneNotes(opOrigin, opCopy);
    opCopy.setReceiveProtocol(opOrigin.getReceiveProtocol());
    opCopy.setSendProtocol(opOrigin.getSendProtocol());
  }

  /**
   * Update Description/Summary notes for CapellaElement 'capellaEltCopy' from CapellaElement 'capellaEltOrigin' origin
   * 
   * @param capellaEltOrigin
   * @param capellaEltCopy
   */
  private void cloneNotes(CapellaElement capellaEltOrigin, CapellaElement capellaEltCopy) {
    // Clone Description note
    capellaEltCopy.setDescription(capellaEltOrigin.getDescription());
    // Clone Summary note
    capellaEltCopy.setSummary(capellaEltOrigin.getSummary());
  }

  /**
   * Creates and adds a ComponentInstance to the AbstractLogicalComponent
   * 
   * @param component
   *          the {@link AbstractLogicalComponent} (either {@link LogicalComponent} or {@link LogicalComponentShortcut}
   */
  void addComponentInstanceToLC(LogicalComponent component) {
    // Builds the component instance and attaches it to its package.
    Part instance = CsFactory.eINSTANCE.createPart(component.getName());
    // ownedpartition replaced by ownedfeature
    component.getOwnedFeatures().add(instance);
    instance.setAbstractType(component);
  }

  /**
   * Creates and adds a ComponentInstance to the AbstractLogicalComponent
   * 
   * @param component
   *          the {@link AbstractLogicalComponent} (either {@link LogicalComponent} or {@link LogicalComponentShortcut}
   */
  void addComponentInstanceToLC(LogicalComponent parent, LogicalComponent component) {
    // Builds the component instance and attaches it to its package.
    Part instance = CsFactory.eINSTANCE.createPart(component.getName());
    parent.getOwnedFeatures().add(instance);
    instance.setAbstractType(component);
  }

  /**
   * Creates and adds a ComponentInstance to the AbstractLogicalComponent
   * 
   * @param component
   *          the {@link AbstractLogicalComponent} (either {@link LogicalComponent} or {@link LogicalComponentShortcut}
   */
  void addComponentInstanceToLC2(LogicalComponent parent, LogicalComponent component, DecompositionComponent comp,
      boolean compName) {
    // Builds the component instance and attaches it to its package.

    Part partInstance = null;
    if ((component.getAbstractTypedElements() != null) && !component.getAbstractTypedElements().isEmpty()) {
      if (compName) {
        partInstance = CsFactory.eINSTANCE.createPart(comp.getName());
        EList<AbstractTypedElement> abstractTypedElements = component.getAbstractTypedElements();
        for (AbstractTypedElement abstractTypedElement : abstractTypedElements) {
          abstractTypedElement.setName(comp.getName());
        }
      } else {
        partInstance = CsFactory.eINSTANCE.createPart(component.getAbstractTypedElements().get(0).getName());
      }
    } else {
      if (compName) {
        partInstance = CsFactory.eINSTANCE.createPart(comp.getName());
      } else {
        partInstance = CsFactory.eINSTANCE.createPart(component.getName());
      }
    }

    parent.getOwnedFeatures().add(partInstance);
    partInstance.setAbstractType(component);
  }

  /**
   * Gets the list of target components removed in case of single decomposition
   * 
   * @param sourceComp
   *          the source component
   * @param decomposition
   *          the decomposition
   * @return list of removed target SubLCs
   */
  List<LogicalComponent> getRemovedTargetComponents(LogicalComponent sourceComp, Decomposition decomposition) {
    List<LogicalComponent> removedComponents = new ArrayList<LogicalComponent>(1);
    for (LogicalComponent lc : sourceComp.getSubLogicalComponents()) {
      boolean flag = true;
      for (DecompositionComponent comp : decomposition.getTargetComponents()) {
        Object obj = comp.getValue();
        if ((obj != null) && obj.equals(lc)) {
          flag = false;
          break;
        }
      }
      if (flag) {
        removedComponents.add(lc);
      }
    }
    return removedComponents;
  }

  /**
   * Gets the list of LogicalArchitectures removed from the model
   * 
   * @param sourceComp
   *          the source LC
   * @param decompositions
   *          list of decompositions in the model
   * @return the list of removed LogicalArchitectures
   */
  List<LogicalArchitecture> getRemovedDecompositions(LogicalComponent sourceComp, List<Decomposition> decompositions) {
    List<LogicalArchitecture> removedComponents = new ArrayList<LogicalArchitecture>(1);
    for (LogicalArchitecture arch : sourceComp.getOwnedLogicalArchitectures()) {
      boolean flag = true;
      for (Decomposition decomp : decompositions) {
        if ((decomp.getValue() != null) && decomp.getValue().equals(arch)) {
          flag = false;
          break;
        }
      }
      if (flag) {
        removedComponents.add(arch);
      }
    }
    return removedComponents;
  }

  /**
   * Gets the list of LCs removed from the model (but available with the LogicalArchitecture).
   * 
   * @param decomposition
   *          the decomposition
   * @return list of LCs removed
   */
  List<LogicalComponent> getRemovedLCsFromDecomposition(Decomposition decomposition) {
    List<LogicalComponent> removedComponents = new ArrayList<LogicalComponent>(1);
    LogicalArchitecture logArch = (LogicalArchitecture) decomposition.getValue();
    boolean flag = true;
    for (DecompositionComponent comp : decomposition.getTargetComponents()) {
      Object obj = comp.getValue();
      if ((obj != null) && obj.equals(logArch.getSystem())) {
        flag = false;
        break;
      }
    }
    if (flag) {
      removedComponents.add((LogicalComponent)logArch.getSystem());
    }
    return removedComponents;
  }

  /**
   * Adds interfaces to an LC
   * 
   * @param pairs
   *          the list of DecompositionItem
   * @param lc
   *          the LogicalComponent
   */
  void updateInterfacesLinks(List<DecompositionItem> pairs, LogicalComponent lc) {

    for (DecompositionItem pair : pairs) {
      Interface inter = (Interface) pair.getValue();
      if (pair.isInterfaceUsage()) {
        boolean alreadyUsed = false;
        for (InterfaceUse use : lc.getUsedInterfaceLinks()) {
          if ((use.getUsedInterface() != null) && use.getUsedInterface().equals(inter)) {
            alreadyUsed = true;
            break;
          }
        }
        if (!alreadyUsed) {
          InterfaceUse interfaceUse = CsFactory.eINSTANCE.createInterfaceUse();
          interfaceUse.setUsedInterface(inter);

          lc.getOwnedInterfaceUses().add(interfaceUse);
        }
      } else {
        boolean alreadyImplemented = false;
        for (InterfaceImplementation impl : lc.getImplementedInterfaceLinks()) {
          if ((impl.getImplementedInterface() != null) && impl.getImplementedInterface().equals(inter)) {
            alreadyImplemented = true;
            break;
          }
        }
        if (!alreadyImplemented) {
          InterfaceImplementation interfaceImpl = CsFactory.eINSTANCE.createInterfaceImplementation();
          interfaceImpl.setImplementedInterface(inter);

          lc.getOwnedInterfaceImplementations().add(interfaceImpl);
        }
      }
    }

    removeInternalInterfacesSelectedByButton(); // (Hook) Remove Internal Interface destroy by button
    removeUnusedInterfaces(lc, pairs);
    removeUnImplementedInterfaces(lc, pairs);
  }

  // (Hook) Remove Internal Interface destroy by button
  private void removeInternalInterfacesSelectedByButton() {
    for (DecompositionItem decItem : _controller.getModel().getDecompositionItemRemoved()) {
      if (decItem.getValue() != null) {
        Interface itfToRemove = (Interface) decItem.getValue();
        cleanInternalInterface(itfToRemove, true);
      }
    }
  }

  /**
   * Removes all unused Interfaces from the LogicalComponent
   * 
   * @param lc
   *          the LogicalComponent
   * @param pairs
   *          list of DecompositionItem
   */
  void removeUnusedInterfaces(LogicalComponent lc, List<DecompositionItem> pairs) {
    List<InterfaceUse> unusedInterfaces = new ArrayList<InterfaceUse>();
    for (InterfaceUse interfaceUse : lc.getUsedInterfaceLinks()) {
      boolean flag = true;
      for (DecompositionItem pair : pairs) {
        if (pair.getValue().equals(interfaceUse.getUsedInterface())) {
          flag = false;
          break;
        }
      }
      if (flag) {
        unusedInterfaces.add(interfaceUse);
      }
    }

    // Remove Internal Interface when all owned Operation is delegated kind
    for (InterfaceUse useLink : unusedInterfaces) {
      cleanInternalInterface(useLink.getUsedInterface(), false);
    }

    lc.getOwnedInterfaceUses().remove(unusedInterfaces);
  }

  /*
   * Remove Internal Interface when all owned Operation is delegated kind
   */
  private void cleanInternalInterface(Interface anInterface, boolean cleanAll) {
    // Check if the Interface is internal (Refinement link toward Package)
    if (RefinementLinkExt.getRefinementRelatedTargetElements(anInterface, CsPackage.Literals.INTERFACE).size() != 0) {
      ArrayList<ExchangeItemAllocation> listOpToRemove = new ArrayList<ExchangeItemAllocation>();
      for (ExchangeItemAllocation currentOp : anInterface.getOwnedExchangeItemAllocations()) {
        if (cleanAll || isRefinementTraceExist(currentOp)) {
          listOpToRemove.add(currentOp);
        }
      }
      for (ExchangeItemAllocation operation : listOpToRemove) {
        // Remove traceability link and Operation
        CapellaElementExt.cleanTraces(operation);
        anInterface.getOwnedExchangeItemAllocations().remove(operation);
      }

      if (cleanAll || (anInterface.getOwnedExchangeItemAllocations().size() == 0)) {
        // Internal Interface is empty : Remove it
        CapellaElementExt.cleanTraces(anInterface);
        InterfacePkg pkg = (InterfacePkg) anInterface.eContainer();
        pkg.getOwnedInterfaces().remove(anInterface);
      }
    }
  }

  /**
   * Removes all unused Interfaces from the LogicalComponent
   * 
   * @param lc
   *          the LogicalComponent
   * @param pairs
   *          list of DecompositionItem
   */
  void removeUnImplementedInterfaces(LogicalComponent lc, List<DecompositionItem> pairs) {
    List<InterfaceImplementation> unimplementedInterfaces = new ArrayList<InterfaceImplementation>(1);
    for (InterfaceImplementation interfaceImpl : lc.getImplementedInterfaceLinks()) {
      boolean flag = true;
      for (DecompositionItem pair : pairs) {
        if (pair.getValue().equals(interfaceImpl.getImplementedInterface())) {
          flag = false;
          break;
        }
      }
      if (flag) {
        unimplementedInterfaces.add(interfaceImpl);
      }
    }

    // Remove Internal Interface when all owned Operation is delegated kind
    for (InterfaceImplementation implLink : unimplementedInterfaces) {
      cleanInternalInterface(implLink.getImplementedInterface(), false);
    }

    lc.getOwnedInterfaceImplementations().remove(unimplementedInterfaces);
  }

  /**
   * @return the controller
   */
  public LCDecompositionController getController() {
    return _controller;
  }

  /**
   * @param controller
   *          the controller to set
   */
  public void setController(LCDecompositionController controller) {
    _controller = controller;
  }

  private boolean isMultipartDriven(ModelElement element) {
    return TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(element));
  }
}
