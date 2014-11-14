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
package org.polarsys.capella.core.model.handler.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.KeyValue;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.capellamodeller.Project;

/**
 * Provides services to deal with Capella Project concept.
 */
public class CapellaProjectHelper {

  /**
   * 
   */
  public static final String PROGRESS_STATUS_KEYWORD = "ProgressStatus"; //$NON-NLS-1$

  /**
   * Capella project approach.<br>
   */
  public enum ProjectApproach {
    /**
     * DO NOT allow reuse of components with parts.
     */
    SingletonComponents,
    /**
     * Allow reuse of components with parts.
     */
    ReusableComponents
  }

  public enum TriStateBoolean {
    /**
     * True
     */
    True,
    /**
     * False
     */
    False,
    /**
     * Not True nor False
     */
    Undefined
  }

  /**
   * Project Approach Key.
   */
  private static final String PROJECT_APPROACH_KEY = "projectApproach"; //$NON-NLS-1$

  /**
   * Set given project with specified approach.
   * @param approach_p
   * @param capellaProject_p
   */
  public static void setProjectWithApproach(Project capellaProject_p, ProjectApproach approach_p) {
    // Preconditions : the capella project must not already have an approach set.
    if (TriStateBoolean.True.equals(hasGivenApproach(ProjectApproach.SingletonComponents, capellaProject_p))
        || TriStateBoolean.True.equals(hasGivenApproach(ProjectApproach.ReusableComponents, capellaProject_p))) {
      return;
    }
    // Create a key value to store the project approach.
    KeyValue natureKeyValue = CapellacoreFactory.eINSTANCE.createKeyValue();
    natureKeyValue.setKey(PROJECT_APPROACH_KEY);
    natureKeyValue.setValue(approach_p.name());
    capellaProject_p.getKeyValuePairs().add(natureKeyValue);
  }

  /**
   * Has specified project the given approach ?
   * @param approach_p
   * @param capellaProject_p
   * @return <code>TriStateBoolean.True</code> means yes; <code>TriStateBoolean.Undefined</code> if capellaProject is <code>null</code>.
   */
  private static TriStateBoolean hasGivenApproach(ProjectApproach approach_p, Project capellaProject_p) {
    TriStateBoolean result = TriStateBoolean.False;
    // Precondition.
    if (null == capellaProject_p) {
      return TriStateBoolean.Undefined;
    }
    for (KeyValue keyValue : capellaProject_p.getKeyValuePairs()) {
      // Check if a key / value is the nature one.
      if (PROJECT_APPROACH_KEY.equals(keyValue.getKey())) {
        // Compare serialized one to given one.
        if ((approach_p.name().equals(keyValue.getValue()))) {
          // Match OK !
          result = TriStateBoolean.True;
          break;
        }
      }
    }
    return result;
  }

  /**
   * Is given capella element Singleton Components driven ?
   * @param capellaElement_p
   * @return <code>true</code> means yes.
   */
  public static TriStateBoolean isSingletonComponentsDriven(EObject capellaElement_p) {
    return hasGivenApproach(ProjectApproach.SingletonComponents, getProject(capellaElement_p));
  }

  /**
   * Is given capella element Reusable Components driven ?
   * @param capellaElement_p
   * @return <code>true</code> means yes.
   */
  public static TriStateBoolean isReusableComponentsDriven(EObject capellaElement_p) {
    return hasGivenApproach(ProjectApproach.ReusableComponents, getProject(capellaElement_p));
  }

  /**
   * Remove deprecated project approach content.
   * @param capellaProject
   */
  public static void removeOldProjectApproachContent(Project capellaProject) {
    List<KeyValue> keyValuePairs = capellaProject.getKeyValuePairs();
    if (!keyValuePairs.isEmpty()) {
      for (Iterator<KeyValue> keyValues = keyValuePairs.iterator(); keyValues.hasNext();) {
        KeyValue projectApproachKeyValue = keyValues.next();
        if (PROJECT_APPROACH_KEY.equals(projectApproachKeyValue.getKey())) {
          String value = projectApproachKeyValue.getValue();
          if (!ProjectApproach.ReusableComponents.name().equals(value) && !ProjectApproach.SingletonComponents.name().equals(value)) {
            keyValues.remove();
          }
          break;
        }
      }
    }
  }

  /**
   * @param project_p
   */
  public static void addProjectProgressStatus(Project project_p) {
    EnumerationPropertyType type = CapellacoreFactory.eINSTANCE.createEnumerationPropertyType(CapellaProjectHelper.PROGRESS_STATUS_KEYWORD);
    type.getOwnedLiterals().add(CapellacoreFactory.eINSTANCE.createEnumerationPropertyLiteral("DRAFT")); //$NON-NLS-1$
    type.getOwnedLiterals().add(CapellacoreFactory.eINSTANCE.createEnumerationPropertyLiteral("TO_BE_REVIEWED")); //$NON-NLS-1$
    type.getOwnedLiterals().add(CapellacoreFactory.eINSTANCE.createEnumerationPropertyLiteral("TO_BE_DISCUSSED")); //$NON-NLS-1$
    type.getOwnedLiterals().add(CapellacoreFactory.eINSTANCE.createEnumerationPropertyLiteral("REWORK_NECESSARY")); //$NON-NLS-1$
    type.getOwnedLiterals().add(CapellacoreFactory.eINSTANCE.createEnumerationPropertyLiteral("UNDER_REWORK")); //$NON-NLS-1$
    type.getOwnedLiterals().add(CapellacoreFactory.eINSTANCE.createEnumerationPropertyLiteral("REVIEWED_OK")); //$NON-NLS-1$
    project_p.getOwnedEnumerationPropertyTypes().add(type);
  }

  /**
   * @param eObject_p
   * @param key_p
   * @return
   */
  public static EnumerationPropertyType getEnumerationPropertyType(EObject eObject_p, String key_p) {
    if (null != eObject_p) {
      Project project = getProject(eObject_p);
      if (null != project) {
        for (EnumerationPropertyType current : project.getOwnedEnumerationPropertyTypes()) {
          if (current.getName().equals(key_p)) {
            return current;
          }
        }
      }
    }
    return null;
  }

  /**
   * @param eObject_p
   * @param key_p
   * @return
   */
  public static List<EnumerationPropertyLiteral> getEnumerationPropertyLiterals(EObject eObject_p, String key_p) {
    List<EnumerationPropertyLiteral> result = new ArrayList<EnumerationPropertyLiteral>();
    if (null != eObject_p) {
      Project project = getProject(eObject_p);
      if (null != project) {
        for (EnumerationPropertyType current : project.getOwnedEnumerationPropertyTypes()) {
          if (current.getName().equals(key_p)) {
            result.addAll(current.getOwnedLiterals());
          }
        }
      }
    }
    return result;
  }

  /**
   * Get project that contains given model element.
   * @param modelElement_p
   * @return <code>null</code> if not found.
   */
  public static Project getProject(EObject modelElement_p) {
    EObject parent = modelElement_p;
    while (!(parent instanceof Project) && (null != parent)) {
      parent = parent.eContainer();
    }
    return (null != parent) ? (Project) parent : null;
  }
}
