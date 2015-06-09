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
   * @param srcDiagram
   * @param target
   */
  public ScenarioRefinement(Scenario srcDiagram, NamedElement target) {
    this(srcDiagram, target, false);
  }

  /**
   * Constructor
   * 
   * @param srcDiagram
   * @param target
   * @param isIntraLayer
   */
  public ScenarioRefinement(Scenario srcDiagram, NamedElement target, boolean isIntraLayer) {
    this(srcDiagram, target, isIntraLayer, true);
  }

  /**
   * Constructor
   * 
   * @param srcDiagram
   * @param target
   * @param isIntraLayer
   * @param loadAdditionalProcessors
   */
  public ScenarioRefinement(Scenario srcDiagram, NamedElement target, boolean isIntraLayer, boolean loadAdditionalProcessors) {
    _srcDiagram = srcDiagram;
    _tgtElement = target;
    _isIntraLayerRefinement = isIntraLayer;
    _loadAdditionalProcessors = loadAdditionalProcessors;

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
   * @param tgtDiagram
   */
  protected void forceTargetScenario(Scenario tgtDiagram) {
    _tgtDiagram = tgtDiagram;
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(java.util.List)
   * 
   * @param context
   */
  public void setContext(List<ModelElement> context) {
    if ((context != null) && (context.size()>0)) {
      setContext(context.get(0));
    }
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(org.polarsys.capella.core.common.model.CapellaElement)
   * 
   * @param context
   */
  public void setContext(ModelElement context) {
    if (context instanceof Scenario) {
      _srcDiagram = (Scenario) context;

      /** update preprocessors */
      for (IProcessor processor : _pluggedPreprocessors) {
        processor.setContext(context);
      }
      /** update post-processors */
      for (IProcessor processor : _pluggedPostprocessors) {
        processor.setContext(context);
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
   * @param target
   */
  public void setTarget(NamedElement target) {
    _tgtElement = target;

    /** update preprocessors */
    for (IProcessor processor : _pluggedPreprocessors) {
      processor.setTarget(target);
    }
    /** update post-processors */
    for (IProcessor processor : _pluggedPostprocessors) {
      processor.setTarget(target);
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
   * @param mapper
   */
  private void addPlug(IMapper mapper) {
    addPlug(-1, mapper);
  }

  /**
   * Appends a new resolver at the end of the resolvers list.
   * @param resolver
   */
  private void addPlug(IResolver resolver) {
    addPlug(-1, resolver);
  }

  /**
   * Sets a new scheduler.
   * @param scheduler
   */
  private void addPlug(IScheduler scheduler) {
    _pluggedSchedulers = scheduler;
  }

  /**
   * Appends a new processor at the end of the processors list.
   * @param processor
   * @param type
   */
  private void addPlug(IProcessor processor, IProcessor.ProcessingType type) {
    addPlug(-1, processor, type);
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
   * @param order
   * @param resolver
   */
  private void addPlug(int order, IResolver resolver) {
    if (null == _pluggedResolvers) {
      _pluggedResolvers = new ArrayList<IResolver>();
    }
    if ((order < 0) || (order > _pluggedMappers.size()))
      _pluggedResolvers.add(resolver);
    else
      _pluggedResolvers.add(order, resolver);
  }

  /**
   * Adds a new preprocessor to the preprocessors list at the 'order' position.
   * @param order
   * @param processor
   * @param type
   */
  private void addPlug(int order, IProcessor processor, IProcessor.ProcessingType type) {
    switch (type) {
      case PREPROCESSING: {
        if (null == _pluggedPreprocessors) {
          _pluggedPreprocessors = new ArrayList<IProcessor>();
        }
        if ((order < 0) || (order > _pluggedPreprocessors.size()))
          _pluggedPreprocessors.add(processor);
        else
          _pluggedPreprocessors.add(order, processor);
        break;
      }
      case POSTPROCESSING: {
        if (null == _pluggedPostprocessors) {
          _pluggedPostprocessors = new ArrayList<IProcessor>();
        }
        if ((order < 0) || (order > _pluggedPostprocessors.size()))
          _pluggedPostprocessors.add(processor);
        else
          _pluggedPostprocessors.add(order, processor);
      }
    }
  }

  /**
   * This method returns TRUE if the element {@link use} interacts with the element {@link elt}.
   * 
   * @param msg
   * @param elt
   */
  protected boolean isInteracting(InteractionUse use, NamedElement elt) {
    boolean startResult = false;
    boolean finishResult = false;

    if ((use != null) && (elt != null)) {
      FragmentEnd startEnd = (FragmentEnd) use.getStart();
      if (startEnd != null) {
        startResult = isInteracting(startEnd, elt);
      }
      FragmentEnd finishEnd = (FragmentEnd) use.getFinish();
      if (finishEnd != null) {
        finishResult = isInteracting(finishEnd, elt);
      }
    }

    return (startResult || finishResult);
  }

  /**
   * This method returns TRUE if the message {@link msg} interacts with the element {@link elt}.
   * 
   * @param msg
   * @param elt
   */
  private boolean isInteracting(SequenceMessage msg, NamedElement elt) {
    boolean sndResult = false;
    boolean rcvResult = false;

    if ((msg != null) && (elt != null)) {
      MessageEnd sndMsgEnd = msg.getSendingEnd();
      if (sndMsgEnd != null) {
        sndResult = isInteracting(sndMsgEnd, elt);
      }
      MessageEnd rcvMsgEnd = msg.getReceivingEnd();
      if (rcvMsgEnd != null) {
        rcvResult = isInteracting(rcvMsgEnd, elt);
      }
    }

    return (sndResult || rcvResult);
  }

  /**
   * This method returns TRUE if the execution end {@link execEnd} interacts with the element {@link elt}.
   * 
   * @param execEnd
   * @param elt
   */
  private boolean isInteracting(ExecutionEnd execEnd, NamedElement elt) {
    boolean result = false;

    if ((execEnd != null) && (elt != null)) {
      result = isInteracting((AbstractEnd) execEnd, elt);

      /**
       * The sending message is also tested
       */
      if (!result) {
        Execution exec = execEnd.getExecution();
        if (exec != null) {
          MessageEnd msgEnd = MessageEndExt.getOppositeMessageEnd((MessageEnd) exec.getStart());
          if (msgEnd != null) {
            result = isInteracting(msgEnd, elt);
          }
        }
      }
    }

    return result;
  }

  /**
   * This method returns TRUE if the message end {@link absEnd} interacts with the element {@link elt}.
   * 
   * @param absEnd
   * @param elt
   */
  private boolean isInteracting(AbstractEnd absEnd, NamedElement elt) {
    boolean result = false;

    if ((absEnd != null) && (elt != null)) {
      InstanceRole instRole = absEnd.getCovered();
      if (instRole != null) {
        AbstractInstance inst = instRole.getRepresentedInstance();
        if (inst != null) {
          if (elt instanceof Part) {
            result = elt.equals(inst);
          } else {
            AbstractType type = inst.getType();
            if (type instanceof AbstractExchangeItem) {
              for (Object objectRef : EObjectExt.getReferencers(type, CommunicationPackage.Literals.COMMUNICATION_LINK__EXCHANGE_ITEM)) {
                EObject owner = ((CommunicationLink) objectRef).eContainer();
                if (elt.equals(owner)) return true;
              }
            } else {
              result = elt.equals(type);
            }
          }
        }
      }
    }

    return result;
  }

  /**
   * This method returns TRUE if the fragment end {@link absEnd} interacts with the element {@link elt}.
   * 
   * @param absEnd
   * @param elt
   */
  private boolean isInteracting(FragmentEnd absEnd, NamedElement elt) {
    boolean result = false;

    if ((absEnd != null) && (elt != null)) {
      for (InstanceRole instRole : absEnd.getCoveredInstanceRoles()) {
        AbstractInstance inst = instRole.getRepresentedInstance();
        if (inst != null) {
          if (elt instanceof Part) {
            result |= elt.equals(inst);
          } else {
            AbstractType type = inst.getType();
            if (type instanceof AbstractExchangeItem) {
              for (Object objectRef : EObjectExt.getReferencers(type, CommunicationPackage.Literals.COMMUNICATION_LINK__EXCHANGE_ITEM)) {
                EObject owner = ((CommunicationLink) objectRef).eContainer();
                if (elt.equals(owner)) return true;
              }
            } else {
              result |= elt.equals(type);
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
  private void preprocessing(IProgressMonitor progressMonitor) throws ProcessorException {
    try {
      if (_pluggedPreprocessors != null) {
        String loggedMsg;

        /** Refinement progress initialization */
        int totalWork = _PRE_POST_PROCESSOR_PROGRESS_STEP * _pluggedPreprocessors.size();
        progressMonitor.beginTask(Messages.PreProcessing_Progress, totalWork);

        for (IProcessor preProcessor : _pluggedPreprocessors) {
          preProcessor.execute(progressMonitor);

          /** logging */
          Object processorName = preProcessor.getName();
          if (processorName != null) {
            loggedMsg = MessageFormat.format(Messages.PreProcessing_Succeeded, processorName);
            _logger.debug(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, _srcDiagram));
          }

          /** Refinement progress increment */
          progressMonitor.worked(_PRE_POST_PROCESSOR_PROGRESS_STEP);
        }
      }
    }
    finally {
      progressMonitor.done();
    }
  }
 
  /**
   * 
   * @throws ProcessorException
   */
  private void postprocessing(IProgressMonitor progressMonitor) throws ProcessorException {
    try {
      if (_pluggedPostprocessors != null) {
        String loggedMsg;

        /** Refinement progress initialization */
        int totalWork = _PRE_POST_PROCESSOR_PROGRESS_STEP * _pluggedPostprocessors.size();
        progressMonitor.beginTask(Messages.PostProcessing_Progress, totalWork);

        for (IProcessor postProcessor : _pluggedPostprocessors) {
          postProcessor.execute(progressMonitor);

          /** logging */
          Object processorName = postProcessor.getName();
          if (processorName != null) {
            loggedMsg = MessageFormat.format(Messages.PostProcessing_Succeeded, processorName);
            _logger.debug(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, _srcDiagram));
          }

          /** Refinement progress increment */
          progressMonitor.worked(_PRE_POST_PROCESSOR_PROGRESS_STEP);
        }
      }
    }
    finally {
      progressMonitor.done();
    }
  }

  private void refineMsg(ScenarioRepresentation srcTree, ScenarioRepresentation tgtTree, Scenario tgtDiagram, List<AbstractEnd> unmappedMsg, Node<InteractionFragment> currentSrcNode, AbstractEnd absMsg, InstanceRole instRole, Component decomposedCpnt, AbstractEventOperation invokedOperation, COMPONENT_TYPE type) {
    List<AbstractInstance> candidateAbstractInstances = null;
    List<AbstractInstance> targetfinalAbstractInstances = null;

    NamedElement tgtElement = (_tgtElement instanceof Part) ? ((Part) _tgtElement).getType() : _tgtElement;

    /**
     * 2nd Step: The component type will determine the set of components that will potentially be selected as the final target component.
     */
    candidateAbstractInstances = new ArrayList<AbstractInstance>();
    for (IMapper m : _pluggedMappers) {
      for (AbstractInstance candidateCpnt : m.candidateComponents(instRole.getRepresentedInstance(), _isIntraLayerRefinement, decomposedCpnt, tgtElement, srcTree.getScenario(), absMsg)) {
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
      for (AbstractInstance mappedCpnt : m.componentMapping(type, invokedOperation, candidateAbstractInstances, absMsg)) {
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
          targetfinalAbstractInstances = resolver.resolving(targetfinalAbstractInstances, srcTree, tgtTree, absMsg, type);
          if (targetfinalAbstractInstances.size() == 1) {
            finalAbstractInstance = targetfinalAbstractInstances.get(0);
            break;
          }
        }
      }
    }

    if (finalAbstractInstance == null) {
      unmappedMsg.add(absMsg);
      /** logging */
      if (absMsg instanceof MessageEnd) {
        logUnmappedMessage(((MessageEnd) absMsg).getMessage(), type);
      }
    }

    /**
     * 5th Step: The current message is re-ordered in the target tree.
     */
    if (_pluggedSchedulers != null) {
      _pluggedSchedulers.doOrdering(srcTree, tgtTree, finalAbstractInstance, currentSrcNode, tgtElement);
    }
  }

  /**
   * @param srcTree
   * @param tgtDiagram
   * @param tgtTree
   * @param currentSrcNode
   * @param state
   * @param unmappedMsg
   * @param progressMonitor
   */
  private void refine(ScenarioRepresentation srcTree, Scenario tgtDiagram, ScenarioRepresentation tgtTree, Node<InteractionFragment> currentSrcNode, InteractionState state, List<AbstractEnd> unmappedMsg, IProgressMonitor progressMonitor) {
    InstanceRole instRole = state.getCovered();
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
   * @param srcTree
   * @param tgtDiagram
   * @param tgtTree
   * @param currentSrcNode
   * @param operand
   * @param unmappedMsg
   * @param progressMonitor
   */
  private void refine(ScenarioRepresentation srcTree, Scenario tgtDiagram, ScenarioRepresentation tgtTree, Node<InteractionFragment> currentSrcNode, InteractionOperand operand, List<AbstractEnd> unmappedMsg, IProgressMonitor progressMonitor) {
    if (_pluggedSchedulers != null) {
      _pluggedSchedulers.doOrdering(srcTree, tgtTree, null, currentSrcNode, null);
    }
  }

  /**
   * @param srcTree
   * @param tgtDiagram
   * @param tgtTree
   * @param currentSrcNode
   * @param fragment
   * @param unmappedMsg
   * @param progressMonitor
   */
  private void refine(ScenarioRepresentation srcTree, Scenario tgtDiagram, ScenarioRepresentation tgtTree, Node<InteractionFragment> currentSrcNode, FragmentEnd fragment, List<AbstractEnd> unmappedMsg, IProgressMonitor progressMonitor) {
    if (!_isIntraLayerRefinement || isInteracting(fragment, _tgtElement)) {
      if (_pluggedSchedulers != null) {
        _pluggedSchedulers.doOrdering(srcTree, tgtTree, null, currentSrcNode, _tgtElement);
      }
    }
  }

  /**
   * @param srcTree
   * @param tgtDiagram
   * @param tgtTree
   * @param currentSrcNode
   * @param absMsg
   * @param unmappedMsg
   * @param progressMonitor
   */
  private void refine(ScenarioRepresentation srcTree, Scenario tgtDiagram, ScenarioRepresentation tgtTree, Node<InteractionFragment> currentSrcNode, AbstractEnd absMsg, List<AbstractEnd> unmappedMsg, IProgressMonitor progressMonitor) {
    InstanceRole instRole = absMsg.getCovered();
    if (instRole != null) {
      AbstractInstance cpntInst = instRole.getRepresentedInstance();
      if (cpntInst != null) {
        AbstractType instType = cpntInst.getAbstractType();
        if (instType != null) {
          boolean skipMsg = false;
          Component decomposedCpnt = _isIntraLayerRefinement ? getDecomposedComponent() : null;
          COMPONENT_TYPE type = COMPONENT_TYPE.UNDEFINED;
          AbstractEventOperation invokedOperation = getOperation(absMsg);
          if (absMsg instanceof MessageEnd) {
            MessageEnd srcMsg = (MessageEnd) absMsg;
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
                  if (unmappedMsg.contains(callbranch.getSendingEnd()) || unmappedMsg.contains(callbranch.getReceivingEnd())) {
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
          else if (absMsg instanceof ExecutionEnd) {
            type = COMPONENT_TYPE.RECEIVER;

            /**
             * If the current abstract end does not interact with the component
             * in which we are refining, we skip it.
             */
            if (_isIntraLayerRefinement) {
              skipMsg = evaluateMessageSkip((ExecutionEnd) absMsg, decomposedCpnt);
            }
          }

          if (!skipMsg) {
            refineMsg(srcTree, tgtTree, tgtDiagram, unmappedMsg, currentSrcNode, absMsg, instRole, decomposedCpnt, invokedOperation, type);
          }
        }
        else {
          tgtTree.cleanClonedElements();
          String message = MessageFormat.format(Messages.ErrorInstanceNotTyped, cpntInst.getName());
          throw new ProcessorException(message, this);
        }
      }
      else {
        tgtTree.cleanClonedElements();
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
  private void refine(ScenarioRepresentation srcTree, Scenario tgtDiagram, IProgressMonitor progressMonitor) {
    String loggedMsg;
    List<AbstractEnd> unmappedMsg = new ArrayList<AbstractEnd>();

    ScenarioRepresentation tgtTree = loadDiagram(tgtDiagram);
    linkDiagrams(srcTree, tgtTree);

    for (Node<InteractionFragment> currentSrcNode : srcTree.walk()) {
      InteractionFragment interactionFragment = currentSrcNode.getData();
      if (interactionFragment instanceof AbstractEnd) {
        refine(srcTree, tgtDiagram, tgtTree, currentSrcNode, (AbstractEnd) interactionFragment, unmappedMsg, progressMonitor);
      } else if (interactionFragment instanceof FragmentEnd) {
        refine(srcTree, tgtDiagram, tgtTree, currentSrcNode, (FragmentEnd) interactionFragment, unmappedMsg, progressMonitor);
      } else if (interactionFragment instanceof InteractionOperand) {
        refine(srcTree, tgtDiagram, tgtTree, currentSrcNode, (InteractionOperand) interactionFragment, unmappedMsg, progressMonitor);
      } else if (interactionFragment instanceof InteractionState) {
        refine(srcTree, tgtDiagram, tgtTree, currentSrcNode, (InteractionState) interactionFragment, unmappedMsg, progressMonitor);
      }

      /** Refinement progress increment */
      progressMonitor.worked(_MESSAGE_PROGRESS_STEP);
    }

    /**
     * 5th Step: The resulting tree structure is now exported to the target scenario.
     */
    tgtTree.export(srcTree);

    /** logging */
    loggedMsg = MessageFormat.format(Messages.Processing_Succeeded, getName());
    _logger.debug(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, _srcDiagram));
  }

  /**
   * 
   */
  private AbstractEventOperation getOperation(AbstractEnd absMsg) {
    AbstractEventOperation invokedOp = AbstractEndExt.getOperation(absMsg);
    AbstractEventOperation finalOp = null;

	  if (invokedOp != null)
		  finalOp = RefinementServices.getDelegatedOperation(_srcDiagram, _tgtDiagram, invokedOp);

	  return finalOp;
  }

  /**
   * @throws ProcessorException
   */
  public void execute(IProgressMonitor progressMonitor) throws ProcessorException {
    String loggedMsg;

    try {
      /** PreProcessing Step */
      preprocessing(progressMonitor);

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
          progressMonitor.beginTask(message, totalWork);
          /** */
          refine(srcTree, tgtDiagram, progressMonitor);
          /** */
          progressMonitor.done();
        }

        /** PostProcessing Step */
        postprocessing(progressMonitor);
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
      progressMonitor.done();
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
   * @param srcDiagram
   * @param tgtDiagram
   */
  private void linkDiagrams(ScenarioRepresentation srcDiagram, ScenarioRepresentation tgtDiagram) {
    srcDiagram.clean();

    for (Node<InteractionFragment> tgtNode : tgtDiagram.walk()) {
      InteractionFragment tgtMsg = tgtNode.getData();
      if (tgtMsg != null) {
        for (CapellaElement sc : RefinementLinkExt.getRefinementRelatedTargetElements(tgtMsg, InteractionPackage.Literals.ABSTRACT_END)) {
          for (Node<InteractionFragment> srcNode : srcDiagram.walk()) {
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
   * @param diagram
   * @return an internal representation of the SequenceDiagram
   */
  private ScenarioRepresentation loadDiagram(Scenario diagram) {
    ScenarioRepresentation tree = null;

    if (diagram != null) {
      tree = new ScenarioRepresentation(diagram);
    }

    return tree;
  }

  /**
   * @param skipMsg
   * @param srcSeqMsg
   * @param type
   */
  private void logMessageMapping(boolean skipMsg, SequenceMessage srcSeqMsg, COMPONENT_TYPE type) {
    String loggedMsg;

    if (skipMsg) {
      if (srcSeqMsg.getKind() == MessageKind.REPLY) {
        loggedMsg = MessageFormat.format(Messages.SkeepSequenceMessageReplyMapping, srcSeqMsg.getName());
      } else {
        loggedMsg = MessageFormat.format(Messages.SkeepSequenceMessageMapping, srcSeqMsg.getName());
      }
    } else {
      if (type == COMPONENT_TYPE.RECEIVER) {
        if (srcSeqMsg.getKind() == MessageKind.REPLY) {
          loggedMsg = MessageFormat.format(Messages.TryMapSequenceMessageReplyReceiver, srcSeqMsg.getName());
        } else {
          loggedMsg = MessageFormat.format(Messages.TryMapSequenceMessageReceiver, srcSeqMsg.getName());
        }
      } else {
        if (srcSeqMsg.getKind() == MessageKind.REPLY) {
          loggedMsg = MessageFormat.format(Messages.TryMapSequenceMessageReplySender, srcSeqMsg.getName());
        } else {
          loggedMsg = MessageFormat.format(Messages.TryMapSequenceMessageSender, srcSeqMsg.getName());
        }
      }
    }
    _logger.debug(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, _srcDiagram));
  }

  /**
   * @param srcSeqMsg
   * @param type
   */
  private void logUnmappedMessage(SequenceMessage srcSeqMsg, COMPONENT_TYPE type) {
    String loggedMsg;

    if (type == COMPONENT_TYPE.RECEIVER) {
      if (srcSeqMsg.getKind() == MessageKind.REPLY) {
        loggedMsg = MessageFormat.format(Messages.UnmappedSequenceMessageReplyReceiver, srcSeqMsg.getName());
      } else {
        loggedMsg = MessageFormat.format(Messages.UnmappedSequenceMessageReceiver, srcSeqMsg.getName());
      }
    } else {
      if (srcSeqMsg.getKind() == MessageKind.REPLY) {
        loggedMsg = MessageFormat.format(Messages.UnmappedSequenceMessageReplySender, srcSeqMsg.getName());
      } else {
        loggedMsg = MessageFormat.format(Messages.UnmappedSequenceMessageSender, srcSeqMsg.getName());
      }
    }
    _logger.warn(new EmbeddedMessage(loggedMsg, IReportManagerDefaultComponents.REFINEMENT, _srcDiagram));
  }

  /**
   * 
   */
  private boolean evaluateMessageSkip(SequenceMessage sequenceMessage, Component decomposedCpnt) {
    if (_tgtElement instanceof Part) {
      return !isInteracting(sequenceMessage, _tgtElement);
    } else if (_tgtElement instanceof Component) {
      return !isInteracting(sequenceMessage, decomposedCpnt);
    } else if (_tgtElement instanceof LogicalArchitecture) {
      return !isInteracting(sequenceMessage, decomposedCpnt);
    }
    return false;
  }

  /**
   * 
   */
  private boolean evaluateMessageSkip(ExecutionEnd executionEnd, Component decomposedCpnt) {
    if (_tgtElement instanceof Part) {
      return !isInteracting(executionEnd, _tgtElement);
    } else if (_tgtElement instanceof Component) {
      return !isInteracting(executionEnd, decomposedCpnt);
    } else if (_tgtElement instanceof LogicalArchitecture) {
      return !isInteracting(executionEnd, decomposedCpnt);
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
   * @param scenario
   * @param target
   */
  protected void applyNamingRule(NamedElement scenario, NamedElement target) {
    applyNamingRule(scenario, target, scenario.getName());
  }

  /**
   * 
   * @param scenario
   * @param target
   * @param name
   */
  protected void applyNamingRule(NamedElement scenario, NamedElement target, String name) {
    String scName = evaluateNamingRule(target, name);
    if (!scenario.getName().equals(scName)) {
      scenario.setName(scName);
    }
  }

  /**
   * 
   */
  public static String evaluateNamingRule(NamedElement target, String name) {
    return name;

// Naming Rule based on hierarchical Level is obsolete
//    if (target instanceof LogicalArchitecture) {
//      EObject container = target.eContainer();
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
//    else if (target instanceof LogicalComponent) {
//      String oldPrefix = MessageFormat.format(REFINED_SCENARIO_PREFIX, new Integer(LogicalComponentExt.getLCLevel((LogicalComponent) target)-1));
//      String newPrefix = MessageFormat.format(REFINED_SCENARIO_PREFIX, new Integer(LogicalComponentExt.getLCLevel((LogicalComponent) target)));
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
