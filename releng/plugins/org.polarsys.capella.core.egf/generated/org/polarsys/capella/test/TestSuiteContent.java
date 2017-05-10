//Generated with EGF 1.4.1.v20161010-1511
package org.polarsys.capella.test;

import org.eclipse.egf.common.helper.*;
import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;
import org.eclipse.emf.common.util.*;

public class TestSuiteContent {
  protected static String nl;

  public static synchronized TestSuiteContent create(String lineSeparator) {
    nl = lineSeparator;
    TestSuiteContent result = new TestSuiteContent();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "    tests.add(new Test_";
  protected final String TEXT_2 = "(this));" + NL;
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL;

  public TestSuiteContent() {
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

    paramDesc = new IQuery.ParameterDescription("genClass", "http://www.eclipse.org/emf/2002/GenModel#//GenClass");
    queryCtx = new HashMap<String, String>();
    List<Object> genClassList = QueryHelper.load(ctx, "org.eclipse.egf.pattern.query.EObjectInjectedContextQuery")
        .execute(paramDesc, queryCtx, ctx);

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

    stringBuffer.append(TEXT_3);
    stringBuffer.append(TEXT_4);
    return stringBuffer.toString();
  }

  public String orchestration(PatternContext ctx) throws Exception {
    InternalPatternContext ictx = (InternalPatternContext) ctx;

    method_generateContent(new StringBuffer(), ictx);

    if (ictx.useReporter()) {
      Map<String, Object> parameterValues = new HashMap<String, Object>();
      parameterValues.put("genClass", this.genClass);
      String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
      String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
      ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx, parameterValues);
    }
    return null;
  }

  protected java.lang.String eclassCondition = null;

  public void set_eclassCondition(java.lang.String object) {
    this.eclassCondition = object;
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

  protected void method_generateContent(final StringBuffer stringBuffer, final PatternContext ctx) throws Exception {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(genClass.getEcoreClass().getName());
    stringBuffer.append(TEXT_2);
    InternalPatternContext ictx = (InternalPatternContext) ctx;
    new Node.DataLeaf(ictx.getNode(), getClass(), "generateContent", stringBuffer.toString());
  }

  public boolean preCondition(PatternContext ctx) throws Exception {
    eclassCondition = (String) ctx.getValue("eclassCondition");

    if (!genClass.isAbstract()) {
      EClass eclass = genClass.getEcoreClass();
      EAnnotation annotation = eclass.getEAnnotation("http://www.eclipse.org/emf/2002/GenModel");
      if (annotation != null) {
        EMap<String, String> details = annotation.getDetails();
        String value = details.get("documentation");
        if ((value != null) && value.startsWith("@deprecated")) {
          return false;
        }
      }

      if (null != eclassCondition) {
        for (EClass superClass : eclass.getEAllSuperTypes()) {
          String superClassName = superClass.getName();
          if ((superClassName != null) && superClassName.equalsIgnoreCase(eclassCondition)) {
            return true;
          }
        }
      }
    }
    return false;

  }
}