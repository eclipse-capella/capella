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
package org.polarsys.capella.core.ui.search;

import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;

/**
 * Represents a line match for a match entry
 */
public class CapellaSearchMatchEntryLine extends CapellaSearchMatchEntry {

  private CapellaSearchMatchEntry matchEntry;
  
  private int index;

  public CapellaSearchMatchEntryLine(Object source, String text, IProject project, CapellaSearchMatchEntry matchEntry, int index) {
    super(source, text, project);
    this.matchEntry = matchEntry;
    this.index = index;
  }

  public CapellaSearchMatchEntry getMatchEntry() {
    return matchEntry;
  }
  
  @Override
  public Object getAttribute() {
    return matchEntry.getAttribute();
  }
  
  @Override
  public boolean replace(Pattern searchPattern, String replacement) {
    String oldLine = getText();
    String newContent = searchPattern.matcher(oldLine).replaceAll(replacement);
    if (getMatchEntry().getAttribute() instanceof EAttribute && getMatchEntry().getElement() instanceof EObject) {
      EAttribute matchEntryAttribute = (EAttribute) getMatchEntry().getAttribute();
      EObject matchEntryElement = (EObject) getMatchEntry().getElement();
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
        setText(newContent);
        return true;
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((getMatchEntry() == null) ? 0 : getMatchEntry().hashCode());
    result = prime * result + this.index;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    CapellaSearchMatchEntryLine other = (CapellaSearchMatchEntryLine) obj;
    return getElement().equals(other.getElement()) && getText().equals(other.getText())
        && getProject().equals(other.getProject()) && getMatchEntry().equals(other.getMatchEntry());
  }
}
