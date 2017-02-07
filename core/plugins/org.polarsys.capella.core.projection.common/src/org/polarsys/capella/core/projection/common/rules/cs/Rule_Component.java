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
package org.polarsys.capella.core.projection.common.rules.cs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentContext;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.preferences.IProjectionPreferences;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.projection.preferences.ProjectionPreferencesPlugin;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;

public class Rule_Component extends Rule_CapellaElement {

  protected boolean _isItfProjectionHandle = IProjectionPreferences.DEFAULT_INTERFACE_PROJECTION.booleanValue();

  protected boolean _isStateMachineProjectionHandle = IProjectionPreferences.DEFAULT_STATE_MACHINE_PROJECTION.booleanValue();

  protected List<Interface> alreadyTransitioned;

  protected EReference attachmentSource;
  protected EReference attachmentTarget;

  /**
   * @param sourceType_p
   * @param targetType_p
   * @param specificLinkKind_p
   */
  public Rule_Component() {
    this(CsPackage.Literals.COMPONENT, CsPackage.Literals.COMPONENT, CsPackage.Literals.COMPONENT_ALLOCATION);
  }

  public Rule_Component(EClass source, EClass target, EClass realization) {
    super(source, target, realization);
    setNeedsContext(true);
    alreadyTransitioned = new ArrayList<Interface>();
    _updatedAttributes.add(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__ABSTRACT.getName());
  }

  public Rule_Component(EClass source, EClass target) {
    super(source, target);
    setNeedsContext(true);
    alreadyTransitioned = new ArrayList<Interface>();
    _updatedAttributes.add(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__ABSTRACT.getName());
  }

  public Rule_Component(EClass source, EClass target, EClass realization, EReference attachmentSource_p, EReference attachmentTarget_p) {
    this(source, target, realization);
    this.attachmentSource = attachmentSource_p;
    this.attachmentTarget = attachmentTarget_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    EObject transfoSource = (EObject) context_p.get(TransfoEngine.TRANSFO_SOURCE);
    if (transfoSource instanceof Component) {
      if (!((transfoSource == element_p) || ComponentExt.isComponentAncestor((Component) transfoSource, (Component) element_p))) {
        return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, NLS.bind(ProjectionMessages.OutOfScope,
            EObjectLabelProviderHelper.getText(element_p)));
      }
    }
    if (transfoSource instanceof LogicalActorPkg) {
      return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, NLS.bind(ProjectionMessages.OutOfScope,
          EObjectLabelProviderHelper.getText(element_p)));
    }
    return Status.OK_STATUS;
  }

  /**
   * Perform Interface(s) projection manipulated (used/implement) by the current LC
   * @param transfo_p
   */
  private void interfacesProjectionProcessing(Component currentLC_p, ITransfo transfo_p) {
    Collection<Interface> itfManipulated = ComponentExt.getRelatedInterfaces(currentLC_p);

    if (currentLC_p.getOwnedInterfacePkg() != null) {
      runSubTransition(currentLC_p.getOwnedInterfacePkg(), transfo_p);
    }

    for (Interface itf : itfManipulated) {

      if (!alreadyTransitioned.contains(itf)) {
        runSubTransition(itf, transfo_p);
      }
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    EObject result = super.transformDirectElement(element_p, context_p);
    if (result instanceof PhysicalComponent) {
      ((PhysicalComponent) result).setNature(PhysicalComponentNature.BEHAVIOR);
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    if (container_p instanceof PhysicalComponentPkg) {
      if (result_p instanceof PhysicalComponentPkg) {
        return PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENT_PKGS;
      } else if (result_p instanceof PhysicalComponent) {
        return PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_COMPONENTS;
      }

    } else if (container_p instanceof PhysicalComponent) {
      if (result_p instanceof PhysicalComponentPkg) {
        return PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS;
      } else if (result_p instanceof PhysicalComponent) {
        return PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS;
      }
    }

    return super.getTargetContainementFeature(element_p, result_p, container_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject container = element_p.eContainer();
    if ((container != null) && (container instanceof Component)) {
      EObject parent = container;
      while (parent != null) {
        if (Query.isElementTransformed(parent, context_p.getTransfo())) {
          EObject targetContainer = Query.retrieveFirstTransformedElement(parent, context_p.getTransfo(), CsPackage.Literals.COMPONENT);
          if (targetContainer == null) {
            targetContainer = Query.retrieveFirstTransformedElement(parent, context_p.getTransfo());
          }
          return targetContainer;
        }
        parent = parent.eContainer();
      }
    }

    return super.getBestContainer(element_p, result_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {

    EObject sourceContainer = element_p.eContainer();

    if (sourceContainer instanceof Component) {
      BlockArchitecture architecture = (BlockArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
      if (architecture != null) {
        Component rootComponent = BlockArchitectureExt.getFirstComponent(architecture);
        return rootComponent;
      }
    }

    return super.getDefaultContainer(element_p, result_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    Component sourceElement = (Component) element_p;

    //Display a message if the component was previously transitioned to a pkg
    Object targetIp2 = Query.retrieveTransformedElement(sourceElement, context_p.getTransfo(), CsPackage.Literals.COMPONENT);
    if (targetIp2 != null) {
      Object test = Query.retrieveTransformedElement(sourceElement, context_p.getTransfo(), PaPackage.Literals.PHYSICAL_COMPONENT_PKG);
      if (test != null) {
        notifyMessage(NLS.bind(ProjectionMessages.ComponentWasTransitionedToPackage, EObjectLabelProviderHelper.getText(sourceElement)),
            new Object[] { sourceElement, test, targetIp2 }, ReportManagerConstants.LOG_LEVEL_WARN, context_p.getTransfo());
      }
    }
  }

  @Override
  public List<EObject> retrieveRelatedElements_(EObject element_p, ITransfo transfo_p) {
    _isItfProjectionHandle = ProjectionPreferencesPlugin.getDefault().transitionInterfaceWhileComponentTransition();
    _isStateMachineProjectionHandle = ProjectionPreferencesPlugin.getDefault().transitionStateMachineWhileComponentTransition();
    return super.retrieveRelatedElements_(element_p, transfo_p);
  }

  @Override
  protected void runSubTransition(EObject element_p, ITransfo transfo_p) {
    //Perform Interface(s) projection manipulated (used/implement) by the current LC
    //Only in according with user preference)
    if (element_p instanceof Component) {
      Component sourceElement = (Component) element_p;
      if (_isItfProjectionHandle) {
        interfacesProjectionProcessing(sourceElement, transfo_p);
      }

      if (_isStateMachineProjectionHandle) {
        if (sourceElement.getOwnedStateMachines().size() > 0) {
          for (StateMachine machine : sourceElement.getOwnedStateMachines()) {
            runSubTransition(machine, transfo_p);
          }
        }
      }
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    retrieveComponentGoDeep(source_p, result_p, context_p);
  }

  protected void retrieveComponentGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    Component sourceElement = (Component) source_p;
    EObject transfoSource = (EObject) getTransfo().get(TransfoEngine.TRANSFO_SOURCE);

    //Add the related part if we are in one part mode
    for (Partition partition : sourceElement.getRepresentingPartitions()) {

      if (TriStateBoolean.False.equals(CapellaProjectHelper.isReusableComponentsDriven(partition))
          || ((partition instanceof Part) && (partition.getType() != null) && (partition.eContainer() != null) && (partition.eContainer() instanceof ComponentContext))) {
        result_p.add(partition);
      }
    }

    if (EcoreUtil2.isOrIsContainedBy(sourceElement, transfoSource)) {

      // Retrieve Part for the current LC and StandardPort
      for (Partition partition : sourceElement.getOwnedPartitions()) {
        if ((partition instanceof Part) && (partition.getType() != null)) {
          result_p.add(partition);
        } else if (partition instanceof ComponentPort) {
          result_p.add(partition);
        }
      }

      // Retrieve Use/Implement link between current LC and Interfaces
      for (InterfaceUse use : sourceElement.getUsedInterfaceLinks()) {
        result_p.add(use);
      }
      for (InterfaceImplementation impl : sourceElement.getImplementedInterfaceLinks()) {
        result_p.add(impl);
      }

      // Retrieve ComponentFunctionalAllocation link between current component and functions
      // This should be disabled here for System, scenario transition can trigger a transition of System and we don't 
      // want component functional allocation of system being transitioned
      if (!(sourceElement instanceof System)) {
        for (ComponentFunctionalAllocation compFuncAlloc : sourceElement.getFunctionalAllocations()) {
          result_p.add(compFuncAlloc);
        }
      }

      if (sourceElement instanceof LogicalComponent) {
        // Retrieve sub LogicalComponentPkg from the current LC
        for (LogicalComponentPkg pkg : ((LogicalComponent) sourceElement).getOwnedLogicalComponentPkgs()) {
          result_p.add(pkg);
        }

        // Retrieve sub LogicalComponent from the current LC
        for (LogicalComponent subLc : ((LogicalComponent) sourceElement).getOwnedLogicalComponents()) {
          result_p.add(subLc);
        }
      }

      for (CommunicationLink link : sourceElement.getOwnedCommunicationLinks()) {
        result_p.add(link);
      }

      for (Generalization generalization : sourceElement.getOwnedGeneralizations()) {
        result_p.add(generalization);
      }

      for (ComponentExchange connection : sourceElement.getOwnedComponentExchanges()) {
        result_p.add(connection);
      }

      // exporting componentExchanges
      for (Feature feature : sourceElement.getOwnedFeatures()) {
        if (feature instanceof ComponentPort) {
          ComponentPort fp = (ComponentPort) feature;
          result_p.add(fp);
          for (ComponentExchange connection : fp.getComponentExchanges()) {
            if (TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(connection, context_p).isOK()) {
              result_p.add(connection);
            }
          }
        }
      }

    }
  }

}
