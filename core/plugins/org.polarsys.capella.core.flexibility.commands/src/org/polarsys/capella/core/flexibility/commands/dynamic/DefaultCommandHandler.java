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
package org.polarsys.capella.core.flexibility.commands.dynamic;

import java.lang.annotation.Annotation;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;

import org.polarsys.capella.core.flexibility.commands.actions.DefaultAction;
import org.polarsys.capella.core.flexibility.commands.dynamic.tools.annotations.Command;
import org.polarsys.capella.core.flexibility.commands.dynamic.tools.annotations.CommandImage;

/**
 */
public class DefaultCommandHandler extends DefaultAction {

  /**
   * @param shell_p
   * @param selectionProvider_p
   */
  public DefaultCommandHandler(Shell shell_p, ISelectionProvider selectionProvider_p) {
    super(shell_p, selectionProvider_p);
  }

  public String getClassAnnotationValue(Class classType, Class annotationType, String attributeName) {
    String value = null;

    Annotation annotation = classType.getAnnotation(annotationType);
    if (annotation != null) {
      try {
        value = (String) annotation.annotationType().getMethod(attributeName).invoke(annotation);
      } catch (Exception ex) {
      }
    }

    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText() {
    String value = getClassAnnotationValue(getClass(), Command.class, Command.COMMAND__NAME);
    if (value == null) {
      value = getId();
    }
    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getCategory() {
    String value = getClassAnnotationValue(getClass(), Command.class, Command.COMMAND__CATEGORY);
    if (value == null) {
      value = getCategory();
    }
    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription() {
    String value = getClassAnnotationValue(getClass(), Command.class, Command.COMMAND__DESCRIPTION);
    if (value == null) {
      value = getId();
    }
    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getId() {
    String value = getClassAnnotationValue(getClass(), Command.class, Command.COMMAND__ID);
    if ((value == null) || value.equals(Command.COMMAND__ID_DEFAULT_VALUE)) {
      value = getClass().getCanonicalName();
    }
    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getIconFile() {
    String value = getClassAnnotationValue(getClass(), CommandImage.class, CommandImage.COMMAND_IMAGE__VALUE);
    return value;
  }

}
