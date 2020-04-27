/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.flexibility.wizards.ui.tabbed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;
import org.polarsys.capella.common.flexibility.wizards.group.renderer.DefaultGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.group.renderer.FlatGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.policy.AbstractRendererPolicy;
import org.polarsys.capella.common.flexibility.wizards.policy.IPolicifiedRendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.services.helper.FormHelper;

public class PropertiesSection extends AbstractPropertySection {
  IPropertyContext propertyContext;
  IRendererContext rendererContext;
  IPropertyGroup group;

  public PropertiesSection(IPropertyContext context, IRendererContext renderers, IPropertyGroup group) {
    init(context, renderers, group);
  }

  protected PropertiesSection() {
  }

  protected void init(IPropertyContext context, IRendererContext renderers, IPropertyGroup group) {
    this.propertyContext = context;
    this.group = group;
    this.rendererContext = renderers;
    initRendererContext(rendererContext);
  }

  /**
   * 
   */
  protected void initRendererContext(final IRendererContext rendererContext) {

    if (rendererContext instanceof IPolicifiedRendererContext) {
      ((IPolicifiedRendererContext) rendererContext).addRendererPolicy(new AbstractRendererPolicy() {

        @Override
        public boolean match(IPropertyGroup group) {
          return true;
        }

        @Override
        public IGroupRenderer createRenderer(IPropertyGroup group) {
          // Sub groups should not use this renderer !
          for (IPropertyGroup grp : rendererContext.getPropertyContext().getProperties().getGroups(IPropertyGroup.EMPTY)) {
            if (grp.getId().equals(group.getParentId())) {
              return new FlatGroupRenderer();
            }
          }
          return new DefaultGroupRenderer() {
            @Override
            protected boolean isDisplayLabel(IPropertyGroup group) {
              return true;
            }

            @Override
            protected String getGroupName(IPropertyGroup group) {
              return "";
            }
          };
        }

      });
    }
  }

  protected void write(final IPropertyContext context) {
    if ((context != null) && context.isModified()) {
      AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
        @Override
        public String getName() {
          return "Model edition";
        }

        public void run() {
          context.writeAll();
        }
      };
      TransactionHelper.getExecutionManager((Collection) context.getSourceAsList()).execute(cmd);
    }
  }

  /**
   * @param selection
   */
  @SuppressWarnings("unchecked")
  protected Object getSource(ISelection selection) {
    Collection<Object> objects = new ArrayList<Object>();
    Iterator<Object> e = ((IStructuredSelection) selection).iterator();
    while (e.hasNext()) {
      objects.add(e.next());
    }
    if (objects.size() == 1) {
      return objects.iterator().next();
    }
    return objects;
  }

  protected void setContext(ISelection selection) {
    propertyContext.setSource(getSource(selection));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setInput(final IWorkbenchPart part, final ISelection selection) {
    write(propertyContext);
    propertyContext.setSource(getSource(selection));
    super.setInput(part, selection);
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
    write(propertyContext);
    for (IProperty property : propertyContext.getProperties().getAllItems()) {
      IRenderer renderer = rendererContext.getRenderer(property);
      if (renderer != null) {
        renderer.dispose(rendererContext);
      }
    }
    super.dispose();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void aboutToBeHidden() {
    write(propertyContext);
    super.aboutToBeHidden();
  }

  /**
   * @return
   */
  protected ILabelProvider getLabelProvider() {
    return rendererContext.getLabelProvider();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    // feed the group renderers what they expect..
    Composite comp = new Composite(parent, SWT.NONE);
    GridLayout gl = new GridLayout();
    gl.marginWidth=0;
    gl.marginHeight=0;
    comp.setLayout(gl);

    IGroupRenderer groupRenderer = rendererContext.getRenderer(group);
    if (groupRenderer != null) {
      groupRenderer.render(comp, rendererContext);
    }
    
    if (aTabbedPropertySheetPage != null) {
      FormHelper.adaptBackgroundColor((Control) comp, aTabbedPropertySheetPage.getWidgetFactory().getColors().getBackground(), false);
    }

    comp.pack();
  }

}
