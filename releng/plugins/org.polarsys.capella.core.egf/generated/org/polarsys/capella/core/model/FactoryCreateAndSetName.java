//Generated with EGF 1.6.4.202309111303
package org.polarsys.capella.core.model;

import org.eclipse.egf.common.helper.*;
import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;

public class FactoryCreateAndSetName {
  protected static String nl;

  public static synchronized FactoryCreateAndSetName create(String lineSeparator) {
    nl = lineSeparator;
    FactoryCreateAndSetName result = new FactoryCreateAndSetName();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t";
  protected final String TEXT_2 = "\t" + NL + "\t/**" + NL + "\t * Creates class and sets its name" + NL
      + "\t * (This method comes from a customization of the standard EMF generator)" + NL + "\t *" + NL
      + "\t * @param name_p : default name of created element" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_3 = " create";
  protected final String TEXT_4 = "(String name_p);";
  protected final String TEXT_5 = NL;

  public FactoryCreateAndSetName() {
    //Here is the constructor
    StringBuffer stringBuffer = new StringBuffer();

    // add initialisation of the pattern variables (declaration has been already done).

  }

  public String generate(Object argument) throws Exception {
    final StringBuffer stringBuffer = new StringBuffer();

    InternalPatternContext ctx = (InternalPatternContext) argument;
    Map<String, String> queryCtx = null;
    IQuery.ParameterDescription paramDesc = null;
    Node.Container currentNode = ctx.getNode();

    List<Object> genClassList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)

    for (Object genClassParameter : genClassList) {

      this.genClass = (org.eclipse.emf.codegen.ecore.genmodel.GenClass) genClassParameter;

      if (preCondition(ctx)) {
        ctx.setNode(new Node.Container(currentNode, getClass()));
        orchestration(ctx);
      }

    }
    ctx.setNode(currentNode);
    if (ctx.useReporter()) {
      ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
    }

    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_5);
    return stringBuffer.toString();
  }

  public String orchestration(PatternContext ctx) throws Exception {
    InternalPatternContext ictx = (InternalPatternContext) ctx;

    method_body(new StringBuffer(), ictx);

    if (ictx.useReporter()) {
      Map<String, Object> parameterValues = new HashMap<String, Object>();
      parameterValues.put("genClass", this.genClass);
      String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
      String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
      ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx, parameterValues);
    }
    return null;
  }

  protected org.eclipse.emf.codegen.ecore.genmodel.GenClass genClass = null;

  public void set_genClass(org.eclipse.emf.codegen.ecore.genmodel.GenClass object) {
    this.genClass = object;
  }

  public Map<String, Object> getParameters() {
    final Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("genClass", this.genClass);
    return parameters;
  }

  protected void method_body(final StringBuffer stringBuffer, final PatternContext ctx) throws Exception {

    //IN : GenClass genClass
    stringBuffer.append(TEXT_1);
    //IN : GenFeature namingAttribute
    stringBuffer.append(TEXT_2);
    stringBuffer.append(genClass.getTypeParameters());
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceTypeArguments());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_4);
    InternalPatternContext ictx = (InternalPatternContext) ctx;
    new Node.DataLeaf(ictx.getNode(), getClass(), "body", stringBuffer.toString());
  }

  public boolean preCondition(PatternContext ctx) throws Exception {
    return true;
  }
}