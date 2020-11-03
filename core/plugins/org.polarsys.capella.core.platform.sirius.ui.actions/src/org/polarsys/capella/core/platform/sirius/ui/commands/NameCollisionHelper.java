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
package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.StringHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

/**
 * A helper class that can handle name collisions on the contents of a model element.
 * 
 */
public class NameCollisionHelper {

  /**
   * The pattern that should be used in capella
   */
  public static final Pattern COPYOF_PATTERN = Pattern.compile("Copy \\((\\d)*\\) of (.*)"); //$NON-NLS-1$
  
  /**
   * The pattern that's used in this NameCollisionHelper
   */
  private Pattern pattern;
  
  /**
   * A default, reusable instance.
   */
  private static NameCollisionHelper instance;
  
  /**
   * Create a new name collision helper that will use
   * the given pattern to resolve name conflicts.
   * 
   * Clients should however use the static getDefault(),
   * to assure a consistent pattern across the application.
   * 
   * @param pattern
   */
  public NameCollisionHelper(Pattern pattern){
    this.pattern = pattern; 
  }
  
  public static NameCollisionHelper getDefault(){
    if (instance == null){
      instance = new NameCollisionHelper(COPYOF_PATTERN);
    }
    return instance;
  }
  
  /**
   * Find among a list of candidates, the elements whose name conflicts 
   * with a name in an existing list of elements.
   * 
   * @param existing A list of objects, the ones that had their name 'before' the candidates even existed.
   * @param candidates A list that may contain elements that conflict with elements in the 'existing' list
   * @return A list of elements that conflict with elements in the 'existing' list. Never null.
   */
  public Collection<AbstractNamedElement> findConflictingElements(Collection<?> existing, Collection<?> candidates) {
    List<AbstractNamedElement> result = new ArrayList<AbstractNamedElement>(0);
    for (Object candidate : candidates) {
      if (candidate instanceof AbstractNamedElement) {
        if (isCollide(existing, ((AbstractNamedElement) candidate).getName())) {
          result.add((AbstractNamedElement) candidate);
        }
      }
    }
    return result;
  }
  
  /**
   * Handle naming collision for specified objects.
   * @param objects
   * @param owner
   */
  public void handleNamingCollision(Collection<? extends AbstractNamedElement> objects, EObject owner) {
    // Loop over objects to handle.
    for (AbstractNamedElement element : objects) {
      if (null != element) {
        String name = element.getName();
        // Flag to indicate if it is the first attempt.
        boolean firstAttempt = true;
        List<EObject> eContents = owner.eContents();
        while (isCollide(eContents, name)) {
          Matcher patternMatcher = pattern.matcher(name);
          if (firstAttempt) {
            name = Messages.CapellaPasteCommand_COPY_OF + name;
            firstAttempt = false;
          } else if (name.startsWith(Messages.CapellaPasteCommand_COPY_OF)) {
            name = Messages.CapellaPasteCommand_COPY_2_OF + name.substring(Messages.CapellaPasteCommand_COPY_OF.length());
          } else if (patternMatcher.matches()) {
            // patternMatcher.group(1) fails when the number is > 10
            String numberAsString =
                StringHelper.substring(ICommonConstants.EMPTY_STRING + ICommonConstants.PARENTHESIS_OPEN_CHARACTER,
                    ICommonConstants.EMPTY_STRING + ICommonConstants.PARENTHESIS_CLOSE_CHARACTER, name, false);
            int number = Integer.parseInt(numberAsString) + 1;
            name = NLS.bind(Messages.CapellaPasteCommand_COPY_MULTIPLE, new String[] { String.valueOf(number), patternMatcher.group(2) });
          }
        }
        element.setName(name);
      }
    }
  }

  /**
   * Is the given name already present in the list of elements.
   * @param modelElements a list of model elements
   * @param name
   * @return
   */
  public boolean isCollide(Collection<?> modelElements, String name) {
    for (Object object : modelElements) {
      if (object instanceof AbstractNamedElement) {
        AbstractNamedElement element = (AbstractNamedElement) object;
        if (element.getName() != null && element.getName().equals(name))
          return true;
      }
    }
    return false;
  }
  
}
