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
package org.polarsys.capella.common.re.re2rpl.create.properties;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IModifiedProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.common.re.properties.AbstractReProperty;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class InitialTargetProperty extends AbstractReProperty implements IEditableProperty, ICompoundProperty, IModifiedProperty {

  private static CatalogElement _rootElement = null;

  protected static String TARGET = "RPL";

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context_p) {
    IContext context = (IContext) context_p.getSource();

    CatalogElement rootElement = (CatalogElement) context.get(TARGET);

    if (rootElement == null) {
      CatalogElement source =
          (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE));

      if (source != null) {

        Collection<CatalogElement> selectedElements = ReplicableElementHandlerHelper.getInstance(context).getIndirectlySelectedReplicableElements(context);

        for (CatalogElement element : selectedElements) {
          if (!source.equals(element)) {
            context.put(TARGET, element);
            return element;
          }
        }

        CatalogElement element = ReplicableElementHandlerHelper.getInstance(context).createReplica();

        if (element.eContainer() == null) {
          EObject tsource = source;
          Collection selection = (Collection) context.get(ITransitionConstants.TRANSITION_SOURCES);
          if (!selection.isEmpty()) {
            tsource = (EObject) selection.iterator().next();
          }
          CatalogElementPkg pkg = ReplicableElementHandlerHelper.getInstance(context).getRootPackage(tsource);
          pkg.getOwnedElements().add(element);
        }

        context.put(TARGET, element);
        rootElement = element;
      }
    }

    return rootElement;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getType() {
    return CatalogElement.class;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object toType(Object value_p, IPropertyContext context_p) {
    IContext context = (IContext) context_p.getSource();
    return value_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context_p) {
    IContext context = (IContext) context_p.getSource();
    CatalogElement source =
        (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE));

    CatalogElement replica =
        (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));

    if ((replica != null) && (replica.getOrigin() == null)) {
      replica.setOrigin(source);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue_p, IPropertyContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String[] getRelatedProperties() {
    return new String[] { IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property_p, IPropertyContext context_p) {
    // Nothing here

    if (property_p.getId().equals(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE)) {
      IContext context = (IContext) context_p.getSource();

      CatalogElement element = (CatalogElement) context.get(TARGET);
      if (element != null) {
        CatalogElement source = (CatalogElement) context_p.getCurrentValue(property_p);
        if (!element.equals(source) && (element.getOrigin() == null)) {
          element.setOrigin(source);
        }
      }
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isModified(IPropertyContext context_p) {
    return true;
  }
}
