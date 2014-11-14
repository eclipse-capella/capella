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
package org.polarsys.capella.core.sequencediag.specific.tool.internal.edit.policy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.common.ui.tools.api.selection.EObjectSelectionWizard;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.AbstractSiriusEditPolicy;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeItemExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;

/**
 * Custom edit policy to choose a service.
 */
public class ChooseServiceEditPolicy extends AbstractSiriusEditPolicy {

  /**
   * {@inheritDoc}
   * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#understandsRequest(org.eclipse.gef.Request)
   */
  @SuppressWarnings("static-access")
  @Override
  public boolean understandsRequest(Request request) {
    if (RequestConstants.REQ_OPEN.equals(request.getType())) {
      return true;
    }
    return false;
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse.gef.Request)
   */
  @SuppressWarnings("static-access")
  @Override
  public Command getCommand(final Request request) {
    if (RequestConstants.REQ_OPEN.equals(request.getType())) {
      final DSemanticDecorator semanticDecorator = this.getFirstDecorateSemanticElement();
      if (semanticDecorator.getTarget() instanceof SequenceMessage) {
        final CompoundCommand cmd = new CompoundCommand();
        final SequenceMessage sequenceMessage = (SequenceMessage) semanticDecorator.getTarget();
        cmd.add(new Command() {

          private AbstractEventOperation operation;

          @SuppressWarnings({ "synthetic-access", "deprecation" })
          @Override
          public void execute() {
            if (sequenceMessage.getKind() == MessageKind.SYNCHRONOUS_CALL) {
              operation = chooseService();
              if (operation != null) {
                affectService(sequenceMessage, operation).execute();
                canUseInterface((Interface) operation.eContainer(), sequenceMessage);
              }
            } else if (sequenceMessage.getKind() == MessageKind.ASYNCHRONOUS_CALL) {
              operation = chooseSignal();
              if (operation != null) {
                affectSignal(sequenceMessage, operation).execute();
                canUseInterface((Interface) operation.eContainer(), sequenceMessage);
              }
            }
            new ICommandProxy(new GMFCommandWrapper(getEditingDomain(), new RecordingCommand(getEditingDomain()) {
              @Override
              protected void doExecute() {
                getSirius().refresh();
              }
            })).execute();
            // Do not call RefreshDiagramCommand anymore since Sirius 4.18.
          }

          @Override
          public String getLabel() {
            return "Affect service"; //$NON-NLS-1$
          }

          @Override
          public String getDebugLabel() {
            return getLabel();
          }
        });
        return cmd;
      }
    }
    return null;
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getTargetEditPart(org.eclipse.gef.Request)
   */
  @Override
  public EditPart getTargetEditPart(Request request) {
    return getHost();
  }

  public void canUseInterface(Interface ownerClassifier, SequenceMessage sequenceMessage) {

    final InstanceRole source = sequenceMessage.getSendingEnd().getCovered();
    final InstanceRole target = sequenceMessage.getReceivingEnd().getCovered();

    final AbstractInstance client = source.getRepresentedInstance();
    final AbstractInstance provider = target.getRepresentedInstance();

    if ((client.getAbstractType() instanceof Component) && (provider.getAbstractType() instanceof Component)) {

      final Component clientComponent = (Component) client.getAbstractType();
      final Component providerComponent = (Component) provider.getAbstractType();

      if (!clientComponent.getUsedInterfaces().contains(ownerClassifier)) {
        final InterfaceUse interfaceUse = CsFactory.eINSTANCE.createInterfaceUse();
        new ICommandProxy(new GMFCommandWrapper(getEditingDomain(), new SetCommand(getEditingDomain(), interfaceUse,
            CsPackage.eINSTANCE.getInterfaceUse_UsedInterface(), ownerClassifier))).execute();
        new ICommandProxy(new GMFCommandWrapper(getEditingDomain(), new AddCommand(getEditingDomain(), clientComponent.getOwnedInterfaceUses(), interfaceUse)))
            .execute();
      }

      if (!providerComponent.getImplementedInterfaces().contains(ownerClassifier)) {
        final InterfaceImplementation interfaceImplementation = CsFactory.eINSTANCE.createInterfaceImplementation();
        new ICommandProxy(new GMFCommandWrapper(getEditingDomain(), new SetCommand(getEditingDomain(), interfaceImplementation,
            CsPackage.eINSTANCE.getInterfaceImplementation_ImplementedInterface(), ownerClassifier))).execute();
        new ICommandProxy(new GMFCommandWrapper(getEditingDomain(), new AddCommand(getEditingDomain(), providerComponent.getOwnedInterfaceImplementations(),
            interfaceImplementation))).execute();
      }

    }

  }

  public Command affectService(SequenceMessage sequenceMessage, AbstractEventOperation operation) {

    if (operation != null) {

      final MessageEnd send = sequenceMessage.getSendingEnd();
      final MessageEnd receive = sequenceMessage.getReceivingEnd();

      final org.eclipse.emf.common.command.CompoundCommand cc = new org.eclipse.emf.common.command.CompoundCommand();

      EventSentOperation eventSentOperation = getSentEvent(send, cc);

      EventReceiptOperation eventReceiptOperation = getReceiptEvent(receive, cc);

      cc.append(new SetCommand(getEditingDomain(), eventSentOperation, InteractionPackage.eINSTANCE.getEventSentOperation_Operation(), operation));
      cc.append(new SetCommand(getEditingDomain(), eventReceiptOperation, InteractionPackage.eINSTANCE.getEventReceiptOperation_Operation(), operation));
      cc.append(new SetCommand(getEditingDomain(), eventSentOperation, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(),
          "sndOp_" + operation.getName() + "_" //$NON-NLS-1$ //$NON-NLS-2$
              + sequenceMessage.getKind().getName()));
      cc.append(new SetCommand(getEditingDomain(), eventReceiptOperation, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(),
          "rcvOp_" + operation.getName() + "_" //$NON-NLS-1$ //$NON-NLS-2$
              + sequenceMessage.getKind().getName()));

      final SequenceMessage reply = findReply(sequenceMessage);

      if (reply != null) {
        final MessageEnd sendReply = reply.getSendingEnd();
        final MessageEnd receiveReply = reply.getReceivingEnd();

        final EventSentOperation eventSentOperationReply = getSentEvent(sendReply, cc);
        final EventReceiptOperation eventReceiptOperationReply = getReceiptEvent(receiveReply, cc);

        cc.append(new SetCommand(getEditingDomain(), eventSentOperationReply, InteractionPackage.eINSTANCE.getEventSentOperation_Operation(), operation));
        cc.append(new SetCommand(getEditingDomain(), eventReceiptOperationReply, InteractionPackage.eINSTANCE.getEventReceiptOperation_Operation(), operation));
        cc.append(new SetCommand(getEditingDomain(), eventSentOperationReply, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(),
            "sndOp_" + operation.getName() + "_" //$NON-NLS-1$ //$NON-NLS-2$
                + reply.getKind().getName()));
        cc.append(new SetCommand(getEditingDomain(), eventReceiptOperationReply, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(),
            "rcvOp_" + operation.getName() + "_" //$NON-NLS-1$ //$NON-NLS-2$
                + reply.getKind().getName()));

        cc.append(new SetCommand(getEditingDomain(), reply, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(), operation.getName()));
        cc.append(new SetCommand(getEditingDomain(), reply.getSendingEnd(), ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(),
            "sndMsgEnd_" + operation.getName() + "_" //$NON-NLS-1$ //$NON-NLS-2$
                + reply.getKind().getName()));
        cc.append(new SetCommand(getEditingDomain(), reply.getReceivingEnd(), ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(),
            "rcvMsgEnd_" + operation.getName() + "_" //$NON-NLS-1$ //$NON-NLS-2$
                + reply.getKind().getName()));
      }

      cc.append(new SetCommand(getEditingDomain(), sequenceMessage, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(), operation.getName()));
      cc.append(new SetCommand(getEditingDomain(), sequenceMessage.getSendingEnd(), ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(),
          "sndMsgEnd_" + operation.getName() + "_" //$NON-NLS-1$ //$NON-NLS-2$
              + sequenceMessage.getKind().getName()));
      cc.append(new SetCommand(getEditingDomain(), sequenceMessage.getReceivingEnd(), ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(),
          "rcvMsgEnd_" + operation.getName() + "_" //$NON-NLS-1$ //$NON-NLS-2$
              + sequenceMessage.getKind().getName()));

      return new ICommandProxy(new GMFCommandWrapper(getEditingDomain(), cc));
    }
    return UnexecutableCommand.INSTANCE;
  }

  private Command affectSignal(SequenceMessage sequenceMessage, AbstractEventOperation operation) {
    if (operation != null) {
      final MessageEnd send = sequenceMessage.getSendingEnd();
      final MessageEnd receive = sequenceMessage.getReceivingEnd();

      final org.eclipse.emf.common.command.CompoundCommand cc = new org.eclipse.emf.common.command.CompoundCommand();

      final EventSentOperation eventSentOperation = getSentEvent(send, cc);
      final EventReceiptOperation eventReceiptOperation = getReceiptEvent(receive, cc);

      cc.append(new SetCommand(getEditingDomain(), eventSentOperation, InteractionPackage.eINSTANCE.getEventSentOperation_Operation(), operation));
      cc.append(new SetCommand(getEditingDomain(), eventReceiptOperation, InteractionPackage.eINSTANCE.getEventReceiptOperation_Operation(), operation));

      cc.append(new SetCommand(getEditingDomain(), eventSentOperation, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(),
          "sndOp_" + operation.getName() + "_" //$NON-NLS-1$ //$NON-NLS-2$
              + sequenceMessage.getKind().getName()));
      cc.append(new SetCommand(getEditingDomain(), eventReceiptOperation, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(),
          "rcvOp_" + operation.getName() + "_" //$NON-NLS-1$ //$NON-NLS-2$
              + sequenceMessage.getKind().getName()));

      cc.append(new SetCommand(getEditingDomain(), sequenceMessage, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(), operation.getName()));
      cc.append(new SetCommand(getEditingDomain(), sequenceMessage.getSendingEnd(), ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(),
          "sndMsgEnd_" + operation.getName() + "_" //$NON-NLS-1$ //$NON-NLS-2$
              + sequenceMessage.getKind().getName()));
      cc.append(new SetCommand(getEditingDomain(), sequenceMessage.getReceivingEnd(), ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(),
          "rcvMsgEnd_" + operation.getName() + "_" //$NON-NLS-1$ //$NON-NLS-2$
              + sequenceMessage.getKind().getName()));

      return new ICommandProxy(new GMFCommandWrapper(getEditingDomain(), cc));
    }
    return UnexecutableCommand.INSTANCE;
  }

  private SequenceMessage findReply(SequenceMessage sequenceMessage) {
    final ECrossReferenceAdapter crossReferenceAdapter = ECrossReferenceAdapter.getCrossReferenceAdapter(sequenceMessage.getReceivingEnd());
    for (Setting setting : crossReferenceAdapter.getInverseReferences(sequenceMessage.getReceivingEnd())) {
      if ((setting.getEObject() instanceof Execution) && (setting.getEStructuralFeature() == InteractionPackage.eINSTANCE.getTimeLapse_Start())) {
        return ((MessageEnd) ((Execution) setting.getEObject()).getFinish()).getMessage();
      }
    }
    return null;
  }

  public AbstractEventOperation chooseService() {
    final ModellingArchitecture architecture = this.findArchitecture(getFirstDecorateSemanticElement().getTarget());

    final TreeItemWrapper root = new TreeItemWrapper(null, null);

    final List<Interface> interfaces = getInterfaces(architecture);

    for (final Interface itf : interfaces) {
      final TreeItemWrapper interfaceWrapper = new TreeItemWrapper(itf, root);
      root.getChildren().add(interfaceWrapper);

      for (final ExchangeItem operation : ExchangeItemExt.getOperations(itf.getExchangeItems())) {
        TreeItemWrapper wrapper = new TreeItemWrapper(operation, interfaceWrapper);
        interfaceWrapper.getChildren().add(wrapper);
      }
    }

    final EObjectSelectionWizard wizard =
        new EObjectSelectionWizard("Select Service", "Select the service to invoke", null, root, SiriusEditPlugin.getPlugin().getItemProvidersAdapterFactory());
    wizard.setMany(false);
    final Shell shell = new Shell();
    final WizardDialog dlg = new WizardDialog(shell, wizard);
    final int result = dlg.open();
    shell.dispose();
    if (result == Window.OK) {
      final EObject selectedElement = wizard.getSelectedEObject();
      if (selectedElement instanceof AbstractEventOperation) {
        return (AbstractEventOperation) selectedElement;
      }
    }
    return null;
  }

  public AbstractEventOperation chooseSignal() {
    final ModellingArchitecture architecture = this.findArchitecture(getFirstDecorateSemanticElement().getTarget());

    final TreeItemWrapper root = new TreeItemWrapper(null, null);

    final List<Interface> interfaces = getInterfaces(architecture);

    for (final Interface itf : interfaces) {
      final TreeItemWrapper interfaceWrapper = new TreeItemWrapper(itf, root);
      root.getChildren().add(interfaceWrapper);

      for (final ExchangeItem operation : ExchangeItemExt.getEvents(itf.getExchangeItems())) {
        TreeItemWrapper wrapper = new TreeItemWrapper(operation, interfaceWrapper);
        interfaceWrapper.getChildren().add(wrapper);
      }

    }

    final EObjectSelectionWizard wizard =
        new EObjectSelectionWizard("Select Signal", "Select the signal to send", null, root, DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory());
    wizard.setMany(false);
    final Shell shell = new Shell();
    final WizardDialog dlg = new WizardDialog(shell, wizard);
    final int result = dlg.open();
    shell.dispose();
    if (result == Window.OK) {
      final EObject selectedElement = wizard.getSelectedEObject();

      if (selectedElement instanceof AbstractEventOperation) {
        return (AbstractEventOperation) selectedElement;
      }
    }
    return null;
  }

  private List<Interface> getInterfaces(ModellingArchitecture architecture) {
    if (architecture instanceof SystemAnalysis) {
      return getInterfaces((SystemAnalysis) architecture);
    } else if (architecture instanceof LogicalArchitecture) {
      return getInterfaces((LogicalArchitecture) architecture);
    } else if (architecture instanceof PhysicalArchitecture) {
      return getInterfaces((PhysicalArchitecture) architecture);
    }
    return Collections.<Interface> emptyList();
  }

  private List<Interface> getInterfaces(SystemAnalysis architecture) {
    return (architecture != null) && (architecture.getOwnedInterfacePkg() != null) ? architecture.getOwnedInterfacePkg().getOwnedInterfaces() : Collections
        .<Interface> emptyList();
  }

  private List<Interface> getInterfaces(LogicalArchitecture architecture) {
    final Set<Interface> result = new LinkedHashSet<Interface>();
    for (final SystemAnalysis ctxArchitecture : filter(((SystemEngineering) architecture.eContainer()).getOwnedArchitectures(), SystemAnalysis.class)) {
      result.addAll(getInterfaces(ctxArchitecture));
    }
    result.addAll((architecture != null) && (architecture.getOwnedInterfacePkg() != null) ? architecture.getOwnedInterfacePkg().getOwnedInterfaces()
                                                                                         : Collections.<Interface> emptyList());
    return new ArrayList<Interface>(result);
  }

  private List<Interface> getInterfaces(PhysicalArchitecture architecture) {
    final Set<Interface> result = new LinkedHashSet<Interface>();
    for (final LogicalArchitecture logArchitecture : filter(((SystemEngineering) architecture.eContainer()).getOwnedArchitectures(), LogicalArchitecture.class)) {
      result.addAll(getInterfaces(logArchitecture));
    }
    result.addAll((architecture != null) && (architecture.getOwnedInterfacePkg() != null) ? architecture.getOwnedInterfacePkg().getOwnedInterfaces()
                                                                                         : Collections.<Interface> emptyList());
    return new ArrayList<Interface>(result);
  }

  /**
   * Returns the modeling architecture that owns the given element.
   * @param ownedElement an element.
   * @return the modeling architecture that owns the given element.
   */
  private ModellingArchitecture findArchitecture(final EObject ownedElement) {
    ModellingArchitecture result = null;
    EObject current = ownedElement;
    while ((result == null) && (current != null)) {
      if (CapellacorePackage.eINSTANCE.getModellingArchitecture().isInstance(current)) {
        result = (ModellingArchitecture) current;
      }
      current = current.eContainer();
    }
    return result;
  }

  private static <T> Collection<T> filter(final Collection<?> collection, final Class<T> type) {
    final ArrayList<T> result = new ArrayList<T>(collection.size());

    for (final Object o : collection) {
      if (type.isInstance(o)) {
        result.add(type.cast(o));
      }
    }
    result.trimToSize();

    return result;
  }

  private EventSentOperation getSentEvent(final MessageEnd messageEnd, final org.eclipse.emf.common.command.CompoundCommand cc) {
    EventSentOperation result;
    if (messageEnd.getEvent() instanceof EventSentOperation) {
      result = (EventSentOperation) messageEnd.getEvent();
    } else {
      result = InteractionFactory.eINSTANCE.createEventSentOperation();
      Scenario scenario = (Scenario) messageEnd.eContainer();
      cc.append(new AddCommand(getEditingDomain(), scenario.getOwnedEvents(), result));
      cc.append(new SetCommand(getEditingDomain(), messageEnd, InteractionPackage.eINSTANCE.getAbstractEnd_Event(), result));
    }
    return result;
  }

  private EventReceiptOperation getReceiptEvent(final MessageEnd messageEnd, final org.eclipse.emf.common.command.CompoundCommand cc) {
    EventReceiptOperation result;
    if (messageEnd.getEvent() instanceof EventReceiptOperation) {
      result = (EventReceiptOperation) messageEnd.getEvent();
    } else {
      result = InteractionFactory.eINSTANCE.createEventReceiptOperation();
      Scenario scenario = (Scenario) messageEnd.eContainer();
      cc.append(new AddCommand(getEditingDomain(), scenario.getOwnedEvents(), result));
      cc.append(new SetCommand(getEditingDomain(), messageEnd, InteractionPackage.eINSTANCE.getAbstractEnd_Event(), result));
    }
    return result;
  }

}
