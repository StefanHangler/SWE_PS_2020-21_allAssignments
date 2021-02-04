package a8se2020ws;

import a8se2020ws.Hangler_MoserSchwaiger.CodeReconstruction;
import a8se2020ws.assignment8_int.Reconstructor;

public class A8SE2020WS implements Reconstructor {
	private final CodeReconstruction codeReconstruction;

	public A8SE2020WS() {
		this.codeReconstruction = new CodeReconstruction();
	}

	@Override
	public String reconstructFromClass(Class c) {

		if(c == null)
			return null;

		return codeReconstruction.constructJavaCode(c);
	}

	@Override
	public String reconstructFromClassName(String fullClassname) throws ClassNotFoundException {

		if(fullClassname == null)
			return null;

		Class<?> clazz = Class.forName(fullClassname, true, this.getClass().getClassLoader());
		return codeReconstruction.constructJavaCode(clazz);
	}
}