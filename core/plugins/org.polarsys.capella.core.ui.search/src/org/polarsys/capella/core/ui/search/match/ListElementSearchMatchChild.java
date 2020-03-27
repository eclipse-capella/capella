/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search.match;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;

public class ListElementSearchMatchChild extends SearchMatchChild {

  private int index;

  public ListElementSearchMatchChild(Object source, String text, IProject project, SearchMatch parent, int index) {
    super(source, text, project, parent);
    this.index = index;
  }

  @Override
  public boolean replace(Pattern searchPattern, String replacement) {
    String oldLine = getOriginalText();
    String newContent = searchPattern.matcher(oldLine).replaceAll(replacement);
    if (getParent().getAttribute() instanceof EAttribute && getParent().getElement() instanceof EObject) {
      EAttribute matchEntryAttribute = (EAttribute) getParent().getAttribute();
      EObject matchEntryElement = (EObject) getParent().getElement();
      if (matchEntryElement.eGet(matchEntryAttribute) instanceof List) {
        TransactionHelper.getExecutionManager(matchEntryElement).execute(new AbstractReadWriteCommand() {
          @SuppressWarnings("unchecked")
          @Override
          public void run() {
            @SuppressWarnings("rawtypes")
            List lineMatches = (List) matchEntryElement.eGet(matchEntryAttribute);
            lineMatches.remove(index);
            lineMatches.add(index, newContent);
          }
        });
        setOriginalText(newContent);
        return true;
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + Objects.hash(index);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (!(obj instanceof ListElementSearchMatchChild)) {
      return false;
    }
    ListElementSearchMatchChild other = (ListElementSearchMatchChild) obj;
    return index == other.index;
  }

}
