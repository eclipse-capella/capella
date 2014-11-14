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
package org.polarsys.capella.core.ui.search;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.polarsys.kitalpha.emde.model.Element;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.re.ReNamedElement;

/**
 */
public class CommandFactory {

  public static ICommand createReplaceAllInNameCommand(final Set<Element> matchingElements_p, final String findString_p, final String replaceString_p,
      final boolean caseSensitive, final boolean ignoreWildCards, boolean wholeWord, boolean regExSearch) {
    if (matchingElements_p.isEmpty()) {
      return null;
    }
    return new AbstractReadWriteCommand() {

      @Override
      public void run() {
        for (final Element elt : matchingElements_p) {
          String label = null;
          if (elt instanceof AbstractNamedElement) {
            label = ((AbstractNamedElement) elt).getName();
          }
          if (elt instanceof ReNamedElement) {
            label = ((ReNamedElement) elt).getName();
          }
          if (null == label) {
            return;
          }
          String wildCardFindString = findString_p;

          if (!ignoreWildCards) {
            wildCardFindString = wildCardsToRegexForName(findString_p);
          }

          final String newName = replaceAll(label, wildCardFindString, replaceString_p, caseSensitive, ignoreWildCards);
          if (elt instanceof AbstractNamedElement) {
            ((AbstractNamedElement) elt).setName(newName);
          }
          if (elt instanceof ReNamedElement) {
            ((ReNamedElement) elt).setName(newName);
          }

        }

      }

    };
  }

  public static ICommand createReplaceAllInSummary(final Set<Element> matchingElements_p, final String findString_p, final String replaceString_p,
      boolean caseSensitive, final boolean ignoreWildCards, boolean wholeWord, boolean regExSearch) {
    if (matchingElements_p.isEmpty()) {
      return null;
    }
    return new AbstractReadWriteCommand() {

      @Override
      public void run() {
        for (final Element elt : matchingElements_p) {
          if (elt instanceof CapellaElement) {
            CapellaElement capellaElt = (CapellaElement) elt;
            String label = capellaElt.getSummary();
            String wildCardFindString = findString_p;
            if (!ignoreWildCards) {
              wildCardFindString = wildCardsToRegex(findString_p);
            }
            final String newSummary = label.replaceAll(wildCardFindString, replaceString_p);

            capellaElt.setSummary(newSummary);

          }
        }
      }
    };
  }

  public static ICommand createReplaceAllInDescription(final Set<Element> matchingElements_p, final String findString_p, final String replaceString_p,
      boolean caseSensitive, final boolean ignoreWildCards, boolean wholeWord, boolean regExSearch) {
    if (matchingElements_p.isEmpty()) {
      return null;
    }
    return new AbstractReadWriteCommand() {

      @Override
      public void run() {
        for (final Element elt : matchingElements_p) {
          if (elt instanceof CapellaElement) {
            String wildCardFindString = findString_p;

            if (!ignoreWildCards) {
              wildCardFindString = wildCardsToRegex(findString_p);
            }
            ReplaceInDescriptionSaxParser parser = new ReplaceInDescriptionSaxParser();
            parser.replace(wildCardFindString, replaceString_p, elt);

          }
        }
      }
    };
  }

  static String replaceAll(String name_p, String regex_p, String replacement_p, boolean caseSensitive_p, boolean ignoreWildCards_p) {
    Pattern p = PatternFactory.createPattern(regex_p, !caseSensitive_p, ignoreWildCards_p);
    Matcher m = p.matcher(name_p);
    StringBuffer sb = new StringBuffer();

    while (m.find()) {
      m.appendReplacement(sb, replacement_p);
    }
    m.appendTail(sb);
    return sb.toString();
  }

  public static String wildCardsToRegex(final String findString_p) {
    return findString_p.replace("?", ".?").replace("*", ".*?\\b"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
  }

  public static String wildCardsToRegexForName(final String findString_p) {
    return findString_p.replace("?", ".?").replace("*", ".*"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
  }

}
