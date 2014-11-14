/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.scenarios.core;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.ExtensionPriorityComparator;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.helpers.interaction.services.AbstractEndExt;
import org.polarsys.capella.core.data.helpers.interaction.services.MessageEndExt;
import org.polarsys.capella.core.data.helpers.interaction.services.MessageEndExt.COMPONENT_TYPE;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.FragmentEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionState;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.model.helpers.SequenceMessageExt;
import org.polarsys.capella.core.refinement.scenarios.core.datastructures.Node;
import org.polarsys.capella.core.refinement.scenarios.core.datastructures.ScenarioRepresentation;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ProcessorException;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.RefinementException;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IMapper;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IResolver;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IScheduler;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public abstract class ScenarioRefinement implements IProcessor {

	private Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);
	private static final int _MESSAGE_PROGRESS_STEP = 5;
	private static final int _PRE_POST_PROCESSOR_PROGRESS_STEP = 10;
	private static final String POSTPROCESSING_EXTENSION_ID = "postprocessingExtension"; //$NON-NLS-1$
	private static final String SCHEDULING_EXTENSION_ID = "schedulingExtension"; //$NON-NLS-1$
	private static final String RESOLVING_EXTENSION_ID = "resolvingExtension"; //$NON-NLS-1$
	private static final String MAPPING_EXTENSION_ID = "mappingExtension"; //$NON-NLS-1$
	private static final String PREPROCESSING_EXTENSION_ID = "preprocessingExtension"; //$NON-NLS-1$
	private static final String REFINEMENT_FRAMEWORK_PLUGIN_ID = "org.polarsys.capella.core.refinement.framework"; //$NON-NLS-1$

  /**
   * Scenario that will be refined
   */
  protected Scenario _srcDiagram = null;
  protected Scenario _tgtDiagram = null;

  /**
   * Refinement targets (can be either a 'ComponentArchitecture', or a 'LogicalComponent')
   */
  protected NamedElement _tgtElement = null;
  protected boolean _isIntraLayerRefinement = false;
  private boolean _loadAdditionalProcessors = true;
  private IScheduler _pluggedSchedulers = null;
  private List<IMapper> _pluggedMappers = null;
  private List<IProcessor> _pluggedPreprocessors = null;
  private List<IProcessor> _pluggedPostprocessors = null;
  private List<IResolver> _pluggedResolvers = null;

  /**
   * Constructor
   * 
   * @param srcDiagram_p
   * @param target_p
   */
  public ScenarioRefinement(Scenario srcDiagram_p, NamedElement target_p) {
    this(srcDiagram_p, target_p, false);
  }

  /**
   * Constructor
   * 
   * @param srcDiagram_p
   * @param target_p
   * @param isIntraLayer_p
   */
  public ScenarioRefinement(Scenario srcDiagram_p, NamedElement target_p, boolean isIntraLayer_p) {
    this(srcDiagram_p, target_p, isIntraLayer_p, true);
  }

  /**
   * Constructor
   * 
   * @param srcDiagram_p
   * @param target_p
   * @param isIntraLayer_p
   * @param loadAdditionalProcessors_p
   */
  public ScenarioRefinement(Scenario srcDiagram_p, NamedElement target_p, boolean isIntraLayer_p, boolean loadAdditionalProcessors_p) {
    _srcDiagram = srcDiagram_p;
    _tgtElement = target_p;
    _isIntraLayerRefinement = isIntraLayer_p;
    _loadAdditionalProcessors = loadAdditionalProcessors_p;

    /** */
    addPluggedModules();
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#getName()
   */
  public Object getName() {
    return "Scenario Refinement"; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#getResult()
   */
  public Object getResult() {
    return _tgtDiagram;
  }

  /**
   * 
   */
  public boolean isIntraLayerRefinement() {
    return _isIntraLayerRefinement;
  }

  /**
   * 
   * @param tgtDiagram_p
   */
  protected void forceTargetScenario(Scenario tgtDiagram_p) {
    _tgtDiagram = tgtDiagram_p;
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(java.util.List)
   * 
   * @param context_p
   */
  public void setContext(List<ModelElement> context_p) {
    if ((context_p != null) && (context_p.size()>0)) {
      setContext(context_p.get(0));
    }
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(org.polarsys.capella.core.common.model.CapellaElement)
   * 
   * @param context_p
   */
  public void setContext(ModelElement context_p) {
    if (context_p instanceof Scenario) {
      _srcDiagram = (Scenario) context_p;

      /** update preprocessors */
      for (IProcessor processor : _pluggedPreprocessors) {
        processor.setContext(context_p);
      }
      /** update post-processors */
      for (IProcessor processor : _pluggedPostprocessors) {
        processor.setContext(context_p);
      }
    }
  }

  /**
   * @return
   */
  public Scenario getContext() {
    return _srcDiagram;
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setTarget(org.polarsys.capella.core.common.model.NamedElement)
   * 
   * @param target_p
   */
  public void setTarget(NamedElement target_p) {
    _tgtElement = target_p;

    /** update preprocessors */
    for (IProcessor processor : _pluggedPreprocessors) {
      processor.setTarget(target_p);
    }
    /** update post-processors */
    for (IProcessor processor : _pluggedPostprocessors) {
      processor.setTarget(target_p);
    }
  }

  /**
   * @return
   */
  public NamedElement getTarget() {
    if (_tgtElement instanceof Part) {
      return ((Part) _tgtElement).getType();
    }
    return _tgtElement;
  }

  /**
   * Appends a new mapper at the end of the mappers list.
   * @param mapper_p
   */
  private void addPlug(IMapper mapper_p) {
    addPlug(-1, mapper_p);
  }

  /**
   * Appends a new resolver at the end of the resolvers list.
   * @param resolver_p
   */
  private void addPlug(IResolver resolver_p) {
    addPlug(-1, resolver_p);
  }

  /**
   * Sets a new scheduler.
   * @param scheduler_p
   */
  private void addPlug(IScheduler scheduler_p) {
    _pluggedSchedulers = scheduler_p;
  }

  /**
   * Appends a new processor at the end of the processors list.
   * @param processor_p
   * @param type_p
   */
  private void addPlug(IProcessor processor_p, IProcessor.ProcessingType type_p) {
    addPlug(-1, processor_p, type_p);
  }

  /**
   * Adds a new mapper to the mappers list at the 'order' position.
   * @param order
   * @param mapper
   */
  private void addPlug(int order, IMapper mapper) {
    if (null == _pluggedMappers) {
      _pluggedMappers = new ArrayList<IMapper>();
    }
    if ((order < 0) || (order > _pluggedMappers.size()))
      _pluggedMappers.add(mapper);
    else
      _pluggedMappers.add(order, mapper);
  }

  /**
   * Adds a new resolver to the resolvers list at the 'order' position.
   * @param order_p
   * @param resolver_p
   */
  private void addPlug(int order_p, IResolver resolver_p) {
    if (null == _pluggedResolvers) {
      _pluggedResolvers = new ArrayList<IResolver>();
    }
    if ((order_p < 0) || (order_p > _pluggedMappers.size()))
      _pluggedResolvers.add(resolver_p);
    else
      _pluggedResolvers.add(order_p, resolver_p);
  }

  /**
   * Adds a new preprocessor to the preprocessors list at the 'order' position.
   * @param order_p
   * @param processor_p
   * @param type_p
   */
  private void addPlug(int order_p, IProcessor processor_p, IProcessor.ProcessingType type_p) {
    switch (type_p) {
      case PREPROCESSING: {
        if (null == _pluggedPreprocessors) {
          _pluggedPreprocessors = new ArrayList<IProcessor>();
        }
        if ((order_p < 0) || (order_p > _pluggedPreprocessors.size()))
          _pluggedPreprocessors.add(processor_p);
        else
          _pluggedPreprocessors.add(order_p, processor_p);
        break;
      }
      case POSTPROCESSING: {
        if (null == _pluggedPostprocessors) {
          _pluggedPostprocessors = new ArrayList<IProcessor>();
        }
        if ((order_p < 0) || (order_p > _pluggedPostprocessors.size()))
          _pluggedPostprocessors.add(processor_p);
        else
          _pluggedPostprocessors.add(order_p, processor_p);
      }
    }
  }

  /**
   * This method returns TRUE if the element {@link use_p} interacts with the element {@link elt_p}.
   * 
   * @param msg_p
   * @param elt_p
   */
  protected boolean isInteracting(InteractionUse use_p, NamedElement elt_p) {
    boolean startResult = false;
    boolean finishResult = false;

    if ((use_p != null) && (elt_p != null)) {
      FragmentEnd startEnd = (FragmentEnd) use_p.getStart();
      if (startEnd != null) {
        startResult = isInteracting(startEnd, elt_p);
      }
      FragmentEnd finishEnd = (FragmentEnd) use_p.getFinish();
      if (finishEnd != null) {
        finishResult = isInteracting(finishEnd, elt_p);
      }
    }

    return (startResult || finishResult);
  }

  /**
   * This method returns TRUE if the message {@link msg_p} interacts with the element {@link elt_p}.
   * 
   * @param msg_p
   * @param elt_p
   */
  private boolean isInteracting(SequenceMessage msg_p, NamedElement elt_p) {
    boolean sndResult = false;
    boolean rcvResult = false;

    if ((msg_p != null) && (elt_p != null)) {
      MessageEnd sndMsgEnd = msg_p.getSendingEnd();
      if (sndMsgEnd != null) {
        sndResult = isInteracting(sndMsgEnd, elt_p);
      }
      MessageEnd rcvMsgEnd = msg_p.getReceivingEnd();
      if (rcvMsgEnd != null) {
        rcvResult = isInteracting(rcvMsgEnd, elt_p);
      }
    }

    return (sndResult || rcvResult);
  }

  /**
   * This method returns TRUE if the execution end {@link execEnd_p} interacts with the element {@link elt_p}.
   * 
   * @param execEnd_p
   * @param elt_p
   */
  private boolean isInteracting(ExecutionEnd execEnd_p, NamedElement elt_p) {
    boolean result = false;

    if ((execEnd_p != null) && (elt_p != null)) {
      result = isInteracting((AbstractEnd) execEnd_p, elt_p);

      /**
       * The sending message is also tested
       */
      if (!result) {
        Execution exec = execEnd_p.getExecution();
        if (exec != null) {
          MessageEnd msgEnd = MessageEndExt.getOppositeMessageEnd((MessageEnd) exec.getStart());
          if (msgEnd != null) {
            result = isInteracting(msgEnd, elt_p);
          }
        }
      }
    }

    return result;
  }

  /**
   * This method returns TRUE if the message end {@link absEnd_p} interacts with the element {@link elt_p}.
   * 
   * @param absEnd_p
   * @param elt_p
   */
  private boolean isInteracting(AbstractEnd absEnd_p, NamedElement elt_p) {
    boolean result = false;

    if ((absEnd_p != null) && (elt_p != null)) {
      InstanceRole instRole = absEnd_p.getCovered();
      if (instRole != null) {
        AbstractInstance inst = instRole.getRepresentedInstance();
        if (inst != null) {
          if (elt_p instanceof Part) {
            result = elt_p.equals(inst);
          } else {
            AbstractType type = inst.getType();
            if (type instanceof AbstractExchangeItem) {
              for (Object objectRef : EObjectExt.getReferencers(type, CommunicationPackage.Literals.COMMUNICATION_LINK__EXCHANGE_ITEM)) {
                EObject owner = ((CommunicationLink) objectRef).eContainer();
                if (elt_p.equals(owner)) return true;
              }
            } else {
              result = elt_p.equals(type);
            }
          }
        }
      }
    }

    return result;
  }

  /**
   * This method returns TRUE if the fragment end {@link absEnd_p} interacts with the element {@link elt_p}.
   * 
   * @param absEnd_p
   * @param elt_p
   */
  private boolean isInteracting(FragmentEnd absEnd_p, NamedElement elt_p) {
    boolean result = false;

    if ((absEnd_p != null) && (elt_p != null)) {
      for (InstanceRole instRole : absEnd_p.getCoveredInstanceRoles()) {
        AbstractInstance inst = instRole.getRepresentedInstance();
        if (inst != null) {
          if (elt_p instanceof Part) {
            result |= elt_p.equals(inst);
          } else {
            AbstractType type = inst.getType();
            if (type instanceof AbstractExchangeItem) {
              for (Object objectRef : EObjectExt.getReferencers(type, CommunicationPackage.Literals.COMMUNICATION_LINK__EXCHANGE_ITEM)) {
                EObject owner = ((CommunicationLink) objectRef).eContainer();
                if (elt_p.equals(owner)) return true;
              }
            } else {
              result |= elt_p.equals(type);
            }
          }
        }
      }
    }

    return result;
  }

  /**
   * 
   * @throws ProcessorException
   */
  private void preprocessing(IProgressMonitor progressMonitor_p) throws ProcessorException {
    try {
      if (_pluggedPreprocessors != null) {
        String loggedMsg;

        /** Refinement progress initialization */
        int totalWork = _PRE_POST_PROCESSOR_PROGRESS_STEP * _pluggedPreprocessors.size();
        progressMonitor_p.beginTask(Messages.PreProcessing_Progress, totalWork);

        for (IProcessor preProcessor : _pluggedPreprocessors) {
          preProcessor.execute(progressMonitor_p);

          /** logging */
          Object processorName = preProcessor.getName();
          if (processorName != null) {
            loggedMsg = MessageFormat.format(Messages.PreProcessing_Succeeded, processorName);
            _logger.debug(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, _srcDiagram));
          }

          /** Refinement progress increment */
          progressMonitor_p.worked(_PRE_POST_PROCESSOR_PROGRESS_STEP);
        }
      }
    }
    finally {
      progressMonitor_p.done();
    }
  }
 
  /**
   * 
   * @throws ProcessorException
   */
  private void postprocessing(IProgressMonitor progressMonitor_p) throws ProcessorException {
    try {
      if (_pluggedPostprocessors != null) {
        String loggedMsg;

        /** Refinement progress initialization */
        int totalWork = _PRE_POST_PROCESSOR_PROGRESS_STEP * _pluggedPostprocessors.size();
        progressMonitor_p.beginTask(Messages.PostProcessing_Progress, totalWork);

        for (IProcessor postProcessor : _pluggedPostprocessors) {
          postProcessor.execute(progressMonitor_p);

          /** logging */
          Object processorName = postProcessor.getName();
          if (processorName != null) {
            loggedMsg = MessageFormat.format(Messages.PostProcessing_Succeeded, processorName);
            _logger.debug(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, _srcDiagram));
          }

          /** Refinement progress increment */
          progressMonitor_p.worked(_PRE_POST_PROCESSOR_PROGRESS_STEP);
        }
      }
    }
    finally {
      progressMonitor_p.done();
    }
  }

  private void refineMsg(ScenarioRepresentation srcTree_p, ScenarioRepresentation tgtTree_p, Scenario tgtDiagram_p, List<AbstractEnd> unmappedMsg_p, Node<InteractionFragment> currentSrcNode_p, AbstractEnd absMsg_p, InstanceRole instRole_p, Component decomposedCpnt_p, AbstractEventOperation invokedOperation_p, COMPONENT_TYPE type_p) {
    List<AbstractInstance> candidateAbstractInstances = null;
    List<AbstractInstance> targetfinalAbstractInstances = null;

    NamedElement tgtElement = (_tgtElement instanceof Part) ? ((Part) _tgtElement).getType() : _tgtElement;

    /**
     * 2nd Step: The component type will determine the set of components that will potentially be selected as the final target component.
     */
    candidateAbstractInstances = new ArrayList<AbstractInstance>();
    for (IMapper m : _pluggedMappers) {
      for (AbstractInstance candidateCpnt : m.candidateComponents(instRole_p.getRepresentedInstance(), _isIntraLayerRefinement, decomposedCpnt_p, tgtElement, srcTree_p.getScenario(), absMsg_p)) {
        if (!candidateAbstractInstances.contains(candidateCpnt)) {
          candidateAbstractInstances.add(candidateCpnt);
        }
      }
    }

    /**
     * 3rd Step: A set of simple rules can help to automatically select the target component, without any user interaction.
     */
    targetfinalAbstractInstances = new ArrayList<AbstractInstance>();
    for (IMapper m : _pluggedMappers) {
      for (AbstractInstance mappedCpnt : m.componentMapping(type_p, invokedOperation_p, candidateAbstractInstances, absMsg_p)) {
        if (!targetfinalAbstractInstances.contains(mappedCpnt)) {
          targetfinalAbstractInstances.add(mappedCpnt);
        }
      }
    }

    AbstractInstance finalAbstractInstance = null;
    /**
     * 4th Step: If the target component is still ambiguous, a dialog box is displayed to ask user to take the final decision.
     */
    if (targetfinalAbstractInstances.size() > 0) {
      if (targetfinalAbstractInstances.size() == 1) {
        finalAbstractInstance = targetfinalAbstractInstances.get(0);
      }
      else {
        for (IResolver resolver : _pluggedResolvers) {
          targetfinalAbstractInstances = resolver.resolving(targetfinalAbstractInstances, srcTree_p, tgtTree_p, absMsg_p, type_p);
          if (targetfinalAbstractInstances.size() == 1) {
            finalAbstractInstance = targetfinalAbstractInstances.get(0);
            break;
          }
        }
      }
    }

    if (finalAbstractInstance == null) {
      unmappedMsg_p.add(absMsg_p);
      /** logging */
      if (absMsg_p instanceof MessageEnd) {
        logUnmappedMessage(((MessageEnd) absMsg_p).getMessage(), type_p);
      }
    }

    /**
     * 5th Step: The current message is re-ordered in the target tree.
     */
    if (_pluggedSchedulers != null) {
      _pluggedSchedulers.doOrdering(srcTree_p, tgtTree_p, finalAbstractInstance, currentSrcNode_p, tgtElement);
    }
  }

  /**
   * @param srcTree_p
   * @param tgtDiagram_p
   * @param tgtTree_p
   * @param currentSrcNode_p
   * @param state_p
   * @param unmappedMsg_p
   * @param progressMonitor_p
   */
  private void refine(ScenarioRepresentation srcTree_p, Scenario tgtDiagram_p, ScenarioRepresentation tgtTree_p, Node<InteractionFragment> currentSrcNode_p, InteractionState state_p, List<AbstractEnd> unmappedMsg_p, IProgressMonitor progressMonitor_p) {
    InstanceRole instRole = state_p.getCovered();
    if (instRole != null) {
      AbstractInstance cpntInst = instRole.getRepresentedInstance();
      if (cpntInst != null) {
        AbstractType instType = cpntInst.getAbstractType();
        if (instType != null) {
          // TODO
        }
      }
    }
  }

  /**
   * @param srcTree_p
   * @param tgtDiagram_p
   * @param tgtTree_p
   * @param currentSrcNode_p
   * @param operand_p
   * @param unmappedMsg_p
   * @param progressMonitor_p
   */
  private void refine(ScenarioRepresentation srcTree_p, Scenario tgtDiagram_p, ScenarioRepresentation tgtTree_p, Node<InteractionFragment> currentSrcNode_p, InteractionOperand operand_p, List<AbstractEnd> unmappedMsg_p, IProgressMonitor progressMonitor_p) {
    if (_pluggedSchedulers != null) {
      _pluggedSchedulers.doOrdering(srcTree_p, tgtTree_p, null, currentSrcNode_p, null);
    }
  }

  /**
   * @param srcTree_p
   * @param tgtDiagram_p
   * @param tgtTree_p
   * @param currentSrcNode_p
   * @param fragment_p
   * @param unmappedMsg_p
   * @param progressMonitor_p
   */
  private void refine(ScenarioRepresentation srcTree_p, Scenario tgtDiagram_p, ScenarioRepresentation tgtTree_p, Node<InteractionFragment> currentSrcNode_p, FragmentEnd fragment_p, List<AbstractEnd> unmappedMsg_p, IProgressMonitor progressMonitor_p) {
    if (!_isIntraLayerRefinement || isInteracting(fragment_p, _tgtElement)) {
      if (_pluggedSchedulers != null) {
        _pluggedSchedulers.doOrdering(srcTree_p, tgtTree_p, null, currentSrcNode_p, _tgtElement);
      }
    }
  }

  /**
   * @param srcTree_p
   * @param tgtDiagram_p
   * @param tgtTree_p
   * @param currentSrcNode_p
   * @param absMsg_p
   * @param unmappedMsg_p
   * @param progressMonitor_p
   */
  private void refine(ScenarioRepresentation srcTree_p, Scenario tgtDiagram_p, ScenarioRepresentation tgtTree_p, Node<InteractionFragment> currentSrcNode_p, AbstractEnd absMsg_p, List<AbstractEnd> unmappedMsg_p, IProgressMonitor progressMonitor_p) {
    InstanceRole instRole = absMsg_p.getCovered();
    if (instRole != null) {
      AbstractInstance cpntInst = instRole.getRepresentedInstance();
      if (cpntInst != null) {
        AbstractType instType = cpntInst.getAbstractType();
        if (instType != null) {
          boolean skipMsg = false;
          Component decomposedCpnt = _isIntraLayerRefinement ? getDecomposedComponent() : null;
          COMPONENT_TYPE type = COMPONENT_TYPE.UNDEFINED;
          AbstractEventOperation invokedOperation = getOperation(absMsg_p);
          if (absMsg_p instanceof MessageEnd) {
            MessageEnd srcMsg = (MessageEnd) absMsg_p;
            type = MessageEndExt.getMessageEndType(srcMsg);

            SequenceMessage srcSeqMsg = srcMsg.getMessage();
            if (srcSeqMsg != null) {
              if (srcSeqMsg.getKind() == MessageKind.REPLY) {
                if (type == COMPONENT_TYPE.RECEIVER) type = COMPONENT_TYPE.SENDER;
                else if (type == COMPONENT_TYPE.SENDER) type = COMPONENT_TYPE.RECEIVER;

                /**
                 * If the calling branch of the reply message has not been mapped, we skip it.
                 * This has been done to avoid a second ambiguity selection wizard to be launched.
                 */
                SequenceMessage callbranch = SequenceMessageExt.getOppositeSequenceMessage(srcSeqMsg);
                if (callbranch != null) {
                  if (unmappedMsg_p.contains(callbranch.getSendingEnd()) || unmappedMsg_p.contains(callbranch.getReceivingEnd())) {
                    skipMsg = true;
                  }
                }
              }

              /**
               * If the current abstract end does not interact with the component
               * in which we are refining, we skip it.
               */
              if (_isIntraLayerRefinement) {
                skipMsg |= evaluateMessageSkip(srcSeqMsg, decomposedCpnt);
              }

              /**
               * If the current abstract end belongs to an internal sequence message, we skip it.
               * [specification update => we do not skip it anymore]
               */
              //if (!_isIntraLayerRefinement) {
              //  NamedElement sender = SequenceMessageExt.getSender(srcSeqMsg);
              //  NamedElement receiver = SequenceMessageExt.getReceiver(srcSeqMsg);
              //  NamedElement tgtElement = (_tgtElement instanceof Part) ? ((Part) _tgtElement).getType() : _tgtElement;

              //  if ((tgtElement instanceof PhysicalArchitecture) && (sender instanceof LogicalComponent) && (receiver instanceof LogicalComponent)) {
              //    // If sender/receiver is the same component, the message is not skipped
              //    if (!sender.equals(receiver)) {
              //      skipMsg = LogicalComponentExt.haveSameImplementor((LogicalComponent) sender, (LogicalComponent) receiver, (PhysicalArchitecture) tgtElement);
              //    }
              //  } else if ((tgtElement instanceof EPBSArchitecture) && (sender instanceof PhysicalComponent) && (receiver instanceof PhysicalComponent)) {
              //    // If sender/receiver is the same component, the message is not skipped
              //    if (!sender.equals(receiver)) {
              //      skipMsg = PhysicalComponentExt.haveSameImplementor((PhysicalComponent) sender, (PhysicalComponent) receiver, (EPBSArchitecture) tgtElement);
              //    }
              //  }
              //}

              /** logging */
              logMessageMapping(skipMsg, srcSeqMsg, type);
            }
            else {
              throw new ProcessorException(Messages.ErrorMessageEndNotLinkedToSequenceMessage, this);
            }
          }
          else if (absMsg_p instanceof ExecutionEnd) {
            type = COMPONENT_TYPE.RECEIVER;

            /**
             * If the current abstract end does not interact with the component
             * in which we are refining, we skip it.
             */
            if (_isIntraLayerRefinement) {
              skipMsg = evaluateMessageSkip((ExecutionEnd) absMsg_p, decomposedCpnt);
            }
          }

          if (!skipMsg) {
            refineMsg(srcTree_p, tgtTree_p, tgtDiagram_p, unmappedMsg_p, currentSrcNode_p, absMsg_p, instRole, decomposedCpnt, invokedOperation, type);
          }
        }
        else {
          tgtTree_p.cleanClonedElements();
          String message = MessageFormat.format(Messages.ErrorInstanceNotTyped, cpntInst.getName());
          throw new ProcessorException(message, this);
        }
      }
      else {
        tgtTree_p.cleanClonedElements();
        String message = MessageFormat.format(Messages.ErrorInstanceRoleNotLinked, instRole.getName());
        throw new ProcessorException(message, this);
      }
    }
    else {
      throw new ProcessorException(Messages.ErrorAbstractEndNotLinkedToInstanceRole, this);
    }
  }

  /**
   * 
   */
  private void refine(ScenarioRepresentation srcTree_p, Scenario tgtDiagram_p, IProgressMonitor progressMonitor_p) {
    String loggedMsg;
    List<AbstractEnd> unmappedMsg = new ArrayList<AbstractEnd>();

    ScenarioRepresentation tgtTree = loadDiagram(tgtDiagram_p);
    linkDiagrams(srcTree_p, tgtTree);

    for (Node<InteractionFragment> currentSrcNode : srcTree_p.walk()) {
      InteractionFragment interactionFragment = currentSrcNode.getData();
      if (interactionFragment instanceof AbstractEnd) {
        refine(srcTree_p, tgtDiagram_p, tgtTree, currentSrcNode, (AbstractEnd) interactionFragment, unmappedMsg, progressMonitor_p);
      } else if (interactionFragment instanceof FragmentEnd) {
        refine(srcTree_p, tgtDiagram_p, tgtTree, currentSrcNode, (FragmentEnd) interactionFragment, unmappedMsg, progressMonitor_p);
      } else if (interactionFragment instanceof InteractionOperand) {
        refine(srcTree_p, tgtDiagram_p, tgtTree, currentSrcNode, (InteractionOperand) interactionFragment, unmappedMsg, progressMonitor_p);
      } else if (interactionFragment instanceof InteractionState) {
        refine(srcTree_p, tgtDiagram_p, tgtTree, currentSrcNode, (InteractionState) interactionFragment, unmappedMsg, progressMonitor_p);
      }

      /** Refinement progress increment */
      progressMonitor_p.worked(_MESSAGE_PROGRESS_STEP);
    }

    /**
     * 5th Step: The resulting tree structure is now exported to the target scenario.
     */
    tgtTree.export(srcTree_p);

    /** logging */
    loggedMsg = MessageFormat.format(Messages.Processing_Succeeded, getName());
    _logger.debug(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, _srcDiagram));
  }

  /**
   * 
   */
  private AbstractEventOperation getOperation(AbstractEnd absMsg_p) {
    AbstractEventOperation invokedOp = AbstractEndExt.getOperation(absMsg_p);
    AbstractEventOperation finalOp = null;

	  if (invokedOp != null)
		  finalOp = RefinementServices.getDelegatedOperation(_srcDiagram, _tgtDiagram, invokedOp);

	  return finalOp;
  }

  /**
   * @throws ProcessorException
   */
  public void execute(IProgressMonitor progressMonitor_p) throws ProcessorException {
    String loggedMsg;

    try {
      /** PreProcessing Step */
      preprocessing(progressMonitor_p);

      /**
       * 1st Step: The source scenario is imported in an internal tree structure format.
       */
      ScenarioRepresentation srcTree = loadDiagram(_srcDiagram);

      if (srcTree != null) {
        List<Scenario> existingScenarios = new ArrayList<Scenario>();

        /**
         * if the target scenario have not been set manually,
         * we check the existence of a scenario generated by a previous refinement.
         */
        if (_tgtDiagram == null) {
          NamedElement tgtElement = (_tgtElement instanceof Part) ? ((Part) _tgtElement).getType() : _tgtElement;
          for (CapellaElement target : RefinementLinkExt.getRefinementRelatedSourceElements(_srcDiagram, InteractionPackage.Literals.SCENARIO)) {
            if (EcoreUtil2.isContainedBy(target, tgtElement)) {
              _tgtDiagram = (Scenario) target;
              existingScenarios.add(_tgtDiagram);
            }
          }
        }
        else {
          existingScenarios.add(_tgtDiagram);
        }

        /** Refinement progress initialization */
        int totalWork = _MESSAGE_PROGRESS_STEP * srcTree.walk().size() * existingScenarios.size();

        for (Scenario tgtDiagram : existingScenarios) {
          /** */
          String message = MessageFormat.format(Messages.ScenarioRefinement_Progress, _srcDiagram.getName(), tgtDiagram.getName());
          progressMonitor_p.beginTask(message, totalWork);
          /** */
          refine(srcTree, tgtDiagram, progressMonitor_p);
          /** */
          progressMonitor_p.done();
        }

        /** PostProcessing Step */
        postprocessing(progressMonitor_p);
      }

      /** logging */
      loggedMsg = MessageFormat.format(Messages.ScenarioRefinement_Succeeded, _srcDiagram.getName());
      _logger.info(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, _srcDiagram));
    }
    catch (RefinementException ex) {
      /** logging */
      loggedMsg = MessageFormat.format(Messages.ScenarioRefinement_Failed, _srcDiagram.getName(), ex.getMessage());
      _logger.error(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, _srcDiagram));
    }
    finally {
      progressMonitor_p.done();
    }
  }

  /**
   * 
   */
  private void addPluggedModules() {
    Scenario srcDiagram = getContext();
    NamedElement target = getTarget();
    Comparator<IConfigurationElement> priorityComparator = new ExtensionPriorityComparator();

    /**
     * add preprocessors
     */
    if (_loadAdditionalProcessors) {
      List<IConfigurationElement> preprocessorProvider = Arrays.asList(ExtensionPointHelper.getConfigurationElements(REFINEMENT_FRAMEWORK_PLUGIN_ID, PREPROCESSING_EXTENSION_ID));
      Collections.sort(preprocessorProvider, priorityComparator);
      for (IConfigurationElement configurationElement : preprocessorProvider) {
        /** logging */
        String loggedMsg = MessageFormat.format(Messages.NewPreProcessorProvider, configurationElement.getAttribute(ExtensionPointHelper.ATT_ID));
        _logger.debug(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, srcDiagram));
        /** */
        IProcessor processor = (IProcessor) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
        if (processor != null) {
          processor.setContext(srcDiagram);
          processor.setTarget(target);
          addPlug(processor, ProcessingType.PREPROCESSING);
        }
      }
    }

    /**
     * add mappers
     */
    List<IConfigurationElement> mapperProvider = Arrays.asList(ExtensionPointHelper.getConfigurationElements(REFINEMENT_FRAMEWORK_PLUGIN_ID, MAPPING_EXTENSION_ID));
    Collections.sort(mapperProvider, priorityComparator);
    for (IConfigurationElement configurationElement : mapperProvider) {
      /** logging */
      String loggedMsg = MessageFormat.format(Messages.NewMapperProvider, configurationElement.getAttribute(ExtensionPointHelper.ATT_ID));
      _logger.debug(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, srcDiagram));
      /** */
      IMapper mapper = (IMapper) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
      if (mapper != null) {
        addPlug(mapper);
      }
    }

    /**
     * add resolvers
     */
    List<IConfigurationElement> resolverProvider = Arrays.asList(ExtensionPointHelper.getConfigurationElements(REFINEMENT_FRAMEWORK_PLUGIN_ID, RESOLVING_EXTENSION_ID));
    Collections.sort(resolverProvider, priorityComparator);
    for (IConfigurationElement configurationElement : resolverProvider) {
      /** logging */
      String loggedMsg = MessageFormat.format(Messages.NewResolverProvider, configurationElement.getAttribute(ExtensionPointHelper.ATT_ID));
      _logger.debug(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, srcDiagram));
      /** */
      IResolver resolver = (IResolver) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
      if (resolver != null) {
        addPlug(resolver);
      }
    }

    /**
     * add scheduler
     */
    List<IConfigurationElement> schedulerProvider = Arrays.asList(ExtensionPointHelper.getConfigurationElements(REFINEMENT_FRAMEWORK_PLUGIN_ID, SCHEDULING_EXTENSION_ID));
    Collections.sort(schedulerProvider, priorityComparator);
    for (IConfigurationElement configurationElement : schedulerProvider) {
      /** logging */
      String loggedMsg = MessageFormat.format(Messages.NewSchedulerProvider, configurationElement.getAttribute(ExtensionPointHelper.ATT_ID));
      _logger.debug(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, srcDiagram));
      /** */
      IScheduler scheduler = (IScheduler) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
      if (scheduler != null) {
        addPlug(scheduler);
      }
    }

    /**
     * add postprocessors
     */
    if (_loadAdditionalProcessors) {
      List<IConfigurationElement> postprocessorProvider = Arrays.asList(ExtensionPointHelper.getConfigurationElements(REFINEMENT_FRAMEWORK_PLUGIN_ID, POSTPROCESSING_EXTENSION_ID));
      Collections.sort(postprocessorProvider, priorityComparator);
      for (IConfigurationElement configurationElement : postprocessorProvider) {
        /** logging */
        String loggedMsg = MessageFormat.format(Messages.NewPostProcessorProvider, configurationElement.getAttribute(ExtensionPointHelper.ATT_ID));
        _logger.debug(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, srcDiagram));
        /** */
        IProcessor processor = (IProcessor) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
        if (processor != null) {
          processor.setContext(srcDiagram);
          processor.setTarget(target);
          addPlug(processor, ProcessingType.POSTPROCESSING);
        }
      }
    }
  }

  /**
   * Retrieves the refined nodes from the target diagram tree representation and link them to the corresponding nodes of the source diagram tree representation.
   * @param srcDiagram_p
   * @param tgtDiagram_p
   */
  private void linkDiagrams(ScenarioRepresentation srcDiagram_p, ScenarioRepresentation tgtDiagram_p) {
    srcDiagram_p.clean();

    for (Node<InteractionFragment> tgtNode : tgtDiagram_p.walk()) {
      InteractionFragment tgtMsg = tgtNode.getData();
      if (tgtMsg != null) {
        for (CapellaElement sc : RefinementLinkExt.getRefinementRelatedTargetElements(tgtMsg, InteractionPackage.Literals.ABSTRACT_END)) {
          for (Node<InteractionFragment> srcNode : srcDiagram_p.walk()) {
            if (srcNode.getData() == sc) {
              tgtNode.relatedNode = srcNode;
              srcNode.relatedNode = tgtNode;
            }
          }
        }
      }
    }
  }

  /**
   * @param diagram_p
   * @return an internal representation of the SequenceDiagram
   */
  private ScenarioRepresentation loadDiagram(Scenario diagram_p) {
    ScenarioRepresentation tree = null;

    if (diagram_p != null) {
      tree = new ScenarioRepresentation(diagram_p);
    }

    return tree;
  }

  /**
   * @param skipMsg_p
   * @param srcSeqMsg_p
   * @param type_p
   */
  private void logMessageMapping(boolean skipMsg_p, SequenceMessage srcSeqMsg_p, COMPONENT_TYPE type_p) {
    String loggedMsg;

    if (skipMsg_p) {
      if (srcSeqMsg_p.getKind() == MessageKind.REPLY) {
        loggedMsg = MessageFormat.format(Messages.SkeepSequenceMessageReplyMapping, srcSeqMsg_p.getName());
      } else {
        loggedMsg = MessageFormat.format(Messages.SkeepSequenceMessageMapping, srcSeqMsg_p.getName());
      }
    } else {
      if (type_p == COMPONENT_TYPE.RECEIVER) {
        if (srcSeqMsg_p.getKind() == MessageKind.REPLY) {
          loggedMsg = MessageFormat.format(Messages.TryMapSequenceMessageReplyReceiver, srcSeqMsg_p.getName());
        } else {
          loggedMsg = MessageFormat.format(Messages.TryMapSequenceMessageReceiver, srcSeqMsg_p.getName());
        }
      } else {
        if (srcSeqMsg_p.getKind() == MessageKind.REPLY) {
          loggedMsg = MessageFormat.format(Messages.TryMapSequenceMessageReplySender, srcSeqMsg_p.getName());
        } else {
          loggedMsg = MessageFormat.format(Messages.TryMapSequenceMessageSender, srcSeqMsg_p.getName());
        }
      }
    }
    _logger.debug(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, _srcDiagram));
  }

  /**
   * @param srcSeqMsg_p
   * @param type_p
   */
  private void logUnmappedMessage(SequenceMessage srcSeqMsg_p, COMPONENT_TYPE type_p) {
    String loggedMsg;

    if (type_p == COMPONENT_TYPE.RECEIVER) {
      if (srcSeqMsg_p.getKind() == MessageKind.REPLY) {
        loggedMsg = MessageFormat.format(Messages.UnmappedSequenceMessageReplyReceiver, srcSeqMsg_p.getName());
      } else {
        loggedMsg = MessageFormat.format(Messages.UnmappedSequenceMessageReceiver, srcSeqMsg_p.getName());
      }
    } else {
      if (srcSeqMsg_p.getKind() == MessageKind.REPLY) {
        loggedMsg = MessageFormat.format(Messages.UnmappedSequenceMessageReplySender, srcSeqMsg_p.getName());
      } else {
        loggedMsg = MessageFormat.format(Messages.UnmappedSequenceMessageSender, srcSeqMsg_p.getName());
      }
    }
    _logger.warn(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, _srcDiagram));
  }

  /**
   * 
   */
  private boolean evaluateMessageSkip(SequenceMessage sequenceMessage_p, Component decomposedCpnt_p) {
    if (_tgtElement instanceof Part) {
      return !isInteracting(sequenceMessage_p, _tgtElement);
    } else if (_tgtElement instanceof Component) {
      return !isInteracting(sequenceMessage_p, decomposedCpnt_p);
    } else if (_tgtElement instanceof LogicalArchitecture) {
      return !isInteracting(sequenceMessage_p, decomposedCpnt_p);
    }
    return false;
  }

  /**
   * 
   */
  private boolean evaluateMessageSkip(ExecutionEnd executionEnd_p, Component decomposedCpnt_p) {
    if (_tgtElement instanceof Part) {
      return !isInteracting(executionEnd_p, _tgtElement);
    } else if (_tgtElement instanceof Component) {
      return !isInteracting(executionEnd_p, decomposedCpnt_p);
    } else if (_tgtElement instanceof LogicalArchitecture) {
      return !isInteracting(executionEnd_p, decomposedCpnt_p);
    }
    return false;
  }

  /**
   * 
   */
  private Component getDecomposedComponent() {
    if (_tgtElement instanceof Part) {
      return (Component) ((Part) _tgtElement).getAbstractType();
    } else if (_tgtElement instanceof Component) {
      return (Component) _tgtElement;
    } else if (_tgtElement instanceof LogicalArchitecture) {
      return (Component) _tgtElement.eContainer();
    }
    return null;
  }

  /**
   * 
   */
  protected static String REFINED_SCENARIO_PREFIX = "L{0} - "; //$NON-NLS-1$

  /**
   * 
   * @param scenario_p
   * @param target_p
   */
  protected void applyNamingRule(NamedElement scenario_p, NamedElement target_p) {
    applyNamingRule(scenario_p, target_p, scenario_p.getName());
  }

  /**
   * 
   * @param scenario_p
   * @param target_p
   * @param name_p
   */
  protected void applyNamingRule(NamedElement scenario_p, NamedElement target_p, String name_p) {
    String scName = evaluateNamingRule(target_p, name_p);
    if (!scenario_p.getName().equals(scName)) {
      scenario_p.setName(scName);
    }
  }

  /**
   * 
   */
  public static String evaluateNamingRule(NamedElement target_p, String name_p) {
    return name_p;

// Naming Rule based on hierarchical Level is obsolete
//    if (target_p instanceof LogicalArchitecture) {
//      EObject container = target_p.eContainer();
//      if (container instanceof System) {
//        String prefix = MessageFormat.format(REFINED_SCENARIO_PREFIX, new Integer(0));
//        if (!scName.startsWith(prefix)) {
//          scName = prefix + scName;
//        }
//      }
//      else if (container instanceof LogicalComponent) {
//        String oldPrefix = MessageFormat.format(REFINED_SCENARIO_PREFIX, new Integer(LogicalComponentExt.getLCLevel((LogicalComponent) container)-1));
//        String newPrefix = MessageFormat.format(REFINED_SCENARIO_PREFIX, new Integer(LogicalComponentExt.getLCLevel((LogicalComponent) container)));
//
//        if (scName.lastIndexOf(oldPrefix) != -1) {
//          scName = scName.replace(oldPrefix, newPrefix);
//        }
//        else if (scName.lastIndexOf(newPrefix) == -1) {
//          scName = newPrefix + scName;
//        }
//      }
//    }
//    else if (target_p instanceof LogicalComponent) {
//      String oldPrefix = MessageFormat.format(REFINED_SCENARIO_PREFIX, new Integer(LogicalComponentExt.getLCLevel((LogicalComponent) target_p)-1));
//      String newPrefix = MessageFormat.format(REFINED_SCENARIO_PREFIX, new Integer(LogicalComponentExt.getLCLevel((LogicalComponent) target_p)));
//
//      if (scName.lastIndexOf(oldPrefix) != -1) {
//        scName = scName.replace(oldPrefix, newPrefix);
//      }
//      else if (scName.lastIndexOf(newPrefix) == -1) {
//        scName = newPrefix + scName;
//      }
//    }
//
//    return scName;
  }
}
