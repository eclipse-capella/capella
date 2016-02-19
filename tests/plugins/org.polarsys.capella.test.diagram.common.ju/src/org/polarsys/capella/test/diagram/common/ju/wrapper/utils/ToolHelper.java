/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.wrapper.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.sequence.description.tool.MessageCreationTool;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

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

    final List<AbstractToolDescription> tools = new DiagramComponentizationManager().getAllTools(
        _session.getSelectedViewpoints(true), _diagram.getDescription());
    AbstractToolDescription theAbstractToolDescription = null;

    for (AbstractToolDescription current : tools) {
      if (current.getName().equals(toolName)) {
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
  public AbstractToolDescription getToolByLabel(final String toolName) {

    Assert.assertNotNull(_session);
    Assert.assertNotNull(_diagram);

    final List<AbstractToolDescription> tools = new DiagramComponentizationManager().getAllTools(
        _session.getSelectedViewpoints(true), _diagram.getDescription());
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

    final List<AbstractToolDescription> tools = new DiagramComponentizationManager().getAllTools(
        _session.getSelectedViewpoints(true), _diagram.getDescription());
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
      if (!(getGroupTools(tools, "Constraints").contains(toolDescription)) && !(getGroupTools(tools, "Accelerators").contains(toolDescription))) { //$NON-NLS-1$ //$NON-NLS-2$
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
}
