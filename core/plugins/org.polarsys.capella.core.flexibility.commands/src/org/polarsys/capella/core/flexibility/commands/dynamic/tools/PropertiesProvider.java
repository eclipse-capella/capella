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
package org.polarsys.capella.core.flexibility.commands.dynamic.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;

import org.polarsys.capella.core.flexibility.commands.actions.DefaultAction;
import org.polarsys.capella.core.flexibility.commands.dynamic.IActionsProvider;
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;

/**
 *
 */
public class PropertiesProvider implements IActionsProvider {

  /**
   * @see org.polarsys.capella.core.flexibility.commands.dynamic.IActionsProvider#getActions()
   */
  public Collection<DefaultAction> getActions(Shell shell_p, ISelectionProvider selectionProvider_p) {
    List<DefaultAction> list = new ArrayList<DefaultAction>();


    list.add(new PropertiesAccessor2(shell_p, selectionProvider_p));


    return list;
  }

  private class CompoundWeightProperty extends AbstractProperty implements ICompoundProperty {

    /**
     * {@inheritDoc}
     */
    public Object getValue(IPropertyContext context_p) {

      Object object = context_p.getCurrentValue(context_p.getProperties().getProperty("weight"));
      if (object instanceof Integer) {
        return ((Integer) object) * 2;
      }
      return null;
    }

    /**
     * {@inheritDoc}
     */
    public IStatus validate(Object newValue_p, IPropertyContext context_p) {

      Object object = context_p.getCurrentValue(context_p.getProperties().getProperty("weight"));
      if (!(object instanceof Integer)) {
        return new Status(IStatus.ERROR, "hop", "weight should be an integer value");
      }

      Object objectMax = context_p.getCurrentValue(context_p.getProperties().getProperty("maxweight"));
      if (!(objectMax instanceof Integer)) {
        return new Status(IStatus.ERROR, "hop", "weightmax should be an integer value");
      }

      if (!(newValue_p instanceof Integer)) {
        return new Status(IStatus.ERROR, "hop", "weightdouble should be an integer value");
      }

      if (((Integer) newValue_p).intValue() > ((Integer) objectMax).intValue()) {
        return new Status(IStatus.ERROR, "hop", "Double is not lower than max weight");
      }

      return Status.OK_STATUS;
    }

    /**
     * {@inheritDoc}
     */
    public Object getType() {
      return Integer.class;
    }

    /**
     * {@inheritDoc}
     */
    public Object toType(Object value_p, IPropertyContext context_p) {
      if (value_p instanceof String) {
        return Integer.parseInt((String) value_p);
      }
      return value_p;
    }

    /**
     * {@inheritDoc}
     */
    public String[] getRelatedProperties() {
      return new String[] { "weight", "maxweight" };
    }

    /**
     * {@inheritDoc}
     */
    public void updatedValue(IProperty property_p, IPropertyContext context_p) {

    }

  }

  private class AttributeProperty extends AbstractProperty {

    /**
     * {@inheritDoc}
     */
    public Object getValue(IPropertyContext context_p) {
      return "";
    }

    /**
     * {@inheritDoc}
     */
    public IStatus validate(Object newValue_p, IPropertyContext context_p) {

      return Status.OK_STATUS;
    }

    /**
     * {@inheritDoc}
     */
    public Object getType() {
      return Integer.class;
    }

    /**
     * {@inheritDoc}
     */
    public Object toType(Object value_p, IPropertyContext context_p) {
      return value_p;
    }

  }

  @SuppressWarnings("nls")
  private class PropertiesAccessor2 extends DefaultAction {

    public PropertiesAccessor2(Shell shell_p, ISelectionProvider selectionProvider_p) {
      super(shell_p, selectionProvider_p);
    }

    @Override
    public boolean isSelectionCompatible() {
      return getSelection(EObject.class).size() > 0;
    }

    @Override
    protected String getIconFile() {
      return "process.gif";
    }

    @Override
    public String getText() {
      return "Properties";
    }

    @Override
    public String getCategory() {
      return "org.polarsys.capella.core.flexibility.tools";
    }

    @Override
    public void execute() {
      final EObject first = getSelectedEObjects().iterator().next();

    }
  }
}
