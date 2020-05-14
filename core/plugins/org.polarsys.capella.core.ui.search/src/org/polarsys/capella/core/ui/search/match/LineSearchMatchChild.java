package org.polarsys.capella.core.ui.search.match;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.helpers.TransactionHelper;

public class LineSearchMatchChild extends SearchMatchChild {

  private int lineNumber;

  public LineSearchMatchChild(Object source, String text, IProject project, SearchMatch parent, int lineNumber) {
    super(source, text, project, parent);
    this.lineNumber = lineNumber;

  }

  @Override
  public boolean replace(Pattern searchPattern, String replacement) {

    Object attribute = getParent().getAttribute();
    Object object = getParent().getElement();

    if (attribute instanceof EAttribute && object instanceof EObject) {
      EAttribute eAttribute = (EAttribute) attribute;
      EObject eObject = (EObject) object;

      Object fullValue = eObject.eGet(eAttribute);
      if (fullValue instanceof String) {
        String fullText = (String) fullValue;
        String[] fullTextLines = fullText.split("\n");

        if (lineNumber < fullTextLines.length) {
          String lineToModify = fullTextLines[lineNumber];
          String modifiedLine = searchPattern.matcher(lineToModify).replaceAll(replacement);
          fullTextLines[lineNumber] = modifiedLine;

          String modifiedFullText = Arrays.stream(fullTextLines).collect(Collectors.joining("\n"));

          TransactionalEditingDomain domain = TransactionHelper.getEditingDomain(eObject);
          Command setCommand = SetCommand.create(domain, eObject, eAttribute, modifiedFullText);
          domain.getCommandStack().execute(setCommand);

          setOriginalText(modifiedLine);
          return true;
        }
      }
    }

    return false;

  }

  @Override
  protected String computeDisplayedText(String originalText) {
    return lineNumber + ": " + super.computeDisplayedText(originalText);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + Objects.hash(lineNumber);
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
    if (!(obj instanceof LineSearchMatchChild)) {
      return false;
    }
    LineSearchMatchChild other = (LineSearchMatchChild) obj;
    return lineNumber == other.lineNumber;
  }

}