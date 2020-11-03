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
package org.polarsys.capella.test.framework.helpers.log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Erwan Brottier
 */
public abstract class FormatedLogger implements IFormatedLogger {

  private String indentString = "  "; // default is two white space //$NON-NLS-1$
  private int indentStep = 0;
  private boolean indentMustBeDone = false;

  protected List<ObjectPrinter> printers = new ArrayList<ObjectPrinter>();

  public FormatedLogger(List<ObjectPrinter> printers) {
    this.printers.addAll(printers);
  }

  public FormatedLogger(String indentString) {
    this.indentString = indentString;
  }

  public FormatedLogger() {
  }

  protected void print(Object object) {
    if (object instanceof String) {
      basicPrint(object);
    } else if (object instanceof ILoggable) {
      ((ILoggable) object).printInLogger(this);
    } else {
      for (ObjectPrinter printer : printers) {
        if (printer.appliesOn(object)) {
          printer.print(object, this);
          return;
        }
      }
      basicPrint(object);
    }
  }

  protected abstract void basicPrint(Object object);

  public void printException(String message, Exception exception) {
    print("!! EXCEPTION : " + message + '\n'); //$NON-NLS-1$
    printException(exception);
  }

  public void printException(Exception exception) {
    print(exception);
  }

  public void line(Object text) {
    addTextLn(text);
  }

  public void addTextLn(Object text) {
    indent();
    print(text);
    carriageReturn();
  }

  public void addText(Object text) {
    indent();
    print(text);
  }

  public void carriageReturn() {
    print("\n"); //$NON-NLS-1$
    indentMustBeDone = true;
  }

  private void indent() {
    if (indentMustBeDone) {
      for (int i = 0; i < indentStep; i++) {
        print(indentString);
      }
      indentMustBeDone = false;
    }
  }

  public void incIndent() {
    indentStep++;
  }

  public void incIndentLn() {
    incIndent();
    carriageReturn();
  }

  public void decIndent() {
    indentStep--;
  }

  public void decIndentLn() {
    decIndent();
    carriageReturn();
  }

  public void resetIndentation() {
    indentStep = 0;
  }

}
