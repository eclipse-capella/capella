/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.step.filters;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.junit.Assert;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.AbstractDiagramStepWithDelta;

/**
 * Check the list of available filter in order to detect filter renaming, addition, deletion
 */
@Deprecated
public abstract class AbstractFilterTrackChangesTest extends AbstractDiagramStepWithDelta {

  /**
   * @param checkDelta_p
   */
  public AbstractFilterTrackChangesTest(DiagramContext context, boolean checkDelta_p) {
    super(context, checkDelta_p);
  }

  @Override
  protected int getNumberofExpectedNewElement() {
    // In this test, no new element is expected
    return 0;
  }

  public void testFilterTracker() {
    // DO NOTHING
  }

  protected abstract Set<String> expectedFiltersSet();

  /**
   * {@inheritDoc}
   */
  @Override
  protected void postRunTest() {
    // Match the expectedFiltersList() to the actual list of
    // filters from runtime
    DiagramDescription description = getDiagramContext().getDiagram().getDescription();
    EList<FilterDescription> filters = description.getFilters();
    Set<String> setOfActualFilters = getSetOfActualFilters(filters);

    Set<String> expectedFiltersSet = expectedFiltersSet();

    // Check Size
    if (setOfActualFilters.size() > expectedFiltersSet.size()) {
      // A new filter have been created
      setOfActualFilters.removeAll(expectedFiltersSet);
      Assert.assertTrue(MessageFormat.format(Messages.newFilterCreationDetected, setOfActualFilters.toString(),
          getDiagramContext().getDiagramDescriptor().getName()), false);
    } else if (setOfActualFilters.size() < expectedFiltersSet.size()) {
      // A filter have been deleted from diagram
      expectedFiltersSet.removeAll(setOfActualFilters);
      Assert.assertTrue(MessageFormat.format(Messages.filterRemovalDetected, expectedFiltersSet.toString(),
          getDiagramContext().getDiagramDescriptor().getName()), false);
    }

    boolean haveSameFilters = setOfActualFilters.equals(expectedFiltersSet);
    if (!haveSameFilters) {
      List<String> renamingFilterList = new ArrayList<String>(expectedFiltersSet);
      renamingFilterList.removeAll(setOfActualFilters);

      List<String> origineFilterList = new ArrayList<String>(setOfActualFilters);
      origineFilterList.removeAll(expectedFiltersSet);

      Assert.fail(MessageFormat.format(Messages.renamedFiltersFound, getDiagramContext().getDiagramDescriptor().getName(),
          origineFilterList.toString(), renamingFilterList.toString()));
    }
    super.postRunTest();
  }

  /**
   * Get the {@link Set} of the actual filters detected in diagram. Fails if there's a duplicated Name/Label detected in
   * filter list extracted from runtime diagram
   * 
   * @param filters
   *          a EList<FilterDescription>
   * @return the set of found filters in runtime diagram
   */
  private Set<String> getSetOfActualFilters(EList<FilterDescription> filters) {
    Set<String> setOfActualFilters = new HashSet<String>();
    List<String> listOfDuplicated = new ArrayList<String>();

    for (FilterDescription filter : filters) {
      if ((filter.getLabel() == null) || filter.getLabel().equalsIgnoreCase(ICommonConstants.EMPTY_STRING)) {
        if (!setOfActualFilters.add(filter.getName())) {
          listOfDuplicated.add(filter.getName());
        }
        // FIXME: for test initialization purpose
        // System.out.println("set.add(\"" + filter.getName() + "\");"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
        if (!setOfActualFilters.add(filter.getLabel())) {
          listOfDuplicated.add(filter.getLabel());
        }
        // FIXME: for test initialization purpose
        // System.out.println("set.add(\"" + filter.getLabel() //$NON-NLS-1$
        // + "\");"); //$NON-NLS-1$
      }
    }

    Assert.assertTrue(MessageFormat.format(Messages.duplicatedFilterIDDetected, listOfDuplicated.toString(),
        EObjectExt.getText(getDiagramContext().getDiagram())), listOfDuplicated.isEmpty());

    return setOfActualFilters;
  }
}
