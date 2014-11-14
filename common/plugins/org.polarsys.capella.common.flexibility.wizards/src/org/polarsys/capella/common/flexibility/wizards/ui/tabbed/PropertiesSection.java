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
package org.polarsys.capella.common.flexibility.wizards.ui.tabbed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;
import org.polarsys.capella.common.flexibility.wizards.group.renderer.DefaultGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.group.renderer.SectionGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.policy.AbstractRendererPolicy;
import org.polarsys.capella.common.flexibility.wizards.policy.IPolicifiedRendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

public class PropertiesSection extends AbstractPropertySection {
  IPropertyContext _propertyContext;
  IRendererContext _rendererContext;
  IPropertyGroup _group;

  public PropertiesSection(IPropertyContext context_p, IRendererContext renderers_p, IPropertyGroup group_p) {
    init(context_p, renderers_p, group_p);
  }

  protected PropertiesSection() {
  }

  protected void init(IPropertyContext context_p, IRendererContext renderers_p, IPropertyGroup group_p) {
    _propertyContext = context_p;
    _group = group_p;
    _rendererContext = renderers_p;
    initRendererContext(_rendererContext);
  }

  /**
   * 
   */
  protected void initRendererContext(final IRendererContext rendererContext_p) {

    if (rendererContext_p instanceof IPolicifiedRendererContext) {
      ((IPolicifiedRendererContext) rendererContext_p).addRendererPolicy(new AbstractRendererPolicy() {

        @Override
        public boolean match(IPropertyGroup group_p) {
          return true;
        }

        @Override
        public IGroupRenderer createRenderer(IPropertyGroup group_p) {
          //Sub groups should not use this renderer !
          for (IPropertyGroup group : rendererContext_p.getPropertyContext().getProperties().getGroups(IPropertyGroup.EMPTY)) {
            if (group.getId().equals(group_p.getParentId())) {
              return new SectionGroupRenderer();
            }
          }
          return new DefaultGroupRenderer() {
            @Override
            protected boolean isDisplayLabel(IPropertyGroup group_p) {
              return true;
            }

            @Override
            protected String getGroupName(IPropertyGroup group_p) {
              return "";
            }
          };
        }

      });
    }
  }

  protected void write(final IPropertyContext context_p) {
    if ((context_p != null) && context_p.isModified()) {
      MDEAdapterFactory.getExecutionManager().execute(new AbstractReadWriteCommand() {

        @Override
        public String getName() {
          return "Model edition";
        }

        public void run() {
          context_p.writeAll();
        }
      });
    }
  }

  /**
   * @param selection_p
   */
  protected Object getSource(ISelection selection_p) {
    Collection<Object> objects = new ArrayList<Object>();
    Iterator<Object> e = ((IStructuredSelection) selection_p).iterator();
    while (e.hasNext()) {
      objects.add(e.next());
    }
    if (objects.size() == 1) {
      return objects.iterator().next();
    }
    return objects;
  }

  protected void setContext(ISelection selection_p) {
    _propertyContext.setSource(getSource(selection_p));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setInput(final IWorkbenchPart part_p, final ISelection selection_p) {
    write(_propertyContext);
    _propertyContext.setSource(getSource(selection_p));
    super.setInput(part_p, selection_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void aboutToBeShown() {
    super.aboutToBeShown();
  }

  @Override
  public void dispose() {
    write(_propertyContext);
    for (IProperty property : _propertyContext.getProperties().getAllItems()) {
      IRenderer renderer = _rendererContext.getRenderer(property);
      if (renderer != null) {
        renderer.dispose(_rendererContext);
      }
    }
    super.dispose();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void aboutToBeHidden() {
    write(_propertyContext);
    super.aboutToBeHidden();
  }

  /**
   * @return
   */
  protected ILabelProvider getLabelProvider() {
    return _rendererContext.getLabelProvider();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent_p, TabbedPropertySheetPage aTabbedPropertySheetPage_p) {
    super.createControls(parent_p, aTabbedPropertySheetPage_p);
    try {
      parent_p.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
      IGroupRenderer groupRenderer = _rendererContext.getRenderer(_group);
      if (groupRenderer != null) {
        groupRenderer.render(parent_p, _rendererContext);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    parent_p.pack();
  }

}
