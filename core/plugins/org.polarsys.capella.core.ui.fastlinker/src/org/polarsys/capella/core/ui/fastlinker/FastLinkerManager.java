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
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.markers.MarkerViewUtil;

import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.LightMarkerRegistry.IMarkerModification;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerView;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewPlugin;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.links.helpers.LinksCommandRegistry;
import org.polarsys.capella.core.model.links.helpers.commands.AbstractCreateLinksCommand;
import org.polarsys.capella.core.ui.fastlinker.view.FastLinkerView;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

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
   * If the dragged/selected element is not the element to put in the FastLinker, this method get the element to put from the originally dragged/selected
   * element.
   * @param originalElement_p
   * @return
   */
  protected ModelElement getElementToPut(ModelElement originalElement_p) {
    // If the original element is of type Part so FastLinker has to work with the Component of this part.
    if (CsPackage.Literals.PART.isInstance(originalElement_p)) {
      return ((Part) originalElement_p).getAbstractType();
    }
    return originalElement_p;
  }

  /**
   * This method has to be called to know if the FastLinker is able to "work" with the given element (regarding its state and available commands to create
   * links).<br>
   * <b>It is mandatory to call this method {@link FastLinkerManager#putElementInFastLinker(EObject)} since it initializes commands lists.</b>
   * @param elementToPut_p
   * @return
   */
  public boolean acceptElementInFastLinker(EObject elementToPut_p) {
    // Precondition : eliminate null and non ModelElement.
    if ((null == elementToPut_p) || !(elementToPut_p instanceof ModelElement)) {
      return false;
    }
    ModelElement modelElementToPut = getElementToPut((ModelElement) elementToPut_p);
    // Compute the future state of the FastLinker.
    FastLinkerState futureState = _currentState.getPreviewState(modelElementToPut);
    if (null == futureState) {
      return false;
    }
    // Clean command lists.
    _firstToSecondCommands.clear();
    _secondToFirstCommands.clear();

    // Check if element will be the first one in FastLinker.
    if ((null != futureState._firstElement) && (null == futureState._secondElement)) {
      // Is there commands using this element as source or target ?
      LinksCommandRegistry linksCommandRegistryInstance = LinksCommandRegistry.getInstance();
      if (linksCommandRegistryInstance.containsCommandForSourceType(futureState._firstElement.eClass())
          || linksCommandRegistryInstance.containsCommandForTargetType(futureState._firstElement.eClass())) {
        return true;
      }
    }
    // Element will not be the first one -> check commands are available between first and second elements (as source or target).
    else if (((futureState._firstElement != null) && (futureState._secondElement != null))
             && (LinksCommandRegistry.getInstance()
                 .containsCommandForSourceTargetTypes(futureState._firstElement.eClass(), futureState._secondElement.eClass()) || LinksCommandRegistry
                 .getInstance().containsCommandForSourceTargetTypes(futureState._secondElement.eClass(), futureState._firstElement.eClass()))) {
      // Some commands are available -> get them.
      _firstToSecondCommands.addAll(LinksCommandRegistry.getInstance().getExecutableCommands(futureState._firstElement, futureState._secondElement));
      _secondToFirstCommands.addAll(LinksCommandRegistry.getInstance().getExecutableCommands(futureState._secondElement, futureState._firstElement));
      if (!_firstToSecondCommands.isEmpty() || !_secondToFirstCommands.isEmpty()) {
        return true;
      }
    }
    // No command(s) available for the lone element or the two elements -> element is not accepted.
    return false;
  }

  /**
   * Pin or unpin a ModelElement present in the FastLinker.<br>
   * The element will be unpinned if it is already pinned.
   * @param elementToPin_p
   */
  public void pinModelElement(ModelElement elementToPin_p) {
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
   * @return
   */
  public FastLinkerState getCurrentState() {
    return _currentState;
  }

  /**
   * Get an unmodifiable view of available firstToSecondCommands.
   * @return
   */
  public List<AbstractCreateLinksCommand> getFirstToSecondCommands() {
    return Collections.unmodifiableList(_firstToSecondCommands);
  }

  /**
   * Get an unmodifiable view of available secondToFirstCommands.
   * @return
   */
  public List<AbstractCreateLinksCommand> getSecondToFirstCommands() {
    return Collections.unmodifiableList(_secondToFirstCommands);
  }

  /**
   * Show the FastLinker view and return it.
   * @return
   */
  public FastLinkerView showFastLinkerView() {
    try {
      // Show view (if not already shown).
      // The view is not given focus.
      return (FastLinkerView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
          .showView(FastLinkerView.VIEW_ID, null, IWorkbenchPage.VIEW_VISIBLE);
    } catch (PartInitException exception_p) {
      // An error occurred -> log it.
      FastLinkerActivator.getDefault().getLog().log(new Status(IStatus.ERROR, FastLinkerActivator.PLUGIN_ID, exception_p.getLocalizedMessage(), exception_p));
      return null;
    }
  }

  /**
   * Put an element in the FastLinker.<br>
   * <b>It is mandatory to call {@link FastLinkerManager#acceptElementInFastLinker(EObject)} and to get a <code>true</code> result before calling this
   * method.</b>
   * @param elementToPut_p
   */
  public void putElementInFastLinker(EObject elementToPut_p) {
    putElementInFastLinker(elementToPut_p, null);
  }

  /**
   * Put an element in the FastLinker.<br>
   * <b>It is mandatory to call {@link FastLinkerManager#acceptElementInFastLinker(EObject)} and to get a <code>true</code> result before calling this
   * method.</b><br>
   * <b>This method is available for test purposes</b>, it allows to specify a command to execute and so to avoid asking the user to choose the command to
   * execute. In normal usage, {@link FastLinkerManager#putElementInFastLinker(EObject)} must be called.
   * @param elementToPut_p
   * @param commandToExecute_p the command to execute, must be amongst executable commands (use {@link FastLinkerManager#getFirstToSecondCommands()} or
   *          {@link FastLinkerManager#getSecondToFirstCommands()}).
   */
  public void putElementInFastLinker(final EObject elementToPut_p, AbstractCreateLinksCommand commandToExecute_p) {
    // Preconditions.
    // Eliminate null and non ModelElement.
    if ((null == elementToPut_p) || !(elementToPut_p instanceof ModelElement)) {
      return;
    }
    // If a command to execute is specified, it must be amongst executable commands.
    if ((null != commandToExecute_p) && (!_firstToSecondCommands.contains(commandToExecute_p) || !_secondToFirstCommands.contains(commandToExecute_p))) {
      throw new IllegalArgumentException("Command to execute must be amongst executable command(s)."); //$NON-NLS-1$
    }

    ModelElement modelElementToPut = getElementToPut((ModelElement) elementToPut_p);
    // Update FastLinker state.
    _currentState.updateState(modelElementToPut);
    // Get command to execute.
    AbstractCreateLinksCommand commandToExecute;
    if ((0 == _firstToSecondCommands.size()) && (0 == _secondToFirstCommands.size())) {
      // No command to execute (there is probably only one element in the FastLinker, else there is a problem...).
      commandToExecute = null;
    } else if ((1 == _firstToSecondCommands.size()) && (0 == _secondToFirstCommands.size())) {
      // Only one command in the firstToSecond list -> execute it.
      commandToExecute = _firstToSecondCommands.get(0);
    } else if ((0 == _firstToSecondCommands.size()) && (1 == _secondToFirstCommands.size())) {
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
        // Display both elements (without link, since it is not already created).
        fastLinkerView.update();
        // Ask user to choose between available commands.
        commandToExecute = fastLinkerView.chooseCommandToExecute(_firstToSecondCommands, _secondToFirstCommands);
      }
    }
    if (null != commandToExecute) {
      // Execute command (if any).
      LinksCommandRegistry.getInstance().executeCommand(commandToExecute);
      // Display a trace in the Information view.
      showCommandExecutedMessage(commandToExecute);
      // Update state and view with the created link.
      _currentState.setLinkCreated(commandToExecute.getLinkRepresentation());
    }
    showFastLinkerView().update();
  }

  /**
   * Ask this method to display the textual result of an executed command in the Information view.
   * @param executedCommand_p
   */
  public void showCommandExecutedMessage(final AbstractCreateLinksCommand executedCommand_p) {
    EObject createdLinkObject = executedCommand_p.getCreatedLinkObject();
    final String[] informationMessage = new String[1];
    final EObject[] affectedObject = new EObject[1];
    String sourceName = executedCommand_p.getSource().getLabel();
    String targetName = executedCommand_p.getTarget().getLabel();
    if (null != createdLinkObject) {
      informationMessage[0] =
          MessageFormat.format(Messages.FastLinkerManager_QualifiedLinkCommandReport, createdLinkObject.eClass().getName(), sourceName, targetName);
      affectedObject[0] = createdLinkObject;
    } else {
      informationMessage[0] =
          MessageFormat.format(Messages.FastLinkerManager_UnQualifiedLinkCommandReport, executedCommand_p.getLabel(), sourceName, targetName);
      affectedObject[0] = executedCommand_p.getSource();
    }

    IMarkerModification markerModification = new IMarkerModification() {
      @Override
      public void modify(IMarker marker_p) {
        try {
          marker_p.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO);
          marker_p.setAttribute(IMarker.MESSAGE, informationMessage[0]);
          String resourceURI = affectedObject[0].eResource().getURI().toString();
          String objUri = affectedObject[0].eResource().getURIFragment(affectedObject[0]).toString();
          marker_p.setAttribute(EmbeddedMessage.AFFECTED_OBJECTS_URI, resourceURI + "#" + objUri); //$NON-NLS-1$
          marker_p.setAttribute(MarkerViewUtil.PATH_ATTRIBUTE, resourceURI);
        } catch (CoreException e) {
          MarkerViewPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, MarkerViewPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
        }
      }
    };
    LightMarkerRegistry.getInstance().createMarker(ResourcesPlugin.getWorkspace().getRoot(), MarkerView.MARKER_ID, markerModification);
    try {
      // Show the Information view (if not already shown).
      PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(MarkerView.VIEW_ID, null, IWorkbenchPage.VIEW_VISIBLE);
    } catch (PartInitException exception_p) {
      // An error occurred -> log it.
      MarkerViewPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, MarkerViewPlugin.PLUGIN_ID, exception_p.getLocalizedMessage(), exception_p));
    }
  }
}
