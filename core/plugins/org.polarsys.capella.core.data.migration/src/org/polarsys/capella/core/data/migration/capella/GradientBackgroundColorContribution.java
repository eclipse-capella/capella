/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.capella;

import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;
import org.xml.sax.Attributes;

/**
 * When an user has customized a gradient container, backgroundColor doesn't have a value but use by default the foregroundColor. With Sirius 3.0, they don't
 * have migrate this behavior so the backgroundColor become white and a gradient white to foregroundColor is created instead of foregroundColor-foregroundColor
 */
public class GradientBackgroundColorContribution extends AbstractMigrationContribution {

  HashSet<FlatContainerStyle> filledBackground = new HashSet<FlatContainerStyle>();

  @Override
  public void endElement(EObject peekEObject, Attributes attribs, String uri, String localName, String name, Resource resource, MigrationContext context) {
    super.endElement(peekEObject, attribs, uri, localName, name, resource, context);

    if (isCustomBackground(peekEObject)) {
      if (name.equals("backgroundColor")) {
        filledBackground.add((FlatContainerStyle) peekEObject);
      }
    }
  }

  protected boolean isCustomBackground(EObject object) {
    if (object instanceof FlatContainerStyle) {
      if (((FlatContainerStyle) object).getCustomFeatures().contains("backgroundColor")) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void unaryMigrationExecute(EObject currentElement, MigrationContext context) {
    super.unaryMigrationExecute(currentElement, context);

    if (isCustomBackground(currentElement) && !filledBackground.contains(currentElement)) {
      ((FlatContainerStyle) currentElement).setBackgroundColor(((FlatContainerStyle) currentElement).getForegroundColor());
    }

  }

  @Override
  public void dispose(MigrationContext context) {
    super.dispose(context);

    filledBackground.clear();
  }

}
