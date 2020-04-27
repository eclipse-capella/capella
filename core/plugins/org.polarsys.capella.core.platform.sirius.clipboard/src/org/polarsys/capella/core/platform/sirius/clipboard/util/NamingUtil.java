/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.clipboard.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;


/**
 * Utility class for managing names and suffixes
 */
public final class NamingUtil {
  
  /** The separator for name suffixes **/
  private static final String DEFAULT_SUFFIX_START = "_"; //$NON-NLS-1$

	private NamingUtil() {
	  // Forbids instantiation
	}
	
	/**
	 * Return the name of the given element, if any
	 * @param elt a non-null element
	 * @return a potentially null string
	 */
	public static String getName(EObject elt) {
		String result = null;
		if (elt instanceof AbstractNamedElement)
		  result = ((AbstractNamedElement)elt).getName();
		return result;
	}
	
  /**
   * Add a suffix to the element's name, if applicable.
   * @param elt a non-null element
   * @param suffix a potentially null string
   * @return whether the operation succeeded or not
   */
	public static boolean suffixName(EObject elt, String suffix) {
    boolean result = false;
    if (null != suffix && elt instanceof AbstractNamedElement) {
      AbstractNamedElement namedElement = (AbstractNamedElement)elt;
      String name = namedElement.getName();
      if (name != null && name.length() > 0) {
        String newName = buildString(name, suffix);
        try {
          namedElement.setName(newName);
          result = true;
        } catch (RuntimeException e) {
          // Failure
        }
      }
    }
    return result;
  }

  /**
   * Add a suffix to the element's and and all its children's names,
   * whenever applicable.
   */
  public static void suffixNameDeep(EObject elt, final String suffix) {
    suffixName(elt, suffix);
    TreeIterator<EObject> it = elt.eAllContents();
    while(it.hasNext())
      suffixName(it.next(), suffix);
  }

  /**
   * Generalization of suffixNameDeep.
   */
  public static void suffixNamesDeep(Iterable<? extends EObject> elts,
      final String suffix) {
    for (EObject current : elts) suffixNameDeep(current, suffix);
  }

  /**
 * Build a suffix using an integer which is such that it yields a name
 * which is unique (not already used) among the given elements.
 * 
 * @param peers the elements whose names are considered for uniqueness
 * @param baseName the base name for the suffix, null being equivalent to ""
 * @return the suffix
 */
  public static String getSuffixForUniqueName(
      Iterable<? extends EObject> peers, String baseName) {
    assert peers != null;
    int index = getIndexForUniqueNameSuffix(peers, baseName);
    final String suffixProposal = getSuffixFromNumber(index);
    return suffixProposal;
  }
  
  /**
   * Generalization of getSuffixForUniqueName to several names and sets of elements.
   */
  public static String getSuffixForUniqueNames(
      List<? extends Iterable<? extends EObject>> peersList, List<String> baseNames) {
    assert peersList != null && baseNames != null;
    assert peersList.size() == baseNames.size();
    int index = 1;
    // Iterate over all cases and take the max proposed index for a suffix
    for (int i=0; i<baseNames.size(); i++) {
      String currentName = baseNames.get(i);
      if (currentName != null) {
        int indexProposal =
          getIndexForUniqueNameSuffix(peersList.get(i), currentName);
        index = Math.max(index, indexProposal);
      }
    }
    String suffixProposal = getSuffixFromNumber(index);
    return suffixProposal;
  }

  private static int getIndexForUniqueNameSuffix(
      Iterable<? extends EObject> peers, String baseName) {
    assert peers != null;
    final String bn = (null == baseName)? "": baseName; //$NON-NLS-1$
    // Gather names which are already used
    Set<String> names = new HashSet<String>();
    for(EObject eo : peers) {
      String current = getName(eo);
      if (null != current)
        names.add(current);
    }
    // Look for first suffix yielding a unique name based on i
    String nameProposal;
    int i = 0;
    do {
      i++;
      nameProposal = applySuffixNumberOn(bn, i);
    } while(names.contains(nameProposal) && i < Integer.MAX_VALUE);
    return i;
  }
  
  private static String applySuffixNumberOn(String base, int number) {
    return buildString(base, getSuffixFromNumber(number));
  }
  
  private static String getSuffixFromNumber(int number) {
    return buildString(
        DEFAULT_SUFFIX_START, Integer.valueOf(number));
  }
  
  /**
   * Concatenate the String representation of an arbitrary set of objects
   */
  public static String buildString(Object... objects) {
    StringBuilder builder = new StringBuilder();
    for(Object object : objects)
      builder.append(null == object? "": object.toString()); //$NON-NLS-1$
    return builder.toString();
  }
  
}
