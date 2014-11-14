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
package org.polarsys.capella.core.business.abstractqueries;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;

import org.polarsys.capella.core.business.queries.BusinessQueriesPlugin;
import org.polarsys.capella.core.business.queries.preferences.ICoreBusinessQueriesPreferences;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 * This abstraction level is done for queries which do not allow to put in a feature an element a item which is already put as an "owned" feature of model such
 * as "min", "max", "null", "default", "min len", "max len", "min card", "max card",... features.
 */
public abstract class AbstractElement_ValueAvoidingAlreadyOwnedItems extends CapellaElement_CurrentAndHigherLevelsQuery {
  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    // Filters the list to remove the element which shouldn't be affected twice
    List<CapellaElement> unfilteredAvailableElements = super.getAvailableElements(element_p);
    // Gets the plug-n preference store
    IPreferenceStore preferenceStore = BusinessQueriesPlugin.getDefault().getPreferenceStore();
    // gets the "skip already owned elements " preference value
    boolean skipOwnedElements = preferenceStore.getBoolean(ICoreBusinessQueriesPreferences.PREF_SKIP_OWNED_MIN_MAX_NULL_DEFAULT_VALUES);
    return skipOwnedElements ? filterAlreadyOwnedElements(unfilteredAvailableElements) : unfilteredAvailableElements;
  }

  /**
   * Filter the given list to remove the elements which are already affected to an "owned" feature (min, max, default, null,...) in the model.
   * @param list_p the given list
   * @return the filtered list
   */
  protected List<CapellaElement> filterAlreadyOwnedElements(List<CapellaElement> list_p) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    Set<CapellaElement> elementsToAvoid = buildAlreadyOwnedElementsListAtLevelsOf(list_p);
    for (CapellaElement element : list_p) {
      if (!elementsToAvoid.contains(element)) {
        returnValue.add(element);
      }
    }
    return returnValue;
  }

  /**
   * This method builds a list of the elements at the same level as the given elements (including those elements) which are already affected to an "owned"
   * feature (min, max, null, default,...) in the model.
   * @param list_p the list
   * @return the elements which are already affected to an owned feature
   */
  protected Set<CapellaElement> buildAlreadyOwnedElementsListAtLevelsOf(List<CapellaElement> list_p) {
    // Creates a set in order to avoid elements duplications
    Set<CapellaElement> listOfElementToAvoid = new HashSet<CapellaElement>();
    // Also creates a set of the processed container in order to not process them twice
    Set<EObject> alreadyProcessedContainers = new HashSet<EObject>();
    // iterates other every element
    for (CapellaElement element : list_p) {
      // Gets its container
      EObject eContainer = element.eContainer();
      if (!alreadyProcessedContainers.contains(eContainer)) {
        // If the container hasn't been processed yet
        // adds it to the list of the processed ones and process it
        alreadyProcessedContainers.add(eContainer);
        // And adds all its "owned" elements to the list, regarding its type
        if (eContainer instanceof BooleanType) {
          BooleanType container = ((BooleanType) eContainer);
          listOfElementToAvoid.add(container.getOwnedDefaultValue());
        } else if (eContainer instanceof StringType) {
          StringType container = ((StringType) eContainer);
          listOfElementToAvoid.add(container.getOwnedDefaultValue());
          listOfElementToAvoid.add(container.getOwnedNullValue());
          listOfElementToAvoid.add(container.getOwnedMinLength());
          listOfElementToAvoid.add(container.getOwnedMaxLength());
        } else if (eContainer instanceof NumericType) {
          NumericType container = ((NumericType) eContainer);
          listOfElementToAvoid.add(container.getOwnedDefaultValue());
          listOfElementToAvoid.add(container.getOwnedNullValue());
          listOfElementToAvoid.add(container.getOwnedMaxValue());
          listOfElementToAvoid.add(container.getOwnedMinValue());
        } else if (eContainer instanceof Enumeration) {
          Enumeration container = ((Enumeration) eContainer);
          listOfElementToAvoid.add(container.getOwnedDefaultValue());
          listOfElementToAvoid.add(container.getOwnedNullValue());
          listOfElementToAvoid.add(container.getOwnedMaxValue());
          listOfElementToAvoid.add(container.getOwnedMinValue());
        } else if (eContainer instanceof MultiplicityElement) {
          MultiplicityElement container = ((MultiplicityElement) eContainer);
          listOfElementToAvoid.add(container.getOwnedDefaultValue());
          listOfElementToAvoid.add(container.getOwnedNullValue());
          listOfElementToAvoid.add(container.getOwnedMaxValue());
          listOfElementToAvoid.add(container.getOwnedMinValue());
          listOfElementToAvoid.add(container.getOwnedMaxCard());
          listOfElementToAvoid.add(container.getOwnedMinCard());
          listOfElementToAvoid.add(container.getOwnedMinLength());
          listOfElementToAvoid.add(container.getOwnedMaxLength());
        }
      }
    }
    alreadyProcessedContainers = null;
    return listOfElementToAvoid;
  }
}
