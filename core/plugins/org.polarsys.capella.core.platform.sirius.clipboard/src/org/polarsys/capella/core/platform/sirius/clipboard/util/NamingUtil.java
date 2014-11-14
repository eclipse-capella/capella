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
	 * @param elt_p a non-null element
	 * @return a potentially null string
	 */
	public static String getName(EObject elt_p) {
		String result = null;
		if (elt_p instanceof AbstractNamedElement)
		  result = ((AbstractNamedElement)elt_p).getName();
		return result;
	}
	
  /**
   * Add a suffix to the element's name, if applicable.
   * @param elt_p a non-null element
   * @param suffix_p a potentially null string
   * @return whether the operation succeeded or not
   */
	public static boolean suffixName(EObject elt_p, String suffix_p) {
    boolean result = false;
    if (null != suffix_p && elt_p instanceof AbstractNamedElement) {
      AbstractNamedElement namedElement = (AbstractNamedElement)elt_p;
      String name = namedElement.getName();
      if (name != null && name.length() > 0) {
        String newName = buildString(name, suffix_p);
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
  public static void suffixNameDeep(EObject elt_p, final String suffix_p) {
    suffixName(elt_p, suffix_p);
    TreeIterator<EObject> it = elt_p.eAllContents();
    while(it.hasNext())
      suffixName(it.next(), suffix_p);
  }

  /**
   * Generalization of suffixNameDeep.
   */
  public static void suffixNamesDeep(Iterable<? extends EObject> elts_p,
      final String suffix_p) {
    for (EObject current : elts_p) suffixNameDeep(current, suffix_p);
  }

  /**
 * Build a suffix using an integer which is such that it yields a name
 * which is unique (not already used) among the given elements.
 * 
 * @param peers_p the elements whose names are considered for uniqueness
 * @param baseName_p the base name for the suffix, null being equivalent to ""
 * @return the suffix
 */
  public static String getSuffixForUniqueName(
      Iterable<? extends EObject> peers_p, String baseName_p) {
    assert peers_p != null;
    int index = getIndexForUniqueNameSuffix(peers_p, baseName_p);
    final String suffixProposal = getSuffixFromNumber(index);
    return suffixProposal;
  }
  
  /**
   * Generalization of getSuffixForUniqueName to several names and sets of elements.
   */
  public static String getSuffixForUniqueNames(
      List<? extends Iterable<? extends EObject>> peersList_p, List<String> baseNames_p) {
    assert peersList_p != null && baseNames_p != null;
    assert peersList_p.size() == baseNames_p.size();
    int index = 1;
    // Iterate over all cases and take the max proposed index for a suffix
    for (int i=0; i<baseNames_p.size(); i++) {
      String currentName = baseNames_p.get(i);
      if (currentName != null) {
        int indexProposal =
          getIndexForUniqueNameSuffix(peersList_p.get(i), currentName);
        index = Math.max(index, indexProposal);
      }
    }
    String suffixProposal = getSuffixFromNumber(index);
    return suffixProposal;
  }

  private static int getIndexForUniqueNameSuffix(
      Iterable<? extends EObject> peers_p, String baseName_p) {
    assert peers_p != null;
    final String baseName = (null == baseName_p)? "": baseName_p; //$NON-NLS-1$
    // Gather names which are already used
    Set<String> names = new HashSet<String>();
    for(EObject eo : peers_p) {
      String current = getName(eo);
      if (null != current)
        names.add(current);
    }
    // Look for first suffix yielding a unique name based on i
    String nameProposal;
    int i = 0;
    do {
      i++;
      nameProposal = applySuffixNumberOn(baseName, i);
    } while(names.contains(nameProposal) && i < Integer.MAX_VALUE);
    return i;
  }
  
  private static String applySuffixNumberOn(String base_p, int number_p) {
    return buildString(base_p, getSuffixFromNumber(number_p));
  }
  
  private static String getSuffixFromNumber(int number_p) {
    return buildString(
        DEFAULT_SUFFIX_START, new Integer(number_p));
  }
  
  /**
   * Concatenate the String representation of an arbitrary set of objects
   */
  public static String buildString(Object... objects_p) {
    StringBuilder builder = new StringBuilder();
    for(Object object : objects_p)
      builder.append(null == object? "": object.toString()); //$NON-NLS-1$
    return builder.toString();
  }
  
}
