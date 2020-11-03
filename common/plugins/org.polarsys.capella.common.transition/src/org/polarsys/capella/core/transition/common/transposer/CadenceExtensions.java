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

package org.polarsys.capella.core.transition.common.transposer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;

/**
 * This class allows to get Strategies, Refineries and generators contributing at the Manager extensions points.
 * 
 * 
 */
public final class CadenceExtensions {

  // all extension points Attribut
  // WORKFLOW EXTENSION

  public static final String ATT_IDENTIFIER = "Identifier"; //$NON-NLS-1$

  public static final String ATT_NAME = "Name"; //$NON-NLS-1$
  public static final String ATT_DESCRIPTION = "Description"; //$NON-NLS-1$
  public static final String ATT_PARAMETER_DEFINITION = "ParameterDefinition"; //$NON-NLS-1$
  public static final String ATT_TYPE = "Type"; //$NON-NLS-1$
  public static final String ATT_ACTIVITY_CLASS = "ActivityClass"; //$NON-NLS-1$
  public static final String ATT_WORKFLOW_ELEMENT = "WorkflowElement"; //$NON-NLS-1$
  public static final String ATT_ACTIVITY__WORKFLOW = "WorkflowIdentifier"; //$NON-NLS-1$
  public static final String ATT_ACTIVITY__WORKFLOW_ELEMENT = "WorkflowElementIdentifier"; //$NON-NLS-1$
  public static final String PARAMETER_DEFINITION = "ParameterDefinition"; //$NON-NLS-1$

  public static final String ATT_MULTIPLE_CONTRIBUTIONS = "AllowMultipleContributions"; //$NON-NLS-1$
  public static final String ATT_MULTIPLE_ACTIVTY = "Multiple"; //$NON-NLS-1$
  private static final String ACTIVITY_ID = "org.polarsys.kitalpha.cadence.core.activity.declaration"; //$NON-NLS-1$

  private static final String WORKFLOW_ID = "org.polarsys.kitalpha.cadence.core.workflow.declaration"; //$NON-NLS-1$

  /**
   * Method that allows to get all contributing strategies.
   * 
   * @return all strategies registered
   */
  public static IConfigurationElement[] getAllWorkflowsExtensions() {

    return getExtensionElt(WORKFLOW_ID);
  }

  public static IConfigurationElement[] getActivitiesForWorkflowElement(String workflow_id, String workflowElt_id) {
    IConfigurationElement[] configElements = getAllActivityDeclaration();
    List<IConfigurationElement> activities = new ArrayList<IConfigurationElement>();

    for (IConfigurationElement elt : configElements) {
      if (elt.getAttribute(ATT_ACTIVITY__WORKFLOW).equals(workflow_id) && elt.getAttribute(ATT_ACTIVITY__WORKFLOW_ELEMENT).equals(workflowElt_id)) {
        activities.add(elt);
      }
    }
    return activities.toArray(new IConfigurationElement[] {});
  }

  public static IConfigurationElement getActivityConfigElement(String identifier) {
    IConfigurationElement configuration = null;

    IConfigurationElement[] configElements = getAllActivityDeclaration();
    if (configElements != null) {
      for (IConfigurationElement elt : configElements) {
        if (elt.getAttribute(ATT_IDENTIFIER).equals(identifier)) {
          configuration = elt;
        }
      }
    }

    return configuration;
  }

  private static IConfigurationElement[] getExtensionElt(String id) {
    IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(id);
    IExtension[] extensions = point.getExtensions();
    ArrayList<IConfigurationElement> configElements = new ArrayList<IConfigurationElement>();

    for (IExtension extension : extensions) {
      configElements.addAll(Arrays.asList(extension.getConfigurationElements()));
    }

    return configElements.toArray(new IConfigurationElement[] {});
  }

  public static IConfigurationElement getWorkflow(String identifier) {
    IConfigurationElement configuration = null;
    IConfigurationElement[] configElements = getAllWorkflowsExtensions();
    if (configElements != null) {

      for (IConfigurationElement elt : configElements) {
        if (elt.getAttribute(ATT_IDENTIFIER).equals(identifier)) {
          configuration = elt;
        }
      }
    }
    return configuration;
  }

  public static IConfigurationElement getWorkflowElement(String workflow_id, String identifier) {
    IConfigurationElement[] configElements = getWorkflowElementConfigElement(workflow_id, ATT_WORKFLOW_ELEMENT);
    IConfigurationElement configuration = null;
    if (configElements != null) {

      for (IConfigurationElement elt : configElements) {
        if (elt.getAttribute(ATT_IDENTIFIER).equals(identifier)) {
          configuration = elt;
        }
      }
    }

    return configuration;
  }

  public static IConfigurationElement[] getAllActivityDeclaration() {
    return getExtensionElt(ACTIVITY_ID);
  }

  public static IConfigurationElement[] getAllWorkflowElement(String workflow_id) {
    return getWorkflowElementConfigElement(workflow_id, ATT_WORKFLOW_ELEMENT);
  }

  public static IConfigurationElement getWorkflowElementDescription(String workflow_id) {
    return getWorkflowElementConfigElement(workflow_id, ATT_DESCRIPTION)[0];
  }

  public static IConfigurationElement[] getWorkflowElementConfigElement(String workflow_id, String workflowElt_id) {

    IConfigurationElement configElements = getWorkflow(workflow_id);
    IConfigurationElement[] elt = configElements.getChildren(workflowElt_id);
    return elt;
  }

  public static IConfigurationElement[] getWorkflowElementParameters(IConfigurationElement workflowElement) {
    return workflowElement.getChildren(PARAMETER_DEFINITION);

  }

  public static int getSize(String workflow_id, String workflowElement_id) {
    return getWorkflowElementParameters(getWorkflowElement(workflow_id, workflowElement_id)).length;
  }

  public static boolean isMultiple(String workflow_id, String workflowElement_id) {

    IConfigurationElement element = getWorkflowElement(workflow_id, workflowElement_id);
    if (element == null) {
      return true;
    }
    String isMultiple = element.getAttribute(ATT_MULTIPLE_CONTRIBUTIONS);
    return Boolean.parseBoolean(isMultiple);
  }

}
