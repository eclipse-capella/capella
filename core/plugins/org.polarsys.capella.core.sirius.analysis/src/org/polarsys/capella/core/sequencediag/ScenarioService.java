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
package org.polarsys.capella.core.sequencediag;

import static org.polarsys.capella.core.sirius.analysis.refresh.extension.InteractionRefreshExtension.getStateElementToContainerCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.ElementKind;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.information.ParameterDirection;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.AbstractFragment;
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.data.interaction.properties.controllers.InterfaceHelper;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.helpers.SequenceMessageExt;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;
import org.polarsys.capella.core.model.helpers.queries.filters.OnlySharedDataOrEventOrUnsetFilter;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.sirius.analysis.EventContextServices;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.core.sirius.analysis.InformationServices;
import org.polarsys.capella.core.sirius.analysis.SequenceDiagramServices;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

/**
 * Services to manipulate Capella scenario.
 */
public class ScenarioService {

  private static final String EMPTY_STRING = ICommonConstants.EMPTY_STRING;

  /**
   * Moves the end <code>toMove</code> just after the end <code>previousEnd</code>. used in common.odesign, oa.odesign,
   * sequences.odesign
   * 
   * @param toMove
   *          the end to move
   * @param previousEnd
   *          the previous end.
   * @return the moved end.
   */
  public EObject moveEndOnScenario(final InteractionFragment toMove, final InteractionFragment previousEnd) {
    return ScenarioExt.moveEndOnScenario(toMove, previousEnd);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public List<ExchangeItem> getSharedDataEventForSD(DSemanticDecorator elementView) {
    IQueryFilter filter = new OnlySharedDataOrEventOrUnsetFilter();
    return (List) QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_EXCHANGE_ITEMS_FOR_LIB,
        elementView.getTarget(), new QueryContext(), filter);
  }

  // END CODE REFACTOR

  /**
   * Move the end <code>toMove</code> just after the end <code>previousEnd</code> on the instance role of
   * <code>toMove</code>. used in common.odesign, oa.odesign, sequences.odesign
   * 
   * @param toMove
   *          the end to move.
   * @param previousEnd
   *          the previous end (it doesn't need to be on the same instance role of <code>toMove</code>).
   * @return the moved end.
   */
  public EObject moveEndOnInstanceRole(final InteractionFragment toMove, final InteractionFragment previousEnd) {
    // final Scenario scenario = (Scenario) previousEnd.eContainer();
    // final InstanceRole covered = toMove.getCoveredInstanceRoles().get(0);

    /*
     * First of all: move the messageEnd to the end of the list
     */
    // covered.getAbstractEnds().move(covered.getAbstractEnds().size() - 1, (AbstractEnd) toMove);

    /*
     * Compute the new index and move !
     */
    // final int previousEndIndex = scenario.getOwnedInteractionFragments().indexOf(previousEnd);
    //
    // AbstractEnd previousOnIR = null;
    // for (final AbstractEnd end : covered.getAbstractEnds()) {
    // if (end == previousEnd) {
    // previousOnIR = end;
    // break;
    // } else if (scenario.getOwnedInteractionFragments().indexOf(end) > previousEndIndex) {
    // break;
    // } else {
    // previousOnIR = end;
    // }
    // }
    // int newIndex = previousOnIR == null ? 0 : covered.getAbstractEnds().indexOf(previousOnIR) + 1;
    // if (newIndex >= covered.getAbstractEnds().size()) {
    // newIndex = covered.getAbstractEnds().size() - 1;
    // }
    // covered.getAbstractEnds().move(newIndex, (AbstractEnd) toMove);
    return toMove;
  }

  /**
   * Moves the end <code>toMove</code> on the beginning of the scenario. used in common.odesign, oa.odesign,
   * sequences.odesign
   * 
   * @param toMove
   *          the end to move
   * @return the moved end.
   */
  public EObject moveEndOnBeginingOfScenario(final InteractionFragment toMove) {
    return ScenarioExt.moveEndOnBeginingOfScenario(toMove);
  }

  /**
   * Move the end <code>toMove</code> on the begining of the instance role. used in common.odesign, oa.odesign,
   * sequences.odesign
   * 
   * @param toMove
   *          the end to move.
   * @return the moved end.
   */
  public EObject moveEndOnBeginingOfInstanceRole(final InteractionFragment toMove) {
    if (toMove instanceof AbstractEnd) {
      toMove.getCoveredInstanceRoles().get(0).getAbstractEnds().move(0, (AbstractEnd) toMove);
    }
    return toMove;
  }

  /**
   * Moves the message <code>toMove</code> just after the message <code>previousMessage</code>. used in common.odesign,
   * oa.odesign, sequences.odesign
   * 
   * @param toMove
   *          the message to move.
   * @param previousMessage
   *          the previous message.
   * @return the moved message.
   */
  public EObject moveMessage(final SequenceMessage toMove, final SequenceMessage previousMessage) {
    final Scenario scenario = (Scenario) toMove.eContainer();
    /*
     * First of all: move the message to the end of the list
     */
    scenario.getOwnedMessages().move(scenario.getOwnedMessages().size() - 1, toMove);

    /*
     * Compute the new index and move !
     */
    int newIndex = scenario.getOwnedMessages().indexOf(previousMessage) + 1;
    if (newIndex >= scenario.getOwnedMessages().size()) {
      newIndex = scenario.getOwnedMessages().size() - 1;
    }
    scenario.getOwnedMessages().move(newIndex, toMove);
    return toMove;
  }

  /**
   * Moves the message <code>toMove</code> on the beginning of the scenario. used in common.odesign, oa.odesign,
   * sequences.odesign
   * 
   * @param toMove
   *          the message to move.
   * @return the moved message.
   */
  public EObject moveMessageOnBegining(final SequenceMessage toMove) {
    final Scenario scenario = (Scenario) toMove.eContainer();
    scenario.getOwnedMessages().move(0, toMove);
    return toMove;
  }

  private SequenceMessage getMessageOpposite(EObject context) {
    return SequenceMessageExt.getOppositeSequenceMessage((SequenceMessage) context);
  }

  public String getInstanceRoleLabel(InstanceRole ir) {
    StringBuilder result = new StringBuilder();

    AbstractInstance representedInstance = ir.getRepresentedInstance();
    AbstractType type = representedInstance.getAbstractType();

    if (type == null || TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(type))) {
      result.append(EObjectExt.getText(representedInstance));
    } else {
      result.append(EObjectExt.getText(type));
    }

    // we check if the scenario contains other lifelines refering to the same part
    if (ir.eContainer() instanceof Scenario) {
      Scenario scenario = (Scenario) ir.eContainer();
      for (InstanceRole element : scenario.getOwnedInstanceRoles()) {
        if (element != ir && element.getRepresentedInstance() == ir.getRepresentedInstance()) {
          result.insert(0, " : ") //$NON-NLS-1$
              .insert(0, EObjectExt.getText(ir));
          break;
        }
      }
    }

    return result.toString();
  }

  /**
   * returns display name of sequence message used in common.odesign
   * 
   * @param message
   *          the message
   * @return display name of the message
   */
  public String getMessageName(SequenceMessage message) {
    return getMessageName(message, false);
  }

  /**
   * returns display name of sequence message
   * 
   * @param message
   *          the message
   * @return display name of the message
   */
  public String getDFMessageName(SequenceMessage message, DDiagram diagram) {
    if (message == null) {
      return EMPTY_STRING;
    }

    boolean showExchangeItems = false;
    boolean showExchangeItemsParameters = false;
    boolean showFunctionalExchanges = false;

    boolean showFEEI = false;
    boolean showCEEI = false;
    boolean showFEParams = false;
    boolean showFEEIParams = false;

    boolean showCEParams = false;
    boolean showCEEIParams = false;

    boolean showExchangeContext = false;
    boolean showCEExchangeContext = false;
    boolean showFEExchangeContext = false;

    boolean hideCallArguments = false;

    List<? extends AbstractExchangeItem> eiOnMessage = message.getExchangedItems();

    for (FilterDescription filter : diagram.getActivatedFilters()) {
      if (filter.getName().equals(IMappingNameConstants.SHOW_EXCHANGE_ITEMS)) {
        showExchangeItems = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_EXCHANGE_ITEMS_PARAMETERS)) {
        showExchangeItemsParameters = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_FUNCTIONAL_EXCHANGES)) {
        showFunctionalExchanges = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_FUNCTIONAL_EXCHANGES_ECHANGE_ITEMS)) {
        showFEEI = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_FUNCTIONAL_EXCHANGES_PARAMS)) {
        showFEParams = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_COMPONENT_EXCHANGES_ECHANGE_ITEMS)) {
        showCEEI = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_FUNCTIONAL_EXCHANGES_ECHANGE_ITEMS_PARAMS)) {
        showFEEIParams = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_COMPONENT_EXCHANGES_PARAMS)) {
        showCEParams = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_COMPONENT_EXCHANGES_EXCHANGE_ITEMS_PARAMS)) {
        showCEEIParams = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_EXCHANGE_CONTEXT)) {
        showExchangeContext = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_CE_EXCHANGE_CONTEXT)) {
        showExchangeContext = true;
        showCEExchangeContext = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_FE_EXCHANGE_CONTEXT)) {
        showExchangeContext = true;
        showFEExchangeContext = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_EI_EXCHANGE_CONTEXT)) {
        showExchangeContext = true;
      }
      if (filter.getName().equals(IMappingNameConstants.IS_HIDE_CALL_ARGUMENTS)) {
        hideCallArguments = true;
      }
    }

    StringBuilder result = new StringBuilder();

    if (DiagramDescriptionConstants.INTERFACE_SCENARIO.equals(diagram.getDescription().getName())) {
      result.append(getMessageName(message, hideCallArguments));
      if (showExchangeContext) {
        result.append(" "); //$NON-NLS-1$
        appendExchangeContext(message, result);
      }
      return result.toString().trim();
    }

    MessageEnd end = message.getSendingEnd() == null ? message.getReceivingEnd() : message.getSendingEnd();
    Event event = end.getEvent();
    AbstractEventOperation op = null;
    if (event != null) {
      if (event instanceof EventSentOperation) {
        EventSentOperation eso = (EventSentOperation) event;
        op = eso.getOperation();
      } else if (event instanceof EventReceiptOperation) {
        EventReceiptOperation ero = (EventReceiptOperation) event;
        op = ero.getOperation();
      }
    }

    if ((op != null) && showCEParams) {
      return getShowCEParams(op, eiOnMessage);
    }
    if ((op != null) && showCEEIParams) {
      return getShowCEEIParams(op, eiOnMessage);
    }
    if ((op != null) && showCEEI) {
      return getCEEIMessageName(op, eiOnMessage);
    }
    if ((op != null) && showFEEI) {
      return getFEEIMessageName(op, eiOnMessage);
    }
    if ((op != null) && (showFEEIParams || showFEParams)) {
      return showFeEiParams(op, showFEEIParams, eiOnMessage);
    }
    if (showExchangeItems || showFunctionalExchanges || showExchangeItemsParameters) {
      if (op instanceof FunctionalExchange) {
        if (showFunctionalExchanges || ((FunctionalExchange) op).getExchangedItems().isEmpty()) {
          result.append(getSafeName(op));
        } else {
          appendExchangeItems(eiOnMessage, op, showExchangeItemsParameters, result);
        }
      } else if (op instanceof ComponentExchange) {
        ComponentExchange ce = (ComponentExchange) op;
        if (showFunctionalExchanges) {
          appendFunctionalExchanges(ce, result);
        } else {
          appendExchangeItems(eiOnMessage, op, showExchangeItemsParameters, result);
        }
      } else {
        result.append(getSafeName(message));
        result.append(" "); //$NON-NLS-1$
      }
    } else if (showExchangeContext) {
      if (showCEExchangeContext && (op instanceof ComponentExchange)) {
        result.append(getSafeName(op));
      } else if (showFEExchangeContext) {
        appendFunctionalExchanges(op, result);
      }
      result.append(" "); //$NON-NLS-1$
      appendExchangeContext(message, result);
    } else {
      result.append(getSafeName(message));
      result.append(" "); //$NON-NLS-1$
    }
    return result.toString().trim();
  }

  private StringBuilder appendFunctionalExchanges(AbstractEventOperation op, StringBuilder builder) {
    if (op instanceof FunctionalExchange) {
      builder.append(getSafeName(op));
    } else if (op instanceof ComponentExchange) {
      ComponentExchange ce = (ComponentExchange) op;
      int size = ce.getOwnedComponentExchangeFunctionalExchangeAllocations().size();
      int index = 0;
      for (ComponentExchangeFunctionalExchangeAllocation fea : ce
          .getOwnedComponentExchangeFunctionalExchangeAllocations()) {
        builder.append(getSafeName(fea.getAllocatedFunctionalExchange()));
        if (++index < size) {
          builder.append(", "); //$NON-NLS-1$
        }
      }
    }
    return builder;
  }

  private static void appendExchangeItems(List<? extends AbstractExchangeItem> eiOnMessage, AbstractEventOperation op,
      boolean showExchangeItemParameters, StringBuilder builder) {
    List<? extends AbstractExchangeItem> eiOnOperation = Collections.emptyList();
    if (op instanceof ComponentExchange) {
      eiOnOperation = ((ComponentExchange) op).getConvoyedInformations();
    } else if (op instanceof FunctionalExchange) {
      eiOnOperation = ((FunctionalExchange) op).getExchangedItems();
    }
    List<? extends AbstractExchangeItem> selectEIList = firstNonEmpty(eiOnMessage, eiOnOperation);
    if (selectEIList.size() != 0) {
      builder.append("["); //$NON-NLS-1$
    }
    int index = 0;
    for (AbstractExchangeItem ei : selectEIList) {
      builder.append(InformationServices.getEILabel(ei, showExchangeItemParameters));
      if (++index < selectEIList.size()) {
        builder.append(", "); //$NON-NLS-1$
      }
    }
    if (selectEIList.size() != 0) {
      builder.append("]"); //$NON-NLS-1$
    }
  }

  private static void appendExchangeContext(SequenceMessage message, StringBuilder builder) {
    builder.append(String.format("{%s}", message.getExchangeContext() == null ? "" //$NON-NLS-1$ //$NON-NLS-2$
        : CapellaServices.getService().getConstraintLabel(message.getExchangeContext())));
  }

  private static String getSafeName(AbstractNamedElement fe) {
    if ("".equals(fe.getName()) || (null == fe.getName())) { //$NON-NLS-1$
      return "<undefined>"; //$NON-NLS-1$
    }
    return EObjectExt.getText(fe);
  }

  public static String getShowCEEIParams(AbstractEventOperation op, List<? extends AbstractExchangeItem> eiOnMessage) {
    if (!(op instanceof ComponentExchange)) {
      return getFEEIMessageName(op, eiOnMessage);
    }
    ComponentExchange ce = (ComponentExchange) op;
    StringBuilder result = new StringBuilder();
    result.append(getSafeName(op));
    result.append(" "); //$NON-NLS-1$
    result.append("["); //$NON-NLS-1$
    int indice = 0;
    List<? extends AbstractExchangeItem> selectEIList = firstNonEmpty(eiOnMessage, ce.getConvoyedInformations());
    for (AbstractExchangeItem ei : selectEIList) {
      result.append(InformationServices.getEILabel(ei, true));
      indice++;
      if (indice < selectEIList.size()) {
        result.append(", "); //$NON-NLS-1$
      }
    }

    result.append("]"); //$NON-NLS-1$
    return result.toString();
  }

  public static String getShowCEParams(AbstractEventOperation op, List<? extends AbstractExchangeItem> eiOnMessage) {
    if (op instanceof FunctionalExchange) {
      return ""; //$NON-NLS-1$
    }
    ComponentExchange ce = (ComponentExchange) op;
    StringBuilder result = new StringBuilder();
    List<? extends AbstractExchangeItem> selectEIList;
    selectEIList = firstNonEmpty(eiOnMessage, ce.getConvoyedInformations());

    result.append(getSafeName(ce));
    result.append("("); //$NON-NLS-1$
    int indice = 0;

    List<ExchangeItemElement> eies = new ArrayList<ExchangeItemElement>();
    for (AbstractExchangeItem aei : selectEIList) {
      if (aei instanceof ExchangeItem) {
        ExchangeItem ei = (ExchangeItem) aei;
        eies.addAll(ei.getOwnedElements());
      }
    }

    for (ExchangeItemElement eie : eies) {
      AbstractType type = eie.getAbstractType();
      if (type != null) {
        result.append(type.getName());
      } else {
        result.append("<undefined>"); //$NON-NLS-1$
      }
      indice++;
      if (indice < eies.size()) {
        result.append(", "); //$NON-NLS-1$
      }

    }

    result.append(")"); //$NON-NLS-1$

    return result.toString();

  }

  public static String showFeEiParams(AbstractEventOperation op, boolean showEIName,
      List<? extends AbstractExchangeItem> eiOnMessage) {
    FunctionalExchange fe = null;
    if (op instanceof ComponentExchange) {
      for (ComponentExchangeFunctionalExchangeAllocation fea : ((ComponentExchange) op)
          .getOwnedComponentExchangeFunctionalExchangeAllocations()) {
        fe = fea.getAllocatedFunctionalExchange();
      }
    } else {
      fe = (FunctionalExchange) op;
    }
    StringBuilder result = new StringBuilder();
    List<? extends AbstractExchangeItem> selectEIList = firstNonEmpty(eiOnMessage,
        fe != null ? fe.getExchangedItems() : Collections.emptyList());

    result.append(getSafeName(fe));

    int indice = 0;
    if (showEIName) {
      result.append(" "); //$NON-NLS-1$
      result.append("["); //$NON-NLS-1$
      for (AbstractExchangeItem ei : selectEIList) {
        result.append(InformationServices.getEILabel(ei, true));
        indice++;
        if (indice < selectEIList.size()) {
          result.append(", "); //$NON-NLS-1$
        }
      }
      result.append("]"); //$NON-NLS-1$

    } else {
      result.append("("); //$NON-NLS-1$
      // looking for information
      List<ExchangeItemElement> eies = new ArrayList<>();
      for (AbstractExchangeItem aei : selectEIList) {
        if (aei instanceof ExchangeItem) {
          ExchangeItem ei = (ExchangeItem) aei;
          eies.addAll(ei.getOwnedElements());
        }
      }
      // using gathered information
      for (ExchangeItemElement eie : eies) {
        AbstractType type = eie.getAbstractType();
        if (type != null) {
          result.append(type.getName());
        } else {
          result.append("<undefined>"); //$NON-NLS-1$
        }
        indice++;
        if (indice < eies.size()) {
          result.append(", "); //$NON-NLS-1$
        }

      }
      result.append(")"); //$NON-NLS-1$
    }

    return result.toString();
  }

  public static String getFEEIMessageName(AbstractEventOperation op, List<? extends AbstractExchangeItem> eiOnMessage) {
    StringBuilder result = new StringBuilder();
    List<? extends AbstractExchangeItem> selectEIList;
    if (op instanceof FunctionalExchange) {
      result.append(getSafeName(op));
      selectEIList = firstNonEmpty(eiOnMessage, ((FunctionalExchange) op).getExchangedItems());
    } else {
      ComponentExchange ce = (ComponentExchange) op;
      List<ExchangeItem> itemsOfFe = new ArrayList<>();
      int indice = 0;
      for (ComponentExchangeFunctionalExchangeAllocation fea : ce
          .getOwnedComponentExchangeFunctionalExchangeAllocations()) {
        FunctionalExchange fe = fea.getAllocatedFunctionalExchange();
        result.append(getSafeName(fe));
        itemsOfFe.addAll(fe.getExchangedItems());
        indice++;
        if (indice < ce.getOwnedComponentExchangeFunctionalExchangeAllocations().size()) {
          result.append(", "); //$NON-NLS-1$
        }
      }
      selectEIList = firstNonEmpty(eiOnMessage, itemsOfFe);
    }

    result.append(" "); //$NON-NLS-1$
    result.append("["); //$NON-NLS-1$
    int indice = 0;

    for (AbstractExchangeItem ei : selectEIList) {
      result.append(InformationServices.getEILabel(ei, false));
      indice++;
      if (indice < selectEIList.size()) {
        result.append(", "); //$NON-NLS-1$
      }
    }

    result.append("]"); //$NON-NLS-1$

    return result.toString();
  }

  public static String getCEEIMessageName(AbstractEventOperation op, List<? extends AbstractExchangeItem> eiOnMessage) {
    if (!(op instanceof ComponentExchange)) {
      return getFEEIMessageName(op, eiOnMessage);
    }
    ComponentExchange ce = (ComponentExchange) op;
    StringBuilder result = new StringBuilder();
    result.append(getSafeName(op));
    result.append(" "); //$NON-NLS-1$
    result.append("["); //$NON-NLS-1$
    int indice = 0;
    List<? extends AbstractExchangeItem> selectEIList = firstNonEmpty(eiOnMessage, ce.getConvoyedInformations());
    for (AbstractExchangeItem ei : selectEIList) {
      result.append(InformationServices.getEILabel(ei, false));
      indice++;
      if (indice < selectEIList.size()) {
        result.append(", "); //$NON-NLS-1$
      }
    }

    result.append("]"); //$NON-NLS-1$
    return result.toString();
  }

  private static List<? extends AbstractExchangeItem> firstNonEmpty(List<? extends AbstractExchangeItem> first,
      List<? extends AbstractExchangeItem> second) {
    return !first.isEmpty() ? first : second;
  }

  /**
   * returns display name of sequence message
   * 
   * @param message
   *          the message
   * @return display name of the message
   */
  public String getMessageName(SequenceMessage message, boolean hideParameters) {
    NamedElement messageOperation = null;

    if (InterfaceHelper.isSharedDataAccess(message)) {
      return SequenceMessageExt.getMessageNameForSharedDataAccess(message);
    }

    if (message == null) {
      return ""; //$NON-NLS-1$
    }

    if ((message.getKind() == MessageKind.REPLY) && hideParameters) {
      return ""; //$NON-NLS-1$
    }

    Event associatedEvent;

    // because of lost/found message, we must select one or the other side
    // of the message
    if (message.getReceivingEnd() != null) {
      associatedEvent = message.getReceivingEnd().getEvent();
    } else {
      associatedEvent = message.getSendingEnd().getEvent();
      // end of lost/found message difference
    }

    if (associatedEvent instanceof EventReceiptOperation) {
      EventReceiptOperation event = (EventReceiptOperation) associatedEvent;
      if (event.getOperation() != null) {
        messageOperation = event.getOperation();
      }
    }
    if (associatedEvent instanceof EventSentOperation) {
      EventSentOperation event = (EventSentOperation) associatedEvent;
      if (event.getOperation() != null) {
        messageOperation = event.getOperation();
      }
    }
    if (messageOperation == null) {
      return message.getName();
    }
    StringBuilder sb = new StringBuilder();

    if (messageOperation instanceof ExchangeItemAllocation) {
      messageOperation = ((ExchangeItemAllocation) messageOperation).getAllocatedItem();
    }

    int nbParameters = 0;
    int nbOutParameters = 0;

    if (messageOperation instanceof ExchangeItem) {
      ExchangeItem item = (ExchangeItem) messageOperation;
      for (ExchangeItemElement element : item.getOwnedElements()) {
        if (element.getKind() == ElementKind.MEMBER) {
          nbParameters++;
          if ((element.getDirection() == ParameterDirection.OUT) || (element.getDirection() == ParameterDirection.INOUT)
              || (element.getDirection() == ParameterDirection.RETURN)) {
            nbOutParameters++;
          }
        }
      }
    } else if (messageOperation instanceof Operation) {
      for (Parameter param : ((Operation) messageOperation).getOwnedParameters()) {
        nbParameters++;
        if ((param.getDirection() == ParameterDirection.OUT) || (param.getDirection() == ParameterDirection.INOUT)
            || (param.getDirection() == ParameterDirection.RETURN)) {
          nbOutParameters++;
        }
      }
    }

    // don't display the name if you display parameters of a return message
    if ((!hideParameters && (message.getKind() == MessageKind.REPLY) && (nbOutParameters == 0))) {
      sb.append(messageOperation.getName());
    }
    if (message.getKind() != MessageKind.REPLY) {
      sb.append(messageOperation.getName());
    }

    if (!hideParameters) {
      sb.append("("); //$NON-NLS-1$

      List<String> paramNames = new ArrayList<String>(nbParameters);

      if (messageOperation instanceof ExchangeItem) {
        ExchangeItem item = (ExchangeItem) messageOperation;
        for (ExchangeItemElement element : item.getOwnedElements()) {
          if (element.getKind() == ElementKind.MEMBER) {
            String name = getNameParameter(element, element.getType(), element.getDirection(), message);
            if (name != null) {
              paramNames.add(name);
            }
          }
        }
      } else if (messageOperation instanceof Operation) {

        for (Parameter param : ((Operation) messageOperation).getOwnedParameters()) {
          String name = getNameParameter(param, param.getAbstractType(), param.getDirection(), message);
          if (name != null) {
            paramNames.add(name);
          }
        }
      }

      int numParam = 0;
      for (String name : paramNames) {
        sb.append(name);
        if (numParam < (paramNames.size() - 1)) {
          sb.append(", "); //$NON-NLS-1$
        }
        numParam++;
      }

      sb.append(")"); //$NON-NLS-1$
    }
    return sb.toString();
  }

  private String getNameParameter(NamedElement parameter, AbstractType type, ParameterDirection direction,
      SequenceMessage message) {
    String name = null;
    if ((message.getKind() == MessageKind.REPLY)
        && ((direction == ParameterDirection.OUT) || (direction == ParameterDirection.INOUT))) {
      name = parameter.getName();
    } else if ((message.getKind() == MessageKind.REPLY) && (direction == ParameterDirection.RETURN)) {
      if (type == null) {
        name = "";//$NON-NLS-1$
      } else {
        name = type.getName();
      }
    } else if (((message.getKind() == MessageKind.SYNCHRONOUS_CALL)
        || (message.getKind() == MessageKind.ASYNCHRONOUS_CALL))
        && ((direction == ParameterDirection.IN) || (direction == ParameterDirection.INOUT))) {
      name = parameter.getName();
    }

    return name;
  }

  /**
   * not used, but can be reused
   */
  public String getMessagePosition(final SequenceMessage message) {
    // the message position is the number of order in the case of a sent
    // message, and is coordinated
    // to the sending message in the context of a return message.
    if (message == null) {
      return "?"; //$NON-NLS-1$
    }
    if (message.getKind() == MessageKind.REPLY) {
      return getMessagePosition(getMessageOpposite(message));
    }

    final Scenario scenario = (Scenario) message.eContainer();
    final int basicPos = scenario.getOwnedMessages().indexOf(message);
    int pos = basicPos + 1;
    for (final SequenceMessage ownedMessage : scenario.getOwnedMessages()) {
      if (ownedMessage.getKind() == MessageKind.REPLY) {
        pos--;
      }
      if (ownedMessage == message) {
        break;
      }
    }
    return Integer.toString(pos);
  }

  /**
   * used in common.odesign, oa.odesign, sequences.odesign
   * 
   * @param end
   * @return
   */
  public MessageEnd getPreviousMessageEnd(InteractionFragment end) {
    if (end instanceof MessageEnd) {
      return (MessageEnd) end;
    }
    final int index = ((Scenario) end.eContainer()).getOwnedInteractionFragments().indexOf(end);
    if (index == 0) {
      return null;
    }
    return getPreviousMessageEnd(((Scenario) end.eContainer()).getOwnedInteractionFragments().get(index - 1));
  }

  /**
   * used in common.odesign, oa.odesign, sequences.odesign
   * 
   * @param message
   * @return
   */
  public String newCallName(final SequenceMessage message) {
    return "Message Call"; //$NON-NLS-1$
  }

  /**
   * return the
   * 
   * @param ir
   * @return
   */
  public Collection<EObject> getInteractionStatesOnExecution(InstanceRole ir) {
    // get state fragments from cache
    Collection<EObject> stateFragments = getStateElementToContainerCache(ir);
    // if not present, compute it and put it in cache
    if (stateFragments == null) {
      stateFragments = EventContextServices.getStateDirectEvents(ir, ir);
    }
    return stateFragments;
  }

  public Collection<EObject> getInteractionStatesOnExecution(Execution exec) {
    // get state fragments from cache
    Collection<EObject> stateFragments = getStateElementToContainerCache(exec);
    // if not present, compute it and put it in cache
    if (stateFragments == null) {
      stateFragments = EventContextServices.getStateDirectEvents(exec,
          SequenceDiagramServices.currentInstanceRole(exec));
    }
    return stateFragments;
  }

  public List<InstanceRole> getCoveredFromAbstractFragment(AbstractFragment af) {
    return af.getStart().getCoveredInstanceRoles();
  }

  public InstanceRole getCoveredFromExecOrIR(EObject context, EObject container) {
    if (container instanceof Execution) {
      Execution exec = (Execution) container;
      return exec.getCovered();
    }
    return (InstanceRole) container;
  }

  public InteractionFragment getOperandBegin(InteractionOperand operand) {
    return operand;
  }

  public InteractionFragment getOperandEnd(InteractionOperand operand) {
    CombinedFragment cf = null;
    Scenario s = (Scenario) operand.eContainer();
    // Find the CombinedFragment containing the given operand.
    for (TimeLapse tl : s.getOwnedTimeLapses()) {
      if (tl instanceof CombinedFragment) {
        CombinedFragment cftmp = (CombinedFragment) tl;
        if (cftmp.getReferencedOperands().contains(operand)) {
          cf = cftmp;
          break;
        }
      }
    }
    // Can not find a CombinedFragment containing the operand -> Stop here.
    // FIXME this null value can not be returned in our context, but if it was returned, the diagram would be
    // corrupted...
    if (null == cf) {
      return null;
    }
    // we can't use referencedOperand to check order, we must look
    // in ownedInteractionFragment
    boolean nextWillBeGood = false;
    for (InteractionFragment fragment : s.getOwnedInteractionFragments()) {
      if (fragment instanceof InteractionOperand) {
        if (cf.getReferencedOperands().contains(fragment) && nextWillBeGood) {
          return fragment;
        }
      }
      if (fragment == operand) {
        nextWillBeGood = true;
      }
    }
    // if we are here, we cannot found a next, so next will be the end of
    // the CF.
    return cf.getFinish();
  }

  public boolean isFunctionalExecution(Execution execution) {
    InteractionFragment end = execution.getStart();
    if (end instanceof MessageEnd) {
      MessageEnd me = (MessageEnd) end;
      if (me.getEvent() instanceof EventReceiptOperation) {
        EventReceiptOperation ero = (EventReceiptOperation) me.getEvent();
        return ero.getOperation() instanceof FunctionalExchange;
      }
    }
    return false;
  }

  /**
   * Returns the scope of available scenario which can be use in an InteractionUse element
   */
  public List<EObject> getReferenceScope(Scenario scenario) {
    List<EObject> availableElements = new ArrayList<EObject>();

    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(
        InteractionPackage.Literals.INTERACTION_USE, InteractionPackage.Literals.INTERACTION_USE__REFERENCED_SCENARIO);

    if (query != null) {
      availableElements.addAll(query.getAvailableElements(scenario));
    }

    return availableElements;
  }

  /**
   * Returns available state modes which can be added into an IS IS-Insert-StateMode
   */
  public Collection<AbstractState> getISStateModes(AbstractInstance instance) {
    return ScenarioExt.getAvailableStateModeStateFragment(instance);
  }

  public Collection<AbstractFunction> getFunctionsForStateAtOA(EObject context, AbstractInstance instance) {
    return ScenarioExt.getAvailableFunctionsStateFragment(instance);
  }

  public Collection<AbstractFunction> getFunctionsForState(EObject context, Component component) {
    return ScenarioExt.getAvailableFunctionsStateFragment(component);
  }

  @SuppressWarnings("nls")
  public String getOperandLabel(InteractionOperand operand) {
    StringBuilder builder = null;
    if (operand.getGuard() != null) {
      builder = new StringBuilder("[ ");
      builder.append(CapellaServices.getService().getConstraintLabel(operand.getGuard())).append(" ]");
    }
    return builder == null ? "" : builder.toString();
  }

  public boolean isValidScenarioDrop(EObject context, Scenario scenario, EObject element) {

    // if already existing in diagram, not valid
    if (element instanceof Component) {
      for (InstanceRole ir : scenario.getOwnedInstanceRoles()) {
        if ((ir.getRepresentedInstance() != null) && (ir.getRepresentedInstance().getAbstractType() != null)) {
          if (ir.getRepresentedInstance().getAbstractType().equals(element)) {
            return false;
          }
        }
      }
    } else if (element instanceof AbstractFunction || element instanceof Role) {
      for (InstanceRole ir : scenario.getOwnedInstanceRoles()) {
        if (ir.getRepresentedInstance().equals(element)) {
          return false;
        }
      }
    }

    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(scenario);
    BlockArchitecture architecture2 = BlockArchitectureExt.getRootBlockArchitecture(element);

    // dropped element shall be in the same architecture than scenario
    if (!architecture.equals(architecture2)) {
      return false;
    }

    // compatibility :
    if ((scenario.getKind() == ScenarioKind.DATA_FLOW) || (scenario.getKind() == ScenarioKind.INTERFACE)) {
      return element instanceof Component && isCorrectComponentLevel(context, element);
    } else if (scenario.getKind() == ScenarioKind.FUNCTIONAL) {
      return (element instanceof AbstractFunction);
    } else if (scenario.getKind() == ScenarioKind.INTERACTION) {
      if (scenario.getOwnedInstanceRoles().size() != 0) {
        InstanceRole firstIr = scenario.getOwnedInstanceRoles().get(0);
        if (firstIr.getRepresentedInstance() instanceof OperationalActivity) {
          return (element instanceof OperationalActivity);
        }
        return (element instanceof Entity) || (element instanceof Role);
      }
      // empty diagram
      return (element instanceof Entity) || (element instanceof Role) || (element instanceof OperationalActivity);
    }
    return true;
  }

  /**
   * in a DnD of SystemComponent, check that this drop is legal : the dropped Element must be inside the context if it
   * is a component. If it's an actor, all cases are legal.
   * 
   * @param context
   *          contextual component
   * @param element
   *          dropped component
   * @return
   */
  private boolean isCorrectComponentLevel(EObject context, EObject element) {
    if (ComponentExt.isActor(element)) {
      return true;
    }

    // find the carrier component of the scenario
    Component referenceComponent = null;
    EObject container = context;
    while (referenceComponent == null && container != null) {
      container = container.eContainer();
      if (container instanceof Component) {
        referenceComponent = (Component) container; // found
      }
    }

    // element is in the parts of this component or below
    if (referenceComponent != null) {
      return ComponentExt.getAllSubUsedComponents(referenceComponent).contains(element);
    }
    return true;
  }

  /**
   * Returns a list of exchange context constraints. If the argument is a diagram that targets a Scenario, the result
   * contains the set of all exchange contexts of all sequence messages in the scenario. If the argument is a diagram
   * element that targets a sequence message and that sequence message has an exchange context, the result will contain
   * exactly that exchange context. Otherwise the result is empty.
   * 
   * @param context
   *          The sirius invocation context
   * @return A possibly empty list of constraints
   **/
  public List<EObject> getExchangeContextsToInsertInDiagram(EObject context) {
    List<EObject> result = new ArrayList<EObject>(0);
    if (context instanceof DDiagram) {
      DSemanticDecorator diagram = (DSemanticDecorator) context;
      EObject target = diagram.getTarget();
      if ((null != target) && (target instanceof Scenario)) {
        for (SequenceMessage msg : ((Scenario) target).getOwnedMessages()) {
          if ((msg.getExchangeContext() != null) && !result.contains(msg.getExchangeContext())) {
            result.add(msg.getExchangeContext());
          }
        }
      }
    } else if (context instanceof DDiagramElement) {
      DDiagramElement element = (DDiagramElement) context;
      EObject target = element.getTarget();
      if ((null != target) && (target instanceof SequenceMessage)
          && (((SequenceMessage) target).getExchangeContext() != null)) {
        result.add(((SequenceMessage) target).getExchangeContext());
      }
    }
    return result;
  }

  /**
   * Gets the exchange context constraints currently visible in a given context.
   * 
   * @param context
   *          : a diagram element, or a diagram
   * @return list of visible exchange contexts
   */
  public List<EObject> getVisibleExchangeContexts(DSemanticDecorator context, DDiagram diagram) {

    Collection<Constraint> allPresentConstraints = new HashSet<Constraint>();
    Collection<Constraint> allAvailableExchangeContexts = new HashSet<Constraint>();

    for (DDiagramElement elem : diagram.getDiagramElements()) {
      if (elem.getTarget() instanceof Constraint) {
        allPresentConstraints.add((Constraint) elem.getTarget());
      } else if ((elem.getTarget() instanceof SequenceMessage)
          && (((SequenceMessage) elem.getTarget()).getExchangeContext() != null)) {
        if ((context == diagram) || (context == elem)) {
          allAvailableExchangeContexts.add(((SequenceMessage) elem.getTarget()).getExchangeContext());
        }
      }
      allPresentConstraints.retainAll(allAvailableExchangeContexts);
    }
    return new ArrayList<EObject>(allPresentConstraints);
  }

  public String getOperatorLabel(EObject context) {
    String operatorkind = null;

    if (context instanceof CombinedFragment) {
      CombinedFragment combfragment = (CombinedFragment) context;
      operatorkind = " " + combfragment.getOperator().getLiteral(); //$NON-NLS-1$
    }
    return operatorkind;
  }

  public String addScenarioPrefix(String name, String prefix) {

    if (!name.startsWith(prefix)) {
      name = prefix + " " + name; //$NON-NLS-1$
    }

    return name;
  }

  public List<Part> getAllMultiInstanceRoleParts(Scenario scenario) {
    BlockArchitecture ba = BlockArchitectureExt.getRootBlockArchitecture(scenario);
    Collection<EObject> roots = new ArrayList<EObject>();
    roots.add(BlockArchitectureExt.getComponentPkg(ba, false));
    return getAllParts(Collections2.filter(roots, Predicates.notNull()));
  }

  public List<Part> getAllMultiInstanceRoleComponentParts(Scenario scenario) {
    // select wizard wants a list
    return new ArrayList<Part>(Collections2.filter(getAllMultiInstanceRoleParts(scenario), new Predicate<Part>() {
      @Override
      public boolean apply(Part input) {
        return !ComponentExt.isActor(input);
      }
    }));
  }

  public List<Part> getAllMultiInstanceRoleActorParts(Scenario scenario) {
    // select wizard wants a list
    return new ArrayList<Part>(Collections2.filter(getAllMultiInstanceRoleParts(scenario), new Predicate<Part>() {
      @Override
      public boolean apply(Part input) {
        return ComponentExt.isActor(input);
      }
    }));
  }

  private List<Part> getAllParts(Collection<EObject> roots) {
    List<Part> result = new ArrayList<Part>();
    for (Iterator<EObject> it = EcoreUtil.getAllContents(roots); it.hasNext();) {
      EObject next = it.next();
      if (next instanceof Part) {
        result.add((Part) next);
      }
    }
    return result;
  }

}
