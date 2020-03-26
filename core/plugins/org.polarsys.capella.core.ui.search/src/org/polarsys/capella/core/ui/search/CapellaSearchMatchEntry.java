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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.search.ui.text.Match;
import org.polarsys.capella.common.helpers.TransactionHelper;

/**
 * Entity responsible of model result hierarchical organization.
 */
public class CapellaSearchMatchEntry extends Match {

  private String text;
  
  private IProject project;
  
  protected Object attribute;
  
  private List<CapellaSearchMatchEntryLine> entryLines;

  public CapellaSearchMatchEntry(Object source, String text,
      IProject project) {
    super(source, -1, -1);
    this.project = project;
    this.text = text;
    this.entryLines = new ArrayList<>();
  }

  public CapellaSearchMatchEntry(Object source, String text, IProject project, Object attribute) {
    this(source, text, project);
    this.attribute = attribute;
  }

  public String getText() {
    return text;
  }
  
  public void setText(String _text) {
    text = _text;
  }
  
  public IProject getProject() {
    return project;
  }
  
  public List<CapellaSearchMatchEntryLine> getEntryLines() {
    return entryLines;
  }
  
  /**
   * 
   * @param searchPattern
   * @param replacement
   * @return true if replaced, false otherwise
   */
  public boolean replace(Pattern searchPattern, String replacement) {
    if (getEntryLines().isEmpty()) {
      String oldLine = getText();
      String newContent = searchPattern.matcher(oldLine).replaceAll(replacement);
      if (attribute != null) {
        Object element = getElement();
        TransactionalEditingDomain domain = TransactionHelper.getEditingDomain((EObject) element);
        Command setCommand = SetCommand.create(domain, element, attribute, newContent);
        domain.getCommandStack().execute(setCommand);
        setText(newContent);
        return true;
      }
    } else {
      boolean replaced = false;
      for (CapellaSearchMatchEntryLine line : getEntryLines()) {
        replaced |= line.replace(searchPattern, replacement);
      }
      if (replaced) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getElement() == null) ? 0 : getElement().hashCode());
    return result;
  }

  public Object getAttribute() {
    return attribute;
  }

   @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final CapellaSearchMatchEntry other = (CapellaSearchMatchEntry) obj;
    if (getElement() == null) {
      if (other.getElement() != null)
        return false;
    } else if (!getElement().equals(other.getElement()))
      return false;
    if (getAttribute() == null) {
      if (other.getAttribute() != null)
        return false;
    } else if (!getAttribute().equals(other.getAttribute()))
      return false;
    if (getText() == null) {
      if (other.getText() != null)
        return false;
    } else if (!getText().equals(other.getText()))
      return false;
    if (getProject() == null) {
      if (other.getProject() != null)
        return false;
    } else if (!getProject().equals(other.getProject()))
      return false;
    return true;
  }
}
