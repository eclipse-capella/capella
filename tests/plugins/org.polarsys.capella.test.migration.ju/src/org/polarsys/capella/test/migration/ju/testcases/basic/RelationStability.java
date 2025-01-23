/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.migration.ju.testcases.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.eclipse.core.resources.IProject;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.swt.SWT;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.information.AggregationKind;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.CDBDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.migration.ju.helpers.MigrationHelper;

import junit.framework.AssertionFailedError;

/**
 * This class tests the migration of the association relation in the cdb diagram.
 * 
 * @author Séraphin Costa
 */
@RunWith(value = Parameterized.class)
public class RelationStability extends AbstractDiagramTestCase {

  private enum NavigableState {
    NONE_A2B_FIRST(false, false), NONE_B2A_FIRST(false, false), A2B(true, false), B2A(false, true), BOTH_A2B_FIRST(true,
        true), BOTH_B2A_FIRST(true, true);

    private boolean navigableAtoB;
    private boolean navigableBtoA;

    NavigableState(boolean a2bNavigable, boolean b2aNavigable) {
      this.navigableAtoB = a2bNavigable;
      this.navigableBtoA = b2aNavigable;
    }

    public boolean isNavigableAtoB() {
      return navigableAtoB;
    }

    public boolean isNavigableBtoA() {
      return navigableBtoA;
    }

    public boolean isNavigableOnlyAtoB() {
      return navigableAtoB && !navigableBtoA;
    }

    public boolean isNavigableOnlyBtoA() {
      return navigableBtoA && !navigableAtoB;
    }
  }

  private enum AbstractState {
    NONE, A2B, B2A, BOTH;

    public boolean isAbstractAtoB() {
      return this == A2B || this == BOTH;
    }

    public boolean isAbstractBtoA() {
      return this == B2A || this == BOTH;
    }
  }

  private static final String CLASS_ID_A = "2a30d109-d64f-4aa4-81f0-3b16e023c542";
  private static final String CLASS_ID_B = "ca411035-9f42-471e-bcc4-2f139ef39f6f";

  private IProject project;
  private Session session;
  private SessionContext context;
  private CDBDiagram cdb;

  final private String filename;
  final private PointList bendPointsExpected;
  final private Rectangle beginLabelBoundsExpected;
  final private int beginLabelStyleExpected;
  final private Rectangle middleLabelBoundsExpected;
  final private Rectangle endLabelBoundsExpected;
  final private int endLabelStyleExpected;
  final private Optional<EdgeArrows> sourceEdgeArrowExpected;
  final private Optional<EdgeArrows> targetEdgeArrowExpected;

  private static EdgeArrows getBaseArrow(AggregationKind kind) {
    switch (kind) {
    case AGGREGATION:
      return EdgeArrows.DIAMOND_LITERAL;
    case COMPOSITION:
      return EdgeArrows.FILL_DIAMOND_LITERAL;
    default:
      return EdgeArrows.NO_DECORATION_LITERAL;
    }
  }

  private static EdgeArrows getArrowWithNavigability(EdgeArrows initial, boolean isNavigable) {
    if (!isNavigable) {
      return initial;
    } else {
      switch (initial) {
      case DIAMOND_LITERAL:
        return EdgeArrows.INPUT_ARROW_WITH_DIAMOND_LITERAL;
      case FILL_DIAMOND_LITERAL:
        return EdgeArrows.INPUT_ARROW_WITH_FILL_DIAMOND_LITERAL;
      default:
        return EdgeArrows.INPUT_ARROW_LITERAL;
      }
    }
  }

  static String getTestFilename(AggregationKind kindAtoB, AggregationKind kindBtoA, NavigableState navigableState,
      AbstractState abstractState) {
    String kindAtoBstr = kindAtoB.toString().substring(0, 3).toLowerCase();
    String kindBtoAstr = kindBtoA.toString().substring(0, 3).toLowerCase();
    String kind = kindAtoBstr + "2" + kindBtoAstr;
    String navigable = "-NAV" + navigableState.toString().toLowerCase();
    String abs = "-ABS" + abstractState.toString().toLowerCase();
    return kind + navigable + abs;
  }

  static Collection<Object[]> getKindTestData() {
    return AggregationKind.VALUES.stream().flatMap(kindAtoB -> {
      return AggregationKind.VALUES.stream().map(kindBtoA -> {
        // expected arrows
        EdgeArrows sourceEdgeArrows;
        EdgeArrows targetEdgeArrows;
        if (kindAtoB.equals(AggregationKind.UNSET) || kindBtoA.equals(AggregationKind.UNSET)) {
          sourceEdgeArrows = getArrowWithNavigability(EdgeArrows.NO_DECORATION_LITERAL, false);
          targetEdgeArrows = getArrowWithNavigability(EdgeArrows.NO_DECORATION_LITERAL, true);
        } else if (!kindAtoB.equals(AggregationKind.ASSOCIATION) && !kindBtoA.equals(AggregationKind.ASSOCIATION)) {
          sourceEdgeArrows = EdgeArrows.NO_DECORATION_LITERAL;
          targetEdgeArrows = EdgeArrows.NO_DECORATION_LITERAL;
        } else {
          sourceEdgeArrows = getArrowWithNavigability(getBaseArrow(kindAtoB), false);
          targetEdgeArrows = getArrowWithNavigability(getBaseArrow(kindBtoA), true);
        }

        return new Object[] { //
            getTestFilename(kindAtoB, kindBtoA, NavigableState.A2B, AbstractState.NONE), //
            new PointList(new int[] { 58, 72, 120, 20, 170, 100, 260, 40, 300, 66 }), // bendpoints
            new Rectangle(103, 47, 0, 0), // begin label bounds
            SWT.NORMAL, // begin label style
            new Rectangle(140, 30, 94, 16), // middle label bounds
            new Rectangle(250, 50, 6, 15), // end label bounds
            SWT.NORMAL, // end label style
            Optional.of(sourceEdgeArrows), //
            Optional.of(targetEdgeArrows), //
        };
      });
    }).toList();
  }

  static Collection<Object[]> getNavigableTestData() {
    return Arrays.stream(NavigableState.values()) //
        .filter(navigableState -> !navigableState.equals(NavigableState.A2B))
        .flatMap(navigableState -> {

          // expected positions
          Rectangle beginLabelBounds, endLabelBounds;
          if (navigableState.isNavigableOnlyAtoB()) {
            beginLabelBounds = new Rectangle(103, 47, 0, 0);
          } else {
            beginLabelBounds = new Rectangle(100, 40, 6, 15);
          }
          if (navigableState.isNavigableOnlyBtoA()) {
            endLabelBounds = new Rectangle(253, 57, 0, 0);
          } else {
            endLabelBounds = new Rectangle(250, 50, 6, 15);
          }

          return Stream.of(new AggregationKind[] { AggregationKind.ASSOCIATION, AggregationKind.ASSOCIATION },
              new AggregationKind[] { AggregationKind.ASSOCIATION, AggregationKind.AGGREGATION },
              new AggregationKind[] { AggregationKind.AGGREGATION, AggregationKind.ASSOCIATION },
              new AggregationKind[] { AggregationKind.ASSOCIATION, AggregationKind.COMPOSITION },
              new AggregationKind[] { AggregationKind.COMPOSITION, AggregationKind.ASSOCIATION }).map(kinds -> {
                AggregationKind kindAtoB = kinds[0];
                AggregationKind kindBtoA = kinds[1];

                // expected arrows
                EdgeArrows sourceEdgeArrows = getArrowWithNavigability(getBaseArrow(kindAtoB),
                    navigableState.isNavigableBtoA());
                EdgeArrows targetEdgeArrows = getArrowWithNavigability(getBaseArrow(kindBtoA),
                    navigableState.isNavigableAtoB());

                return new Object[] { //
                    getTestFilename(kindAtoB, kindBtoA, navigableState, AbstractState.NONE), //
                    new PointList(new int[] { 58, 72, 120, 20, 170, 100, 260, 40, 300, 66 }), // bendpoints
                    beginLabelBounds, //
                    SWT.NORMAL, // begin label style
                    new Rectangle(140, 30, 94, 16), // middle label bounds
                    endLabelBounds, //
                    SWT.NORMAL, // end label style
                    Optional.of(sourceEdgeArrows), //
                    Optional.of(targetEdgeArrows), //
                };
              });
        }).toList();
  }

  static Collection<Object[]> getAbstractTestData() {
    return Stream.of(AbstractState.A2B, AbstractState.B2A, AbstractState.BOTH).flatMap(abstractState -> {
      return Arrays.stream(NavigableState.values()).map(navigableState -> {
        // expected positions
        Rectangle beginLabelBounds, endLabelBounds;
        if (navigableState.isNavigableOnlyAtoB()) {
          beginLabelBounds = new Rectangle(103, 47, 0, 0);
        } else {
          beginLabelBounds = new Rectangle(100, 40, 6, 15);
        }
        if (navigableState.isNavigableOnlyBtoA()) {
          endLabelBounds = new Rectangle(253, 57, 0, 0);
        } else {
          endLabelBounds = new Rectangle(250, 50, 6, 15);
        }

        // expected arrows
        EdgeArrows sourceEdgeArrows = getArrowWithNavigability(EdgeArrows.NO_DECORATION_LITERAL,
            navigableState.isNavigableBtoA());
        EdgeArrows targetEdgeArrows = getArrowWithNavigability(EdgeArrows.NO_DECORATION_LITERAL,
            navigableState.isNavigableAtoB());

        // expected label style
        int beginLabelStyle, endLabelStyle;
        if (abstractState.isAbstractBtoA()) {
          beginLabelStyle = SWT.ITALIC;
        } else {
          beginLabelStyle = SWT.NORMAL;
        }
        if (abstractState.isAbstractAtoB()) {
          endLabelStyle = SWT.ITALIC;
        } else {
          endLabelStyle = SWT.NORMAL;
        }

        return new Object[] { //
            getTestFilename(AggregationKind.ASSOCIATION, AggregationKind.ASSOCIATION, navigableState, abstractState), //
            new PointList(new int[] { 58, 72, 120, 20, 170, 100, 260, 40, 300, 66 }), // bendpoints
            beginLabelBounds, //
            beginLabelStyle, //
            new Rectangle(140, 30, 94, 16), // middle label bounds
            endLabelBounds, //
            endLabelStyle, //
            Optional.of(sourceEdgeArrows), //
            Optional.of(targetEdgeArrows), //
        };
      });
    }).toList();
  }

  @Parameters(name = "{0}")
  public static Collection<Object[]> data() {
    List<Object[]> allTest = new ArrayList<>();
    allTest.addAll(getKindTestData());
    allTest.addAll(getNavigableTestData());
    allTest.addAll(getAbstractTestData());
    return allTest;
  }

  public RelationStability(String filename, PointList bendPoints, Rectangle beginLabelBounds, int beginLabelStyle,
      Rectangle middleLabelBounds, Rectangle endLabelBounds, int endLabelStyle, Optional<EdgeArrows> sourceEdgeArrow,
      Optional<EdgeArrows> targetEdgeArrow) {
    this.filename = filename;
    this.bendPointsExpected = bendPoints;
    this.beginLabelBoundsExpected = beginLabelBounds;
    this.beginLabelStyleExpected = beginLabelStyle;
    this.middleLabelBoundsExpected = middleLabelBounds;
    this.endLabelBoundsExpected = endLabelBounds;
    this.endLabelStyleExpected = endLabelStyle;
    this.sourceEdgeArrowExpected = sourceEdgeArrow;
    this.targetEdgeArrowExpected = targetEdgeArrow;
  }

  @Override
  protected String getRelativeModelsFolderName() {
    return super.getRelativeModelsFolderName() + "/doremi-4873-datatest";
  }

  @Override
  protected String getRequiredTestModel() {
    return filename;
  }

  @Before
  @Override
  public void setUp() throws Exception {
    super.setUp();
    project = IResourceHelpers.getEclipseProjectInWorkspace(getRequiredTestModel());
  }

  @After
  @Override
  public void tearDown() throws Exception {
    super.tearDown();
  }

  private String pointsToString(PointList points) {
    ArrayList<String> pointsArrayList = new ArrayList<>();
    for (int i = 0; i < points.size(); ++i) {
      pointsArrayList.add("(" + points.getPoint(i).x + ", " + points.getPoint(i).y + ")");
    }
    return "{" + String.join(", ", pointsArrayList) + "}";
  }

  private void assertPointsEquals(String message, PointList expected, PointList actual) {
    int len = expected.size();
    if (len != actual.size()) {
      failNotEquals(message, pointsToString(expected), pointsToString(actual));
    }
    for (int i = 0; i < len; ++i) {
      if (!expected.getPoint(i).equals(actual.getPoint(i))) {
        failNotEquals(message, pointsToString(expected), pointsToString(actual));
      }
    }
  }

  private void assertLabelBoundsEquals(String message, Rectangle expected, Rectangle actual) {
    final double positionDelta = 1.;
    final double widthDelta = 16.;
    final double heightDelta = 4.;

    final boolean sameCenter;
    final boolean sameSize;

    Point expectedCenter = expected.getCenter();
    Point actualCenter = actual.getCenter();
    Dimension expectedSize = expected.getSize();
    Dimension actualSize = actual.getSize();

    Dimension centerDiff = actualCenter.getDifference(expectedCenter);
    sameCenter = Math.abs(centerDiff.preciseWidth()) < positionDelta
        && Math.abs(centerDiff.preciseHeight()) < positionDelta;
    if (expectedSize.isEmpty()) {
      sameSize = actualSize.isEmpty();
    } else {
      Dimension sizeDiff = actualSize.getShrinked(expectedSize);
      sameSize = Math.abs(sizeDiff.preciseWidth()) < widthDelta && Math.abs(sizeDiff.preciseHeight()) < heightDelta;
    }

    if (!sameCenter || !sameSize) {
      failNotEquals(message, expected, actual);
    }
  }

  private boolean isClassA(DDiagramElement diagramElement) {
    return diagramElement.getTarget() instanceof Classifier classifier && CLASS_ID_A.equals(classifier.getId());
  }

  private boolean isClassB(DDiagramElement diagramElement) {
    return diagramElement.getTarget() instanceof Classifier classifier && CLASS_ID_B.equals(classifier.getId());
  }

  private boolean isAssociation(DDiagramElement diagramElement) {
    return diagramElement.getTarget() instanceof Association association;
  }

  private Supplier<AssertionFailedError> getFailLambda(String message) {
    return () -> new AssertionFailedError(message);
  }

  @Test
  @Override
  public void test() throws Exception {
    // migration and opening
    MigrationHelper.migrateProject(project);
    session = getSession(getRequiredTestModel());
    context = new SessionContext(session);
    cdb = CDBDiagram.openDiagram(context, "[CDB] Data");
    GuiActions.flushASyncGuiThread();

    // get and check Sirius elements
    List<DDiagramElement> diagramElements = cdb.getDiagram().getOwnedDiagramElements();
    DDiagramElement classA = diagramElements.stream().filter(this::isClassA).findFirst()
        .orElseThrow(getFailLambda("The class A was not found on the diagram after the migration"));
    DDiagramElement classB = diagramElements.stream().filter(this::isClassB).findFirst()
        .orElseThrow(getFailLambda("The class B was not found on the diagram after the migration"));
    DEdge association = diagramElements.stream().filter(this::isAssociation).findFirst() //
        .filter(DEdge.class::isInstance).map(DEdge.class::cast)
        .orElseThrow(getFailLambda("The association was not found on the diagram after the migration"));

    assertEquals("The number of diagram element after migration is wrong", 3, diagramElements.size());
    assertEquals("The association source node after migration is wrong", classA, association.getSourceNode());
    assertEquals("The association target node after migration is wrong", classB, association.getTargetNode());

    sourceEdgeArrowExpected.ifPresent(edgeArrowExpected -> {
      assertEquals("Wrong association source arrow", edgeArrowExpected, association.getOwnedStyle().getSourceArrow());
    });
    targetEdgeArrowExpected.ifPresent(edgeArrowExpected -> {
      assertEquals("Wrong association target arrow", edgeArrowExpected, association.getOwnedStyle().getTargetArrow());
    });

    // get edit parts
    GraphicalEditPart associationEditPart = (GraphicalEditPart) DiagramServices.getDiagramServices()
        .getEditPart(association);
    GraphicalEditPart beginLabelEditPart = associationEditPart.getChildren().stream()
        .filter(DEdgeBeginNameEditPart.class::isInstance).findFirst()
        .orElseThrow(getFailLambda("The association begin label was not found after the migration"));
    GraphicalEditPart middleLabelEditPart = associationEditPart.getChildren().stream()
        .filter(DEdgeNameEditPart.class::isInstance).findFirst()
        .orElseThrow(getFailLambda("The association middle label was not found after the migration"));
    GraphicalEditPart endLabelEditPart = associationEditPart.getChildren().stream()
        .filter(DEdgeEndNameEditPart.class::isInstance).findFirst()
        .orElseThrow(getFailLambda("The association end label was not found after the migration"));

    // get label position and bendpoints
    PointList bendPoints = ((Connection) associationEditPart.getFigure()).getPoints();
    Rectangle beginLabelBounds = beginLabelEditPart.getFigure().getBounds();
    int beginLabelStyle = beginLabelEditPart.getFigure().getFont().getFontData()[0].getStyle();
    Rectangle middleLabelBounds = middleLabelEditPart.getFigure().getBounds();
    Rectangle endLabelBounds = endLabelEditPart.getFigure().getBounds();
    int endLabelStyle = endLabelEditPart.getFigure().getFont().getFontData()[0].getStyle();

    // check label position and bendpoints
    assertPointsEquals("Wrong bendpoints after migration:", bendPointsExpected, bendPoints);
    assertLabelBoundsEquals("Wrong begin label bounds after migration:", beginLabelBoundsExpected, beginLabelBounds);
    assertLabelBoundsEquals("Wrong middle label bounds after migration:", middleLabelBoundsExpected, middleLabelBounds);
    assertLabelBoundsEquals("Wrong end label bounds after migration:", endLabelBoundsExpected, endLabelBounds);
    assertEquals("Wrong begin label style after migration", beginLabelStyleExpected, beginLabelStyle);
    assertEquals("Wrong end label style after migration", endLabelStyleExpected, endLabelStyle);
  }
}
