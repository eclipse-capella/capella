/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.flexibility.commands.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.flexibility.commands.Activator;
import org.polarsys.capella.core.flexibility.commands.MiscUtil;
import org.polarsys.capella.core.flexibility.commands.dynamic.tools.DefaultCategories;

/**
 */
public class DefaultAction extends Action {

  private Logger logger = null;

  Shell shell;
  ISelectionProvider selectionProvider;

  /**
   * @return the shell
   */
  public Shell getShell() {
    return shell;
  }

  public boolean isSelectionCompatible() {
    return getSelection(Object.class).size() > 0;
  }

  /**
   * @return the selectionProvider
   */
  public ISelectionProvider getSelectionProvider() {
    return selectionProvider;
  }

  @Override
  public boolean isEnabled() {
    return super.isEnabled() && isSelectionCompatible();
  }

  public static String getId(Class clazz) {
    return "org.polarsys.capella.core.flexibility.commands.dynamic." + clazz.getSimpleName().toLowerCase();
  }

  /**
   * @param shell
   * @param selectionProvider
   */
  public DefaultAction(Shell shell, ISelectionProvider selectionProvider) {
    this.selectionProvider = selectionProvider;
    this.shell = shell;
    setId(getId(getClass()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ImageDescriptor getImageDescriptor() {
    ImageDescriptor descriptor = super.getImageDescriptor();
    if (descriptor == null) {
      descriptor = Activator.getDefault().getImageDescriptor(getIconFile());
      setImageDescriptor(descriptor);
    }
    return descriptor;
  }

  protected Logger getLogger() {
    return logger;
  }

  /**
   * @return
   */
  protected String getIconFile() {
    return "process.gif";
  }

  @SuppressWarnings("unchecked")
  protected List<?> getSelection(Class<?> objectType) {
    List<Object> resultObjects = new ArrayList<>();
    // Get selected objects if any.
    if (getSelectionProvider().getSelection() instanceof IStructuredSelection) {
      IStructuredSelection selectedObjects = (IStructuredSelection) getSelectionProvider().getSelection();
      // If not empty, get the first selected object.
      if (!selectedObjects.isEmpty()) {
        Iterator<Object> i = selectedObjects.iterator();
        while (i.hasNext()) {
          Object element = i.next();
          if ((objectType == null) || objectType.isInstance(element)) {
            resultObjects.add(element);
          }
        }
      }
    }
    return resultObjects;
  }

  @SuppressWarnings("unchecked")
  protected List<EObject> getSelectedEObjects() {
    return (List<EObject>) getSelection(EObject.class);
  }

  protected Iterable<IResource> getResourceContents() {
    return new Iterable<IResource>() {

      LinkedList<IResource> res = new LinkedList<>((List) getSelection(IResource.class));

      public Iterator<IResource> iterator() {
        return new Iterator<IResource>() {

          Iterator<IResource> current = null;

          public boolean hasNext() {
            return res.size() > 0;
          }

          public IResource next() {
            if (hasNext()) {
              IResource next = res.removeFirst();
              try {
                if (next instanceof IFolder) {
                  res.addAll(Arrays.asList(((IFolder) next).members()));
                }
                if (next instanceof IProject) {
                  res.addAll(Arrays.asList(((IProject) next).members()));
                }
              } catch (CoreException e) {
              }
              return next;
            }
            return null;
          }

          public void remove() {
            //nothing
          }
        };
      }
    };
  }

  protected Iterable<EObject> getEObjectContents() {
    return new Iterable<EObject>() {
      final Iterator<EObject> selecteds = getSelectedEObjects().iterator();

      public Iterator<EObject> iterator() {
        return new Iterator<EObject>() {

          Iterator<EObject> current = null;

          public boolean hasNext() {
            if ((current == null) && selecteds.hasNext()) {
              return true;
            } else if (current != null && !current.hasNext()) {
              return selecteds.hasNext();
            }
            return current != null ? current.hasNext() : false;
          }

          public EObject next() {
            if ((current == null) || !current.hasNext()) {
              EObject next = selecteds.next();
              current = next.eAllContents();
              return next;
            }
            return current.next();
          }

          public void remove() {
            //nothing
          }
        };
      }
    };
  }

  public String getCategory() {
    return DefaultCategories.DEFAULT_CATEGORY;
  }

  public String getReportComponent() {
    return IReportManagerDefaultComponents.BRIDGE;
  }

  public void execute() {
    // Nothing yet
  }

  /**
   * @see IActionDelegate#run(IAction)
   */
  @Override
  public void run() {
    MiscUtil.transactionallyExecute(getSelectedEObjects(), new AbstractReadWriteCommand() {
      @Override
      public String getName() {
        return getText();
      }

      public void run() {
        logger = ReportManagerRegistry.getInstance().subscribe(getReportComponent());
        String strCategory = "(default)";
        if ((getCategory() != null)) {
          strCategory = getCategory();
        }
        logger.info(new EmbeddedMessage(NLS.bind("----------------- {0} / {1} -----------------", strCategory, getText()), IReportManagerDefaultComponents.UI));

        execute();

        logger = null;

      }
    });
  }

  public void println(String str) {
    if (str != null) {
      System.out.println(str);
    }
  }
}
