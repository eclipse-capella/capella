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
package org.polarsys.capella.core.transition.system.handlers.traceability;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerFactory;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.handler.command.DeleteStructureCommand;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.capella.core.transition.common.handlers.notify.INotifyChangeEvent;
import org.polarsys.capella.core.transition.common.handlers.notify.INotifyListener;
import org.polarsys.capella.core.transition.common.handlers.notify.NotifyHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityTraceHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.LinkTraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class RealizationLinkTraceabilityHandler extends LinkTraceabilityHandler implements ITraceabilityTraceHandler, INotifyListener {

  // A map to store which eClass to create according to source/target elements
  public static final String MAPPING_MAP = "_mPp";

  // All created realization links while transformation
  public static final String REALISATION_LINKS = "_rL";
  public static final String REALISATION_LINKS_UNATTACHED = "_rLu";

  public static final String DEFAULT_OWNER = "DEFAULT_OWNER";

  protected class RealizationLinkMapping {
    protected EClass source;

    protected EClass target;

    protected EClass realizationLink;

    protected EStructuralFeature feature;

    /**
     * @param source
     * @param target
     * @param realizationLink
     * @param feature
     */
    public RealizationLinkMapping(EClass source, EClass target, EClass realizationLink, EStructuralFeature feature) {
      super();
      this.source = source;
      this.target = target;
      this.realizationLink = realizationLink;
      this.feature = feature;
    }

    @Override
    public String toString() {
      return source.getName() + " " + target.getName() + " " + realizationLink.getName();
    }

    /**
     * @param sourceElement
     * @param targetElement
     * @param context
     * @return
     */
    public boolean isValid(EObject sourceElement, EObject targetElement, IContext context) {
      if (sourceElement == null) {
        return false;
      }
      if (targetElement == null) {
        return false;
      }
      return true;
    }

    /**
     * @param targetElement
     * @param context
     * @return
     */
    public boolean isValidSource(EObject targetElement, IContext context) {
      if (targetElement == null) {
        return false;
      }
      return source.equals(targetElement.eClass());
    }

    /**
     * @param targetElement
     * @param context
     * @return
     */
    public boolean isValidTarget(EObject targetElement, IContext context) {
      if (targetElement == null) {
        return false;
      }
      return target.equals(targetElement.eClass());
    }
  }

  /**
   * @return the defaultMapping
   */
  public RealizationLinkMapping getDefaultMapping() {
    return defaultMapping;
  }

  private RealizationLinkMapping defaultMapping = new RealizationLinkMapping(ModellingcorePackage.Literals.TRACEABLE_ELEMENT,
      ModellingcorePackage.Literals.TRACEABLE_ELEMENT, CapellacommonPackage.Literals.TRANSFO_LINK, CapellacorePackage.Literals.NAMESPACE__OWNED_TRACES) {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(EObject sourceElement, EObject targetElement, IContext context) {
      if (sourceElement instanceof BlockArchitecture) {
        if (targetElement instanceof BlockArchitecture) {
          return false;
        }
      }

      if (sourceElement instanceof ComponentExchange) {
        if (targetElement instanceof Interface) {
          return false;
        }
      }

      return super.isValid(sourceElement, targetElement, context);
    }

    @Override
    public boolean isValidSource(EObject targetElement, IContext context) {
      return source.isInstance(targetElement);
    }

    /**
     * @param targetElement
     * @param context
     * @return
     */
    @Override
    public boolean isValidTarget(EObject targetElement, IContext context) {
      return target.isInstance(targetElement);
    }

  };

  private String _realizationIdentifier = null;

  public RealizationLinkTraceabilityHandler(String identifier) {
    super(identifier);
    _realizationIdentifier = REALISATION_LINKS + getIdentifier();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context) {
    NotifyHandlerHelper.getInstance(context).addListener(ITransitionConstants.NOTIFY__END_TRANSFORMATION, this, context);
    return super.init(context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context) {
    disposeUnattachedElements(context);
    return super.dispose(context);
  }

  /**
   * @param context
   */
  private void disposeUnattachedElements(IContext context) {
    Collection<EObject> traces = Collections.singletonList((EObject) getDefaultOwner(context));
    if (!traces.isEmpty()) {
      DeleteStructureCommand command =
          new DeleteStructureCommand((TransactionalEditingDomain) context.get(ITransitionConstants.TRANSITION_TARGET_EDITING_DOMAIN), getDefaultOwner(
              context).eContents(), true);
      if (command.canExecute()) {
        command.execute();
      }
    }
  }

  protected Namespace getDefaultOwner(IContext context) {
    if (!context.exists(DEFAULT_OWNER)) {
      Namespace owner = CapellamodellerFactory.eINSTANCE.createFolder();
      AttachmentHelper.getInstance(context).createdElement(owner, owner, context);
      context.put(DEFAULT_OWNER, owner);
    }
    return (Namespace) context.get(DEFAULT_OWNER);
  }

  protected Collection<AbstractTrace> getRealizationLinks(IContext context) {
    if (!context.exists(_realizationIdentifier)) {
      Collection<AbstractTrace> mapping = new ArrayList<AbstractTrace>();
      context.put(_realizationIdentifier, mapping);
    }
    return (Collection<AbstractTrace>) context.get(_realizationIdentifier);
  }

  protected Collection<RealizationLinkMapping> getMappings(IContext iContext1) {

    if (!iContext1.exists(MAPPING_MAP)) {
      Collection<RealizationLinkMapping> mapping = new ArrayList<RealizationLinkMapping>();
      iContext1.put(MAPPING_MAP, mapping);

      // miscellaneous realizations
      mappingAdd(mapping, new RealizationLinkMapping(CsPackage.Literals.INTERFACE, CsPackage.Literals.INTERFACE,
          PaPackage.Literals.LOGICAL_INTERFACE_REALIZATION, CsPackage.Literals.INTERFACE_ALLOCATOR__OWNED_INTERFACE_ALLOCATIONS) {
        @Override
        public boolean isValid(EObject sourceElement, EObject targetElement, IContext context) {
          return !CapellaLayerCheckingExt.isAOrInContextLayer((CapellaElement) sourceElement);
        }
      });

      mappingAdd(mapping, new RealizationLinkMapping(CsPackage.Literals.INTERFACE, CsPackage.Literals.INTERFACE,
          LaPackage.Literals.CONTEXT_INTERFACE_REALIZATION, CsPackage.Literals.INTERFACE_ALLOCATOR__OWNED_INTERFACE_ALLOCATIONS) {
        @Override
        public boolean isValid(EObject sourceElement, EObject targetElement, IContext context) {
          return CapellaLayerCheckingExt.isAOrInContextLayer((CapellaElement) sourceElement);
        }
      });

      mappingAdd(mapping, new RealizationLinkMapping(FaPackage.Literals.COMPONENT_EXCHANGE, FaPackage.Literals.COMPONENT_EXCHANGE,
          FaPackage.Literals.COMPONENT_EXCHANGE_REALIZATION, FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS));

      mappingAdd(mapping, new RealizationLinkMapping(CsPackage.Literals.PHYSICAL_LINK, CsPackage.Literals.PHYSICAL_LINK,
          CsPackage.Literals.PHYSICAL_LINK_REALIZATION, CsPackage.Literals.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_REALIZATIONS));

      mappingAdd(mapping, new RealizationLinkMapping(CsPackage.Literals.PHYSICAL_PATH, CsPackage.Literals.PHYSICAL_PATH,
          CsPackage.Literals.PHYSICAL_PATH_REALIZATION, CsPackage.Literals.PHYSICAL_PATH__OWNED_PHYSICAL_PATH_REALIZATIONS));

      mappingAdd(mapping, new RealizationLinkMapping(OaPackage.Literals.COMMUNICATION_MEAN, FaPackage.Literals.COMPONENT_EXCHANGE,
          FaPackage.Literals.COMPONENT_EXCHANGE_REALIZATION, FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS));

      mappingAdd(mapping, new RealizationLinkMapping(FaPackage.Literals.FUNCTIONAL_EXCHANGE, FaPackage.Literals.FUNCTIONAL_EXCHANGE,
          FaPackage.Literals.FUNCTIONAL_EXCHANGE_REALIZATION, FaPackage.Literals.FUNCTIONAL_EXCHANGE__OWNED_FUNCTIONAL_EXCHANGE_REALIZATIONS));

      mappingAdd(mapping, new RealizationLinkMapping(FaPackage.Literals.COMPONENT_EXCHANGE, FaPackage.Literals.FUNCTIONAL_EXCHANGE,
          FaPackage.Literals.COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION,
          FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS));

      mappingAdd(mapping, new RealizationLinkMapping(InformationPackage.Literals.PORT, InformationPackage.Literals.PORT,
          InformationPackage.Literals.PORT_REALIZATION, InformationPackage.Literals.PORT__OWNED_PORT_REALIZATIONS));

      mappingAdd(mapping, new RealizationLinkMapping(FaPackage.Literals.COMPONENT_PORT, FaPackage.Literals.COMPONENT_PORT,
          InformationPackage.Literals.PORT_REALIZATION, InformationPackage.Literals.PORT__OWNED_PORT_REALIZATIONS));

      // scenario realizations
      mappingAdd(mapping, new RealizationLinkMapping(InteractionPackage.Literals.SCENARIO, InteractionPackage.Literals.SCENARIO,
          InteractionPackage.Literals.SCENARIO_REALIZATION, InteractionPackage.Literals.SCENARIO__OWNED_SCENARIO_REALIZATION));

      // capability realizations
      mappingAdd(mapping, new RealizationLinkMapping(OaPackage.Literals.OPERATIONAL_CAPABILITY, CtxPackage.Literals.CAPABILITY,
          InteractionPackage.Literals.ABSTRACT_CAPABILITY_REALIZATION, InteractionPackage.Literals.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(CtxPackage.Literals.CAPABILITY, LaPackage.Literals.CAPABILITY_REALIZATION,
          InteractionPackage.Literals.ABSTRACT_CAPABILITY_REALIZATION, InteractionPackage.Literals.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(LaPackage.Literals.CAPABILITY_REALIZATION, LaPackage.Literals.CAPABILITY_REALIZATION,
          InteractionPackage.Literals.ABSTRACT_CAPABILITY_REALIZATION, InteractionPackage.Literals.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS));

      // state machine realizations
      mappingAdd(mapping, new RealizationLinkMapping(CapellacommonPackage.Literals.CHOICE_PSEUDO_STATE, CapellacommonPackage.Literals.CHOICE_PSEUDO_STATE,
          CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION, CapellacommonPackage.Literals.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(CapellacommonPackage.Literals.FORK_PSEUDO_STATE, CapellacommonPackage.Literals.FORK_PSEUDO_STATE,
          CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION, CapellacommonPackage.Literals.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(CapellacommonPackage.Literals.INITIAL_PSEUDO_STATE, CapellacommonPackage.Literals.INITIAL_PSEUDO_STATE,
          CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION, CapellacommonPackage.Literals.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(CapellacommonPackage.Literals.JOIN_PSEUDO_STATE, CapellacommonPackage.Literals.JOIN_PSEUDO_STATE,
          CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION, CapellacommonPackage.Literals.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(CapellacommonPackage.Literals.TERMINATE_PSEUDO_STATE,
          CapellacommonPackage.Literals.TERMINATE_PSEUDO_STATE, CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION,
          CapellacommonPackage.Literals.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(CapellacommonPackage.Literals.FINAL_STATE, CapellacommonPackage.Literals.FINAL_STATE,
          CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION, CapellacommonPackage.Literals.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(CapellacommonPackage.Literals.MODE, CapellacommonPackage.Literals.MODE,
          CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION, CapellacommonPackage.Literals.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(CapellacommonPackage.Literals.STATE, CapellacommonPackage.Literals.STATE,
          CapellacommonPackage.Literals.ABSTRACT_STATE_REALIZATION, CapellacommonPackage.Literals.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(CapellacommonPackage.Literals.STATE_TRANSITION, CapellacommonPackage.Literals.STATE_TRANSITION,
          CapellacommonPackage.Literals.STATE_TRANSITION_REALIZATION, CapellacommonPackage.Literals.STATE_TRANSITION__OWNED_STATE_TRANSITION_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(CapellacommonPackage.Literals.CHANGE_EVENT, CapellacommonPackage.Literals.CHANGE_EVENT,
          CapellacommonPackage.Literals.STATE_EVENT_REALIZATION, CapellacommonPackage.Literals.STATE_EVENT__OWNED_STATE_EVENT_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(CapellacommonPackage.Literals.TIME_EVENT, CapellacommonPackage.Literals.TIME_EVENT,
          CapellacommonPackage.Literals.STATE_EVENT_REALIZATION, CapellacommonPackage.Literals.STATE_EVENT__OWNED_STATE_EVENT_REALIZATIONS));

      // functional chain realizations
      mappingAdd(mapping, new RealizationLinkMapping(OaPackage.Literals.OPERATIONAL_PROCESS, FaPackage.Literals.FUNCTIONAL_CHAIN,
          FaPackage.Literals.FUNCTIONAL_CHAIN_REALIZATION, FaPackage.Literals.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(FaPackage.Literals.FUNCTIONAL_CHAIN, FaPackage.Literals.FUNCTIONAL_CHAIN,
          FaPackage.Literals.FUNCTIONAL_CHAIN_REALIZATION, FaPackage.Literals.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS));

      // information realizations
      mappingAdd(mapping, new RealizationLinkMapping(DatatypePackage.Literals.BOOLEAN_TYPE, DatatypePackage.Literals.BOOLEAN_TYPE,
          InformationPackage.Literals.INFORMATION_REALIZATION, DatatypePackage.Literals.DATA_TYPE__OWNED_INFORMATION_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(DatatypePackage.Literals.ENUMERATION, DatatypePackage.Literals.ENUMERATION,
          InformationPackage.Literals.INFORMATION_REALIZATION, DatatypePackage.Literals.DATA_TYPE__OWNED_INFORMATION_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(DatatypePackage.Literals.NUMERIC_TYPE, DatatypePackage.Literals.NUMERIC_TYPE,
          InformationPackage.Literals.INFORMATION_REALIZATION, DatatypePackage.Literals.DATA_TYPE__OWNED_INFORMATION_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(DatatypePackage.Literals.PHYSICAL_QUANTITY, DatatypePackage.Literals.PHYSICAL_QUANTITY,
          InformationPackage.Literals.INFORMATION_REALIZATION, DatatypePackage.Literals.DATA_TYPE__OWNED_INFORMATION_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(DatatypePackage.Literals.STRING_TYPE, DatatypePackage.Literals.STRING_TYPE,
          InformationPackage.Literals.INFORMATION_REALIZATION, DatatypePackage.Literals.DATA_TYPE__OWNED_INFORMATION_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(InformationPackage.Literals.CLASS, InformationPackage.Literals.CLASS,
          InformationPackage.Literals.INFORMATION_REALIZATION, InformationPackage.Literals.CLASS__OWNED_INFORMATION_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(InformationPackage.Literals.EXCHANGE_ITEM, InformationPackage.Literals.EXCHANGE_ITEM,
          InformationPackage.Literals.INFORMATION_REALIZATION, InformationPackage.Literals.EXCHANGE_ITEM__OWNED_INFORMATION_REALIZATIONS));

      // function realizations
      mappingAdd(mapping, new RealizationLinkMapping(FaPackage.Literals.ABSTRACT_FUNCTION, FaPackage.Literals.ABSTRACT_FUNCTION,
          FaPackage.Literals.FUNCTION_REALIZATION, FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(OaPackage.Literals.OPERATIONAL_ACTIVITY, CtxPackage.Literals.SYSTEM_FUNCTION,
          FaPackage.Literals.FUNCTION_REALIZATION, FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(CtxPackage.Literals.SYSTEM_FUNCTION, LaPackage.Literals.LOGICAL_FUNCTION,
          FaPackage.Literals.FUNCTION_REALIZATION, FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(LaPackage.Literals.LOGICAL_FUNCTION, PaPackage.Literals.PHYSICAL_FUNCTION,
          FaPackage.Literals.FUNCTION_REALIZATION, FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(FaPackage.Literals.FUNCTION_INPUT_PORT, FaPackage.Literals.FUNCTION_INPUT_PORT,
          InformationPackage.Literals.PORT_REALIZATION, InformationPackage.Literals.PORT__OWNED_PORT_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(FaPackage.Literals.FUNCTION_OUTPUT_PORT, FaPackage.Literals.FUNCTION_OUTPUT_PORT,
          InformationPackage.Literals.PORT_REALIZATION, InformationPackage.Literals.PORT__OWNED_PORT_REALIZATIONS));

      // component realizations
      mappingAdd(mapping, new RealizationLinkMapping(OaPackage.Literals.OPERATIONAL_ACTOR, CtxPackage.Literals.ACTOR,
          CtxPackage.Literals.OPERATIONAL_ACTOR_REALIZATION, CtxPackage.Literals.ACTOR__OWNED_OPERATIONAL_ACTOR_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(OaPackage.Literals.ENTITY, CtxPackage.Literals.ACTOR, CtxPackage.Literals.OPERATIONAL_ENTITY_REALIZATION,
          CtxPackage.Literals.ACTOR__OWNED_OPERATIONAL_ENTITY_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(CtxPackage.Literals.ACTOR, LaPackage.Literals.LOGICAL_ACTOR, LaPackage.Literals.SYSTEM_ACTOR_REALIZATION,
          LaPackage.Literals.LOGICAL_ACTOR__OWNED_SYSTEM_ACTOR_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(LaPackage.Literals.LOGICAL_ACTOR, PaPackage.Literals.PHYSICAL_ACTOR,
          PaPackage.Literals.LOGICAL_ACTOR_REALIZATION, PaPackage.Literals.PHYSICAL_ACTOR__OWNED_LOGICAL_ACTOR_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(OaPackage.Literals.ENTITY, CtxPackage.Literals.SYSTEM, CtxPackage.Literals.OPERATIONAL_ENTITY_REALIZATION,
          CtxPackage.Literals.SYSTEM__OWNED_ENTITY_REALIZATIONS));
      mappingAdd(mapping, new RealizationLinkMapping(CtxPackage.Literals.SYSTEM, LaPackage.Literals.LOGICAL_COMPONENT, LaPackage.Literals.SYSTEM_REALIZATION,
          LaPackage.Literals.LOGICAL_COMPONENT__OWNED_SYSTEM_REALIZATIONS));

      mappingAdd(mapping, new RealizationLinkMapping(LaPackage.Literals.LOGICAL_COMPONENT, PaPackage.Literals.PHYSICAL_COMPONENT,
          PaPackage.Literals.LOGICAL_COMPONENT_REALIZATION, PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_REALIZATIONS));

      // Architectures
      mappingAdd(mapping, new RealizationLinkMapping(OaPackage.Literals.OPERATIONAL_ANALYSIS, CtxPackage.Literals.SYSTEM_ANALYSIS,
          CtxPackage.Literals.OPERATIONAL_ANALYSIS_REALIZATION, CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_OPERATIONAL_ANALYSIS_REALIZATIONS));

      mappingAdd(mapping, new RealizationLinkMapping(CtxPackage.Literals.SYSTEM_ANALYSIS, LaPackage.Literals.LOGICAL_ARCHITECTURE,
          LaPackage.Literals.SYSTEM_ANALYSIS_REALIZATION, LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_SYSTEM_ANALYSIS_REALIZATIONS));

      mappingAdd(mapping, new RealizationLinkMapping(LaPackage.Literals.LOGICAL_ARCHITECTURE, PaPackage.Literals.PHYSICAL_ARCHITECTURE,
          PaPackage.Literals.LOGICAL_ARCHITECTURE_REALIZATION, PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_LOGICAL_ARCHITECTURE_REALIZATIONS));

      mappingAdd(mapping, new RealizationLinkMapping(PaPackage.Literals.PHYSICAL_ARCHITECTURE, EpbsPackage.Literals.EPBS_ARCHITECTURE,
          EpbsPackage.Literals.PHYSICAL_ARCHITECTURE_REALIZATION, EpbsPackage.Literals.EPBS_ARCHITECTURE__OWNED_PHYSICAL_ARCHITECTURE_REALIZATIONS));

      mappingAdd(mapping, new RealizationLinkMapping(CsPackage.Literals.PHYSICAL_PORT, CsPackage.Literals.PHYSICAL_PORT,
          CsPackage.Literals.PHYSICAL_PORT_REALIZATION, CsPackage.Literals.PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS));

      mappingAdd(mapping, new RealizationLinkMapping(CsPackage.Literals.PHYSICAL_PORT, CsPackage.Literals.PHYSICAL_LINK,
          CsPackage.Literals.PHYSICAL_LINK_REALIZATION, CsPackage.Literals.PHYSICAL_LINK__OWNED_PHYSICAL_LINK_REALIZATIONS));

      mappingAdd(mapping, new RealizationLinkMapping(CsPackage.Literals.PHYSICAL_PATH, CsPackage.Literals.PHYSICAL_PATH,
          CsPackage.Literals.PHYSICAL_PATH_REALIZATION, CsPackage.Literals.PHYSICAL_PATH__OWNED_PHYSICAL_PATH_REALIZATIONS));
    }

    return (Collection<RealizationLinkMapping>) iContext1.get(MAPPING_MAP);
  }

  /**
   * @param mapping
   * @param realizationLinkMapping
   */
  private void mappingAdd(Collection<RealizationLinkMapping> mapping, RealizationLinkMapping realizationLinkMapping) {
    mapping.add(realizationLinkMapping);
  }

  protected Collection<RealizationLinkMapping> getMappingsSource(EObject targetElement, IContext context) {

    Collection<RealizationLinkMapping> traces = new LinkedList<RealizationLinkMapping>();
    if (targetElement instanceof CapellaElement) {
      Collection<RealizationLinkMapping> map = getMappings(context);
      for (RealizationLinkMapping link : map) {
        if (link.isValidSource(targetElement, context)) { // link are inverted
          traces.add(link);
        }
      }
    }

    if (traces.isEmpty()) {
      traces.add(defaultMapping);
    }
    return traces;
  }

  protected Collection<RealizationLinkMapping> getMappingsTarget(EObject targetElement, IContext context) {

    Collection<RealizationLinkMapping> traces = new LinkedList<RealizationLinkMapping>();
    if (targetElement instanceof CapellaElement) {
      Collection<RealizationLinkMapping> map = getMappings(context);
      for (RealizationLinkMapping link : map) {
        if (link.isValidTarget(targetElement, context)) { // link are inverted
          traces.add(link);
        }
      }
    }

    if (traces.isEmpty()) {
      traces.add(defaultMapping);
    }
    return traces;
  }

  @Override
  protected List<EObject> getSourceAttachments(EObject targetElement, IContext context) {
    List<EObject> elements = new ArrayList<EObject>();

    Collection<RealizationLinkMapping> traces = getMappingsTarget(targetElement, context);
    if (targetElement instanceof TraceableElement) {
      for (AbstractTrace trace : ((TraceableElement) targetElement).getOutgoingTraces()) {
        for (RealizationLinkMapping link : traces) {
          if (isValidMapping(trace, link, context)) {
            elements.add(trace.getTargetElement()); // link is inverted
          }
        }
      }
    }
    return elements;
  }

  protected List<EObject> getTargetAttachments(EObject sourceElement, IContext context) {
    List<EObject> elements = new ArrayList<EObject>();

    Collection<RealizationLinkMapping> traces = getMappingsSource(sourceElement, context);
    if (sourceElement instanceof TraceableElement) {
      for (AbstractTrace trace : ((TraceableElement) sourceElement).getIncomingTraces()) {
        for (RealizationLinkMapping link : traces) {
          if (isValidMapping(trace, link, context)) {
            elements.add(trace.getSourceElement()); // link is inverted
          }
        }
      }
    }
    return elements;
  }

  /**
   * @param trace
   * @param link
   * @param context
   * @return
   */
  private boolean isValidMapping(AbstractTrace trace, RealizationLinkMapping link, IContext context) {
    EObject sourceElement = trace.getTargetElement(); // link is inverted
    EObject targetElement = trace.getSourceElement();

    if (link.realizationLink.isInstance(trace)) {
      if ((link.isValidSource(sourceElement, context)) && link.isValidTarget(targetElement, context)
          && link.isValid(sourceElement, targetElement, context)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void attachTraceability(EObject sourceElement, EObject targetElement, IContext context) {
    if (targetElement != null) { // we allow transformation one to nothing
      createAttachment(sourceElement, targetElement, context);
    }
  }

  protected RealizationLinkMapping getBestMapping(EObject sourceElement, EObject targetElement, IContext context) {
    Collection<RealizationLinkMapping> map = getMappings(context);

    RealizationLinkMapping mapping = defaultMapping;
    for (RealizationLinkMapping link : map) {
      if (link.isValidSource(sourceElement, context) && link.isValidTarget(targetElement, context)
          && link.isValid(sourceElement, targetElement, context)) {
        mapping = link;
        break;
      }
    }

    if (mapping == defaultMapping) {
      if (!(mapping.isValidSource(sourceElement, context) && mapping.isValidTarget(targetElement, context) && mapping.isValid(sourceElement,
          targetElement, context))) {
        mapping = null;
      }
    }
    return mapping;
  }

  protected void createAttachment(EObject sourceElement, EObject targetElement, IContext context) {
    RealizationLinkMapping mapping = getBestMapping(sourceElement, targetElement, context);

    if (mapping != null) {
      EObject link = ((EPackage) mapping.realizationLink.eContainer()).getEFactoryInstance().create(mapping.realizationLink);

      if (sourceElement instanceof TraceableElement) {
        if (targetElement instanceof TraceableElement) {
          // A trace is inverted!
          ((AbstractTrace) link).setSourceElement((TraceableElement) targetElement);
          ((AbstractTrace) link).setTargetElement((TraceableElement) sourceElement);
          getRealizationLinks(context).add((AbstractTrace) link);
        }
      }
    }
    addMappings(sourceElement, targetElement, context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isTrace(EObject element, IContext context) {

    if (element != null) {
      if (element instanceof TransfoLink) {
        return true;
      } else if (element.eClass().getName().endsWith("Realization")) {
        if (!(element instanceof CapabilityRealization)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getSourceElement(EObject trace, IContext context) {
    if (trace instanceof AbstractTrace) {
      return ((AbstractTrace) trace).getTargetElement(); // bottom up
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getTargetElement(EObject trace, IContext context) {
    if (trace instanceof AbstractTrace) {
      return ((AbstractTrace) trace).getSourceElement(); // bottom up
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void notifyChanged(INotifyChangeEvent event, IContext context) {

    Collection<RealizationLinkMapping> map = getMappings(context);
    HashMap<EClass, EStructuralFeature> refs = new HashMap<EClass, EStructuralFeature>();

    for (AbstractTrace realizationLink : getRealizationLinks(context)) {
      EObject sourceElement = realizationLink.getSourceElement();
      EObject targetElement = realizationLink.getTargetElement();

      EClass realizationLink2 = CapellacommonPackage.Literals.TRANSFO_LINK;
      EStructuralFeature feature2 = CapellacorePackage.Literals.NAMESPACE__OWNED_TRACES;

      for (RealizationLinkMapping link : map) {
        if (link.realizationLink.isInstance(realizationLink)) {

          if (link.target.equals(sourceElement.eClass()) && link.source.equals(targetElement.eClass())) {
            realizationLink2 = link.realizationLink;
            feature2 = link.feature;
            break;
          }
        }
      }

      if (realizationLink.eContainer() == null) {
        EObject parent = sourceElement;
        EClass parentFeature = (EClass) feature2.eContainer();

        boolean isAttached = false;

        while (parent != null) {
          if (parentFeature.isInstance(parent)) {
            try {
              ((EList) (parent.eGet(feature2))).add(realizationLink);
              isAttached = true;
              break;

            } catch (Exception e) {
              // Continue to next parent. realizationLink is not valid.
              LogHelper.getInstance().debug(
                  NLS.bind("Realization link ''{0}'' cannot be attached into ''{1}''", realizationLink.eClass().getName(), parent.eClass().getName()),
                  ITransitionConstants.ATTACHMENT_HANDLER);

            }
          }

          parent = parent.eContainer();
        }

        if (!isAttached) {
          getDefaultOwner(context).getOwnedTraces().add((Trace) realizationLink);
        }
      }
    }
  }
}
