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
package org.polarsys.capella.test.diagram.common.ju.wrapper.utils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.sequence.description.tool.MessageCreationTool;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemDescription;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.ui.IEditorPart;
import org.junit.Assert;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.sirius.analysis.tool.StringUtil;
import org.polarsys.capella.test.diagram.common.ju.wrapper.OperationActionToolDescriptionWrapper;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 * Useful class for tools on a given session and diagram
 */
public class ToolHelper {

  Session _session;
  DDiagram _diagram;

  /**
   * Constructor
   * 
   * @param session_p
   *          the target session
   * @param diagram
   *          the target diagram
   */
  public ToolHelper(Session session, DDiagram diagram) {

    _session = session;
    _diagram = diagram;

  }

  /**
   * Return the Tools so-called toolName for a given diagram, null if it does not exist
   * 
   * @param toolName
   *          the tool name
   * @return <code>null</code> if an error occurred.
   */
  public AbstractToolDescription getTool(final String toolName) {
    Assert.assertNotNull(_session);
    Assert.assertNotNull(_diagram);

    final List<AbstractToolDescription> tools = new DiagramComponentizationManager()
        .getAllTools(_session.getSelectedViewpoints(true), _diagram.getDescription());
    AbstractToolDescription theAbstractToolDescription = null;

    for (AbstractToolDescription current : tools) {
      if (current.getName().equals(toolName)) {
        theAbstractToolDescription = current;
        break;
      }

      if (current instanceof PopupMenu) {
        for (MenuItemDescription item : ((PopupMenu) current).getMenuItemDescription()) {
          if (item.getName().equals(toolName)) {
            theAbstractToolDescription = item;
            break;
          }
        }
      }
    }

    return theAbstractToolDescription;
  }

  /**
   * Return the Tools so-called toolName for a given diagram, null if it does not exist
   * 
   * @param toolName
   *          the tool name
   * @return <code>null</code> if an error occurred.
   */
  public AbstractToolDescription getToolByLabel(final String toolName) {

    Assert.assertNotNull(_session);
    Assert.assertNotNull(_diagram);

    final List<AbstractToolDescription> tools = new DiagramComponentizationManager()
        .getAllTools(_session.getSelectedViewpoints(true), _diagram.getDescription());
    AbstractToolDescription theAbstractToolDescription = null;

    for (AbstractToolDescription current : tools) {
      if ((current.getLabel() != null) && current.getLabel().equals(toolName)) {
        theAbstractToolDescription = current;
        break;
      }
    }

    return theAbstractToolDescription;
  }

  /**
   * Return the Tools so-called toolName for a given diagram, null if it does not exist
   * 
   * @param toolName
   *          the tool name
   * @return <code>null</code> if an error occurred.
   */
  public AbstractToolDescription getToolByLabel(final String toolName, String toolLabel) {

    Assert.assertNotNull(_session);
    Assert.assertNotNull(_diagram);

    final List<AbstractToolDescription> tools = new DiagramComponentizationManager()
        .getAllTools(_session.getSelectedViewpoints(true), _diagram.getDescription());
    AbstractToolDescription theAbstractToolDescription = null;

    for (AbstractToolDescription current : tools) {
      if (current.getName().equals(toolName)) {
        if ((current.getLabel() != null) && current.getLabel().equals(toolLabel)) {
          theAbstractToolDescription = current;
          break;
        }
      }
    }

    return theAbstractToolDescription;
  }

  /**
   * Get the {@link Set} of the actual tools detected in diagram. Fails if there's a duplicated Name/Label detected in
   * filter list extracted from runtime diagram
   * 
   * @param tools
   *          a EList<AbstractToolDescription>
   * @return the set of found filters in runtime diagram
   */
  public static Set<String> getSetOfActualTools(EList<AbstractToolDescription> tools) {
    Set<String> setOfActualTools = new HashSet<String>();
    List<String> listOfDuplicated = new ArrayList<String>();

    for (AbstractToolDescription toolDescription : tools) {

      if ((toolDescription instanceof ContainerCreationDescription)
          || (toolDescription instanceof NodeCreationDescription)
          || (toolDescription instanceof EdgeCreationDescription) || (toolDescription instanceof ToolDescription)
          || (toolDescription instanceof MessageCreationTool)) {

        if ((toolDescription.getLabel() == null)
            || toolDescription.getLabel().equalsIgnoreCase(ICommonConstants.EMPTY_STRING)) {
          if (!setOfActualTools.add(toolDescription.getName())) {
            listOfDuplicated.add(toolDescription.getName());
          }
        }

        else {
          if (!setOfActualTools.add(toolDescription.getLabel())) {
            listOfDuplicated.add(toolDescription.getLabel());
          }
        }
      }
    }

    return setOfActualTools;
  }

  /**
   * Get the list of the tools of a diagram excepting Constraints group tools and Accelerators section tools, which are
   * common to many diagrams
   * 
   * @param tools
   * @return
   */
  public static Set<String> getSetOfActualToolsWithoutConstraintsAccelerators(EList<AbstractToolDescription> tools) {
    Set<String> setOfActualTools = new HashSet<String>();
    List<String> listOfDuplicated = new ArrayList<String>();

    for (AbstractToolDescription toolDescription : tools) {
      if (!(getGroupTools(tools, "Constraints").contains(toolDescription)) //$NON-NLS-1$
          && !(getGroupTools(tools, "Accelerators").contains(toolDescription))) { //$NON-NLS-1$
        if ((toolDescription instanceof ContainerCreationDescription)
            || (toolDescription instanceof NodeCreationDescription)
            || (toolDescription instanceof EdgeCreationDescription) || (toolDescription instanceof ToolDescription)
            || ((toolDescription instanceof MessageCreationTool))) {

          if (!setOfActualTools.add(toolDescription.getName())) {
            listOfDuplicated.add(toolDescription.getName());
          }
        }
      }
    }

    return setOfActualTools;
  }

  /**
   * get the list of tools contained in a group/section named by groupName_p
   * 
   * @param tools
   * @param groupName_p
   * @return
   */
  private static List<AbstractToolDescription> getGroupTools(EList<AbstractToolDescription> tools, String groupName) {
    List<AbstractToolDescription> listOfGroupTools = new ArrayList<AbstractToolDescription>();
    for (AbstractToolDescription toolDescription : tools) {

      if ((toolDescription instanceof ContainerCreationDescription)
          || (toolDescription instanceof NodeCreationDescription)
          || (toolDescription instanceof EdgeCreationDescription) || (toolDescription instanceof ToolDescription)
          || (toolDescription instanceof MessageCreationTool)) {
        if (toolDescription.eContainer() instanceof IdentifiedElement) {
          IdentifiedElement container = (IdentifiedElement) toolDescription.eContainer();
          if (container.getName().equals(groupName)) {
            listOfGroupTools.add(toolDescription);
          }
        }
      }
    }
    return listOfGroupTools;
  }

  /**
   * For a tool that have an expression specifying which ElementsToSelect, check if the selected views after the tool
   * are the expected ones. It must be called after tool being executed. before, it will do wrong checks.
   */
  public static void checkSelectionOk(OperationActionToolDescriptionWrapper toolWrapper) {

    if (toolWrapper.isPreconditionOk()) {

      String elementsToSelect = toolWrapper.getTool().getElementsToSelect();
      if ((elementsToSelect != null) && !StringUtil.isEmpty(elementsToSelect)) {

        Collection<DSemanticDecorator> initialViews = (Collection<DSemanticDecorator>) toolWrapper
            .getArgumentValue(ArgumentType.COLLECTION);
        EObject semantic = ((DDiagramElement) initialViews.iterator().next()).getTarget();
        InterpreterRegistry interpreterRegistry = SiriusPlugin.getDefault().getInterpreterRegistry();
        IInterpreter interpreter = interpreterRegistry.getInterpreter(semantic);

        DDiagram diagram = ((DDiagramElement) initialViews.iterator().next()).getParentDiagram();
        interpreter.setVariable("views", initialViews); // There is no constants for it in Sirius
        interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);

        RuntimeLoggerInterpreter decorate = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
        EAttribute attribute = ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

        Collection<EObject> result = decorate.evaluateCollection(semantic, toolWrapper.getTool(), attribute);
        // remove hidden elements since they will not be selected
        result = result.stream().filter(x -> DiagramHelper.isDiagramElementSelectable((DDiagramElement) x))
            .collect(Collectors.toList());

        if (!result.isEmpty()) {

          // Selection is made on async in UI thread. we need to wait the async execution for this tool.
          GuiActions.flushASyncGuiThread();
          IEditorPart part = DiagramHelper.getDiagramEditor(SessionManager.INSTANCE.getSession(semantic), diagram);
          Collection<DSemanticDecorator> selectedViews = DialectUIManager.INSTANCE.getSelection((DialectEditor) part);

          boolean isValid = result.containsAll(selectedViews) && selectedViews.containsAll(result);
          Assert.assertTrue(MessageFormat.format(Messages.failedSelectedElements, EObjectExt.getText(diagram),
              toolWrapper.getTool().getName(), selectedViews.size()), isValid);
        }

        interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
        interpreter.unSetVariable("views");
      }
    }
  }

}
