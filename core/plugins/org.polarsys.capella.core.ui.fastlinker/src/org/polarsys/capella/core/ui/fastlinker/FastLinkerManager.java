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
package org.polarsys.capella.core.ui.fastlinker;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
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
	protected final FastLinkerState _currentState;
	/**
	 * Available commands to create links between first and second elements.
	 */
	protected final List<AbstractCreateLinksCommand> _firstToSecondCommands;
	/**
	 * Available commands to create links between second and first elements.
	 */
	protected final List<AbstractCreateLinksCommand> _secondToFirstCommands;

	/**
	 * Constructor.
	 */
	public FastLinkerManager() {
		_currentState = new FastLinkerState();
		_firstToSecondCommands = new ArrayList<AbstractCreateLinksCommand>();
		_secondToFirstCommands = new ArrayList<AbstractCreateLinksCommand>();
	}

	/**
	 * If the dragged/selected element is not the element to put in the
	 * FastLinker, this method get the element to put from the originally
	 * dragged/selected element.
	 * 
	 * @param originalElement_p
	 * @return
	 */
	protected Collection getElementToPut(Collection originalElement_p) {
		// If the original element is of type Part so FastLinker has to work
		// with the Component of this part.
		Collection ret = new ArrayList();
		for (Object elem : originalElement_p) {
			if (CsPackage.Literals.PART.isInstance(originalElement_p)) {
				ret.add(((Part) elem).getAbstractType());
			} else
				ret.add(elem);
		}
		return ret;
	}
	/**
	 * Given a set of elements, find their lowest common meta-class
	 * 
	 * @param elements_p
	 *            a non-null collection of model elements
	 * @return a meta-class which is not null if elements_p is not empty
	 */
	public static EClass getCommonType(Collection<? extends EObject> elements_p) {
		EClass result = null;
		if (!elements_p.isEmpty()) {
			List<EClass> common = new ArrayList<EClass>(
					getSuperTypes(elements_p.iterator().next().eClass()));
			for (EObject elt : elements_p) {
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
	 * @param class_p
	 *            a non-null meta-class
	 * @return a non-null, non-empty, unmodifiable list
	 */
	private static List<EClass> getSuperTypes(EClass class_p) {
		List<EClass> allButSelf = class_p.getEAllSuperTypes();
		List<EClass> result = new ArrayList<EClass>(allButSelf.size() + 1);
		result.addAll(allButSelf);
		result.add(class_p);
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
	 * @param elementToPut_p
	 * @return
	 */
	public boolean acceptElementInFastLinker(Collection elementToPut_p) {
		// Precondition : eliminate null and non ModelElement.
		if (!putInFastLinkerPreconditionsRespected(elementToPut_p)) {
			return false;
		}

		Collection modelElementToPut = getElementToPut(elementToPut_p);
		// Compute the future state of the FastLinker.
		FastLinkerState futureState = _currentState
				.getPreviewState(modelElementToPut);
		if (null == futureState) {
			return false;
		}
		// Clean command lists.
		_firstToSecondCommands.clear();
		_secondToFirstCommands.clear();
		
		// Check if element will be the first one in FastLinker.
		if ((null != futureState._firstElement)
				&& (null == futureState._secondElement)
				&& (!futureState._firstElement.isEmpty())
				) {
			EClass commonSuperType = getCommonType(elementToPut_p);
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
			if (!futureState._firstElement.isEmpty() && !futureState._secondElement.isEmpty()) {
				EClass firstSuperType = getCommonType(futureState._firstElement);
				EClass secondSuperType = getCommonType(futureState._secondElement);
			if (  LinksCommandRegistry.getInstance()
				.containsCommandForSourceTargetTypes(
						firstSuperType,
						secondSuperType)
				|| LinksCommandRegistry.getInstance()
						.containsCommandForSourceTargetTypes(
								secondSuperType,
										firstSuperType)) {
			// Some commands are available -> get them.
			_firstToSecondCommands.addAll(LinksCommandRegistry.getInstance()
					.getExecutableCommands(futureState._firstElement,
							futureState._secondElement));
			_secondToFirstCommands.addAll(LinksCommandRegistry.getInstance()
					.getExecutableCommands(futureState._secondElement,
							futureState._firstElement));
			if (!_firstToSecondCommands.isEmpty()
					|| !_secondToFirstCommands.isEmpty()) {
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
			Collection elementToPut_p) {
		if ((null == elementToPut_p || elementToPut_p.isEmpty())) {
			return false;
		}
		for (Object it : elementToPut_p) {
			if (!(it instanceof ModelElement))
				return false;
		}
		return getCommonType(elementToPut_p)!= null;
		
	}

	/**
	 * Pin or unpin a ModelElement present in the FastLinker.<br>
	 * The element will be unpinned if it is already pinned.
	 * 
	 * @param elementToPin_p
	 */
	public void pinModelElement(Collection elementToPin_p) {
		_currentState.pinModelElement(elementToPin_p);
		showFastLinkerView().update();
	}

	/**
	 * Clean FastLinker.
	 */
	public void clearFastLinker() {
		_currentState.clear();
		showFastLinkerView().update();
	}

	/**
	 * Get current state.
	 * 
	 * @return
	 */
	public FastLinkerState getCurrentState() {
		return _currentState;
	}

	/**
	 * Get an unmodifiable view of available firstToSecondCommands.
	 * 
	 * @return
	 */
	public List<AbstractCreateLinksCommand> getFirstToSecondCommands() {
		return Collections.unmodifiableList(_firstToSecondCommands);
	}

	/**
	 * Get an unmodifiable view of available secondToFirstCommands.
	 * 
	 * @return
	 */
	public List<AbstractCreateLinksCommand> getSecondToFirstCommands() {
		return Collections.unmodifiableList(_secondToFirstCommands);
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
		} catch (PartInitException exception_p) {
			// An error occurred -> log it.
			FastLinkerActivator
					.getDefault()
					.getLog()
					.log(new Status(IStatus.ERROR,
							FastLinkerActivator.PLUGIN_ID, exception_p
									.getLocalizedMessage(), exception_p));
			return null;
		}
	}

	/**
	 * Put an element in the FastLinker.<br>
	 * <b>It is mandatory to call
	 * {@link FastLinkerManager#acceptElementInFastLinker(EObject)} and to get a
	 * <code>true</code> result before calling this method.</b>
	 * 
	 * @param elementToPut_p
	 */
	public void putElementInFastLinker(Collection elementToPut_p) {
		putElementInFastLinker(elementToPut_p, null);
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
	 * @param elementToPut_p
	 * @param commandToExecute_p
	 *            the command to execute, must be amongst executable commands
	 *            (use {@link FastLinkerManager#getFirstToSecondCommands()} or
	 *            {@link FastLinkerManager#getSecondToFirstCommands()}).
	 */
	public void putElementInFastLinker(final Collection elementToPut_p,
			AbstractCreateLinksCommand commandToExecute_p) {
		// Preconditions.
		// Eliminate null and non ModelElement.
		if (!putInFastLinkerPreconditionsRespected(elementToPut_p)) {
			return;
		}
		// If a command to execute is specified, it must be amongst executable
		// commands.
		if ((null != commandToExecute_p)
				&& (!_firstToSecondCommands.contains(commandToExecute_p) || !_secondToFirstCommands
						.contains(commandToExecute_p))) {
			throw new IllegalArgumentException(
					"Command to execute must be amongst executable command(s)."); //$NON-NLS-1$
		}

		Collection modelElementToPut = getElementToPut(elementToPut_p);
		// Update FastLinker state.
		_currentState.updateState(modelElementToPut);
		// Get command to execute.
		AbstractCreateLinksCommand commandToExecute;
		if ((0 == _firstToSecondCommands.size())
				&& (0 == _secondToFirstCommands.size())) {
			// No command to execute (there is probably only one element in the
			// FastLinker, else there is a problem...).
			commandToExecute = null;
		} else if ((1 == _firstToSecondCommands.size())
				&& (0 == _secondToFirstCommands.size())) {
			// Only one command in the firstToSecond list -> execute it.
			commandToExecute = _firstToSecondCommands.get(0);
		} else if ((0 == _firstToSecondCommands.size())
				&& (1 == _secondToFirstCommands.size())) {
			// Only one command in the secondToFirst list -> execute it.
			commandToExecute = _secondToFirstCommands.get(0);
		} else {
			// Several commands.
			if (null != commandToExecute_p) {
				// If a command to execute is specified -> execute it.
				commandToExecute = commandToExecute_p;
			} else {
				// No command specified -> ask user to choose one.
				FastLinkerView fastLinkerView = showFastLinkerView();
				// Display both elements (without link, since it is not already
				// created).
				fastLinkerView.update();
				// Ask user to choose between available commands.
				commandToExecute = fastLinkerView.chooseCommandToExecute(
						_firstToSecondCommands, _secondToFirstCommands);
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
			_currentState.setLinkCreated(commandToExecute
					.getLinkRepresentation());
		}
		showFastLinkerView().update();
	}

	/**
	 * Ask this method to display the textual result of an executed command in
	 * the Information view.
	 * 
	 * @param executedCommand_p
	 */
	public void showCommandExecutedMessage(
			final AbstractCreateLinksCommand executedCommand_p) {
		EObject createdLinkObject = executedCommand_p.getCreatedLinkObject();
		final String informationMessage;
		final EObject affectedObject;
		String sourceName = executedCommand_p.getSource().getLabel();
		String targetName = executedCommand_p.getTarget().getLabel();
		
		if (null != createdLinkObject) {
			informationMessage = MessageFormat.format(
					Messages.FastLinkerManager_QualifiedLinkCommandReport,
					createdLinkObject.eClass().getName(), sourceName,
					targetName);
			affectedObject = createdLinkObject;
		} else {
			informationMessage = MessageFormat.format(
					Messages.FastLinkerManager_UnQualifiedLinkCommandReport,
					executedCommand_p.getLabel(), sourceName, targetName);
			affectedObject = executedCommand_p.getSource();
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
		} catch (PartInitException exception_p) {
			// An error occurred -> log it.
			MarkerViewPlugin
					.getDefault()
					.getLog()
					.log(new Status(IStatus.ERROR, MarkerViewPlugin.PLUGIN_ID,
							exception_p.getLocalizedMessage(), exception_p));
		}
	}
	
	public void updateCurrentState(Collection first, Collection second , Collection pinned ){
		_currentState.updateState(first , second , pinned);
		showFastLinkerView().update();
	}
}
