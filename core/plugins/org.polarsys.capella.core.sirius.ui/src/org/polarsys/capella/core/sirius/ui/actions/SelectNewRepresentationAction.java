package org.polarsys.capella.core.sirius.ui.actions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription;
import org.eclipse.sirius.table.metamodel.table.provider.TableUIPlugin;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectNewRepresentationDialog;
import org.polarsys.capella.core.sirius.analysis.NewRepresentationCommand;

/**
 * The action allowing to create new representations.
 */
public class SelectNewRepresentationAction extends BaseSelectionListenerAction {
	private EObject selectedEObject;
	protected Set<RepresentationDescription> descriptions;
	protected Session session;

	protected boolean openRepresentation;
	private boolean isCanceled;	
	private String message = "Select a representation to create :";

	public SelectNewRepresentationAction(Collection<RepresentationDescription> descriptions, EObject selectedEObject, Session session, String message) {
		this(descriptions, selectedEObject, session, true);
		this.message = message +"\n" + this.message;
	}

	public SelectNewRepresentationAction(Collection<RepresentationDescription> descriptions, EObject selectedEObject, Session session) {
		this(descriptions, selectedEObject, session, true);
	}

	public SelectNewRepresentationAction(Collection<RepresentationDescription> descriptions, EObject selectedEObject, Session session,
			boolean openRepresentation) {
		super("Select a representation to create");

		this.selectedEObject = selectedEObject;
		this.descriptions = new HashSet<RepresentationDescription>(descriptions);
		this.session = session;
		this.openRepresentation = openRepresentation;

		RepresentationDescription firstDescription = descriptions.iterator().next();

		ImageDescriptor imageDescriptor = getDescriptionImageDescriptor(firstDescription);
		setImageDescriptor(imageDescriptor);
	}

	protected String getDescriptionLabel(RepresentationDescription description) {
		return MessageTranslator.INSTANCE.getMessage(description, new IdentifiedElementQuery(description).getLabel());
	}

	protected ImageDescriptor getDescriptionImageDescriptor(RepresentationDescription description) {
		ImageDescriptor imageDescriptor = null;

		// Handle specific representations : Table ones.
		if (description instanceof CrossTableDescription) {
			imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(TableUIPlugin.ID,
					"/icons/full/obj16/CrossTableDescription.gif"); //$NON-NLS-1$

		} else if (description instanceof EditionTableDescription) {
			imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(TableUIPlugin.ID, "/icons/full/obj16/DTable.gif"); //$NON-NLS-1$

		} else if (description instanceof SequenceDiagramDescription) {
			imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.sirius.diagram.sequence.edit", //$NON-NLS-1$
					"/icons/full/obj16/TSequenceDiagram.gif"); //$NON-NLS-1$

		} else {
			// Standard diagram.
			imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(DiagramUIPlugin.ID,
					"/icons/full/obj16/DDiagram.gif"); //$NON-NLS-1$
		}

		if (null == imageDescriptor) {
			imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
		}
		return imageDescriptor;
	}

	@Override
	public void run() {
		//RepresentationDescription firstDescription = descriptions.iterator().next();
		Shell activeShell = Display.getDefault().getActiveShell();      

		SelectNewRepresentationDialog dialog = new SelectNewRepresentationDialog(activeShell, message, selectedEObject, descriptions);	
		isCanceled = Window.CANCEL == dialog.open();

		if (!isCanceled) {
			// defaultName = dialog.getValue();
		} else {
			return;
		}

		String name = dialog.getName();
		RepresentationDescription selectedDescription = dialog.getSelectedRepresentationDescription();
		// Do not call ToggleCanonicalRefresh anymore since Sirius 4.18.
		// Executes the NewRepresentationCommand.
		NewRepresentationCommand command = new NewRepresentationCommand(name, selectedEObject, selectedDescription, session);
		TransactionHelper.getExecutionManager(session).execute(command);

		if (null != command.getRepresentation()) {
			SessionManager.INSTANCE.notifyRepresentationCreated(session);
			if (openRepresentation) {
				DialectUIManager.INSTANCE.openEditor(session, command.getRepresentation(), new NullProgressMonitor());
			}
		}
	}


	public boolean isCanceled() {
		return isCanceled;
	}

	public void setCanceled(boolean isCanceled) {
		this.isCanceled = isCanceled;
	}



}
