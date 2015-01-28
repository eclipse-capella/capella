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
package org.polarsys.capella.common.tools.report.appenders.reportlogview;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.Category;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.views.markers.MarkerViewUtil;
import org.eclipse.ui.views.markers.WorkbenchMarkerResolution;
import org.polarsys.capella.common.helpers.validation.ConstraintStatusDiagnostic;
import org.polarsys.capella.common.helpers.validation.IValidationConstants;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;

class MarkerViewColumns {

  private TreeViewer viewer;
  private List<TreeViewerColumn> columns;

  private Class<?> providerClass;
  private Map<Class<?>, Runnable> columnFactories;
  private MarkerViewHelper helper;

  // stores per column comparator direction state. either SWT.UP or SWT.DOWN
  private Map<ColumnLabelProvider, Integer> comparatorDirections;

  private SelectionListener listener;

  final String key = "markerviewcolumns.sorter"; //$NON-NLS-1$
  private ColumnLabelProvider messageLabelProvider;
  private ColumnLabelProvider ruleIDLabelProvider;
  private ColumnLabelProvider preferenceReulSetLabelProvider;

  private ColumnLabelProvider originLabelProvider;
  private ColumnLabelProvider timeLabelProvider;
  private ColumnLabelProvider resourceLabelProvider;
  private ColumnLabelProvider severityLabelProvider;

  public MarkerViewColumns(TreeViewer viewer_p, MarkerViewHelper helper_p) {
    viewer = viewer_p;
    helper = helper_p;
    listener = createSelectionListener();

    comparatorDirections = new HashMap<ColumnLabelProvider, Integer>();

    columns = new ArrayList<TreeViewerColumn>();
    columnFactories = new HashMap<Class<?>, Runnable>();
    columnFactories.put(RuleIdContentProvider.class, new Runnable() {
      @SuppressWarnings("synthetic-access")
      public void run() {
        createMessageColumn();
        createSeverityColumn();
        createRuleIdColumn();
        createPreferencesRuleSetColumn();
        createOriginColumn();
        createResourceColumn();
        createTimeColumn();
        restoreComparator();
      }
    });

    columnFactories.put(PreferenceRuleSetLabelProvider.class, new Runnable() {
      @SuppressWarnings("synthetic-access")
      public void run() {
        createMessageColumn();
        createSeverityColumn();
        createRuleIdColumn();
        createPreferencesRuleSetColumn();
        createOriginColumn();
        createResourceColumn();
        createTimeColumn();
        restoreComparator();
      }
    });

    columnFactories.put(SeverityContentProvider.class, new Runnable() {
      @SuppressWarnings("synthetic-access")
      public void run() {
        createMessageColumn();
        createSeverityColumn();
        createRuleIdColumn();
        createPreferencesRuleSetColumn();
        createOriginColumn();
        createResourceColumn();
        createTimeColumn();
        restoreComparator();
      }

    });

    columnFactories.put(CanonicalContentProvider.class, new Runnable() {
      @SuppressWarnings("synthetic-access")
      public void run() {
        createMessageColumn();
        createSeverityColumn();
        createRuleIdColumn();
        createPreferencesRuleSetColumn();
        createOriginColumn();
        createResourceColumn();
        createTimeColumn();
        restoreComparator();
      }
    });

    columnFactories.put(CategoryContentProvider.class, new Runnable() {
      @SuppressWarnings("synthetic-access")
      public void run() {
        createMessageColumn();
        createSeverityColumn();
        createRuleIdColumn();
        createPreferencesRuleSetColumn();
        createOriginColumn();
        createResourceColumn();
        createTimeColumn();
        restoreComparator();
      }
    });
  }

  private void restoreComparator() {
    LabelProviderComparator comparator = ((LabelProviderComparator) viewer.getComparator());
    if (comparator != null) {
      ColumnLabelProvider active = comparator.getProvider();
      for (TreeViewerColumn column : columns) {
        ColumnLabelProvider provider = (ColumnLabelProvider) column.getColumn().getData(key);
        if (provider == active) {
          viewer.getTree().setSortColumn(column.getColumn());
          viewer.getTree().setSortDirection(viewer.getTree().getSortDirection());
        }
        break;
      }
    } else {
      comparator = new LabelProviderComparator((ColumnLabelProvider) columns.get(0).getColumn().getData(key), SWT.DOWN);
      viewer.setComparator(comparator);
      viewer.getTree().setSortColumn(columns.get(0).getColumn());
      viewer.getTree().setSortDirection(SWT.DOWN);
    }
  }

  /*
   * handle sort on column selection
   */
  private SelectionListener createSelectionListener() {

    return new SelectionAdapter() {
      @SuppressWarnings({ "synthetic-access", "boxing" })
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        ColumnLabelProvider provider = (ColumnLabelProvider) event_p.widget.getData(key);
        LabelProviderComparator comparator = (LabelProviderComparator) viewer.getComparator();
        if (comparator.getProvider() == provider) {
          comparator.flip();
        } else {
          comparator.setProvider(provider);
          Integer direction = comparatorDirections.get(provider);
          if (direction == null) {
            direction = SWT.DOWN;
          }
          comparator.setDirection(direction);
        }
        comparatorDirections.put(provider, comparator.getDirection());
        viewer.getTree().setSortColumn((TreeColumn) event_p.widget);
        viewer.getTree().setSortDirection(comparator.getDirection());
        viewer.refresh();
      }
    };
  }

  /**
   * Provide a ContentProvider to Column mapping. Must be called after installing a new content provider on the marker view's viewer.
   */
  void update(AbstractMarkerViewContentProvider provider_p) {
    Class<?> providerClass_p = provider_p.getClass();
    if (providerClass_p != providerClass) {
      disposeAll();
      for (Class<?> c : columnFactories.keySet()) {
        if (c.isAssignableFrom(providerClass_p)) {
          columnFactories.get(providerClass_p).run();
        }
      }
      providerClass = provider_p.getClass();
    }
  }

  void disposeAll() {
    for (Iterator<TreeViewerColumn> it = columns.iterator(); it.hasNext();) {
      TreeViewerColumn column = it.next();
      it.remove();
      column.getColumn().dispose();
    }
  }

  private void createMessageColumn() {
    TreeViewerColumn cMessage = new TreeViewerColumn(viewer, SWT.NONE);
    cMessage.getColumn().setWidth(500);
    cMessage.getColumn().setMoveable(true);
    cMessage.getColumn().setText(Messages.MarkerView_Column_Title_Message);
    ColumnLabelProvider provider = getMessageLabelProvider();
    cMessage.setLabelProvider(provider);
    cMessage.getColumn().setData(key, provider);
    cMessage.getColumn().addSelectionListener(listener);
    columns.add(cMessage);
  }

  private void createSeverityColumn() {
    TreeViewerColumn cSeverity = new TreeViewerColumn(viewer, SWT.NONE);
    cSeverity.getColumn().setWidth(80);
    cSeverity.getColumn().setMoveable(true);
    cSeverity.getColumn().setText(Messages.MarkerView_Column_Title_Level);
    ColumnLabelProvider provider = getSeverityLabelProvider();
    cSeverity.setLabelProvider(provider);
    cSeverity.getColumn().setData(key, provider);
    cSeverity.getColumn().addSelectionListener(listener);
    columns.add(cSeverity);
  }

  private void createTimeColumn() {
    TreeViewerColumn cTime = new TreeViewerColumn(viewer, SWT.NONE);
    cTime.getColumn().setWidth(80);
    cTime.getColumn().setMoveable(true);
    cTime.getColumn().setText(Messages.MarkerView_Column_Title_Time);
    ColumnLabelProvider provider = getTimeLabelProvider();
    cTime.setLabelProvider(provider);
    cTime.getColumn().setData(key, provider);
    cTime.getColumn().addSelectionListener(listener);
    columns.add(cTime);
  }

  private void createResourceColumn() {
    TreeViewerColumn cResource = new TreeViewerColumn(viewer, SWT.NONE);
    cResource.getColumn().setWidth(120);
    cResource.getColumn().setMoveable(true);
    cResource.getColumn().setText(Messages.MarkerView_Column_Title_Resource);
    ColumnLabelProvider provider = getResourceLabelProvider();
    cResource.setLabelProvider(provider);
    cResource.getColumn().setData(key, provider);
    cResource.getColumn().addSelectionListener(listener);
    columns.add(cResource);
  }

  private void createRuleIdColumn() {
    TreeViewerColumn cRule = new TreeViewerColumn(viewer, SWT.NONE);
    cRule.getColumn().setWidth(80);
    cRule.getColumn().setMoveable(true);
    cRule.getColumn().setText(Messages.MarkerView_Column_Title_RuleId);
    ColumnLabelProvider provider = getRuleIDLabelProvider();
    cRule.setLabelProvider(provider);
    cRule.getColumn().setData(key, provider);
    cRule.getColumn().addSelectionListener(listener);
    columns.add(cRule);
  }

  private void createPreferencesRuleSetColumn() {
    TreeViewerColumn preferenceRuleSetName = new TreeViewerColumn(viewer, SWT.NONE);
    preferenceRuleSetName.getColumn().setWidth(120);
    preferenceRuleSetName.getColumn().setMoveable(true);
    preferenceRuleSetName.getColumn().setText(Messages.MarkerView_Column_Title_RuleSetId);
    ColumnLabelProvider provider = getPreferenceReulSetLabelProvider();
    preferenceRuleSetName.setLabelProvider(provider);
    preferenceRuleSetName.getColumn().setData(key, provider);
    preferenceRuleSetName.getColumn().addSelectionListener(listener);
    columns.add(preferenceRuleSetName);
  }

  private void createOriginColumn() {
    TreeViewerColumn cOrigin = new TreeViewerColumn(viewer, SWT.NONE);
    cOrigin.getColumn().setWidth(200);
    cOrigin.getColumn().setMoveable(true);
    cOrigin.getColumn().setText(Messages.MarkerView_Column_Title_Origin);
    ColumnLabelProvider provider = getCategoryLabelProvider();
    cOrigin.setLabelProvider(provider);
    cOrigin.getColumn().setData(key, provider);
    cOrigin.getColumn().addSelectionListener(listener);
    columns.add(cOrigin);
  }

  private ColumnLabelProvider getResourceLabelProvider() {
    if (resourceLabelProvider == null) {
      resourceLabelProvider = new MarkerViewColumnLabelProvider() {
        @Override
        public String getText(Object element) {
          String result = ICommonConstants.EMPTY_STRING;
          if (element instanceof IMarker) {
            result = ((IMarker) element).getAttribute(MarkerViewUtil.PATH_ATTRIBUTE, ICommonConstants.EMPTY_STRING);
          }
          return result;
        }
      };
    }
    return resourceLabelProvider;
  }

  private ColumnLabelProvider getTimeLabelProvider() {

    if (timeLabelProvider == null) {
      timeLabelProvider = new MarkerViewColumnLabelProvider() {
        final DateFormat format = DateFormat.getTimeInstance();

        @Override
        public String getText(Object element) {
          String result = ICommonConstants.EMPTY_STRING;
          if (element instanceof IMarker) {
            try {
              result = format.format(new Date(((IMarker) element).getCreationTime()));
            } catch (CoreException e) {
              MarkerViewPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, MarkerViewPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
            }
          }
          return result;
        }
      };
    }
    return timeLabelProvider;

  }

  private ColumnLabelProvider getMessageLabelProvider() {
    if (messageLabelProvider == null) {
      messageLabelProvider = new MarkerViewColumnLabelProvider() {

        Pattern pattern = Pattern.compile("(\\r|\\n)"); //$NON-NLS-1$

        // Straightforward but uncached and slow implementation
        // to support annotation of labels with # of child elements.
        @SuppressWarnings("synthetic-access")
        private int countMarkerChildren(Object element) {
          ITreeContentProvider provider = (ITreeContentProvider) viewer.getContentProvider();
          int markerChildren = 0;
          Object[] children = provider.getChildren(element);
          if (children != null) {
            for (Object c : children) {
              if (c instanceof IMarker) {
                boolean selected = true;
                for (ViewerFilter filter : viewer.getFilters()) {
                  if (!filter.select(viewer, element, c)) {
                    selected = false;
                    break;
                  }
                }
                if (selected) {
                  markerChildren++;
                }
              } else {
                markerChildren += countMarkerChildren(c);
              }
            }
          }
          return markerChildren;
        }

        @Override
        public String getText(Object element) {
          String result = element.toString();

          // yes..
          if ((element == MarkerViewHelper.OTHER_CATEGORY) || (element == MarkerViewHelper.OTHER_RULES)) {
            result = Messages.MarkerLabelProvider_EcoreDiagnosticSourceLabel;
          }

          if (element instanceof IMarker) {
            result = ((IMarker) element).getAttribute(IMarker.MESSAGE, ICommonConstants.EMPTY_STRING).toString();
            result = pattern.matcher(result).replaceAll(" "); //$NON-NLS-1$ 

          } else if (element instanceof IConstraintDescriptor) {
            result = ((IConstraintDescriptor) element).getName();
          } else if (element instanceof Category) {
            result = ((Category) element).getName();
          }

          int markerChildren = countMarkerChildren(element);
          if (markerChildren == 1) {
            result += " (1 item)"; //$NON-NLS-1$
          } else if (markerChildren > 1) {
            result += " (" + markerChildren + " items)"; //$NON-NLS-1$//$NON-NLS-2$
          }

          return result;
        }

        @Override
        public Image getImage(Object element) {
          if (element instanceof IMarker) {
            IMarker marker = (IMarker) element;
            List<EObject> elements = MarkerViewHelper.getModelElementsFromMarker(marker);
            if (elements.size() > 0) {
              return EObjectLabelProviderHelper.getImage(elements.get(0));
            }
          } else if (element instanceof SeverityLevel) {
            return ((SeverityLevel) element).getImage();
          } else {
            IWorkbench workbench = PlatformUI.getWorkbench();
            ISharedImages images = workbench.getSharedImages();
            return images.getImage(ISharedImages.IMG_OBJ_FOLDER);
          }
          return null;
        }

        @Override
        public String getToolTipText(Object element) {
          String result = null;
          if (element instanceof IMarker) {
            result = ((IMarker) element).getAttribute(IMarker.MESSAGE, ICommonConstants.EMPTY_STRING);
          } else {
            result = super.getToolTipText(element);
          }
          return result;
        }

      };
    }
    return messageLabelProvider;
  }

  private ColumnLabelProvider getRuleIDLabelProvider() {
    if (ruleIDLabelProvider == null) {
      ruleIDLabelProvider = new MarkerViewColumnLabelProvider() {
        @SuppressWarnings("synthetic-access")
        @Override
        public String getText(Object element) {
          String result = ICommonConstants.EMPTY_STRING;
          if (element instanceof IMarker) {
            result = MarkerViewHelper.getRuleId((IMarker) element);
            if (result != null) {
              if (result.startsWith(MarkerViewHelper.ECORE_DIAGNOSTIC_SOURCE)) {
                result = Messages.MarkerLabelProvider_EcoreDiagnosticSourceLabel;
              } else {
                result = helper.getUnqualifiedRuleId(result);
              }
            }
          }
          return result;
        }

        @Override
        public Image getImage(Object element) {
          if (element instanceof IMarker) {
            // don't use hasResolutions, since this returns
            // optimistically "true" for
            // resolution generators in plugins that aren't yet
            // loaded.
            IMarker marker = (IMarker) element;
            IMarkerResolution[] resolutions = IDE.getMarkerHelpRegistry().getResolutions(marker);

            if ((resolutions != null) && (resolutions.length > 0)) {

              if (hasAtLeastOneMultipleMarkerResolution(marker, resolutions)) {
                return MarkerViewPlugin.getDefault().getImage("quickfixAll-repository.png"); //$NON-NLS-1$								
              } else {
                return MarkerViewPlugin.getDefault().getImage("quickfix.gif"); //$NON-NLS-1$
              }
            }
          }
          return null;
        }

        // check if there is at least one multiple marker resolution
        private boolean hasAtLeastOneMultipleMarkerResolution(IMarker marker, IMarkerResolution[] resolutions) {
          // get all markers
          Collection<IMarker> allMarkers = LightMarkerRegistry.getInstance().getMarkers();
          List<IMarker> sameConstraintMarkers = new ArrayList<IMarker>();

          // is there any other markers with same rule id
          IConstraintDescriptor markerDesc = MarkerViewHelper.getConstraintDescriptor(marker);
          for (IMarker m : allMarkers) {
            if (MarkerViewHelper.getConstraintDescriptor(m) == markerDesc) {
              sameConstraintMarkers.add(m);
            }
          }
          // check that there is at least one resolution that can
          // handle multiple markers
          for (IMarkerResolution res : resolutions) {
            if (res instanceof WorkbenchMarkerResolution) {
              IMarker[] similarMarkers = ((WorkbenchMarkerResolution) res).findOtherMarkers(sameConstraintMarkers.toArray(new IMarker[0]));
              if (similarMarkers.length > 1) {
                return true;
              }
            }
          }
          return false;
        }
      };
    }
    return ruleIDLabelProvider;
  }

  public ColumnLabelProvider getPreferenceReulSetLabelProvider() {
    if (preferenceReulSetLabelProvider == null) {
      preferenceReulSetLabelProvider = new MarkerViewColumnLabelProvider() {
        @Override
        public String getText(Object element) {
          String result = ICommonConstants.EMPTY_STRING;
          if (element instanceof IMarker) {
            result = MarkerViewHelper.getPreferenceFileName((IMarker) element);
            if (result != null) {
              if (!result.isEmpty()) {
                return result;
              }
            }
          }
          return result;
        }

        @Override
        public Image getImage(Object element) {
          Image result = null;
          // XXX : choose the image for the preferences
          return result;
        }
      };
    }
    return preferenceReulSetLabelProvider;
  }

  private ColumnLabelProvider getSeverityLabelProvider() {
    if (severityLabelProvider == null) {
      severityLabelProvider = new MarkerViewColumnLabelProvider() {
        @Override
        public String getText(Object element) {
          String result = ICommonConstants.EMPTY_STRING;
          if (element instanceof IMarker) {
            SeverityLevel level = SeverityLevel.getLevel((IMarker) element);
            if (level != null) {
              result = level.toString();
            }
          }
          return result;
        }

        @Override
        public Image getImage(Object element) {
          Image result = null;
          if (element instanceof IMarker) {
            SeverityLevel level = SeverityLevel.getLevel((IMarker) element);
            if (level != null) {
              result = level.getImage();
            }
          }
          return result;
        }
      };
    }
    return severityLabelProvider;
  }

  private ColumnLabelProvider getCategoryLabelProvider() {
    if (originLabelProvider == null) {
      originLabelProvider = new MarkerViewColumnLabelProvider() {
        @Override
        public String getText(Object element) {
          String result = ICommonConstants.EMPTY_STRING;
          if (element instanceof IMarker) {
            try {
              Diagnostic diagnostic = (Diagnostic) ((IMarker) element).getAttribute(IValidationConstants.TAG_DIAGNOSTIC);
              if (diagnostic instanceof ConstraintStatusDiagnostic) {
                Set<Category> cats = ((ConstraintStatusDiagnostic) diagnostic).getConstraintStatus().getConstraint().getDescriptor().getCategories();
                if (!cats.isEmpty()) {
                  result = cats.iterator().next().getQualifiedName();
                }
              }
            } catch (CoreException e) {
              MarkerViewPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, MarkerViewPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
            }
          }
          return result;
        }
      };
    }
    return originLabelProvider;
  }

  class LabelProviderComparator extends ViewerComparator {

    private ColumnLabelProvider provider;
    private int direction;

    LabelProviderComparator(ColumnLabelProvider provider_p, int direction_p) {
      provider = provider_p;
      direction = direction_p;
    }

    @Override
    /**
     * This makes sure that nodes with children are always on top
     * {@inheritDoc}
     */
    public int category(Object o) {
      if (o instanceof IMarker) {
        return 100;
      }
      return 0;
    }

    /**
     * flip the sort direction
     */
    public void flip() {
      if (direction == SWT.UP) {
        direction = SWT.DOWN;
      } else {
        direction = SWT.UP;
      }
    }

    void setDirection(int direction_p) {
      direction = direction_p;
    }

    int getDirection() {
      return direction;
    }

    void setProvider(ColumnLabelProvider provider_p) {
      provider = provider_p;
    }

    ColumnLabelProvider getProvider() {
      return provider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(Viewer viewer_p, Object o1_p, Object o2_p) {

      int cat1 = category(o1_p);
      int cat2 = category(o2_p);
      if (cat1 != cat2) {
        return cat1 - cat2;
      }

      // for markers, just use the label provider of the current
      // sort column's label provider
      if ((o1_p instanceof IMarker) && (o2_p instanceof IMarker)) {
        String t1 = provider.getText(o1_p);
        String t2 = provider.getText(o2_p);
        if (t1 == null) {
          t1 = ICommonConstants.EMPTY_STRING;
        }
        if (t2 == null) {
          t2 = ICommonConstants.EMPTY_STRING;
        }
        int flip = 1;
        if (direction == SWT.UP) {
          flip = -1;
        }
        return flip * t1.compareTo(t2);
      }

      // high levels are on top of low levels. inverse the result because
      // SeverityLevel declares levels from low to high
      if ((o1_p instanceof SeverityLevel) && (o2_p instanceof SeverityLevel)) {
        SeverityLevel l1 = (SeverityLevel) o1_p;
        SeverityLevel l2 = (SeverityLevel) o2_p;
        return -1 * l1.compareTo(l2);
      }

      // categories are always alphabetically
      if ((o1_p instanceof Category) && (o2_p instanceof Category)) {
        Category c1 = (Category) o1_p;
        Category c2 = (Category) o2_p;
        return c1.getName().compareTo(c2.getName());
      }

      // last resort
      return o1_p.toString().compareTo(o2_p.toString());

    }
  }

  /**
   * Compares IMarkers and makes sure to return an order unless the two markers are identical in the sense of ==.
   */
  abstract class MarkerComparator implements Comparator<IMarker> {
    public final int compare(IMarker marker1, IMarker marker2) {
      int result = 0;
      if (marker1 != marker2) {
        result = doCompare(marker1, marker2);
        if (result == 0) {
          int nbMarker0 = marker1.getAttribute(MarkerView.MARKER_NUMBER, 0);
          int nbMarker1 = marker2.getAttribute(MarkerView.MARKER_NUMBER, 0);
          result = nbMarker0 - nbMarker1;
        }

        if (result == 0) {
          // make sure to return an order if two markers are not the
          // exact same object
          return marker1.hashCode() - marker2.hashCode();
        }
      }
      return result;
    }

    protected abstract int doCompare(IMarker marker1_p, IMarker marker2_p);
  }

  /**
   * Show rule description as tooltip on validation markers.
   */
  class MarkerViewColumnLabelProvider extends ColumnLabelProvider {

    @Override
    public String getToolTipText(Object element) {
      String result = null;
      if (element instanceof IConstraintDescriptor) {
        result = ((IConstraintDescriptor) element).getDescription();
      } else if (element instanceof IMarker) {
        IConstraintDescriptor descriptor = MarkerViewHelper.getConstraintDescriptor((IMarker) element);
        if (descriptor != null) {
          result = descriptor.getDescription();
        }
      } else if (element instanceof Category) {
        result = ((Category) element).getDescription();
      }
      return result;
    }
  }
}
