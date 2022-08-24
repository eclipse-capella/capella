package org.polarsys.capella.core.sirius.analysis.policies;

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenDiagramEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.model.business.internal.spec.DNodeSpec;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.ui.tools.internal.editor.NavigateToCommand;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.ui.toolkit.dialogs.OpenRepresentationDialog;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectNewRepresentationDialog;
import org.polarsys.capella.core.commands.preferences.ui.sirius.DoubleClickBehaviourUtil;

/**
 * This Edit policy is designed to open related diagrams on double click
 * 
 * @author etraisnel
 */
public class OpenRelatedDiagramEditPolicy extends OpenDiagramEditPolicy {
	@SuppressWarnings("restriction")
	@Override
	protected Command getOpenCommand(Request request) {
		if (RequestConstants.REQ_OPEN.equals(request.getType())) {

			EditPart targetEditPart = getTargetEditPart(request);
			if (targetEditPart instanceof IGraphicalEditPart) {
				IGraphicalEditPart editPart = (IGraphicalEditPart)targetEditPart;
				View view = editPart.getNotationView();			
				if (view !=null){
					EObject element = ViewUtil.resolveSemanticElement(view);
					EObject targetSemanticElement = null;
					if (element instanceof DNodeContainer) {
						DNodeContainer container = (DNodeContainer) element;				
						targetSemanticElement = container.getTarget();
					} else if (element instanceof DNodeSpec) {
						DNodeSpec container = (DNodeSpec) element;
						targetSemanticElement = container.getTarget();
					}				
					if (!((DDiagramElement) element).getParentDiagram().isIsInShowingMode()) {
						if (DoubleClickBehaviourUtil.INSTANCE.shouldOpenRelatedDiagramsOnDoubleClick(targetSemanticElement)) {
							if (targetSemanticElement != null) {					
								Session session = SessionManager.INSTANCE.getSession(element);
								Collection<DRepresentationDescriptor>  representations = DoubleClickBehaviourUtil.INSTANCE.getRepresentationsDescriptors(targetSemanticElement);
								if (!representations.isEmpty()) {
									if (representations.size() > 1 ) {
										Shell activeShell = Display.getDefault().getActiveShell();
										OpenRepresentationDialog dialog = new OpenRepresentationDialog(activeShell, representations) ;					
										dialog.open();
										if (dialog.getReturnCode() == Window.OK) {
											return new ICommandProxy(
													new GMFCommandWrapper(session.getTransactionalEditingDomain(), new NavigateToCommand(session, dialog.getSelectedDescriptor().getRepresentation())));
										}							
									} else {
										DRepresentationDescriptor element1 = (DRepresentationDescriptor) representations.toArray()[0];
										return new ICommandProxy(
												new GMFCommandWrapper(session.getTransactionalEditingDomain(), new NavigateToCommand(session, element1.getRepresentation())));
									}
								} else {
									Session currentSession = SessionManager.INSTANCE.getSession(targetSemanticElement);
									Collection<Viewpoint> selectedViewpoints = currentSession.getSelectedViewpoints(false);
									Collection<RepresentationDescription> descriptions = DialectManager.INSTANCE.getAvailableRepresentationDescriptions(selectedViewpoints, targetSemanticElement);
									if (!descriptions.isEmpty()) {
										if (descriptions.size() > 1) {	
											Shell activeShell = Display.getDefault().getActiveShell();
											SelectNewRepresentationDialog dialog = new SelectNewRepresentationDialog(activeShell, "No existing representation found, would you like to create one ?\n", targetSemanticElement, descriptions);	
											dialog.open();
											if (dialog.getReturnCode() == Window.OK) {
												CreateRepresentationCommand createRepresentationCommand = new CreateRepresentationCommand(session, dialog.getSelectedRepresentationDescription(), targetSemanticElement,dialog.getName(), new NullProgressMonitor()) {
													@Override
													protected void doExecute() {
														super.doExecute();
														NavigateToCommand navCommand = new NavigateToCommand(session,getCreatedRepresentation());
														navCommand.execute();
													}
												};
												return new ICommandProxy(
														new GMFCommandWrapper(session.getTransactionalEditingDomain(), createRepresentationCommand));

											}
										} else {
											Shell activeShell = Display.getDefault().getActiveShell();
											RepresentationDescription description = descriptions.iterator().next();
											IInterpreter interpreter = InterpreterUtil.getInterpreter(targetSemanticElement);
											String titleExpression = description.getTitleExpression();
											String newName ="";
											try {
												newName = interpreter.evaluateString(targetSemanticElement, titleExpression);
											} catch (EvaluationException e) {
												e.printStackTrace();
											}

											IInputValidator validator = new IInputValidator() {

												@Override
												public String isValid(String newText) {
													if(newText.isBlank()) {
														return "Representation name cannot be blank";
													} else {
														return null;
													}
												}
											};

											InputDialog dialog = new InputDialog(activeShell,"New " +  MessageTranslator.INSTANCE.getMessage(description, new IdentifiedElementQuery(description).getLabel()),"No existing representation found, would you like to create one ?\n\nName:", newName, validator);
											dialog.open();
											if (dialog.getReturnCode() == Window.OK) {								

												CreateRepresentationCommand createRepresentationCommand = new CreateRepresentationCommand(session, description, targetSemanticElement,dialog.getValue(), new NullProgressMonitor()) {
													@Override
													protected void doExecute() {
														super.doExecute();
														NavigateToCommand navCommand = new NavigateToCommand(session,getCreatedRepresentation());
														navCommand.execute();
													}
												};

												return new ICommandProxy(
														new GMFCommandWrapper(session.getTransactionalEditingDomain(),createRepresentationCommand));

											}

										}

									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

}
