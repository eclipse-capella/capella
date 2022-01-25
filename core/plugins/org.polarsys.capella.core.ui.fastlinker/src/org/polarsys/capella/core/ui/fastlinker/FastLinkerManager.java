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
package org.polarsys.capella.core.ui.fastlinker;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerView;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewPlugin;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.links.helpers.LinksCommandRegistry;
import org.polarsys.capella.core.model.links.helpers.commands.AbstractCreateLinksCommand;
import org.polarsys.capella.core.ui.fastlinker.view.FastLinkerView;

/**
 * FastLinker main class.
 */
public class FastLinkerManager {
	/**
	 * Object managing the FastLinker state.
	 */
	protected final FastLinkerState currentState;
	/**
	 * Available commands to create links between first and second elements.
	 */
	protected final List<AbstractCreateLinksCommand> firstToSecondCommands;
	/**
	 * Available commands to create links between second and first elements.
	 */
	protected final List<AbstractCreateLinksCommand> secondToFirstCommands;

	/**
	 * Constructor.
	 */
	public FastLinkerManager() {
		currentState = new FastLinkerState();
		firstToSecondCommands = new ArrayList<AbstractCreateLinksCommand>();
		secondToFirstCommands = new ArrayList<AbstractCreateLinksCommand>();
	}

	/**
	 * If the dragged/selected element is not the element to put in the
	 * FastLinker, this method get the element to put from the originally
	 * dragged/selected element.
	 * 
	 * @param originalElement
	 * @return
	 */
	protected Collection getElementToPut(Collection originalElement) {
		// If the original element is of type Part so FastLinker has to work
		// with the Component of this part.
		Collection ret = new ArrayList();
		for (Object elem : originalElement) {
			if (CsPackage.Literals.PART.isInstance(originalElement)) {
				ret.add(((Part) elem).getAbstractType());
			} else
				ret.add(elem);
		}
		return ret;
	}
	/**
	 * Given a set of elements, find their lowest common meta-class
	 * 
	 * @param elements
	 *            a non-null collection of model elements
	 * @return a meta-class which is not null if elements is not empty
	 */
	public static EClass getCommonType(Collection<? extends EObject> elements) {
		EClass result = null;
		if (!elements.isEmpty()) {
			List<EClass> common = new ArrayList<EClass>(
					getSuperTypes(elements.iterator().next().eClass()));
			for (EObject elt : elements) {
				common.retainAll(getSuperTypes(elt.eClass()));
			}
			if (!common.isEmpty()) {
				result = common.get(common.size() - 1);
			}
		}
		return result;
	}
	/**
	 * Return the super types of the given meta-class including the class
	 * itself, ordered from higher to lower in the hierarchy
	 * 
	 * @param cls
	 *            a non-null meta-class
	 * @return a non-null, non-empty, unmodifiable list
	 */
	private static List<EClass> getSuperTypes(EClass cls) {
		List<EClass> allButSelf = cls.getEAllSuperTypes();
		List<EClass> result = new ArrayList<EClass>(allButSelf.size() + 1);
		result.addAll(allButSelf);
		result.add(cls);
		return Collections.unmodifiableList(result);
	}
	/**
	 * This method has to be called to know if the FastLinker is able to "work"
	 * with the given element (regarding its state and available commands to
	 * create links).<br>
	 * <b>It is mandatory to call this method
	 * {@link FastLinkerManager#putElementInFastLinker(EObject)} since it
	 * initializes commands lists.</b>
	 * 
	 * @param elementToPut
	 * @return
	 */
	public boolean acceptElementInFastLinker(Collection elementToPut) {
		// Precondition : eliminate null and non ModelElement.
		if (!putInFastLinkerPreconditionsRespected(elementToPut)) {
			return false;
		}

		Collection modelElementToPut = getElementToPut(elementToPut);
		// Compute the future state of the FastLinker.
		FastLinkerState futureState = currentState
				.getPreviewState(modelElementToPut);
		if (null == futureState) {
			return false;
		}
		// Clean command lists.
		firstToSecondCommands.clear();
		secondToFirstCommands.clear();
		
		// Check if element will be the first one in FastLinker.
		if ((null != futureState.firstElement)
				&& (null == futureState.secondElement)
				&& (!futureState.firstElement.isEmpty())
				) {
			EClass commonSuperType = getCommonType(elementToPut);
			// Is there commands using this element as source or target ?
			LinksCommandRegistry linksCommandRegistryInstance = LinksCommandRegistry
					.getInstance();
			if (linksCommandRegistryInstance
					.containsCommandForSourceType(commonSuperType)
					|| linksCommandRegistryInstance
							.containsCommandForTargetType(commonSuperType)) {
				return true;
			}
		}
		// Element will not be the first one -> check commands are available
		// between first and second elements (as source or target).
		else {
			if (!futureState.firstElement.isEmpty() && !futureState.secondElement.isEmpty()) {
				EClass firstSuperType = getCommonType(futureState.firstElement);
				EClass secondSuperType = getCommonType(futureState.secondElement);
			if (  LinksCommandRegistry.getInstance()
				.containsCommandForSourceTargetTypes(
						firstSuperType,
						secondSuperType)
				|| LinksCommandRegistry.getInstance()
						.containsCommandForSourceTargetTypes(
								secondSuperType,
										firstSuperType)) {
			// Some commands are available -> get them.
			firstToSecondCommands.addAll(LinksCommandRegistry.getInstance()
					.getExecutableCommands(futureState.firstElement,
							futureState.secondElement));
			secondToFirstCommands.addAll(LinksCommandRegistry.getInstance()
					.getExecutableCommands(futureState.secondElement,
							futureState.firstElement));
			if (!firstToSecondCommands.isEmpty()
					|| !secondToFirstCommands.isEmpty()) {
				return true;
			}
		}
			}
		}
		// No command(s) available for the lone element or the two elements ->
		// element is not accepted.
		return false;
	}

	private boolean putInFastLinkerPreconditionsRespected(
			Collection elementToPut) {
		if ((null == elementToPut || elementToPut.isEmpty())) {
			return false;
		}
		for (Object it : elementToPut) {
			if (!(it instanceof ModelElement))
				return false;
		}
		return getCommonType(elementToPut)!= null;
		
	}

	/**
	 * Pin or unpin a ModelElement present in the FastLinker.<br>
	 * The element will be unpinned if it is already pinned.
	 * 
	 * @param elementToPin
	 */
	public void pinModelElement(Collection elementToPin) {
		currentState.pinModelElement(elementToPin);
		showFastLinkerView().update();
	}

	/**
	 * Clean FastLinker.
	 */
	public void clearFastLinker() {
		currentState.clear();
		showFastLinkerView().update();
	}

	/**
	 * Get current state.
	 * 
	 * @return
	 */
	public FastLinkerState getCurrentState() {
		return currentState;
	}

	/**
	 * Get an unmodifiable view of available firstToSecondCommands.
	 * 
	 * @return
	 */
	public List<AbstractCreateLinksCommand> getFirstToSecondCommands() {
		return Collections.unmodifiableList(firstToSecondCommands);
	}

	/**
	 * Get an unmodifiable view of available secondToFirstCommands.
	 * 
	 * @return
	 */
	public List<AbstractCreateLinksCommand> getSecondToFirstCommands() {
		return Collections.unmodifiableList(secondToFirstCommands);
	}

	/**
	 * Show the FastLinker view and return it.
	 * 
	 * @return
	 */
	public FastLinkerView showFastLinkerView() {
		try {
			// Show view (if not already shown).
			// The view is not given focus.
			return (FastLinkerView) PlatformUI
					.getWorkbench()
					.getActiveWorkbenchWindow()
					.getActivePage()
					.showView(FastLinkerView.VIEW_ID, null,
							IWorkbenchPage.VIEW_VISIBLE);
		} catch (PartInitException exception) {
			// An error occurred -> log it.
		  Platform.getLog(FastLinkerActivator.class).log(new Status(IStatus.ERROR,
							FastLinkerActivator.PLUGIN_ID, exception
									.getLocalizedMessage(), exception));
			return null;
		}
	}

	/**
	 * Put an element in the FastLinker.<br>
	 * <b>It is mandatory to call
	 * {@link FastLinkerManager#acceptElementInFastLinker(EObject)} and to get a
	 * <code>true</code> result before calling this method.</b>
	 * 
	 * @param elementToPut
	 */
	public void putElementInFastLinker(Collection elementToPut) {
		putElementInFastLinker(elementToPut, null);
	}

	/**
	 * Put an element in the FastLinker.<br>
	 * <b>It is mandatory to call
	 * {@link FastLinkerManager#acceptElementInFastLinker(EObject)} and to get a
	 * <code>true</code> result before calling this method.</b><br>
	 * <b>This method is available for test purposes</b>, it allows to specify a
	 * command to execute and so to avoid asking the user to choose the command
	 * to execute. In normal usage,
	 * {@link FastLinkerManager#putElementInFastLinker(EObject)} must be called.
	 * 
	 * @param elementToPut
	 * @param cmd
	 *            the command to execute, must be amongst executable commands
	 *            (use {@link FastLinkerManager#getFirstToSecondCommands()} or
	 *            {@link FastLinkerManager#getSecondToFirstCommands()}).
	 */
	public void putElementInFastLinker(final Collection elementToPut,
			AbstractCreateLinksCommand cmd) {
		// Preconditions.
		// Eliminate null and non ModelElement.
		if (!putInFastLinkerPreconditionsRespected(elementToPut)) {
			return;
		}
		// If a command to execute is specified, it must be amongst executable
		// commands.
		if ((null != cmd)
				&& (!firstToSecondCommands.contains(cmd) || !secondToFirstCommands
						.contains(cmd))) {
			throw new IllegalArgumentException(
					"Command to execute must be amongst executable command(s)."); //$NON-NLS-1$
		}

		Collection modelElementToPut = getElementToPut(elementToPut);
		// Update FastLinker state.
		currentState.updateState(modelElementToPut);
		// Get command to execute.
		AbstractCreateLinksCommand commandToExecute;
		if ((0 == firstToSecondCommands.size())
				&& (0 == secondToFirstCommands.size())) {
			// No command to execute (there is probably only one element in the
			// FastLinker, else there is a problem...).
			commandToExecute = null;
		} else if ((1 == firstToSecondCommands.size())
				&& (0 == secondToFirstCommands.size())) {
			// Only one command in the firstToSecond list -> execute it.
			commandToExecute = firstToSecondCommands.get(0);
		} else if ((0 == firstToSecondCommands.size())
				&& (1 == secondToFirstCommands.size())) {
			// Only one command in the secondToFirst list -> execute it.
			commandToExecute = secondToFirstCommands.get(0);
		} else {
			// Several commands.
			if (null != cmd) {
				// If a command to execute is specified -> execute it.
				commandToExecute = cmd;
			} else {
				// No command specified -> ask user to choose one.
				FastLinkerView fastLinkerView = showFastLinkerView();
				// Display both elements (without link, since it is not already
				// created).
				fastLinkerView.update();
				// Ask user to choose between available commands.
				commandToExecute = fastLinkerView.chooseCommandToExecute(
						firstToSecondCommands, secondToFirstCommands);
			}
		}
		if (null != commandToExecute) {
			Collection<? extends ModelElement> sources = commandToExecute.getSources();
			Collection<? extends ModelElement> targets = commandToExecute.getTargets();
			for (ModelElement src : sources) {
				for (ModelElement tgt : targets) {
				commandToExecute.setSources(Collections.singleton(src));
				commandToExecute.setTargets(Collections.singleton(tgt));
				// Execute command (if any).
				LinksCommandRegistry.getInstance().executeCommand(src, commandToExecute);
				// Display a trace in the Information view.
				showCommandExecutedMessage(commandToExecute);
				
				}
			}
			
			// Update state and view with the created link.
			currentState.setLinkCreated(commandToExecute
					.getLinkRepresentation());
		}
		showFastLinkerView().update();
	}

	/**
	 * Ask this method to display the textual result of an executed command in
	 * the Information view.
	 * 
	 * @param cmd
	 */
	public void showCommandExecutedMessage(
			final AbstractCreateLinksCommand cmd) {
		EObject createdLinkObject = cmd.getCreatedLinkObject();
		final String informationMessage;
		final EObject affectedObject;
		String sourceName = cmd.getSource().getLabel();
		String targetName = cmd.getTarget().getLabel();
		
		if (null != createdLinkObject) {
			informationMessage = MessageFormat.format(
					Messages.FastLinkerManager_QualifiedLinkCommandReport,
					createdLinkObject.eClass().getName(), sourceName,
					targetName);
			affectedObject = createdLinkObject;
		} else {
			informationMessage = MessageFormat.format(
					Messages.FastLinkerManager_UnQualifiedLinkCommandReport,
					cmd.getLabel(), sourceName, targetName);
			affectedObject = cmd.getSource();
		}
		LightMarkerRegistry.getInstance().createMarker(
				ResourcesPlugin.getWorkspace().getRoot(), new BasicDiagnostic(Messages.FastLinker, 0, informationMessage, new Object[] { affectedObject }));
		try {
			// Show the Information view (if not already shown).
			PlatformUI
					.getWorkbench()
					.getActiveWorkbenchWindow()
					.getActivePage()
					.showView(MarkerView.VIEW_ID, null,
							IWorkbenchPage.VIEW_VISIBLE);
		} catch (PartInitException exception) {
			// An error occurred -> log it.
		  Platform.getLog(MarkerViewPlugin.class).log(new Status(IStatus.ERROR, MarkerViewPlugin.PLUGIN_ID,
							exception.getLocalizedMessage(), exception));
		}
	}
	
	public void updateCurrentState(Collection first, Collection second , Collection pinned ){
		currentState.updateState(first , second , pinned);
		showFastLinkerView().update();
	}
}
