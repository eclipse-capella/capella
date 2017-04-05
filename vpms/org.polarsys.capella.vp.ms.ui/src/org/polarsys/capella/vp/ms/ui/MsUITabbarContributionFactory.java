/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.vp.ms.ui;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.AbstractMenuContributionItem;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions.SiriusTabbarExtensionContributionFactory;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions.expressions.DDiagramTabbarExpression;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;
import org.polarsys.capella.vp.ms.provider.MsEditPlugin;

import ms.configuration.services.cs.CsConfigurationServices;

public class MsUITabbarContributionFactory extends SiriusTabbarExtensionContributionFactory {

  @Override
  public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {
    super.createContributionItems(serviceLocator, additions);

    TabbarContribution tabbarContribution = new TabbarContribution();
    ContributionItem filterItem = tabbarContribution.createContributionItem(getManager());
    tabbarContribution.setPart(getPart());

    additions.addContributionItem(filterItem, new DDiagramTabbarExpression() {

      @Override
      protected boolean isVisible(IStructuredSelection selection) {
        return super.isVisible(selection) && configLayerActive(selection);
      }

      private boolean configLayerActive(IStructuredSelection selection) {

        Object o = selection.getFirstElement();
        if (o instanceof DiagramEditPart) {
          DiagramEditPart part = (DiagramEditPart) o;
          if (part.getModel() instanceof Diagram && ((Diagram) part.getModel()).getElement() instanceof DDiagram) {
            DDiagram diagram = (DDiagram) ((Diagram) part.getModel()).getElement();
            if (CsConfigurationServices.isConfigurationsLayerActive(diagram)) {
              return true;
            }
          }
        }

        return false;
      }

    });
  }

  static class TabbarContribution extends AbstractMenuContributionItem {

    @Override
    protected Image getMenuImage() {
      return ExtendedImageRegistry.getInstance().getImage(MsEditPlugin.INSTANCE.getImage("full/obj16/CSConfiguration"));
    }

    @Override
    protected String getLabel() {
      return "Configurations";
    }

    @Override
    protected void menuShow(IMenuManager manager) {

      manager.add(new ContributionItem() {

        @Override
        public void fill(Menu menu, int index) {

          final MenuItem item = new MenuItem(menu, SWT.CHECK);
          item.setText("Show child configuration relationships");
          item.setSelection(CsConfigurationServices.isShowChildConfigurationRelationships(diagram));

          item.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
              TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(diagram);
              domain.getCommandStack().execute(new RecordingCommand(domain) {

                @Override
                public String getLabel() {
                  return item.getText();
                }

                @Override
                protected void doExecute() {
                  CsConfigurationServices.setShowChildConfigurationRelationships(diagram, item.getSelection());
                  DialectManager.INSTANCE.refresh(diagram, new NullProgressMonitor());
                }
              });
            }
          });

        }

      });

    }
  }
}
