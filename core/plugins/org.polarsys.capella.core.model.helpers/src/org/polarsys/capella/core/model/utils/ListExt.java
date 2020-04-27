/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

/**
 */
public class ListExt {

  /**
   * Retrieves from the given list the elements named with the given string
   *
   * @param list list of elements
   * @param name string to be used as filter
   * @return a list of elements named with the given string
   */
  public static List<EObject> filterByName(List<EObject> list, String name) {
    List<EObject> filteredList = new ArrayList<EObject>();

    for (EObject obj : list) {
        if ((obj instanceof NamedElement) && (((NamedElement)obj).getName().equals(name))) {
          filteredList.add(obj);
        }
    }

    return filteredList;
  }

  /**
   * Returns whether at least one element of elements is inside list into
   */
  public static <A> boolean containsAny(Collection<A> elements, Collection<A> into) {
    for (A o : elements) {
      if (into.contains(o)) {
        return true;
      }
    }
    return false;
  }

  public static <A> String toString(Collection<A> objects, String separator) {
    String result = "";
    Iterator<A> iterator = objects.iterator();
      while (iterator.hasNext()) {
        A string = iterator.next();
        result = result + string;
        if (iterator.hasNext()) {
          result = result + separator;
        }
      }
    return result;
  }
  
  /**
   * Retrieves from the given list the elements named with the given string
   *
   * @param list list of interfaces
   * @param name string to be used as filter
   * @return a list of interfaces named with the given string
   */
  public static List<Interface> filterInterfacesByName(List<Interface> list, String name) {
    List<Interface> filteredList = new ArrayList<Interface>();

    for (Interface itf : list) {
        if (itf.getName().equals(name)) {
          filteredList.add(itf);
        }
    }

    return filteredList;
  }

  /**
   * Retrieves from the given list the elements named with the given string
   *
   * @param list list of components
   * @param name string to be used as filter
   * @return a list of components named with the given string
   */
  public static List<Component> filterComponentsByName(List<Component> list, String name) {
    List<Component> filteredList = new ArrayList<Component>();

    for (Component cpnt : list) {
        if (cpnt.getName().equals(name)) {
          filteredList.add(cpnt);
        }
    }

    return filteredList;
  }

  /**
   * 
   * @param list1
   * @param list2
   * @return
   */
  public static List<CapellaElement> substract(List<CapellaElement> list1, List<CapellaElement> list2) {
    List<CapellaElement> substractedList = new ArrayList<CapellaElement>();

    for (CapellaElement pc : list1) {
      if (!list2.contains(pc)) {
        substractedList.add(pc);
      }
    }

    return substractedList;
  }

  /**
   * 
   * @param list
   * @return
   */
  public static List<SequenceMessage> reverse(List<SequenceMessage> list) {
    List<SequenceMessage> reversedList = new ArrayList<SequenceMessage>();

    ListIterator<SequenceMessage> iterator = list.listIterator(list.size());
    while (iterator.hasPrevious()) {
      reversedList.add(iterator.previous());
    }

    return reversedList;
  }

  /**
   * Removes the duplicate entries in the list
   * 
   * @param list the list with duplicate entries
   * @return list with no duplicates
   */
  static public List<EObject> removeDuplicates(List<EObject> list) {
    Set<EObject> set = new HashSet<EObject>(list);
    list.clear();
    list.addAll(set);
    return list;
  }
  
  /**
   * Gives the intersection between two lists and gives the common elements
   * @param firstList
   * @param secondList
   * @return intersection list
   */
  static public List<Object> getIntersectionList(List<?> firstList, List<?> secondList) {
    List<Object> intersectionList = new ArrayList<Object>(1);
    for(Object element : firstList) {
      if(secondList.contains(element))
        intersectionList.add(element);
    }
    return intersectionList;
  }
}
