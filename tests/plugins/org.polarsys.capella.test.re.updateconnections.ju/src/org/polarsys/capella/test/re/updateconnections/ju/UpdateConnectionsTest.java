/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.re.updateconnections.ju;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.diffmerge.api.IMergeSelector;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.re.updateconnections.ui.DiffmergeHandler;
import org.polarsys.capella.core.re.updateconnections.ui.SingleUsePortsMatcher;
import org.polarsys.capella.test.framework.api.BasicCommandTestCase;

public abstract class UpdateConnectionsTest extends BasicCommandTestCase {

  /**
   * The rpl1 name by convention
   */
  public static final String RPL1_Name = "RPL1"; //$NON-NLS-1$

  /**
   * The rpl2 name by convention
   */
  public static final String RPL2_Name = "RPL2"; //$NON-NLS-1$

  /**
   * By convention all presences start with this prefix
   */
  public static final String PRESENCE_PREFIX = "presence_"; //$NON-NLS-1$


  public static class Multipart extends UpdateConnectionsTest {
    @Override
    public List<String> getRequiredTestModels() {
      return Collections.singletonList("updatelinksTest3Multipart"); //$NON-NLS-1$
    }
  }

  public static class Simple extends UpdateConnectionsTest {
    @Override
    public List<String> getRequiredTestModels() {
      return Collections.singletonList("updatelinksTest1"); //$NON-NLS-1$
    }
  }

  public static class Library extends UpdateConnectionsTest {
    @Override
    public List<String> getRequiredTestModels() {
      return Arrays.asList("updatelinksTest2", "updatelinksTest2Lib"); //$NON-NLS-1$//$NON-NLS-2$
    }
  }
  
  protected CatalogElement rpl1;
  protected CatalogElement rpl2;

  /**
   * The expected element presences, all found by convention: Name starts with presence_
   */
  protected Collection<EObject> expectedPresences = new ArrayList<EObject>();

  public void setUp() throws Exception {
    super.setUp();
    rpl1 = findCatalogElement(getModelResource(), RPL1_Name);
    rpl2 = findCatalogElement(getModelResource(), RPL2_Name);

    // make sure to use rec1's resource to cover the case with libraries
    expectedPresences = findAllExpectedPresences(rpl1.getOrigin().eResource());
  }

  private Collection<EObject> findAllExpectedPresences(Resource res) {
    Collection<EObject> result = new ArrayList<EObject>();
    for (Iterator< EObject>it = EcoreUtil.getAllContents(res, true); it.hasNext();) {
      EObject next = it.next();
      if (next instanceof NamedElement) {
        NamedElement ne = (NamedElement) next;
        if (ne.getName() != null && ne.getName().startsWith(PRESENCE_PREFIX)) {
          result.add(next);
          if (next instanceof ComponentExchange) {
            result.addAll(((ComponentExchange) next).getOwnedComponentExchangeEnds());
          }
          if (next instanceof PhysicalLink) {
            result.addAll(((PhysicalLink) next).getOwnedPhysicalLinkEnds());
          }
        }
      }
    }
    return result;
  }

  private CatalogElement findCatalogElement(Resource res, String name) {
    for (Iterator< EObject>it = EcoreUtil.getAllContents(res, true); it.hasNext();) {
      EObject next = it.next();
      if (next instanceof CatalogElement && ((CatalogElement) next).getName().equals(name)) {
        return (CatalogElement) next;
      }
    }
    return null;
  }

  @Override
  public void performTest() throws Exception {

    assertNotNull(rpl1);
    assertNotNull(rpl2);

    DiffmergeHandler handler = new DiffmergeHandler(rpl1, rpl2, new SingleUsePortsMatcher());

    EComparison comp = handler.computeDifferences(new NullProgressMonitor());

    assertTrue(comp.getDuplicateMatchIDs(Role.TARGET).isEmpty());
    assertTrue(comp.getDuplicateMatchIDs(Role.REFERENCE).isEmpty());

    Collection<EObject> remainingExpectedPresences = new ArrayList<EObject>(expectedPresences);
    for (IDifference diff : comp.getRemainingDifferences()) {
      if (diff instanceof IElementPresence) {
        IElementPresence presence = (IElementPresence) diff;
        assertSame(Role.TARGET, presence.getPresenceRole());
        assertTrue(remainingExpectedPresences.remove(presence.getElement()));
      }
    }
    assertTrue(remainingExpectedPresences.isEmpty());

    IMergeSelector sel = new IMergeSelector() {
      @Override
      public Role getMergeDirection(IDifference difference) {
        if (difference instanceof IElementPresence) {
          return Role.REFERENCE;
        }
        return null;
      }
    };

    Collection<IDifference> allMergedDifferences = comp.merge(sel, true, new NullProgressMonitor());
    assertSame(expectedPresences.size(), allMergedDifferences.size());

    // all merged presences have been attached into the containment tree
    for (IDifference diff : allMergedDifferences) {
      EObject copy = ((IElementPresence) diff).getElementMatch().get(Role.REFERENCE);
      assertTrue(copy.eContainer() != null);
    }

    // now compare again. No more presence differences should be found
    handler = new DiffmergeHandler(rpl1, rpl2, new SingleUsePortsMatcher());
    comp = handler.computeDifferences(new NullProgressMonitor());

    for (IDifference diff : comp.getRemainingDifferences()) {
      if (diff instanceof IElementPresence) {
        fail("Found unexpected element presence differences after merge"); //$NON-NLS-1$
      }
    }
  }

  @Override
  // here, always return the resource containing the project,
  // see https://bugs.polarsys.org/show_bug.cgi?id=1319
  protected Resource getModelResource() {
    if (modelResource == null) {
      Session session = getSessionForTestModel(getRequiredTestModels().get(0));
      for (Resource resource : session.getSemanticResources()) {
        // Exclude AFM's Metadata resource
        if (resource.getContents().get(0).eClass() == CapellamodellerPackage.Literals.PROJECT) {
          modelResource = resource;
        }
      }
    }
    return modelResource;
  }

}
