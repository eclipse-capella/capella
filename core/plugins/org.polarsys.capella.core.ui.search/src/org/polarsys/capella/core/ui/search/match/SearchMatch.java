/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.search.match;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.search.ui.text.Match;
import org.polarsys.capella.common.helpers.TransactionHelper;

/**
 * Entity responsible of model result hierarchical organization.
 */
public class SearchMatch extends Match {

  private static final Set<String> ATTRIBUTE_NAMES_TO_FORMAT;

  static {
    ATTRIBUTE_NAMES_TO_FORMAT = new HashSet<>();
    ATTRIBUTE_NAMES_TO_FORMAT.add("description");
    ATTRIBUTE_NAMES_TO_FORMAT.add("documentation");
  }

  /**
   * The original text as is it stored in the inputObject.
   */
  protected String originalText;

  /**
   * The display text that will be displayed in the Search Result. It can be different from the original text (for
   * example for HTML text the HTML tags can be removed).
   */
  protected String displayText;

  protected IProject project;
  protected Object attribute;
  protected List<SearchMatchChild> children;

  public SearchMatch(Object inputObject, String originalText, IProject project) {
    super(inputObject, -1, -1);
    this.project = project;
    this.originalText = originalText;
    this.children = new ArrayList<>();
  }

  public SearchMatch(Object inputObject, String originalText, IProject project, Object attribute) {
    this(inputObject, originalText, project);
    this.attribute = attribute;
  }

  /**
   * 
   * @return the original text as is it stored in the inputObject.
   */
  public String getOriginalText() {
    return originalText;
  }

  public void setOriginalText(String text) {
    this.originalText = text;
    this.displayText = computeDisplayedText(text);
  }

  /**
   * 
   * @return the display text that will be displayed in the Search Result. It can be different from the original text
   *         (for example for HTML text the HTML tags can be removed).
   */
  public String getDisplayText() {
    if (displayText == null) {
      displayText = computeDisplayedText(originalText);
    }

    return displayText;
  }

  public IProject getProject() {
    return project;
  }

  public List<SearchMatchChild> getChildren() {
    return children;
  }

  public Object getAttribute() {
    return attribute;
  }

  public boolean replace(Pattern searchPattern, String replacement) {
    return getChildren().isEmpty() ? replaceLocally(searchPattern, replacement)
        : replaceInChildren(searchPattern, replacement);
  }

  protected boolean replaceLocally(Pattern searchPattern, String replacement) {
    Object eAttribute = getAttribute();

    if (eAttribute != null) {
      Object element = getElement();

      if (element instanceof EObject) {
        String oldLine = getOriginalText();
        String newContent = searchPattern.matcher(oldLine).replaceAll(replacement);
        EObject eObject = (EObject) element;
        TransactionalEditingDomain domain = TransactionHelper.getEditingDomain(eObject);

        Command setCommand = SetCommand.create(domain, eObject, eAttribute, newContent);
        domain.getCommandStack().execute(setCommand);

        setOriginalText(newContent);
        return true;
      }
    }

    return false;
  }

  protected boolean replaceInChildren(Pattern searchPattern, String replacement) {
    boolean replaced = false;

    for (SearchMatchChild child : getChildren()) {
      replaced |= child.replace(searchPattern, replacement);
    }

    return replaced;
  }

  /**
   * Computes the displayed text. Can be by children classes to change the displayed text accordingly.
   * 
   * @param originalText
   *          the original text.
   * @return the displayed text.
   */
  protected String computeDisplayedText(String originalText) {
    if (originalText != null && shouldSanitizeHTML()) {
      return StringEscapeUtils.unescapeHtml(originalText).replaceAll("\\<[^>]*>", "").trim();
    }

    return originalText;
  }

  protected boolean shouldSanitizeHTML() {
    Object lAttribute = getAttribute();
    if (lAttribute instanceof EAttribute) {
      EAttribute eAttribute = (EAttribute) lAttribute;
      return ATTRIBUTE_NAMES_TO_FORMAT.contains(eAttribute.getName());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(attribute, children, displayText, originalText, project);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof SearchMatch)) {
      return false;
    }
    SearchMatch other = (SearchMatch) obj;
    return Objects.equals(attribute, other.attribute) && Objects.equals(children, other.children)
        && Objects.equals(displayText, other.displayText) && Objects.equals(originalText, other.originalText)
        && Objects.equals(project, other.project);
  }

}
