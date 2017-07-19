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

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.ui.tools.api.editor.DTableEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import ms.configuration.services.cs.CsConfigurationServices;

public class ConfigurationContentFilter extends CompoundContributionItem implements IWorkbenchContribution {

  private IPartService partService;

  private IContributionItem makeItem(final String text, final Predicate<DTable> checked,
      final Function<Boolean, Object> action) {

    final DTableEditor editor = (DTableEditor) partService.getActivePart();
    final DTable table = (DTable) (((SessionEditorInput) editor.getEditorInput())).getInput();
    final TransactionalEditingDomain editingDomain = (TransactionalEditingDomain) editor.getEditingDomain();

    return new ContributionItem() {

      @Override
      public void fill(Menu menu, int index) {
        final MenuItem item = new MenuItem(menu, SWT.CHECK);
        item.setText(text);
        item.setSelection(checked.apply(table));
        item.addSelectionListener(new SelectionListener() {
          @Override
          public void widgetSelected(SelectionEvent e) {
            editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
              @Override
              protected void doExecute() {
                action.apply(item.getSelection());
              }
            });
            new org.eclipse.sirius.table.ui.tools.internal.editor.action.RefreshAction((AbstractDTableEditor) editor)
                .run();
          }

          @Override
          public void widgetDefaultSelected(SelectionEvent e) {
          }
        });
      }
    };
  }

  @Override
  public void initialize(IServiceLocator serviceLocator) {
    partService = serviceLocator.getService(IPartService.class);
  }

  @Override
  protected IContributionItem[] getContributionItems() {

    final DTableEditor editor = (DTableEditor) partService.getActivePart();
    final DTable table = (DTable) (((SessionEditorInput) editor.getEditorInput())).getInput();

    IContributionItem[] items = new IContributionItem[5];

    items[0] = makeItem(Messages.ConfigurationContentFilter_showPorts, new Predicate<DTable>() {
      @Override
      public boolean apply(DTable t) {
        return CsConfigurationServices.isShowPorts(t);
      }
    }, new Function<Boolean, Object>() {
      @Override
      public Object apply(Boolean b) {
        CsConfigurationServices.setShowPorts(table, b);
        return null;
      }
    });

    items[1] = makeItem(Messages.ConfigurationContentFilter_showFunctions, new Predicate<DTable>() {
      @Override
      public boolean apply(DTable t) {
        return CsConfigurationServices.isShowFunctions(t);
      }
    }, new Function<Boolean, Object>() {
      @Override
      public Object apply(Boolean b) {
        CsConfigurationServices.setShowFunctions(table, b);
        return null;
      }
    });

    items[2] = makeItem(Messages.ConfigurationContentFilter_showFunctionalChains, new Predicate<DTable>() {
      @Override
      public boolean apply(DTable t) {
        return CsConfigurationServices.isShowFunctionalChains(t);
      }
    }, new Function<Boolean, Object>() {
      @Override
      public Object apply(Boolean b) {
        CsConfigurationServices.setShowFunctionalChains(table, b);
        return null;
      }
    });

    items[3] = makeItem(Messages.ConfigurationContentFilter_showComponents, new Predicate<DTable>() {
      @Override
      public boolean apply(DTable t) {
        return CsConfigurationServices.isShowComponents(t);
      }
    }, new Function<Boolean, Object>() {
      @Override
      public Object apply(Boolean b) {
        CsConfigurationServices.setShowComponents(table, b);
        return null;
      }
    });

    items[4] = makeItem(Messages.ConfigurationContentFilter_showScenarios, new Predicate<DTable>() {
      @Override
      public boolean apply(DTable t) {
        return CsConfigurationServices.isShowScenarios(t);
      }
    }, new Function<Boolean, Object>(){
      @Override
      public Object apply(Boolean b) {
        CsConfigurationServices.setShowScenarios(table, b);
        return null;
      }
    });

    return items;

  }

}
